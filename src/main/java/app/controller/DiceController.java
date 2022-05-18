package app.controller;

import app.dto.Dice;
import app.dto.DiceType;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiceController {
    private Button[] moveButtons;
    private Dice diceLeft;
    private Dice diceRight;

    public DiceController(ObservableList<Node> group3) {
        diceLeft = new Dice(DiceType.LEFT, (ImageView) group3.get(6));
        diceRight = new Dice(DiceType.RIGHT, (ImageView) group3.get(7));
        moveButtons = new Button[4];
        moveButtons[0]=(Button)group3.get(2);
        moveButtons[1]=(Button)group3.get(5);
        moveButtons[2]=(Button)group3.get(10);
        moveButtons[3]=(Button)group3.get(13);
    }

    public int throwTheDice() {
        diceLeft.throwTheDice();
        diceRight.throwTheDice();

        switch (diceLeft.getResultOfThrowingDice()) {
            case 1:
                diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_1.png")));
                break;
            case 2:
                diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_2.png")));
                break;
            case 3:
                diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_3.png")));
                break;
            case 4:
                diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_4.png")));
                break;
            case 5:
                diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_5.png")));
                break;
            default:
                diceLeft.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_6.png")));
        }

        switch (diceRight.getResultOfThrowingDice()) {
            case 1:
                diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_1.png")));
                break;
            case 2:
                diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_2.png")));
                break;
            case 3:
                diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_3.png")));
                break;
            case 4:
                diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_4.png")));
                break;
            case 5:
                diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_5.png")));
                break;
            default:
                diceRight.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_6.png")));
        }

        return diceLeft.getResultOfThrowingDice()+diceRight.getResultOfThrowingDice();
    }

    public Button[] getMoveButtons() { return moveButtons; }
}
