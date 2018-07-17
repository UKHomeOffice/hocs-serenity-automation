package config;

public enum ExceptionStatuses {

    ASSESSMENT_OUTCOME_NOT_LINKED("2500013548"),
    CHANGE_OF_REQUIREMENT_SUBTYPE("2500010072"),
    INBOUND_PROCESS_CONTACT_NSI_CREATED_("2500011050"),
    LIMITED_ACCESS_OFFENDER("2500010064"),
    LIMITED_ACCESS_OFFENDER_REMOVED("2500010070");

    private final String exceptionStatusName;

    ExceptionStatuses(String exceptionStatusName) {
        this.exceptionStatusName = exceptionStatusName;
    }

    public String getExceptionStatusName() {
        return exceptionStatusName;
    }

}
