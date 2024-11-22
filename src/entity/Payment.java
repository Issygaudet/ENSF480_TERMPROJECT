package entity;

public class Payment {
    private int userId;
    private float amount;
    private Date date;

    public Payment(int userId, float amount, Date date) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
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

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}