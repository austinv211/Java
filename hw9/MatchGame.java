/*
NAME: Austin Vargason
DESCRIPTION: Class for Game
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

//imports
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    private Duration time;
    private IntegerProperty score;
    private int maxScore;

    //constructor
    public MatchGame(int size, String style, Stage stage) {
        this.score = new SimpleIntegerProperty(0);
        this.size = size;
        this.maxScore = (int)((Math.pow(size, 2)) / 2);
        this.time = new Duration(0);
        this.stage = stage;
        this.style = style;
        this.scene = initGameScene();
        this.stage.setScene(this.scene);
        this.stage.setResizable(true);
        this.stage.setFullScreen(true);
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
        cardGrid.setHgap(100 / this.size );
        cardGrid.setVgap(5.0);
        cardGrid.setPadding(new Insets(20, 20, 20, 20));


        //set the on mouse clicked for the card
        cardGrid.setOnMouseClicked(event -> {
            getMatches(cardGrid);
        });

        //side Panes
        VBox leftPane = new VBox();
        VBox rightPane = new VBox();

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
        Text timeText = new Text("" + time);
        timeText.setStyle("-fx-font: 24 Arial"); //set the text style

        //add to left and right pane
        leftPane.getChildren().add(scoreGroup);
        rightPane.getChildren().add(timeText);

        //set the nodes of the borderPane
        borderPane.setCenter(cardGrid);
        borderPane.setLeft(leftPane);
        borderPane.setRight(rightPane);

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
        if((Card.getNumberOfClicks() % 2) == 0) {
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


}
