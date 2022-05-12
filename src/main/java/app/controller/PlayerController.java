package app.controller;

public class PlayerController {
    private double dice1,dice2;     //Zmienne na przetrzymywanie wartosci rzutu kostką
    private boolean areDiceRolled;  //Zmienna po to by sprawdzać czy gracz już zakręcił kostką czy nie
    private void rollADice(){
        dice1 = Math.floor(Math.random() * 6) + 1;
        dice2 = Math.floor(Math.random() * 6) + 1;
        areDiceRolled = true;
    }
    private void resetDice(){
        areDiceRolled = false;
    }

}
