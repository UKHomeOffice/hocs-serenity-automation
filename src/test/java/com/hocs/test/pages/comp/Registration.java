package com.hocs.test.pages.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Registration extends BasePage {

    AddCorrespondent addCorrespondent;

    @FindBy(xpath = "//input[@name='ComplainantDOB-day']")
    public WebElementFacade complainantDOBDayField;

    @FindBy(xpath = "//input[@name='ComplainantDOB-month']")
    public WebElementFacade complainantDOBMonthField;

    @FindBy(xpath = "//input[@name='ComplainantDOB-year']")
    public WebElementFacade complainantDOBYearField;

    @FindBy(css = "label[for='ComplainantGender-Male']")
    public WebElementFacade genderMaleRadioButton;

    @FindBy(css = "label[for='ComplainantGender-Female']")
    public WebElementFacade genderFemaleRadioButton;

    @FindBy(id = "ComplainantNationality")
    private WebElementFacade nationalityDropdown;

    @FindBy(xpath = "//input[@name='ComplainantCompanyName']")
    public WebElementFacade companyNameField;

    @FindBy(xpath = "//input[@name='ComplainantHORef']")
    public WebElementFacade homeOfficeReferenceField;

    @FindBy(xpath = "//input[@name='ComplainantPortRef']")
    public WebElementFacade portReferenceField;

    @FindBy(xpath = "//label[contains(text(),'Service')]")
    public WebElementFacade complaintTypeServiceRadioButton;

    @FindBy(css = "label[for='CompType-Minor']")
    public WebElementFacade complaintTypeMinorMisconductRadioButton;

    @FindBy(css = "label[for='Channel-NA']")
    public WebElementFacade channelNotApplicableRadioButton;

    @FindBy(css = "label[for='Channel-TELEPHONE']")
    public WebElementFacade channelTelephoneRadioButton;

    @FindBy(css = "label[for='Channel-EMAIL']")
    public WebElementFacade channelEmailRadioButton;

    @FindBy(css = "label[for='Channel-HMPOTRT']")
    public WebElementFacade channelHMPOTRTRadioButton;

    @FindBy(css = "label[for='Channel-Letter']")
    public WebElementFacade channelLetterRadioButton;

    @FindBy(css = "label[for='Channel-FACE']")
    public WebElementFacade channelFaceRadioButton;

    @FindBy(css = "label[for='Channel-Webform']")
    public WebElementFacade channelWebformRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseSummary']")
    public WebElementFacade caseSummaryTextArea;

    @FindBy(css = "label[for='Severity-1 Very High']")
    public WebElementFacade severityVeryHighRadioButton;

    @FindBy(css = "label[for='Severity-2 High']")
    public WebElementFacade severityHighRadioButton;

    @FindBy(css = "label[for='Severity-3 Medium']")
    public WebElementFacade severityMediumRadioButton;

    @FindBy(css = "label[for='Severity-4 Low']")
    public WebElementFacade severityLowRadioButton;

    @FindBy(css = "label[for='Severity-5 Very Low']")
    public WebElementFacade severityVeryLowRadioButton;

    @FindBy(css = "label[for='SeveritySafeGuarding_SafeGuarding']")
    public WebElementFacade safeGuardingCheckbox;

    @FindBy(css = "label[for='SeverityVulnerable_Vulnerable']")
    public WebElementFacade vulnerableCheckbox;

    @FindBy(xpath = "//input[@name='PrevUkviRef']")
    public WebElementFacade previousUKVIComplaintReferenceField;

    @FindBy(xpath = "//input[@name='3rdPartyRef']")
    public WebElementFacade thirdPartyReferenceField;

    @FindBy(xpath = "//button[text()='Service']")
    public WebElementFacade claimCategoryServiceAccordionButton;

    @FindBy(xpath = "//button[text()='Serious and Minor']")
    public WebElementFacade claimCategorySeriousAndMinorAccordionButton;

    @FindBy(xpath = "//button[text()='Serious']")
    public WebElementFacade claimCategorySeriousAccordionButton;

    @FindBy(css = "label[for='CatDelay_Delay']")
    public WebElementFacade serviceDelayCheckbox;

    @FindBy(css = "label[for='CatAdminErr_AdminErr']")
    public WebElementFacade serviceAdmineProcessErrorCheckbox;

    @FindBy(css = "label[for='CatPoorComm_PoorComm']")
    public WebElementFacade servicePoorCommunicationCheckbox;

    @FindBy(css = "label[for='CatWrongInfo_WrongInfo']")
    public WebElementFacade serviceWrongInformationCheckbox;

    @FindBy(css = "label[for='CatLost_Lost']")
    public WebElementFacade servicePropertiesAndLostDocumentsCheckbox;

    @FindBy(css = "label[for='CatCCPhy_CCPhy']")
    public WebElementFacade servicePhysicalEnvironmentCheckbox;

    @FindBy(css = "label[for='CatCCAvail_CCAvail']")
    public WebElementFacade serviceAvailabilityOfServicesCheckbox;

    @FindBy(css = "label[for='CatCCProvMinor_CCProvMinor']")
    public WebElementFacade serviceProvisionForMinorsCheckbox;

    @FindBy(css = "label[for='CatCCHandle_CCHandle']")
    public WebElementFacade serviceComplaintHandlingCheckbox;

    @FindBy(css = "label[for='CatDamBF_DamBF']")
    public WebElementFacade serviceDamageCheckbox;

    @FindBy(css = "label[for='CatCustodyBF_CustodyBF']")
    public WebElementFacade serviceCustodyCheckbox;

    @FindBy(css = "label[for='CatRude_Rude']")
    public WebElementFacade seriousAndMinorRudenessCheckbox;

    @FindBy(css = "label[for='CatUnfair_Unfair']")
    public WebElementFacade seriousAndMinorUnfairTreatmentCheckbox;

    @FindBy(css = "label[for='CatOtherUnprof_OtherUnprof']")
    public WebElementFacade seriousAndMinorOtherUnprofessionalCheckbox;

    @FindBy(css = "label[for='CatTheft_Theft']")
    public WebElementFacade seriousTheftCheckbox;

    @FindBy(css = "label[for='CatAssault_Assault']")
    public WebElementFacade seriousAssaultCheckbox;

    @FindBy(css = "label[for='CatSexAssault_SexAssault']")
    public WebElementFacade seriousSexualAssaultCheckbox;

    @FindBy(css = "label[for='CatFraud_Fraud']")
    public WebElementFacade seriousFraudCorruptionCheckbox;

    @FindBy(css = "label[for='CatRacism_Racism']")
    public WebElementFacade seriousRacismAndOtherDiscriminationCheckbox;

    @FindBy(id = "OwningCSU")
    private WebElementFacade owningCSUDropdown;

    public void moveCaseFromRegistrationToServiceTriage() {
        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        selectAGender();
        selectANationality();
        enterACompanyName();
        enterAHomeOfficeReference();
        enterAPortReference();
        clickTheButton("Continue");
        selectComplaintType("Service");
        clickTheButton("Continue");
        selectAChannel();
        enterADescriptionOfTheComplaint();
        selectASeverity();
        selectSafeGuardingAndVulnerableIfPossible();
        enterAPreviousUKVIComplaintReference();
        enterAThirdPartyReference();
        clickTheButton("Continue");
        openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        selectAVisibleClaimCategory();
        selectAnOwningCSU();
        safeClickOn(finishButton);
    }

    public void enterComplainantDOB(String complainantDOB) {
        enterDateIntoDateFieldsWithHeading(complainantDOB, "Date of Birth");
    }

    public void selectAGender() {
        selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    public void selectANationality() {
        selectRandomOptionFromDropdownWithHeading("Nationality");
    }

    public void enterACompanyName() {
        enterRandomTextIntoTextFieldWithHeading("Company Name");
    }

    public void enterAHomeOfficeReference() {
        enterRandomTextIntoTextFieldWithHeading("Home Office Reference");
    }

    public void enterAPortReference() {
        enterRandomTextIntoTextFieldWithHeading("Port Reference");
    }


    public void selectComplaintType(String complaintType) {
        switch (complaintType.toUpperCase()) {
            case "SERVICE":
                selectSpecificRadioButtonFromGroupWithHeading("Service", "Complaint Type");
                break;
            case "MINOR MISCONDUCT":
                selectSpecificRadioButtonFromGroupWithHeading("Minor Misconduct", "Complaint Type");
                break;
            default:
                pendingStep(complaintType + " is not defined within " + getMethodName());
        }
    }

    public void selectAChannel() {
        selectRandomRadioButtonFromGroupWithHeading("Channel");
    }

    public void enterADescriptionOfTheComplaint() {
        enterRandomTextIntoTextAreaWithHeading("Case Summary");
    }

    public void selectASeverity() {
        selectRandomRadioButtonFromGroupWithHeading("Severity");
    }

    public void selectSafeGuardingAndVulnerableIfPossible() {
        waitABit(500);
        if (safeGuardingCheckbox.isCurrentlyVisible()) {
            checkSpecificCheckbox("Safe Guarding");
            checkSpecificCheckbox("Vulnerable");
        }
    }

    public void enterAPreviousUKVIComplaintReference() {
        enterRandomTextIntoTextFieldWithHeading("Previous UKVI Complaint Ref");
    }

    public void enterAThirdPartyReference() {
        enterRandomTextIntoTextFieldWithHeading("Third Party Reference");
    }

    public void openTheServiceComplaintCategoryAccordion() {
        openAccordionSection("Service");
    }

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        openAccordionSection("Serious and Minor");
    }

    public void openTheSeriousComplaintCategoryAccordion() {
        openAccordionSection("Serious");
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory: claimCategories) {
            if (claimCategory.isCurrentlyVisible()) {
                visibleClaimCategories.add(claimCategory);
            }
        }
        checkRandomCheckboxFromList(visibleClaimCategories);
    }

    public void selectAnOwningCSU() {
        selectRandomOptionFromDropdownWithHeading("Owning CSU");
    }
}
