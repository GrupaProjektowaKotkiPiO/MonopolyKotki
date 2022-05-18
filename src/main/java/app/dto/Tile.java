package app.dto;

public class Tile {
    private TileType type;
    private PlayerType player;
    private PlayerType owner;
    String name;
    private int price;

    public Tile(TileType inputType, String inputName, int inputPrice) {
        type=inputType;
        name=inputName;
        price=inputPrice;
    }

    public boolean hasOwner() { return owner != null; }

    public boolean hasPlayer() {return player != null; }

    public TileType getType() { return type; }

    public String getName() { return name; }

    public int getPrice() { return price; }

    public PlayerType getPlayer() { return player; }

    public PlayerType getOwner() { return owner; }

    public void setPlayer(PlayerType player) { this.player = player; }

    public void setOwner(PlayerType owner) { this.owner = owner; }
}
