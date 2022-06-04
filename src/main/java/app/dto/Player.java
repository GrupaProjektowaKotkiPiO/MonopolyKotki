package app.dto;

import javafx.scene.image.ImageView;

public class Player {
    private final PlayerType type;
    private final ImageView playerOnBoard;
    private int position = 0;
    private int cardsCounter = 0;
    private int money = 1000;
    private int score;

    public Player(PlayerType inputType, ImageView inputPlayerOnBoard) {
        type = inputType;
        playerOnBoard=inputPlayerOnBoard;
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

    public void countScore() {
        if(money <= 0) {
            score = 0;
        }
        else {
            score = money + cardsCounter * 100;
        }
    }

    public int getScore() {
        countScore();
        return score;
    }
}
