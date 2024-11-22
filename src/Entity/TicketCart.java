package entity;

import java.util.ArrayList;

public class TicketCart {
    public ArrayList<Ticket> tickets_in_cart = new ArrayList<Ticket>();
    public float totalPrice = 0;



    public void addToCart(Ticket ticket) {
        tickets_in_cart.add(ticket);
    }
    public void removeFromCart(Ticket ticket) {
        tickets_in_cart.remove(ticket);
    }
    public ArrayList<Ticket> getTicketsInCart() {
        return tickets_in_cart;
    }


}
