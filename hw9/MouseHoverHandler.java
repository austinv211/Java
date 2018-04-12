package hw9;

//imports
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MouseHoverHandler {

    private EventHandler<MouseEvent> mouseEnteredHandler;
    private EventHandler<MouseEvent> mouseExitedHandler;


    public MouseHoverHandler(Text text) {
        this.mouseEnteredHandler = initMouseEnteredHandler(text);
        this.mouseExitedHandler = initMouseExitedHandler(text);
    }

    //GETTERS

    public EventHandler<MouseEvent> getMouseEnteredHandler() {
        return mouseEnteredHandler;
    }

    public EventHandler<MouseEvent> getMouseExitedHandler() {
        return mouseExitedHandler;
    }

    //METHODS

    //text entered mouse event
    public EventHandler<MouseEvent> initMouseEnteredHandler(Text text) {

        EventHandler<MouseEvent> enteredEvent = (event -> {
            text.setFill(Color.WHITE);
            text.setStroke(Color.BLACK);
        });

        return enteredEvent;
    }

    //text exited mouse event
    public EventHandler<MouseEvent> initMouseExitedHandler(Text text) {

        EventHandler<MouseEvent> enteredEvent = (event -> {
            text.setFill(Color.BLACK);
        });

        return enteredEvent;
    }
}