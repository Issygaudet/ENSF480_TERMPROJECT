package entity;

public class Showtime {
    private int showtimeId;
    private int movieId;
    private Movie movie;
    private String time;  // Change from String to Time
    private Theatre theatre;

    // Constructor updated to use java.sql.Time for time
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

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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

    public String getTime() {
        return time;  // Return time as Time object
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Utility method to get formatted time as String if needed
    public String getFormattedTime() {
        return time.toString();  // Returns the time in HH:mm:ss format
    }
}
