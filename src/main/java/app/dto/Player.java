package app.dto;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Player {
    private final PlayerType type;
    private final ImageView playerOnBoard;
    private final Label playersMoneyLabel;
    private int position = 0;
    private int cardsCounter = 0;
    private int money = 1000;
    private int score;
    private boolean isPlayerInGame = true;
    private int lostQueues = 0; //pozostała ilość straconych kolejek gry -> NIE MA IMPLEMENTACJI

    public Player(PlayerType inputType, ImageView inputPlayerOnBoard, Label playersMoneyLabel) {
        type = inputType;
        playerOnBoard = inputPlayerOnBoard;
        this.playersMoneyLabel = playersMoneyLabel;
    }

    public PlayerType getType() {
        return type;
    }

    public ImageView getPlayerOnBoard() {
        return playerOnBoard;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (!isPlayerInGame()) {
            return;
        }

        this.money = money;

        if (this.money < 0) {
            playersMoneyLabel.setText("DEBET");
            isPlayerInGame = false;
        }

        playersMoneyLabel.setText(money + " €");
    }

    public int getCardsCounter() {
        return cardsCounter;
    }

    public void setCardsCounter(int cardsCounter) {
        this.cardsCounter = cardsCounter;
    }

    public void countScore() {
        if (money <= 0) {
            score = 0;
        } else {
            score = money + cardsCounter * 100;
        }
    }

    public int getScore() {
        countScore();
        return score;
    }

    public boolean isPlayerInGame() {
        return isPlayerInGame;
    }

    public int getLostQueues() {
        return lostQueues;
    }

    public void setLostQueues(int lostQueues) {
        this.lostQueues = lostQueues;
    }
}
