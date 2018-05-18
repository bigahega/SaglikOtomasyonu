package javax.faces.webapp;

import java.io.Serializable;

public class Article implements Serializable {

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwnerDoctor() {
        return ownerDoctor;
    }

    public void setOwnerDoctor(String ownerDoctor) {
        this.ownerDoctor = ownerDoctor;
    }

    private int articleId;
    private String title;
    private String content;
    private String ownerDoctor;

}
