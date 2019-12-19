package config;

public enum Users {

    DCU("smoke_test_user_dcu", "Password1"),
    TEST("",""),
    CASEY("casey.prosser@ten10.com", "Password1!"),
    EAMON("eamon.droko@ten10.com", "Password1!"), //Just Eamon
    FAKE("FakeUser", "FAKE1!"),
    PROD("", "");

    private final String username;

    private final String password;

    Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}