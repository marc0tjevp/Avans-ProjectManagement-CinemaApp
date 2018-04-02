package nl.marcovp.avans.cavanz.Domain;

/**
 * Created by marco on 4/2/18.
 */

public enum TicketType {
    TICKET_ADULT(11.50, "Volwassenen"),
    TICKET_KIDS(8.50, "Kinderen"),
    TICKET_STUDENT(10.00, "Studenten");

    private double ticketPrice;
    private String ticketTypeName;

    TicketType(double ticketPrice, String ticketTypeName) {
        this.ticketPrice = ticketPrice;
        this.ticketTypeName = ticketTypeName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }
}
