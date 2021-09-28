package com.hocs.test.glue.dcu;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.TimelineTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PrivateOfficeApprovalStepDefs extends BasePage {

    Dashboard dashboard;

    PrivateOfficeApproval privateOfficeApproval;

    AccordionDCU accordionDCU;

    CaseView caseView;

    SummaryTab summaryTab;

    TimelineTab timelineTab;

    DCUProgressCase dcuProgressCase;

    @And("I override the Primary Topic of the case at the Private Office stage to {string}")
    public void iOverrideTheOfTheCaseAtThePrivateOfficeStage(String input) {
        privateOfficeApproval.changeTopicAtPOStage(input);
    }

    @And("I select to change minister")
    public void iSelectToChangeMinister() {
        privateOfficeApproval.getToChangeMinisterScreenPrerequisites();
    }

    @And("I select {string} as the new Private Office team")
    public void iSelectAsTheNewMinister(String newPOTeam) {
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(newPOTeam);
    }

    @And("I enter {string} as the reason for changing Private Office team")
    public void iEnterAsTheReasonForChangingPrivateOfficeTeam(String reason) {
        privateOfficeApproval.enterAReasonForChangingPOTeam(reason);
    }

    @Then("the information shown should match what I entered on the change Private Office Team page")
    public void theInformationShownShouldMatchWhatIEnteredOnTheChangePrivateOfficeTeamPage() {
        accordionDCU.assertAccordionPrivateOfficeApprovalFieldsAfterPOTeamChange();
    }

    @Then("the reason for changing the primary topic of the case should be added as a case note in the timeline")
    public void theReasonForChangingPrimaryTopicOfCaseShouldBeAddedAsCaseNoteInTheTimeline() {
        timelineTab.selectTimelineTab();
        privateOfficeApproval.assertTopicChangeCaseNoteIsAddedToTimeline();
    }

    @And("I change the minister to {string}")
    public void iChangeTheMinisterTo(String minister) {
        privateOfficeApproval.getToChangeMinisterScreenPrerequisites();
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(minister);
        privateOfficeApproval.enterAReasonForChangingPOTeam("Test change deadlines at PO stage");
        clickTheButton("Finish");
    }

    @And("I advance the case to the Private Office Approval stage")
    public void iAdvanceTheCaseToThePrivateOfficeApprovalStage() {
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromInitialDraftToQaResponse();
        dcuProgressCase.moveCaseFromQAResponseToPrivateOfficeApprovalOrDispatch();
    }
}
