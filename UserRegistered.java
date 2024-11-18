public class UserRegistered extends UserOrdinary{
    public String cardNumber;
    public String cardExpiration;
    public String cardCVV;

    public UserRegistered(String name, String email, String password){
        super(name, email, password);
    }

    public void addCard(String cardNumber, String cardExpiration, String cardCVV){
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardCVV = cardCVV;
    }

    public void createAccount(String name, String email, String password){
    }????

    public void login(String email, String password){
        // check email and password match??
    }
    public void logout(){
    }

}
