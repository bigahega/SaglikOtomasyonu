package javax.faces.webapp;

public class Question {

    private int questionid;
    private String question;
    private String answer;
    private String questionOwner;

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

}
