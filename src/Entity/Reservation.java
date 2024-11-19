package src.Entity;

public class Reservation {
    private int reservationId;
    private int userId;
    private int showtimeId;
    private int seatId;

    public Reservation(int reservationId, int userId, int showtimeId, int seatId) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
}