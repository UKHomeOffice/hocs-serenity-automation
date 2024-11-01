package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class LinkTopicToTeam extends BasePage {

    @FindBy(xpath = "//input[@id='topics-input']")
    public WebElementFacade topicsSearchBar;

    @FindBy(xpath = "//input[@id='draft-qa-input']")
    public WebElementFacade draftAndQATeamSearchBar;

    @FindBy(xpath = "//input[@id='private-minister-input']")
    public WebElementFacade privateAndMinisterTeamSearchBar;

    @FindBy(css = ".govuk-table__body tr:nth-child(1) td")
    public WebElementFacade draftQaTeamAssignment;

    @FindBy(css = ".govuk-table__body tr:nth-child(2) td")
    public WebElementFacade privateMinisterTeamAssignment;

    @FindBy(css = ".govuk-error-summary__body")
    public WebElementFacade errorMessageContents;

    @FindBy(css = ".govuk-heading-l")
    public WebElementFacade topicInSummary;

    public void selectATopic(String topic) {
        selectSpecificOptionFromTypeaheadWithHeading(topic, "Topics");
//        waitABit(1000);
//        safeClickOn(topicsSearchBar);
//        topicsSearchBar.sendKeys(topic);
//        waitABit(1000);
//        topicsSearchBar.sendKeys(Keys.ENTER);
        setSessionVariable("chosenTopic").to(topic);

    }

    public void selectADraftAndQATeam(String team) {
        selectSpecificOptionFromTypeaheadWithHeading(team,"Select team assignment for Initial Draft and QA response stages");
//        waitABit(1000);
//        safeClickOn(draftAndQATeamSearchBar);
//        draftAndQATeamSearchBar.sendKeys(team);
//        waitABit(1000);
//        draftAndQATeamSearchBar.sendKeys(Keys.ENTER);
        setSessionVariable("chosenDraftAndQATeam").to(team);
    }

    public void selectAPrivateAndMinisterTeam(String team) {
        selectSpecificOptionFromTypeaheadWithHeading(team,"Select team assignment for Private Office and Minister sign off stages");
//        waitABit(1000);
//        safeClickOn(privateAndMinisterTeamSearchBar);
//        privateAndMinisterTeamSearchBar.sendKeys(team);
//        setSessionVariable("chosenPrivateAndMinisterTeam").to(team);
//        waitABit(1000);
//        privateAndMinisterTeamSearchBar.sendKeys(Keys.ENTER);
        setSessionVariable("chosenPrivateAndMinisterTeam").to(team);

    }

    public void assertSummaryDisplaysSelectedTopicAndTeams() {
        waitABit(2000);
        topicInSummary.shouldContainText(sessionVariableCalled("chosenTopic").toString());
        draftQaTeamAssignment.shouldContainText(sessionVariableCalled("chosenDraftAndQATeam").toString());
        privateMinisterTeamAssignment.shouldContainText(sessionVariableCalled("chosenPrivateAndMinisterTeam").toString());
    }

    public void assertLinkTopicToTeamPageTitle() {
        managementUIPageTitle.shouldContainText("Topic search");
    }

    public void assertTopicIsRequiredErrorMessage() {
        errorMessageContents.shouldContainText("Select a topic before submitting.");
    }

    public void assertDraftandQATeamIsRequiredErrorMessage() {
        errorMessageContents.shouldContainText("The team for Initial Draft and QA response stages is required");
    }

    public void assertPrivateAndMinisterTeamIsRequiredErrorMessage() {
        errorMessageContents.shouldContainText("The team for Private Office and Minister sign off stages is required");
    }
}
