package com.hocs.test.pages.data_input;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput extends Page {

    SuccessfulCaseCreation successfulCaseCreation;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    Page page;

    // Elements

    @FindBy(css = "label[for='OriginalChannel-EMAIL']")
    public WebElementFacade emailOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-POST']")
    public WebElementFacade postOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-PHONE']")
    public WebElementFacade phoneOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-NO10']")
    public WebElementFacade numberTenOriginalChannelRadioButton;

    @FindBy(id = "DateOfCorrespondence-day")
    public WebElementFacade dateCorrespondenceSentDayField;

    @FindBy(id = "DateOfCorrespondence-month")
    public WebElementFacade dateCorrespondenceSentMonthField;

    @FindBy(id = "DateOfCorrespondence-year")
    public WebElementFacade dateCorrespondenceSentYearField;

    @FindBy(id = "DateReceived-day")
    public WebElementFacade dateCorrespondenceReceivedDayField;

    @FindBy(id = "DateReceived-month")
    public WebElementFacade dateCorrespondenceReceivedMonthField;

    @FindBy(id = "DateReceived-year")
    public WebElementFacade dateCorrespondenceReceivedYearField;

    @FindBy(id = "")
    public WebElementFacade referenceTextField;

    @FindBy(xpath = "(//a[@class='govuk-body govuk-link'])[1]")
    public WebElementFacade addCorrespondentLink;

    @FindBy(css = "label[for='isMember-false']")
    public WebElementFacade correspondentMemberNoRadioButton;

    @FindBy(css = "label[for='isMember-true']")
    public WebElementFacade correspondentMemberYesRadioButton;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    public WebElementFacade addMemberOfParliamentSearchField;

    @FindBy(css = "label[for='CopyNumberTen-TRUE']")
    public WebElementFacade sendCopyToNumberTenTickBox;

    @FindBy(css = "label[for='AdditionalCorrespondent-FALSE']")
    public WebElementFacade addCorrespondentNoRadioButton;

    @FindBy(css = "label[for='AdditionalCorrespondent-TRUE']")
    public WebElementFacade addCorrespondentYesRadioButton;

    @FindBy(css = "label[for='CopyNumberTen-TRUE']")
    public WebElementFacade shouldResponseBeCopiedN10YesRadioButton;

    @FindBy(css = "label[for='CopyNumberTen-FALSE']")
    public WebElementFacade shouldResponseBeCopiedN10NoRadioButton;

    @FindBy(xpath = "//a[text()='When was the correspondence sent? is required']")
    public WebElementFacade correspondenceDateErrorMessage;

    @FindBy(xpath = "//a[text()='How was the correspondence received? is required']")
    public WebElementFacade howWasCorrespondenceReceivedErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary correspondent? is required']")
    public WebElementFacade whichIsThePrimaryCorrespondentErrorMessage;

    @FindBy(xpath = "//a[text()='The correspondent type must be provided']")
    public WebElementFacade correspondentTypeMustBeProvidedErrorMessage;

    @FindBy(xpath = "//a[text()='Member is required']")
    public WebElementFacade memberIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='The correspondent must have a type']")
    public WebElementFacade correspondentMustHaveATypeErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#fullname-error')]")
    public WebElementFacade correspondentNameMustBeEnteredErrorMessage;

    @FindBy(xpath = "//span[text()='Should the response be copied to Number 10? is required']")
    public WebElementFacade shouldTheResponseBeCopiedN10ErrorMessage;


    // Basic Methods

    public void selectAddAnMPField() {
        addMemberOfParliamentSearchField.click();
        addMemberOfParliamentSearchField.sendKeys("example");
    }
    public void selectAddACorrespondentLink() {
        clickOn(addCorrespondentLink);
    }

    public void selectCorrespondentIsAMemberRadioButton(){
        clickOn(correspondentMemberYesRadioButton);
    }

    public void selectCorrespondentIsNotAMemberRadioButton() {
        clickOn(correspondentMemberNoRadioButton);
    }

    // Multi Step Methods

    public void dataInputFullFlow() {
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickFinishButton();
    }

    public void dataInputFullFlowWithCopyToN10() {
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes();
        clickContinueButton();
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickFinishButton();
    }

    public void moveCaseFromDataInputToMarkup() {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickFinishButton();
    }

    public void setDateMinusOneDay() {
        enterDayOfCorrespondenceReceived(todayPlusNDaysGetDay(-1));
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
    }

    public void clearDateCorrespondenceReceived() {
        dateCorrespondenceReceivedDayField.clear();
        dateCorrespondenceReceivedMonthField.clear();
        dateCorrespondenceReceivedYearField.clear();
    }

    public void clearDateCorrespondenceSent() {
        dateCorrespondenceSentDayField.clear();
        dateCorrespondenceSentMonthField.clear();
        dateCorrespondenceSentYearField.clear();
    }

    public void enterDayOfCorrespondenceReceived(String day) {
        typeInto(dateCorrespondenceReceivedDayField, day);
    }

    public void enterMonthOfCorrespondenceReceived(String month) {
        typeInto(dateCorrespondenceReceivedMonthField, month);
    }

    public void enterYearOfCorrespondenceReceived(String year) {
        typeInto(dateCorrespondenceReceivedYearField, year);
    }

    public void enterDayOfCorrespondenceSent(String day) {
         typeInto(dateCorrespondenceSentDayField, day);
    }

    public void enterMonthOfCorrespondenceSent(String month) {
        typeInto(dateCorrespondenceSentMonthField, month);
    }

    public void enterYearOfCorrespondenceSent(String year) {
        typeInto(dateCorrespondenceSentYearField, year);
    }

    public void enterReferenceText() {
        referenceTextField.clear();
        referenceTextField.sendKeys("");
    }

    public void fillAllMandatoryCorrespondenceFields() {
        enterDayOfCorrespondenceSent(todayPlusNDaysGetDay(-2));
        enterMonthOfCorrespondenceSent(todayPlusNDaysGetMonth(-2));
        enterYearOfCorrespondenceSent(todayPlusNDaysGetYear(-2));
        enterDayOfCorrespondenceReceived(getCurrentDay());
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
        emailOriginalChannelRadioButton.click();
        shouldResponseBeCopiedN10NoRadioButton.click();
    }

    public void fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes() {
        enterDayOfCorrespondenceSent(todayPlusNDaysGetDay(-2));
        enterMonthOfCorrespondenceSent(todayPlusNDaysGetMonth(-2));
        enterYearOfCorrespondenceSent(todayPlusNDaysGetYear(-2));
        enterDayOfCorrespondenceReceived(getCurrentDay());
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
        emailOriginalChannelRadioButton.click();
        shouldResponseBeCopiedN10YesRadioButton.click();
    }

    public void getToAddMemberOfParliamentPrerequisites() {
        enterDayOfCorrespondenceSent("01");
        enterMonthOfCorrespondenceSent("01");
        enterYearOfCorrespondenceSent("2019");
        emailOriginalChannelRadioButton.click();
        shouldResponseBeCopiedN10NoRadioButton.click();
        clickContinueButton();
        sleep(500);
        addCorrespondentLink.click();
        correspondentMemberYesRadioButton.click();
        clickContinueButton();
    }

    public void getToIsCorrespondentAnMPPrerequisites() {
        enterDayOfCorrespondenceSent("01");
        enterMonthOfCorrespondenceSent("01");
        enterYearOfCorrespondenceSent("2019");
        emailOriginalChannelRadioButton.click();
        shouldResponseBeCopiedN10NoRadioButton.click();
        clickContinueButton();
        sleep(500);
        addCorrespondentLink.click();
    }

    public void getToRecordCorrespondentDetailsPrerequisites() {
        enterDayOfCorrespondenceSent("01");
        enterMonthOfCorrespondenceSent("01");
        enterYearOfCorrespondenceSent("2019");
        emailOriginalChannelRadioButton.click();
        shouldResponseBeCopiedN10NoRadioButton.click();
        clickContinueButton();
        sleep(500);
        addCorrespondentLink.click();
        correspondentMemberNoRadioButton.click();
        continueButton.click();
    }

    public void invalidCorrespondenceReceivedDate() {
        enterDayOfCorrespondenceReceived("32");
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
    }

    public void invalidCorrespondenceSentDate() {
        enterDayOfCorrespondenceSent("32");
        enterMonthOfCorrespondenceSent(getCurrentMonth());
        enterYearOfCorrespondenceSent(getCurrentYear());
    }

    // Assertions

    public void assertPageTitle() {
        assertTitle("Record Correspondence Details");
    }

    public void addACorrespondentLinkIsDisplayed() {
        addCorrespondentLink.isDisplayed();
    }

    public void assertCorrespondenceDateErrorMessage() {
        assertThat(correspondenceDateErrorMessage.getText(), is("When was the correspondence sent? is required"));
    }

    public void assertHowWasCorrespondenceReceivedErrorMessage() {
        assertThat(howWasCorrespondenceReceivedErrorMessage.getText(),
                is("How was the correspondence received? is required"));
    }

    public void assertWhichIsThePrimaryCorrespondentErrorMessage() {
        assertThat(whichIsThePrimaryCorrespondentErrorMessage.getText(),
                is("Which is the primary correspondent? is required"));
    }

    public void assertCorrespondentTypeMustBeSelectedErrorMessage() {
        assertThat(correspondentTypeMustBeProvidedErrorMessage.getText(), is("The correspondent type must be provided"));
    }

    public void assertMemberIsRequiredErrorMessage() {
        assertThat(memberIsRequiredErrorMessage.getText(), is("Member is required"));
    }

    public void assertCorrespondentTypeDropDownErrorMessage() {
        assertThat(correspondentMustHaveATypeErrorMessage.getText(), is("The correspondent must have a type"));
    }

    public void assertCorrespondentFullNameErrorMessage() {
        assertThat(correspondentNameMustBeEnteredErrorMessage.getText(), is("The correspondent's full name is required"));
    }

    public void assertShouldResponseBeCopiedN10ErrorMessage() {
        assertThat(shouldTheResponseBeCopiedN10ErrorMessage.getText(),
                is("Should the response be copied to Number 10? is required"));
    }
}
