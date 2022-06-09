package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class Monopoly extends Application {
    public static final double SCENEHEIGHT=1080;
    public static final double SCENEWIDTH=1920;
    public static final double RATIO=SCENEWIDTH/SCENEHEIGHT;
    //starting the game
    @Override
    public void start(Stage stage) {
        try {
            Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("controller/fxml/Menu.fxml")));
            Scene scene = new Scene(root);
            stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
            stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
            stage.centerOnScreen();
            ((GridPane)root.getChildren().get(1)).setPrefSize(stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}