package entity;

public class Theatre {
    private int theatreId;
    private String location;

    public Theatre(int theatreId, String name, String location) {
        this.theatreId = theatreId;
        this.location = location;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}