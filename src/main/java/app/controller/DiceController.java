package app.controller;

import app.dto.Dice;
import app.dto.DiceType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class DiceController {
    private final Button[] moveButtons;
    private final Dice diceLeft;
    private final Dice diceRight;
    //class contructor -> sets diceLeft and diceRight from fxml file, and gives every player our movebutton
    public DiceController(GridPane diceAndMoveGroup) {
        diceLeft = new Dice(DiceType.LEFT, (ImageView) diceAndMoveGroup.getChildren().get(4));
        diceRight = new Dice(DiceType.RIGHT, (ImageView) diceAndMoveGroup.getChildren().get(5));
        moveButtons = new Button[4];

        moveButtons[0]=(Button)diceAndMoveGroup.getChildren().get(1);
        moveButtons[1]=(Button)diceAndMoveGroup.getChildren().get(3);
        moveButtons[2]=(Button)diceAndMoveGroup.getChildren().get(7);
        moveButtons[3]=(Button)diceAndMoveGroup.getChildren().get(9);
    }
    //method which throws our two dices and sets their image
    public int throwTheDice() {
        diceLeft.throwTheDice();
        diceRight.throwTheDice();
        diceLeft.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_" + diceLeft.getResultOfThrowingDice() + ".png"))));
        diceRight.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_" + diceRight.getResultOfThrowingDice() + ".png"))));

        return diceLeft.getResultOfThrowingDice()+diceRight.getResultOfThrowingDice();
    }
    //getter of buttons
    public Button[] getMoveButtons() { return moveButtons; }
}
