/*
NAME: Austin Vargason
DESCRIPTION: Class for Game
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

//imports
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;

//GameScene class
public class MatchGame {

    //data fields
    private String style;
    private Scene scene;
    private int size;
    private Stage stage;
    private StringProperty time;
    private Timeline timeline;
    private IntegerProperty score;
    private int maxScore;

    //constructor
    public MatchGame(int size, String style, Stage stage) {
        this.score = new SimpleIntegerProperty(0);
        this.size = size;
        this.maxScore = (int)((Math.pow(size, 2)) / 2);
        this.time = new SimpleStringProperty("00");
        this.stage = stage;
        this.style = style;
        this.scene = initGameScene();
        this.stage.setScene(this.scene);
        this.stage.setResizable(true);
        this.stage.show();
    }

    //methods
    //method to build the scene of the game
    private Scene initGameScene() {


        BorderPane borderPane = new BorderPane();
        GridPane cardGrid = new GridPane();


        int numberOfCards = this.size * this.size;

        //cards
        ArrayList<Card> cardArrayList = new ArrayList<Card>();


        for (int i = 1; i <= (numberOfCards / 2); i++) {
            cardArrayList.add(new Card(this.style, this.size, i));
            cardArrayList.add(new Card(this.style, this.size, i));
        }

        //shuffle card list
        Collections.shuffle(cardArrayList);

        //iterator for card Array List
        int cardCounter = 0;
        //add to cardGrid
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                cardGrid.add(cardArrayList.get(cardCounter), j, i);

                cardCounter++;
            }
        }

        //set the cardGrid Settings
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.setHgap(5.0);
        cardGrid.setVgap(5.0);
        cardGrid.setPadding(new Insets(20, 20, 20, 20));


        //set the on mouse clicked for the card
        cardGrid.setOnMouseClicked(event -> {
            getMatches(cardGrid);
        });

        //side Panes
        VBox bottomPane = new VBox();

        //text to hold the score
        Text scoreText = new Text("" + this.score); //initialize score text
        scoreText.textProperty().bind(Bindings.convert(this.score));
        scoreText.setStyle("-fx-font: 24 Arial;"); //set the text style
        Label labelForScore = new Label("Score: ", scoreText);
        labelForScore.setContentDisplay(ContentDisplay.RIGHT);
        labelForScore.setStyle("-fx-font: 24 Arial;");


        //create a group to hold the label and the text
        Group scoreGroup = new Group(labelForScore, scoreText);


        //text to hold the time
        Text timerText = new Text("");
        GameTimer gameTimer = new GameTimer();
        timerText.textProperty().bind(Bindings.convert(gameTimer.getTime()));
        Label timerLabel = new Label("Time: ", timerText);
        timerLabel.setContentDisplay(ContentDisplay.RIGHT);


        //group for label and timer
        Group timeGroup = new Group(timerLabel, timerText);
        timeGroup.setStyle("-fx-font: 24 Arial");

        EventHandler<ActionEvent> eventHandler = event -> {
            gameTimer.setTime(gameTimer.getMillisecondsTotal() + 1);
            this.time = gameTimer.getTime();
        };

        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), eventHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //add to left and right pane
        bottomPane.getChildren().add(scoreGroup);
        bottomPane.getChildren().add(timeGroup);

        //set the nodes of the borderPane
        borderPane.setCenter(cardGrid);
        borderPane.setBottom(bottomPane);

        //add to game scene
        Scene gameScene = new Scene(borderPane);

        //return the gamescene
        return gameScene;
    }

    //method to get the matches and additional pause transition
    private void getMatches(GridPane cardGrid) {

        //new array list for the selected cards
        ArrayList<Card> selectedCards = new ArrayList<>();

        //if the number of clicks is evenly divisible by 2
        if ((Card.getNumberOfClicks() % 2) == 0) {
            //foreach node in the cardGrid
            for (Node node : cardGrid.getChildren()) {
                //cast the card node to a card
                Card card = (Card) node;

                //if the front of the card is showing and the card isn't matched, add to selected cards
                if (!(card.isFrontShowing()) && !card.isMatched()) {
                    selectedCards.add(card);
                }

            }

            //if the size of the selected cards is greater than 0
            if (selectedCards.size() > 0) {

                //if the match numbers of the cards are equal, add to matches, and set isMatched to true
                if (selectedCards.get(0).getMatchNumber() == selectedCards.get(1).getMatchNumber()) {
                    score.setValue(this.score.getValue() + 1);

                    for (Card selectedCard : selectedCards) {
                        selectedCard.setMatched(true);
                    }

                    if(score.getValue() == maxScore) {
                        getWinScene();
                    }
                }
                //if not a match, pause and flip the card back
                else {
                    //play the pause transition then flip the card back
                    for (Card selectedCard : selectedCards) {
                        //PauseTransition to pause the flipping back of the card
                        PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));
                        pauseTransition.setOnFinished(event -> {
                            selectedCard.playCardFlip();
                        });
                        pauseTransition.play();
                    }
                }
            }
        }
    }

    private void getWinScene() {
        timeline.stop();
        VBox winBox = new VBox();
        Text winText = new Text("You Won!");
        winText.setStyle("-fx-font: 30 Arial; -fx-font-weight: bold");
        Text timeText = new Text("You found " + this.maxScore + " pairs in " + this.time.getValue());
        timeText.setStyle("-fx-font: 30 Arial;");

        Button playAgainButton = new Button("Play again");
        Button quitButton = new Button("Quit");

        quitButton.setOnMouseClicked(event -> {
            System.exit(0);
        });

        playAgainButton.setOnMouseClicked(event -> {
            this.stage.close();
            MainMenu mainMenu = new MainMenu(this.stage);
            Scene startMenuScene = mainMenu.getScene();
            this.stage.setScene(startMenuScene);
            this.stage.setTitle("Main");
            this.stage.setResizable(false);
            this.stage.show();
        });

        playAgainButton.setStyle("-fx-font: 22 arial;-fx-font-weight: bold;");
        quitButton.setStyle("-fx-font: 22 arial;-fx-font-weight: bold;");

        winBox.getChildren().add(winText);
        winBox.getChildren().add(timeText);
        winBox.getChildren().add(playAgainButton);
        winBox.getChildren().add(quitButton);

        winBox.setAlignment(Pos.CENTER);
        winBox.setSpacing(50);

        Scene winScene = new Scene(winBox, 500, 500);
        this.stage.setScene(winScene);
    }


}
