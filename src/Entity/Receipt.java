package Entity;

public class Receipt {
    private int receiptId;
    private int reservationId;
    private double amount;
    private String date;

    public Receipt(int receiptId, int reservationId, double amount, String date) {
        this.receiptId = receiptId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.date = date;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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