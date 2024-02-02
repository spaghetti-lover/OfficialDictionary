package quiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private Connection connection;
    static List<Question> questions = new ArrayList<>();

    public QuestionDao(){

    }
    public QuestionDao(Connection connection) {
        this.connection = connection;
    }

    public List<Question> getAllQuestions(String select) {
        //List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM " + select;

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(1));  // Assuming the ID is in the first column
                question.setQuestionText(resultSet.getString(2)); // Assuming the question is in the third column
                question.setOption1(resultSet.getString(3));
                question.setOption2(resultSet.getString(4));
                question.setOption3(resultSet.getString(5));
                question.setOption4(resultSet.getString(6));
                question.setAnswer(resultSet.getString(7));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }


    public void addQuestionToDatabase(Question question, String select) {
        //Connection connection = QuizDatabase.connectToDatabase();
        //QuestionDao questionDAO = new QuestionDao(connection);
        String query = "INSERT INTO " + select + " (Question, Option1, Option2, Option3, Option4, Result) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, question.getQuestionText());
            statement.setString(2, question.getOption1());
            statement.setString(3, question.getOption2());
            statement.setString(4, question.getOption3());
            statement.setString(5, question.getOption4());
            statement.setString(6, question.getAnswer());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Question> getAllQuestions2(String select) {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        String query = "SELECT * FROM " + select;

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(1));
                question.setQuestionText(resultSet.getString(2));
                question.setOption1(resultSet.getString(3));
                question.setOption2(resultSet.getString(4));
                question.setOption3(resultSet.getString(5));
                question.setOption4(resultSet.getString(6));
                question.setAnswer(resultSet.getString(7));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public void deleteQuestionFromDatabase(int questionId, String select) {
        String query = "DELETE FROM " + select + " WHERE IDQues = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
