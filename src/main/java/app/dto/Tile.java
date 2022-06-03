package app.dto;

import javafx.scene.Group;

public class Tile {
    private final TileType type;
    private final int rent1railroad;
    private final int rent2railroads;
    private final int rent3railroad;
    private final int rent4railroads;
    private Player owner;
    private final Group tileGroup;
    private final String name;
    private final int price;
    private int rent;
    private final int rentAllColor;
    private final int homeCost;
    private final int hotelCost;
    private int homeCounter = 0;
    private int hotelCounter = 0;

    public Tile(Group group, TileType inputType, String inputName, int inputPrice, int rent, int rentAllColor, int homeCost, int hotelCost) {
        tileGroup = group;
        type=inputType;
        name=inputName;
        price=inputPrice;
        
        this.rent = rent;
        this.rentAllColor = rentAllColor;
        this.homeCost = homeCost;
        this.hotelCost = hotelCost;
        
        this.rent1railroad = 0;
        this.rent2railroads = 0;
        this.rent3railroad = 0;
        this.rent4railroads = 0;
    }

    public Tile(Group group, String inputName, int inputPrice, int rent1railroad, int rent2railroads, int rent3railroad, int rent4railroads) {
        tileGroup = group;
        type=TileType.RAILROAD;
        name=inputName;
        price=inputPrice;

        this.rent = 0;
        this.rentAllColor = 0;
        this.homeCost = 0;
        this.hotelCost = 0;
        
        this.rent1railroad = rent1railroad;
        this.rent2railroads = rent2railroads;
        this.rent3railroad = rent3railroad;
        this.rent4railroads = rent4railroads;
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

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}
