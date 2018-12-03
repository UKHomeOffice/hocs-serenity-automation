package config;

public enum Environments {

    LOCAL("http://localhost"),
    DEV("https://dev.cs-notprod.homeoffice.gov.uk/"),
    QA("https://qa.cs-notprod.homeoffice.gov.uk/"),
    CI("http://keycloak-proxy/");

    private final String hostname;

    Environments(String hostname) {
        this.hostname = hostname;
    }


    public String getEnvironmentURL() {
        return hostname;
    }

}
