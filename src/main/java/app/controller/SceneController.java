package app.controller;

import app.dto.Dice;
import app.dto.DiceType;
import app.dto.Tile;
import app.service.MoveLogic;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Pane root;
    private Dice diceLeft;
    private Dice diceRight;

    public void switchToMenu(ActionEvent event) throws IOException, IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/Game.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/Settings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        ((GridPane) root.getChildren().get(2)).setPrefSize(stage.getWidth() * 0.95, stage.getHeight() * 0.90);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void switchToMultiPlayer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/MultiPlayer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(4)).setPrefSize(stage.getWidth() * 0.95, stage.getHeight() * 0.9);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        root.setOnScroll(e -> {
            double zoomFactor = 1.5;
            if (e.getDeltaY() <= 0) {
                zoomFactor = 1 / zoomFactor;
                zoom(root, zoomFactor, e.getSceneX(), e.getSceneY(), true);
            } else
                zoom(root, zoomFactor, e.getSceneX(), e.getSceneY(), false);
        });

        ObservableList<Node> group3=((GridPane)root.getChildren().get(4)).getChildren();
        ObservableList<Node> group2=((Group)root.getChildren().get(3)).getChildren();
        (new MoveLogic(new TileController(), new PlayerController(group2), new DiceController(group3))).start();
    }

    public void switchToSinglePlayer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/SinglePlayer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(2)).setPrefSize(stage.getWidth() * 0.95, stage.getHeight() * 0.90);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToQuit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void zoom(Node node, double factor, double x, double y, boolean isZoomOut) {
        double oldScale = node.getScaleX();
        double scale = oldScale * factor;
        if (scale < 1 || scale > 2) return;

        double f = (scale / oldScale) - 1;
        Bounds bounds = node.localToScene(node.getBoundsInLocal());
        double dx = (x - (bounds.getWidth() / 2 + bounds.getMinX()));
        double dy = (y - (bounds.getHeight() / 2 + bounds.getMinY()));
        if ((y < bounds.getWidth() / 25)) return;

        if (!isZoomOut) {
            if (y > (Screen.getPrimary().getVisualBounds().getHeight() / 2))
                node.setTranslateY(node.getTranslateY() - f * dy - bounds.getHeight() / 1.5);
            else
                node.setTranslateY(node.getTranslateY() - f * dy + bounds.getHeight() / 50);
            node.setTranslateX(node.getTranslateX() - f * dx - bounds.getWidth() / 4);
        } else {
            node.setTranslateX(0);
            node.setTranslateY(0);
        }
        node.setScaleX(scale);
        node.setScaleY(scale);
        node.getTransforms().setAll(new Scale(scale, scale));
    }
}
