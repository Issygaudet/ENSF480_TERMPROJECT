package src.Entity;

import java.util.Vector;

public class UserRegistered extends UserOrdinary {
    private boolean annualFeePaid;
    private UserBankInfo bankInfo;

    public UserRegistered(String name, String email, String password, UserBankInfo bankInfo) {
        super(name, email, password);
        this.annualFeePaid = false;
        this.bankInfo = bankInfo;
    }

    public void payAnnualFee() {
        // Implementation to pay the annual fee
        this.annualFeePaid = true;
    }

    public boolean isAnnualFeePaid() {
        return annualFeePaid;
    }

    public UserBankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(UserBankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    //THE 10% THING
    public void selectSpecialAccessSeats() {
        // Implementation to select special access seats for registered users
        System.out.println("Selecting special access seats for registered users.");
    }

    @Override 
    public Vector<Movie> searchMovie(String query) {
        // Implementation to search for movies with priority access for registered users
        Vector<Movie> movies = super.searchMovie(query);
        // Add logic to prioritize movies for registered users
        System.out.println("Searching for movies with priority access for registered users.");
        return movies;
    }

    //already have card info....
    @Override
    public void makePayment(Payment payment) {
        // Implementation to make a payment using registered user's bank info
        if (this.annualFeePaid) {
            // Process payment without admin fee
        } else {
            // Process payment with admin fee
        }
    }

    @Override
    public void cancelBooking(int bookingId) {
        // gives 100 refund NOT 85
    }
}