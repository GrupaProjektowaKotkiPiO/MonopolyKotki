package app.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static app.Monopoly.*;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Pane root;

    public void switchToMenu(ActionEvent event) throws IOException, IOException {
        root= FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        ((GridPane)root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        scene=new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("fxml/Game.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        ((GridPane)root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        scene=new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("fxml/Settings.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        ((GridPane)root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        ((GridPane)root.getChildren().get(2)).setPrefSize(stage.getWidth()*0.95, stage.getHeight()*0.90);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void switchToMultiPlayer(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("fxml/MultiPlayer.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        ((GridPane)root.getChildren().get(1)).setPrefSize(stage.getWidth()*0.95, stage.getHeight()*0.9);
        ((GridPane)root.getChildren().get(2)).setPrefSize(stage.getWidth()*0.95, stage.getHeight()*0.9);
        ((GridPane)root.getChildren().get(3)).setPrefSize(stage.getWidth()*0.95, stage.getHeight()*0.9);
        ((GridPane)root.getChildren().get(4)).setPrefSize(stage.getWidth()*0.95, stage.getHeight()*0.9);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToSinglePlayer(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("fxml/SinglePlayer.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        ((GridPane)root.getChildren().get(1)).setPrefSize(stage.getWidth()*0.95, stage.getHeight()*0.90);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToQuit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
