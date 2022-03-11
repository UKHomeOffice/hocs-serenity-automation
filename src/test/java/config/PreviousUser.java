package config;

public class PreviousUser {
    private static PreviousUser instance;
    private User previousUser;
    private PreviousUser() {
    }
    public synchronized static PreviousUser getInstance() {
        if (instance == null) {
            instance = new PreviousUser();
        }
        return instance;
    }
    public synchronized void setUser(User user) {
        this.previousUser = user;
    }
    public synchronized User getUser() {
        return previousUser;
    }
}
