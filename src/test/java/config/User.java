package config;

public enum User {

    DECS_USER("decs automated regression user", "Password1!", "DECS Regression (decsregression@test.com)"),
    MPAM_USER("mpam automated regression user", "Password1!", "MPAM Regression (mpamregression@test.com)"),
    DCU_USER("dcu automated regression user", "Password1!", "DCU Regression (dcuregression@test.com)"),
    WCS_USER("wcs automated regression user", "Password1!", "WCS Regression (wcsregression@test.com)"),
    COMP_USER("comp automated regression user", "Password1!", "COMP Regression (compregression@test.com)"),
    IEDET_USER("iedet automated regression user", "Password1!", "IEDET Regression (iedetregressiont@test.com)"),
    SMC_USER("smc automated regression user", "Password1!", "SMC Regression (smcregressiont@test.com)"),
    SMC_REGISTRATION_USER_1("smc registration user", "Password1!", "SMC Registration User (smcregistrationuser@test.com)"),
    SMC_REGISTRATION_USER_2("smc registration user 2", "Password1!", "SMC Registration User 2 (smcregistrationuser@test.com)"),
    SMC_CASEWORK_USER("smc casework user", "Password1!", "SMC Casework User (smccaseworkuser@test.com)"),
    FOI_USER("foi automated regression user", "Password1!", "FOI Regression (foiregressiont@test.com)"),
    BF_USER("bf automated regression user", "Password1!", "BF Regression (bfregression@test.com)"),
    TO_USER("to automated regression user", "Password1!", "TO Regression (toregression@test.com)"),
    HMPO_USER("hmpo automated regression user", "Password1!", "HMPO Regression (hmporegression@test.com)"),
    MPAM_SUPERUSER("mpam super user", "Password1!", "MPAM Super User (mpamsuperuser@test.com)"),
    COMP_SUPERUSER("comp super user", "Password1!", "COMP Super User (compsuperuser@test.com)"),
    FOI_SUPERUSER("foi super user", "Password1!", "FOI Super User (foisuperuser@test.com)"),
    WCS_TASKFORCE_USER("wcs taskforce user", "Password1!", "WCS Taskforce User (wcstaskforceuser@test.com)"),
    TEST_USER_1("test user 1", "Password1!", "Test User 1 (testuser1@test.com)"),
    CASEY("casey.prosser@ten10.com", "Password1!", "Casey Prosser (casey.prosser@ten10.com)"),
    CAMERON("cameron.page@ten10.com", "Password1!", "Cameron Page (cameron.page@ten10.com)"),
    FAKE("FakeUser", "FAKE1!", "");

    private final String username;

    private final String password;

    private final String allocationText;

    private User currentUser;

    private User previousUser;

    User(String username, String password, String allocationText) {
        this.username = username;
        this.password = password;
        this.allocationText = allocationText;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAllocationText() {
        return allocationText;
    }
}