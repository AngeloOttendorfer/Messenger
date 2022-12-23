package controller;

import com.example.messenger.MessengerApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatController{
    @FXML
    private Label lbl_msg;
    @FXML
    private Label lbl_chat;
    @FXML
    private TextField tf_msg;
    @FXML
    private TextArea ta_chat;
    @FXML
    private ScrollBar scroll_chat;
    @FXML
    private ScrollBar scroll_members;
    @FXML
    private Button btn_send;
    @FXML
    private Button btn_logout;
    @FXML
    private ListView<String> members = new ListView <>();
    @FXML
    private ObservableList<String> crew = FXCollections.observableArrayList();


    public void enterPressed(KeyEvent e){
        String message = tf_msg.getText();
        if((e.getCode() == KeyCode.ENTER) && (!message.isEmpty())){
            ta_chat.appendText(message + "\n");
        }
    }

    public void sendMessage(){
        String message = tf_msg.getText();
        if(!message.isEmpty()){
            ta_chat.appendText(tf_msg.getText()+ "\n");
        }
    }

    public void log_out() throws IOException {
        //server.removeClient(client);
        FXMLLoader welcomeLoader = new FXMLLoader(MessengerApplication.class.getResource("welcomeScreen.fxml"));
        Stage welcome = (Stage) btn_logout.getScene().getWindow();
        welcome.setScene(new Scene(welcomeLoader.load()));

    }
}
