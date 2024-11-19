package src.Entity;

public class Seat {
    private int seatId;
    private int roomId;
    private boolean isAvailable;

    public Seat(int seatId, int roomId, boolean isAvailable) {
        this.seatId = seatId;
        this.roomId = roomId;
        this.isAvailable = isAvailable;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }