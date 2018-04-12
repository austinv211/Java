/*
NAME: Austin Vargason
DESCRIPTION: Class for Game
AUTHOR: Austin Vargason
DATE MODIFIED: 4/12/18
 */

//homework 9 package
package hw9;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        this.scene = new Scene(new VBox(new Text("" + this.size)), 500, 500);

        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
