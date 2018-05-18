package javax.faces.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@ManagedBean
@ViewScoped
public class Articles implements Serializable {

    public ArrayList<Article> getArticles() {
        return articles;
    }

    private ArrayList<Article> articles = new ArrayList<Article>();
    private String errorText;

    public Articles() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saglikotomasyonu", "saglik", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT articleid, title, content, doctors.name AS doctorname FROM articles JOIN doctors ON articles.ownerid = doctors.doctorid");
            while (resultSet.next()) {
                Article article = new Article();
                article.setArticleId(resultSet.getInt("articleid"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setOwnerDoctor(resultSet.getString("doctorname"));
                this.articles.add(article);
            }
        } catch (Exception ex) {
            errorText = ex.getMessage();
            ex.printStackTrace();
        }
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
