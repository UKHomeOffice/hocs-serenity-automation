package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.draft.Draft;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.draft.Qa;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DraftResponseStepDefs {

    Draft draft;

    DraftingTeamDecision draftingTeamDecision;

    Page page;

    Homepage homepage;

    AddDocuments addDocuments;

    QAResponse qaResponse;

    DataInput dataInput;

    SuccessfulCaseCreation successfulCaseCreation;

    Qa qa;

    @When("^I complete the initial draft stage$")
    public void completeInitialDraftStage() {
        dataInput.selectTeam1();
        successfulCaseCreation.clickSessionVariableViaLinkText();
        draftingTeamDecision.clickAcceptInitialDraftDecision();
        page.clickContinueButton();
        draftingTeamDecision.clickDraftingResponseLetter();
        page.clickContinueButton();
        draft.clickAddDocumentsButton();
        draft.selectDocumentTypeByIndex(1);
        addDocuments.uploadDocument();
        page.clickAddButton();
        page.clickContinueButton();
        // offline QA decision NO
        qa.clickOfflineQANoRadioButton();
        page.clickContinueButton();
        //qaResponse.clickOfflineQANoButton();
        System.out.println("I have completed the initial draft and am QA'ing the case");
        // Should belong to QAResponse StepDefs vv
    }

    @When("^I select to reply by \"([^\"]*)\"$")
    public void iClickToAnswerBy(String method) {
        draft.clickAnsweredByMyTeamYesRadioButton();
        page.clickContinueButton();
        switch (method.toUpperCase()) {
            case "EMAIL":
                draft.clickEmailReplyRadioButton();
                break;
            case "PHONE":
                draft.clickPhoneReplyRadioButton();
                break;
            case "POST":
                draft.clickLetterReplyRadioButton();
                break;
            default:
                fail("Please enter EMAIL, PHONE or POST");
        }
        page.clickContinueButton();
    }

    @Then("^I can see the drafting deadline for a case$")
    public void iCanSeeTheDraftingDeadlineForACase() {
        draft.draftingDeadlineIsDisplayed();
    }

    @When("^a case has gone beyond the drafting deadline$")
    public void aCaseHasGoneBeyondTheDraftingDeadline() {

    }

    @And("^I enter call notes$")
    public void iEnterCallNotes() {
        draft.enterAllocationNoteField();
    }

    @And("^I do not enter call notes$")
    public void iDoNotEnterCallNotes() {
        draft.clearAllocationNoteField();
    }

    @Then("^an error message appears instructing me to add rejection reasons$")
    public void anErrorMessageAppearsInstructingMeToAddRejectionReasons() {
        draft.assertEnterRejectionReasonsError();
    }

    @When("^I finish drafting$")
    public void iFinishDrafting() {

    }

    @Then("^I see an error message instructing me to enter call notes$")
    public void iSeeAnErrorMessageInstructingMeToEnterCallNotes() {
        draft.assertEnterCallNotesError();
    }

    @Then("^I can see an error message instructing me to select either ‘online QA’ or ‘offline QA’$")
    public void iCanSeeAnErrorMessageInstructingMeToSelectEitherOnlineQAOrOfflineQA() {
        draft.assertEnterQaMethodError();
    }

    @And("^I download the standard line for the case$")
    public void iDownloadTheStandardLineForTheCase() {
        draft.clickStandardLine();
    }

    @And("^I select an \"([^\"]*)\" Quality Assurer$")
    public void iSelectAQualityAssurer(String qa) {
        switch (qa.toUpperCase()) {
            case "OFFLINE":
                break;
            case "ONLINE":
                break;
            default:
                fail("Please select OFFLINE or ONLINE as a QA option");
        }
    }

    @When("^a case is not answered by my team$")
    public void aCaseIsNotAnsweredByMyTeam() {
        draft.clickAnsweredByMyTeamNoRadioButton();
        page.clickContinueButton();
        page.enterRejectionNotes();
    }

    @When("^I select a case \"([^\"]*)\" answered by my team$")
    public void iSelectACaseAnsweredByMyTeam(String decision) {
        page.getCaseId();

        switch (decision.toUpperCase()) {
            case "SHOULD":
                draftingTeamDecision.clickAcceptInitialDraftDecision();
                break;
            case "SHOULD NOT":
                draftingTeamDecision.clickRejectInitialDraftDecision();
                break;
            default:
                fail(decision
                        + " is not defined in DraftResponseStepDefs.iSelectACaseAnsweredByMyTeam");
        }
        page.clickContinueButton();
    }

    @When("^I \"([^\"]*)\" the call details$")
    public void iTheCallDetails(String callDetails)  {
        switch (callDetails.toUpperCase()) {
            case "COMPLETE":
                draftingTeamDecision.enterPhoneCallSummaryNote();
                page.clickFinishButton();
                break;
            case "DO NOT COMPLETE":
                page.clickFinishButton();
                break;
            default:
                fail(callDetails
                        + " is not defined in DraftResponseStepDefs.iTheCallDetails");
        }
    }
}
