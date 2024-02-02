package quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuizSelectionController {
    public static String select = "";  // This variable stores the selected option

    public void selectGrammar(ActionEvent event) {
        select = "GrammarQuiz"; // Set the select variable to "GrammarQuiz"
        loadQuizScene(event);


    }

    public void selectVocabulary(ActionEvent event) {
        select = "Quiz"; // Set the select variable to "Quiz"
        loadQuizScene(event);
    }

    private void loadQuizScene(ActionEvent event) {
        try {
            Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
            homeStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz_question.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
