package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField locationField;

    @FXML
    private RadioButton maleRadioBtn;

    @FXML
    private RadioButton femaleRadioBtn;

    @FXML
    void initialize() {
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'register.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'register.fxml'.";
        assert registerBtn != null : "fx:id=\"registerBtn\" was not injected: check your FXML file 'register.fxml'.";
        assert firstNameField != null : "fx:id=\"firstNameField\" was not injected: check your FXML file 'register.fxml'.";
        assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'register.fxml'.";
        assert locationField != null : "fx:id=\"locationField\" was not injected: check your FXML file 'register.fxml'.";
        assert maleRadioBtn != null : "fx:id=\"maleRadioBtn\" was not injected: check your FXML file 'register.fxml'.";
        assert femaleRadioBtn != null : "fx:id=\"femaleRadioBtn\" was not injected: check your FXML file 'register.fxml'.";

        registerBtn.setOnAction(event -> {
            registerUser();
        });
    }

    private void registerUser() {
        DatabaseWorker db = new DatabaseWorker();

        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String location = locationField.getText().trim();
        String gender = maleRadioBtn.isSelected() ? "male" : "female";

        if (login.isEmpty() || password.isEmpty() || firstName.isEmpty()
                || lastName.isEmpty() || location.isEmpty()) {
            System.out.println("401 unauthorized: All fields are required");
            return;
        }

        User user = new User(login, password, firstName, lastName, location, gender);

        db.createUser(user);
    }
}
