package controller;

import java.sql.SQLException;

import database.ControlDatabase;
import database.WriteDatabase;
import entity.*;

public class InstanceController {
    private Seat selectedSeat;
    private int selectedShowtimeId;
    private UserOrdinary user;
    private TicketCart ticketCart;

    private static InstanceController instance = null;

    private InstanceController() {
        ticketCart = new TicketCart();
    }

    public static InstanceController getInstance() {
        if (instance == null) {
            instance = new InstanceController();
        }
        return instance;
    }

    // Manage user
    public void setUser(UserOrdinary user) {
        this.user = user;
        if (user instanceof UserRegistered) {
            ((UserRegistered) user).selectSpecialAccessSeats();
        }
    }

    public UserOrdinary getUser() {
        return user;
    }

    // Fetch user from database
    public UserRegistered fetchUser(int userId) {
        UserRegistered registeredUser = ControlDatabase.getInstance().getUserRegistered(userId);
        setUser(registeredUser);
        return registeredUser;
    }

    // Save user to database
    public void saveUser() {
    if (user instanceof UserRegistered) {
        WriteDatabase writeDatabase = new WriteDatabase();
        try {
            writeDatabase.saveSingleUser((UserRegistered) user);
            System.out.println("User saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    // Manage seat selection
    public void setSelectedSeat(Seat seat) {
        this.selectedSeat = seat;
    }

    public Seat getSelectedSeat() {
        return selectedSeat;
    }

    // Manage tickets
    public TicketCart getTicketCart() {
        return ticketCart;
    }
    
    public void logout() {
        this.user = null;
        this.selectedSeat = null;
        this.selectedShowtimeId = 0;
        this.ticketCart = new TicketCart(); // Reset the ticket cart
    }
}
