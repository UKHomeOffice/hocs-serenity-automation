package com.hocs.test.glue;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.UnassignedCaseView;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.Workstacks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class PrivateOfficeApprovalStepDefs extends BasePage {

    Homepage homepage;

    PrivateOfficeApproval privateOfficeApproval;

    Workstacks workstacks;

    UnassignedCaseView UnassignedCaseView;

    @When("I complete the Private Office stage")
    public void completePrivateOfficeStagePerCaseType() {
        String caseType = sessionVariableCalled("caseType");
        switch(caseType.toUpperCase()) {
            case "MIN" :
            case "DTEN":
                if (homepage.myCases.isVisible()) {
                    homepage.getCurrentCase();
                    safeClickOn(workstacks.allocateToMeButton);
                }
                safeClickOn(privateOfficeApproval.privateOfficeAcceptRadioButton);
                safeClickOn(privateOfficeApproval.continueButton);
                break;
            case "TRO" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
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
        UnassignedCaseView.assertAccordionPrivateOfficeApprovalFieldsAfterPOTeamChange();
    }
}
