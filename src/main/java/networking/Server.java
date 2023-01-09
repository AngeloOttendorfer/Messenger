package networking;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements  Runnable{
    private String message = "";
    private ArrayList<String> clients = new ArrayList<>();
    private Socket socket;

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8000)){
            while (true) {
                socket = serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClient(String username) {
        clients.add(username);
    }

    public void setMessage(String message){
        this.message = message;
    }

    /*public void broadcastMessage(String message) {
        for (String client : clients) {
            client.sendMessage(message);
        }
    }*/

    public void addUserData(String username, String password, String email) {
        Client data = new Client(username, password, email);

        if (containsUserData(username, password)) {
            showException(new RuntimeException("user data already available"));
        }

        try {
            File file = new File("user_data.txt");
            FileWriter writer = new FileWriter(file, true); // true for appending new data in the existing file
            writer.write(data.getUsername() + "," + data.getPassword() + "," + data.getEmail() + "\n");
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

    /*public static void main(String[] args) throws IOException {
        new Server().run();
    }*/
}
