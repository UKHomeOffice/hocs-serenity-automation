package config;

public enum Environments {

    LOCAL("localhost"),
    DEV("https://dev.notprod.cs.homeoffice.gov.uk/");

    private final String hostname;

    Environments(String hostname) {
        this.hostname = hostname;
    }

    public String getEnvironmentURL() {
        return hostname;
    }


}
