package app.controller;

import app.dto.Tile;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class BuyHomeWindowController {
    private final Group buyHome;

    public BuyHomeWindowController(Group buyHome) {
        this.buyHome = buyHome;
    }

    public void show(Tile tile) {
        buyHomeButtonAction(tile);

        skipButtonAction();

        buyHome.setVisible(true);
    }

    private void skipButtonAction() {
        ((Button) buyHome.getChildren().get(5)).setOnAction(e -> buyHome.setVisible(false));
    }

    private void buyHomeButtonAction(Tile tile) {
        ((Button) buyHome.getChildren().get(4)).setOnAction(e -> {
            // todo -> oplaty za domy

            Group houses = (Group) tile.getTileGroup().getChildren().get(4);

            switch(tile.getHomeCounter()) {
                case 0:
                    houses.setVisible(true);
                    houses.getChildren().get(0).setVisible(false);
                    houses.getChildren().get(2).setVisible(false);
                    break;
                case 1:
                    houses.getChildren().get(0).setVisible(true);
                    break;
                case 2:
                    houses.getChildren().get(2).setVisible(true);
                    break;
            }

            tile.setHomeCounter(tile.getHomeCounter() + 1);
            tile.setRent(tile.getRent() + 20 * tile.getHomeCounter());

            buyHome.setVisible(false);
        });
    }
}
