package entity;

public class UserBankInfo {
    private int bankInfoID;
    private String cardNumber;
    private String cardHolder;
    private Date expiryDate;
    private int cvv;

    public UserBankInfo(int bankInfoID, String cardNumber, String cardHolder, Date expiryDate, int cvv){
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public int getBankInfoID() {
        return bankInfoID;
    }

    public void setBankInfoID(int bankInfoID) {
        this.bankInfoID = bankInfoID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }   

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }


}