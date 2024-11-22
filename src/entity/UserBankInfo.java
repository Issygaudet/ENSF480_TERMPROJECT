package entity;

public class UserBankInfo {
    private int userId;
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;

    public UserBankInfo(int userId, String bankName, String accountNumber) {
        this.userId = userId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}