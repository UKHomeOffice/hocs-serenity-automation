package config;

public enum Users {

    AUTOMATION_USER("automation.user", "Password1!", "Automation User (automation.user@test.com)"),
    CASEY("casey.prosser@ten10.com", "Password1!", "Casey Prosser (casey.prosser@ten10.com)"),
    CAMERON("cameron.page@ten10.com", "Password1!", "Cameron Page (cameron.page@ten10.com)"),
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