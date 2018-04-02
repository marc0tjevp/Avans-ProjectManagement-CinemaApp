package nl.marcovp.avans.cavanz.Domain;

import java.io.Serializable;

/**
 * Created by Sander on 3/29/2018.
 */

public class Hall implements Serializable {
    private final String TAG = getClass().getSimpleName();

    private String hallNumber;
    private int seatPerRow;
    private int rowAmount;

<<<<<<< HEAD
    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
=======
    public Hall(int hallNumber, int seatPerRow, int rowAmount) {
        this.hallNumber = hallNumber;
        this.seatPerRow = seatPerRow;
        this.rowAmount = rowAmount;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
>>>>>>> develop
        this.hallNumber = hallNumber;
    }

    public int getSeatPerRow() {
        return seatPerRow;
    }

    public void setSeatPerRow(int seatPerRow) {
        this.seatPerRow = seatPerRow;
    }

    public int getRowAmount() {
        return rowAmount;
    }

    public void setRowAmount(int rowAmount) {
        this.rowAmount = rowAmount;
    }
}
