package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.SummaryTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class SummaryTabStepDefs extends BasePage {

    CaseView caseView;

    SummaryTab summaryTab;

    @And("I select the summary tab")
    public void iSelectTheSummaryTab() {
        caseView.waitForCaseToLoad();
        summaryTab.selectSummaryTab();
    }

    @And("I select the previous COMP case reference from the COMP2 case summary tab")
    public void thePreviousCOMPCaseReferenceIsDisplayedInTheCOMP2CaseSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.selectPreviousCaseReference();
    }

    @Then("the deadline of the FOI case should be extended the correct number of days")
    public void theDeadlineOfTheFOICaseShouldBeExtended() {
        summaryTab.selectSummaryTab();
        summaryTab.assertDeadlineOfExtendedFOICase();
    }

    @Then("the information entered for the FOI appeal should be displayed in the summary")
    public void theInformationEnteredForTheFOIAppealShouldBeDisplayedInTheSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.assertAppealInformationIsDisplayed();
    }

    @Then("the stage deadline dates displayed in the summary are correct for a {string} case")
    public void theStageDeadlineDatesDisplayedInTheSummaryAreCorrectForACase(String deadlineDecidingFactor) {
        summaryTab.selectSummaryTab();
        switch (deadlineDecidingFactor.toUpperCase()) {
            case "MIN":
            case "HOME SECRETARY SIGN-OFF":
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Data Input");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Markup");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Initial Draft");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "QA Response");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Private Office Approval");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Ministerial Sign Off");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Transfer Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "No Response Needed Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Dispatch");
                break;
            case "DTEN":
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Dispatch");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Initial Draft");
                break;
            case "TRO":
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Data Input");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Markup");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Initial Draft");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "QA Response");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Transfer Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "No Response Needed Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Dispatch");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Copy To Number 10");
                break;
            default:
                pendingStep(deadlineDecidingFactor + " is not defined within " + getMethodName());
        }
    }

    @Then("the case deadline date displayed in the summary is correct for a {string} case")
    public void theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString(String deadlineDecidingFactor) {
        summaryTab.assertDeadlineDateOfCase(deadlineDecidingFactor);
    }

    @Then("the stage and case deadlines have altered to those for a 10 day SLA")
    public void theStageAndCaseDeadlinesHaveAlteredToThoseForADaySLA() {
        theStageDeadlineDatesDisplayedInTheSummaryAreCorrectForACase("HOME SECRETARY SIGN-OFF");
        theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString("HOME SECRETARY SIGN-OFF");
    }

    @Then("the stage and case deadlines revert back to those for a 20 day SLA")
    public void theDeadlineDatesRevertBackToThoseForADaySLA() {
        theStageDeadlineDatesDisplayedInTheSummaryAreCorrectForACase("MIN");
        theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString("MIN");
    }
}
