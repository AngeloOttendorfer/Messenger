package networking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientList {
    private static ClientList instance = null;
    private final ObservableList<String> clients;

    private ClientList() {
        clients = FXCollections.observableArrayList();
    }

    public static ClientList getInstance() {
        if (instance == null) {
            instance = new ClientList();
        }
        return instance;
    }

    public ObservableList<String> getClients() {
        return clients;
    }

    public void addClient(String client) {
        clients.add(client);
    }
}
