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
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.core.Serenity.pendingStep;

public class DraftResponseStepDefs extends Page {

    @Steps(shared = true)
    Draft draft;

    Homepage homepage;

    DraftingTeamDecision draftingTeamDecision;

    Qa qa;

    Teamqueue teamqueue;

    AddDocuments addDocuments;

    Workstacks workstacks;

    @When("^I complete the Initial Draft stage$")
    public void initialDraftFullFlow() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        if (isElementDisplayed($("//span[contains(text(), 'DTEN')]"))) {
            draftingTeamDecision.dtenAcceptAndDraftALetter();
            draftingTeamDecision.uploadDraftResponse();
            qa.dontQAOffline();
        } else {
            draftingTeamDecision.acceptAndDraftALetter();
            draftingTeamDecision.uploadDraftResponse();
            qa.dontQAOffline();
        }
    }

    @When("^I complete the Initial Draft stage for \"([^\"]*)\"$")
    public void initialDraftFullFlowPerCaseType(String caseType) {
        switch(caseType.toUpperCase()) {
            case "DCU MIN" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draftingTeamDecision.acceptAndDraftALetter();
                draftingTeamDecision.uploadDraftResponse();
                qa.dontQAOffline();
                break;
            case "DCU TRO" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draftingTeamDecision.acceptAndDraftALetter();
                draftingTeamDecision.uploadDraftResponse();
                qa.dontQAOffline();
                // adding comment to force a change in the code
                break;
            case "DCU N10" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the continue button on the correspondence answer screen$")
    public void clickContinueButtonOnCorrespondenceAnswerScreen() {
        clickOn(draft.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected radio buttons on this screen$")
    public void assertThatCorrespondenceCanBeAnsweredErrorMessageIsShown() {
        draft.assertCorrespondenceAnsweredErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered a reason in the text box$")
    public void assertThatShouldBeAnsweredErrorMessageIsShown() {
        draft.assertShouldBeAnsweredErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected a response on this screen$")
    public void assertThatHowDoYouIntendToRespondScreenIsShown() {
        draft.assertHowDoYouIntendToRespondErrorMessage();
    }

    @Then("^an error message should be displayed as I have not summarised the call$")
    public void assertThatSummariseYourCallErrorMessageIsShown() {
        draft.assertPleaseSummariseYourCallErrorMessage();
    }

    @Then("^an error message should be displayed as I have not added a primary draft document$")
    public void assertThatWhichIsThePrimaryDraftDocumentErrorMesasgeIsShown() {
        draft.assertWhichIsThePrimaryDraftDocumentErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected a document type and added a document$")
    public void assertThatAddDocumentErrorMessagesAreShown() {
        addDocuments.assertDocumentTypeIsRequiredErrorMessage();
        addDocuments.assertDocumentIsRequiredErrorMessage();
    }

    @When("^I click the continue button on the do you want QA this offline screen$")
    public void clickContinueButtonOnDoYouWantToQAOfflineScreen() {
        clickOn(draft.answeredByMyTeamYesRadioButton);
        clickOn(draft.continueButton);
        clickOn(draft.letterReplyRadioButton);
        clickOn(draft.continueButton);
        clickOn(draft.draftStageAddDocumentsButton);
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        clickOn(addDocuments.addButton);
        clickOn(draft.continueButton);
        draft.sleep(500);
        clickOn(draft.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected whether the case should be QA offline or not$")
    public void assertThatQAOfflineErrorMessageIsShown() {
        draft.assertDoYouWantToQAThisOfflineErrorMessage();
    }

    @When("^I click the finish button on the who has done the offline QA screen$")
    public void clickFinishButtonOnWhoHasDoneTheOfflineQAScreen() {
        clickOn(draft.answeredByMyTeamYesRadioButton);
        clickOn(draft.continueButton);
        clickOn(draft.letterReplyRadioButton);
        clickOn(draft.continueButton);
        clickOn(draft.draftStageAddDocumentsButton);
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        clickOn(addDocuments.addButton);
        clickOn(draft.continueButton);
        clickOn(qa.offlineQaYesRadioButton);
        clickOn(draft.continueButton);
        clickOn(draft.finishButton);
    }

    @Then("^an error message should be displayed as I have not selected the user that did the offline QA$")
    public void assertThatWhoHasDoneTheOfflineQAErrorMessageIsShown() {
        draft.assertWhoHasDoneOfflineQAErrorMessage();
    }

    @When("^I select to reply by \"([^\"]*)\"$")
    public void iClickToAnswerBy(String method) {
        clickOn(draft.answeredByMyTeamYesRadioButton);
        clickOn(draft.continueButton);
        switch (method.toUpperCase()) {
            case "EMAIL":
                clickOn(draft.emailReplyRadioButton);
                break;
            case "PHONE":
                clickOn(draft.phoneReplyRadioButton);
                break;
            case "POST":
                clickOn(draft.letterReplyRadioButton);
                break;
            default:
                pendingStep(method + " is not defined within " + getMethodName());
        }
        clickOn(draft.continueButton);
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
        clickOn(draft.standardLine);
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
        clickOn(draft.answeredByMyTeamNoRadioButton);
        clickOn(draft.continueButton);
        draft.enterRejectionNotes();
    }

    @When("^I select a case \"([^\"]*)\" answered by my team$")
    public void iSelectACaseAnsweredByMyTeam(String decision) {
        getCaseId();

        switch (decision.toUpperCase()) {
            case "SHOULD":
                clickOn(draftingTeamDecision.initialDraftingDecisionAccept);
                break;
            case "SHOULD NOT":
                clickOn(draftingTeamDecision.initialDraftingDecisionReject);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        clickOn(draft.continueButton);
    }

    @When("^I \"([^\"]*)\" the call details$")
    public void iTheCallDetails(String callDetails) {
        switch (callDetails.toUpperCase()) {
            case "COMPLETE":
                draftingTeamDecision.enterPhoneCallSummaryNote();
                clickOn(draft.finishButton);
                break;
            case "DO NOT COMPLETE":
                clickOn(draft.finishButton);
                break;
            default:
                pendingStep(callDetails + " is not defined within " + getMethodName());
        }
    }

    @Then("^the \"([^\"]*)\" case should be moved to the \"([^\"]*)\" stage$")
    public void assertCaseTypeReturnedToStage(String caseType, String stage) {
        switch(caseType.toUpperCase()) {
            case "DCU MIN" :
                assertCaseReturnedToStage(stage);
                break;
            case "DCU TRO" :
                switch(stage.toUpperCase()){
                    case "DATA INPUT" :
                        clickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP" :
                        clickOn(homepage.transferN10Team);
                        teamqueue.assertCaseStage(stage);
                        break;
                    case "INITIAL DRAFT" :
                        clickOn(homepage.animalsInScienceTeam);
                        teamqueue.assertCaseStage(stage);
                        break;
                    case "QA RESPONSE" :
                        clickOn(homepage.animalsInScienceTeam);
                        break;
                    case "PRIVATE OFFICE APPROVAL" :
                        clickOn(homepage.ministerForLordsTeam);
                        break;
                    case "MINISTERIAL SIGN OFF" :
                        clickOn(homepage.ministerForLordsTeam);
                        break;
                    case "DISPATCH" :
                        clickOn(homepage.animalsInScienceTeam);
                        break;
                    case "COPY TO NUMBER 10" :
                        clickOn(homepage.transferN10Team);
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("^the case should be moved to the \"([^\"]*)\" stage$")
    public void assertCaseReturnedToStage(String stage) {
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                clickOn(homepage.performanceProcessTeam);
                break;
            case "MARKUP":
                clickOn(homepage.performanceProcessTeam);
                break;
            case "INITIAL DRAFT":
                clickOn(homepage.animalsInScienceTeam);
                break;
            case "QA RESPONSE":
                clickOn(homepage.animalsInScienceTeam);
                break;
            case "PRIVATE OFFICE APPROVAL":
                clickOn(homepage.ministerForLordsTeam);
                break;
            case "MINISTERIAL SIGN OFF":
                clickOn(homepage.ministerForLordsTeam);
                break;
            case "DISPATCH":
                clickOn(homepage.performanceProcessTeam);
                break;
            case "COPY TO NUMBER 10":
                clickOn(homepage.transferN10Team);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        teamqueue.assertCaseStage(stage);
    }
}
