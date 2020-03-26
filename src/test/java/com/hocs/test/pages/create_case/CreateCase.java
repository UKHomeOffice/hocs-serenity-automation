package com.hocs.test.pages.create_case;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.documents.Documents;
import com.hocs.test.pages.homepage.Homepage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

public class CreateCase extends Page {

    Documents addDocuments;

    SuccessfulCaseCreation successfulCaseCreation;

    Homepage homepage;

    // Elements

    @FindBy(className = "govuk-radios")
    public WebElementFacade allRadioButtons;

    @FindBy(id = "")
    public WebElementFacade caseDetailsFreeTextField;

    @FindBy(id = "")
    public WebElementFacade createBulkCaseRadioButton;

    @FindBy(linkText = "Create Single Case")
    public WebElementFacade createSingleCaseLink;

    @FindBy(id = "")
    public WebElementFacade createSingleCaseRadioButton;

    @FindBy(css = "label[for='case-type-MIN']")
    public WebElementFacade dcuMinRadioButton;

    @FindBy(css = "label[for='case-type-TRO']")
    public WebElementFacade dcuTroRadioButton;

    @FindBy(css = "label[for='case-type-DTEN']")
    public WebElementFacade dcuDtenRadioButton;

    @FindBy(id = "")
    public WebElementFacade addDocumentsNoRadioButton;

    @FindBy(id = "")
    public WebElementFacade addDocumentsYesRadioButton;

    @FindBy(id = "DTENDispatchDeadline-day")
    public WebElementFacade d10DispatchDeadlineDay;

    @FindBy(id = "DTENDispatchDeadline-month")
    public WebElementFacade d10DispatchDeadlineMonth;

    @FindBy(id = "DTENDispatchDeadline-year")
    public WebElementFacade d10DispatchDeadlineYear;

    @FindBy(id = "DTENDraftDeadline-day")
    public WebElementFacade d10DraftDeadlineDay;

    @FindBy(id = "DTENDraftDeadline-month")
    public WebElementFacade d10DraftDeadlineMonth;

    @FindBy(id = "DTENDraftDeadline-year")
    public WebElementFacade d10DraftDeadlineYear;

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
        clickOn(dcuMinRadioButton);
    }

    public void clickDcuTroRadioButton() {
        clickOn(dcuTroRadioButton);
    }

    public void clickDcuDtenRadioButton() {
        clickOn(dcuDtenRadioButton);
    }

    public void enterCaseDetailsFreeText() {
        typeInto(caseDetailsFreeTextField, generateRandomString());
    }

    private void enterDispatchDeadlineDay(String day) {
        typeInto(d10DispatchDeadlineDay, day);
    }

    private void enterDispatchDeadlineMonth(String month) {
        typeInto(d10DispatchDeadlineMonth, month);
    }

    private void enterDispatchDeadlineYear(String year) {
        typeInto(d10DispatchDeadlineYear, year);
    }

    private void enterDraftDeadlineDay(String day) {
        typeInto(d10DispatchDeadlineDay, day);
    }

    private void enterDraftDeadlineMonth(String month) {
        typeInto(d10DispatchDeadlineMonth, month);
    }

    private void enterDraftDeadlineYear(String year) {
        typeInto(d10DispatchDeadlineYear, year);
    }

    // Multi Step Methods

    public void createDCUMinSingleCase() {
        clickOn(homepage.createSingleCase);
        clickOn(dcuMinRadioButton);
        completeSingleCaseCreation();
    }

    public void createDCUMinSingleCaseWithCorrespondenceReceivedDate(String dd, String mm, String yyyy) {
        clickOn(homepage.createSingleCase);
        clickOn(dcuMinRadioButton);
        clickOn(nextButton);
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
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
    }

    public void createDCUTROSingleCaseWithCorrespondenceReceivedDate(String dd, String mm, String yyyy) {
        clickOn(homepage.createSingleCase);
        clickOn(dcuTroRadioButton);
        clickOn(nextButton);
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
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
    }

    public void createDCUDTenSingleCaseWithCorrespondenceReceivedDate(String dd, String mm, String yyyy) {
        clickOn(homepage.createSingleCase);
        clickOn(dcuDtenRadioButton);
        clickOn(nextButton);
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
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
    }

    public void enterNoDate() {
        typeInto(correspondenceReceivedDayField, "");
        typeInto(correspondenceReceivedMonthField, "");
        typeInto(correspondenceReceivedYearField, "");
    }

    public void createCaseWithoutSelectingCorrespondenceType() {
        clickOn(homepage.createSingleCase);
        clickOn(nextButton);
    }

    public void createDCUMinSingleCaseWithID() {
        clickOn(homepage.createSingleCase);
        clickOn(dcuMinRadioButton);
        clickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
    }

    public void createDCUTENSingleCaseWithID() {
        clickOn(homepage.createSingleCase);
        clickOn(dcuDtenRadioButton);
        clickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
    }

    public void createDCUTROSingleCaseWithID() {
        clickOn(homepage.createSingleCase);
        clickOn(dcuTroRadioButton);
        clickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
    }

    public void createDCU10SingleCase() {
        clickOn(homepage.createSingleCase);
        clickOn(dcuDtenRadioButton);
        completeSingleCaseCreation();
    }

    public void createDCUTROSingleCase() {
        clickOn(homepage.createSingleCase);
        clickOn(dcuTroRadioButton);
        completeSingleCaseCreation();
    }

    public void completeSingleCaseCreation() {
        clickOn(nextButton);
        addDocuments.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDay();
        storeCorrespondenceReceivedMonth();
        storeCorrespondenceReceivedYear();
        clickOn(submitButton);
        successfulCaseCreation.getCaseReference();
   }

    public void getToWhenWasCorReceived() {
        //openACase();
        clickOn(dcuMinRadioButton);
        clickOn(nextButton);
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
