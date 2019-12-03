package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.teamqueue.Teamqueue;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.draft.Draft;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;


public class GenericInputStepDefs extends Page {

    @Managed
    WebDriver driver;

    DataInput dataInput;

    Homepage homepage;

    Workstacks workstacks;

    RecordCorrespondentDetails recordCorrespondentDetails;

    MarkUpDecision markUpDecision;

    Topics topics;

    Draft draft;

    AddDocuments addDocuments;

    MinisterSignOff minister;

    Qa qa;

    PrivateOffice privateOffice;

    QAResponse qaResponse;

    Dispatch dispatch;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    Teamqueue teamqueue;

    @Then("^\"([^\"]*)\" dropdown defaults to \"([^\"]*)\"$")
    public void dropdownDefaultsTo(String dropdown, String expectedText) {
        switch (dropdown.toUpperCase()) {
            case "":
                break;
            default:
                pendingStep(expectedText + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the \"([^\"]*)\" link")
    public void clickLink(String name) {
        switch (name.toUpperCase()) {
            case "UPDATE":
                clickOn(updateLink);
                break;
            case "NEW":
                clickOn(newLink);
                break;
            case "DELETE":
                clickOn(deleteLink);
                break;
            case "VIEW":
                clickOn(viewLink);
                break;
            default:
                pendingStep(name + " is not defined within " + getMethodName());
        }
    }

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
//        waitABit(4000);
    }


    @Then("^The page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        assertTitle(title);
    }

    @Then("^I should see the \"([^\"]*)\" message$")
    public void iSeeTheMessage(String message) {
        switch (message.toUpperCase()) {
            case "DOCUMENT PENDING":
                break;
            case "DOCUMENT UPLOAD FAILED":
                break;
            case "NO DOCUMENTS":
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    @And("I upload a \"([^\"]*)\" document")
    public void IUploadADocument(String docType) {
        switch (docType.toUpperCase()) {
            case "ORIGINAL":
                addDocuments.addAOriginalDocument();
                break;
            case "DRAFT":
                addDocuments.addADraftDocument();
                break;
            case "FINAL":
                addDocuments.addAFinalDocument();
                break;
            default:
                pendingStep(docType + " is not defined within " + getMethodName());
        }
        clickOn(continueButton);
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

    @When("^I take the case up to the DISPATCH stage$")
    public void moveFromDataInputToDispatch() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(markUpDecision.policyResponseRadioButton);
        clickOn(markUpDecision.continueButton);
        clickOn(topics.addTopicButton);
        topics.enterRealTopic();
        clickOn(markUpDecision.addButton);
        clickOn(continueButton);
        clickOn(finishButton);
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        draft.acceptAndDraftALetter();
        addDocuments.addADraftDocument();
        qa.dontQAOffline();
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(qaResponse.QAAcceptRadioButton);
        System.out.println("Finished QA Response, returning to home page.");
        clickOn(continueButton);
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(privateOffice.privateOfficeAcceptRadioButton);
        clickOn(continueButton);
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(minister.ministerSignOffAcceptRadioButton);
        clickOn(continueButton);
    }

    @When("^I set the date to \"([^\"]*)\"$")
    public void iSetTheDate(String date) {
        switch (date.toUpperCase()) {
            case "TODAY":
                getCurrentDay();
                getCurrentMonth();
                getCurrentYear();
                break;
            case "TOMORROW":
                break;
            case "YESTERDAY":
                break;
            default:
                pendingStep(date + " is not defined within " + getMethodName());
        }
    }

    @When("I set a date of (\\d+) days time in the \"([^\"]*)\" field")
    public void iSetADateOfNDaysTimeInTheField(int days, String field) {
        String day = todayPlusNDaysGetDay(days);
        String month = todayPlusNDaysGetMonth(days);
        String year = todayPlusNDaysGetYear(days);
    }

    @When("^I set a deadline of -1 days$")
    public void setDeadlineToDaysDays(int days) {
        dataInput.setDateMinusOneDay();
    }

    @Then("^an error message is displayed$")
    public void anErrorMessageIsDisplayed() {
        errorMessageIsDisplayed();
    }

    @Then("^the file is downloaded$")
    public void theFileIsDownloaded() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I cannot exit the case$")
    public void iCannotClickToExitTheCase() {
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
            case "CANCEL":
                clickOn(cancelButton);
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
            case "DISPATCH":
                dispatch.rejectCaseWithoutReason();
                break;
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
            case "ALLOCATE":
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
                clickOn(finishButton);
                break;
            case "REASON FOR NO RESPONSE NEEDED":
                clickOn(finishButton);
                break;
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

    @Given("^I add (\\d+) \"([^\"]*)\" to a case$")
    public void iAddToACase(int number, String addition) throws Throwable {
        for (int i = 0; i < number; i++) {
            switch (addition.toUpperCase()) {
                case "TOPICS":
                    break;
                default:
                    pendingStep(addition + " is not defined within " + getMethodName());
            }
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
            case "DCU TRO":
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
                qaResponse.enterDraftDecision();
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

