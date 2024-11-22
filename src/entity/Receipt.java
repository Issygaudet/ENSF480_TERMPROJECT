package entity;

public class Receipt {
    private int receiptId;
    private float amount;
    private Date date;

    public Receipt(int receiptId, float amount, Date date) {
        this.receiptId = receiptId;
        this.amount = amount;
        this.date = date;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
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