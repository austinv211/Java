/*
NAME: SecondaryMenu.java
DESCRIPTION: Class for secondary menu to choose game options
AUTHOR: Austin Vargason
DATE MODIFIED: 4/24/18
 */

//homework 9
package hw9;

//imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//secondary menu class
public class SecondaryMenu extends Menu {

    //data fields
    private Scene scene;
    private int sizeChoice;
    private String styleChoice;
    private boolean isSizeChoiceChosen;
    private boolean isStyleChoiceChosen;

    //constructors
    public SecondaryMenu(Stage stage) {
        super(stage);
        this.scene = getSecondaryMenuScene();
        this.isSizeChoiceChosen = false;
        this.isStyleChoiceChosen = false;
        super.getStage().setScene(this.scene);
    }

    //getters
    public Scene getScene() {
        return this.scene;
    }

    public int getSizeChoice() {
        return sizeChoice;
    }

    public String getStyleChoice() {
        return styleChoice;
    }

    //setters
    private void setSizeChoice(int sizeChoice) {
        this.sizeChoice = sizeChoice;
    }

    private void setStyleChoice(String styleChoice) {
        this.styleChoice = styleChoice;
    }

    public void setSizeChoiceChosen(boolean sizeChoiceChosen) {
        this.isSizeChoiceChosen = sizeChoiceChosen;
    }

    public void setStyleChoiceChosen(boolean styleChoiceChosen) {
        this.isStyleChoiceChosen = styleChoiceChosen;
    }

    //METHODS
    //get secondary Menu Scene
    private Scene getSecondaryMenuScene() {

        //borderpane to hold the vbox
        BorderPane secondaryMenuPane = new BorderPane();

        //create start game button
        HBox bottom = new HBox();
        Button startGameButton = new Button("Start Game"); //set button
        startGameButton.setVisible(false); //make invisible
        startGameButton.setStyle(super.getButtonStyle()); //set style
        bottom.getChildren().add(startGameButton); //add to hbox
        bottom.setAlignment(Pos.CENTER_RIGHT); //set pos
        bottom.setPadding(new Insets(10, 30, 30, 10)); //set the padding

        //set startGameButton handler
        startGameButton.setOnMouseClicked(event -> {
            new MatchGame(this.sizeChoice, this.styleChoice, super.getStage()); //go to secondary menu
        });

        //add to borderpane
        secondaryMenuPane.setBottom(bottom);


        //style options vbox
        VBox styleOptions = new VBox();
        styleOptions.setAlignment(Pos.CENTER);

        //text for style options
        Text music = new Text("Music");
        Text shapes = new Text("Movies");
        Text cities = new Text("Cities");


        //add text to styleOptions
        styleOptions.getChildren().add(music);
        styleOptions.getChildren().add(cities);
        styleOptions.getChildren().add(shapes);

        //set the style of the style options
        styleOptions.setStyle(super.getTextStyle());

        //foreach node, set the event handlers
        for (Node node : styleOptions.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text)node; //cast to text

                //set hover
                text.setOnMouseEntered(new MouseHoverHandler(text).getMouseEnteredHandler());
                text.setOnMouseExited(new MouseHoverHandler(text).getMouseExitedHandler());


                //set when clicked
                text.setOnMouseClicked(event -> {

                    //get current menu choice
                    getStyleMenuChoiceVariables(text);

                    //set all the other options to be not underlined
                    for (Node innerNode : styleOptions.getChildren()) {
                        if (node instanceof Text) {
                            Text innerText = (Text)innerNode;
                            innerText.setUnderline(false);
                        }
                    }

                    //set chose text to be underlined
                    text.setUnderline(true);

                    //determine whether to show start button
                    if (this.isStyleChoiceChosen && this.isSizeChoiceChosen) {
                        startGameButton.setVisible(true);
                    }

                });
            }
        }

        //label for the style options
        Label labelForStyle = new Label("Choose a theme", styleOptions);
        labelForStyle.setStyle("-fx-font: 20 Arial;"); //set the style of the label

        //create a group for style options
        Group styleOptionsGroup = new Group(labelForStyle, styleOptions);

        //size options VBox
        VBox sizeOptions =  new VBox();
        sizeOptions.setAlignment(Pos.CENTER); //set the alignment

        //text options for size options of games
        Text fourByFour = new Text("4 x 4");
        Text sixBySix = new Text("6 x 6");
        Text tenByTen = new Text("10 x 10");

        //add the text to the size options
        sizeOptions.getChildren().add(fourByFour);
        sizeOptions.getChildren().add(sixBySix);
        sizeOptions.getChildren().add(tenByTen);

        //set the text style
        sizeOptions.setStyle(super.getTextStyle());

        //foreach node, set the event handlers
        for (Node node : sizeOptions.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text)node;//cast to text

                //set hover options
                text.setOnMouseEntered(new MouseHoverHandler(text).getMouseEnteredHandler());
                text.setOnMouseExited(new MouseHoverHandler(text).getMouseExitedHandler());

                //set event when clicked
                text.setOnMouseClicked(event -> {

                    //get current size choice
                    getSizeMenuChoiceVariables(text);

                    //set all the other nodes to be not underlined
                    for (Node innerNode : sizeOptions.getChildren()) {
                        if (node instanceof Text) {
                            Text innerText = (Text)innerNode;
                            innerText.setUnderline(false);
                        }
                    }

                    //underline current choice
                    text.setUnderline(true);

                    //determine whether to show start button
                    if (this.isStyleChoiceChosen && this.isSizeChoiceChosen) {
                        startGameButton.setVisible(true);
                    }

                });
            }
        }

        //set label for size choice
        Label labelForSize = new Label("Choose a Size", sizeOptions);
        labelForSize.setStyle("-fx-font: 20 Arial;"); //set style for label

        //create a group for the size options
        Group sizeOptionsGroup = new Group(labelForSize, sizeOptions);

        //create a vbox to store the style options and size options
        VBox secondaryMenu = new VBox(styleOptionsGroup, sizeOptionsGroup);
        secondaryMenu.setAlignment(Pos.CENTER); //set the alignment
        secondaryMenu.setSpacing(20); //set the spacing

        //set the center to the secondary menu vbox
        secondaryMenuPane.setCenter(secondaryMenu);


        //create the scene
        Scene secondaryMenuScene = new Scene(secondaryMenuPane, 768, 576);

        secondaryMenuPane.setStyle(super.getBackgroundStyle());

        //return the menu scene
        return secondaryMenuScene;
    }

    //get the current size choice
    private void getSizeMenuChoiceVariables (Text text) {
        String sizeString = text.getText();
        sizeString = sizeString.substring(0,2);
        sizeString = sizeString.trim();
        int size = Integer.parseInt(sizeString);
        setSizeChoice(size);
        setSizeChoiceChosen(true);
    }

    //get the current style choice
    private void getStyleMenuChoiceVariables (Text text) {
        setStyleChoiceChosen(true);
        String style = text.getText();
        setStyleChoice(style);
        setStyleChoiceChosen(true);
    }

}
