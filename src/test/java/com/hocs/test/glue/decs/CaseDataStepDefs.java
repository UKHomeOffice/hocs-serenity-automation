package com.hocs.test.glue.decs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.UnallocatedCaseView;
import com.hocs.test.pages.decs.Workstacks;
import io.cucumber.java.en.And;

public class CaseDataStepDefs extends BasePage {

    RecordCaseData recordCaseData;

    UnallocatedCaseView unallocatedCaseView;

    SummaryTab summaryTab;

    Dashboard dashboard;

    Workstacks workstacks;

    @And("the read-only Case Details accordion should contain all case information entered during the {string} stage")
    public void theReadOnlyCaseDetailsAccordionShouldContainAllCaseInformationEnteredDuringTheStage(String stageName) {
        if (!accordionSectionIsVisible(stageName)) {
            summaryTab.selectSummaryTab();
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader("User", getCurrentUser().getUsername());
            String assignedTeam = summaryTab.getSummaryTabValueForGivenHeader("Team");
            goToDashboard();
            dashboard.selectWorkstackByTeamName(assignedTeam);
            workstacks.unallocateSelectedCase(getCurrentCaseReference());
            workstacks.selectSpecificCaseReferenceLink(getCurrentCaseReference());
        }
        openOrCloseAccordionSection(stageName);
        recordCaseData.assertAllRecordedCaseDataIsDisplayedInTheReadOnlyAccordionSection();
    }
}
