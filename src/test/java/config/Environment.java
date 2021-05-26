package config;

public enum Environment {

    LOCAL("http://localhost"),
    CS_DEV("https://dev.internal.cs-notprod.homeoffice.gov.uk/"),
    CS_DEV_MUI("http://dev-management.internal.cs-notprod.homeoffice.gov.uk/"),
    CS_QA("https://qa.internal.cs-notprod.homeoffice.gov.uk/"),
    CS_QA_MUI("https://qa-management.internal.cs-notprod.homeoffice.gov.uk"),
    CS_DEMO("https://demo.cs-notprod.homeoffice.gov.uk/"),
    CS_DEMO_MUI("http://demo-management.cs-notprod.homeoffice.gov.uk/"),
    WCS_DEV("https://dev.internal.wcs-notprod.homeoffice.gov.uk/"),
    WCS_DEV_MUI("http://dev-management.internal.wcs-notprod.homeoffice.gov.uk/"),
    WCS_QA("https://qa.internal.wcs-notprod.homeoffice.gov.uk/"),
    WCS_QA_MUI("https://qa-management.internal.wcs-notprod.homeoffice.gov.uk"),
    WCS_DEMO("https://demo.wcs-notprod.homeoffice.gov.uk/"),
    WCS_DEMO_MUI("http://demo-management.wcs-notprod.homeoffice.gov.uk/"),
    QAX("https://qax.internal.cs-notprod.homeoffice.gov.uk/"),
    QAX_MUI("https://qax-management.internal.cs-notprod.homeoffice.gov.uk/"),
    DELTA("https://delta.internal.cs-notprod.homeoffice.gov.uk/"),
    DELTA_MUI("https://delta-management.internal.cs-notprod.homeoffice.gov.uk/"),
    GAMMA("https://gamma.internal.cs-notprod.homeoffice.gov.uk/"),
    GAMMA_MUI("https://gamma-management.internal.cs-notprod.homeoffice.gov.uk/"),
    EPSILON("https://epsilon.internal.cs-notprod.homeoffice.gov.uk/"),
    EPSILON_MUI(" http://hocs-epsilon-management.internal.cs-notprod.homeoffice.gov.uk/");

    private final String hostname;

    Environment(String hostname) {
        this.hostname = hostname;
    }

    public String getEnvironmentURL() {
        return hostname;
    }

}
