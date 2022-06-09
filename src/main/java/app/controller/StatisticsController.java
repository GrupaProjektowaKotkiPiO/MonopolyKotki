package app.controller;

import app.dto.Player;
import app.dto.Tile;
import app.dto.TileType;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class StatisticsController {
    public static final int PLAYERS_NUMBER = 4;
    public static final int TILES_POSSIBLE_TO_BUY = 26;
    public static final int BLANK_CARD_NUMBER = 400;
    private final ImageView[][] cards;
    private final int[][] cardsIndexes = new int[PLAYERS_NUMBER][TILES_POSSIBLE_TO_BUY];
    private static final Label[] priceLabels = new Label[PLAYERS_NUMBER];
    private final Group buyPanel;
    private final Group payPanel;
    private final Group handleWindow;
    private final Button buy;
    private final Button skip;

    StatisticsController(Group inputStatisticsGroup,Group inputBuyPanel,Group inputPayPanel,Group inputHandleWindow) {
        cards = new ImageView[PLAYERS_NUMBER][TILES_POSSIBLE_TO_BUY];
        buyPanel = inputBuyPanel;
        payPanel = inputPayPanel;
        handleWindow = inputHandleWindow;

        for (int i = 0; i < PLAYERS_NUMBER; i++) {
            for(int j = 0; j < TILES_POSSIBLE_TO_BUY; j++) {
                ScrollPane scrollPane = (ScrollPane)inputStatisticsGroup.getChildren().get(7 + i);
                AnchorPane anchorPane = ((AnchorPane)scrollPane.getContent());
                cards[i][j] = (ImageView) anchorPane.getChildren().get(j);
                cardsIndexes[i][j] = BLANK_CARD_NUMBER;
            }
        }

        for (int i = 0,k = 2; i < PLAYERS_NUMBER; i++,k++) {
            priceLabels[i]=(Label) ((Group) ((Group) inputStatisticsGroup.getChildren().get(k)).getChildren().get(0)).getChildren().get(3);
        }

        buy=(Button) inputBuyPanel.getChildren().get(3);
        skip=(Button) inputBuyPanel.getChildren().get(4);
    }

    public void action(Player player,Tile tile, BuyHotelWindowController buyHotelWindowController, BuyHomeWindowController buyHomeWindowController, ChanceController chanceController, CommunityChestController communityChestController, PlayerController playerController, TileController tileController) {
        if(tile.getType()==TileType.START ||
                tile.getType()==TileType.JUST_VISITING ||
                tile.getType()==TileType.FREE_PARKING ||
                tile.getType()==TileType.JAIL ||
                tile.getType()==TileType.INCOME_TAX ||
                tile.getType()==TileType.ELECTRIC_COMPANY ||
                tile.getType()==TileType.WATER_WORKS ||
                tile.getType()==TileType.LUXURY_TAX) {
            return;
        }

        if(tile.getType()==TileType.CHANCE) {
            chanceController.show(player, playerController, tileController);
            return;
        }

        if(tile.getType()==TileType.COMMUNITY_CHEST) {
            communityChestController.show(player, playerController, tileController);
            return;
        }

        if(tile.getType()==TileType.JAIL) {
            player.setPrisoner(true);
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
                if (tile.getHomeCounter() < 3 && player.getMoney() > tile.getHomeCost()) {
                    buyHomeWindowController.show(player, tile);
                } else if (tile.getHotelCounter() == 0 && player.getMoney() > tile.getHotelCost()) {
                    buyHotelWindowController.show(player, tile);
                }
            }
        }
        else {
            pay(player, tile);
        }
    }

    private void buy(Player player, Tile tile) {
        if(!tile.hasOwner() && player.getMoney()>tile.getPrice()) {
            mainWindowOff(tile.hasOwner());

            buy.setOnMousePressed(e -> {
                cards[player.getType().ordinal()][player.getCardsCounter()].
                        setImage(new Image(Objects.requireNonNull(getClass().
                                getResourceAsStream("css/images/Cards/Card" + player.getPosition() + ".png"))));

                // sortowanie kart kolorami
                // żeby stacje kolejowe się sortowały ich numer jest ustawiany w konwencji: NUMER_KARTY * 10
                if(tile.getType().toString().contains("RAILROAD")){
                    cardsIndexes[player.getType().ordinal()][player.getCardsCounter()] = player.getPosition() * 10;
                }
                else {
                    cardsIndexes[player.getType().ordinal()][player.getCardsCounter()] = player.getPosition();
                }
                sortCardsIndexes(player.getType().ordinal());
                setImages(player.getType().ordinal());

                player.setMoney(player.getMoney()-tile.getPrice());
                player.setCardsCounter(player.getCardsCounter()+1);
                tile.setOwner(player);
                displayPlayerBudget(player);
                mainWindowOn();
            });

            skip.setOnMousePressed(e -> mainWindowOn());
        }
    }

    private void sortCardsIndexes(int playerIndex) {
        for (int i = 0; i < TILES_POSSIBLE_TO_BUY - 1; i++)
            for (int j = 0; j < TILES_POSSIBLE_TO_BUY - i - 1; j++)
                if (cardsIndexes[playerIndex][j] > cardsIndexes[playerIndex][j + 1]) {
                    int temp = cardsIndexes[playerIndex][j];
                    cardsIndexes[playerIndex][j] = cardsIndexes[playerIndex][j + 1];
                    cardsIndexes[playerIndex][j + 1] = temp;
                }
    }

    public void setImages(int playerIndex) {
        for (int i = 0; i < TILES_POSSIBLE_TO_BUY && cardsIndexes[playerIndex][i] != BLANK_CARD_NUMBER; i++) {
            if(cardsIndexes[playerIndex][i] > 40) {
                cards[playerIndex][i].
                        setImage(new Image(Objects.requireNonNull(getClass().
                                getResourceAsStream("css/images/Cards/Card" + cardsIndexes[playerIndex][i] / 10 + ".png"))));
            }
            else {
                cards[playerIndex][i].
                        setImage(new Image(Objects.requireNonNull(getClass().
                                getResourceAsStream("css/images/Cards/Card" + cardsIndexes[playerIndex][i] + ".png"))));
            }
        }
    }

    private void pay(Player player, Tile tile) {
        if(tile.hasOwner()) {
            mainWindowOff(tile.hasOwner());

            setPlayerOnPanePanel(5,player.getType().ordinal()+1);
            setPlayerOnPanePanel(7,tile.getOwner().getType().ordinal()+1);

            ((Label) payPanel.getChildren().get(6)).setText("-"+tile.getCurrentRent()+" €");
            ((Label) payPanel.getChildren().get(8)).setText("+"+tile.getCurrentRent()+" €");

            player.setMoney(player.getMoney()-tile.getCurrentRent());
            tile.getOwner().setMoney(tile.getOwner().getMoney()+tile.getCurrentRent());
            displayPlayerBudget(player);
            displayPlayerBudget(tile.getOwner());

            payPanel.getChildren().get(3).setOnMousePressed(e -> mainWindowOn());
        }
    }

    private void setPlayerOnPanePanel(int nodeID,int playerID) {
        ((ImageView) payPanel.getChildren().get(nodeID)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + playerID + ".png"))));
    }

    private void setPlayerOnHandleWindow(int playerID) {
        ((ImageView) handleWindow.getChildren().get(5)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + playerID + ".png"))));
    }

    public static void displayPlayerBudget(Player player) {
        if (player.getMoney() >= 1000)
            priceLabels[player.getType().ordinal()].setText(player.getMoney()+" €");
        else if (player.getMoney() >= 100)
            priceLabels[player.getType().ordinal()].setText(player.getMoney()+" €");
        else if (player.getMoney() >= 10)
            priceLabels[player.getType().ordinal()].setText(player.getMoney()+" €");
        else if (player.getMoney() >= 0)
            priceLabels[player.getType().ordinal()].setText(player.getMoney()+" €");
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
