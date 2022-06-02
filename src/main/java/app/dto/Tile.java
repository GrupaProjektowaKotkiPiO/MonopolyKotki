package app.dto;

import javafx.scene.Group;

public class Tile {
    private TileType type;
    private Player owner;
    private Group tileGroup;
    String name;
    private int price;
    private int homeCounter = 0;
    private int hotelCounter = 0;

    public Tile(Group group, TileType inputType, String inputName, int inputPrice) {
        tileGroup = group;
        type=inputType;
        name=inputName;
        price=inputPrice;
    }

    public boolean hasOwner() { return owner != null; }

    public Player getOwner() { return owner;}

    public TileType getType() { return type; }

    public String getName() { return name; }

    public int getPrice() { return price; }

    public void setOwner(Player owner) { this.owner = owner; }

    public int getHomeCounter() {
        return homeCounter;
    }

    public void setHomeCounter(int homeCounter) {
        this.homeCounter = homeCounter;
    }

    public int getHotelCounter() {
        return hotelCounter;
    }

    public void setHotelCounter(int hotelCounter) {
        this.hotelCounter = hotelCounter;
    }

    public Group getTileGroup() {
        return tileGroup;
    }
}
