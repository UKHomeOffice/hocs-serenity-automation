package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.PeopleTab;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.TimelineTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.foi.ActionsTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;

public class BaseStepDefs extends BasePage {

    Dashboard dashboard;

    SummaryTab summaryTab;

    PeopleTab peopleTab;

    TimelineTab timelineTab;

    ActionsTab actionsTab;

    CaseView caseView;

    @Then("the {string} page should be displayed")
    public void thePageShouldBeDisplayed(String pageTitle) {
        assertDECSPageTitle(pageTitle);
    }

    @Then("I should be returned to the {string} page")
    public void iShouldBeReturnedToTheScreen(String pageTitle) {
        assertDECSPageTitle(pageTitle);
    }

    @Then("an error message is displayed")
    public void anErrorMessageIsDisplayed() {
        assertThatAnErrorMessageIsDisplayed();
    }

    @When("I click the {string} link")
    public void iClickTheLink(String linkText) {
        clickTheLink(linkText);
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

    @When("I open/close the {string} accordion( section)")
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
        selectSpecificRadioButton("Save changes");
    }

    @Then("the case/claim should be closed")
    public void theCaseShouldBeClosed() {
        dashboard.ensureViewingCurrentCase();
        caseView.assertCaseCannotBeAllocated();
        summaryTab.assertNoActiveStageVisible();
        timelineTab.assertCaseLogWithTitleIsVisible("Case Closed");
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
                } catch (ElementNotInteractableException | StaleElementReferenceException e) {
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

    @And("I (attempt to )view the {string} tab")
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
            case "ACTIONS":
                actionsTab.selectActionsTab();
                break;
            default:
                pendingStep(tab + " is not defined within " + getMethodName());
        }
    }

    @Then("the case should be allocated to the previous user")
    public void caseShouldBeAllocatedToThePreviousUser() {
        summaryTab.selectSummaryTab();
        int retest = 0;
        while (retest < 5) {
            try {
                summaryTab.assertAllocatedUserIs(getPreviousUser());
                break;
            } catch (AssertionError a) {
                retest++;
                timelineTab.selectTimelineTab();
                summaryTab.selectSummaryTab();
            }
        }
        summaryTab.assertAllocatedUserIs(getPreviousUser());
    }

    @And("I record the case reference of this {string} case")
    public void iRecordTheCaseReferenceOfThisCase(String sessionVariableName) {
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

    @And("the {string} radio button should be unselected")
    public void theRadioButtonShouldBeUnselected(String radioButtonText) {
        caseView.clickAllocateToMeLink();
        assertRadioButtonIsSelected(radioButtonText);
    }
}
