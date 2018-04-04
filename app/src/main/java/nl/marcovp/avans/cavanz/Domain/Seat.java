package nl.marcovp.avans.cavanz.Domain;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Sander on 4/4/2018.
 */

public class Seat implements Serializable {
    private boolean selected;
    private int x;
    private int y;
    private ImageView seat;
    private boolean isTaken = false;


    public Seat(int x, int y){
        this.x = x;
        this.y = y;

        if ((new Random().nextInt(26) == 13)){
            isTaken = true;
        }

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageView getSeat() {
        return seat;
    }

    public void setSeat(ImageView seat) {
        this.seat = seat;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}
