package app.controller;

import app.dto.Player;
import app.dto.Tile;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class BuyHotelWindowController {
    private final Group buyHotel;

    //class contructor -> sets the group
    public BuyHotelWindowController(Group buyHotel) {
        this.buyHotel = buyHotel;
    }
    //method that shows us graphic representation of buying hotel building
    public void show(Player player, Tile tile) {
        buyHotel.setVisible(true);

        buyHotelButtonAction(player, tile);

        skipButtonAction();
    }

    //method which hides our Action button while buying hotel
    private void skipButtonAction() {
        ((Button) buyHotel.getChildren().get(5)).setOnAction(e -> buyHotel.setVisible(false));
    }

    //If we click buy, we use our setOwnerOfHotel method
    private void buyHotelButtonAction(Player player, Tile tile) {
        ((Button) buyHotel.getChildren().get(4)).setOnAction(e -> {

            setOwnerOfHotel(player, tile);

            buyHotel.setVisible(false);
        });
    }

    //sets hotel counter to 1, increases rent, disables remaining home buildings
    //takes money out of players account and set ownership of hotel
    private void setOwnerOfHotel(Player player, Tile tile) {
        tile.setHotelCounter(1);
        tile.setHotelRent(tile.getRent() + 100);

        tile.getTileGroup().getChildren().get(4).setVisible(false);
        tile.getTileGroup().getChildren().get(5).setVisible(true);

        player.setMoney(player.getMoney() - tile.getHotelCost());
        player.setCardsCounter(player.getCardsCounter() + 1);
        tile.setOwner(player);
        StatisticsController.displayPlayerBudget(player);
    }
}
