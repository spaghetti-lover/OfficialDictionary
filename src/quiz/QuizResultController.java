package quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class QuizResultController extends QuizController {
    private static double AverageMark = 5.0;
    private static double MiddleMark = 8.0;

    @FXML
    private Label mark,marksText;
    @FXML
    private Button YesButton,NoButton;

    @FXML
    AnchorPane resultQuizPane;

    @FXML
    public void initialize(){
        mark.setText(correct + "/10");

        if(QuizController.wrong <= AverageMark) {
            marksText.setText("Học nhiều lên đi !!!");

        } else if (QuizController.wrong > AverageMark && QuizController.wrong <= MiddleMark) {
            marksText.setText("Cố gắng hơn nữa nhé");

        } else {
            marksText.setText("Bạn rất xuất sắc");
        }
    }
    @FXML
    public void noButton(ActionEvent event){
        try{
            Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
            homeStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quiz/quiz_menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void yesButton(ActionEvent event){
        try{
            Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
            homeStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz_question.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
