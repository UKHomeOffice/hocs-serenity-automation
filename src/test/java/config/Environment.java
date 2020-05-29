package config;

public enum Environment {

    LOCAL("http://localhost"),
    DEV("https://dev.cs-notprod.homeoffice.gov.uk/"),
    DEMO("https://demo.cs-notprod.homeoffice.gov.uk/"),
    QA("https://qa.cs-notprod.homeoffice.gov.uk/"),
    MANAGEMENTUIQA("http://qa-management.cs-notprod.homeoffice.gov.uk/"),
    MANAGEMENTUIDEV("http://dev-management.cs-notprod.homeoffice.gov.uk/");

    private final String hostname;

    Environment(String hostname) {
        this.hostname = hostname;
    }

    public String getEnvironmentURL() {
        return hostname;
    }

}
