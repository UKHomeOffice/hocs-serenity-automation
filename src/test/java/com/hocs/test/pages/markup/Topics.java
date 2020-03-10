package com.hocs.test.pages.markup;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Topics extends Page {

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    MarkUpDecision markUpDecision;

    Workstacks workstacks;

    @FindBy(xpath = "//li[1]/p[2]")
    public WebElementFacade topicInTimeline;

    @FindBy(xpath = "//th[contains(text(),'Primary topic')]/following-sibling::td")
    public WebElementFacade primaryTopicInSummary;

    @FindBy(css = "[id^=react-select")
    private WebElementFacade topicsTextField;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicButton;

    @FindBy(xpath = "//label")
    public WebElementFacade assignedTopic;

    @FindBy(xpath = "//div[@class='css-xp4uvy govuk-typeahead__single-value']")
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
        clickOn(addTopicButton);
    }

    public void enterTopic() {
        typeInto(topicsTextField, generateRandomString());
    }

    public void clickTopicsTextField() {
        clickOn(topicsTextField);
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
        clickOn(addTopicButton);
        clickOn(topicsTextField);
        typeInto(topicsTextField, topic);
        waitABit(1000);
        hitReturnToSendTopic();
        clickOn(addButton);
        clickOn(continueButton);
        clickOn(finishButton);
    }

    public void enterATopicWithoutContinuingToTheDraftStage(String topic) {
        clickOn(addTopicButton);
        clickOn(topicsTextField);
        typeInto(topicsTextField, topic);
        waitABit(1000);
        hitReturnToSendTopic();
        if (!isElementDisplayed(markUpDecision.topicIsRequiredErrorMessage)) {
            clickOn(addButton);
        }
    }

    public void enterRealTopic() {
        clickOn(topicsTextField);
        typeInto(topicsTextField, "Cardiff University Kittens");
        hitReturnToSendTopic();
        String topicName = selectedTopicName.getText();
        setSessionVariable("selectedTopicName").to(topicName);
        waitABit(1000);
    }

    public void fromMarkupStartSelectATopic (String topic) {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        clickContinueButton();
        enterATopic(topic);
    }

    public void fromMarkupStartSelectATopicAndStayOnPrimaryTopicsPage (String topic) {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        clickContinueButton();
        enterATopicWithoutContinuingToTheDraftStage(topic);
    }

    public void recordSelectedDraftingAndPrivateOfficeTeams() {
        selectedDraftingTeamName.withTimeoutOf(10, TimeUnit.SECONDS).waitUntilVisible();
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

}
