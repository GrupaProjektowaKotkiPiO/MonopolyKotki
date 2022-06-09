package app.controller;

import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DisplayWindowController {
    private final ImageView turnWindow;
    private final ImageView cardWindow;
    //class contructor -> sets Images of our turnwindow and card window
    DisplayWindowController (Group handleWindow) {
        turnWindow=(ImageView) handleWindow.getChildren().get(3);
        cardWindow=(ImageView) handleWindow.getChildren().get(4);
    }
    //change current player in frame
    public void changePlayerInWindow(PlayerType type) {
        int playerID=type.ordinal()+1;
        turnWindow.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Kotek_" + playerID + ".png"))));
    }
    //change current field card on side pane
    public void changeCardInWindow(int position) {
        cardWindow.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Cards/Card" + position + ".png"))));
    }
}
