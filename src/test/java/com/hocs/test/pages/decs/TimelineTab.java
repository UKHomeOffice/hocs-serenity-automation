package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;
import org.junit.Assert;

public class TimelineTab extends BasePage {

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

    @FindBy(xpath = "//div[@class='timeline']//li[3]/p[1]")
    public WebElementFacade fourthNoteContents;
    @FindBy(xpath = "//div[@class='timeline']//li[2]/p[1]")
    public WebElementFacade thirdNoteContents;

    @FindBy(xpath = "//a[text()='Edit'][1]")
    public WebElementFacade editButton;

    @FindBy(css = "input[value='Save']")
    public WebElementFacade saveButton;

    @FindBy(xpath = "//li/p/strong[text()='Case Closed']")
    public WebElementFacade caseClosedNote;

    public void selectTimelineTab() {
        selectTheTab("Timeline");
    }

    public void refreshTimelineTab() {
        refreshTheTab("Timeline");
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

    public void assertAllocationToUserLogVisible(User user, String stage) {
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
            refreshTimelineTab();
            assertThat(stageCompletionLog.isVisible(), is(true));
        }
    }

    public void assertStageStartedLogVisible(String stage) {
        WebElementFacade stageStartedLog = findBy("//div[@class='timeline']//li/p[1]/strong[text()="
                + "'Stage: " + stage + " Started']");
        try {
            assertThat(stageStartedLog.isVisible(), is(true));
        } catch (AssertionError e) {
            refreshTimelineTab();
            assertThat(stageStartedLog.isVisible(), is(true));
        }
    }

    public void assertTopicAddedLogVisible() {
        String testTopic = sessionVariableCalled("topic").toString();
        String renameTopic = topCaseNoteOrLog.getText().substring(7, 27);
        assertThat(renameTopic.equals(testTopic), Is.is(true));
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
    public void assertFourthNoteContainsEnteredText(String text) {
        fourthNoteContents.shouldContainText(text);
    }

    public void assertEditedCaseNoteAppearInCorrectStage(String stage) {
        String[] words = stage.split("\\s");
        String capitalise = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterFirst = w.substring(1);
            capitalise += first.toUpperCase() + afterFirst.toLowerCase() + " ";
        }
        String formatStage = capitalise.trim();
        WebElementFacade caseNote = findBy("//li/p[text()='" + formatStage + "']/parent::li/preceding-sibling::li[1]/p[1]");
        String fullCaseNote = caseNote.getText();
        assertThat(fullCaseNote.contains("Case note "), is(true));
    }

    public void assertCaseNoteAppearsBetweenLogsForStage(String stage) {
        WebElementFacade stageLogFollowingCaseNote = findBy(
                "//p[text()='" + sessionVariableCalled("createdNoteContents") + "']//ancestor::li/preceding-sibling::li//*[contains(text(),'" + stage + "')]");
        WebElementFacade stageLogPrecedingCaseNote = findBy(
                "//p[text()='" + sessionVariableCalled("createdNoteContents") + "']//ancestor::li/following-sibling::li//*[contains(text(),'" + stage + "')]");
        if (!stageLogFollowingCaseNote.isCurrentlyVisible() || !stageLogPrecedingCaseNote.isCurrentlyVisible()) {
            Assert.fail(stage + " stage log are not visible either side of the case note int he timeline");
        }
    }

    public void assertCaseClosedNoteVisible() {
        selectTimelineTab();
        caseClosedNote.shouldBeVisible();
    }

    public void assertCaseNoteWithTitleIsVisible(String caseNoteTitle) {
        selectTimelineTab();
        WebElementFacade caseNote = findBy("//p/span[text()='" + caseNoteTitle + "']/ancestor::li");
        if (!caseNote.isVisible()) {
            Assert.fail("The case note with title '" + caseNoteTitle + "' is not visible in the timeline");
        }
    }

    public void assertCaseNoteWithTitleContainsText(String caseNoteTitle, String text) {
        selectTimelineTab();
        WebElementFacade caseNote = findBy("//p/span[text()='" + caseNoteTitle + "']/ancestor::li");
        String caseNoteContents = caseNote.getText().replace("\n", " ");
        if (!caseNoteContents.contains(text)) {
            Assert.fail("The '" + caseNoteTitle + "' case note was expected to have text: '" + caseNoteContents + "' but had text: '" + text + "'");
        }
    }

    public void assertCaseLogWithTitleIsVisible(String caseLogTitle) {
        selectTimelineTab();
        WebElementFacade caseLog = findBy("//p/strong[text()='" + caseLogTitle + "']/ancestor::li");
        if (!caseLog.isVisible()) {
            Assert.fail("The case log with title '" + caseLogTitle + "' is not visible in the timeline");
        }
    }

    public void assertCaseLogWithTitleContainsText(String caseLogTitle, String text) {
        selectTimelineTab();
        WebElementFacade caseLog = findBy("//p/strong[text()='" + caseLogTitle + "']/ancestor::li");
        String caseLogContents = caseLog.getText().replace("\n", " ");
        if (!caseLogContents.contains(text)) {
            Assert.fail("The '" + caseLogTitle + "' case log was expected to have text: '" + caseLogContents + "' but had text: '" + text + "'");
        }
    }

    public void assertCaseNotesAuthoredByUserAreNotVisible(User user) {
        WebElementFacade caseNoteAuthoredByUser = findBy("//li[@class='case-note']//span[text()='" + user.getUsername() + "']");
        if (caseNoteAuthoredByUser.isCurrentlyVisible()) {
            Assert.fail("Case notes authored by " + user.getUsername() + " are visible in the timeline");
        }
    }

    public void assertCaseNotesAuthoredByUserAreVisible(User user) {
        WebElementFacade caseNoteAuthoredByUser = findBy("//li[@class='case-note']//span[text()='" + user.getUsername() + "']");
        if (!caseNoteAuthoredByUser.isCurrentlyVisible()) {
            Assert.fail("Case notes authored by " + user.getUsername() + " are not visible in the timeline");
        }
    }

    public void assertTimelineLogsAttributedToUserAreVisible(User user) {
        WebElementFacade logAttributedToUser = findBy("//div[@class='timeline']//li[not(@class='case-note')]//span[text()='" + user.getUsername() + "']");
        if (!logAttributedToUser.isCurrentlyVisible()) {
            Assert.fail("Logs attributed to " + user.getUsername() + " are not visible in the timeline");
        }
    }

    public void assertTimelineLogsAttributedToUserAreNotVisible(User user) {
        WebElementFacade logAttributedToUser = findBy("//div[@class='timeline']//li[not(@class='case-note')]//span[text()='" + user.getUsername() + "']");
        if (logAttributedToUser.isCurrentlyVisible()) {
            Assert.fail("Logs attributed to " + user.getUsername() + " are visible in the timeline");
        }
    }

    public void assertThirdNoteContainsEnteredText(String text) {
       thirdNoteContents.shouldContainText(text);
    }
}
