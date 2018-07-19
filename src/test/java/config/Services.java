package config;

public enum Services {

    CASE("8082"),
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
