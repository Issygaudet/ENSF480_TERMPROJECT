package entity;

public class Payment {
    private int paymentId;
    private int userId;
    private double amount;
    private String date;

    public Payment(int paymentId, int userId, double amount, String date) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}