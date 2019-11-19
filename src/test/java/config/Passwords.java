package config;

public enum Passwords {

    CASEYPASS("Password1!"),
    DCUPASS("Password1"),
    TESTERPASS(""),
    EAMONPASS("Password1!"),
    FAKEPASS("FAKE1!"),
    PRODPASS("");

    private final String password;

    Passwords(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
