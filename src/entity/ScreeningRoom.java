package entity;

import java.util.ArrayList;

public class ScreeningRoom {
    private int roomId;
    private int rows; 
    private int columns;
    private ArrayList<Seat> seats = new ArrayList<>();

    public ScreeningRoom(int roomId, int rows, int columns) {
        this.roomId = roomId;
        this.rows = rows;
        this.columns = columns;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return rows * columns;
    }

    // 

    public Seat getSeat(int row, int column) {
        int coloff = (row - 1) * columns;
        return seats.get(coloff + (column - 1));
    }

}