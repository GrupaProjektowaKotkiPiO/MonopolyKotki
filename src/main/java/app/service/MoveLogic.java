package app.service;

import app.controller.PlayerController;
import app.dto.Player;

public class MoveLogic {
    PlayerController playerController = new PlayerController();

    public void move(){
        if (!playerController.AreDiceRolled()){
            playerController.rollADice();

        }
    }
}
