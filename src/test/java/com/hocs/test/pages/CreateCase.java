package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateCase extends BasePage {

    Documents addDocuments;

    CreateCase_SuccessPage createCaseSuccessPage;

    Homepage homepage;

    // Elements

    @FindBy(className = "govuk-radios")
    public WebElementFacade allRadioButtons;

    @FindBy(css = "label[for='case-type-MIN']")
    public WebElementFacade dcuMinRadioButton;

    @FindBy(css = "label[for='case-type-TRO']")
    public WebElementFacade dcuTroRadioButton;

    @FindBy(css = "label[for='case-type-DTEN']")
    public WebElementFacade dcuDTenRadioButton;

    @FindBy(css = "label[for='case-type-MPAM']")
    public WebElementFacade ukviRadioButton;

    @FindBy(id = "DateReceived-day")
    public WebElementFacade correspondenceReceivedDayField;

    @FindBy(id = "DateReceived-month")
    public WebElementFacade correspondenceReceivedMonthField;

    @FindBy(id = "DateReceived-year")
    public WebElementFacade correspondenceReceivedYearField;

    @FindBy(xpath = "//a[text()='Case type is required']")
    public WebElementFacade caseTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Date received is required']")
    public WebElementFacade dateReceivedIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Documents are mandatory when bulk creating a case']")
    public WebElementFacade documentsAreMandatoryErrorMessage;

    @FindBy(xpath = "//a[text()='Date received must be a valid date']")
    public WebElementFacade dateReceivedIsInvalidErrorMessage;

    @FindBy(css = "[value = 'Create case']")
    public WebElementFacade createCaseButton;

    @FindBy(css = "[value = 'Create cases']")
    public WebElementFacade createCasesButton;

    // Basic Methods

    public void assertNoOptionsAvailable() {
        allRadioButtons.shouldContainText("No options available");
    }

    private void clickDcuMinRadioButton() {
        safeClickOn(dcuMinRadioButton);
    }

    private void clickDcuTroRadioButton() {
        safeClickOn(dcuTroRadioButton);
    }

    private void clickDcuDtenRadioButton() {
        safeClickOn(dcuDTenRadioButton);
    }

    private void clickUkviRadioButton() {
        safeClickOn(ukviRadioButton);
    }

    public void clickCreateCaseButton() {safeClickOn(createCaseButton);}

    public void clickCreateCasesButton() {safeClickOn(createCasesButton);}

    public void selectCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                clickDcuMinRadioButton();
                break;
            case "TRO":
                clickDcuTroRadioButton();
                break;
            case "DTEN":
                clickDcuDtenRadioButton();
                break;
            case "UKVI":
                clickUkviRadioButton();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    // Multi Step Methods

    public void createCaseOfType(String caseType) {
        safeClickOn(homepage.createSingleCase);
        selectCaseType(caseType);
        completeSingleCaseCreation();
        setSessionVariable("caseType").to(caseType);
    }

    public void createCaseOfTypeWithoutDocument(String caseType) {
        safeClickOn(homepage.createSingleCase);
        selectCaseType(caseType);
        safeClickOn(nextButton);
        clickCreateCaseButton();
        createCaseSuccessPage.getCaseReference();
        setSessionVariable("caseType").to(caseType);
    }

    public void completeSingleCaseCreation() {
        safeClickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.getCaseReference();
    }

    public void createCaseWithSetCorrespondenceReceivedDate(String caseType, String dd, String mm, String yyyy) {
        safeClickOn(homepage.createSingleCase);
        selectCaseType(caseType);
        safeClickOn(nextButton);
        correspondenceReceivedDayField.clear();
        typeInto(correspondenceReceivedDayField, dd);
        correspondenceReceivedMonthField.clear();
        typeInto(correspondenceReceivedMonthField, mm);
        correspondenceReceivedYearField.clear();
        typeInto(correspondenceReceivedYearField, yyyy);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.getCaseReference();
    }

    public void enterNoDate() {
        typeInto(correspondenceReceivedDayField, "");
        typeInto(correspondenceReceivedMonthField, "");
        typeInto(correspondenceReceivedYearField, "");
    }

    public void createCaseWithoutSelectingCorrespondenceType() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(nextButton);
    }

    public void getToWhenWasCorReceived() {
        safeClickOn(dcuMinRadioButton);
        safeClickOn(nextButton);
        waitABit(100);
    }

    public void storeCorrespondenceReceivedDate() {
        String correspondenceDay = correspondenceReceivedDayField.getValue();
        setSessionVariable("correspondenceReceivedDay").to(correspondenceDay);
        String correspondenceMonth = correspondenceReceivedMonthField.getValue();
        setSessionVariable("correspondenceReceivedMonth").to(correspondenceMonth);
        String correspondenceYear = correspondenceReceivedYearField.getValue();
        setSessionVariable("correspondenceReceivedYear").to(correspondenceYear);
    }

    //Assertions

    public void assertPageTitle() {
        assertPageTitle("Create case");
    }

    public void assertCaseTypeErrorMessage() {
        caseTypeIsRequiredErrorMessage.shouldContainText("Case type is required");
    }

    public void assertDateReceivedNotEnteredErrorMessage() {
        dateReceivedIsRequiredErrorMessage.shouldContainText("Date received is required");
    }

    public void assertDateReceivedIsInvalidErrorMessage() {
        dateReceivedIsInvalidErrorMessage.shouldContainText("Date received must be a valid date");
    }

    public void createCaseReceivedNDaysAgo(String caseType, int days) {
        createCaseWithSetCorrespondenceReceivedDate(caseType, todayPlusMinusNDaysGetDay(-days), todayPlusMinusNDaysGetMonth(-days),
                todayPlusMinusNDaysGetYear(-days));
    }
}
