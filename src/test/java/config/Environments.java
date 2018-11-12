package config;

public enum Environments {

    LOCAL("http://localhost"),
    DEV("https://dev.cs-notprod.homeoffice.gov.uk/"),
    QA("");

    private final String hostname;

    Environments(String hostname) {
        this.hostname = hostname;
    }


    public String getEnvironmentURL() {
        return hostname;
    }

}
