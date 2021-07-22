package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.UnallocatedCaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.dcu.InitialDraft;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InitialDraftStepDefs extends BasePage {

    InitialDraft initialDraft;

    Dashboard dashboard;

    Documents documents;

    Workstacks workstacks;

    UnallocatedCaseView unallocatedCaseView;

    SummaryTab summaryTab;

    @When("I complete the Initial Draft stage")
    public void initialDraftFullFlowPerCaseType() {
        if (!initialDraft.answeredByMyTeamYesRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        String caseType = sessionVariableCalled("caseType");
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "TRO":
                initialDraft.acceptAndDraftALetter();
                documents.addADraftDocumentAtDraftStage();
                initialDraft.dontQAOffline();
                break;
            case "DTEN":
                initialDraft.dtenAcceptAndDraftALetter();
                documents.addADraftDocumentAtDraftStage();
                initialDraft.dontQAOffline();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not selected a document type and added a document")
    public void assertThatAddDocumentErrorMessagesAreShown() {
        documents.assertDocumentTypeIsRequiredErrorMessage();
        documents.assertDocumentIsRequiredErrorMessage();
    }

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

    @When("I {string} the call details")
    public void iTheCallDetails(String callDetails) {
        switch (callDetails.toUpperCase()) {
            case "COMPLETE":
                initialDraft.enterTextInSummariseCallTextbox();
                safeClickOn(initialDraft.continueButton);
                break;
            case "DO NOT COMPLETE":
                safeClickOn(initialDraft.continueButton);
                break;
            default:
                pendingStep(callDetails + " is not defined within " + getMethodName());
        }
    }

    @And("I select {string} to choosing another Response Type")
    public void iSelectToChoosingAnotherResponseType(String decision) {
        switch (decision.toUpperCase()) {
            case "YES":
                safeClickOn(initialDraft.chooseAnotherResponseTypeYesButton);
                break;
            case "NO":
                safeClickOn(initialDraft.chooseAnotherResponseTypeNoButton);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        safeClickOn(initialDraft.finishButton);
    }

    @And("I select {string} to QA offline")
    public void iSelectToQAOffline(String decision) {
        switch (decision.toUpperCase()) {
            case "YES":
                safeClickOn(initialDraft.offlineQaYesRadioButton);
                break;
            case "NO":
                safeClickOn(initialDraft.offlineQaNoRadioButton);
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        safeClickOn(continueButton);
    }

    @And("I select {string} as the offline QA")
    public void iSelectAsTheOfflineQA(String teamMember) {
        switch (teamMember.toUpperCase()) {
            case "CAMERON":
                initialDraft.selectOfflineQualityAssurer(User.CAMERON.getAllocationText());
                safeClickOn(finishButton);
                break;
            case "CASEY":
                initialDraft.selectOfflineQualityAssurer(User.CASEY.getAllocationText());
                safeClickOn(finishButton);
                break;
            case "DECS_USER":
                initialDraft.selectOfflineQualityAssurer(User.DECS_USER.getAllocationText());
                safeClickOn(finishButton);
                break;
            default:
                pendingStep(teamMember + " is not defined within " + getMethodName());
        }
    }

    @And("I select the {string} document as the primary draft")
    public void iSelectTheDocumentAsThePrimaryDraft(String document) {
        initialDraft.selectPrimaryDraft(sessionVariableCalled(document));
    }

    @And("the {string} document should be tagged as the primary draft")
    public void theDocumentShouldBeTaggedAsThePrimaryDraft(String document) {
        workstacks.goToCurrentCaseFromWorkstack();
        initialDraft.assertThatPrimaryDraftIs(sessionVariableCalled(document));
    }

    @And("the case should be assigned to the DCU draft team created in Management UI")
    public void theCaseShouldBeAssignedToTheDCUDraftTeamCreatedInMUI() {
        safeClickOn(summaryTab.summaryTab);
        summaryTab.assertAllocatedDCUTeam(sessionVariableCalled("draftingTeamName"));
    }
}
