package app.controller;

import app.dto.Player;
import app.dto.Tile;
import app.dto.TileType;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StatisticsController {
    private ImageView[][] cards;
    private Label[] priceLabels;
    private Group buyPanel;
    private Group payPanel;
    private Group handleWindow;
    private Button buy;
    private Button skip;

    StatisticsController(Group inputStatisticsGroup,Group inputBuyPanel,Group inputPayPanel,Group inputHandleWindow) {
        cards=new ImageView[4][8];
        priceLabels=new Label[4];
        buyPanel =inputBuyPanel;
        payPanel=inputPayPanel;
        handleWindow=inputHandleWindow;

        for (int i=0,k=2; i<4; i++,k++) {
            for(int j=0,l=1; j<8; j++,l++) {
                cards[i][j]=(ImageView) ((Group) inputStatisticsGroup.getChildren().get(k)).getChildren().get(l);
            }
        }

        for (int i=0,k=2; i<4; i++,k++) {
            priceLabels[i]=(Label) ((Group) ((Group) inputStatisticsGroup.getChildren().get(k)).getChildren().get(0)).getChildren().get(3);
        }

        buy=(Button) inputBuyPanel.getChildren().get(3);
        skip=(Button) inputBuyPanel.getChildren().get(4);
    }

    public void action(Player player,Tile tile, BuyHotelWindowController buyHotelWindowController, BuyHomeWindowController buyHomeWindowController) {
        if(tile.getType()==TileType.START ||
                tile.getType()==TileType.JUST_VISITING ||
                tile.getType()==TileType.FREE_PARKING ||
                tile.getType()==TileType.JAIL ||
                tile.getType()==TileType.CHANCE ||
                tile.getType()==TileType.INCOME_TAX ||
                tile.getType()==TileType.ELECTRIC_COMPANY ||
                tile.getType()==TileType.WATER_WORKS ||
                tile.getType()==TileType.LUXURY_TAX ||
                tile.getType()==TileType.COMMUNITY_CHEST) {
            return;
        }

        setPlayerOnHandleWindow(player.getType().ordinal()+1);

        if(!tile.hasOwner()) {
            buy(player, tile);
            return;
        }

        // nie usuwać zagnieżdżenia ifów! musi być tak zagnieżdżone, bo:
        // jeśli to jest jego pole i jest to pole normalne to może kupić dom/hotel
        // jeśli to nie jest jego pole to musi komuś płacić
        // pole które rozważamy zawsze ma właściciela (patrz poprzedni if)
        if(player == tile.getOwner()) {
            if(tile.getType().toString().contains("NORMAL")) {
                if (tile.getHomeCounter() < 3) {
                    buyHomeWindowController.show(tile);
                } else if (tile.getHotelCounter() == 0) {
                    buyHotelWindowController.show(tile);
                }
            }
        }
        else {
            pay(player, tile);
        }
    }

    private boolean buy(Player player, Tile tile) {
        if(!tile.hasOwner() && player.getCardsCounter()<8 && player.getMoney()>tile.getPrice()) {
            mainWindowOff(tile.hasOwner());

            buy.setOnMousePressed(e -> {
                cards[player.getType().ordinal()][player.getCardsCounter()].
                        setImage(new Image(getClass().
                                getResourceAsStream("css/images/Cards/Card"+player.getPosition()+".png")));

                player.setMoney(player.getMoney()-tile.getPrice());
                player.setCardsCounter(player.getCardsCounter()+1);
                tile.setOwner(player);
                displayPlayerBudget(player);
                mainWindowOn();
            });

            skip.setOnMousePressed(e -> {
                mainWindowOn();
            });

            return true;
        }

        return false;
    }

    private void pay(Player player, Tile tile) {
        if(tile.hasOwner()) {
            mainWindowOff(tile.hasOwner());

            setPlayerOnPanePanel(5,player.getType().ordinal()+1);
            setPlayerOnPanePanel(7,tile.getOwner().getType().ordinal()+1);

            ((Label) payPanel.getChildren().get(6)).setText("-"+tile.getPrice()+" €");
            ((Label) payPanel.getChildren().get(8)).setText("+"+tile.getPrice()+" €");

            player.setMoney(player.getMoney()-tile.getPrice());
            tile.getOwner().setMoney(tile.getOwner().getMoney()+tile.getPrice());
            displayPlayerBudget(player);
            displayPlayerBudget(tile.getOwner());

            payPanel.getChildren().get(3).setOnMousePressed(e -> {
                mainWindowOn();
            });
        }
    }

    private void setPlayerOnPanePanel(int nodeID,int playerID) {
        ((ImageView) payPanel.getChildren().get(nodeID)).setImage(new Image(getClass().
                getResourceAsStream("css/images/Kotek_"+playerID+".png")));
    }

    private void setPlayerOnHandleWindow(int playerID) {
        ((ImageView) handleWindow.getChildren().get(5)).setImage(new Image(getClass().
                getResourceAsStream("css/images/Kotek_"+playerID+".png")));
    }

    private void displayPlayerBudget(Player player) {
        if (player.getMoney() >= 1000)
            priceLabels[player.getType().ordinal()].setText(player.getMoney()+" €");
        else if (player.getMoney() >= 100)
            priceLabels[player.getType().ordinal()].setText("0"+player.getMoney()+" €");
        else if (player.getMoney() >= 10)
            priceLabels[player.getType().ordinal()].setText("00"+player.getMoney()+" €");
        else if (player.getMoney() >= 0)
            priceLabels[player.getType().ordinal()].setText("000"+player.getMoney()+" €");
        else
            priceLabels[player.getType().ordinal()].setText("Debet!");
    }

    private void mainWindowOff(boolean hasTileOwner) {
        if (!hasTileOwner) {
            buyPanel.setVisible(true);
        } else {
            payPanel.setVisible(true);
        }
        handleWindow.getChildren().get(3).setVisible(false);
        handleWindow.getChildren().get(5).setVisible(true);
    }

    private void mainWindowOn() {
        handleWindow.getChildren().get(5).setVisible(false);
        handleWindow.getChildren().get(3).setVisible(true);
        buyPanel.setVisible(false);
        payPanel.setVisible(false);
    }
}
