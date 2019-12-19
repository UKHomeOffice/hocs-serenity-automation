package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.draft.Draft;

import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GenericInputStepDefs extends Page {

    DataInput dataInput;

    Homepage homepage;

    Workstacks workstacks;

    RecordCorrespondentDetails recordCorrespondentDetails;

    MarkUpDecision markUpDecision;

    Draft draft;

    MinisterSignOff minister;

    PrivateOffice privateOffice;

    QAResponse qaResponse;

    Dispatch dispatch;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    @When("^I enter \"([^\"]*)\" in the \"([^\"]*)\" field")
    public void iEnterTextIntoTheNominatedField(String input, String element) {
        switch (element.toUpperCase()) {
            case "ADD A MEMBER OF PARLIAMENT":
                setSessionVariable("fullName").to(input);
                recordCorrespondentDetails.addAMemberOfParliamentCorrespondent(input);
                break;
            case "FULL NAME":
                setSessionVariable("fullName").to(input);
                recordCorrespondentDetails.enterCorrespondentFullName(input);
                break;
            case "REJECT REASON":
                setSessionVariable("fullName").to(input);
                dispatch.enterDispatchRejectionNotes();
                break;
            default:
                pendingStep(element + " is not defined within " + getMethodName());
        }
    }

    @When("^I fill all mandatory fields on the \"([^\"]*)\" page with valid data$")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "DATA INPUT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                break;
            case "CORRESPONDENT DETAILS":
                recordCorrespondentDetails.fillMandatoryCorrespondentFields();
                dataInput.clickAddButton();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
    }

    @Then("^\"([^\"]*)\" error message is displayed$")
    public void errorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "INVALID DATE":
                assertErrorMessageText("");
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

    @When("^I enter an invalid \"([^\"]*)\" date$")
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

    @Then("^an error message is displayed$")
    public void anErrorMessageIsDisplayed() {
        errorMessageIsDisplayed();
    }

    @When("^I click the \"([^\"]*)\" button on the \"([^\"]*)\" page$")
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
                markUpDecision.getToMarkupAddATopicScreenPrerequisites();
                break;
            case "ENTER A NEW TOPIC":
                markUpDecision.getToMarkupEnterANewTopicScreenPrerequisites();
                break;
            case "CASE REJECTION":
                draft.getToDraftCaseRejectionScreenPrerequisites();
                break;
            case "HOW DO YOU INTEND TO RESPOND":
                draft.getToHowDoYouIntendToRespondScreenPrerequisites();
                break;
            case "SUMMARISE YOUR CALL":
                draft.getToSummariseYouCallScreenPrerequisites();
                break;
            case "PRIMARY DRAFT DOCUMENT":
                draft.getToPrimaryDraftDocumentScreenPrerequisites();
                break;
            case "ADD DOCUMENT":
                draft.getToAddDocumentScreenPrerequisites();
                break;
            case "DO YOU WANT TO QA OFFLINE":
                draft.getToDoYouWantToQAOfflineScreenPrerequisites();
                break;
            case "WHO HAS DONE THE QA OFFLINE":
                draft.getToWhoDidTheQAOfflineScreenPrerequisites();
                break;
            case "QA RESPONSE FEEDBACK":
                qaResponse.getToQAResponseFeedbackScreenPrerequisites();
                break;
            case "CHANGE MINISTER":
                privateOffice.getToChangeMinisterScreenPrerequisites();
                break;
            case "PO FEEDBACK RESPONSE":
                privateOffice.getToPOFeedbackResponseScreenPrerequisites();
                break;
            case "MINISTER SIGN OFF FEEDBACK RESPONSE":
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
                clickOn(continueButton);
                break;
            case "FINISH":
                clickOn(finishButton);
                break;
            case "ADD":
                clickOn(addButton);
                break;
            default:
                pendingStep(button + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the \"([^\"]*)\" button$")
    public void clickTheButton(String button) {
        switch (button.toUpperCase()) {
            case "CONTINUE":
                clickOn(continueButton);
                break;
            case "FINISH":
                clickOn(finishButton);
                break;
            case "ADD":
                clickOn(addButton);
                break;
            case "NEXT":
                clickOn(nextButton);
                break;
            case "BACK":
                clickOn(backButton);
                break;
            case "ADD A TOPIC":
                clickOn(addTopicButton);
                break;
            case "VIEW TEAM":
                clickOn(teamManagement.viewTeamButton);
                break;
            case "SUBMIT":
                clickOn(unitManagement.submitButton);
                break;
            default:
                pendingStep(button + " is not defined within " + getMethodName());
        }
    }

    @When("^I attempt to reject the \"([^\"]*)\" case without reason$")
    public void iAttemptToRejectACaseWithoutReason(String caseType) {
        switch (caseType.toUpperCase()) {
            case "INITIAL DRAFT":
                clickRejectButton();
                while (isElementDisplayed(nextButton)) {
                    clickOn(nextButton);
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

    @When("^I \"([^\"]*)\" the case$")
    public void iActionTheCase(String action) {
        setSessionVariable("caseReference").to(getCaseId());
        switch (action.toUpperCase()) {
            case "ACCEPT":
                clickOn(acceptButton);
                clickOn(continueButton);
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

    @But("^I do not enter (?:a|an) \"([^\"]*)\"$")
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
                clickOn(finishButton);
                break;
            default:
                pendingStep(fieldName + " is not defined within " + getMethodName());
        }
    }

    @Then("^the case is completed$")
    public void theCaseIsCompleted() {
        homepage.assertCaseIsCompleteViaSearch();
    }

    @Then("^\"([^\"]*)\" link is displayed$")
    public void linkIsDisplayed(String linkText) {
        switch (linkText.toUpperCase()) {
            case "ADD A CORRESPONDENT":
                dataInput.addACorrespondentLinkIsDisplayed();
                break;
            default:
                pendingStep(linkText + " is not defined within " + getMethodName());
        }
    }

    @And("^I \"([^\"]*)\" the rejection note$")
    public void iTheRejectionNote(String rejection) {
        switch (rejection.toUpperCase()) {
            case "COMPLETE":
                enterRejectionNotes();
                clickOn(finishButton);
                break;
            case "DO NOT COMPLETE":
                clickOn(finishButton);
                break;
            default:
                pendingStep(rejection + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the add button when creating a case note$")
    public void userDoesNotEnterTextIntoTheCaseNoteTextBox() {
        clickOn(workstacks.caseTimelineTab);
        clickOn(workstacks.addCaseNoteButton);
        clickOn(workstacks.addButton);
    }

    @Then("^the \"([^\"]*)\" case should be moved to the \"([^\"]*)\" stage$")
    public void assertCaseTypeReturnedToStage(String caseType, String stage) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                    case "DISPATCH":
                        clickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP":
                        clickOn(homepage.centralDraftingTeam);
                        break;
                    case "INITIAL DRAFT":
                    case "QA RESPONSE":
                        clickOn(homepage.animalsInScienceTeam);
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                    case "MINISTERIAL SIGN OFF":
                        clickOn(homepage.ministerForLordsTeam);
                        break;
                    case "COPY TO NUMBER 10":
                        clickOn(homepage.transferN10Team);
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "DCU TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        clickOn(homepage.performanceProcessTeam);
                        break;
                    case "MARKUP":
                        clickOn(homepage.centralDraftingTeam);
                        break;
                    case "INITIAL DRAFT":
                    case "QA RESPONSE":
                    case "DISPATCH":
                        clickOn(homepage.animalsInScienceTeam);
                        break;
                    case "COPY TO NUMBER 10":
                        clickOn(homepage.transferN10Team);
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "DCU N10":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                    case "MARKUP":
                    case "DISPATCH":
                        clickOn(homepage.transferN10Team);
                        break;
                    case "INITIAL DRAFT":
                    case "QA RESPONSE":
                        clickOn(homepage.animalsInScienceTeam);
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        clickOn(homepage.ministerForLordsTeam);
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

    @And("^I reject the case at the \"([^\"]*)\" stage$")
    public void iRejectTheCaseAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "INITIAL DRAFT":
                clickOn(draft.answeredByMyTeamNoRadioButton);
                clickOn(draft.continueButton);
                draft.enterRejectionNotes();
                clickOn(draft.finishButton);
                break;
            case "QA RESPONSE":
                clickOn(qaResponse.QARejectRadioButton);
                clickOn(qaResponse.continueButton);
                qaResponse.enterQARejectionNote();
                clickOn(qaResponse.finishButton);
                break;
            case "PRIVATE OFFICE APPROVAL":
                clickOn(privateOffice.privateOfficeRejectRadioButton);
                clickOn(privateOffice.continueButton);
                privateOffice.enterPORejectNotes();
                clickOn(privateOffice.finishButton);
                break;
            case "MINISTERIAL SIGN OFF":
                clickOn(minister.ministerSignOffRejectRadioButton);
                clickOn(minister.continueButton);
                minister.enterMinisterRejectionNote();
                clickOn(minister.continueButton);
                break;
            case "DISPATCH":
                clickOn(dispatch.dispatchRejectRadioButton);
                clickOn(continueButton);
                dispatch.enterDispatchRejectionNotes();
                clickOn(finishButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }

    }
}

