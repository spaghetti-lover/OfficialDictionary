package quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDatabase {
    static Connection connection = null;

    public static Connection connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:MultipleQues.db");
            if (connection != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
    /*
    public List<Question> retrieveQuestions(Connection connection) {
        List<Question> questions = new ArrayList<>();
        //PreparedStatement preparedStatement = null;

        try {
            // Call the existing retrieveData method, which retrieves data from the Quiz table
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Quiz");
            ResultSet resultSet = preparedStatement.executeQuery();

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
    */
}

