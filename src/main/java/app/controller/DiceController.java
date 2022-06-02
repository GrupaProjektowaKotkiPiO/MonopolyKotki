package app.controller;

import app.dto.Dice;
import app.dto.DiceType;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DiceController {
    private Button[] moveButtons;
    private Dice diceLeft;
    private Dice diceRight;

    public DiceController(GridPane diceAndMoveGroup) {
        diceLeft = new Dice(DiceType.LEFT, (ImageView) diceAndMoveGroup.getChildren().get(4));
        diceRight = new Dice(DiceType.RIGHT, (ImageView) diceAndMoveGroup.getChildren().get(5));
        moveButtons = new Button[4];

        moveButtons[0]=(Button)diceAndMoveGroup.getChildren().get(1);
        moveButtons[1]=(Button)diceAndMoveGroup.getChildren().get(3);
        moveButtons[2]=(Button)diceAndMoveGroup.getChildren().get(7);
        moveButtons[3]=(Button)diceAndMoveGroup.getChildren().get(9);
    }

    public int throwTheDice() {
        diceLeft.throwTheDice();
        diceRight.throwTheDice();
        diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_"+diceLeft.getResultOfThrowingDice()+".png")));
        diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_"+diceRight.getResultOfThrowingDice()+".png")));

        return diceLeft.getResultOfThrowingDice()+diceRight.getResultOfThrowingDice();
    }

    public Button[] getMoveButtons() { return moveButtons; }
}
