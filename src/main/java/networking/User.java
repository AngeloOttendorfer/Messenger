package networking;

public class User {

    public String username;
    public String password;
    public String email;

    private boolean isOnline;

    public User(){

    }

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
