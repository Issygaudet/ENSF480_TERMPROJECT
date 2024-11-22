package entity;
public class Ticket {
    public String ticketID;
    public Showtime showtime;
    public String seat;

    public Ticket(String ticketID, Movie movie, Theatre theatre, String date, Showtime showtime, String seat) {
        this.ticketID = ticketID;
        this.showtime = showtime;
        this.seat = seat;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
