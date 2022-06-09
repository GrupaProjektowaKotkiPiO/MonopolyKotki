package app.controller;

import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class ChanceController {
    private static final int NUMBER_OF_CARDS = 5;
    private final Group chanceCard;
    private final Label information;

    //class constructor -> sets the chanceCard & gets information label from fxml file
    public ChanceController(Group chanceCard) {
        this.chanceCard = chanceCard;
        information = (Label) chanceCard.getChildren().get(3);
    }

    //shows our graphic representation of our chance cards
    public void show(Player player, PlayerController playerController, TileController tileController) {

        generateCard(player, playerController, tileController);

        okButtonAction();

        chanceCard.setVisible(true);
    }

    //generates our card which is randomly generated
    private void generateCard(Player player, PlayerController playerController, TileController tileController) {
        switch (getRandomNumber()) {
            case 0:
                information.setText("Masz urodziny i od każdego\ngracza otrzymujesz po\n40 monet.");

                Player[] players = PlayerController.getPlayers();
                //checking if players have enough money or are in game to pay the player
                int playersStillInGameCtr = 0;
                for (int i = 0; i < PlayerController.PLAYERS_NUMBER; i++) {
                    if (players[i].getType() != player.getType() && player.isPlayerInGame()) {
                        players[i].setMoney(players[i].getMoney() - 40);
                        playersStillInGameCtr++;
                    }
                }

                player.setMoney(player.getMoney() + playersStillInGameCtr * 40);
                break;
            case 1:
                information.setText("Idziesz na urlop.\nOtrzymujesz 250 monet\ni stoisz 2 kolejki.");
                player.setMoney(player.getMoney() + 250);
                player.setLostQueues(2);
                break;
            case 2:
                information.setText("Trafiłeś 6 prawidłowych\nskreśleń w grze losowej.\nOtrzymujesz 200 monet.");
                player.setMoney(player.getMoney() + 200);
                break;
            case 3:
                information.setText("Wracasz na start.");
                player.setPosition(0);
                playerController.moveThePlayer(player.getType(), player.getPosition() / 10, tileController.getPadding());
                break;
            default:
                information.setText("Za wygraną w konkursie\nradiowym otrzymujesz\nnagrodę 500 monet.");
                player.setMoney(player.getMoney() + 500);
                break;
        }
    }

    //simple method to generate our next random number which contains Card
    private int getRandomNumber() {
        Random number = new Random();
        return number.nextInt(NUMBER_OF_CARDS);
    }
    //hides our okButton
    private void okButtonAction() {
        ((Button) chanceCard.getChildren().get(2)).setOnAction(e -> chanceCard.setVisible(false));
    }
}
