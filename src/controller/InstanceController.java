package controller;

import entity.Seat;
import entity.Ticket;
import entity.TicketCart;
import entity.UserOrdinary;
import entity.UserRegistered;

public class InstanceController {
    private Seat selectedSeat;
    private int selectedShowtimeId;
    private UserOrdinary user;
    private TicketCart ticketCart = new TicketCart();

    private static InstanceController instance = null;

    private InstanceController() {
        selectedSeat = null;
        if (user instanceof UserRegistered) {
            ((UserRegistered) user).selectSpecialAccessSeats();
        }
    }

    public static InstanceController getInstance() {
        if (instance == null) {
            instance = new InstanceController();
        }
        return instance;
    }

    public void setUser(UserOrdinary user) {
        this.user = user;
    }

    public UserOrdinary getUser() {
        return user;
    }

    public void setSelectedSeat(Seat seat) {
        selectedSeat = seat;
    }

    public Seat getSelectedSeat() {
        return selectedSeat;
    }
}
