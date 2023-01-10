package controller;

import com.example.messenger.MessengerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import networking.Server;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_pwd;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_return;

    private Server server = new Server();

    /**
     * @brief this function is called whenever btn_login (login button in the GUI) is pressed,
     *        afterwards it checks if the user data that was entered by the user is valid, hence
     *        included in the user_data.txt file. An Alert is popping up if the entered data is invalid
     * @throws IOException
     */
    public void login() throws IOException {
        String username = tf_username.getText();
        String password = pf_pwd.getText();

        if(!server.containsUserData(username, password)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Error occurred");
            alert.setContentText("Username and/or password is incorrect");

            alert.showAndWait();
        }
        else{
            //Client client = new Client(username, password);
            //client.start();
            //client.connectToServer("127.0.0.1", 8000);// client-server connection

            FXMLLoader chatLoader = new FXMLLoader(MessengerApplication.class.getResource("chatScreen.fxml"));
            Parent root = chatLoader.load();
            ChatController chatController = chatLoader.getController();
            chatController.setUsername(username);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    /**
     * @brief this method is called if btn_return is pressed in the GUI, afterwards it switches over to the welcomeScreen
     * @throws IOException
     */
    public void goBack()throws IOException{
        FXMLLoader welcomeLoader = new FXMLLoader(MessengerApplication.class.getResource("welcomeScreen.fxml"));
        Stage welcome = (Stage) btn_return.getScene().getWindow();
        welcome.setScene(new Scene(welcomeLoader.load()));
    }
}
