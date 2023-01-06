package controller;

import com.example.messenger.MessengerApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChatController{
    @FXML
    private TextField tf_msg;
    @FXML
    private TextArea ta_send;
    @FXML
    private TextArea ta_chat;
    @FXML
   private ImageView send_button;


    public void handleSendButton(){
        send_button.setOnMouseClicked(event -> {
            if(!tf_msg.getText().isEmpty()){
                ta_chat.appendText(tf_msg.getText() + "\n");
            }
        });
    }

    public void handleEnterKey() {
        tf_msg.setOnKeyPressed(event -> {
            String message = tf_msg.getText();
            if ((event.getCode() == KeyCode.ENTER) && (!message.isEmpty())) {
                ta_chat.appendText(message + "\n");
            }
        });
    }

    @FXML
    public void initialize(){
        handleSendButton();
        handleEnterKey();
    }


    public void sendMessage(){
        String message = tf_msg.getText();
        if(!message.isEmpty()){
            ta_chat.appendText(tf_msg.getText()+ "\n");
        }
    }

    /*public void log_out() throws IOException {
        //server.removeClient(client);
        FXMLLoader welcomeLoader = new FXMLLoader(MessengerApplication.class.getResource("welcomeScreen.fxml"));
        Stage welcome = (Stage) btn_logout.getScene().getWindow();
        welcome.setScene(new Scene(welcomeLoader.load()));

    }*/
}
