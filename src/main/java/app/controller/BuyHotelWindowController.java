package app.controller;

import app.dto.Tile;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class BuyHotelWindowController {
    private final Group buyHotel;

    public BuyHotelWindowController(Group buyHotel) {
        this.buyHotel = buyHotel;
    }

    public void show(Tile tile) {
        buyHotel.setVisible(true);

        buyHotelButtonAction(tile);

        skipButtonAction();
    }

    private void skipButtonAction() {
        ((Button) buyHotel.getChildren().get(5)).setOnAction(e -> buyHotel.setVisible(false));
    }

    private void buyHotelButtonAction(Tile tile) {
        ((Button) buyHotel.getChildren().get(4)).setOnAction(e -> {
            // todo -> oplaty za hotel

            tile.setHotelCounter(1);
            tile.setRent(tile.getRent() + 100);

            tile.getTileGroup().getChildren().get(4).setVisible(false);
            tile.getTileGroup().getChildren().get(5).setVisible(true);

            buyHotel.setVisible(false);

        });
    }
}
