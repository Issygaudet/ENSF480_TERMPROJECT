package entity;
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
      
        Vector<Movie> movies = new Vector<>();
        
        System.out.println("Searching for movies with low access for ordinary users with query: " + query);
        return movies;
    }

    public void selectMovie(Movie movie){
   
        System.out.println("Selected movie: " + movie.getName());
    }

    public void selectTheater(Theatre theater){
       
        System.out.println("Selected theater: " + theater.getLocation());
    }

    public Vector<Showtime> viewShowtimes(){
       
        Vector<Showtime> showtimes = new Vector<>();
     
        System.out.println("Viewing showtimes.");
        return showtimes;
    }

    public void selectShowtime(Showtime showtime){
        
        System.out.println("Selected showtime: " + showtime.getTime());
    }

    public Vector<Seat> viewSeats(){
        Vector<Seat> seats = new Vector<>();
        
        System.out.println("Viewing seats.");
        return seats;
    }

    public void selectSeat(Seat seat){
    
        System.out.println("Selected seat: " + seat.getSeatId());
    }

    public void makePayment(Payment payment){
        /// will have to prompt user for bank info and create vaninfo object
        System.out.println("Payment made with amount: " + payment.getAmount());
    }

    public void viewBooking(){

        System.out.println("Viewing booking details.");
    }

    public void cancelBooking(int bookingId) {
        
        System.out.println("Booking cancelled with 85% refund.");
    }
}