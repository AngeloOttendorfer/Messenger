package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
        /*List<String> clients = server.getClients();
        if(clients.size() > 0){
            ta_username.setStyle("-fx-text-fill: white;");
            ta_username.appendText(clients.get(clients.size() - 1));
        }*/
        handleSendButton();
        handleEnterKey();
    }

    public void setUsername(String username){
        lbl_username.setText(username);
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
