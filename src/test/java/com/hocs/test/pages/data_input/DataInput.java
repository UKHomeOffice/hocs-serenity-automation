package com.hocs.test.pages.data_input;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput extends Page {

    Homepage homepage;
    SuccessfulCaseCreation successfulCaseCreation;
    RecordCorrespondentDetails recordCorrespondentDetails;
    Workstacks workstacks;

    // Elements

    @FindBy(css = "label[for='OriginalChannel-EMAIL']")
    private WebElementFacade emailOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-POST']")
    private WebElementFacade postOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-PHONE']")
    private WebElementFacade phoneOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-NO10']")
    private WebElementFacade numberTenOriginalChannelRadioButton;

    @FindBy(id = "DateOfCorrespondence-day")
    private WebElementFacade dateCorrespondenceSentDayField;

    @FindBy(id = "DateOfCorrespondence-month")
    private WebElementFacade dateCorrespondenceSentMonthField;

    @FindBy(id = "DateOfCorrespondence-year")
    private WebElementFacade dateCorrespondenceSentYearField;

    @FindBy(id = "DateReceived-day")
    private WebElementFacade dateCorrespondenceReceivedDayField;

    @FindBy(id = "DateReceived-month")
    private WebElementFacade dateCorrespondenceReceivedMonthField;

    @FindBy(id = "DateReceived-year")
    private WebElementFacade dateCorrespondenceReceivedYearField;

    @FindBy(id = "")
    private WebElementFacade correspondentDropdown;

    @FindBy(id = "")
    private WebElementFacade referenceTextField;

    //@FindBy(xpath = "(//div//a[@class='govuk-body govuk-link'])[1]")
    @FindBy(xpath = "(//a[text()='Add a '])")
    private WebElementFacade addCorrespondentLink;

    @FindBy(id = "")
    private WebElementFacade primaryCorrespondent;

    @FindBy(css = "label[for='isMember-false']")
    private WebElementFacade correspondentMemberNoRadioButton;

    @FindBy(css = "label[for='isMember-true']")
    private WebElementFacade correspondentMemberYesRadioButton;

    @FindBy(css = "label[for='CopyNumberTen-TRUE']")
    private WebElementFacade sendCopyToNumberTenTickBox;

    @FindBy(css = "label[for='AdditionalCorrespondent-FALSE']")
    private WebElementFacade addCorrespondentNoRadioButton;

    @FindBy(css = "label[for='AdditionalCorrespondent-TRUE']")
    private WebElementFacade addCorrespondentYesRadioButton;

    @FindBy(xpath = "//a[text()='When was the correspondence sent? is required']")
    private WebElementFacade correspondenceDateErrorMessage;

    @FindBy(xpath = "//a[text()='How was the correspondence received? is required']")
    private WebElementFacade howWasCorrespondenceReceivedErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary correspondent? is required']")
    private WebElementFacade whichIsThePrimaryCorrespondentErrorMessage;

    @FindBy(xpath = "//a[text()='The correspondent type must be provided']")
    private WebElementFacade correspondentTypeMustBeProvidedErrorMessage;

    @FindBy(xpath = "//a[text()='Member is required']")
    private WebElementFacade memberIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='The correspondent must have a type']")
    private WebElementFacade correspondentMustHaveATypeErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#fullname-error')]")
    private WebElementFacade correspondentNameMustBeEnteredErrorMessage;

    // Basic Methods

    public void clickAddCorrespondentLink() {
        addCorrespondentLink.click();
    }

    public void clickCorrespondentIsAMember() {
        correspondentMemberYesRadioButton.click();
    }

    public void clickCorrespondentIsNotAMember() {
        correspondentMemberNoRadioButton.click();
    }

    public void clickEmailCorrespondenceChannelRadioButton() {
        emailOriginalChannelRadioButton.click();
    }

    public void clickPostCorrespondenceChannelRadioButton() {
        postOriginalChannelRadioButton.click();
    }

    public void clickNo10CorrespondenceChannelRadioButton() {
        numberTenOriginalChannelRadioButton.click();
    }

    public void clickPhoneCorrespondenceChannelRadioButton() {
        phoneOriginalChannelRadioButton.click();
    }

    public void tickSendCopyToNumber10() {
        sendCopyToNumberTenTickBox.click();
    }


    // Multi Step Methods

    public void dataInputFullFlow() {
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        fillAllMandatoryFields();
        clickContinueButton();
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickFinishButton();
    }

    public void setDateMinusOneDay (){
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
        dateCorrespondenceReceivedDayField.clear();
        dateCorrespondenceReceivedDayField.sendKeys(day);
    }

    public void enterMonthOfCorrespondenceReceived(String month) {
        dateCorrespondenceReceivedMonthField.clear();
        dateCorrespondenceReceivedMonthField.sendKeys(month);
    }

    public void enterYearOfCorrespondenceReceived(String year) {
        dateCorrespondenceReceivedYearField.clear();
        dateCorrespondenceReceivedYearField.sendKeys(year);
    }

    public void enterDayOfCorrespondenceSent(String day) {
        dateCorrespondenceSentDayField.clear();
        dateCorrespondenceSentDayField.sendKeys(day);
    }

    public void enterMonthOfCorrespondenceSent(String month) {
        dateCorrespondenceSentMonthField.clear();
        dateCorrespondenceSentMonthField.sendKeys(month);
    }

    public void enterYearOfCorrespondenceSent(String year) {
        dateCorrespondenceSentYearField.clear();
        dateCorrespondenceSentYearField.sendKeys(year);
    }

    public void enterReferenceText() {
        referenceTextField.clear();
        referenceTextField.sendKeys("");
    }

    public void fillAllMandatoryFields() {
        emailOriginalChannelRadioButton.click();
        enterDayOfCorrespondenceSent(todayPlusNDaysGetDay(-2));
        enterMonthOfCorrespondenceSent(todayPlusNDaysGetMonth(-2));
        enterYearOfCorrespondenceSent(todayPlusNDaysGetYear(-2));
        enterDayOfCorrespondenceReceived(getCurrentDay());
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
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
        assertThat(howWasCorrespondenceReceivedErrorMessage.getText(), is("How was the correspondence received? is required"));
    }

    public void assertWhichIsThePrimaryCorrespondentErrorMessage() {
        assertThat(whichIsThePrimaryCorrespondentErrorMessage.getText(), is("Which is the primary correspondent? is required"));
    }

    public void assertCorrespondentTypeMustBeSelectedErrorMessage() {
        assertThat(correspondentTypeMustBeProvidedErrorMessage.getText(), is ("The correspondent type must be provided"));
    }

    public void assertMemberIsRequiredErrorMessage() {
        assertThat(memberIsRequiredErrorMessage.getText(), is ("Member is required"));
    }

    public void assertCorrespondentTypeDropDownErrorMessage() {
        assertThat(correspondentMustHaveATypeErrorMessage.getText(), is ("The correspondent must have a type"));
    }

    public void assertCorrespondentFullNameErrorMessage() {
        assertThat(correspondentNameMustBeEnteredErrorMessage.getText(), is("The correspondent's full name is required") );
    }
}
