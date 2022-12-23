package networking;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements  Runnable{
    private List<Client> clients;

    public Server() {
        clients = new ArrayList<>();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8000)){
            while (true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket, this);
                //client.start();
                //clients.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClient(Client client) {
        clients.add(client);
        if (clients.contains(client)) {
            System.out.println("Client exist");
            clients.remove(client);
        } else {
            System.out.println("Client was not added to the list");
        }
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message) {
        for (Client client : clients) {
            client.sendMessage(message);
        }
    }

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

    public static void main(String[] args) throws IOException {
        new Server().run();
    }
}
