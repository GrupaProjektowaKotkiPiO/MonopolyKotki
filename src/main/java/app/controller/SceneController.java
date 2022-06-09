package app.controller;

import app.dto.Player;
import app.service.MoveLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {
    public Group gameSummary;
    private Stage stage;
    private Scene scene;
    private Pane root;
    private boolean[] tempWindowVisible;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/MultiPlayer.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(6)).setPrefSize(stage.getWidth() * 0.95, stage.getHeight() * 0.9);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        tempWindowVisible=new boolean[2];

        root.setOnScroll(e -> {
            double zoomFactor = 1.5;
            if (e.getDeltaY() <= 0) {
                zoomFactor = 1 / zoomFactor;
                zoom(root, zoomFactor, e.getSceneX(), e.getSceneY(), true);
            } else
                zoom(root, zoomFactor, e.getSceneX(), e.getSceneY(), false);
        });


        Group communityChestCard = (Group) root.getChildren().get(16);
        Group finish = (Group) root.getChildren().get(root.getChildren().size()-1);
        Group chanceCard = (Group) root.getChildren().get(15);
        Group usersMoney = (Group) root.getChildren().get(14);
        Group buyHotel = (Group) root.getChildren().get(12);
        Group buyHome = (Group) root.getChildren().get(11);
        Group payPanel=(Group) root.getChildren().get(10);
        Group buyPanel=(Group) root.getChildren().get(9);
        Group statisticsPanel=(Group) root.getChildren().get(8);
        Group handleWindow=(Group) root.getChildren().get(7);
        GridPane diceAndMoveGroup=(GridPane) root.getChildren().get(6);
        Group players=(Group) root.getChildren().get(5);
        Group tiles = (Group) root.getChildren().get(4);

        diceAndMoveGroup.getChildren().get(10).setOnMousePressed(e -> {
            statisticsPanel.setVisible(true);
            setTempWindowVisible(buyPanel.isVisible(),0);
            setTempWindowVisible(payPanel.isVisible(), 1);
            buyPanel.setVisible(false);
            payPanel.setVisible(false);
            usersMoney.setVisible(false);
        });

        statisticsPanel.getChildren().get(6).setOnMousePressed(e -> {
            statisticsPanel.setVisible(false);
            buyPanel.setVisible(getTempWindowVisible(0));
            payPanel.setVisible(getTempWindowVisible(1));
            usersMoney.setVisible(true);
        });

        (new MoveLogic(new TileController(tiles),
                new PlayerController(players, usersMoney),
                new DiceController(diceAndMoveGroup),
                new DisplayWindowController(handleWindow),
                new StatisticsController(statisticsPanel,buyPanel,payPanel,handleWindow),
                new BuyHotelWindowController(buyHotel),
                new BuyHomeWindowController(buyHome),
                new ChanceController(chanceCard),
                new CommunityChestController(communityChestCard),
                new EndOfTheGame(finish)))
                .start();
    }

    public void switchToSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Settings.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ((GridPane) root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
        ((GridPane) root.getChildren().get(2)).setPrefSize(stage.getWidth() * 0.95, stage.getHeight() * 0.90);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void switchToQuit() {
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

    private void setTempWindowVisible(boolean value, int i) { tempWindowVisible[i]=value; }

    private boolean getTempWindowVisible(int i) { return tempWindowVisible[i]; }

    public void showGameSummary(ActionEvent actionEvent) {
        Player[] players = PlayerController.getPlayers();

        int n = PlayerController.PLAYERS_NUMBER;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (players[j].getScore() < players[j + 1].getScore()) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }

        ((ImageView) gameSummary.getChildren().get(4)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + (players[0].getType().ordinal() + 1) + ".png"))));

        ((ImageView) gameSummary.getChildren().get(5)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + (players[1].getType().ordinal() + 1) + ".png"))));


        ((ImageView) gameSummary.getChildren().get(6)).setImage(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("css/images/Kotek_" + (players[2].getType().ordinal() + 1) + ".png"))));


        gameSummary.setVisible(true);
    }
}
