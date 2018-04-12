//homework 9 package
package hw9;

//imports
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//class for the memory game
public class MemoryGame extends Application {

    //override the start method
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene startMenuScene = getStartMenuScene(primaryStage);
        primaryStage.setScene(startMenuScene);
        primaryStage.setTitle("Main");
        primaryStage.show();
    }

    //Methods to build the start menu
    //method to build startMenuScene
    public static Scene getStartMenuScene(Stage primaryStage) {
        BorderPane startMenuBackgroundPane = getStartMenuBorderPane(primaryStage);
        Scene startMenuScene = new Scene(startMenuBackgroundPane, 500, 500);

        return  startMenuScene;
    }

    //method to build startMenuBorderPane
    public static BorderPane getStartMenuBorderPane(Stage primaryStage) {
        BorderPane startMenuBorderPane = new BorderPane();

        //add menu to center
        VBox menuItemsPane = getMenuItemsVbox(primaryStage);

        //set the top
        Text title = new Text("Match Game");
        title.setStyle("-fx-font: 38 Arial;" +
                "-fx-font-weight: bold;");
        startMenuBorderPane.setTop(title);

        startMenuBorderPane.setAlignment(title, Pos.CENTER);

        //add vbox to center of borderpane
        startMenuBorderPane.setCenter(menuItemsPane);


        return startMenuBorderPane;
    }

    //method to build menu items group
    public static VBox getMenuItemsVbox(Stage primaryStage) {
        //create Vbox to Store menu items
        VBox menuItemsPane = new VBox();

        //text to add
        Text playGameText = new Text("Play Game");
        Text exitGameText = new Text("Quit Game");

        //set text Style
        String textStyle = "-fx-font: 34 Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;";

        playGameText.setStyle(textStyle);
        exitGameText.setStyle(textStyle);

        //set actions
        playGameText.setOnMouseEntered(getMouseEnteredHandler(playGameText));
        playGameText.setOnMouseExited(getMouseExitedHandler(playGameText));
        exitGameText.setOnMouseEntered(getMouseEnteredHandler(exitGameText));
        exitGameText.setOnMouseExited(getMouseExitedHandler(exitGameText));

        playGameText.setOnMouseClicked(event -> {
            primaryStage.setScene(getSecondaryMenuScene(textStyle));
        });

        //set style and alignment
        menuItemsPane.setAlignment(Pos.CENTER);

        //add to Vbox
        menuItemsPane.getChildren().add(playGameText);
        menuItemsPane.getChildren().add(exitGameText);

        return menuItemsPane;
    }

    //get secondary Menu Scene
    public static Scene getSecondaryMenuScene(String textStyle) {

        //borderpane to hold the vbox
        BorderPane secondaryMenuPane = new BorderPane();

        //style options vbox
        VBox styleOptions = new VBox();
        styleOptions.setAlignment(Pos.CENTER);

        //text for style options
        Text music = new Text("Music");
        Text cities = new Text("Cities");
        Text shapes = new Text("Shapes");

        //add text to styleOptions
        styleOptions.getChildren().add(music);
        styleOptions.getChildren().add(cities);
        styleOptions.getChildren().add(shapes);

        //set the style of the style options
        styleOptions.setStyle(textStyle);

        //foreach node, set the event handlers
        for (Node node : styleOptions.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text)node; //cast to text

                //set hover
                text.setOnMouseEntered(getMouseEnteredHandler(text));
                text.setOnMouseExited(getMouseExitedHandler(text));

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
        sizeOptions.setStyle(textStyle);

        //foreach node, set the event handlers
        for (Node node : sizeOptions.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text)node;//cast to text

                //set hover options
                text.setOnMouseEntered(getMouseEnteredHandler(text));
                text.setOnMouseExited(getMouseExitedHandler(text));

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
        Scene secondaryMenuScene = new Scene(secondaryMenuPane, 500, 500);

        //return the menu scene
        return secondaryMenuScene;
    }

    //get the current size choice
    public static void getSizeMenuChoiceVariables (Text text) {
        String sizeString = text.getText();
        sizeString = sizeString.substring(0,2);
        sizeString = sizeString.trim();
        int size = Integer.parseInt(sizeString);
        System.out.println(size);
    }

    //get the current style choice
    public static void getStyleMenuChoiceVariables (Text text) {
        String style = text.getText();
        System.out.println(style);
    }


    //general methods

    //text entered mouse event
    public static EventHandler<MouseEvent> getMouseEnteredHandler(Text text) {

        EventHandler<MouseEvent> enteredEvent = (event -> {
            text.setFill(Color.WHITE);
            text.setStroke(Color.BLACK);
        });

        return enteredEvent;
    }

    //text exited mouse event
    public static EventHandler<MouseEvent> getMouseExitedHandler(Text text) {

        EventHandler<MouseEvent> enteredEvent = (event -> {
            text.setFill(Color.BLACK);
        });

        return enteredEvent;
    }


    //main method for testing in IDE
    public static void main(String[] args) {
        Application.launch(args);
    }
}
