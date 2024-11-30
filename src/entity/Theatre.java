package entity;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private int theatreId;
    private String location;
    private List<Movie> movies;  // List to hold movies for this theatre

    public Theatre(int theatreId, String location, List<Movie> movies) {
        this.theatreId = theatreId;
        this.location = location;
        this.movies = movies;
    }

    public Theatre(int theatreId, String location) {
    this.theatreId = theatreId;
    this.location = location;
    this.movies = new ArrayList<>(); // Initialize an empty list if no movies are provided
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
