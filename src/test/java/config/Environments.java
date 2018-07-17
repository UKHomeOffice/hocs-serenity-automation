package config;

public enum Environments {

    SR2("spg-dls-310", "7001", "jdbc:oracle:thin:@spg-dls-310:1521:DNDA"),
    TRAINING("spg-wlg-200", "7001", "jdbc:oracle:thin:@spg-wlg-200:1521:DNDA");

    private final String hostname;
    private final String port;
    private final String databaseUrl;

    Environments(String hostname, String port, String databaseUrl) {
        this.hostname = hostname;
        this.port = port;
        this.databaseUrl = databaseUrl;
    }

    public String getNDeliusURL() {
        return hostname + ":" + port;
    }

    public String getDatabaseURL() {
        return databaseUrl;
    }

}
