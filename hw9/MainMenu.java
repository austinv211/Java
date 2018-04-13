/*
NAME: MainMenu.java
DESCRIPTION: Class for Main Menu with playgame option, launches secondary option menu
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

//imports
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//main menu class
public class MainMenu extends Menu {

    //data fields
    private Scene scene;

    //constructors
    public MainMenu (Stage stage) {
        super(stage);
        this.scene = getStartMenuScene();
    }

    //GETTERS
    public Scene getScene() {
        return this.scene;
    }

    //Methods to build the start menu
    //method to build startMenuScene
    private Scene getStartMenuScene() {
        BorderPane startMenuBackgroundPane = getStartMenuBorderPane();
        Scene startMenuScene = new Scene(startMenuBackgroundPane, 768, 576);

        return  startMenuScene;
    }

    //method to build startMenuBorderPane
    private BorderPane getStartMenuBorderPane() {
        BorderPane startMenuBorderPane = new BorderPane();

        //add menu to center
        VBox menuItemsPane = getMenuItemsVbox();

        //set the top
        Text title = new Text("Match Game");
        title.setStyle("-fx-font: 38 Arial;" +
                "-fx-font-weight: bold;");
        startMenuBorderPane.setTop(title);

        startMenuBorderPane.setAlignment(title, Pos.CENTER);

        //add vbox to center of borderpane
        startMenuBorderPane.setCenter(menuItemsPane);

        startMenuBorderPane.setStyle(super.getBackgroundStyle());

        return startMenuBorderPane;
    }

    //method to build menu items group
    private VBox getMenuItemsVbox() {
        //create Vbox to Store menu items
        VBox menuItemsPane = new VBox();

        //text to add
        Text playGameText = new Text("Play Game");
        Text exitGameText = new Text("Quit Game");

        playGameText.setStyle(super.getTextStyle());
        exitGameText.setStyle(super.getTextStyle());

        //set actions
        playGameText.setOnMouseEntered(new MouseHoverHandler(playGameText).getMouseEnteredHandler());
        playGameText.setOnMouseExited(new MouseHoverHandler(playGameText).getMouseExitedHandler());
        exitGameText.setOnMouseEntered(new MouseHoverHandler(exitGameText).getMouseEnteredHandler());
        exitGameText.setOnMouseExited(new MouseHoverHandler(exitGameText).getMouseExitedHandler());

        //set the play game event handler
        playGameText.setOnMouseClicked(event -> {
            new SecondaryMenu(super.getStage());
        });

        //if the quit game button is clicked, exit the program
        exitGameText.setOnMouseClicked(event -> {
            System.exit(0);
        });

        //set style and alignment
        menuItemsPane.setAlignment(Pos.CENTER);

        //add to Vbox
        menuItemsPane.getChildren().add(playGameText);
        menuItemsPane.getChildren().add(exitGameText);

        return menuItemsPane;
    }

}
