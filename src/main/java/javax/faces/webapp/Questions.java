package javax.faces.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@ManagedBean
@ViewScoped
public class Questions implements Serializable {

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    private ArrayList<Question> questions = new ArrayList<Question>();

    public Questions() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saglikotomasyonu", "saglik", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT questionid, question, answer, users.username as ownername, doctors.name as doctorname FROM questions JOIN users ON questions.ownerid = users.userid JOIN doctors ON questions.doctorid = doctors.doctorid");
            while(resultSet.next()) {
                Question question = new Question();
                question.setQuestionid(resultSet.getInt("questionid"));
                question.setQuestion(resultSet.getString("question"));
                question.setAnswer(resultSet.getString("answer"));
                question.setQuestionOwner(resultSet.getString("ownername"));
                question.setAnswerOwner(resultSet.getString("doctorname"));
                questions.add(question);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
