package nl.marcovp.avans.cavanz.Domain;

/**
 * Created by Sander on 3/29/2018.
 */

public class Ticket {
    private final String TAG = getClass().getSimpleName();

    private int seatNumber;
    private int rowNumber;
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
}
