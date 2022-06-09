package app.controller;

import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class CommunityChestController {
    private static final int NUMBER_OF_CARDS = 5;
    private final Group communityChestWindow;
    private final Label information;
    public CommunityChestController(Group communityChestWindow) {
        this.communityChestWindow = communityChestWindow;
        information = (Label) communityChestWindow.getChildren().get(3);
    }

    public void show(Player player, PlayerController playerController, TileController tileController) {

        generateCard(player, playerController, tileController);

        okButtonAction();

        communityChestWindow.setVisible(true);
    }

    private void generateCard(Player player, PlayerController playerController, TileController tileController) {
        switch(getRandomNumber()) {
            case 0:
                information.setText("Zachorowałeś i idziesz do\nszpitala. Płacisz za prywatne\nleczenie 200 monet.");
                player.setMoney(player.getMoney() - 200);
                break;
            case 1:
                information.setText("Płacisz za wszystkie media.\nZa każdą posesję 20 monet.");
                player.setMoney(player.getMoney() - player.getCardsCounter() * 20);
                break;
            case 2:
                information.setText("Idziesz zaparkować auto.\nStajesz na pole parking.\nPłacisz za parking 50 monet.");
                player.setPosition(20);
                playerController.moveThePlayer(player.getType(), player.getPosition() / 10, tileController.getPadding());
                player.setMoney(player.getMoney() - 50);
                break;
            case 3:
                information.setText("Idziesz do więzienia.");
                player.setPosition(30);
                player.setPrisoner(true);
                playerController.moveThePlayer(player.getType(), player.getPosition() / 10, tileController.getPadding());
                break;
            default:
                information.setText("Zapłać podatek roczny od\ngruntów. Za każdą posesję\n10 monet.");
                player.setMoney(player.getMoney() - player.getCardsCounter() * 20);
                break;
        }
    }

    private int getRandomNumber() {
        Random number = new Random();
        return number.nextInt(NUMBER_OF_CARDS);
    }

    private void okButtonAction() {
        ((Button) communityChestWindow.getChildren().get(2)).setOnAction(e -> communityChestWindow.setVisible(false));
    }
}
