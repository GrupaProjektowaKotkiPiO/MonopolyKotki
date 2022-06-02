package app.dto;

import javafx.scene.image.ImageView;

public class Player {
    PlayerType type;
    String firstName;
    String lastName;
    private ImageView playerOnBoard;
    private int position;
    private int cardsCounter;
    private int money;

    public Player(PlayerType inputType, ImageView inputPlayerOnBoard) {
        type = inputType;
        playerOnBoard=inputPlayerOnBoard;
        position=0;
        cardsCounter=0;
        money=1000;
    }

    public PlayerType getType() {
        return type;
    }

    public ImageView getPlayerOnBoard() {
        return playerOnBoard;
    }

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }

    public int getCardsCounter() { return cardsCounter; }

    public void setCardsCounter(int cardsCounter) { this.cardsCounter = cardsCounter; }
}
