/*
NAME: Menu.java
DESCRIPTION: Parent menu class for main menu and secondary menu
AUTHOR: Austin Vargason
DATE MODIFIED: 4/24/18
 */

//homework 9 package
package hw9;

import javafx.stage.Stage;

//menu class
public class Menu {

    private String textStyle;
    private Stage stage;
    private String buttonStyle;
    private String backgroundStyle;

    public Menu(Stage primaryStage) {
        this.textStyle = "-fx-font: 34 Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;";
        this.buttonStyle = "-fx-font: 22 arial;" +
                "-fx-font-weight: bold;";
        this.backgroundStyle = "-fx-background-image: url('/resources/background.jpg');" +
                "-fx-background-repeat: stretch;";
        this.stage = primaryStage;
    }

    //getters
    public String getTextStyle() {
        return this.textStyle;
    }

    public String getButtonStyle() {
        return buttonStyle;
    }

    public Stage getStage() {
        return stage;
    }

    public String getBackgroundStyle() {
        return backgroundStyle;
    }
}
