package app.service;

import app.controller.*;
import app.dto.PlayerType;
import javafx.scene.control.Button;

public class MoveLogic {
    private final TileController tileController;
    private final PlayerController playerController;
    private final DiceController diceController;
    private final DisplayWindowController displayWindowController;
    private final StatisticsController statisticsController;
    private final BuyHotelWindowController buyHotelWindowController;
    private final BuyHomeWindowController buyHomeWindowController;
    private final ChanceController chanceController;
    private final CommunityChestController communityChestController;
    private int currentPlayer;

    public MoveLogic(TileController inputTileController,
                     PlayerController inputPlayerController,
                     DiceController inputDiceController,
                     DisplayWindowController inputDisplayWindowController,
                     StatisticsController inputStatisticsController,
                     BuyHotelWindowController buyHotelWindowController,
                     BuyHomeWindowController buyHomeWindowController,
                     ChanceController chanceController,
                     CommunityChestController communityChestController) {
        this.tileController = inputTileController;
        this.playerController = inputPlayerController;
        this.diceController = inputDiceController;
        this.displayWindowController = inputDisplayWindowController;
        this.statisticsController = inputStatisticsController;
        this.buyHomeWindowController = buyHomeWindowController;
        this.buyHotelWindowController = buyHotelWindowController;
        this.chanceController = chanceController;
        this.communityChestController = communityChestController;
        currentPlayer=3;
    }

    public void start() {
        move(diceController.getMoveButtons()[0], PlayerType.PLAYER1);
        move(diceController.getMoveButtons()[1], PlayerType.PLAYER2);
        move(diceController.getMoveButtons()[2], PlayerType.PLAYER3);
        move(diceController.getMoveButtons()[3], PlayerType.PLAYER4);
    }

    private void move(Button player, PlayerType type) {
        int i=type.ordinal();

        player.setOnMousePressed(e -> {
            if(currentPlayer==3)
                currentPlayer=-1;
            if(currentPlayer+1==i) {
                PlayerController.getPlayers()[i].setPosition(PlayerController.getPlayers()[i].getPosition() + diceController.throwTheDice());
                if (PlayerController.getPlayers()[i].getPosition() >= 40) {
                    PlayerController.getPlayers()[i].setPosition(PlayerController.getPlayers()[i].getPosition() % 10);
                    PlayerController.getPlayers()[i].setMoney(PlayerController.getPlayers()[i].getMoney()+200);
                }

                playerController.moveThePlayer(type, PlayerController.getPlayers()[i].getPosition() / 10, tileController.getPadding());
                currentPlayer=i;

                statisticsController.
                        action( PlayerController.getPlayers()[currentPlayer],
                                TileController.getBoard()[PlayerController.getPlayers()[currentPlayer].getPosition()],
                                buyHotelWindowController,
                                buyHomeWindowController,
                                chanceController,
                                communityChestController,
                                playerController,
                                tileController);

                if(currentPlayer==3) {
                    displayWindowController.changePlayerInWindow(PlayerController.getPlayers()[0].getType());
                }
                else {
                    displayWindowController.changePlayerInWindow(PlayerController.getPlayers()[currentPlayer+1].getType());
                }

                displayWindowController.changeCardInWindow(PlayerController.getPlayers()[currentPlayer].getPosition());
            }
        });
    }
}