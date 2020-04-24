package com.hocs.test.pages.DCU_Workflow;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.TimelineTab;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.CreateCase_SuccessPage;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Markup_AddTopics extends BasePage {

    Homepage homepage;

    CreateCase_SuccessPage createCaseSuccessPage;

    Markup_Decision markupDecision;

    Workstacks workstacks;

    TimelineTab timelineTab;

    @FindBy(xpath = "//th[contains(text(),'Primary topic')]/following-sibling::td")
    public WebElementFacade primaryTopicInSummary;

    @FindBy(id = "react-select-2-input")
    private WebElementFacade topicsTextField;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicButton;

    @FindBy(xpath = "//label")
    public WebElementFacade assignedTopic;

    @FindBy(xpath = "//div[contains(@class, 'govuk-typeahead__single-value')]")
    public WebElementFacade selectedTopicName;

    @FindBy(id = "DraftingTeamName")
    public WebElementFacade autoAssignedDraftTeam;

    @FindBy(id = "POTeamName")
    public WebElementFacade autoAssignedPrivateOfficeTeam;

    @FindBy(id = "DraftingTeamName")
    public WebElementFacade selectedDraftingTeamName;

    @FindBy(id = "POTeamName")
    public WebElementFacade selectedPrivateOfficeTeamName;

    @FindBy(id = "OverrideDraftingTeamUUID")
    public WebElementFacade overrideInitialDraftTeamDropdown;

    @FindBy(id = "OverridePOTeamUUID")
    public WebElementFacade overridePrivateOfficeTeamDropdown;

    // Basic Methods

    public String getPrimaryTopicText() {
        String thisTopic = assignedTopic.getText();
        return thisTopic;
    }

    public void clickAddTopicButton() {
        safeClickOn(addTopicButton);
    }

    public void enterTopic() {
        typeInto(topicsTextField, generateRandomString());
    }

    public void clickTopicsTextField() {
        safeClickOn(topicsTextField);
    }

    public void hitReturnToSendTopic() {
        topicsTextField.sendKeys(Keys.RETURN);
    }

    public void selectOverridePrivateOfficeTeamByVisibleText(String newPrivateTeam) {
        overridePrivateOfficeTeamDropdown.selectByVisibleText(newPrivateTeam);
    }

    public void selectOverrideInitialDraftTeamByVisibleText(String newInitialDraftTeam) {
        overrideInitialDraftTeamDropdown.selectByVisibleText(newInitialDraftTeam);
    }

    // Multi Step Methods

    public void enterATopic(String topic) {
        safeClickOn(addTopicButton);
        topicsTextField.click();
        topicsTextField.sendKeys(topic);
        waitABit(1000);
        hitReturnToSendTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
    }

    public void enterATopicWithoutHittingFinish(String topic) {
        safeClickOn(addTopicButton);
        safeClickOn(topicsTextField);
        topicsTextField.sendKeys(topic);
        waitABit(1000);
        hitReturnToSendTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
    }

    public void enterATopicWithoutContinuingToTheDraftStage(String topic) {
        safeClickOn(addTopicButton);
        safeClickOn(topicsTextField);
        topicsTextField.sendKeys(topic);
        waitABit(1000);
        hitReturnToSendTopic();
        if (!isElementDisplayed(markupDecision.topicIsRequiredErrorMessage)) {
            safeClickOn(addButton);
        }
    }

    public void enterRealTopic() {
        safeClickOn(topicsTextField);
        topicsTextField.sendKeys("Cardiff University Kittens");
        hitReturnToSendTopic();
        String topicName = selectedTopicName.getText();
        setSessionVariable("selectedTopicName").to(topicName);
        waitABit(1000);
    }

    public void fromMarkupStartSelectATopic (String topic) {
        homepage.selectCentralDraftingTeam();
        createCaseSuccessPage.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        createCaseSuccessPage.selectCaseReferenceNumberViaXpath();
        markupDecision.clickPolicyResponseRadioButton();
        clickContinueButton();
        enterATopic(topic);
    }

    public void recordSelectedDraftingAndPrivateOfficeTeams() {
        selectedDraftingTeamName.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        String selectedInitialDraftingTeamName = selectedDraftingTeamName.getValue();
        setSessionVariable("selectedDraftingTeamName").to(selectedInitialDraftingTeamName);
        String selectedPOTeamName = selectedPrivateOfficeTeamName.getValue();
        setSessionVariable("selectedPrivateOfficeTeamName").to(selectedPOTeamName);
    }

    public void getCurrentDefaultTeamsForTopic() {
        waitABit(2000);
        setSessionVariable("defaultDraftTeam").to(autoAssignedDraftTeam.getValue());
        setSessionVariable("defaultPrivateOfficeTeam").to(autoAssignedPrivateOfficeTeam.getValue());
    }

    // Assertions

    public void assertTopicsTextFieldDisplayed() {
        isElementDisplayed(topicsTextField);
    }

    public void assertTopicsAssigned() {
        workstacks.selectSummaryTab();
        waitABit(2000);
        String testTopic = sessionVariableCalled("topic").toString();
        String thisTopic = primaryTopicInSummary.getText().toUpperCase();
        System.out.println(thisTopic);
        assertThat(thisTopic.equals(testTopic), is(true));
    }

    public void assertTopicIsAssignedThroughTimeline() {
        safeClickOn(workstacks.caseTimelineTab);
        waitABit(1000);
        String testTopic = sessionVariableCalled("topic").toString();
        String thisTopic = timelineTab.topCaseNoteOrLog.getText().substring(7, 27);
        assertThat(thisTopic.equals(testTopic), is(true));
    }
}
