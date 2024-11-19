package src.Entity;
import java.util.Vector;

public class UserOrdinary {
    public String name;
    public String email;
    public String password;

    public UserOrdinary(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Vector<Movie> searchMovie(String query){
        // Implementation here
        return new Vector<>();
    }

    public void selectMovie(Movie movie){
        // Implementation here
    }

    public void selectTheater(Theatre theater){
        // Implementation here
    }

    public Vector<Showtime> viewShowtimes(){
        // Implementation here
        return new Vector<>();
    }

    public void selectShowtime(Showtime showtime){
        // Implementation here
    }

    public Vector<Seat> viewSeats(){
        // Implementation here
        return new Vector<>();
    }

    public void selectSeat(Seat seat){
        // Implementation here
    }

    public void makePayment(){
        // Implementation here
    }

    public void viewBooking(){
        // Implementation here
    }

    public void cancelBooking(){
        // Implementation here
    }
}