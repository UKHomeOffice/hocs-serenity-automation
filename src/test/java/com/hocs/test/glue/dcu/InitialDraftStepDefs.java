package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.dcu.InitialDraft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InitialDraftStepDefs extends BasePage {

    InitialDraft initialDraft;

    Documents documents;

    SummaryTab summaryTab;

    CaseView caseView;

    @When("I select to reply by {string}")
    public void iClickToAnswerBy(String method) {
        switch (method.toUpperCase()) {
            case "EMAIL":
                safeClickOn(initialDraft.emailReplyRadioButton);
                break;
            case "PHONE":
                safeClickOn(initialDraft.phoneReplyRadioButton);
                break;
            case "POST":
                safeClickOn(initialDraft.letterReplyRadioButton);
                break;
            default:
                pendingStep(method + " is not defined within " + getMethodName());
        }
        jsClickOn(continueButton);
    }

    @When("I select a case {string} be answered by my team")
    public void iSelectACaseAnsweredByMyTeam(String decision) {
        switch (decision.toUpperCase()) {
            case "SHOULD":
                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                break;
            case "SHOULD NOT":
                safeClickOn(initialDraft.answeredByMyTeamNoRadioButton);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        safeClickOn(initialDraft.continueButton);
    }

    @When("I complete the call details")
    public void iTheCallDetails() {
        initialDraft.enterCallSummary();
        safeClickOn(continueButton);
    }

    @And("I select {string} to choosing another Response Type")
    public void iSelectToChoosingAnotherResponseType(String yesNo) {
        initialDraft.selectIfAnotherResponseTypeRequired(yesNo);
        safeClickOn(finishButton);
    }

    @And("I record who on my Team completed the offline QA Approval")
    public void iRecordWhoOnMyTeamCompletedTheOfflineQAApproval() {
        initialDraft.selectAOfflineQAIndividual();
        clickTheButton("Finish");
    }

    @And("I select the {string} document as the primary draft")
    public void iSelectTheDocumentAsThePrimaryDraft(String document) {
        documents.selectPrimaryDraft(sessionVariableCalled(document));
    }

    @And("the case should be assigned to the DCU draft team created in Management UI")
    public void theCaseShouldBeAssignedToTheDCUDraftTeamCreatedInMUI() {
        summaryTab.selectSummaryTab();
        summaryTab.assertAllocatedTeam(sessionVariableCalled("draftingTeamName"));
    }

    @And("the case should (still be owned by)(be returned to) the drafting team")
    public void theCaseShouldStillBeOwnedByTheDraftingTeam() {
        openOrCloseAccordionSection("Markup");
        String draftingTeam = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Initial Draft Team").get(0);
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(draftingTeam, "Team");
    }

    @And("I select that the QA Process should be completed on DECS")
    public void iSelectThatTheQAProcessShouldBeCompletedOnDECS() {
        initialDraft.selectQAOfflineDecision("No");
        safeClickOn(continueButton);
    }

    @And("I select that the QA Process has been completed offline")
    public void iSelectThatTheQAProcessHasBeenCompletedOffline() {
        initialDraft.selectQAOfflineDecision("Yes");
        safeClickOn(continueButton);
    }

    @And("the case should now be owned be the correct Private Office team")
    public void theCaseShouldNowBeOwnedBeThePrivateOfficeTeam() {
        openOrCloseAccordionSection("Markup");
        String privateOfficeTeam = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Private Office Team").get(0);
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(privateOfficeTeam, "Team");
    }

    @And("I reject the case at the Initial Draft stage")
    public void iRejectTheCaseAtTheInitialDraftStage() {
        initialDraft.selectIfCaseCanBeAnsweredByTeam("No");
        safeClickOn(continueButton);
        initialDraft.enterReasonTeamCannotAnswer();
        safeClickOn(finishButton);
    }

    @Then("the document added through MUI should be displayed in the list of available standard line documents")
    public void theDocumentAddedThroughMUIShouldBeDisplayedInTheListOfAvailableStandardLineDocuments() {
        initialDraft.assertStandardLineDocumentIsVisible();
    }
}
