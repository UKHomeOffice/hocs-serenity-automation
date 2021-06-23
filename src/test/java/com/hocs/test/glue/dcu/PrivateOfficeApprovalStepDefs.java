package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.TimelineTab;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOfficeApprovalStepDefs extends BasePage {

    Dashboard dashboard;

    PrivateOfficeApproval privateOfficeApproval;

    AccordionDCU accordionDCU;

    UnallocatedCaseView unallocatedCaseView;

    SummaryTab summaryTab;

    TimelineTab timelineTab;

    @When("I complete the Private Office stage")
    public void completePrivateOfficeStagePerCaseType() {
        String caseType = sessionVariableCalled("caseType");
        switch(caseType.toUpperCase()) {
            case "MIN" :
            case "DTEN":
                if (!privateOfficeApproval.privateOfficeAcceptRadioButton.isVisible()) {
                    dashboard.getCurrentCase();
                    safeClickOn(unallocatedCaseView.allocateToMeLink);
                }
                safeClickOn(privateOfficeApproval.privateOfficeAcceptRadioButton);
                safeClickOn(continueButton);
                break;
            case "TRO" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I override the Primary Topic of the case at the Private Office stage to {string}")
    public void iOverrideTheOfTheCaseAtThePrivateOfficeStage(String input) {
        privateOfficeApproval.changeTopicAtPOStage(input);
    }

    @Then("an error message should be displayed as I have not selected whether I approve the response")
    public void assertThatApprovedResponseErrorMessageIsShown() {
        privateOfficeApproval.assertDoYouApproveTheResponseErrorMessage();
    }

    @Then("error messages should be displayed as I have not selected an override team or entered change reasoning")
    public void assertThatChangeMinisterErrorMessagesAreShown() {
        privateOfficeApproval.assertChangeMinisterErrorMessages();
    }

    @Then("an error message should be displayed as I have not entered feedback into the text box")
    public void assertThatFeedbackResponseErrorMessageIsShown() {
        privateOfficeApproval.assertWhatIsYourFeedbackResponse();
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
        safeClickOn(timelineTab.timelineTab);
        privateOfficeApproval.assertTopicChangeCaseNoteIsAddedToTimeline();
    }

    @Then("the {string} of the case should be updated to {string} in the summary tab")
    public void theOfTheCaseShouldBeUpdatedToInTheSummaryTab(String category, String input) {
        WebElementFacade summaryTabField = null;
        if (!summaryTab.activeStage.isVisible()) {
            safeClickOn(summaryTab.summaryTab);
        }
        waitABit(1000);
        switch (category.toUpperCase()) {
            case "PRIMARY TOPIC":
                summaryTabField = summaryTab.primaryTopic;
                break;
            case "TEAM":
                summaryTabField = summaryTab.currentTeam;
                break;
            case "PRIVATE OFFICE TEAM":
                summaryTabField = summaryTab.privateOfficeTeam;
                break;
            case "OVERRIDE PRIVATE OFFICE TEAM":
                summaryTabField = summaryTab.overridePrivateOfficeTeam;
                break;
            default:
                pendingStep(category + " is not defined within " + getMethodName());
        }
        assertThat(summaryTabField.getText().toUpperCase().contains(input.toUpperCase()), is(true));
    }

    @And("I change the minister to {string}")
    public void iChangeTheMinisterTo(String minister) {
        privateOfficeApproval.getToChangeMinisterScreenPrerequisites();
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(minister);
        privateOfficeApproval.enterAReasonForChangingPOTeam("Test change deadlines at PO stage");
        clickTheButton("Finish");
    }
}
