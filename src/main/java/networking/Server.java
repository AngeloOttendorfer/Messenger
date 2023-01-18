package networking;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public void addUserData(String username, String password, String email) {

        if (containsUserData(username, password)) {
            showException(new RuntimeException("user data already available"));
        }

        try {
            File file = new File("user_data.txt");
            FileWriter writer = new FileWriter(file, true); // true for appending new data in the existing file
            writer.write(username + "," + password + "," + email + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsUserData(String username, String password){
        // read user data from file
        try {
            File file = new File("user_data.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showException(RuntimeException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An Error occurred");
        alert.setContentText(e.getMessage());

        alert.showAndWait();
    }

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(3000);
            while(true) {
                System.out.println("Waiting for clients...");
                socket = serverSocket.accept();
                System.out.println("Connected");
                ClientHandler clientThread = new ClientHandler(socket, clients);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
