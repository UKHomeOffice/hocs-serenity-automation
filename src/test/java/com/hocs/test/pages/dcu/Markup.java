package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Markup extends BasePage {

    Markup_AddTopics markupAddTopics;

    @FindBy(css = "label[for='MarkupDecision-PR']")
    public WebElementFacade policyResponseRadioButton;

    @FindBy(css = "label[for='MarkupDecision-OGD']")
    public WebElementFacade referToOgdRadioButton;

    @FindBy(css = "label[for='MarkupDecision-FAQ']")
    public WebElementFacade faqRadioButton;

    @FindBy(css = "label[for='MarkupDecision-NRN']")
    public WebElementFacade noReplyNeededRadioButton;

    @FindBy(css = "label[for='MarkupDecision-REJ']")
    public WebElementFacade rejectToDataInputRadioButton;

    @FindBy(id = "OGDDept")
    public WebElementFacade OGDDestinationTextBox;

    @FindBy(id = "CaseNote_OGD")
    public WebElementFacade OGDReasonTextBox;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addATopicButton;

    @FindBy(xpath = "//a[text()='What sort of response is required? is required']")
    public WebElementFacade whatSortOfResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary topic? is required']")
    public WebElementFacade whichIsThePrimaryTopicErrorMessage;

    @FindBy(xpath = "//a[text()='Topic is required']")
    public WebElementFacade topicIsRequiredErrorMessage;

    @FindBy(id = "CaseNote_NRN")
    public WebElementFacade noResponseNeededTextField;

    @FindBy(id = "CaseNote_REJ")
    public WebElementFacade rejectToDataInputTextField;

    @FindBy(id = "POTeamName")
    public WebElementFacade privateOfficeTeamTextField;

    //Basic Methods

    public void selectFAQRadioButton() {
        safeClickOn(faqRadioButton);
    }

    public void selectNoReplyNeededRadioButton() {
        safeClickOn(noReplyNeededRadioButton);
    }

    public void selectPolicyResponseRadioButton() {
        safeClickOn(policyResponseRadioButton);
    }

    public void selectReferToOGDRadioButton() {
        safeClickOn(referToOgdRadioButton);
    }

    public void selectRejectToDataInput() {
        safeClickOn(rejectToDataInputRadioButton);
    }

    public void clickPolicyResponseRadioButton() {
        safeClickOn(policyResponseRadioButton);
    }

    //Multi Step Methods

    public void getToMarkupAddATopicScreenPrerequisites() {
        safeClickOn(policyResponseRadioButton);
        safeClickOn(continueButton);
        waitABit(500);
    }

    public void getToMarkupEnterANewTopicScreenPrerequisites() {
        safeClickOn(policyResponseRadioButton);
        safeClickOn(continueButton);
        safeClickOn(addATopicButton);
    }

    public void chooseResponseTypeAndRecordDecision() {
        safeClickOn(policyResponseRadioButton);
        String whatSortOfResponseRadioButton = policyResponseRadioButton.getTextContent();
        setSessionVariable("selectedWhatSortOfResponseRadioButton").to(whatSortOfResponseRadioButton);
    }

    //Assertions

    public void assertSortOfResponseErrorMessage() {
        whatSortOfResponseErrorMessage.shouldContainText("What sort of response is required? is required");
    }

    public void assertAddATopicErrorMessage() {
        whichIsThePrimaryTopicErrorMessage.shouldContainText("Which is the primary topic? is required");
    }

    public void assertTopicIsRequiredErrorMessage() {
        topicIsRequiredErrorMessage.shouldContainText("Topic is required");
    }

    public void assertNRNTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(noResponseNeededTextField), is(true));
    }

    public void assertRejectTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(rejectToDataInputTextField), is(true));
    }

    public void assertOGDDestinationTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(OGDDestinationTextBox), is(true));
    }

    public void enterRejectToDataInputReasonIntoTextBox() {
        waitFor(rejectToDataInputTextField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        typeInto(rejectToDataInputTextField, rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void moveCaseFromMarkupToInitialDraft() {
        safeClickOn(policyResponseRadioButton);
        safeClickOn(continueButton);
        markupAddTopics.clickAddTopicLink();
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToNRNConfirmation() {
        safeClickOn(noReplyNeededRadioButton);
        safeClickOn(continueButton);
        noResponseNeededTextField.sendKeys("Test reason for NRN");
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToTransferConfirmation() {
        safeClickOn(referToOgdRadioButton);
        safeClickOn(continueButton);
        enterOGDDestinationAndReason();
        safeClickOn(finishButton);
    }

    public void completeMarkupStageAndStoreEnteredInformation() {
        chooseResponseTypeAndRecordDecision();
        safeClickOn(continueButton);
        markupAddTopics.clickAddTopicLink();
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        markupAddTopics.recordSelectedDraftingAndPrivateOfficeTeams();
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToInitialDraftWithSpecificTopic(String topic) {
        safeClickOn(policyResponseRadioButton);
        safeClickOn(continueButton);
        markupAddTopics.enterATopic(topic);
        safeClickOn(finishButton);
    }

    public void enterOGDDestinationAndReason() {
        OGDDestinationTextBox.sendKeys("Test other Dept.");
        OGDReasonTextBox.sendKeys("Test reason for transfer");
    }

    public void enterNRNreason() {
        noResponseNeededTextField.sendKeys("Test reason for NRN");
    }
}
