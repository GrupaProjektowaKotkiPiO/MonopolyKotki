package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class PlayerController {
    private Player[] players;

    public PlayerController(ObservableList<Node> group2) {
        players=new Player[4];
        players[0]=new Player(PlayerType.PLAYER1,"Szymon","Jakubaszek",(ImageView)group2.get(0));
        players[1]=new Player(PlayerType.PLAYER1,"Daria","Glińska",(ImageView)group2.get(1));
        players[2]=new Player(PlayerType.PLAYER1,"Maciej","Sierzputowski",(ImageView)group2.get(2));
        players[3]=new Player(PlayerType.PLAYER1,"Robert","Banasiak",(ImageView)group2.get(3));
    }

    public void moveThePlayer(PlayerType type, int paddingType, double[][] padding) {
        switch (type) {
            case PLAYER1:
                setPositionOnBoard(players[0], paddingType, padding);
                break;
            case PLAYER2:
                setPositionOnBoard(players[1], paddingType, padding);
                break;
            case PLAYER3:
                setPositionOnBoard(players[2], paddingType, padding);
                break;
            default:
                setPositionOnBoard(players[3], paddingType, padding);
        }
    }

    private void setPositionOnBoard(Player player, int paddingType, double[][] padding) {
        player.getPlayerOnBoard().setLayoutX(padding[paddingType][0] + padding[paddingType][1]*(player.getPosition()%10));
        player.getPlayerOnBoard().setLayoutY(padding[paddingType][2] + padding[paddingType][3]*(player.getPosition()%10));
    }

    public Player[] getPlayers() { return players; }
}
