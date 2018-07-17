package config;

public enum Users {

    CASE_ADMIN_PUBLIC("CASE ADMIN (PUBLIC)", "nDelius06", "Password1"),
    CASE_ADMIN_PRIVATE("CASE ADMIN (PRIVATE)", "nDelius20", "Password1"),
    OFFENDER_MANAGER_PUBLIC("OFFENDER MANAGER (PUBLIC)", "nDelius13", "Password1"),
    OFFENDER_MANAGER_PRIVATE("OFFENDER MANAGER (PRIVATE)", "nDelius17", "Password1"),
    LOCAL_DATA_ADMIN_PUBLIC("LOCAL DATA ADMIN (PUBLIC)", "nDelius19", "Password1"),
    LOCAL_DATA_ADMIN_PRIVATE("LOCAL DATA ADMIN (PRIVATE)", "USERNAME", "PASSWORD"), // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    RECEPTIONIST_ACCESS("RECEPTIONIST ACCESS", "USERNAME", "PASSWORD"),             // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    OMIC("OMIC", "USERNAME", "PASSWORD"),                                           // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    PROGRAMMES_OPERATIONAL("PROGRAMMES OPERATIONAL", "USERNAME", "PASSWORD"),       // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    AP_STAFF("AP STAFF", "USERNAME", "PASSWORD"),                                   // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    NATIONAL_SEARCH("NATIONAL SEARCH", "USERNAME", "PASSWORD"),                     // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    NAT_DATA_ADMIN("NAT DATA ADMIN", "USERNAME", "PASSWORD"),                       // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    UPW_OPERATIONAL("UPW OPERATIONAL", "USERNAME", "PASSWORD"),                     // todo - update when we have the new users created from nDelius21 onwards - Kyle Smith 22/05/2018
    DATABASE_ADMIN("DATABASE USER", "delius_app_schema", "Ndelius1");

    private final String role;
    private final String username;
    private final String password;

    Users(String role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
