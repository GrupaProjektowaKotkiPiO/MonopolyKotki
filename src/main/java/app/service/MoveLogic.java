package app.service;

import app.controller.*;
import app.dto.Player;
import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class MoveLogic {
    public Group gameSummary;
    private final TileController tileController;
    private final PlayerController playerController;
    private final DiceController diceController;
    private final DisplayWindowController displayWindowController;
    private final StatisticsController statisticsController;
    private final BuyHotelWindowController buyHotelWindowController;
    private final BuyHomeWindowController buyHomeWindowController;
    private final ChanceController chanceController;
    private final CommunityChestController communityChestController;

    private final EndOfTheGame endOfTheGame;
    private int currentPlayer;
    private int debitCounter;

    //class constructor
    public MoveLogic(TileController inputTileController,
                     PlayerController inputPlayerController,
                     DiceController inputDiceController,
                     DisplayWindowController inputDisplayWindowController,
                     StatisticsController inputStatisticsController,
                     BuyHotelWindowController buyHotelWindowController,
                     BuyHomeWindowController buyHomeWindowController,
                     ChanceController chanceController,
                     CommunityChestController communityChestController,
                     EndOfTheGame inputEndOfTheGame) {
        this.tileController = inputTileController;
        this.playerController = inputPlayerController;
        this.diceController = inputDiceController;
        this.displayWindowController = inputDisplayWindowController;
        this.statisticsController = inputStatisticsController;
        this.buyHomeWindowController = buyHomeWindowController;
        this.buyHotelWindowController = buyHotelWindowController;
        this.chanceController = chanceController;
        this.communityChestController = communityChestController;
        this.endOfTheGame = inputEndOfTheGame;
        currentPlayer = 3;
        debitCounter = 0;
    }

    public void start() {
        move(diceController.getMoveButtons()[0], PlayerType.PLAYER1);
        move(diceController.getMoveButtons()[1], PlayerType.PLAYER2);
        move(diceController.getMoveButtons()[2], PlayerType.PLAYER3);
        move(diceController.getMoveButtons()[3], PlayerType.PLAYER4);
    }

    //implementation of player moving
    private void move(Button player, PlayerType type) {
        //getting which turn is now
        int i = type.ordinal();

        player.setOnMousePressed(e -> {
            if (currentPlayer == 3)
                currentPlayer = -1;

            if (currentPlayer + 1 == i) {
                if(playerController.getPlayers()[i].isPrisoner() && playerController.getPlayers()[i].getLostQueues()!=0) {
                    playerController.getPlayers()[i].setPrisoner(false);
                    playerController.getPlayers()[i].setLostQueues(playerController.getPlayers()[i].getLostQueues() - 1);
                }

                if(playerController.getPlayers()[i].isPrisoner() && playerController.getPlayers()[i].getLostQueues()<3)
                    playerController.getPlayers()[i].setLostQueues(playerController.getPlayers()[i].getLostQueues()+1);

                if(!playerController.getPlayers()[i].isPrisoner()) {
                    PlayerController.getPlayers()[i].setPosition(PlayerController.getPlayers()[i].getPosition() + diceController.throwTheDice());
                    if (PlayerController.getPlayers()[i].getPosition() >= 40) {
                        PlayerController.getPlayers()[i].setPosition(PlayerController.getPlayers()[i].getPosition() % 10);
                        PlayerController.getPlayers()[i].setMoney(PlayerController.getPlayers()[i].getMoney() + 200);
                    }
                }

                playerController.moveThePlayer(type, PlayerController.getPlayers()[i].getPosition() / 10, tileController.getPadding());
                currentPlayer = i;

                statisticsController.
                        action(PlayerController.getPlayers()[currentPlayer],
                                TileController.getBoard()[PlayerController.getPlayers()[currentPlayer].getPosition()],
                                buyHotelWindowController,
                                buyHomeWindowController,
                                chanceController,
                                communityChestController,
                                playerController,
                                tileController);

                skipPlayerIfHasDebit();
                if (currentPlayer == 3) {
                    displayWindowController.changePlayerInWindow(PlayerController.getPlayers()[0].getType());
                } else {
                    displayWindowController.changePlayerInWindow(PlayerController.getPlayers()[currentPlayer + 1].getType());
                }
                if(debitCounter==3)
                    endOfTheGame.show();

                displayWindowController.changeCardInWindow(PlayerController.getPlayers()[currentPlayer].getPosition());
            }
        });
    }

    private boolean hasTheNextPlayerDebit(Player player) {
        int nextPlayer=0;
        if(player.getType().ordinal()<3)
            nextPlayer=player.getType().ordinal()+1;

        return playerController.getPlayers()[nextPlayer].getMoney() < 1;
    }

    private void skipPlayerIfHasDebit() {
        int counter = 0;
        while (hasTheNextPlayerDebit(playerController.getPlayers()[currentPlayer]) && counter<3) {
            if(currentPlayer==3)
                currentPlayer=0;
            else
                currentPlayer++;
            counter++;
        }
        debitCounter=counter;
    }

    private void skipIfPrisoner() {
        while (playerController.getPlayers()[currentPlayer].isPrisoner()) {
            if(currentPlayer==3)
                currentPlayer=0;
            else
                currentPlayer++;
        }
    }
}