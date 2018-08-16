package config;

public enum Headers {

    X_DOCREPOSITORY_REMOTE_USER("X-DocRepository-Remote-User", "C01"),
    X_DOCREPOSITORY_REAL_REMOTE_USER("X-DocRepository-Real-Remote-User", "real name");

    private final String headerKey;

    private final String headerValue;

    Headers(String headerKey, String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public String getHeaderValue() {
        return headerValue;
    }

}
