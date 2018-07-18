package config;

public enum Environments {

    LOCAL("localhost");

    private final String hostname;

    Environments(String hostname) {
        this.hostname = hostname;
    }

    public String getEnvironmentURL() {
        return hostname;
    }


}
