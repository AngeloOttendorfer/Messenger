package controller;

import com.example.messenger.MessengerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController{
    @FXML
    private Button btn_register;
    @FXML
    private Button btn_login;

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