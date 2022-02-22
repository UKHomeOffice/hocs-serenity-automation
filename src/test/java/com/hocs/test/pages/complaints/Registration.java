package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Registration extends BasePage {

    RecordCaseData recordCaseData;

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

    public void enterComplainantDOB(String complainantDOB) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(complainantDOB, "Date of Birth");
    }

    public void selectAGender() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    public void selectANationality() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Nationality");
    }

    public void enterACompanyName() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Company Name");
    }

    public void enterAHomeOfficeReference(String HOReference) {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(HOReference, "Home Office Reference");
    }

    public void enterAPortReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Port Reference");
    }

    public void enterComplainantDetails() {
        enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        selectAGender();
        selectANationality();
        enterACompanyName();
        enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
        enterAPortReference();
        clickTheButton("Continue");
    }

    public void selectASpecificComplaintType(String complaintType) {
        switch (complaintType.toUpperCase()) {
            case "SERVICE":
                recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Service", "Complaint Type");
                setSessionVariable("complaintType").to("Service");
                break;
            case "MINOR MISCONDUCT":
                recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Minor Misconduct", "Complaint Type");
                setSessionVariable("complaintType").to("Minor Misconduct");
                break;
            case "EX-GRATIA":
                recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Ex-Gratia", "Complaint Type");
                setSessionVariable("complaintType").to("Ex-Gratia");
                break;
            default:
                pendingStep(complaintType + " is not defined within " + getMethodName());
        }
        clickTheButton("Continue");
        System.out.println("Complaint type: " + complaintType);
    }

    public void selectAComplaintType() {
        String complaintType = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Complaint Type");
        setSessionVariable("complaintType").to(complaintType);
    }

    public void selectAChannel() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel");
    }

    public void selectAdditionalInformation() {
        List<String> additionalInfoList = Arrays.asList("Safe Guarding","Vulnerable","Case is made by / on behalf of a minor");
        Random rand = new Random();
        String additionalInfo = additionalInfoList.get(rand.nextInt(additionalInfoList.size()));
        recordCaseData.checkSpecificCheckbox(additionalInfo);
    }

    public void selectComplaintOrigin() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint Origin");
    }

    public void enterADescriptionOfTheComplaint() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Case Summary");
    }

    public void selectASeverity() {
        String selectedSeverity = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Severity");
        if (selectedSeverity.equalsIgnoreCase("Very high")| selectedSeverity.equalsIgnoreCase("High")) {
            recordCaseData.checkSpecificCheckbox("Safe Guarding");
            recordCaseData.checkSpecificCheckbox("Vulnerable");
        }
    }

    public void enterAPreviousUKVIComplaintReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous UKVI Complaint Ref");
    }

    public void enterAPreviousComplaintReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous Complaint Reference");
    }

    public void enterAThirdPartyReference() { recordCaseData.enterTextIntoTextFieldWithHeading("Third Party Reference");
    }

    public void enterComplaintDetails() {
        selectAChannel();
        enterADescriptionOfTheComplaint();
        selectASeverity();
        enterAPreviousUKVIComplaintReference();
        enterAThirdPartyReference();
    }

    public void openTheServiceComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Service");
    }

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious and Minor");
    }

    public void openTheSeriousComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious");
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//input[not(@checked)]/following-sibling::label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory: claimCategories) {
            if (claimCategory.isCurrentlyVisible()) {
                visibleClaimCategories.add(claimCategory);
            }
        }
        recordCaseData.checkRandomCheckboxFromList(visibleClaimCategories);
    }

    public void selectAnOwningCSU() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Owning CSU");
    }
}
