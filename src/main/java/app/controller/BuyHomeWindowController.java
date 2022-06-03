package app.controller;

import app.dto.Player;
import app.dto.Tile;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class BuyHomeWindowController {
    private final Group buyHome;

    public BuyHomeWindowController(Group buyHome) {
        this.buyHome = buyHome;
    }

    public void show(Player player, Tile tile) {
        buyHomeButtonAction(player, tile);

        skipButtonAction();

        buyHome.setVisible(true);
    }

    private void skipButtonAction() {
        ((Button) buyHome.getChildren().get(5)).setOnAction(e -> buyHome.setVisible(false));
    }

    private void buyHomeButtonAction(Player player, Tile tile) {
        ((Button) buyHome.getChildren().get(4)).setOnAction(e -> {

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
            tile.setHomeRent(tile.getRent() + 20 * tile.getHomeCounter());

            player.setMoney(player.getMoney() - tile.getHomeCost());
            player.setCardsCounter(player.getCardsCounter() + 1);
            tile.setOwner(player);
            StatisticsController.displayPlayerBudget(player);

            buyHome.setVisible(false);
        });
    }
}
