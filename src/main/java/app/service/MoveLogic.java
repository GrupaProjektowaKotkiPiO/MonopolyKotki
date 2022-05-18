package app.service;

import app.controller.DiceController;
import app.controller.DisplayWindowController;
import app.controller.PlayerController;
import app.controller.TileController;
import app.dto.PlayerType;
import javafx.scene.control.Button;

public class MoveLogic {
    private TileController tileController;
    private PlayerController playerController;
    private DiceController diceController;
    private DisplayWindowController displayWindowController;
    private int currentPlayer;
    private PlayerType temp;

    public MoveLogic(TileController inputTileController, PlayerController inputPlayerController, DiceController inputDiceController, DisplayWindowController inputDisplayWindowController) {
        this.tileController = inputTileController;
        this.playerController = inputPlayerController;
        this.diceController = inputDiceController;
        this.displayWindowController = inputDisplayWindowController;
        currentPlayer=3;
    }

    public void start() {
        move(diceController.getMoveButtons()[0], PlayerType.PLAYER1);
        move(diceController.getMoveButtons()[1], PlayerType.PLAYER2);
        move(diceController.getMoveButtons()[2], PlayerType.PLAYER3);
        move(diceController.getMoveButtons()[3], PlayerType.PLAYER4);
    }

    private void move(Button player, PlayerType type) {
        int i;
        switch (type) {
            case PLAYER1:
                i = 0;
                break;
            case PLAYER2:
                i = 1;
                break;
            case PLAYER3:
                i = 2;
                break;
            default:
                i = 3;
        }

        player.setOnMousePressed(e -> {
            if(currentPlayer==3)
                currentPlayer=-1;
            if(currentPlayer+1==i) {
                playerController.getPlayers()[i].setPosition(playerController.getPlayers()[i].getPosition() + diceController.throwTheDice());
                if (playerController.getPlayers()[i].getPosition() >= 40)
                    playerController.getPlayers()[i].setPosition(playerController.getPlayers()[i].getPosition() % 10);

                if (playerController.getPlayers()[i].getPosition() >= 0 && playerController.getPlayers()[i].getPosition() < 10)
                    playerController.moveThePlayer(type, 0, tileController.getPading());
                if (playerController.getPlayers()[i].getPosition() >= 10 && playerController.getPlayers()[i].getPosition() < 20)
                    playerController.moveThePlayer(type, 1, tileController.getPading());
                if (playerController.getPlayers()[i].getPosition() >= 20 && playerController.getPlayers()[i].getPosition() < 30)
                    playerController.moveThePlayer(type, 2, tileController.getPading());
                if (playerController.getPlayers()[i].getPosition() >= 30 && playerController.getPlayers()[i].getPosition() < 40)
                    playerController.moveThePlayer(type, 3, tileController.getPading());
                currentPlayer=i;

                if(currentPlayer==3)
                    displayWindowController.changePlayerInWindow(playerController.getPlayers()[0].getType());
                else
                    displayWindowController.changePlayerInWindow(playerController.getPlayers()[currentPlayer+1].getType());
            }
        });
    }
}