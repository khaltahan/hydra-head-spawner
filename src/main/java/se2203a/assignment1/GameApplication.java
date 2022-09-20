package se2203a.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Loads the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        // Builds the scene graph in memory
        Scene scene = new Scene(fxmlLoader.load(), 800, 850);
        // Set stage title
        stage.setTitle("Hydra Game");
        // Save image from resources folder
        Image icon = new Image("file:src/main/resources/se2203a/assignment1/HydraIcon.png");
        // Set image as the icon of the stage
        stage.getIcons().add(icon);
        // Set stage scene
        stage.setScene(scene);
        // Display the GUI
        stage.show();
    }

    // Application main method
    public static void main(String[] args) {
        launch();
    }
}