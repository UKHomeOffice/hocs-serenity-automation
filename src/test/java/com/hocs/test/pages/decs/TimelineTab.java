package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;

public class TimelineTab extends BasePage {

    @FindBy(xpath = "//a[contains(@class,'tab')][text()='Timeline']")
    public WebElementFacade timelineTab;

    @FindBy(xpath = "//span[@class='govuk-details__summary-text']")
    public WebElementFacade addCaseNoteButton;

    @FindBy(xpath = "//textarea[@id='case-note']")
    public WebElementFacade caseNoteTextbox;

    @FindBy(xpath = "//span[@id='case-note-error']")
    public WebElementFacade caseNoteMustNotBeBlankErrorMessage;

    @FindBy(xpath = "//div[@class='timeline']//li[1]")
    public WebElementFacade topCaseNoteOrLog;

    @FindBy(xpath = "//div[@class='timeline']//li[1]/p[1]")
    public WebElementFacade topNoteContents;

    @FindBy(xpath = "//div[@class='timeline']//li[1]/p[2]")
    public WebElementFacade topNoteSignature;

    @FindBy(xpath = "//div[@class='timeline']//li[2]/p[1]")
    public WebElementFacade secondNoteContents;

    @FindBy(xpath = "//a[text()='Edit'][1]")
    public WebElementFacade editButton;

    @FindBy(css = "input[value='Save']")
    public WebElementFacade saveButton;

    @FindBy(xpath = "//li//span[text()='Rejection note']/ancestor::p")
    public WebElementFacade rejectionNoteContents;

    @FindBy(xpath = "//li//span[text()='Escalation note']/ancestor::p")
    public WebElementFacade escalationNoteContents;

    @FindBy(xpath = "//li//span[text()='Case closure note']/ancestor::p")
    public WebElementFacade closureNoteContents;

    @FindBy(xpath = "//li//span[text()='Contribution request note']/ancestor::p")
    public WebElementFacade contributionRequestNoteContents;

    @FindBy(xpath = "//li//span[text()='Details of follow up']/ancestor::p")
    public WebElementFacade detailsOfFollowUpNoteContents;

    @FindBy(xpath = "//li//span[text()='Follow up not completed']/ancestor::p")
    public WebElementFacade followUpNotCompletedReasonNoteContents;

    @FindBy(xpath = "//li//span[contains(text(),'Conversion note')]/ancestor::p")
    public WebElementFacade conversionNoteContents;

    @FindBy(xpath = "//li//span[text()='Case transfer reason']/parent::p")
    public WebElementFacade caseTransferReasonNoteContents;

    @FindBy(xpath = "//span[text()='Change note']/parent::p")
    public WebElementFacade topicChangeCaseNoteContents;

    @FindBy(xpath = "//li//span[text()='Case withdrawn']/ancestor::p")
    public WebElementFacade caseWithdrawnNoteContents;

    @FindBy(xpath = "//li/p/strong[text()='Case Closed']")
    public WebElementFacade caseClosedNote;

    @FindBy(xpath ="//a[@class='tab'][not(@class='tab__active')]")
    public WebElementFacade nonActiveTab;

    public void selectTimelineTab() {
        if(!timelineTabIsActiveTab()) {
            safeClickOn(timelineTab);
        }
    }

    public boolean timelineTabIsActiveTab() {
        return timelineTab.getAttribute("class").contains("active");
    }

    public void clickAddCaseNote() {
        safeClickOn(addCaseNoteButton);
    }

    public void enterTextIntoCaseNote(String text) {
        safeClickOn(caseNoteTextbox);
        caseNoteTextbox.sendKeys(text);
    }

    public void editACaseNote(String input) {
        safeClickOn(editButton);
        WebElementFacade editTextBox = findBy("//div[@class='timeline']//textarea");
        editTextBox.clear();
        editTextBox.sendKeys(input);
        safeClickOn(saveButton);
        setSessionVariable("createdNoteContents").to(input);
    }

    public void createACaseNote() {
        clickAddCaseNote();
        String noteContents = generateRandomString();
        setSessionVariable("createdNoteContents").to(noteContents);
        enterTextIntoCaseNote(noteContents);
        clickAddButton();
        waitABit(1000);
    }

    public void assertTopNoteContainsEnteredText(String text) {
        topNoteContents.shouldContainText(text);
    }

    public void assertCaseNoteAtTopOfTimeline() {
        assertThat(topCaseNoteOrLog.getAttribute("class").equals("case-note"), is(true));
    }

    public void assertLogAtTopOfTimeline() {
        assertThat(topCaseNoteOrLog.getClass().equals("case-note"), is(false));
    }

    public void assertTopNoteSignatureContainsCreator(User user) {
        topNoteSignature.shouldContainText(user.getUsername());
    }

    public void assertCaseNoteMustNotBeBlankErrorMessage() {
        caseNoteMustNotBeBlankErrorMessage.shouldContainText("Case note must not be blank");
    }

    public void assertAllocationLogVisible(User user, String stage) {
        WebElementFacade logAllocatedUser = findBy(".timeline > ul > li:nth-child(1) > p:nth-child(1)");
        WebElementFacade logCaseStage = findBy(".timeline > ul > li:nth-child(1) > p:nth-child(2)");
        logAllocatedUser.shouldContainText("Allocated to " + user.getUsername());
        logCaseStage.shouldContainText("Stage: " + stage);
    }

    public void assertStageCompletionLogVisible(String stage) {
        WebElementFacade stageCompletionLog = findBy("//div[@class='timeline']//li/p[1]/strong[text()="
                + "'Stage: " + stage + " Completed']");
        try {
            assertThat(stageCompletionLog.isVisible(), is(true));
        } catch (AssertionError e) {
            safeClickOn(nonActiveTab);
            selectTimelineTab();
            assertThat(stageCompletionLog.isVisible(), is(true));
        }
    }

    public void assertStageStartedLogVisible(String stage) {
        WebElementFacade stageStartedLog = findBy("//div[@class='timeline']//li/p[1]/strong[text()="
                + "'Stage: " + stage + " Started']");
        try {
            assertThat(stageStartedLog.isVisible(), is(true));
        } catch (AssertionError e) {
            safeClickOn(nonActiveTab);
            selectTimelineTab();
            assertThat(stageStartedLog.isVisible(), is(true));
        }
    }

    public void createAnotherCaseNote() {
        String noteContents = generateRandomString();
        setSessionVariable("secondNoteContents").to(noteContents);
        enterTextIntoCaseNote(noteContents);
        clickAddButton();
        waitABit(1000);
    }

    public void assertSecondNoteContainsEnteredText(String text) {
        secondNoteContents.shouldContainText(text);
    }

    public void assertEditedCaseNoteAppearInCorrectStage(String stage) {
        String[] words = stage.split("\\s");
        String capitalise = "";
        for (String w:words) {
            String first = w.substring(0,1);
            String afterFirst = w.substring(1);
            capitalise += first.toUpperCase() + afterFirst.toLowerCase() + " ";
        }
        String formatStage = capitalise.trim();
        WebElementFacade caseNote = findBy("//li/p[text()='" + formatStage + "']/parent::li/preceding-sibling::li[1]/p[1]");
        String fullCaseNote = caseNote.getText();
        assertThat(fullCaseNote.contains("Case note "), is(true));
    }

    public void assertRejectionNoteVisible() {
        selectTimelineTab();
        String rejectionReason = sessionVariableCalled("rejectionReason");
        assertThat(rejectionNoteContents.getText().contains(rejectionReason), is(true));
    }

    public void assertEscalationNoteVisible() {
        selectTimelineTab();
        String escalationReason = sessionVariableCalled("escalationReason");
        assertThat(escalationNoteContents.getText().contains(escalationReason), is(true));
    }

    public void assertClosureNoteVisible() {
        selectTimelineTab();
        String closureReason = sessionVariableCalled("closureReason");
        assertThat(closureNoteContents.getText().contains(closureReason), is(true));
    }

    public void assertContributionRequestNoteVisible() {
        selectTimelineTab();
        String requestDescription = sessionVariableCalled("requestDescription");
        assertThat(contributionRequestNoteContents.getText().contains(requestDescription), is(true));
    }

    public void assertDetailsOfFollowUpNoteVisible() {
        selectTimelineTab();
        String followUpDetails = sessionVariableCalled("followUpDetails");
        assertThat(detailsOfFollowUpNoteContents.getText().contains(followUpDetails), is(true));
    }

    public void assertFollowUpNotCompletedNoteVisible() {
        selectTimelineTab();
        String followUpNotCompletedReason = sessionVariableCalled("followUpNotCompletedReason");
        assertThat(followUpNotCompletedReasonNoteContents.getText().contains(followUpNotCompletedReason), is(true));
    }

    public void assertConversionNoteVisible() {
        selectTimelineTab();
        String conversionNotes = sessionVariableCalled("conversionNotes");
        assertThat(conversionNoteContents.getText().contains(conversionNotes), is(true));
    }

    public void assertCaseTransferReason() {
        String inputTransferReason = sessionVariableCalled("inputReasonForTransfer");
        assertThat(caseTransferReasonNoteContents.getText().contains(inputTransferReason), is(true));
    }

    public void assertCaseClosedNoteVisible() {
        selectTimelineTab();
        caseClosedNote.shouldBeVisible();
    }

    public void assertCaseWithDrawnNoteVisible() {
        selectTimelineTab();
        String withdrawalNotes = sessionVariableCalled("withdrawalNotes");
        assertThat(caseWithdrawnNoteContents.getText().contains(withdrawalNotes), is(true));
    }

    public void assertTopicAddedLogVisible() {
        String testTopic = sessionVariableCalled("topic").toString();
        String renameTopic = topCaseNoteOrLog.getText().substring(7, 27);
        assertThat(renameTopic.equals(testTopic), Is.is(true));
    }

    public void assertTopicChangeCaseNoteIsAddedToTimeline() {
        topicChangeCaseNoteContents.shouldContainText(sessionVariableCalled("topicOverrideReason"));
    }
}
