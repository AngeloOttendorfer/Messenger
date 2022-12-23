package controller;

import com.example.messenger.MessengerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import networking.ServerManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements  Initializable{
    @FXML
    private Button btn_register;
    @FXML
    private Button btn_login;
    @FXML
    private ImageView imageView;

    private ServerManager serverManager = ServerManager.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Starte den Server-Thread beim Laden der Welcome.fxml
        Thread serverThread = new Thread(serverManager.getServer());
        serverThread.start();
       /*try{
           Image image = new Image(getClass().getResourceAsStream("/images/messenger.png"));
           imageView.setImage(image);
       } catch(Exception e){
           e.printStackTrace();
       }*/
    }

    public void registration() throws IOException {
        FXMLLoader registrationLoader = new FXMLLoader(MessengerApplication.class.getResource("registrationScreen.fxml"));
        Stage registration = (Stage) btn_register.getScene().getWindow();
        registration.setScene(new Scene(registrationLoader.load()));
    }

    public void login() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(MessengerApplication.class.getResource("loginScreen.fxml"));
        Stage login = (Stage) btn_login.getScene().getWindow();
        login.setScene(new Scene(loginLoader.load()));

    }
}
