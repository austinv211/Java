package hw9;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.Collections;

public class Card extends StackPane {
    //data fields
    private int cardSize;
    private int matchNumber;
    private String style;
    private boolean isFrontShowing;
    private boolean isMatched;
    private static int numberOfClicks;
    private RotateTransition cardFlip;

    public Card(String style, int size, int matchNumber) {
        if (size == 4) {
            this.cardSize = 100;
        }
        else if (size == 6) {
            this.cardSize = 75;
        }
        else {
            this.cardSize = 50;
        }

        this.style = style;
        numberOfClicks = 0;
        this.matchNumber = matchNumber;
        initCard();
        setCardFlip();
        setOnClick();
        this.isMatched = false;
        this.style = style;
        this.isFrontShowing = true;
    }

    //getters
    public boolean isFrontShowing() {
        return isFrontShowing;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public static int getNumberOfClicks() {
        return numberOfClicks;
    }

    public boolean isMatched() {
        return isMatched;
    }

    //setters

    public void setMatched(boolean matched) {
        isMatched = matched;

    }

    //method to get card group
    private void initCard() {

        //card parts
        Rectangle cardBody = new Rectangle(this.cardSize, this.cardSize * 1.5);

        cardBody.setFill(Color.WHITE);
        cardBody.setStroke(Color.BLACK);
        cardBody.setArcHeight(15);
        cardBody.setArcWidth(15);

        Ellipse cardFront = new Ellipse(10, 10);
        cardFront.setFill(Color.LIGHTBLUE);
        cardFront.setStroke(Color.BLACK);

        //picture style for the card
        String url = "/Images/" + this.style + "/" + this.matchNumber + ".jpg";
        ImageView cardBack = new ImageView(new Image(url));
        cardBack.setFitHeight(this.cardSize - 20);
        cardBack.setFitWidth(this.cardSize - 20);
        cardBack.setPreserveRatio(true);

        this.getChildren().add(cardBack);
        this.getChildren().add(cardBody);
        this.getChildren().add(cardFront);

    }

    private void setOnClick() {
        this.setOnMouseClicked(event -> {
                playCardFlip();
        });
    }

    //set flipping animation for card
    private void setCardFlip () {

        RotateTransition cardFlip = new RotateTransition(Duration.millis(150), this.getChildren().get(1));

        //rotation properties
        cardFlip.setAxis(Rotate.Y_AXIS); //rotate about the y axis of the card
        cardFlip.setFromAngle(0);
        cardFlip.setToAngle(180);

        cardFlip.setCycleCount(1);

        this.cardFlip = cardFlip;
    }

    public void playCardFlip() {

        if (!this.isMatched) {
            numberOfClicks++;
            this.isFrontShowing = !isFrontShowing();
            this.cardFlip.play();

            ObservableList<Node> paneList = FXCollections.observableArrayList(this.getChildren());

            paneList.get(2).setVisible(false);

            Collections.swap(paneList, 0, 2);

            PauseTransition pauseTransition = new PauseTransition();
            pauseTransition.setDuration(Duration.millis(100));
            pauseTransition.setOnFinished(event -> {
                paneList.get(2).setVisible(true);
                this.getChildren().setAll(paneList);
            });

            pauseTransition.play();

        }
    }

}
