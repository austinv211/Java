/*
Name: Card.java
Description: Class for a card in the game
Author: Austin Vargas
Date Modified: 4/24/18
 */

//homework 9
package hw9;

//imports
import javafx.animation.Animation;
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

        //based on the size of the board, set the cardSize
        if (size == 4) {
            this.cardSize = 120;
        }
        else if (size == 6) {
            this.cardSize = 95;
        }
        else {
            this.cardSize = 75;
        }

        //initiate variables
        this.style = style;
        numberOfClicks = 0;
        this.matchNumber = matchNumber;
        initCard(); //initialize card
        setCardFlip(); //set the flip animation
        setOnClick(); //set what to do when clicked
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

    //set the matched data field
    public void setMatched(boolean matched) {
        isMatched = matched;

    }

    //method to get card group
    private void initCard() {
        //card parts

        //body for the card
        Rectangle cardBody = new Rectangle(this.cardSize, this.cardSize);

        //style for the card body
        cardBody.setFill(Color.WHITE);
        cardBody.setStroke(Color.BLACK);
        cardBody.setArcHeight(15);
        cardBody.setArcWidth(15);

        //front of the card
        Ellipse cardFront = new Ellipse(10, 10);
        cardFront.setFill(Color.LIGHTBLUE);
        cardFront.setStroke(Color.BLACK);

        //picture style for the back of the card
        //url is in the resources folder
        String url = "resources/Images/" + this.style + "/" + this.matchNumber + ".jpg";
        ImageView cardBack = new ImageView(new Image(url));
        cardBack.setFitHeight(this.cardSize - (this.cardSize / 10));
        cardBack.setFitWidth(this.cardSize - (this.cardSize / 10));
        cardBack.setPreserveRatio(true);

        //add the items to the cardPane
        this.getChildren().add(cardBack);
        this.getChildren().add(cardBody);
        this.getChildren().add(cardFront);

    }

    //method to set the on click behavior for the card
    private void setOnClick() {
        this.setOnMouseClicked(event -> {
            playCardFlip();
        });
    }

    //set flipping animation for card
    private void setCardFlip () {

        //rotation transition for card flip
        RotateTransition cardFlip = new RotateTransition(Duration.millis(250), this.getChildren().get(1));

        //rotation properties
        cardFlip.setAxis(Rotate.Y_AXIS); //rotate about the y axis of the card
        cardFlip.setFromAngle(0);
        cardFlip.setToAngle(180);
        cardFlip.setCycleCount(1);

        //set the objects cardFlip to the transition
        this.cardFlip = cardFlip;
    }

    //method to play the animation and properly switch the front and back of the card
    public void playCardFlip() {

        //if the card is not matched and the cardflip animation is not currently running
        if (!this.isMatched && !(this.cardFlip.getStatus().equals(Animation.Status.RUNNING))) {

            //increase the number of clicks
            numberOfClicks++;

            //set the the front showing to its contradiction
            this.isFrontShowing = !isFrontShowing();

            //play the transition
            this.cardFlip.play();

            //convert the pane children to an observable list
            ObservableList<Node> paneList = FXCollections.observableArrayList(this.getChildren());

            //set the items in the pane to be invisible
            paneList.get(0).setVisible(false);
            paneList.get(2).setVisible(false);

            //swap the items on the front and th eback of the card
            Collections.swap(paneList, 0, 2);

            //when the animation is done, set the items to visible and set the pane accordingly
            this.cardFlip.setOnFinished(event -> {
                paneList.get(0).setVisible(true);
                paneList.get(2).setVisible(true);
                this.getChildren().setAll(paneList);
            });

        }
    }

}
