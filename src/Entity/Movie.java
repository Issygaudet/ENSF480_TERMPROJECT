package Entity;

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

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

// no setters- done in db
}