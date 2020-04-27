package com.hocs.test.pages;

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
    public WebElementFacade dcuDtenRadioButton;

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

    // Basic Methods

    public void assertNoOptionsAvailable() {
        allRadioButtons.shouldContainText("No options available");
    }

    public void clickDcuMinRadioButton() {
        safeClickOn(dcuMinRadioButton);
    }

    public void clickDcuTroRadioButton() {
        safeClickOn(dcuTroRadioButton);
    }

    public void clickDcuDtenRadioButton() {
        safeClickOn(dcuDtenRadioButton);
    }

    // Multi Step Methods

    public void createDCUMinSingleCase() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuMinRadioButton);
        completeSingleCaseCreation();
    }

    public void createDCU10SingleCase() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuDtenRadioButton);
        completeSingleCaseCreation();
    }

    public void createDCUTROSingleCase() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuTroRadioButton);
        completeSingleCaseCreation();
    }

    public void createDCUMinSingleCaseWithCorrespondenceReceivedDate(String dd, String mm, String yyyy) {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuMinRadioButton);
        safeClickOn(nextButton);
        correspondenceReceivedDayField.clear();
        typeInto(correspondenceReceivedDayField, dd);
        correspondenceReceivedMonthField.clear();
        typeInto(correspondenceReceivedMonthField, mm);
        correspondenceReceivedYearField.clear();
        typeInto(correspondenceReceivedYearField, yyyy);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDay();
        storeCorrespondenceReceivedMonth();
        storeCorrespondenceReceivedYear();
        safeClickOn(submitButton);
        createCaseSuccessPage.getCaseReference();
    }

    public void createDCUTROSingleCaseWithCorrespondenceReceivedDate(String dd, String mm, String yyyy) {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuTroRadioButton);
        safeClickOn(nextButton);
        correspondenceReceivedDayField.clear();
        typeInto(correspondenceReceivedDayField, dd);
        correspondenceReceivedMonthField.clear();
        typeInto(correspondenceReceivedMonthField, mm);
        correspondenceReceivedYearField.clear();
        typeInto(correspondenceReceivedYearField, yyyy);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDay();
        storeCorrespondenceReceivedMonth();
        storeCorrespondenceReceivedYear();
        safeClickOn(submitButton);
        createCaseSuccessPage.getCaseReference();
    }

    public void createDCUDTenSingleCaseWithCorrespondenceReceivedDate(String dd, String mm, String yyyy) {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuDtenRadioButton);
        safeClickOn(nextButton);
        correspondenceReceivedDayField.clear();
        typeInto(correspondenceReceivedDayField, dd);
        correspondenceReceivedMonthField.clear();
        typeInto(correspondenceReceivedMonthField, mm);
        correspondenceReceivedYearField.clear();
        typeInto(correspondenceReceivedYearField, yyyy);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDay();
        storeCorrespondenceReceivedMonth();
        storeCorrespondenceReceivedYear();
        safeClickOn(submitButton);
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

    public void createDCUMinSingleCaseWithID() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuMinRadioButton);
        safeClickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        safeClickOn(submitButton);
        createCaseSuccessPage.getCaseReference();
    }

    public void createDCUTENSingleCaseWithID() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuDtenRadioButton);
        safeClickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        safeClickOn(submitButton);
        createCaseSuccessPage.getCaseReference();
    }

    public void createDCUTROSingleCaseWithID() {
        safeClickOn(homepage.createSingleCase);
        safeClickOn(dcuTroRadioButton);
        safeClickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        safeClickOn(submitButton);
        createCaseSuccessPage.getCaseReference();
    }

    public void completeSingleCaseCreation() {
        safeClickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDay();
        storeCorrespondenceReceivedMonth();
        storeCorrespondenceReceivedYear();
        safeClickOn(submitButton);
        createCaseSuccessPage.getCaseReference();
   }

    public void getToWhenWasCorReceived() {
        safeClickOn(dcuMinRadioButton);
        safeClickOn(nextButton);
        waitABit(100);
    }

    public void storeCorrespondenceReceivedDay() {
        String correspondenceDay = correspondenceReceivedDayField.getValue();
        setSessionVariable("correspondenceReceivedDay").to(correspondenceDay);
        System.out.println(correspondenceDay);
    }

    public void storeCorrespondenceReceivedMonth() {
        String correspondenceMonth = correspondenceReceivedMonthField.getValue();
        setSessionVariable("correspondenceReceivedMonth").to(correspondenceMonth);
        System.out.println(correspondenceMonth);
    }

    public void storeCorrespondenceReceivedYear() {
        String correspondenceYear = correspondenceReceivedYearField.getValue();
        setSessionVariable("correspondenceReceivedYear").to(correspondenceYear);
        System.out.println(correspondenceYear);
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
}
