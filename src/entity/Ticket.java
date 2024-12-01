package entity;

import java.util.Random;

public class Ticket {
    private String ticketID;
    private Showtime showtime;
    private String seat;
    private Movie movie;
    private Theatre theatre;
    private String date;

    public Ticket(String ticketID, Movie movie, Theatre theatre, String date, Showtime showtime, String seat) {
        this.ticketID = ticketID;
        this.movie = movie;
        this.theatre = theatre;
        this.date = date;
        this.showtime = showtime;
        this.seat = seat;
    }

    public Ticket(Movie movie, Theatre theatre, String date, Showtime showtime, String seat) {
        this.ticketID = new Random().nextInt(5) + "";
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
}
