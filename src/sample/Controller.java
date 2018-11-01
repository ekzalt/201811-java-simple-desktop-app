package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {
    private void renderScene(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));

        try {
            fxmlLoader.load();
        } catch (IOException error) {
            error.printStackTrace();
        }

        Stage stage = new Stage();
        Parent root = fxmlLoader.getRoot();

        loginBtn.getScene().getWindow().hide();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void loginUser(String loginText, String passText) {
        DatabaseWorker db = new DatabaseWorker();

        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passText);

        ResultSet data = db.findUser(user);

        int counter = 0;

        try {
            while (data.next()) {
                counter++;
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        if (counter >= 1) {
            System.out.println("Found! :)");
            renderScene("/sample/home.fxml");
        } else {
            Shake wrongLogin = new Shake(loginField);
            Shake wrongPass = new Shake(passwordField);
            wrongLogin.play();
            wrongPass.play();
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    void initialize() {
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'sample2.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'sample2.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'sample2.fxml'.";
        assert registerBtn != null : "fx:id=\"registerBtn\" was not injected: check your FXML file 'sample2.fxml'.";

        // лямда-функции :)
        registerBtn.setOnAction(event -> {
            System.out.println("register button has been clicked");
            renderScene("/sample/register.fxml");
        });

        loginBtn.setOnAction(event -> {
            System.out.println("login button has been clicked");

            String loginText = loginField.getText().trim();
            String passText = passwordField.getText().trim();

            // написал 2 варианта себе на заметку
            if (loginText.isEmpty() || passText.equals("")) {
                System.out.println("401 unauthorized: login or password is empty");
            } else {
                loginUser(loginText, passText);
            }
        });
    }
}
