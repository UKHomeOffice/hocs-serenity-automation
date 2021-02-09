package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateCase extends BasePage {

    Documents addDocuments;

    CreateCase_SuccessPage createCaseSuccessPage;

    Homepage homepage;

    Workdays workdays;

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
    public WebElementFacade mpamRadioButton;

    @FindBy(css = "label[for='case-type-MTS']")
    public WebElementFacade mtsRadioButton;

    @FindBy(xpath = "//label[contains(@for, 'case-type')]")
    public WebElementFacade topCaseRadioButton;

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

    private void clickMpamRadioButton() {
        safeClickOn(mpamRadioButton);
    }

    private void clickMtsRadioButton() {
        safeClickOn(mtsRadioButton);
    }

    private void clickTopCaseRadioButton() {
        safeClickOn(topCaseRadioButton);
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
            case "MPAM":
                clickMpamRadioButton();
                break;
            case "MTS":
                clickMtsRadioButton();
                break;
            case "ANY":
                clickTopCaseRadioButton();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    // Multi Step Methods

    public void createCaseOfType(String caseType) {
        safeClickOn(homepage.createSingleCase);
        if (!nextButton.isVisible()) {
            safeClickOn(homepage.createSingleCase);
        }
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

    public void createCaseWithSetCorrespondenceReceivedDate(String caseType, String date) {
        safeClickOn(homepage.createSingleCase);
        selectCaseType(caseType);
        safeClickOn(nextButton);
        waitFor(correspondenceReceivedDayField);
        typeIntoDateField(correspondenceReceivedDayField, correspondenceReceivedMonthField, correspondenceReceivedYearField, date);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.getCaseReference();
        setSessionVariable("caseType").to(caseType);
    }

    public void createCaseReceivedFiveDaysBeforeOrAfterDate(String caseType, String beforeAfter, String inputDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        int numberOfDays = 0;
        if (beforeAfter.toUpperCase().equals("BEFORE")) {
            numberOfDays = (-5);
        } else if (beforeAfter.toUpperCase().equals("AFTER")) {
            numberOfDays = 5;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(inputDate));
        c.add(Calendar.DAY_OF_WEEK, numberOfDays);
        String date = format.format(c.getTime());
        createCaseWithSetCorrespondenceReceivedDate(caseType, date);
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
        setSessionVariable("correspondenceReceivedDate").to(correspondenceDay + "/" + correspondenceMonth + "/" +correspondenceYear);
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

    public void createCaseReceivedNWorkdaysAgo(String caseType, int days) {
        createCaseWithSetCorrespondenceReceivedDate(caseType, workdays.getDateXWorkdaysAgo(days));
    }
}
