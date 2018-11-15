package config;

public enum Usernames {

    DANNYLARGE("danny.large@ten10.com"),
    DCUSER("smoke_test_user_dcu"),
    TESTER("");

    private final String username;

    Usernames(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
