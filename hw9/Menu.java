/*
NAME: Menu.java
DESCRIPTION: Parent menu class for main menu and secondary menu
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

import javafx.stage.Stage;

//menu class
public class Menu {

    private String textStyle;
    private Stage stage;

    public Menu(Stage primaryStage) {
        this.textStyle = "-fx-font: 34 Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;";
        this.stage = primaryStage;
    }

    //getter
    public String getTextStyle() {
        return this.textStyle;
    }

    public Stage getStage() {
        return stage;
    }
}
