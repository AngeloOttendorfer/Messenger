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

    public Client(){

    }

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
        try {
            socket = new Socket("127.0.0.1", 8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
