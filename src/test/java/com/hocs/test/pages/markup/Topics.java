package com.hocs.test.pages.markup;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Topics extends Page {

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    MarkUpDecision markUpDecision;

    Workstacks workstacks;

    @FindBy(xpath = "//li[1]/p[3]")
    public WebElementFacade topicInTimeline;

    // Explore @findAll during test so Topic is not hardcoded

    @FindBy(id = "react-select-2-input")
    private WebElementFacade topicsTextField;

    @FindBy(id = "react-select-2-input")
    private WebElementFacade focusedTopicsTextField;

    @FindBy(id = "DraftingTeamName")
    public WebElementFacade autoAssignedDraftTeam;

    @FindBy(id = "POTeamName")
    public WebElementFacade autoAssignedPrivateOfficeTeam;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicButton;

    @FindBy(id = "OverrideDraftingTeamUUID")
    public WebElementFacade overrideInitialDraftTeamDropdown;

    @FindBy(id = "OverridePOTeamUUID")
    public WebElementFacade overridePrivateOfficeTeamDropdown;

    @FindBy(xpath = "//label")
    public WebElementFacade assignedTopic;


    // Basic Methods

    public String getPrimaryTopicText() {
        String thisTopic = assignedTopic.getText();
        return thisTopic;
    }

    public void clickAddTopicButton() {
        addTopicButton.click();
    }

    public void enterTopic() {
        topicsTextField.sendKeys(generateRandomString());
    }

    public void clickTopicsTextField() {
        topicsTextField.click();
    }

    public void hitReturnToSendTopic() {
        topicsTextField.sendKeys(Keys.RETURN);
    }

    public void enterATopic(String topic) {
        clickOn(addTopicButton);
        clickOn(topicsTextField);
        focusedTopicsTextField.sendKeys(topic);
        sleep(1000);
        hitReturnToSendTopic();
        clickOn(addButton);
        clickOn(continueButton);
    }

    public void enterATopicWithoutContinuingToTheDraftStage(String topic) {
        clickOn(addTopicButton);
        clickOn(topicsTextField);
        focusedTopicsTextField.sendKeys(topic);
        sleep(1000);
        hitReturnToSendTopic();
        clickOn(addButton);
    }

    // Multi Step Methods

    public void enterRealTopic() {
        clickOn(topicsTextField);
        focusedTopicsTextField.sendKeys("Cardiff University Kittens");
        sleep(1000);
        topicsTextField.sendKeys(Keys.RETURN);
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


    // Assertions

    public void assertTopicsTextFieldDisplayed() {
        isElementDisplayed(topicsTextField);
    }

    public void selectOverridePrivateOfficeTeamByVisibleText(String newPrivateTeam) {
        overridePrivateOfficeTeamDropdown.selectByVisibleText(newPrivateTeam);
    }

    public void selectOverrideInitialDraftTeamByVisibleText(String newInitialDraftTeam) {
        overrideInitialDraftTeamDropdown.selectByVisibleText(newInitialDraftTeam);
    }

    public void assertTopicsAssigned() {
        workstacks.selectTimeLineTab();
        waitABit(2000);
        workstacks.selectSummaryTab();
        waitABit(2000);
        workstacks.selectTimeLineTab();
        waitABit(2000);
        String testTopic = sessionVariableCalled("topic").toString();
        String thisTopic = topicInTimeline.getText().toUpperCase();
        System.out.println(thisTopic);
        System.out.println(thisTopic);
        assertThat(thisTopic, is("NAME: " + testTopic));
    }
}
