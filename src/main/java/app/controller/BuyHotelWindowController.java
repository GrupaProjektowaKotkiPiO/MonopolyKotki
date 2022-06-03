package app.controller;

import app.dto.Player;
import app.dto.Tile;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class BuyHotelWindowController {
    private final Group buyHotel;

    public BuyHotelWindowController(Group buyHotel) {
        this.buyHotel = buyHotel;
    }

    public void show(Player player, Tile tile) {
        buyHotel.setVisible(true);

        buyHotelButtonAction(player, tile);

        skipButtonAction();
    }

    private void skipButtonAction() {
        ((Button) buyHotel.getChildren().get(5)).setOnAction(e -> buyHotel.setVisible(false));
    }

    private void buyHotelButtonAction(Player player, Tile tile) {
        ((Button) buyHotel.getChildren().get(4)).setOnAction(e -> {

            tile.setHotelCounter(1);
            tile.setHotelRent(tile.getRent() + 100);

            tile.getTileGroup().getChildren().get(4).setVisible(false);
            tile.getTileGroup().getChildren().get(5).setVisible(true);

            player.setMoney(player.getMoney() - tile.getHotelCost());
            player.setCardsCounter(player.getCardsCounter() + 1);
            tile.setOwner(player);
            StatisticsController.displayPlayerBudget(player);

            buyHotel.setVisible(false);
        });
    }
}
