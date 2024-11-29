package entity;

import java.util.Vector;

public class UserRegistered extends UserOrdinary {
    private boolean annualFeePaid;
    private UserBankInfo bankInfo;
    private Date dateJoined;

    public UserRegistered(int userID, String name, String email, String password, UserBankInfo bankInfo, Date dateJoined) {
        super(userID, name, email, password);
        this.annualFeePaid = false;
        this.bankInfo = bankInfo;
        this.dateJoined = dateJoined;
    }

    // Getter and Setter for Annual Fee Paid
    public boolean isAnnualFeePaid() {
        return annualFeePaid;
    }

    public void payAnnualFee() {
        this.annualFeePaid = true;
    }

    // Getter and Setter for Bank Info
    public UserBankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(UserBankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    // Split Full Name into First and Last Name
    public String getFirstName() {
        String[] parts = this.getName().split(" ");
        return parts.length > 0 ? parts[0] : "";
    }

    public String getLastName() {
        String[] parts = this.getName().split(" ");
        return parts.length > 1 ? parts[1] : "";
    }

    // Getter and Setter for Join Date
    public Date getdateJoined() {
        return dateJoined;
    }

    public void setdateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    // Access Join Date Components
    public int getJoinDay() {
        return dateJoined.getDay();
    }

    public int getJoinMonth() {
        return dateJoined.getMonth();
    }

    public int getJoinYear() {
        return dateJoined.getYear();
    }

    // Special Access Seat Selection
    public void selectSpecialAccessSeats() {
        System.out.println("Selecting special access seats for registered users.");
    }

    // Movie Search with Priority
    @Override
    public Vector<Movie> searchMovie(String query) {
        Vector<Movie> movies = super.searchMovie(query);
        System.out.println("Searching for movies with priority access for registered users.");
        return movies;
    }

    // Override Make Payment
    @Override
    public void makePayment(Payment payment) {
        if (this.annualFeePaid) {
            // Process payment without admin fee
        } else {
            // Process payment with admin fee
        }
    }

    // Override Cancel Booking
    @Override
    public void cancelBooking(int bookingId) {
        System.out.println("Refund 100% for registered users, not 85%.");
    }
}
