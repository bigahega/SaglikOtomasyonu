package javax.faces.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.sql.*;

@ManagedBean
@SessionScoped
public class Registration implements Serializable {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private String result;
    private UserData userData = null;

    public UserData getUserData() {
        return this.userData;
    }

    private ExternalContext externalContext;

    public Registration() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.result = null;
    }

    public void login() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saglikotomasyonu", "saglik", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s'", this.username, this.password));
            boolean success = false;
            while (resultSet.next()) {
                this.userData = new UserData();
                this.userData.setUsername(this.username);
                this.userData.setUserId(resultSet.getInt("userid"));
                this.userData.setMail(resultSet.getString("mail"));
                success = true;
                HttpServletResponse response = (HttpServletResponse) this.externalContext.getResponse();
                response.sendRedirect("index.xhtml");
            }
            if(!success) {
                this.result = "Kullanıcı bulunamadı ya da şifre hatalı";
                this.externalContext.invalidateSession();
                HttpServletResponse response = (HttpServletResponse) this.externalContext.getResponse();
                response.sendRedirect("uyegirisi.xhtml");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void register() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saglikotomasyonu", "saglik", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO users(username, mail, password) VALUES('%s', '%s', '%s')", this.username, this.email, this.password));

            ResultSet resultSet = statement.executeQuery("SELECT userid FROM users WHERE username = '" + this.username + "'");
            int userId = -1;
            while (resultSet.next()) {
                userId = resultSet.getInt("userid");
            }

            this.userData = new UserData();
            this.userData.setUsername(this.username);
            this.userData.setMail(this.email);
            this.userData.setUserId(userId);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.sendRedirect("index.xhtml");
        } catch (Exception ex) {
            result = ex.getMessage();
            ex.printStackTrace();
        }
    }

    public void logout() {
        this.userData = null;
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            response.sendRedirect("index.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
