package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TimelineTab extends BasePage {

    Workstacks workstacks;

    @FindBy(xpath = "//a[@class='tab'][text()='Timeline']")
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

    @FindBy(xpath = "//li//span[text()='Case closure note']/ancestor::p")
    public WebElementFacade closureNoteContents;

    @FindBy(xpath = "//li//span[text()='Contribution request note']/ancestor::p")
    public WebElementFacade contributionRequestNoteContents;

    @FindBy(xpath = "//li//span[text()='Details of follow up']/ancestor::p")
    public WebElementFacade detailsOfFollowUpNoteContents;

    @FindBy(xpath = "//li//span[text()='Follow up not completed']/ancestor::p")
    public WebElementFacade followUpNotCompletedReasonNoteContents;

    public void selectTimelineTab() {
        safeClickOn(timelineTab);
    }

    public void clickAddCaseNote() {
        safeClickOn(addCaseNoteButton);
    }

    public void enterTextIntoCaseNote(String text) {
        safeClickOn(caseNoteTextbox);
        typeInto(caseNoteTextbox, text);
    }

    public void editACase(String input) {
        safeClickOn(editButton);
        WebElementFacade editTextBox = findBy("//div[@class='timeline']//textarea");
        editTextBox.clear();
        typeInto(editTextBox, input);
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
            workstacks.selectSummaryTab();
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
            workstacks.selectSummaryTab();
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
        String words[] = stage.split("\\s");
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
}
