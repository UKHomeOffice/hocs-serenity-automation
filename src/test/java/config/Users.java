package config;

public enum Users {

    DCU("smoke_test_user_dcu", "Password1", ""),
    TEST("", "", ""),
    CASEY("casey.prosser@ten10.com", "Password1!", "Casey Prosser (casey.prosser@ten10.com)"),
    EAMON("eamon.droko@ten10.com", "Password1!", "Eamon Droko (eamon.droko@ten10.com)"), //Just Eamon
    FAKE("FakeUser", "FAKE1!", ""),
    PROD("", "", "");

    private final String username;

    private final String password;

    private final String allocationText;

    Users(String username, String password, String allocationText) {
        this.username = username;
        this.password = password;
        this.allocationText = allocationText;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAllocationText() {
        return allocationText;
    }
}