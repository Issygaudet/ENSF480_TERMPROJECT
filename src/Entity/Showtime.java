package entity;

public class Showtime {
    private int showtimeId;
    private int movieId;
    private Movie movie;
    private String time;
    private Theatre theatre;

    public Showtime(int showtimeId, int movieId, Movie movie, Theatre theatre, String time) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.movie = movie;
        this.theatre = theatre;
        this.time = time;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }
    
    public String getTime() {
        return time;
    }
}