package entity;

public class Theatre {
    private int theatreId;
    private String location;
    private float price;

    public Theatre(int theatreId, String name, String location, float price) {
        this.theatreId = theatreId;
        this.location = location;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}