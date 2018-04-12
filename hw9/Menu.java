/*
NAME: Menu.java
DESCRIPTION: Parent menu class for main menu and secondary menu
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

//menu class
public class Menu {
    private String textStyle;

    public Menu() {
        this.textStyle = "-fx-font: 34 Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;";
    }

    //getter
    public String getTextStyle() {
        return this.textStyle;
    }
}
