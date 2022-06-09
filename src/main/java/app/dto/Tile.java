package app.dto;

import app.controller.TileController;
import javafx.scene.Group;

public class Tile {
    private static final int RAILROADS_COUNTER = 4;
    private final TileType type;
    private Player owner;
    private final Group tileGroup;
    private final String name;
    private final int price;
    private final int homeCost;
    private final int hotelCost;

    // railroad rent
    private final int rent1railroad;
    private final int rent2railroads;
    private final int rent3railroads;
    private final int rent4railroads;

    // regular rent
    private int rent;
    private final int rentAllColor;
    private int homeRent = 0;
    private int hotelRent = 0;
    private int homeCounter = 0;
    private int hotelCounter = 0;
    //class constructor
    public Tile(Group group, TileType inputType, String inputName, int inputPrice, int rent, int rentAllColor, int homeCost, int hotelCost) {
        tileGroup = group;
        type=inputType;
        name=inputName;
        price=inputPrice;
        
        this.rent = rent;
        this.rentAllColor = rentAllColor;
        this.homeCost = homeCost;
        this.hotelCost = hotelCost + 3 * homeCost;
        
        this.rent1railroad = 0;
        this.rent2railroads = 0;
        this.rent3railroads = 0;
        this.rent4railroads = 0;
    }

    public Tile(Group group, String inputName, int inputPrice, int rent1railroad, int rent2railroads, int rent3railroads, int rent4railroads) {
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
        this.rent3railroads = rent3railroads;
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

    public int getHomeCost() {
        return homeCost;
    }

    public int getHotelCost() {
        return hotelCost;
    }

    public void setHomeRent(int homeRent) {
        this.homeRent = homeRent;
    }

    public void setHotelRent(int hotelRent) {
        this.hotelRent = hotelRent;
    }
    //getter of current rent on certain tile
    public int getCurrentRent() {
        if(type.toString().contains("NORMAL")) {
            if (playerHasAllColor()) {
                return rentAllColor;
            } else if (hotelCounter == 1) {
                return hotelRent;
            } else if (homeCounter > 0) {
                return homeRent;
            }

            return rent;
        }
        else {
            switch(howManyRailroadsPlayerHas()) {
                case 4:
                    return rent4railroads;
                case 3:
                    return rent3railroads;
                case 2:
                    return rent2railroads;
                default:
                    return rent1railroad;
            }
        }
    }
    //calculating how many Railroads player has
    private int howManyRailroadsPlayerHas() {
        int ctr = 0;
        for(int i = 0; i < RAILROADS_COUNTER; i++) {
            if((TileController.getRailroadsBoard())[i].owner == owner) {
                ctr++;
            }
        }

        return ctr;
    }
    //checking if player has all tiles in certain colour
    private boolean playerHasAllColor() {
        int tilesInColor = 3;
        if(type == TileType.NORMAL_BROWN || type == TileType.NORMAL_BLUEDARK) {
            tilesInColor--;
        }

        return TileController.howManySpecificTypeTilesPlayerHas(type, owner) == tilesInColor;
    }

}
