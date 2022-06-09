package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlayerController {
    public static final int PLAYERS_NUMBER = 4;
    private static final Player[] players = new Player[PLAYERS_NUMBER];

    //class contructor -> sets players image
    public PlayerController(Group playersGroup, Group usersMoney) {
        players[0] = new Player(PlayerType.PLAYER1, (ImageView) playersGroup.getChildren().get(0), (Label) usersMoney.getChildren().get(0));
        players[1] = new Player(PlayerType.PLAYER2, (ImageView) playersGroup.getChildren().get(1), (Label) usersMoney.getChildren().get(1));
        players[2] = new Player(PlayerType.PLAYER3, (ImageView) playersGroup.getChildren().get(2), (Label) usersMoney.getChildren().get(2));
        players[3] = new Player(PlayerType.PLAYER4, (ImageView) playersGroup.getChildren().get(3), (Label) usersMoney.getChildren().get(3));
    }

    //set player position with proper padding
    public void moveThePlayer(PlayerType type, int paddingType, double[][] padding) {
        setPositionOnBoard(players[type.ordinal()], paddingType, padding);
    }

    //calculates players position on certain tile
    private void setPositionOnBoard(Player player, int paddingType, double[][] padding) {
        player.getPlayerOnBoard().setLayoutX(padding[paddingType][0] + padding[paddingType][1] * (player.getPosition() % 10));
        player.getPlayerOnBoard().setLayoutY(padding[paddingType][2] + padding[paddingType][3] * (player.getPosition() % 10));
    }

    //getter of tile
    public static Player[] getPlayers() {
        return players;
    }
}
