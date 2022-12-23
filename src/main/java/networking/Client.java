package networking;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    private String username;
    private String password;
    private String email;
    private Socket socket;
    private Server server = new Server();
    private PrintWriter writer;
    private Scanner reader;

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Client(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Client(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    public Client(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
        try {
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.reader = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        server.addClient(this); // add this client to the list of connected clients

        // send a message to all connected clients to inform them that a new client has joined the chat
        server.broadcastMessage(username + " has joined the chat");

        // lists for messages from this client and broadcast them to all connected clients
        while (reader.hasNextLine()) {
            String message = reader.nextLine();
            server.broadcastMessage(username + ": " + message);
        }

        //server.removeClient(this); // remove this client from the list of connected clients
        //server.broadcastMessage(username + " has left the chat");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void connectToServer(String serverIP, int port) throws IOException {
        socket = new Socket(serverIP, port);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new Scanner(socket.getInputStream());
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}
