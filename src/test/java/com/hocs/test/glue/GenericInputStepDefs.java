package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.DCU_Workflow.DataInput;
import com.hocs.test.pages.DCU_Workflow.InitialDraft_RecordCorrespondentDetails;
import com.hocs.test.pages.DCU_Workflow.Dispatch;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import com.hocs.test.pages.DCU_Workflow.Markup_Decision;
import com.hocs.test.pages.DCU_Workflow.MinisterialSignOff;
import com.hocs.test.pages.DCU_Workflow.PrivateOfficeApproval;
import com.hocs.test.pages.DCU_Workflow.QAResponse;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.DCU_Workflow.InitialDraft;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenericInputStepDefs extends BasePage {

    DataInput dataInput;

    Homepage homepage;

    Workstacks workstacks;

    InitialDraft_RecordCorrespondentDetails initialDraftRecordCorrespondentDetails;

    Markup_Decision markupDecision;

    InitialDraft initialDraft;

    MinisterialSignOff minister;

    PrivateOfficeApproval privateOfficeApproval;

    QAResponse qaResponse;

    Dispatch dispatch;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    @When("I enter {string} in the {string} field")
    public void iEnterTextIntoTheNominatedField(String input, String element) {
        switch (element.toUpperCase()) {
            case "ADD A MEMBER OF PARLIAMENT":
                setSessionVariable("fullName").to(input);
                initialDraftRecordCorrespondentDetails.addAMemberOfParliamentCorrespondent(input);
                break;
            case "FULL NAME":
                setSessionVariable("fullName").to(input);
                initialDraftRecordCorrespondentDetails.enterCorrespondentFullName(input);
                break;
            case "REJECT REASON":
                setSessionVariable("fullName").to(input);
                dispatch.enterDispatchRejectionNotes();
                break;
            default:
                pendingStep(element + " is not defined within " + getMethodName());
        }
    }

    @When("I fill all mandatory fields on the {string} page with valid data")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "DATA INPUT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                break;
            case "CORRESPONDENT DETAILS":
                initialDraftRecordCorrespondentDetails.fillMandatoryCorrespondentFields();
                dataInput.clickAddButton();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
    }

    @Then("{string} error message is displayed")
    public void errorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "INVALID DATE":
                assertErrorMessageText("must be a date in the past");
                break;
            case "CORRESPONDENCE RECEIVED":
                assertErrorMessageText("When was the correspondence received? is required");
                break;
            case "CORRESPONDENCE SENT":
                assertErrorMessageText("When was the correspondence sent? is required");
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @When("I enter an invalid {string} date")
    public void iEnterAnInvalidDate(String dateField) {
        switch (dateField.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED":
                dataInput.invalidCorrespondenceReceivedDate();
                break;
            case "CORRESPONDENCE SENT":
                dataInput.invalidCorrespondenceSentDate();
                break;
            default:
                pendingStep(dateField + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message is displayed")
    public void anErrorMessageIsDisplayed() {
        errorMessageIsDisplayed();
    }

    @When("I click the {string} button on the {string} page")
    public void selectGenericButtonFromSpecificPage(String button, String page) {
        switch (page.toUpperCase()) {
            case "IS THE CORRESPONDENT AN MP":
                dataInput.getToIsCorrespondentAnMPPrerequisites();
                break;
            case "ADD MEMBER OF PARLIAMENT":
                dataInput.getToAddMemberOfParliamentPrerequisites();
                break;
            case "RECORD CORRESPONDENT DETAILS":
                dataInput.getToRecordCorrespondentDetailsPrerequisites();
                break;
            case "ADD A TOPIC":
                markupDecision.getToMarkupAddATopicScreenPrerequisites();
                break;
            case "ENTER A NEW TOPIC":
                markupDecision.getToMarkupEnterANewTopicScreenPrerequisites();
                break;
            case "CASE REJECTION":
                initialDraft.getToDraftCaseRejectionScreenPrerequisites();
                break;
            case "HOW DO YOU INTEND TO RESPOND":
                initialDraft.getToHowDoYouIntendToRespondScreenPrerequisites();
                break;
            case "SUMMARISE YOUR CALL":
                initialDraft.getToSummariseYouCallScreenPrerequisites();
                break;
            case "PRIMARY DRAFT DOCUMENT":
                initialDraft.getToPrimaryDraftDocumentScreenPrerequisites();
                break;
            case "ADD DOCUMENT":
                initialDraft.getToAddDocumentScreenPrerequisites();
                break;
            case "DO YOU WANT TO QA OFFLINE":
                initialDraft.getToDoYouWantToQAOfflineScreenPrerequisites();
                break;
            case "WHO HAS DONE THE QA OFFLINE":
                initialDraft.getToWhoDidTheQAOfflineScreenPrerequisites();
                break;
            case "QA RESPONSE FEEDBACK":
                qaResponse.getToQAResponseFeedbackScreenPrerequisites();
                break;
            case "CHANGE MINISTER":
                privateOfficeApproval.getToChangeMinisterScreenPrerequisites();
                break;
            case "PO FEEDBACK RESPONSE":
                privateOfficeApproval.getToPOFeedbackResponseScreenPrerequisites();
                break;
            case "MINISTERIAL SIGN OFF FEEDBACK RESPONSE":
                minister.getToMinisterFeedbackResponseScreenPrerequisites();
                break;
            case "UNABLE TO DISPATCH":
                dispatch.getToUnableToDispatchScreenPrerequisites();
                break;
            default:
                pendingStep(page + " is not defined within " + getMethodName());
        }

        switch (button.toUpperCase()) {
            case "CONTINUE":
                safeClickOn(continueButton);
                break;
            case "FINISH":
                safeClickOn(finishButton);
                break;
            case "ADD":
                safeClickOn(addButton);
                break;
            default:
                pendingStep(button + " is not defined within " + getMethodName());
        }
    }

    @When("I click the {string} button")
    public void clickTheButton(String button) {
        switch (button.toUpperCase()) {
            case "CONTINUE":
                safeClickOn(continueButton);
                break;
            case "FINISH":
                safeClickOn(finishButton);
                break;
            case "ADD":
                safeClickOn(addButton);
                break;
            case "NEXT":
                safeClickOn(nextButton);
                break;
            case "BACK":
                safeClickOn(backButton);
                break;
            case "ADD A TOPIC":
                safeClickOn(addTopicButton);
                break;
            case "VIEW TEAM":
                safeClickOn(teamManagement.viewTeamButton);
                break;
            case "SUBMIT":
                safeClickOn(unitManagement.submitButton);
                break;
            default:
                pendingStep(button + " is not defined within " + getMethodName());
        }
    }

    @When("I attempt to reject the {string} case without reason")
    public void iAttemptToRejectACaseWithoutReason(String caseType) {
        switch (caseType.toUpperCase()) {
            case "INITIAL DRAFT":
                clickRejectButton();
                while (isElementDisplayed(nextButton)) {
                    safeClickOn(nextButton);
                }
                break;
            case "QA RESPONSE":
                qaResponse.rejectCaseWithoutReason();
                break;
            case "DISPATCH":
                dispatch.rejectCaseWithoutReason();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I {string} the case")
    public void iActionTheCase(String action) {
        setCaseReferenceFromUnassignedCase();
        switch (action.toUpperCase()) {
            case "ACCEPT":
                safeClickOn(acceptButton);
                safeClickOn(continueButton);
                break;
            case "DISPATCH":
                dispatch.dispatchTheCase();
                break;
            case "REJECT":
                dispatch.selectDispatchRejectButton();
                clickContinueButton();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @But("I do not enter a {string}")
    public void iDoNotEnterA(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED DATE":
                dataInput.clearDateCorrespondenceReceived();
                break;
            case "CORRESPONDENCE SENT DATE":
                dataInput.clearDateCorrespondenceSent();
                break;
            case "OTHER GOVERNMENT DEPARTMENT":
            case "REASON FOR NO RESPONSE NEEDED":
            case "REASON FOR REJECTING TO DATA INPUT":
                safeClickOn(finishButton);
                break;
            default:
                pendingStep(fieldName + " is not defined within " + getMethodName());
        }
    }

    @Then("the case is completed")
    public void theCaseIsCompleted() {
        homepage.assertCaseIsCompleteViaSearch();
    }

    @Then("{string} link is displayed")
    public void linkIsDisplayed(String linkText) {
        switch (linkText.toUpperCase()) {
            case "ADD A CORRESPONDENT":
                dataInput.addACorrespondentLinkIsDisplayed();
                break;
            default:
                pendingStep(linkText + " is not defined within " + getMethodName());
        }
    }

    @And("I {string} the rejection note")
    public void iTheRejectionNote(String rejection) {
        switch (rejection.toUpperCase()) {
            case "COMPLETE":
                enterRejectionNotes();
                safeClickOn(finishButton);
                break;
            case "DO NOT COMPLETE":
                safeClickOn(finishButton);
                break;
            default:
                pendingStep(rejection + " is not defined within " + getMethodName());
        }
    }

    @When("I click the add button when creating a case note")
    public void userDoesNotEnterTextIntoTheCaseNoteTextBox() {
        safeClickOn(workstacks.caseTimelineTab);
        safeClickOn(workstacks.addCaseNoteButton);
        safeClickOn(workstacks.addButton);
    }

    @Then("the {string} case should be moved to the {string} stage")
    public void assertCaseTypeReturnedToStage(String caseType, String stage) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                    case "DISPATCH":
                        safeClickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP":
                        safeClickOn(homepage.centralDraftingTeam);
                        break;
                    case "INITIAL DRAFT":
                    case "QA RESPONSE":
                        safeClickOn(homepage.animalsInScienceTeam);
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                    case "MINISTERIAL SIGN OFF":
                        safeClickOn(homepage.ministerForLordsTeam);
                        break;
                    case "COPY TO NUMBER 10":
                        safeClickOn(homepage.transferN10Team);
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        safeClickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP":
                        safeClickOn(homepage.centralDraftingTeam);
                        break;
                    case "INITIAL DRAFT":
                    case "QA RESPONSE":
                    case "DISPATCH":
                        safeClickOn(homepage.animalsInScienceTeam);
                        break;
                    case "COPY TO NUMBER 10":
                        safeClickOn(homepage.transferN10Team);
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "DTEN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                    case "MARKUP":
                    case "DISPATCH":
                        safeClickOn(homepage.transferN10Team);
                        break;
                    case "INITIAL DRAFT":
                    case "QA RESPONSE":
                        safeClickOn(homepage.animalsInScienceTeam);
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        safeClickOn(homepage.ministerForLordsTeam);
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        workstacks.assertCaseStage(stage);
    }

    @And("I reject the case at the {string} stage")
    public void iRejectTheCaseAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "INITIAL DRAFT":
                safeClickOn(initialDraft.answeredByMyTeamNoRadioButton);
                safeClickOn(initialDraft.continueButton);
                initialDraft.enterRejectionNotes();
                safeClickOn(initialDraft.finishButton);
                break;
            case "QA RESPONSE":
                safeClickOn(qaResponse.QARejectRadioButton);
                safeClickOn(qaResponse.continueButton);
                qaResponse.enterQARejectionNote();
                safeClickOn(qaResponse.finishButton);
                break;
            case "PRIVATE OFFICE APPROVAL":
                safeClickOn(privateOfficeApproval.privateOfficeRejectRadioButton);
                safeClickOn(privateOfficeApproval.continueButton);
                privateOfficeApproval.enterPORejectNotes();
                safeClickOn(privateOfficeApproval.finishButton);
                break;
            case "MINISTERIAL SIGN OFF":
                safeClickOn(minister.ministerSignOffRejectRadioButton);
                safeClickOn(minister.continueButton);
                minister.enterMinisterRejectionNote();
                safeClickOn(minister.continueButton);
                break;
            case "DISPATCH":
                safeClickOn(dispatch.dispatchRejectRadioButton);
                safeClickOn(continueButton);
                dispatch.enterDispatchRejectionNotes();
                safeClickOn(finishButton);
                break;
            case "MARKUP":
                markupDecision.selectRejectToDataInput();
                safeClickOn(continueButton);
                markupDecision.enterRejectToDataInputReasonIntoTextBox();
                safeClickOn(finishButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }

    }
}

