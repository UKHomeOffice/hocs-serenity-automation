package config;

public enum Services {

    CASE(""),
    HOCS("8080"),
    INFO(""),
    WORKFLOW("8081");

    private final String port;

    Services(String port) {
        this.port = port;
    }

    public String getPort() {
        return ":" + port;
    }

}
