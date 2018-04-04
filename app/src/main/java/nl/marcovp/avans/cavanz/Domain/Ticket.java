package nl.marcovp.avans.cavanz.Domain;

import java.io.Serializable;

/**
 * Created by Sander on 3/29/2018.
 */

public class Ticket implements Serializable {
    private final String TAG = getClass().getSimpleName();

    private int seatNumber;
    private int rowNumber;
    private Seat seat;
    private Showing showing;
    private String name;
    private String surname;
    private String email;
    private double totalPrice;

    public Ticket(int seatNumber, int rowNumber, Showing showing, String name, String surname, String email, double totalPrice) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.showing = showing;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    public Ticket(Seat seat, Showing showing, String name, String surname, String email, double totalPrice) {
        this.seat = seat;
        this.showing = showing;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "seatNumber=" + seatNumber +
                ", rowNumber=" + rowNumber +
                ", showing=" + showing +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }


    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
