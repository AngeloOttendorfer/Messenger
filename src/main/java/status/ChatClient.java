package status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ChatClient(String host, int port, String username) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.username = username;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void goOnline() {
        sendMessage("ONLINE|" + username);
    }

    public void goOffline() {
        sendMessage("OFFLINE|" + username);
    }
}
