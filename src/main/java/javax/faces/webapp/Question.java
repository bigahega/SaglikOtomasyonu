package javax.faces.webapp;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@ManagedBean
@ViewScoped
public class Question implements Serializable {

    private int questionid;
    private String question;
    private String answer;
    private String questionOwner;
    @ManagedProperty(value = "#registration")
    private Registration registration;

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionOwner() {
        return questionOwner;
    }

    public void setQuestionOwner(String questionOwner) {
        this.questionOwner = questionOwner;
    }

    public String getAnswerOwner() {
        return answerOwner;
    }

    public void setAnswerOwner(String answerOwner) {
        this.answerOwner = answerOwner;
    }

    private String answerOwner;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    private int ownerId;

    public void ask() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saglikotomasyonu", "saglik", "12345");
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO questions(question, ownerid) VALUES ('" + this.question + "', '" + registration.getUserData().getUserId() + "')");
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            try {
                response.sendRedirect("sorular.xhtml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
