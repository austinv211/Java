package hw9;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public long getMillisecondsTotal() {
        return this.millisecondsTotal;
    }

    public StringProperty getTime() {
        return this.time;
    }

    public void setTime(long milliseconds) {
        this.millisecondsTotal = milliseconds;
        this.minutes = (int)(milliseconds / 1000) / 60;
        this.seconds = (int)(milliseconds / 1000) % 60;
        this.millisecondsLeft = (int)this.millisecondsTotal - (this.minutes * 60000) - (this.seconds * 1000);
        this.time.setValue(String.format("%02d.%02d.%03d", this.minutes, this.seconds, this.millisecondsLeft));
    }


}
