package app.controller;

import app.dto.Player;
import app.dto.Tile;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class BuyHomeWindowController {
    private final Group buyHome;

    //class contructor -> sets the group
    public BuyHomeWindowController(Group buyHome) {
        this.buyHome = buyHome;
    }

    //method that shows us graphic representation of buying home building
    public void show(Player player, Tile tile) {
        buyHomeButtonAction(player, tile);

        skipButtonAction();

        buyHome.setVisible(true);
    }

    //method which hides our Action button while buying home
    private void skipButtonAction() {
        ((Button) buyHome.getChildren().get(5)).setOnAction(e -> buyHome.setVisible(false));
    }

    //method which shows us how many houses do we have at our tile
    //and set ownership of the tile
    private void buyHomeButtonAction(Player player, Tile tile) {
        ((Button) buyHome.getChildren().get(4)).setOnAction(e -> {

            Group houses = (Group) tile.getTileGroup().getChildren().get(4);

            switch (tile.getHomeCounter()) {
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

            setOwnerOfHome(player, tile);

            buyHome.setVisible(false);
        });
    }

    //increments home counter by 1, increases rent,
    //takes money out of players account and sets ownership of the tile
    private void setOwnerOfHome(Player player, Tile tile) {
        tile.setHomeCounter(tile.getHomeCounter() + 1);
        tile.setHomeRent(tile.getRent() + 20 * tile.getHomeCounter());

        player.setMoney(player.getMoney() - tile.getHomeCost());
        player.setCardsCounter(player.getCardsCounter() + 1);
        tile.setOwner(player);
        StatisticsController.displayPlayerBudget(player);
    }
}
