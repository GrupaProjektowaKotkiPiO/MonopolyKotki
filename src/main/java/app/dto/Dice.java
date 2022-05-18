package app.dto;

import javafx.scene.image.ImageView;

import java.util.Random;

public class Dice {
    private ImageView diceTile;
    private DiceType type;
    private int resultOfThrowingDice;

    public Dice(DiceType inputType, ImageView inputDiceTile) {
        type=inputType;
        diceTile=inputDiceTile;
    }

    public DiceType getType() { return type; }

    public void throwTheDice() {
        Random number=new Random();
        resultOfThrowingDice=number.nextInt(6)+1;
    }

    public ImageView getDiceTile() { return diceTile; }

    public int getResultOfThrowingDice() { return resultOfThrowingDice; }
}
