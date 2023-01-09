package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import networking.Client;
import networking.Server;


public class ChatController{
    @FXML
    private TextField tf_msg;
    @FXML
    private Label lbl_username;
    @FXML
    private TextArea ta_send;
    @FXML
    private TextArea ta_chat;
    @FXML
   private ImageView send_button;

    private final Server server = new Server();
    private final Client client = new Client();
    private final Thread serverThread = new Thread(server);
    private final Thread clientThread = new Thread(client);

    public void handleSendButton(){
        send_button.setOnMouseClicked(event -> {
            if(!tf_msg.getText().isEmpty()){
                server.setMessage(tf_msg.getText());
                ta_chat.appendText(tf_msg.getText() + "\n");
            }
        });
    }

    public void handleEnterKey() {
        tf_msg.setOnKeyPressed(event -> {
            String message = tf_msg.getText();
            if ((event.getCode() == KeyCode.ENTER) && (!message.isEmpty())) {
                server.setMessage(tf_msg.getText());
                ta_chat.appendText(message + "\n");
            }
        });
    }

    @FXML
    public void initialize(){
        clientThread.start();
        serverThread.start();
        server.setClient(lbl_username.getText());
        handleSendButton();
        handleEnterKey();
    }

    public void setUsername(String username){
        lbl_username.setText(username);
    }


    public void sendMessage(String username){
        String message = tf_msg.getText();
        if(!message.isEmpty()){
            ta_chat.appendText(tf_msg.getText()+ "\n");
        }
    }
}
