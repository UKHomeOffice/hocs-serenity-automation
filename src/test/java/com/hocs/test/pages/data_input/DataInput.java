package com.hocs.test.pages.data_input;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.homepage.Homepage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class DataInput extends Page {

    SuccessfulCaseCreation successfulCaseCreation;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    Homepage homepage;

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

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-day']")
    public WebElementFacade dtenDraftingDeadlineDayField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-month']")
    public WebElementFacade dtenDraftingDeadlineMonthField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-year']")
    public WebElementFacade dtenDraftingDeadlineYearField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-day']")
    public WebElementFacade dtenDispatchDeadlineDayField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-month']")
    public WebElementFacade dtenDispatchDeadlineMonthField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-year']")
    public WebElementFacade dtenDispatchDeadlineYearField;

    @FindBy(xpath = "//div[@class='css-xp4uvy govuk-typeahead__single-value']")
    public WebElementFacade memberOfParliamentName;

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

    @FindBy(xpath = "//span[@class='govuk-body full-width']")
    public WebElementFacade dataInputAccordionCorrespondenceReceived;

    // Basic Methods

    public void selectAddAnMPField() {
        addMemberOfParliamentSearchField.click();
        typeInto(addMemberOfParliamentSearchField, "example");
    }

    public void selectAddACorrespondentLink() {
        addCorrespondentLink.waitUntilClickable();
        try {
            clickOn(addCorrespondentLink);
        } catch (Exception e) {
            waitABit(2000);
            clickOn(addCorrespondentLink);
        }
    }

    public void selectCorrespondentIsAMemberRadioButton() {
        addCorrespondentLink.waitUntilClickable();
        try {
            clickOn(correspondentMemberYesRadioButton);
        } catch (Exception e) {
            waitABit(2000);
            clickOn(correspondentMemberYesRadioButton);
        }
    }

    public void selectCorrespondentIsNotAMemberRadioButton() {
        try {
            clickOn(correspondentMemberNoRadioButton);
        } catch (Exception e) {
            waitABit(2000);
            clickOn(correspondentMemberNoRadioButton);
        }
    }

    // Multi Step Methods

    public void dataInputFullFlow() {
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        clickOn(workstacks.allocateToMeButton);
        fillAllMandatoryCorrespondenceFields();
        clickOn(continueButton);
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickOn(finishButton);
    }

    public void dataInputFullFlowMIN() {
        homepage.getCurrentCase();
        workstacks.clickAllocateToMeButton();
        fillAllMandatoryCorrespondenceFields();
        clickOn(continueButton);
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickOn(finishButton);
    }

    public void dataInputFullFlowTRO() {
        homepage.getCurrentCase();
        workstacks.clickAllocateToMeButton();
        fillAllMandatoryCorrespondenceFields();
        clickOn(continueButton);
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickOn(finishButton);
    }

    public void dataInputFullFlowWithCopyToN10() {
        homepage.getCurrentCase();
        workstacks.clickAllocateToMeButton();
        fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes();
        clickOn(continueButton);
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickOn(finishButton);
    }

    public void dataInputFullFlowDTEN() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        fillAllMandatoryCorrespondenceFields();
        clickOn(continueButton);
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickOn(finishButton);
    }

    public void moveCaseFromDataInputToMarkup() {
        fillAllMandatoryCorrespondenceFields();
        clickOn(continueButton);
        recordCorrespondentDetails.addAMemberOfPublicCorrespondent();
        clickOn(finishButton);
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
        typeInto(referenceTextField, " ");
    }

    public void fillAllMandatoryCorrespondenceFields() {
        if (isElementDisplayed(dtenDraftingDeadlineDayField)) {
            typeInto(dtenDraftingDeadlineDayField, "01");
            typeInto(dtenDraftingDeadlineMonthField, "01");
            typeInto(dtenDraftingDeadlineYearField, "2019");
            typeInto(dtenDispatchDeadlineDayField, "01");
            typeInto(dtenDispatchDeadlineMonthField, "01");
            typeInto(dtenDispatchDeadlineYearField, "2019");
            clickOn(continueButton);
            enterDayOfCorrespondenceSent(todayPlusNDaysGetDay(-2));
            enterMonthOfCorrespondenceSent(todayPlusNDaysGetMonth(-2));
            enterYearOfCorrespondenceSent(todayPlusNDaysGetYear(-2));
            enterDayOfCorrespondenceReceived(getCurrentDay());
            enterMonthOfCorrespondenceReceived(getCurrentMonth());
            enterYearOfCorrespondenceReceived(getCurrentYear());
            clickOn(emailOriginalChannelRadioButton);
        } else {
            enterDayOfCorrespondenceSent(todayPlusNDaysGetDay(-2));
            enterMonthOfCorrespondenceSent(todayPlusNDaysGetMonth(-2));
            enterYearOfCorrespondenceSent(todayPlusNDaysGetYear(-2));
            enterDayOfCorrespondenceReceived(getCurrentDay());
            enterMonthOfCorrespondenceReceived(getCurrentMonth());
            enterYearOfCorrespondenceReceived(getCurrentYear());
            clickOn(emailOriginalChannelRadioButton);
            clickOn(shouldResponseBeCopiedN10NoRadioButton);
        }
    }

    public void fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes() {
        enterDayOfCorrespondenceSent(todayPlusNDaysGetDay(-2));
        enterMonthOfCorrespondenceSent(todayPlusNDaysGetMonth(-2));
        enterYearOfCorrespondenceSent(todayPlusNDaysGetYear(-2));
        enterDayOfCorrespondenceReceived(getCurrentDay());
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
        clickOn(emailOriginalChannelRadioButton);
        clickOn(shouldResponseBeCopiedN10YesRadioButton);
    }

    public void getToAddMemberOfParliamentPrerequisites() {
        if (isElementDisplayed(dtenDraftingDeadlineDayField)) {
            typeInto(dtenDraftingDeadlineDayField, "01");
            typeInto(dtenDraftingDeadlineMonthField, "01");
            typeInto(dtenDraftingDeadlineYearField, "2019");
            typeInto(dtenDispatchDeadlineDayField, "01");
            typeInto(dtenDispatchDeadlineMonthField, "01");
            typeInto(dtenDispatchDeadlineYearField, "2019");
            clickOn(continueButton);
            enterDayOfCorrespondenceSent("01");
            enterMonthOfCorrespondenceSent("01");
            enterYearOfCorrespondenceSent("2019");
            clickOn(emailOriginalChannelRadioButton);
            clickOn(continueButton);
            waitABit(500);
            clickOn(addCorrespondentLink);
            clickOn(correspondentMemberYesRadioButton);
            clickOn(continueButton);
        } else {
            enterDayOfCorrespondenceSent("01");
            enterMonthOfCorrespondenceSent("01");
            enterYearOfCorrespondenceSent("2019");
            clickOn(emailOriginalChannelRadioButton);
            clickOn(shouldResponseBeCopiedN10NoRadioButton);
            clickOn(continueButton);
            waitABit(500);
            clickOn(addCorrespondentLink);
            clickOn(correspondentMemberYesRadioButton);
            clickOn(continueButton);
        }
    }

    public void getToIsCorrespondentAnMPPrerequisites() {
        if (isElementDisplayed(dtenDraftingDeadlineDayField)) {
            typeInto(dtenDraftingDeadlineDayField, "01");
            typeInto(dtenDraftingDeadlineMonthField, "01");
            typeInto(dtenDraftingDeadlineYearField, "2019");
            typeInto(dtenDispatchDeadlineDayField, "01");
            typeInto(dtenDispatchDeadlineMonthField, "01");
            typeInto(dtenDispatchDeadlineYearField, "2019");
            clickOn(continueButton);
            enterDayOfCorrespondenceSent("01");
            enterMonthOfCorrespondenceSent("01");
            enterYearOfCorrespondenceSent("2019");
            clickOn(emailOriginalChannelRadioButton);
            clickOn(continueButton);
            waitABit(500);
            clickOn(addCorrespondentLink);
        } else {
            enterDayOfCorrespondenceSent("01");
            enterMonthOfCorrespondenceSent("01");
            enterYearOfCorrespondenceSent("2019");
            clickOn(emailOriginalChannelRadioButton);
            clickOn(shouldResponseBeCopiedN10NoRadioButton);
            clickOn(continueButton);
            waitABit(500);
            clickOn(addCorrespondentLink);
        }
    }

    public void getToRecordCorrespondentDetailsPrerequisites() {
        if (isElementDisplayed(dtenDraftingDeadlineDayField)) {
            typeInto(dtenDraftingDeadlineDayField, "01");
            typeInto(dtenDraftingDeadlineMonthField, "01");
            typeInto(dtenDraftingDeadlineYearField, "2019");
            typeInto(dtenDispatchDeadlineDayField, "01");
            typeInto(dtenDispatchDeadlineMonthField, "01");
            typeInto(dtenDispatchDeadlineYearField, "2019");
            clickOn(continueButton);
            enterDayOfCorrespondenceSent("01");
            enterMonthOfCorrespondenceSent("01");
            enterYearOfCorrespondenceSent("2019");
            emailOriginalChannelRadioButton.click();
            clickOn(continueButton);
            waitABit(500);
            clickOn(addCorrespondentLink);
            clickOn(correspondentMemberNoRadioButton);
            clickOn(continueButton);
        } else {
            enterDayOfCorrespondenceSent("01");
            enterMonthOfCorrespondenceSent("01");
            enterYearOfCorrespondenceSent("2019");
            clickOn(emailOriginalChannelRadioButton);
            clickOn(shouldResponseBeCopiedN10NoRadioButton);
            clickOn(continueButton);
            waitABit(500);
            clickOn(addCorrespondentLink);
            clickOn(correspondentMemberNoRadioButton);
            clickOn(continueButton);
        }
    }

    public void invalidCorrespondenceReceivedDate() {
        enterDayOfCorrespondenceReceived(tomorrowsDay());
        enterMonthOfCorrespondenceReceived(tomorrowsMonth());
        enterYearOfCorrespondenceReceived(tomorrowsYear());
    }

    public void invalidCorrespondenceSentDate() {
        enterDayOfCorrespondenceSent(tomorrowsDay());
        enterMonthOfCorrespondenceSent(tomorrowsMonth());
        enterYearOfCorrespondenceSent(tomorrowsYear());
    }

    public void addAnAdditionalCorrespondent() {
        recordCorrespondentDetails.clickAdditionalCorrespondentYes();
        clickOn(continueButton);
        clickOn(correspondentMemberNoRadioButton);
        clickOn(continueButton);
        recordCorrespondentDetails.fillMandatoryCorrespondentFields();
        clickOn(continueButton);
    }

    public void completeTheFirstDataInputFields() {
        enterDayOfCorrespondenceSent("01");
        enterMonthOfCorrespondenceSent("01");
        enterYearOfCorrespondenceSent("2019");
        clickOn(emailOriginalChannelRadioButton);
        clickOn(continueButton);
        clickOn(continueButton);
        clickOn(finishButton);
    }

    public void completeDataInputStageAndStoreEnteredInformation() {
        enterDayOfCorrespondenceSent(getCurrentDay());
        String currentDay = dateCorrespondenceSentDayField.getValue();
        setSessionVariable("currentDay").to(currentDay);

        enterMonthOfCorrespondenceSent(getCurrentMonth());
        String currentMonth = dateCorrespondenceSentMonthField.getValue();
        setSessionVariable("currentMonth").to(currentMonth);

        enterYearOfCorrespondenceSent(getCurrentYear());
        String currentYear = dateCorrespondenceSentYearField.getValue();
        setSessionVariable("currentYear").to(currentYear);

        clickOn(emailOriginalChannelRadioButton);
        String selectedCorrespondenceReceivedRadioButton = emailOriginalChannelRadioButton.getText().toUpperCase();
        setSessionVariable("selectedCorrespondenceReceivedRadioButton").to(selectedCorrespondenceReceivedRadioButton);

        clickOn(shouldResponseBeCopiedN10NoRadioButton);
        String selectedCopiedN10NoRadioButton = shouldResponseBeCopiedN10NoRadioButton.getAttribute("for").substring(14);
        setSessionVariable("selectedCopiedN10NoRadioButton").to(selectedCopiedN10NoRadioButton);

        clickOn(continueButton);
        waitABit(500);
        clickOn(addCorrespondentLink);
        clickOn(correspondentMemberYesRadioButton);
        clickOn(continueButton);

        typeInto(addMemberOfParliamentSearchField, "Nicola Sturgeon MSP");
        addMemberOfParliamentSearchField.sendKeys(Keys.ENTER);
        String memberOfParliament = memberOfParliamentName.getText();
        setSessionVariable("memberOfParliamentName").to(memberOfParliament);
        waitABit(500);
        clickOn(addButton);
        waitABit(500);
        clickOn(addButton);
        clickOn(finishButton);
    }

    // Assertions

    public void assertPageTitle() {
        assertTitle("Data Input");
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

    public void assertAccordionCorrespondenceReceivedDate() {
        String dataInputCorrespondenceReceivedDate =
                sessionVariableCalled("correspondenceReceivedDay") + "/" + sessionVariableCalled(
                        "correspondenceReceivedMonth") + "/" + sessionVariableCalled("correspondenceReceivedYear");
        assertThat(dataInputAccordionCorrespondenceReceived.getText(), containsText(dataInputCorrespondenceReceivedDate));
    }
}
