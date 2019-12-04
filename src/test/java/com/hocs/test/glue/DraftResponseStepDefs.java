package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.draft.Draft;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DraftResponseStepDefs extends Page {

    @Steps(shared = true)
    Draft draft;

    Homepage homepage;

    Qa qa;

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
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draft.acceptAndDraftALetter();
                addDocuments.addADraftDocument();
                qa.dontQAOffline();
                break;
            case "DCU TRO":
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                draft.acceptAndDraftALetter();
                addDocuments.addADraftDocument();
                qa.dontQAOffline();
                break;
            case "DCU N10":
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

    @Then("^an error message should be displayed as I have not selected whether the case should be QA offline or not$")
    public void assertThatQAOfflineErrorMessageIsShown() {
        draft.assertDoYouWantToQAThisOfflineErrorMessage();
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

    @Then("^an error message appears instructing me to add rejection reasons$")
    public void anErrorMessageAppearsInstructingMeToAddRejectionReasons() {
        draft.assertEnterRejectionReasonsError();
    }

    @Then("^I see an error message instructing me to enter call notes$")
    public void iSeeAnErrorMessageInstructingMeToEnterCallNotes() {
        draft.assertEnterCallNotesError();
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
