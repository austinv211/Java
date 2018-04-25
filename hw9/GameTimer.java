/*
Name: GameTimer.java
Description: timer to increment time for game
Author: Austin Vargason
Date Modified: 4/24/18
*/

//homework 9 package
package hw9;

//imports
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class GameTimer {
    //data fields
    private int minutes;
    private int seconds;
    private long millisecondsTotal;
    private int millisecondsLeft;
    private StringProperty time;

    //constructor
    public GameTimer() {
        this.minutes = 0;
        this.seconds = 0;
        this.millisecondsLeft = 0;
        this.millisecondsTotal = 0;
        this.time = new SimpleStringProperty("" + this.minutes + this.seconds);
    }

    //getter for total milliseconds
    public long getMillisecondsTotal() {
        return this.millisecondsTotal;
    }

    //getter for string property time
    public StringProperty getTime() {
        return this.time;
    }

    //method to set the time string property
    public void setTime(long milliseconds) {
        this.millisecondsTotal = milliseconds;
        this.minutes = (int)(milliseconds / 1000) / 60;
        this.seconds = (int)(milliseconds / 1000) % 60;
        this.millisecondsLeft = (int)this.millisecondsTotal - (this.minutes * 60000) - (this.seconds * 1000);
        this.time.setValue(String.format("%02d.%02d.%03d", this.minutes, this.seconds, this.millisecondsLeft));
    }


}
