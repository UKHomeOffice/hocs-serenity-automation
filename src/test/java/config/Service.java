package config;

public enum Service {

    CASE(8082),
    HOCS(8080),
    INFO(8085),
    WORKFLOW(8081);

    private final int port;

    Service(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

}
