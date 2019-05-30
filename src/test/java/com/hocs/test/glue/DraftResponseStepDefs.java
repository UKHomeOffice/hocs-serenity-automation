package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.draft.Draft;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.teamqueue.Teamqueue;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;

public class DraftResponseStepDefs {

    Page page;

    Draft draft;

    Homepage homepage;

    DraftingTeamDecision draftingTeamDecision;

    SuccessfulCaseCreation successfulCaseCreation;

    Qa qa;

    Teamqueue teamqueue;

    AddDocuments addDocuments;

    Workstacks workstacks;

    @When("^I complete the Initial Draft stage$")
    public void initialDraftFullFlow() {
        page.clickOn(homepage.animalsInScienceTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        draftingTeamDecision.acceptAndDraftALetter();
        draftingTeamDecision.uploadDraftResponse();
        qa.dontQAOffline();
    }

    @When("^I click the continue button on the correspondence answer screen$")
    public void clickContinueButtonOnCorrespondenceAnswerScreen() {
        page.clickOn(draft.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected radio buttons on this screen$")
    public void assertThatCorrespondenceCanBeAnsweredErrorMessageIsShown() {
        draft.assertCorrespondenceAnsweredErrorMessage();
    }

//    @When("^I click the finish button on the case rejection screen$")
//    public void clickFinishButtonOnCaseRejectionScreen() {
//        page.clickOn(draft.answeredByMyTeamNoRadioButton);
//        page.clickOn(draft.continueButton);
//        page.clickOn(draft.finishButton);
//    }

    @Then("^an error message should be displayed as I have not entered a reason in the text box$")
    public void assertThatShouldBeAnsweredErrorMessageIsShown() {
        draft.assertShouldBeAnsweredErrorMessage();
    }

//    @When("^I click the continue button on how do you intend to respond screen$")
//    public void clickContinueButtonOnHowDoYouIntendToRespondScreen() {
//        page.clickOn(draft.answeredByMyTeamYesRadioButton);
//        page.clickOn(draft.continueButton);
//        draft.sleep(500);
//        page.clickOn(draft.continueButton);
//    }

    @Then("^an error message should be displayed as I have not selected a response on this screen$")
    public void assertThatHowDoYouIntendToRespondScreenIsShown() {
        draft.assertHowDoYouIntendToRespondErrorMessage();
    }

//    @When("^I click the finish button on the summarise your call screen$")
//    public void clickFinishButtonOnSummariseYourCallScreen() {
//        page.clickOn(draft.answeredByMyTeamYesRadioButton);
//        page.clickOn(draft.continueButton);
//        page.clickOn(draft.phoneReplyRadioButton);
//        page.clickOn(draft.continueButton);
//        page.clickOn(draft.finishButton);
//    }

    @Then("^an error message should be displayed as I have not summarised the call$")
    public void assertThatSummariseYourCallErrorMessageIsShown() {
        draft.assertPleaseSummariseYourCallErrorMessage();
    }

//    @When("^I click the continue button on the which is the primary draft document screen$")
//    public void clickContinueButtonOnWhichIsPrimaryDraftDocScreen() {
//        page.clickOn(draft.answeredByMyTeamYesRadioButton);
//        page.clickOn(draft.continueButton);
//        page.clickOn(draft.letterReplyRadioButton);
//        page.clickOn(draft.continueButton);
//        draft.sleep(500);
//        page.clickOn(draft.continueButton);
//    }

    @Then("^an error message should be displayed as I have not added a primary draft document$")
    public void assertThatWhichIsThePrimaryDraftDocumentErrorMesasgeIsShown() {
        draft.assertWhichIsThePrimaryDraftDocumentErrorMessage();
    }

//    @When("^I click the add button on the add documents screen$")
//    public void clickAddButtonOnAddDocumentScreen() {
//        page.clickOn(draft.answeredByMyTeamYesRadioButton);
//        page.clickOn(draft.continueButton);
//        page.clickOn(draft.letterReplyRadioButton);
//        page.clickOn(draft.continueButton);
//        page.clickOn(draft.draftStageAddDocumentsButton);
//        page.clickOn(addDocuments.addButton);
//    }

    @Then("^an error message should be displayed as I have not selected a document type and added a document$")
    public void assertThatAddDocumentErrorMessagesAreShown() {
        addDocuments.assertDocumentTypeIsRequiredErrorMessage();
        addDocuments.assertDocumentIsRequiredErrorMessage();
    }

    @When("^I click the continue button on the do you want QA this offline screen$")
    public void clickContinueButtonOnDoYouWantToQAOfflineScreen() {
        page.clickOn(draft.answeredByMyTeamYesRadioButton);
        page.clickOn(draft.continueButton);
        page.clickOn(draft.letterReplyRadioButton);
        page.clickOn(draft.continueButton);
        page.clickOn(draft.draftStageAddDocumentsButton);
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        page.clickOn(addDocuments.addButton);
        page.clickOn(draft.continueButton);
        draft.sleep(500);
        page.clickOn(draft.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected whether the case should be QA offline or not$")
    public void assertThatQAOfflineErrorMessageIsShown() {
        draft.assertDoYouWantToQAThisOfflineErrorMessage();
    }

    @When("^I click the finish button on the who has done the offline QA screen$")
    public void clickFinishButtonOnWhoHasDoneTheOfflineQAScreen() {
        page.clickOn(draft.answeredByMyTeamYesRadioButton);
        page.clickOn(draft.continueButton);
        page.clickOn(draft.letterReplyRadioButton);
        page.clickOn(draft.continueButton);
        page.clickOn(draft.draftStageAddDocumentsButton);
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        page.clickOn(addDocuments.addButton);
        page.clickOn(draft.continueButton);
        page.clickOn(qa.offlineQaYesRadioButton);
        page.clickOn(draft.continueButton);
        page.clickOn(draft.finishButton);
    }

    @Then("^an error message should be displayed as I have not selected the user that did the offline QA$")
    public void assertThatWhoHasDoneTheOfflineQAErrorMessageIsShown() {
        draft.assertWhoHasDoneOfflineQAErrorMessage();
    }

    @When("^I select to reply by \"([^\"]*)\"$")
    public void iClickToAnswerBy(String method) {
        page.clickOn(draft.answeredByMyTeamYesRadioButton);
        page.clickOn(draft.continueButton);
        switch (method.toUpperCase()) {
            case "EMAIL":
                page.clickOn(draft.emailReplyRadioButton);
                break;
            case "PHONE":
                page.clickOn(draft.phoneReplyRadioButton);
                break;
            case "POST":
                page.clickOn(draft.letterReplyRadioButton);
                break;
            default:
                pendingStep(method + " is not defined within " + getMethodName());
        }
        page.clickOn(draft.continueButton);
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
        page.clickOn(draft.standardLine);
    }

    @And("^I select an \"([^\"]*)\" Quality Assurer$")
    public void iSelectAQualityAssurer(String qa) {
        switch (qa.toUpperCase()) {
            case "OFFLINE":
                break;
            case "ONLINE":
                break;
            default:
                pendingStep(qa + " is not defined within " + getMethodName());
        }
    }

    @When("^a case is not answered by my team$")
    public void aCaseIsNotAnsweredByMyTeam() {
        page.clickOn(draft.answeredByMyTeamNoRadioButton);
        page.clickOn(draft.continueButton);
        draft.enterRejectionNotes();
    }

    @When("^I select a case \"([^\"]*)\" answered by my team$")
    public void iSelectACaseAnsweredByMyTeam(String decision) {
        page.getCaseId();

        switch (decision.toUpperCase()) {
            case "SHOULD":
                page.clickOn(draftingTeamDecision.initialDraftingDecisionAccept);
                break;
            case "SHOULD NOT":
                page.clickOn(draftingTeamDecision.initialDraftingDecisionReject);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        page.clickOn(draft.continueButton);
    }

    @When("^I \"([^\"]*)\" the call details$")
    public void iTheCallDetails(String callDetails) {
        switch (callDetails.toUpperCase()) {
            case "COMPLETE":
                draftingTeamDecision.enterPhoneCallSummaryNote();
                page.clickOn(draft.finishButton);
                break;
            case "DO NOT COMPLETE":
                page.clickOn(draft.finishButton);
                break;
            default:
                pendingStep(callDetails + " is not defined within " + getMethodName());
        }
    }

    @Then("^the case should be moved to the \"([^\"]*)\" stage$")
    public void assertCaseReturnedToStage(String stage) {
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                page.clickOn(homepage.performanceProcessTeam);
                break;
            case "MARKUP":
                page.clickOn(homepage.centralDraftingTeam);
                break;
            case "INITIAL DRAFT":
                page.clickOn(homepage.animalsInScienceTeam);
                break;
            case "QA RESPONSE":
                page.clickOn(homepage.performanceProcessTeam);
                break;
            case "PRIVATE OFFICE APPROVAL":
                //Depends on the Team but mainly this one
                page.clickOn(homepage.ministerForLordsTeam);
                break;
            case "MINISTERIAL SIGN OFF":
                page.clickOn(homepage.ministerForLordsTeam);
                break;
            case "DISPATCH":
                page.clickOn(homepage.performanceProcessTeam);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        teamqueue.assertCaseStage(stage);
    }
}
