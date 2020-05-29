package config;

public class CurrentUser {
    private static CurrentUser instance;
    private User currentUser;
    private CurrentUser() {
    }
    public synchronized static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }
    public synchronized void setUser(User user) {
        this.currentUser = user;
    }
    public synchronized User getUser() {
        return currentUser;
    }
}
