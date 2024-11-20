package Entity;

public class Showtime {
    private int showtimeId;
    private int movieId;
    private String time;

    public Showtime(int showtimeId, int movieId, String time) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.time = time;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }


    public String getTime() {
        return time;
    }
}