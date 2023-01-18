package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import networking.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.LoginController.users;


public class ChatController extends Thread implements Initializable {
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

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;


    public void connectSocket() {
        try {
            socket = new Socket("localhost", 3000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(LoginController.username + ":")) {
                    continue;
                } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                ta_chat.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSendEvent(MouseEvent event) {
        send();
        for(User user : users) {
            System.out.println(user.username);
        }
    }

    @FXML
    public void sendMessageByKey(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    public void send() {
        String msg = tf_msg.getText();
        writer.println(LoginController.username + ": " + msg);
        ta_chat.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        ta_chat.appendText("Me: " + msg + "\n");
        tf_msg.setText("");
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    public void setUserLabel(){
        lbl_username.setText(LoginController.username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectSocket();
        setUserLabel();
    }
}