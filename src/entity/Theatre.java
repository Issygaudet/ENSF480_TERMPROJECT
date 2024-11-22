package entity;

public class Theatre {
    private int theatreId;
    private String name;
    private String location;
    private float price;

    public Theatre(int theatreId, String name, String location, float price) {
        this.theatreId = theatreId;
        this.name = name;
        this.location = location;
        this.price = price;
    }

    public int getTheatreId() {
        return theatreId;
    }


    public String getName() {
        return name;
    }


    public String getLocation() {
        return location;
    }

    public float getPrice() {
        return price;
    }

}