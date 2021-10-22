package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.PeopleTab;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.TimelineTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.DataInput;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;

public class BaseStepDefs extends BasePage {

    DataInput dataInput;

    Dashboard dashboard;

    SummaryTab summaryTab;

    PeopleTab peopleTab;

    TimelineTab timelineTab;

    User originalUser;

    CaseView caseView;

    @Then("the {string} page should be displayed")
    public void thePageShouldBeDisplayed(String pageTitle) {
        assertPageTitle(pageTitle);
    }

    @Then("an error message is displayed")
    public void anErrorMessageIsDisplayed() {
        errorMessageIsDisplayed();
    }

    @When("I click the {string} link")
    public void clickTheLink(String link) {
        switch (link.toUpperCase()) {
            case "BACK":
            case "CANCEL":
                safeClickOn(backLink);
                break;
            default:
                pendingStep(link + " is not defined within " + getMethodName());
        }
    }

    @When("I click the {string} button")
    public void iClickTheButton(String buttonLabel) {
        try {
            clickTheButton(buttonLabel);
        } catch (StaleElementReferenceException sE) {
            waitABit(500);
            clickTheButton(buttonLabel);
        }
    }

    @When("I open/close the {string} accordion section")
    public void iOpenCloseTheAccordionSection(String accordionLabel) {
        try {
            openOrCloseAccordionSection(accordionLabel);
        } catch (StaleElementReferenceException sE) {
            waitABit(500);
            openOrCloseAccordionSection(accordionLabel);
        }
    }

    @And("I select 'Save changes'")
    public void iSelectSaveChanges() {
        safeClickOn(saveChangesRadioButton);
    }

    @Then("the case/claim should be closed")
    public void theCaseShouldBeClosed() {
        dashboard.getCurrentCase();
        caseView.assertCaseCannotBeAssigned();
//        if (!wcsCase()) {
//            timelineTab.selectTimelineTab();
//            timelineTab.assertCaseClosedNoteVisible();
//        }
        System.out.println("The case is closed");
    }

    @Then("the case/claim should be at/moved/returned (to )(the ){string}( stage)")
    public void assertCaseTypeMovedOrReturnedToStage(String stage) {
        if (foiCase() && (stage.equalsIgnoreCase("ALLOCATION") || stage.equalsIgnoreCase("APPROVAL") || stage.equalsIgnoreCase("DISPATCH") || stage
                .equalsIgnoreCase("SOFT CLOSE"))) {
            waitABit(1000);
            if (caseView.currentCaseIsLoaded()) {
                try {
                    summaryTab.selectSummaryTab();
                } catch (ElementNotVisibleException | StaleElementReferenceException e) {
                    timelineTab.selectTimelineTab();
                    waitABit(500);
                    summaryTab.selectSummaryTab();
                }
            } else {
                dashboard.goToDashboard();
                dashboard.getCurrentCase();
                summaryTab.selectSummaryTab();
            }
        } else {
            dashboard.goToDashboard();
            dashboard.getCurrentCase();
            summaryTab.selectSummaryTab();
        }
        summaryTab.assertCaseStage(stage);
    }

    @And("I view the {string} tab")
    public void iClickToViewTheTab(String tab) {
        switch (tab.toUpperCase()) {
            case "SUMMARY":
                summaryTab.selectSummaryTab();
                break;
            case "TIMELINE":
                timelineTab.selectTimelineTab();
                break;
            case "PEOPLE":
                peopleTab.selectPeopleTab();
                break;
            default:
                pendingStep(tab + " is not defined within " + getMethodName());
        }
    }

    @And("the case {string} be allocated to me in the summary")
    public void theCaseShouldBeAllocatedToMeInTheSummary(String input) {
        summaryTab.selectSummaryTab();
        switch (input.toUpperCase()) {
            case "SHOULD":
                summaryTab.assertAllocatedUserIsMe(true);
                break;
            case "SHOULD NOT":
                summaryTab.assertAllocatedUserIsMe(false);
                break;
            default:
                pendingStep(input + " is not defined within " + getMethodName());
        }
    }

    @And("I record the user who completed the previous stages")
    public void iRecordTheUserWhoCompletedThePreviousStages() {
        originalUser = getCurrentUser();
    }

    @Then("the case should be allocated to the original user")
    public void caseShouldBeAllocatedTo() {
        summaryTab.selectSummaryTab();
        int retest = 0;
        while (retest < 5) {
            try {
                summaryTab.assertAllocatedUserIs(originalUser);
                break;
            } catch (AssertionError a) {
                retest++;
                timelineTab.selectTimelineTab();
                summaryTab.selectSummaryTab();
            }
        }
        summaryTab.assertAllocatedUserIs(originalUser);
    }

    @And("the case should be in the correct MPAM {string} team workstack")
    public void theCaseShouldBeInTheCorrectMPAMTeamWorkstack(String stage) {
        summaryTab.assertAllocatedMPAMTeam(stage);
    }

    @Then("the claim should be sent/returned to the correct WCS Casework team")
    public void theClaimShouldBeReturnedToTheCaseworkTeamThatLastWorkedTheClaim() {
        dashboard.getCurrentCase();
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("selectedCaseworkTeam"), "Team");
    }

    @And("I record the case reference of this case as {string}")
    public void iRecordTheCaseReferenceOfThisCaseAs(String sessionVariableName) {
        setSessionVariable(sessionVariableName).to(getCurrentCaseReference());
    }

    @Then("the header tags in the HTML of the page are properly structured")
    public void theOrderOfHeaderTagsInHTMLOfThePageAreProperlyOrdered() {
        checkOrderOfHeaderTagsOnCaseView();
    }

    @And("the accessibility statement link should be visible")
    public void accessibilityStatementLinkShouldBeVisible() {
        assertVisibilityOfAccessibilityLink();
    }

    @And("the summary should display the owning team as {string}")
    public void theSummaryShouldDisplayTheOwningTeamAs(String teamName) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the summary should display {string} for {string}")
    public void theSummaryShouldDisplayFor(String value, String header) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(value, header);
    }
}

