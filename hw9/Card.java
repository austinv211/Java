package hw9;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Card {
    //data fields
    private Group cardGroup;
    private String style;

    public Card(String style) {
        this.style = style;
        this.cardGroup = initCardGroup();
    }

    public Group getCardGroup() {
        return cardGroup;
    }

    //method to get card group
    private Group initCardGroup() {

        //group to store the contents of a card
        Group card = new Group();

        //stackPane to store card
        StackPane cardPane = new StackPane();

        //card parts
        Rectangle cardBody = new Rectangle(100, 160);
        cardBody.setFill(Color.WHITE);
        cardBody.setStroke(Color.GRAY);
        cardBody.setArcHeight(15);
        cardBody.setArcWidth(15);

        Ellipse cardFront = new Ellipse(30, 30);
        cardFront.setFill(Color.LIGHTBLUE);
        cardFront.setStroke(Color.BLACK);
        cardFront.setCenterX(cardBody.getX());
        cardFront.setCenterY(cardBody.getY());

        //add star
        //creating path.
        Path path = new Path();


        cardPane.getChildren().add(cardBody);
        cardPane.getChildren().add(cardFront);

        //add parts to group
        card.getChildren().add(cardPane);

        //return the card group
        return card;
    }


}
