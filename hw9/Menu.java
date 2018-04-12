package hw9;

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
