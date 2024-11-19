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
   
        return new Vector<>();
    }

    public void selectMovie(Movie movie){
  
    }

    public void selectTheater(Theatre theater){
      
    }

    public Vector<Showtime> viewShowtimes(){
     
        return new Vector<>();
    }

    public void selectShowtime(Showtime showtime){
    }

    public Vector<Seat> viewSeats(){
        return new Vector<>();
    }

    public void selectSeat(Seat seat){

    }

    public void makePayment(){
    }

    public void viewBooking(){
    }

    public void cancelBooking(){
    }
}