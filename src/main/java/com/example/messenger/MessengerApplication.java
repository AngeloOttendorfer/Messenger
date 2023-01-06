package com.example.messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MessengerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MessengerApplication window = new MessengerApplication();
        window.newWindow();
        window.newWindow();
        /*FXMLLoader welcomeLoader = new FXMLLoader(MessengerApplication.class.getResource("welcomeScreen.fxml"));
        Scene welcome = new Scene(welcomeLoader.load());

        stage.setScene(welcome);
        stage.setTitle("Messenger");
        stage.show();*/
    }

    public void newWindow() throws IOException{
        try {
            // FXML-Datei laden und Controller erstellen
            FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("welcomeScreen.fxml"));
            Parent root = welcomeLoader.load();

            // Neue Stage erstellen und Scene hinzufügen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}