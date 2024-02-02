package quiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuizMenuController {
    @FXML
    private Button playButtonQuiz;
    @FXML
    private Button exitButtonQuiz;
    @FXML
    private Button addButtonQuiz;
    @FXML
    private void Initialize(){
        playButtonQuiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try{
                    /*
                Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
                homeStage.close();
                     */
                    Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
                    homeStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz_selection.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        exitButtonQuiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
                homeStage.close();

                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    //stage.initStyle(StageStyle.TRANSPARENT);
                    //scene.setFill(Color.TRANSPARENT);
                    stage.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        addButtonQuiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage homeStage = (Stage)((Button) event.getSource()).getScene().getWindow();
                homeStage.close();

                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz_add_question.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    //stage.initStyle(StageStyle.TRANSPARENT);
                    //scene.setFill(Color.TRANSPARENT);
                    stage.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /*
    @FXML
    public void handleClickAddQuestion(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
     */

}