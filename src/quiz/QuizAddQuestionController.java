package quiz;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class QuizAddQuestionController implements Initializable {

    //Table
    @FXML
    private TableView<Question> tableView;

    //Columns
    @FXML
    private TableColumn<Question, Integer> IDColumn;
    @FXML
    private TableColumn<Question, String> questionColumn;
    @FXML
    private TableColumn<Question, String> opt1Column;
    @FXML
    private TableColumn<Question, String> opt2Column;
    @FXML
    private TableColumn<Question, String> opt3Column;
    @FXML
    private TableColumn<Question, String> opt4Column;
    @FXML
    private TableColumn<Question, String> resultColumn;
    @FXML
    private ChoiceBox selectionDataChoiceBox;

    //Text input
    @FXML
    private TextField questionInput;
    @FXML
    private TextField opt1Input;
    @FXML
    private TextField opt2Input;
    @FXML
    private TextField opt3Input;
    @FXML
    private TextField opt4Input;
    @FXML
    private TextField resultInput;
    private QuestionDao questionDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableView();
        questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("questionText"));
        opt1Column.setCellValueFactory(new PropertyValueFactory<Question, String>("Option1"));
        opt2Column.setCellValueFactory(new PropertyValueFactory<Question, String>("Option2"));
        opt3Column.setCellValueFactory(new PropertyValueFactory<Question, String>("Option3"));
        opt4Column.setCellValueFactory(new PropertyValueFactory<Question, String>("Option4"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("answer"));
        selectionMode();
        selectionDataChoiceBox.setOnAction(this::setSelectionMode);
    }

    public void setSelectionMode(Event event) {
        ObservableList<Question> questionList = questionDao.getAllQuestions2(selectionDataChoiceBox.getValue().toString());
        tableView.setItems(questionList);
    }

    //Submit button
    @FXML
    void submit(ActionEvent event) {
        if (checkValidQuestion(questionInput.getText(),
                opt1Input.getText(), opt2Input.getText(),
                opt3Input.getText(), opt4Input.getText(),
                resultInput.getText()
        )) {
            Question newQuestion = new Question(questionInput.getText(),
                    opt1Input.getText(), opt2Input.getText(),
                    opt3Input.getText(), opt4Input.getText(),
                    resultInput.getText()
            );

            ObservableList<Question> newQuestions = tableView.getItems();
            newQuestions.add(newQuestion);
            tableView.setItems(newQuestions);
            addQuesToDatabase();
        }
    }

    @FXML
    void removeQuestion(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        System.out.println(selectedID);
        if (selectedID >= 0) {
            Question selectedQuestion = tableView.getItems().get(selectedID);
            questionDao.deleteQuestionFromDatabase(selectedQuestion.getId(), selectionDataChoiceBox.getValue().toString());
            tableView.getItems().remove(selectedID);
        }
    }

    public void initTableView() {
        Connection connection = QuizDatabase.connectToDatabase();
        if (connection != null) {
            questionDao = new QuestionDao(connection);
            ObservableList<Question> questionList = questionDao.getAllQuestions2("Quiz");
            tableView.setItems(questionList);
        } else {
            // Handle the case where the connection is null
            System.err.println("Failed to establish a database connection.");
        }
    }

    public void addQuesToDatabase() {
        String addQuestion = questionInput.getText();
        String addAnswer1 = opt1Input.getText();
        String addAnswer2 = opt2Input.getText();
        String addAnswer3 = opt3Input.getText();
        String addAnswer4 = opt4Input.getText();
        String addAnswer = resultInput.getText();

        if (checkValidQuestion(addQuestion, addAnswer1, addAnswer2, addAnswer3, addAnswer4, addAnswer)) {
            Question newQuestion = new Question(addQuestion, addAnswer1, addAnswer2, addAnswer3, addAnswer4, addAnswer);
            //Connection connection = QuizDatabase.connectToDatabase();
            //questionDao = new QuestionDao(connection);
            questionDao.addQuestionToDatabase(newQuestion, selectionDataChoiceBox.getValue().toString());
            questionInput.setText("");
            opt1Input.setText("");
            opt2Input.setText("");
            opt3Input.setText("");
            opt4Input.setText("");
            resultInput.setText("");
        }
    }

    public void selectionMode() {
        String[] selectDb = {"Quiz", "GrammarQuiz"};
        selectionDataChoiceBox.getItems().addAll(selectDb);
        selectionDataChoiceBox.setValue("Quiz");
    }


    private boolean checkValidQuestion(String addQuestion, String addAnswer1, String addAnswer2, String addAnswer3, String addAnswer4, String addAnswer) {
        if (checkEmpty(addAnswer) || checkEmpty(addAnswer1) || checkEmpty(addAnswer2)
                || checkEmpty(addAnswer3) || checkEmpty(addAnswer4) || checkEmpty(addQuestion)
                || ((!addAnswer.equals(addAnswer1)) && (!addAnswer.equals(addAnswer2))
                && (!addAnswer.equals(addAnswer3)) && (!addAnswer.equals(addAnswer4)))) {
            showWarningAlert();
            return false;
        } else {
            return true;
        }
    }

    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Vui lòng kiểm tra kĩ lại !");
        alert.showAndWait();
    }

    boolean checkEmpty(String text) {
        if (text.isEmpty()) {
            return true;
        }
        return false;
    }

    @FXML
    public void exitToQuizMenu(ActionEvent event) {
        try {
            Stage homeStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            homeStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz_menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}