package status;

public class User {
    private String username;
    private boolean isOnline;

    public User(String username) {
        this.username = username;
        this.isOnline = false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
