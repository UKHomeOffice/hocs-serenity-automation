package config;

public enum Users {

    DCU("smoke_test_user_dcu", "Password1"),
    TEST("",""),
    DANNY("danny.large@ten10.com", "Password1!"),
    EAMON("eamon.droko@ten10.com", "Password1!"), //Just Eamon
    FAKE("FakeUser", "FAKE1!");
   /* NoTEAM("NoTEAM@placeholder.com", "Password1!"),
    // Initial Drafting Team Users
    PressOffice("eamon.droko+pressoffice@ten10.com", "Password1!"),
    Finance("eamon.droko+finance@ten10.com", "Password1!"),
    CriminalFinancialInvestigations("eamon.droko+cfi@ten10.com", "Password1!"),
    CTLIPU("eamon.droko+CTLIPU@ten10.com", "Password1!"), //Counter Terrorism Legislation and Investigatory Powers Unit
    CorrespondenceQA("eamon.droko+correspondenceQA@ten10.com", "Password1!"),
    DirectorOfficeCommDir("eamon.droko+DOCD@ten10.com", "Password1!"), //Director Office Communication Directorate
    ExtremismAnalysisUnit("eamon.droko+ExtremismAU@ten10.com", "Password1!"),
    CentralCorrespondence("eamon.droko+centralcorrespondence@ten10.com", "Password1!"),
    ChemBioRadNucExp("eamon.droko@ten10.com", "Password1!"), // Chemical, Biological, Radiological, Nuclear & Explosives
    BorderForceStratComms("eamon.droko+BFSC@ten10.com", "Password1!"), // Border Force Strategic Communications
    Insight("eamon.droko+insight@ten10.com", "Password1!"), // Central Analysis and Insight Team
    CounterExtremismUnit("eamon.droko+CEU@ten10.com", "Password1!"),
    CountryPolicyInfo("eamon.droko+countryPolicy@ten10.com", "Password1!"),
    EconomicAssessmentUnit("eamon.droko+EconomicAU@ten10.com", "Password1!"),
    DirectorsOffice("eamon.droko+directorsOffice@ten10.com", "Password1!"),
    DataIdentityUnit("eamon.droko+DIU@ten10.com", "Password1!"),
    Commercial("eamon.droko+commercial@ten10.com", "Password1!"),
    //Private Office Team Users
    USSCrime("eamon.droko+USSCrime@ten10.com", "Password1!"), // Under Secretary of Stage for Crime, Safeguarding and Vulnerability
    HomeSecretary("eamon.droko+homeSec@ten10.com", "Password1!"),
    DirectorUKVIAsylum("eamon.droko+UKVIAsylum@ten10.com", "Password1!"), // Director of UKVI Asylum
    ResettlementGold("eamon.droko+resettlementGold@ten10.com", "Password1!"), //Director of Resettlement Gold Command
    DCRIE("eamon.droko@ten10.com", "Password1!"),
    DirectorGeneralUKVI("eamon.droko+DGUKVI@ten10.com", "Password1!"),
    DirectorGeneralBorderForce("eamon.droko+DGBorderForce@ten10.com", "Password1!"),
    MinisterForLords("eamon.droko@ten10.com", "Password1!"),
    MoSImmigration("eamon.droko+MoSImmigration@ten10.com", "Password1!"),
    MoSPoliceAndFire("eamon.droko+MoSPoliceAndFire@ten10.com", "Password1!"),
    MoSSecEc("eamon.droko+MoSSecEc@ten10.com", "Password1!"),
    PermanentSecretary("eamon.droko+PermSec@ten10.com", "Password1!"); */


    private final String username;

    private final String password;

    Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}