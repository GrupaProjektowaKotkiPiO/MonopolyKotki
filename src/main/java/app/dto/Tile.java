package app.dto;

public class Tile {
    private TileType type;
    private Player owner;
    String name;
    private int price;

    public Tile(TileType inputType, String inputName, int inputPrice) {
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
}
