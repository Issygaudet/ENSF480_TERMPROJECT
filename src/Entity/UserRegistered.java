package src.Entity;

public class UserRegistered extends UserOrdinary {
    private String address;
    private String creditCard;
    private String debitCard;
    private boolean annualFeePaid;

    public UserRegistered(String name, String email, String password, String address, String creditCard, String debitCard) {
        super(name, email, password);
        this.address = address;
        this.creditCard = creditCard;
        this.debitCard = debitCard;
        this.annualFeePaid = false;
    }

    public void payAnnualFee() {
        // Implementation to pay the annual fee
        this.annualFeePaid = true;
    }

    public boolean isAnnualFeePaid() {
        return annualFeePaid;
    }
}