package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MultiPlayerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}