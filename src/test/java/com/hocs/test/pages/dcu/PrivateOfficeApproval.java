package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.TimelineTab;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOfficeApproval extends BasePage {

    Markup_AddTopics markup_addTopics;

    TimelineTab timelineTab;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='Yes']")
    public WebElementFacade privateOfficeAcceptRadioButton;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='No']")
    public WebElementFacade privateOfficeRejectRadioButton;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='Change Minister']")
    public WebElementFacade privateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    public WebElementFacade privateOfficeRejectNoteField;

    @FindBy(id = "CaseNote_PrivateOfficeOverride")
    public WebElementFacade privateOfficeOverrideNoteField;

    @FindBy(id = "PrivateOfficeOverridePOTeamUUID")
    public WebElementFacade privateOfficeTeamDropdown;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='Change Topic']")
    public WebElementFacade changeTopicRadioButton;

    @FindBy(id = "PrivateOfficeOverridePOTeamUUID")
    public WebElementFacade privateOfficeOverrideDropdown;

    @FindBy(id = "CaseNote_PrivateOfficeOverride")
    public WebElementFacade privateOfficeOverrideReasonTextField;

    @FindBy(id = "CaseNote_PrivateOfficeTopic")
    public WebElementFacade topicOverrideReasonTextField;

    public void enterPORejectNotes() {
        waitFor(privateOfficeRejectNoteField);
        String poRejectNote = "Rejection Reason: " + generateRandomString();
        privateOfficeRejectNoteField.clear();
        privateOfficeRejectNoteField.sendKeys(poRejectNote);
        setSessionVariable("PORejectNote").to(poRejectNote);
    }

    public void getToChangeMinisterScreenPrerequisites() {
        safeClickOn(privateOfficeChangeMinisterRadioButton);
        safeClickOn(continueButton);
    }

    public void getToPOFeedbackResponseScreenPrerequisites() {
        safeClickOn(privateOfficeRejectRadioButton);
        safeClickOn(continueButton);
    }

    public void moveCaseFromPrivateOfficeToMinisterSignOff() {
        safeClickOn(privateOfficeAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void moveDTENCaseFromPrivateOfficeToDispatch() {
        safeClickOn(privateOfficeAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void completePrivateOfficeApprovalStageAndStoreEnteredInformation() {
        safeClickOn(privateOfficeAcceptRadioButton);
        setSessionVariable("privateOfficeAcceptanceDecision").to(privateOfficeAcceptRadioButton.getTextContent());
        safeClickOn(continueButton);
    }

    public void selectNewPrivateOfficeTeamFromDropdown(String newPOTeam) {
        privateOfficeTeamDropdown.selectByVisibleText(newPOTeam);
        setSessionVariable("chosenPOTeam").to(newPOTeam);
    }

    public void enterAReasonForChangingPOTeam(String reason) {
        privateOfficeOverrideNoteField.sendKeys(reason);
        setSessionVariable("reasonForOverridePOTeam").to(reason);
    }

    public void changeTopicAtPOStage(String topic) {
        safeClickOn(changeTopicRadioButton);
        safeClickOn(continueButton);
        safeClickOn(markup_addTopics.addTopicLink);
        markup_addTopics.topicsTextField.click();
        markup_addTopics.topicsTextField.sendKeys(topic);
        waitABit(1000);
        markup_addTopics.hitReturnToSendTopic();
        waitABit(1000);
        safeClickOn(addButton);
        WebElementFacade selectedPrimaryTopic = findBy("//input[@checked]/following-sibling::label");
        if (!selectedPrimaryTopic.getText().toUpperCase().equals(topic.toUpperCase())) {
            WebElementFacade newTopicRadioButton = findBy("//label[text()='" + topic + "']/parent::div/input");
            waitFor(newTopicRadioButton);
            safeClickOn(newTopicRadioButton);
        }
        topicOverrideReasonTextField.sendKeys( "Test");
        setSessionVariable("topicOverrideReason").to("Test");
        safeClickOn(continueButton);
    }

    public void assertTopicChangeCaseNoteIsAddedToTimeline() {
        timelineTab.topicChangeCaseNoteContents.shouldContainText(sessionVariableCalled("topicOverrideReason"));
    }
}
