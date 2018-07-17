package config;

public enum ErrorResolutionStatuses {

    REGENERATED("2500014051"),
    RESOLVED("2500014048"),
    UNDER_INVESTIGATION("2500014050");

    private final String errorResolutionStatusName;

    ErrorResolutionStatuses(String errorResolutionStatusName) {
        this.errorResolutionStatusName = errorResolutionStatusName;
    }

    public String getErrorResolutionStatusName() {
        return errorResolutionStatusName;
    }

}

