package config;

public enum Usernames {

    CASEYPROSSER("casey.prosser@ten10.com"),
    DCUSER("smoke_test_user_dcu"),
    TESTER(""),
    EAMONDROKO("eamon.droko@ten10.com"),
    FAKEUSER("FakeUser"),
    PRODUSER("");

    private final String username;

    Usernames(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
