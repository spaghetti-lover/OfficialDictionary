package flashcard;

import animatefx.animation.SlideInRight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/flashcard/main.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setTitle("Flash cards");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/flashcard/css/main.css");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            new SlideInRight(root).play();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}