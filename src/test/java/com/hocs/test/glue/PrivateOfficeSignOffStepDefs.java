package com.hocs.test.glue;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.accordion.CaseDetailsAccordion;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class PrivateOfficeSignOffStepDefs extends Page {

    Homepage homepage;

    PrivateOffice privateOffice;

    Workstacks workstacks;

    CaseDetailsAccordion caseDetailsAccordion;

    @When("I complete the Private Office stage")
    public void completePrivateOfficeStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(privateOffice.privateOfficeAcceptRadioButton);
        clickOn(privateOffice.continueButton);
    }

    @When("I complete the Private Office stage for {string}")
    public void completePrivateOfficeStagePerCaseType(String caseType) {
        switch(caseType.toUpperCase()) {
            case "MIN" :
            case "DTEN":
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                clickOn(privateOffice.privateOfficeAcceptRadioButton);
                clickOn(privateOffice.continueButton);
                break;
            case "TRO" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not selected whether I approve the response")
    public void assertThatApprovedResponseErrorMessageIsShown() {
        privateOffice.assertDoYouApproveTheResponseErrorMessage();
    }

    @Then("error messages should be displayed as I have not selected an override team or entered change reasoning")
    public void assertThatChangeMinisterErrorMessagesAreShown() {
        privateOffice.assertChangeMinisterErrorMessages();
    }

    @Then("an error message should be displayed as I have not entered feedback into the text box")
    public void assertThatFeedbackResponseErrorMessageIsShown() {
        privateOffice.assertWhatIsYourFeedbackResponse();
    }

    @And("I select to change minister")
    public void iSelectToChangeMinister() {
        privateOffice.getToChangeMinisterScreenPrerequisites();
    }

    @And("I select {string} as the new Private Office team")
    public void iSelectAsTheNewMinister(String newPOTeam) {
        privateOffice.selectNewPrivateOfficeTeamFromDropdown(newPOTeam);
    }

    @And("I enter {string} as the reason for changing Private Office team")
    public void iEnterAsTheReasonForChangingPrivateOfficeTeam(String reason) {
        privateOffice.enterAReasonForChangingPOTeam(reason);
    }

    @Then("the information shown should match what I entered on the change Private Office Team page")
    public void theInformationShownShouldMatchWhatIEnteredOnTheChangePrivateOfficeTeamPage() {
        caseDetailsAccordion.assertAccordionPrivateOfficeApprovalFieldsAfterPOTeamChange();
    }
}
