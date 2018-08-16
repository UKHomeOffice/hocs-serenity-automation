package config;

public enum Environments {

    LOCAL("http://localhost", null, null, null, null, null, null, null),
    DEV("https://dev.notprod.cs.homeoffice.gov.uk/", null, null, null, null, null, null, null),
    QA("", null, null, null, null, null, null, null);

    private final String hostname;

    private final String port;

    private final String databaseUrl;

    private final String alfrescoApiUrl;

    private final String alfrescoApiPort;

    private final String userManagementToolApiUrl;

    private final String userManagementToolApiPort;

    private final String userManagementToolClientUrl;

    Environments(String hostname, String port, String databaseUrl, String alfrescoApiUrl,
            String alfrescoApiPort, String userManagementToolApiUrl,
            String userManagementToolApiPort, String userManagementToolClientUrl) {
        this.hostname = hostname;
        this.port = port;
        this.databaseUrl = databaseUrl;
        this.alfrescoApiUrl = alfrescoApiUrl;
        this.alfrescoApiPort = alfrescoApiPort;
        this.userManagementToolApiUrl = userManagementToolApiUrl;
        this.userManagementToolApiPort = userManagementToolApiPort;
        this.userManagementToolClientUrl = userManagementToolClientUrl;
    }


    public String getEnvironmentURL() {
        return hostname;
    }

    public String getApiPort() { return port; }

}
