package config;

public enum Services {

    CASE(""),
    INFO(""),
    WORKFLOW("8081");

    private final String port;

    Services(String port) {
        this.port = port;
    }

    public String getService() {
        return ":" + port;
    }

}
