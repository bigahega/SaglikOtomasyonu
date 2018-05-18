package javax.faces.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name="bean")
public class UserData implements Serializable {

    private String text;
    private String choice;
    private String result;

    public void submit() {
        result = "Submitted values: " + text + ", " + choice;
        System.out.println(result);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getResult() {
        return result;
    }
}
