package entity;

public class Movie {
    private int movieId;
    private String name;
    private String genre;
    private int year;
    private String director;
    private float duration;
    private float rating;
    private String code;
    private float price;
    private String description;

    public Movie(int movieId, String name, String genre, float duration) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public Movie(int movieId, String name, String genre, int year, String director, float duration, float rating, String code, float price, String description) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.code = code;
        this.price = price;
        this.description = description;
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}