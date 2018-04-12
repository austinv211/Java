package hw9;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Menu {

    private Scene scene;

    public MainMenu (Stage stage) {
        this.scene = getStartMenuScene(stage);
    }

    //GETTERS
    public Scene getScene() {
        return this.scene;
    }

    //Methods to build the start menu
    //method to build startMenuScene
    private Scene getStartMenuScene(Stage primaryStage) {
        BorderPane startMenuBackgroundPane = getStartMenuBorderPane(primaryStage);
        Scene startMenuScene = new Scene(startMenuBackgroundPane, 500, 500);

        return  startMenuScene;
    }

    //method to build startMenuBorderPane
    private BorderPane getStartMenuBorderPane(Stage primaryStage) {
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
    private VBox getMenuItemsVbox(Stage primaryStage) {
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

        playGameText.setOnMouseClicked(event -> {
            primaryStage.setScene(new SecondaryMenu().getScene());
        });

        //set style and alignment
        menuItemsPane.setAlignment(Pos.CENTER);

        //add to Vbox
        menuItemsPane.getChildren().add(playGameText);
        menuItemsPane.getChildren().add(exitGameText);

        return menuItemsPane;
    }

}
