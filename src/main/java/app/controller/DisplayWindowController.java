package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DisplayWindowController {
    private ImageView turnWindow;
    private ImageView cardWindow;

    DisplayWindowController (ObservableList<Node> group4) {
        turnWindow=(ImageView) group4.get(3);
        cardWindow=(ImageView) group4.get(4);
    }

    public void changePlayerInWindow(PlayerType type) {
        switch (type) {
            case PLAYER1:
                turnWindow.setImage(new Image(getClass().getResourceAsStream("css/images/Kotek_1.png")));
                break;
            case PLAYER2:
                turnWindow.setImage(new Image(getClass().getResourceAsStream("css/images/Kotek_2.png")));
                break;
            case PLAYER3:
                turnWindow.setImage(new Image(getClass().getResourceAsStream("css/images/Kotek_3.png")));
                break;
            default:
                turnWindow.setImage(new Image(getClass().getResourceAsStream("css/images/Kotek_4.png")));
        }
    }
}
