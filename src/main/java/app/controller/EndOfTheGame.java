package app.controller;

import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class EndOfTheGame {
    private Group finish;

    public EndOfTheGame(Group finish) {
        this.finish=finish;
    }

    public void show() {
        Player[] players = PlayerController.getPlayers();

        int n = PlayerController.PLAYERS_NUMBER;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (players[j].getScore() < players[j + 1].getScore()) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }

        ((ImageView) finish.getChildren().get(4)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + (players[0].getType().ordinal() + 1) + ".png"))));

        ((ImageView) finish.getChildren().get(5)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + (players[1].getType().ordinal() + 1) + ".png"))));


        ((ImageView) finish.getChildren().get(6)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + (players[2].getType().ordinal() + 1) + ".png"))));


        finish.setVisible(true);
    }
}
