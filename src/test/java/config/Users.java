package config;

public enum Users {

    DOM("dominic.barnett-may@digital.homeoffice.gov.uk", "bourne88"),
    TEST("","");

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
