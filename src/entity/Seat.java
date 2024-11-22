package entity;

public class Seat {
    private String seatId;
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
    public void setUnavailable() {
        this.isAvailable = false;
    }
    // seats set as available in ctor-changed to unavailable if seleceted
}