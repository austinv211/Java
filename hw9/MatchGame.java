/*
NAME: Austin Vargason
DESCRIPTION: Class for Game
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//GameScene class
public class MatchGame {

    //data fields
    private Scene scene;
    private int size;
    private Stage stage;

    //constructor
    public MatchGame(int size, Stage stage) {
        this.size = size;
        this.stage = stage;
        this.scene = initGameScene();

        this.stage.setScene(this.scene);
        this.stage.show();
    }

    //methods
    //method to build the scene of the game
    private Scene initGameScene() {
        GridPane cardGrid = new GridPane();

        //cards
        Card card1 = new Card("");
        cardGrid.add(card1.getCardGroup(), 0, 0);
        cardGrid.setAlignment(Pos.CENTER);

        //add to game scene
        Scene gameScene = new Scene(cardGrid, 768, 576);

        return gameScene;
    }
}
