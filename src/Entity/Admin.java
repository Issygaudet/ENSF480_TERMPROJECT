package entity;

import database.ControlDatabase;

public class Admin {
    private ControlDatabase database_control;

    public Admin() {
        database_control = ControlDatabase.getobject();
    }

    public void addMovie(String movieName, String genre, int duration) {
        database_control.addMovie(new Movie(duration, movieName, genre, duration));
    }

    public void updateMovie(int movieId, String movieName, String genre, int duration) {
        database_control.updateMovie(movieId, movieName, genre, duration);
    }

    public void removeMovie(int movieId) {
        database_control.removeMovie(movieId);
    }

    public void addShowtime(Showtime showtime) {
        database_control.addShowtime(showtime);
    }

    public void updateShowtime(Showtime showtime) {
        database_control.updateShowtime(showtime);
    }

    public void removeShowtime(Showtime showtime) {
        database_control.removeShowtime(showtime);
    }
}