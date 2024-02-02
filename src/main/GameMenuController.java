package main;

import animatefx.animation.SlideInRight;
import flashcard.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameMenuController {
    @FXML
    public void loadQuizScene(ActionEvent event) {
        try {
            Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
            homeStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quiz/quiz_menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadFlashcardScene(ActionEvent event) {
        try {
            Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
            homeStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/flashcard/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add("/flashcard/css/main.css");
            Stage stage = new Stage();
          //  scene.getStylesheets().add("/flashcard/css/main.css");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
