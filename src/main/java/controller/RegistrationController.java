package controller;

import com.example.messenger.MessengerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import networking.Server;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField pf_pwd;
    @FXML
    private Button btn_signIn;
    @FXML
    private Button btn_return;

    private Server server = new Server();

    public void registration() {
        String username = "";
        String password = "";
        String email = "";

        if((tf_username.getText() != null) && (!tf_username.getText().trim().isEmpty())){
            username = tf_username.getText();
        }
        if((tf_email.getText() != null) && (!tf_email.getText().trim().isEmpty())){
            email = tf_email.getText();
        }

        if((pf_pwd.getText() != null) && (!pf_pwd.getText().trim().isEmpty())){
            password = pf_pwd.getText();
        }
        server.addUserData(username, password, email);
    }

    public void goBack() throws IOException{
        FXMLLoader welcomeLoader = new FXMLLoader(MessengerApplication.class.getResource("welcomeScreen.fxml"));
        Stage welcome = (Stage) btn_return.getScene().getWindow();
        welcome.setScene(new Scene(welcomeLoader.load()));
    }
}
