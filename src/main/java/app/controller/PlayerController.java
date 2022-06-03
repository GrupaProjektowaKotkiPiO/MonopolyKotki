package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class PlayerController {
    private final Player[] players;

    public PlayerController(Group playersGroup) {
        players=new Player[4];
        players[0]=new Player(PlayerType.PLAYER1,(ImageView)playersGroup.getChildren().get(0));
        players[1]=new Player(PlayerType.PLAYER2,(ImageView)playersGroup.getChildren().get(1));
        players[2]=new Player(PlayerType.PLAYER3,(ImageView)playersGroup.getChildren().get(2));
        players[3]=new Player(PlayerType.PLAYER4,(ImageView)playersGroup.getChildren().get(3));
    }

    public void moveThePlayer(PlayerType type, int paddingType, double[][] padding) {
        setPositionOnBoard(players[type.ordinal()], paddingType, padding);
    }

    private void setPositionOnBoard(Player player, int paddingType, double[][] padding) {
        player.getPlayerOnBoard().setLayoutX(padding[paddingType][0] + padding[paddingType][1]*(player.getPosition()%10));
        player.getPlayerOnBoard().setLayoutY(padding[paddingType][2] + padding[paddingType][3]*(player.getPosition()%10));
    }

    public Player[] getPlayers() { return players; }
}
