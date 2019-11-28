package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.draft.Draft;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.teamqueue.Teamqueue;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DraftResponseStepDefs extends Page {

    @Steps(shared = true)
    Draft draft;

    Homepage homepage;

    Qa qa;

    Teamqueue teamqueue;

    AddDocuments addDocuments;

    Workstacks workstacks;

    @When("^I complete the Initial Draft stage$")
    public void initialDraftFullFlow() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        if (isElementDisplayed($("//span[contains(text(), 'DTEN')]"))) {
            draft.dtenAcceptAndDraftALetter();
            addDocuments.addADraftDocument();
            qa.dontQAOffline();
        } else {
            draft.acceptAndDraftALetter();
            addDocuments.addADraftDocument();
            qa.dontQAOffline();
        }
    }

    @When("^I complete the Initial Draft stage for \"([^\"]*)\"$")
    public void initialDraftFullFlowPerCaseType(String caseType) {
        switch(caseType.toUpperCase()) {
            case "DCU MIN" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draft.acceptAndDraftALetter();
                addDocuments.addADraftDocument();
                qa.dontQAOffline();
                break;
            case "DCU TRO" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draft.acceptAndDraftALetter();
                addDocuments.addADraftDocument();
                qa.dontQAOffline();
                break;
            case "DCU N10" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draft.dtenAcceptAndDraftALetter();
                addDocuments.addADraftDocument();
                qa.dontQAOffline();
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
        addDocuments.addADraftDocument();
        clickOn(draft.continueButton);
        draft.waitABit(500);
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
        addDocuments.addADraftDocument();
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
        clickOn(draft.downloadStandardLineLink);
    }

    @And("^I download the template for the case$")
    public void iDownloadTheTemplateForTheCase() {
        clickOn(draft.downloadTemplateLink);
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

    @When("^I select a case \"([^\"]*)\" be answered by my team$")
    public void iSelectACaseAnsweredByMyTeam(String decision) {
        getCaseId();
        switch (decision.toUpperCase()) {
            case "SHOULD":
                clickOn(draft.answeredByMyTeamYesRadioButton);
                break;
            case "SHOULD NOT":
                clickOn(draft.answeredByMyTeamNoRadioButton);
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
                draft.enterTextInSummariseCallTextbox();
                clickOn(draft.continueButton);
                break;
            case "DO NOT COMPLETE":
                clickOn(draft.continueButton);
                break;
            default:
                pendingStep(callDetails + " is not defined within " + getMethodName());
        }
    }

    @Then("^the \"([^\"]*)\" case should be moved to the \"([^\"]*)\" stage$")
    public void assertCaseTypeReturnedToStage(String caseType, String stage) {
        switch(caseType.toUpperCase()) {
            case "DCU MIN" :
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        clickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP":
                        clickOn(homepage.centralDraftingTeam);
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
                break;
            case "DCU TRO" :
                switch(stage.toUpperCase()){
                    case "DATA INPUT" :
                        clickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP" :
                        clickOn(homepage.centralDraftingTeam);
                        break;
                    case "INITIAL DRAFT" :
                        clickOn(homepage.animalsInScienceTeam);
                        break;
                    case "QA RESPONSE" :
                        clickOn(homepage.animalsInScienceTeam);
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
            case "DCU N10" :
                switch (stage.toUpperCase()) {
                case "DATA INPUT":
                    clickOn(homepage.transferN10Team);
                    break;
                case "MARKUP":
                    clickOn(homepage.transferN10Team);
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
                case "DISPATCH":
                    clickOn(homepage.transferN10Team);
                    break;
                default:
                    pendingStep(stage + " is not defined within " + getMethodName());
            }
            break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        teamqueue.assertCaseStage(stage);
    }

    @And("^I select \"([^\"]*)\" to choosing another Response Type$")
    public void iSelectToChoosingAnotherResponseType(String decision) {
        switch (decision.toUpperCase()) {
            case "YES":
                clickOn(draft.chooseAnotherResponseTypeYesButton);
                break;
            case "NO":
                clickOn(draft.chooseAnotherResponseTypeNoButton);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        clickOn(draft.finishButton);
    }

    @And("^I select \"([^\"]*)\" to QA offline$")
    public void iSelectToQAOffline(String decision) {
        switch (decision.toUpperCase()) {
            case "YES":
                clickOn(qa.offlineQaYesRadioButton);
                break;
            case "NO":
                clickOn(qa.offlineQaNoRadioButton);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        clickOn(continueButton);
    }

    @And("^I select \"([^\"]*)\" as the offline QA$")
    public void iSelectAsTheOfflineQA(String arg0) throws Throwable {
        qa.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
        clickOn(finishButton);
    }
}
