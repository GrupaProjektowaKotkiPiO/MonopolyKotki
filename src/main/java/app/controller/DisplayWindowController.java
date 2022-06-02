package app.controller;

import app.dto.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DisplayWindowController {
    private ImageView turnWindow;
    private ImageView cardWindow;

    DisplayWindowController (Group handleWindow) {
        turnWindow=(ImageView) handleWindow.getChildren().get(3);
        cardWindow=(ImageView) handleWindow.getChildren().get(4);
    }

    public void changePlayerInWindow(PlayerType type) {
        int playerID=type.ordinal()+1;
        turnWindow.setImage(new Image(getClass().getResourceAsStream("css/images/Kotek_"+playerID+".png")));
    }

    public void changeCardInWindow(int position) {
        cardWindow.setImage(new Image(getClass().getResourceAsStream("css/images/Cards/Card"+position+".png")));
    }
}
