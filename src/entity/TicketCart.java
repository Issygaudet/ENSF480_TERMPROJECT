package entity;

import database.ControlDatabase;

import java.util.ArrayList;

public class TicketCart {
    private ArrayList<Ticket> tickets_in_cart = new ArrayList<Ticket>();
    private float totalPrice = 0;


    public void addToCart(Ticket ticket) {
        tickets_in_cart.add(ticket);
        totalPrice += ticket.getShowtime().getMovie().getPrice();
    }
    public void removeFromCart(Ticket ticket) {
        tickets_in_cart.remove(ticket);
        totalPrice -= ticket.getShowtime().getMovie().getPrice();
    }
    public ArrayList<Ticket> getTicketsInCart() {
        return tickets_in_cart;
    }
    public float getTotalPrice() {
        return totalPrice;
    }
    public void checkout() {
        for (Ticket ticket : tickets_in_cart) {
            System.out.println(
                    "Booked ticket for " +
                            ticket.getShowtime().getMovie().getName() +
                            " at " + ticket.getShowtime().getTime() +
                            "(Ticket number: " + ticket.getTicketID() + ")");
            // Book ticket in database
            ControlDatabase.getInstance().bookTicket(ticket);
        }
        tickets_in_cart.clear();
        totalPrice = 0;
    }

}
