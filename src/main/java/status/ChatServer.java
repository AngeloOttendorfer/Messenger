package status;

import networking.User;

import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private List<User> users;

    public ChatServer() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void setUserOnline(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            user.setOnline(true);
            sendOnlineStatusUpdate(user);
        }
    }

    public void setUserOffline(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            user.setOnline(false);
            sendOnlineStatusUpdate(user);
        }
    }

    private void sendOnlineStatusUpdate(User user) {
        for (User u : users) {
            if (!u.getUsername().equals(user.getUsername())) {
                // Send message to update online status of user u
            }
        }
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
