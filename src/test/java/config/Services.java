package config;

public enum Services {

    CASE(8082),
    HOCS(8080),
    INFO(1),
    WORKFLOW(8081);

    private final int port;

    Services(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

}
