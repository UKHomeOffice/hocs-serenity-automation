package config;

public enum CaseType {


    MIN("DCU Ministerial", 20, "DCU_USER", false),
    DTEN("DCU Number 10", 0, "DCU_USER", false),
    TRO("DCU Treat Official", 20, "DCU_USER", false),
    MPAM("MPAM Case", 20, "MPAM_USER", false),
    MTS("MTS Case", 20, "MPAM_USER", false),
    TO("Treat Official", 20, "TO_USER", false),
    COMP("Complaint Case", 20, "COMP_USER", false),
    COMP2("Complaint Case - Stage 2", 20, "COMP_USER", true),
    COMP2DIRECT("Complaint Case - Stage 2", 20, "COMP_USER", false),
    BF("Border Force Case", 20, "BF_USER", false),
    BF2("Border Force (Stage 2)", 20, "BF_USER", true),
    POGR("HMPO/GRO Complaint Case", 10, "POGR_USER", false),
    POGR2("HMPO/GRO Complaint Case - Stage 2", 10, "POGR_USER", true),
    IEDET("IE Detention Case", 20, "IEDET_USER", false),
    PSU("PSU", 20, "PSU_USER", false),
    FOI("FOI Request", 20, "FOI_USER", false),
    SMC("Serious Misconduct Case", 60, "SMC_USER", false),
    WCS(null, 0, "WCS_USER", false);

    private final String correspondenceTypeLabel;

    private final int defaultSLA;

    private final User associatedUser;

    private final Boolean isStage2;

    CaseType(String correspondenceTypeLabel, int defaultSLA, String associatedUser, Boolean isStage2) {
        this.correspondenceTypeLabel = correspondenceTypeLabel;
        this.defaultSLA = defaultSLA;
        this.associatedUser = User.valueOf(associatedUser);
        this.isStage2 = isStage2;
    }

    public String getCorrespondenceTypeLabel() {
        return correspondenceTypeLabel;
    }

    public int getDefaultSLA() {
        return defaultSLA;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public Boolean isStage2() {
        return isStage2;
    }

    public static CaseType fromLabel(String labelText) {
        for (CaseType caseType : CaseType.values()) {
            if (caseType.correspondenceTypeLabel.equalsIgnoreCase(labelText)) {
                return caseType;
            }
        }
        return null;
    }
}