package src.Entity;

public class Movie {
    private int movieId;
    private String name;
    private String genre;
    private int duration;

    public Movie(int movieId, String name, String genre, int duration) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}