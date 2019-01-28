package com.hocs.test.pages.create_case;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkUpDecision;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateCase extends Page {

    AddDocuments addDocuments;

    SuccessfulCaseCreation successfulCaseCreation;

    Homepage homepage;

    // Elements

    @FindBy(className = "govuk-radios")
    private WebElementFacade allRadioButtons;

    @FindBy(id = "")
    private WebElementFacade caseDetailsFreeTextField;

    @FindBy(id = "")
    private WebElementFacade createBulkCaseRadioButton;

    @FindBy(linkText = "Create Single Case")
    private WebElementFacade createSingleCaseLink;

    @FindBy(id = "")
    private WebElementFacade createSingleCaseRadioButton;

    @FindBy(css = "label[for='case-type-MIN']")
    private WebElementFacade dcuMinRadioButton;

    @FindBy(css = "label[for='case-type-TRO']")
    private WebElementFacade dcuTroRadioButton;

    @FindBy(css = "label[for='case-type-DTEN']")
    private WebElementFacade dcuDtenRadioButton;

    @FindBy(id = "")
    private WebElementFacade addDocumentsNoRadioButton;

    @FindBy(id = "")
    private WebElementFacade addDocumentsYesRadioButton;

    @FindBy(id = "DTENDispatchDeadline-day")
    private WebElementFacade d10DispatchDeadlineDay;

    @FindBy(id = "DTENDispatchDeadline-month")
    private WebElementFacade d10DispatchDeadlineMonth;

    @FindBy(id = "DTENDispatchDeadline-year")
    private WebElementFacade d10DispatchDeadlineYear;

    @FindBy(id = "DTENDraftDeadline-day")
    private WebElementFacade d10DraftDeadlineDay;

    @FindBy(id = "DTENDraftDeadline-month")
    private WebElementFacade d10DraftDeadlineMonth;

    @FindBy(id = "DTENDraftDeadline-year")
    private WebElementFacade d10DraftDeadlineYear;


    // Basic Methods

    public void assertNoOptionsAvailable() {
        assertThat(allRadioButtons.getText(),is("No options available"));
    }

    public void capturedCaseReferenceTest() {
        String thisSessionVar = Serenity.sessionVariableCalled("caseReference");
        System.out.println(thisSessionVar);
    }

    public void clickAddDocumentsRadioButton() {
        addDocumentsYesRadioButton.click();
    }

    public void clickCreateBulkCaseRadioButton() {
        createBulkCaseRadioButton.click();
    }

    public void clickCreateSingleCaseRadioButton() {
        createSingleCaseRadioButton.click();
    }

    public void clickDcuMinRadioButton() {
        dcuMinRadioButton.click();
    }

    public void clickDcuTroRadioButton() { dcuTroRadioButton.click(); }

    public void clickDcuDtenRadioButton() {
        dcuDtenRadioButton.click();
    }

    public void clickNoDocumentsToAddButton() {
        addDocumentsNoRadioButton.click();
    }

    public void enterCaseDetailsFreeText() {
        caseDetailsFreeTextField.clear();
        caseDetailsFreeTextField.sendKeys(generateRandomString());
    }

    public void fillMandatoryDateFields(){
        enterDispatchDeadlineDay(todayPlusNDaysGetDay(+365));
        enterDispatchDeadlineMonth(todayPlusNDaysGetMonth(+365));
        enterDispatchDeadlineYear(todayPlusNDaysGetYear(+365));
        enterDraftDeadlineDay(todayPlusNDaysGetDay(+360));
        enterDraftDeadlineMonth(todayPlusNDaysGetMonth(+360));
        enterDraftDeadlineYear(todayPlusNDaysGetYear(+360));
    }

    private void enterDispatchDeadlineDay(String day){
        d10DispatchDeadlineDay.clear();
        d10DispatchDeadlineDay.sendKeys(day);
    }

    private void enterDispatchDeadlineMonth(String month){
        d10DispatchDeadlineMonth.clear();
        d10DispatchDeadlineMonth.sendKeys(month);
    }

    private void enterDispatchDeadlineYear(String year){
        d10DispatchDeadlineYear.clear();
        d10DispatchDeadlineYear.sendKeys(year);
    }

    private void enterDraftDeadlineDay(String day){
        d10DraftDeadlineDay.clear();
        d10DraftDeadlineDay.sendKeys(day);
    }

    private void enterDraftDeadlineMonth(String month) {
        d10DraftDeadlineMonth.clear();
        d10DraftDeadlineMonth.sendKeys(month);
    }

    private void enterDraftDeadlineYear(String year){
        d10DraftDeadlineYear.clear();
        d10DraftDeadlineYear.sendKeys(year);
    }


    // Multi Step Methods

    // Create a single DCU Min case from the home page
    public void createDCUMinSingleCase() {
        homepage.clickCreateSingleCase();
        clickDcuMinRadioButton();
        completeDCUMINSingleCaseCreation();
    }

    // Create a single DC10 case from the home page
    public void createDC10SingleCase() {
        homepage.clickCreateSingleCase();
        clickDcuDtenRadioButton();
        completeSingleCaseCreation();
    }

    // Create a single DC TRO case from the home page
    public void createDCTROSingleCase() {
        homepage.clickCreateSingleCase();
        clickDcuTroRadioButton();
        completeDCTROSingleCaseCreation();
    }

    public void completeDCTROSingleCaseCreation() {
        clickNextButton();
        addDocuments.uploadDocument();
        clickSubmitButton();
        successfulCaseCreation.getCaseReference();
        successfulCaseCreation.clickSuccessfulCaseBackButton();
        System.out.println("The Case Reference number has been captured as " + Serenity.sessionVariableCalled("caseReference"));
    }

    public void completeDCUMINSingleCaseCreation(){
        clickNextButton();
        addDocuments.uploadDocument();
        clickSubmitButton();
        successfulCaseCreation.getCaseReference();
        successfulCaseCreation.clickSuccessfulCaseBackButton();
        System.out.println("The Case Reference number has been captured as " + Serenity.sessionVariableCalled("caseReference"));
    }

    public void completeSingleCaseCreation() {
        clickNextButton();
        fillMandatoryDateFields();
        addDocuments.uploadDocument();
        clickSubmitButton();
        successfulCaseCreation.getCaseReference();
        successfulCaseCreation.clickSuccessfulCaseBackButton();
        System.out.println("The Case Reference number has been captured as " + Serenity.sessionVariableCalled("caseReference"));
    }


    //Assertions

    public void assertPageTitle() {
        assertTitle("Create case");
    }

    public void radioButtonsAreDisplayed() {
        assertThat(isElementDisplayed(allRadioButtons), is(true));
    }

    public void radioButtonsNotDisplayed() {
        assertThat(isElementDisplayed(allRadioButtons), is(false));
    }

    public void someDCUMINCreateCaseMethod() {
    }

    public void someDCU10CreateCaseMethod() {
    }

    public void someDCUTROCreateCaseMethod() {
    }
}
