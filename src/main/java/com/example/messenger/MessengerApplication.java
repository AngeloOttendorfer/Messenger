package com.example.messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MessengerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(MessengerApplication.class.getResource("welcomeScreen.fxml"));
        Scene welcome = new Scene(welcomeLoader.load());

        stage.setScene(welcome);
        stage.setTitle("Messenger");
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}