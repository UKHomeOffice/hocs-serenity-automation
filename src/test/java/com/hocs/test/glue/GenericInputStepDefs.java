package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.TimelineTab;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.dcu.InitialDraft;

import com.hocs.test.pages.ukvi.Triage;
import config.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenericInputStepDefs extends BasePage {

    DataInput dataInput;

    Homepage homepage;

    Workstacks workstacks;

    Markup markup;

    InitialDraft initialDraft;

    MinisterialSignOff ministerialSignOff;

    PrivateOfficeApproval privateOfficeApproval;

    QAResponse qaResponse;

    Dispatch dispatch;

    AddCorrespondent addCorrespondent;

    SummaryTab summaryTab;

    Triage triage;

    TimelineTab timelineTab;

    @Then("the {string} page should be displayed")
    public void thePageShouldBeDisplayed(String pageTitle) {
        assertPageTitle(pageTitle);
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
    public void selectGenericButtonFromSpecificPage(String buttonLabel, String page) {
        switch (page.toUpperCase()) {
            case "IS THE CORRESPONDENT AN MP":
                dataInput.fillAllMandatoryCorrespondenceFields();
                dataInput.clickContinueButton();
                addCorrespondent.selectToAddACorrespondent();
                break;
            case "ADD MEMBER OF PARLIAMENT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                dataInput.clickContinueButton();
                addCorrespondent.selectToAddACorrespondent();
                addCorrespondent.selectCorrespondentIsMP();
                break;
            case "RECORD CORRESPONDENT DETAILS":
                dataInput.fillAllMandatoryCorrespondenceFields();
                dataInput.clickContinueButton();
                addCorrespondent.selectToAddACorrespondent();
                addCorrespondent.selectCorrespondentIsNotMP();
                break;
            case "ADD A TOPIC":
                markup.getToMarkupAddATopicScreenPrerequisites();
                break;
            case "ENTER A NEW TOPIC":
                markup.getToMarkupEnterANewTopicScreenPrerequisites();
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
                ministerialSignOff.getToMinisterFeedbackResponseScreenPrerequisites();
                break;
            case "UNABLE TO DISPATCH":
                dispatch.getToUnableToDispatchScreenPrerequisites();
                break;
            default:
                pendingStep(page + " is not defined within " + getMethodName());
        }
        clickTheButton(buttonLabel);
    }

    @When("I click the {string} link")
    public void clickTheLink(String link) {
        switch (link.toUpperCase()) {
            case "BACK":
                safeClickOn(backLink);
                break;
            case "ADD A CORRESPONDENT":
                addCorrespondent.selectToAddACorrespondent();
                break;
            case "SET ENQUIRY SUBJECT/REASON":
                safeClickOn(triage.setEnquiryHypertext);
                break;
            default:
                pendingStep(link + " is not defined within " + getMethodName());
        }
    }

    @When("I click the {string} button")
    public void iClickTheButton(String buttonLabel) {
        clickTheButton(buttonLabel);

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
                safeClickOn(finishButton);;
                break;
            default:
                pendingStep(fieldName + " is not defined within " + getMethodName());
        }
    }

    @Then("the case should be closed")
    public void theCaseShouldBeClosed() {
        homepage.assertCaseIsClosedViaSearch();
    }

    @Then("{string} link is displayed")
    public void linkIsDisplayed(String linkText) {
        switch (linkText.toUpperCase()) {
            case "ADD A CORRESPONDENT":
                addCorrespondent.assertAddACorrespondentLinkIsDisplayed();
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
                safeClickOn(finishButton);;
                break;
            case "DO NOT COMPLETE":
                safeClickOn(finishButton);;
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

    @Then("the case should be moved to the {string} stage")
    public void assertCaseTypeReturnedToStage(String stage) {
        String caseType = sessionVariableCalled("caseType");
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
                workstacks.assertCaseStage(stage);
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
                workstacks.assertCaseStage(stage);
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
                workstacks.assertCaseStage(stage);
                break;
            case "UKVI":
                homepage.getCurrentCase();
                summaryTab.selectSummaryTab();
                summaryTab.assertCaseStage(stage);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I reject the case at the {string} stage")
    public void iRejectTheCaseAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "INITIAL DRAFT":
                safeClickOn(initialDraft.answeredByMyTeamNoRadioButton);
                safeClickOn(initialDraft.continueButton);
                initialDraft.enterRejectionNotes();
                safeClickOn(finishButton);;
                break;
            case "QA RESPONSE":
                safeClickOn(qaResponse.QARejectRadioButton);
                safeClickOn(qaResponse.continueButton);
                qaResponse.enterQARejectionNote();
                safeClickOn(finishButton);;
                break;
            case "PRIVATE OFFICE APPROVAL":
                safeClickOn(privateOfficeApproval.privateOfficeRejectRadioButton);
                safeClickOn(privateOfficeApproval.continueButton);
                privateOfficeApproval.enterPORejectNotes();
                safeClickOn(finishButton);;
                break;
            case "MINISTERIAL SIGN OFF":
                safeClickOn(ministerialSignOff.ministerSignOffRejectRadioButton);
                safeClickOn(ministerialSignOff.continueButton);
                ministerialSignOff.enterMinisterRejectionNote();
                safeClickOn(ministerialSignOff.continueButton);
                break;
            case "DISPATCH":
                safeClickOn(dispatch.dispatchRejectRadioButton);
                safeClickOn(continueButton);
                dispatch.enterDispatchRejectionNotes();
                safeClickOn(finishButton);;
                break;
            case "MARKUP":
                markup.selectRejectToDataInput();
                safeClickOn(continueButton);
                markup.enterRejectToDataInputReasonIntoTextBox();
                safeClickOn(finishButton);;
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }

    }

    @Then("an error message should be displayed as I have not entered text in the Case Note text box")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotEnteredTextInTheCaseNoteTextbox() {
        workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @Then("I print the stage name")
    public void iPrintTheStageName() {
        homepage.getCurrentCase();
        workstacks.selectSummaryTab();
        workstacks.summaryPrintActiveStage();
    }

    @Then("the case should be allocated to {string}")
    public void caseShouldBeAllocatedTo(String allocatedUser) {
        int retest = 0;
        while (retest < 5) {
            try {
                summaryTab.assertCaseOwnerIs(Users.valueOf(allocatedUser));
                break;
            } catch (AssertionError a) {
                retest ++;
                safeClickOn(timelineTab.timelineTab);
                safeClickOn(summaryTab.summaryTab);
            }
            summaryTab.assertCaseOwnerIs(Users.valueOf(allocatedUser));
        }
    }
}

