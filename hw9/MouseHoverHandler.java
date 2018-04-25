/*
NAME: MouseHoverHandler.java
DESCRIPTION: Class to store hover handler (mouse entered and exited)
AUTHOR: Austin Vargason
DATE MODIFIED: 4/24/18
 */

//homework 9 package
package hw9;

//imports
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MouseHoverHandler {

    //data fields
    private EventHandler<MouseEvent> mouseEnteredHandler;
    private EventHandler<MouseEvent> mouseExitedHandler;

    //constructor
    public MouseHoverHandler(Text text) {
        this.mouseEnteredHandler = initMouseEnteredHandler(text);
        this.mouseExitedHandler = initMouseExitedHandler(text);
    }

    //GETTERS
    public EventHandler<MouseEvent> getMouseEnteredHandler() {
        return this.mouseEnteredHandler;
    }

    public EventHandler<MouseEvent> getMouseExitedHandler() {
        return this.mouseExitedHandler;
    }

    //METHODS
    //text entered mouse event
    private EventHandler<MouseEvent> initMouseEnteredHandler(Text text) {

        EventHandler<MouseEvent> enteredEvent = (event -> {
            text.setFill(Color.WHITE);
            text.setStroke(Color.BLACK);
        });

        return enteredEvent;
    }

    //text exited mouse event
    private EventHandler<MouseEvent> initMouseExitedHandler(Text text) {

        EventHandler<MouseEvent> enteredEvent = (event -> {
            text.setFill(Color.BLACK);
        });

        return enteredEvent;
    }
}
