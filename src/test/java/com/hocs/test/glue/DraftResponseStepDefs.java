package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assume.assumeNotNull;

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
        homepage.selectAnimalsInScienceTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        draftingTeamDecision.acceptAndDraftALetter();
        draftingTeamDecision.uploadDraftResponse();
        qa.dontQAOffline();
    }

//    @When("^Initial draft stage \"([^\"]*)\"$")
//    public void completeInitialDraftStage(String caseType) {
//        switch (caseType.toUpperCase()){
//            case "DCU MIN":
//                draft.findAndAllocateDraftStage();
//                homepage.selectMyCases();
//                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
//                draftingTeamDecision.acceptAndDraftALetter();
//                draftingTeamDecision.uploadDraftResponse();
//                qa.dontQAOffline();
//                break;
//            case "DCU N10":
//                draft.findAndAllocateDraftStage();
//                homepage.selectMyCases();
//                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
//                draftingTeamDecision.clickAcceptInitialDraftDecision();
//                draft.clickContinueButton();
//                draftingTeamDecision.uploadDraftResponse();
//                qa.dontQAOffline();
//                break;
//            case "DCU TRO":  //does not have offline QA option available
//                draft.findAndAllocateDraftStage();
//                homepage.selectMyCases();
//                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
//                draftingTeamDecision.acceptAndDraftALetter();
//                draftingTeamDecision.uploadDraftResponse();
//                draft.clickContinueButton();
//                break;
//            default:
//                System.out.println(caseType
//                        + " is not defined within " + getClass().getSimpleName()
//                        + " class, " + getMethodName() + " method");
//                caseType = null;
//                assumeNotNull(caseType);
//        }
//    }

    @When("^I click the continue button on the correspondence answer screen$")
    public void clickContinueButtonOnCorrespondenceAnswerScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not selected radio buttons on this screen$")
    public void assertThatCorrespondenceCanBeAnsweredErrorMessageIsShown() {
        draft.assertCorrespondenceAnsweredErrorMessage();
    }

    @When("^I click the finish button on the case rejection screen$")
    public void clickFinishButtonOnCaseRejectionScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamNoRadioButton();
        draft.clickContinueButton();
        draft.clickFinishButton();
    }

    @Then("^an error message should be displayed as I have not entered a reason in the text box$")
    public void assertThatShouldBeAnsweredErrorMessageIsShown() {
        draft.assertShouldBeAnsweredErrorMessage();
    }

    @When("^I click the continue button on how do you intend to respond screen$")
    public void clickContinueButtonOnHowDoYouIntendToRespondScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
        draft.sleep(500);
        draft.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not selected a response on this screen$")
    public void assertThatHowDoYouIntendToRespondScreenIsShown() {
        draft.assertHowDoYouIntendToRespondErrorMessage();
    }

    @When("^I click the finish button on the summarise your call screen$")
    public void clickFinishButtonOnSummariseYourCallScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
        draft.clickPhoneReplyRadioButton();
        draft.clickContinueButton();
        draft.clickFinishButton();
    }

    @Then("^an error message should be displayed as I have not summarised the call$")
    public void assertThatSummariseYourCallErrorMessageIsShown() {
        draft.assertPleaseSummariseYourCallErrorMessage();
    }

    @When("^I click the continue button on the which is the primary draft document screen$")
    public void clickContinueButtonOnWhichIsPrimaryDraftDocScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
        draft.clickLetterReplyRadioButton();
        draft.clickContinueButton();
        draft.sleep(500);
        draft.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not added a primary draft document$")
    public void assertThatWhichIsThePrimaryDraftDocumentErrorMesasgeIsShown() {
        draft.assertWhichIsThePrimaryDraftDocumentErrorMessage();
    }

    @When("^I click the add button on the add documents screen$")
    public void clickAddButtonOnAddDocumentScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
        draft.clickLetterReplyRadioButton();
        draft.clickContinueButton();
        draft.clickAddDocumentsButton();
        addDocuments.clickAddButton();
    }

    @Then("^an error message should be displayed as I have not selected a document type and added a document$")
    public void assertThatAddDocumentErrorMessagesAreShown() {
        addDocuments.assertDocumentTypeIsRequiredErrorMessage();
        addDocuments.assertDocumentIsRequiredErrorMessage();
    }

    @When("^I click the continue button on the do you want QA this offline screen$")
    public void clickContinueButtonOnDoYouWantToQAOfflineScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
        draft.clickLetterReplyRadioButton();
        draft.clickContinueButton();
        draft.clickAddDocumentsButton();
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        addDocuments.clickAddButton();
        draft.clickContinueButton();
        draft.sleep(500);
        draft.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not selected whether the case should be QA offline or not$")
    public void assertThatQAOfflineErrorMessageIsShown() {
        draft.assertDoYouWantToQAThisOfflineErrorMessage();
    }

    @When("^I click the finish button on the who has done the offline QA screen$")
    public void clickFinishButtonOnWhoHasDoneTheOfflineQAScreen() {
        workstacks.clickAllocateToMeButton();
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
        draft.clickLetterReplyRadioButton();
        draft.clickContinueButton();
        draft.clickAddDocumentsButton();
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        addDocuments.clickAddButton();
        draft.clickContinueButton();
        qa.clickOfflineQAYesRadioButton();
        draft.clickContinueButton();
        draft.clickFinishButton();
    }

    @Then("^an error message should be displayed as I have not selected the user that did the offline QA$")
    public void assertThatWhoHasDoneTheOfflineQAErrorMessageIsShown() {
        draft.assertWhoHasDoneOfflineQAErrorMessage();
    }

    @When("^I select to reply by \"([^\"]*)\"$")
    public void iClickToAnswerBy(String method) {
        draft.clickAnsweredByMyTeamYesRadioButton();
        draft.clickContinueButton();
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
                System.out.println(method
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                method = null;
                assumeNotNull(method);
        }
        draft.clickContinueButton();
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
                pendingStep(qa + " is not defined within " + getMethodName());

        }
    }

    @When("^a case is not answered by my team$")
    public void aCaseIsNotAnsweredByMyTeam() {
        draft.clickAnsweredByMyTeamNoRadioButton();
        draft.clickContinueButton();
        draft.enterRejectionNotes();
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
                pendingStep(decision + " is not defined within " + getMethodName());

        }
        draft.clickContinueButton();
    }

    @When("^I \"([^\"]*)\" the call details$")
    public void iTheCallDetails(String callDetails) {
        switch (callDetails.toUpperCase()) {
            case "COMPLETE":
                draftingTeamDecision.enterPhoneCallSummaryNote();
                draft.clickFinishButton();
                break;
            case "DO NOT COMPLETE":
                draft.clickFinishButton();
                break;
            default:
                pendingStep(callDetails + " is not defined within " + getMethodName());

        }
    }

    @Then("^the case should be moved to the \"([^\"]*)\" stage$")
    public void assertCaseReturnedToStage(String stage) {
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                homepage.selectPerformanceProcessTeam();
                break;
            case "MARKUP":
                homepage.selectCentralDraftingTeam();
                break;
            case "INITIAL DRAFT":
                homepage.selectAnimalsInScienceTeam();
                break;
            case "QA RESPONSE":
                homepage.selectPerformanceProcessTeam();
                break;
            case "PRIVATE OFFICE":
                //Depends on the Team but mainly this one
                homepage.selectMinisterForLordsTeam();
                break;
            case "MINISTERIAL SIGN OFF":
                homepage.selectMinisterForLordsTeam();
                break;
            case "DISPATCH":
                homepage.selectPerformanceProcessTeam();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        teamqueue.assertCaseStage(stage);
    }
}
