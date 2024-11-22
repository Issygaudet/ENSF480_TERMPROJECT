package entity;
public class Ticket {
    public String ticketID;
    public Movie movie;
    public Theatre theatre;
    public String date;
    public Showtime showtime;
    public String seat;

    public Ticket(String ticketID, Movie movie, Theatre theatre, String date, Showtime showtime, String seat) {
        this.ticketID = ticketID;
        this.movie = movie;
        this.theatre = theatre;
        this.date = date;
        this.showtime = showtime;
        this.seat = seat;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
