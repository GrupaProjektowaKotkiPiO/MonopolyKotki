package app.controller;

import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DisplayWindowController {
    private final ImageView turnWindow;
    private final ImageView cardWindow;

    DisplayWindowController (Group handleWindow) {
        turnWindow=(ImageView) handleWindow.getChildren().get(3);
        cardWindow=(ImageView) handleWindow.getChildren().get(4);
    }

    public void changePlayerInWindow(PlayerType type) {
        int playerID=type.ordinal()+1;
        turnWindow.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Kotek_" + playerID + ".png"))));
    }

    public void changeCardInWindow(int position) {
        cardWindow.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Cards/Card" + position + ".png"))));
    }
}
