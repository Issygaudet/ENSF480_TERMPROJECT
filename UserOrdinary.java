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
    }
    public void selectMovie(Movie movie){
    }
    public void selectTheater(Theatre theater){
    }
    public Vector<Showtime> viewShowtimes(){}
    public void selectShowtime(Showtime showtime){
    }
    public Vector<Seat> viewSeats(){}
    public void selectSeat(Seat seat){
    }
    public void makePayment(){
    }
    public void viewBooking(){}
    public void cancelBooking(){}
}
