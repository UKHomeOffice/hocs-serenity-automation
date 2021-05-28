package com.hocs.test.pages.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Registration extends BasePage {

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

    @FindBy(css = "label[for='CompType-Service']")
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

    @FindBy(xpath = "//textarea[@name='Description']")
    public WebElementFacade descriptionTextArea;

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
        typeIntoDateField(complainantDOBDayField, complainantDOBMonthField, complainantDOBYearField, complainantDOB);
    }

    public void selectAGender() {
        List<WebElementFacade> genders = findAll("//label[contains(@for,'ComplainantGender')]");
        safeClickOn(getRandomElementFromList(genders));
    }

    public void selectANationality(String nationality) {
        nationalityDropdown.selectByVisibleText(nationality);
    }

    public void enterACompanyName(String companyName) {
        companyNameField.sendKeys(companyName);
    }

    public void enterAHomeOfficeReference(String homeOfficeReference) {
        homeOfficeReferenceField.sendKeys(homeOfficeReference);
    }

    public void enterAPortReference(String portReference) {
        portReferenceField.sendKeys(portReference);
    }


    public void selectComplaintType(String complaintType) {
        switch (complaintType.toUpperCase()) {
            case "SERVICE":
                safeClickOn(complaintTypeServiceRadioButton);
                break;
            case "MINOR MISCONDUCT":
                safeClickOn(complaintTypeMinorMisconductRadioButton);
                break;
            default:
                pendingStep(complaintType + " is not defined within " + getMethodName());
        }
    }

    public void selectAChannel() {
        List<WebElementFacade> channels = findAll("//label[contains(@for,'Channel')]");
        safeClickOn(getRandomElementFromList(channels));
    }

    public void enterADescriptionOfTheComplaint(String complaintDescription) {
        descriptionTextArea.sendKeys(complaintDescription);
    }

    public void selectASeverity() {
        List<WebElementFacade> severities = findAll("//label[contains(@for,'Severity-')]");
        safeClickOn(getRandomElementFromList(severities));
    }

    public void selectSafeGuardingAndVulnerableIfPossible() {
        waitABit(500);
        if (safeGuardingCheckbox.isCurrentlyVisible()) {
            safeClickOn(safeGuardingCheckbox);
            safeClickOn(vulnerableCheckbox);
        }
    }

    public void enterAPreviousUKVIComplaintReference(String previousUKVIComplaintReference) {
        previousUKVIComplaintReferenceField.sendKeys(previousUKVIComplaintReference);
    }

    public void enterAThirdPartyReference(String thirdPartyReference) {
        thirdPartyReferenceField.sendKeys(thirdPartyReference);
    }

    public void openTheServiceComplaintCategoryAccordion() {
        safeClickOn(claimCategoryServiceAccordionButton);
    }

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        safeClickOn(claimCategorySeriousAndMinorAccordionButton);
    }

    public void openTheSeriousComplaintCategoryAccordion() {
        safeClickOn(claimCategorySeriousAccordionButton);
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory: claimCategories) {
            if (claimCategory.isCurrentlyVisible()) {
                visibleClaimCategories.add(claimCategory);
            }
        }
        safeClickOn(getRandomElementFromList(visibleClaimCategories));
    }

    public void selectAnOwningCSU() {
        owningCSUDropdown.selectByIndex(1);
    }

    public void assertErrorMessageIsDisplayed(String expectedMessage) {
        String expectedText = null;
        switch (expectedMessage.toUpperCase()) {
            case "PRIMARY CORRESPONDENT":
                expectedText = "Which is the primary correspondent? is required";
                break;
            case "COMPLAINT TYPE":
                expectedText = "Complaint Type is required";
                break;
            case "CHANNEL":
                expectedText = "Channel is required";
                break;
            case "SEVERITY":
                expectedText = "Severity is required";
                break;
            case "OWNING CSU":
                expectedText = "Owning CSU is required";
                break;
            default:
                pendingStep(expectedMessage + " is not defined within " + getMethodName());
        }
        WebElementFacade errorMessage = findBy("//ul[@class = 'govuk-list govuk-error-summary__list']//a[contains(text(), '" + expectedText +
                "')]");
        errorMessage.shouldBeVisible();
    }
}
