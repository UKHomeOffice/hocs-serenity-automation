package com.hocs.test.glue.decs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CaseDetailsAccordionStepDefs extends BasePage {

    RecordCaseData recordCaseData;

    SummaryTab summaryTab;

    Dashboard dashboard;

    Workstacks workstacks;

    CaseView caseView;

    @And("the read-only Case Details accordion should contain all case information entered during the {string} stage")
    public void theReadOnlyCaseDetailsAccordionShouldContainAllCaseInformationEnteredDuringTheStage(String stageName) {
        if (!accordionSectionIsVisible(stageName)) {
            summaryTab.selectSummaryTab();
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(getCurrentUser().getUsername(), "User");
            String assignedTeam = summaryTab.getSummaryTabValueForGivenHeader("Team");
            dashboard.goToDashboard();
            dashboard.selectWorkstackByTeamName(assignedTeam);
            workstacks.unallocateSelectedCase(getCurrentCaseReference());
            workstacks.selectSpecificCaseReferenceLink(getCurrentCaseReference());
        }
        openOrCloseAccordionSection(stageName);
        recordCaseData.assertAllRecordedCaseDataIsCurrentlyVisibleInTheReadOnlyAccordion();
    }

    @And("the closure reason and details should be visible in the Case Details accordion")
    public void theClosureReasonAndDetailsShouldBeVisibleInTheCaseDetailsAccordion() {
        openOrCloseAccordionSection("Early Closure");
        recordCaseData.assertAllRecordedCaseDataIsCurrentlyVisibleInTheReadOnlyAccordion();
    }

    @Then("I can only view the Registration section of the Case Details accordion")
    public void iCanOnlyViewTheRegistrationSectionOfTheCaseDetailsAccordion() {
        if (!accordionSectionIsVisible("Registration")) {
            Assert.fail("Registration section not visible");
        }
        if (accordionSectionIsVisible("TRIAGE") || accordionSectionIsVisible("DRAFT") ||accordionSectionIsVisible("SEND")) {
            Assert.fail("Section other than Registration is visible");
        }
    }

    @Then("all case data should be visible in the read-only Case Details accordion")
    public void allCaseDataShouldBeVisibleInTheReadOnlyCaseDetailsAccordion() {
        caseView.expandAllCaseDetailsAccordionSections();
        recordCaseData.assertAllRecordedCaseDataIsCurrentlyVisibleInTheReadOnlyAccordion();
    }
}
