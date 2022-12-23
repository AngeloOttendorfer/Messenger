package networking;

public class ServerManager {
    private static ServerManager instance;
    private Server server;
    private Thread serverThread;

    private ServerManager() {
        server = new Server();
        serverThread = new Thread(server);
    }

    public static ServerManager getInstance() {
        if (instance == null) {
            instance = new ServerManager();
        }
        return instance;
    }

    public void startServer() {
        serverThread.start();
    }

    public void stopServer() {
        serverThread.interrupt();
    }

    public Server getServer() {
        return server;
    }
}


