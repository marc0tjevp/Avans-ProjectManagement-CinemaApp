package nl.marcovp.avans.cavanz.Domain;

/**
 * Created by Sander on 3/29/2018.
 */

public class Hall {
    private final String TAG = getClass().getSimpleName();

    private String hallNumber;
    private int seatPerRow;
    private int rowAmount;

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
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
