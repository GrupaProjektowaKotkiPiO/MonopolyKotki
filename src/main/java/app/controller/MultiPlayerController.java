package app.controller;

import app.dto.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class MultiPlayerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Player player;

    public static void addPlayer(String name) { //todo: ogarnac to
        if (players.size() <= 4) {
            players.add(new Player(name));
            player = players.get(0);
        } else {
            System.out.println("Максимум игроков");
        }

        if (players.size() == 4) {
            setFlagTeam();
            System.out.println("Установленно максимально возможное число игроков");
        }
    }
}