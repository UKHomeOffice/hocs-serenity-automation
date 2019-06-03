package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.draft.Draft;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GenericInputStepDefs extends Page {

    @Managed
    WebDriver driver;

    DataInput dataInput;

    Homepage homepage;

    CreateCase createCase;

    DraftingTeamDecision draftingTeamDecision;

    SuccessfulCaseCreation successfulCaseCreation;

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
            case "FULL NAME" :
                setSessionVariable("fullName").to(input);
                recordCorrespondentDetails.enterCorrespondentFullName(input);
                break;
            case "REJECT REASON" :
                setSessionVariable("fullname").to(input);
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
            case "CORRESPONDENT DETAILS" :
                recordCorrespondentDetails.fillMandatoryCorrespondentFields();
                dataInput.clickAddButton();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
        waitABit(4000);
    }


    @Then("^The page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        assertTitle(title);
    }

    @Then("^I should send the string of the first child to the console$")
    public void sendStageToConsole() {
        WebElement caseReferenceStage = driver.findElement(
                By.xpath("//a[text()='MIN/0120171/19']/../following-sibling::td[1]"));
        System.out.println(caseReferenceStage);
        String thisStage = caseReferenceStage.getText();
        System.out.println("The Stage is " + thisStage);
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

    @And("^I am at the \"([^\"]*)\" stage$")
    public void iAmAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                break;
            case "MARKUP":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                successfulCaseCreation.getCaseReference();
                clickOn(workstacks.allocateToMeButton);
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                clickOn(dataInput.emailOriginalChannelRadioButton);
                clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
                clickOn(dataInput.continueButton);
                dataInput.sleep(500);
                clickOn(dataInput.addCorrespondentLink);
                clickOn(dataInput.correspondentMemberNoRadioButton);
                clickOn(dataInput.continueButton);
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                clickOn(dataInput.addButton);
                clickOn(dataInput.finishButton);
                clickOn(homepage.centralDraftingTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "DRAFT":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                successfulCaseCreation.getCaseReference();
                clickOn(workstacks.allocateToMeButton);
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                clickOn(dataInput.emailOriginalChannelRadioButton);
                clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
                clickOn(dataInput.continueButton);
                waitABit(500);
                clickOn(dataInput.addCorrespondentLink);
                clickOn(dataInput.correspondentMemberNoRadioButton);
                clickOn(dataInput.continueButton);
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                clickOn(dataInput.addButton);
                clickOn(dataInput.finishButton);
                clickOn(homepage.centralDraftingTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(markUpDecision.policyResponseRadioButton);
                clickOn(markUpDecision.continueButton);
                waitABit(500);
                clickOn(markUpDecision.addATopicButton);
                topics.enterRealTopic();
                clickOn(topics.addButton);
                clickOn(markUpDecision.continueButton);
                clickOn(markUpDecision.finishButton);
                clickOn(homepage.animalsInScienceTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "QA":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                successfulCaseCreation.getCaseReference();
                clickOn(workstacks.allocateToMeButton);
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                clickOn(dataInput.emailOriginalChannelRadioButton);
                clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
                clickOn(dataInput.continueButton);
                waitABit(500);
                clickOn(dataInput.addCorrespondentLink);
                clickOn(dataInput.correspondentMemberNoRadioButton);
                clickOn(dataInput.continueButton);
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                clickOn(dataInput.addButton);
                clickOn(dataInput.finishButton);
                clickOn(homepage.centralDraftingTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(markUpDecision.policyResponseRadioButton);
                clickOn(markUpDecision.continueButton);
                markUpDecision.sleep(500);
                clickOn(markUpDecision.addATopicButton);
                topics.enterRealTopic();
                clickOn(topics.addButton);
                clickOn(markUpDecision.continueButton);
                clickOn(markUpDecision.finishButton);
                clickOn(homepage.animalsInScienceTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(draft.answeredByMyTeamYesRadioButton);
                clickOn(draft.continueButton);
                clickOn(draft.letterReplyRadioButton);
                clickOn(draft.continueButton);
                clickOn(draft.draftStageAddDocumentsButton);
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                clickOn(addDocuments.addButton);
                clickOn(draft.continueButton);
                clickOn(qa.offlineQaNoRadioButton);
                clickOn(continueButton);
                clickOn(homepage.animalsInScienceTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "PO SIGNOFF":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                successfulCaseCreation.getCaseReference();
                clickOn(workstacks.allocateToMeButton);
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                clickOn(dataInput.emailOriginalChannelRadioButton);
                clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
                clickOn(dataInput.continueButton);
                waitABit(500);
                clickOn(dataInput.addCorrespondentLink);
                clickOn(dataInput.correspondentMemberNoRadioButton);
                clickOn(dataInput.continueButton);
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                clickOn(dataInput.addButton);
                clickOn(dataInput.finishButton);
                clickOn(homepage.centralDraftingTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(markUpDecision.policyResponseRadioButton);
                clickOn(markUpDecision.continueButton);
                markUpDecision.sleep(500);
                clickOn(markUpDecision.addATopicButton);
                topics.enterRealTopic();
                clickOn(topics.addButton);
                clickOn(markUpDecision.continueButton);
                clickOn(markUpDecision.finishButton);
                clickOn(homepage.animalsInScienceTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
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
                draft.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
                clickOn(draft.finishButton);
                clickOn(homepage.performanceProcessTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "MINISTER SIGNOFF":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                successfulCaseCreation.getCaseReference();
                clickOn(workstacks.allocateToMeButton);
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                clickOn(dataInput.emailOriginalChannelRadioButton);
                clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
                clickOn(dataInput.continueButton);
                waitABit(500);
                clickOn(dataInput.addCorrespondentLink);
                clickOn(dataInput.correspondentMemberNoRadioButton);
                clickOn(dataInput.continueButton);
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                clickOn(dataInput.addButton);
                clickOn(dataInput.finishButton);
                clickOn(homepage.centralDraftingTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(markUpDecision.policyResponseRadioButton);
                clickOn(markUpDecision.continueButton);
                waitABit(500);
                clickOn(markUpDecision.addATopicButton);
                topics.enterRealTopic();
                clickOn(topics.addButton);
                clickOn(markUpDecision.continueButton);
                clickOn(markUpDecision.finishButton);
                clickOn(homepage.animalsInScienceTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(draft.answeredByMyTeamYesRadioButton);
                clickOn(continueButton);
                clickOn(draft.letterReplyRadioButton);
                clickOn(continueButton);
                clickOn(draft.draftStageAddDocumentsButton);
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                clickOn(addDocuments.addButton);
                clickOn(continueButton);
                clickOn(qa.offlineQaYesRadioButton);
                clickOn(continueButton);
                draft.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
                clickOn(draft.finishButton);
                clickOn(homepage.performanceProcessTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(privateOffice.privateOfficeAcceptRadioButton);
                clickOn(privateOffice.continueButton);
                clickOn(homepage.ministerForLordsTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "DISPATCH":
                clickOn(homepage.createSingleCase);
                createCase.createDCUMinSingleCase();
                clickOn(successfulCaseCreation.newCaseReference);
                successfulCaseCreation.getCaseReference();
                clickOn(workstacks.allocateToMeButton);
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                clickOn(dataInput.emailOriginalChannelRadioButton);
                clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
                clickOn(dataInput.continueButton);
                waitABit(500);
                clickOn(dataInput.addCorrespondentLink);
                clickOn(dataInput.correspondentMemberNoRadioButton);
                clickOn(dataInput.continueButton);
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                clickOn(dataInput.addButton);
                clickOn(finishButton);
                clickOn(homepage.centralDraftingTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(markUpDecision.policyResponseRadioButton);
                clickOn(markUpDecision.continueButton);
                markUpDecision.sleep(500);
                clickOn(markUpDecision.addATopicButton);
                topics.enterRealTopic();
                clickOn(topics.addButton);
                clickOn(markUpDecision.continueButton);
                clickOn(markUpDecision.finishButton);
                clickOn(homepage.animalsInScienceTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(draft.answeredByMyTeamYesRadioButton);
                clickOn(continueButton);
                clickOn(draft.letterReplyRadioButton);
                clickOn(continueButton);
                clickOn(draft.draftStageAddDocumentsButton);
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                clickOn(addDocuments.addButton);
                clickOn(draft.continueButton);
                clickOn(qa.offlineQaYesRadioButton);
                clickOn(draft.continueButton);
                draft.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
                clickOn(draft.finishButton);
                clickOn(homepage.performanceProcessTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(privateOffice.privateOfficeAcceptRadioButton);
                clickOn(continueButton);
                clickOn(homepage.ministerForLordsTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                clickOn(workstacks.allocateToMeButton);
                clickOn(minister.minsterSignOffAcceptRadioButton);
                clickOn(continueButton);
                clickOn(homepage.performanceProcessTeam);
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
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
        waitABit(1000);
        clickOn(markUpDecision.addButton);
        waitABit(1000);
        clickOn(continueButton);
        waitABit(1000);
        clickOn(finishButton);
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        draftingTeamDecision.acceptAndDraftALetter();
        draftingTeamDecision.uploadDraftResponse();
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
        clickOn(minister.minsterSignOffAcceptRadioButton);
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
        switch(page.toUpperCase()) {
            case "IS THE CORRESPONDENT AN MP" :
                dataInput.getToIsCorrespondentAnMPPrerequisites();
                break;
            case "ADD MEMBER OF PARLIAMENT" :
                dataInput.getToAddMemberOfParliamentPrerequisites();
                break;
//            case "PRIMARY CORRESPONDENT" :
//                break;
            case "RECORD CORRESPONDENT DETAILS" :
                dataInput.getToRecordCorrespondentDetailsPrerequisites();
                break;
            case "ADD A TOPIC" :
                markUpDecision.getToMarkupAddATopicScreenPrerequisites();
                break;
            case "ENTER A NEW TOPIC" :
                markUpDecision.getToMarkupEnterANewTopicScreenPrerequisites();
                break;
            case "CASE REJECTION" :
                draft.getToDraftCaseRejectionScreenPrerequisites();
                break;
            case "HOW DO YOU INTEND TO RESPOND" :
                draft.getToHowDoYouIntendToRespondScreenPrerequisites();
                break;
            case "SUMMARISE YOUR CALL" :
                draft.getToSummariseYouCallScreenPrerequisites();
                break;
            case "PRIMARY DRAFT DOCUMENT" :
                draft.getToPrimaryDraftDocumentScreenPrerequisites();
                break;
            case "ADD DOCUMENT" :
                draft.getToAddDocumentScreenPrerequisites();
                break;
            case "DO YOU WANT TO QA OFFLINE" :
                draft.getToDoYouWantToQAOfflineScreenPrerequisites();
                break;
            case "WHO HAS DONE THE QA OFFLINE" :
                draft.getToWhoDidTheQAOfflineScreenPrerequisites();
                break;
            case "QA RESPONSE FEEDBACK" :
                qaResponse.getToQAResponseFeedbackScreenPrerequisites();
                break;
            case "CHANGE MINISTER" :
                privateOffice.getToChangeMinisterScreenPrerequisites();
                break;
            case "PO FEEDBACK RESPONSE" :
                privateOffice.getToPOFeedbackResponseScreenPrerequisites();
                break;
            case "MINISTER SIGN OFF FEEDBACK RESPONSE" :
                minister.getToMinisterFeedbackResponseScreenPrerequisites();
                break;
            case "UNABLE TO DISPATCH" :
                dispatch.getToUnableToDispatchScreenPrerequisites();
                break;
            default:
                pendingStep(page + " is not defined within " + getMethodName());
        }

        switch (button.toUpperCase()) {
            case "CONTINUE" :
                clickOn(continueButton);
                break;
            case "FINISH" :
                clickOn(finishButton);
                break;
            case "ADD" :
                clickOn(addButton);
                break;
            default:
                pendingStep(button + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the \"([^\"]*)\" button$")
    public void clickTheButton(String button) {
        switch(button.toUpperCase()) {
            case "CONTINUE" :
                clickOn(continueButton);
                break;
            case "FINISH" :
                clickOn(finishButton);
                break;
            case "ADD" :
                clickOn(addButton);
                break;
            case "NEXT" :
                clickOn(nextButton);
                break;
            case "CANCEL" :
                clickOn(cancelButton);
                break;
            case "ADD A TOPIC" :
                clickOn(addTopicButton);
                break;
            default:
                pendingStep(button + " is not defined within " + getMethodName());
        }
    }

    @When("^I attempt to reject the \"([^\"]*)\" case without reason$")
    public void iAttemptToRejectACaseWithoutReason(String caseType) {
        switch(caseType.toUpperCase()){
            case "INITIAL DRAFT" :
                clickRejectButton();
                while (isElementDisplayed(nextButton)) {
                    clickOn(nextButton);
                }
                break;
            case "DISPATCH" :
                dispatch.rejectCaseWithoutReason();
                break;
        }
    }

    @When("^I \"([^\"]*)\" the case$")
    public void iActionTheCase(String action) {
        setSessionVariable("caseId").to(getCaseId());
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
            case "REASON FOR NO RESPONSE NEEDED" :
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
}

