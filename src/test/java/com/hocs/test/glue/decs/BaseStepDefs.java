package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.PeopleTab;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.TimelineTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.ukvi.Triage;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;

public class BaseStepDefs extends BasePage {

    DataInput dataInput;

    Dashboard dashboard;

    Workstacks workstacks;

    Markup markup;

    InitialDraft initialDraft;

    MinisterialSignOff ministerialSignOff;

    PrivateOfficeApproval privateOfficeApproval;

    QAResponse qaResponse;

    Dispatch dispatch;

    Correspondents correspondents;

    SummaryTab summaryTab;

    PeopleTab peopleTab;

    Triage triage;

    TimelineTab timelineTab;

    User originalUser;

    CaseView caseView;

    Documents documents;

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
                dataInput.overwriteCorrespondenceReceivedDate(getDatePlusMinusNDaysAgo(1));
                break;
            case "CORRESPONDENCE SENT":
                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(1));
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
                correspondents.selectToAddACorrespondent();
                break;
            case "ADD MEMBER OF PARLIAMENT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                dataInput.clickContinueButton();
                correspondents.selectToAddACorrespondent();
                correspondents.selectCorrespondentIsMP();
                break;
            case "RECORD CORRESPONDENT DETAILS":
                dataInput.fillAllMandatoryCorrespondenceFields();
                dataInput.clickContinueButton();
                correspondents.selectToAddACorrespondent();
                correspondents.selectCorrespondentIsNotMP();
                break;
            case "ADD A TOPIC":
                markup.selectPolicyResponseRadioButton();
                safeClickOn(continueButton);
                waitABit(1000);
                break;
            case "ENTER A NEW TOPIC":
                markup.selectPolicyResponseRadioButton();
                safeClickOn(continueButton);
                markup.clickAddTopicLink();
                break;
            case "CASE REJECTION":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("No");
                clickTheButton("Continue");
                break;
            case "HOW DO YOU INTEND TO RESPOND":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
                clickTheButton("Continue");
                break;
            case "SUMMARISE YOUR CALL":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
                safeClickOn(continueButton);
                initialDraft.selectSpecificResponseChannel("Phone");
                safeClickOn(continueButton);
                break;
            case "PRIMARY DRAFT DOCUMENT":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
                safeClickOn(continueButton);
                if (!dtenCase()){
                    initialDraft.selectSpecificResponseChannel("Letter");
                    safeClickOn(continueButton);
                }
                break;
            case "ADD DOCUMENT":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
                safeClickOn(continueButton);
                if (!dtenCase()){
                    initialDraft.selectSpecificResponseChannel("Letter");
                    safeClickOn(continueButton);
                }
                safeClickOn(documents.addDocumentsButton);
                break;
            case "DO YOU WANT TO QA OFFLINE":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
                safeClickOn(continueButton);
                if (!dtenCase()){
                    initialDraft.selectSpecificResponseChannel("Letter");
                    safeClickOn(continueButton);
                }
                documents.addADraftDocumentAtDraftStage();
                safeClickOn(continueButton);
                waitABit(1000);
                break;
            case "WHO HAS DONE THE QA OFFLINE":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
                safeClickOn(continueButton);
                if (!dtenCase()){
                    initialDraft.selectSpecificResponseChannel("Letter");
                    safeClickOn(continueButton);
                }
                documents.addADraftDocumentAtDraftStage();
                safeClickOn(continueButton);
                initialDraft.selectQAOfflineDecision("Yes");
                safeClickOn(continueButton);
                safeClickOn(finishButton);
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
            case "CANCEL":
                safeClickOn(backLink);
                break;
            case "ADD A CORRESPONDENT":
                correspondents.selectToAddACorrespondent();
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
        try {
            clickTheButton(buttonLabel);
        } catch (StaleElementReferenceException sE) {
            waitABit(500);
            clickTheButton(buttonLabel);
        }
    }

    @When("I open/close the {string} accordion section")
    public void iOpenCloseTheAccordionSection(String accordionLabel) {
        try {
            openOrCloseAccordionSection(accordionLabel);
        } catch (StaleElementReferenceException sE) {
            waitABit(500);
            openOrCloseAccordionSection(accordionLabel);
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

    @And("I select 'Save changes'")
    public void iSelectSaveChanges() {
        safeClickOn(saveChangesRadioButton);
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

    @Then("the case/claim should be closed")
    public void theCaseShouldBeClosed() {
        dashboard.getCurrentCase();
        caseView.assertCaseCannotBeAssigned();
//        if (!wcsCase()) {
//            timelineTab.selectTimelineTab();
//            timelineTab.assertCaseClosedNoteVisible();
//        }
        System.out.println("The case is closed");
    }

    @Then("{string} link is displayed")
    public void linkIsDisplayed(String linkText) {
        switch (linkText.toUpperCase()) {
            case "ADD A CORRESPONDENT":
                correspondents.assertAddACorrespondentLinkIsDisplayed();
                break;
            default:
                pendingStep(linkText + " is not defined within " + getMethodName());
        }
    }

    @Then("the case/claim should be at/moved/returned (to )(the ){string}( stage)")
    public void assertCaseTypeMovedOrReturnedToStage(String stage) {
        dashboard.goToDashboard();
        dashboard.getCurrentCase();
        summaryTab.selectSummaryTab();
        summaryTab.assertCaseStage(stage);
    }

    @Then("the FOI case should be moved/returned to (the ){string}( stage)")
    public void theFOICaseShouldBeMovedReturnedToTheStage(String stage) {
        if (stage.equalsIgnoreCase("ALLOCATION") || stage.equalsIgnoreCase("APPROVAL") || stage.equalsIgnoreCase("DISPATCH") || stage.equalsIgnoreCase("SOFT CLOSE")) {
            try {
                summaryTab.selectSummaryTab();
            } catch (ElementNotVisibleException e) {
                timelineTab.selectTimelineTab();
                waitABit(500);
                summaryTab.selectSummaryTab();
            }
            summaryTab.assertCaseStage(stage);
        } else {
            assertCaseTypeMovedOrReturnedToStage(stage);
        }
    }

    @And("I reject the case at the {string} stage")
    public void iRejectTheCaseAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "INITIAL DRAFT":
                initialDraft.selectIfCaseCanBeAnsweredByTeam("No");
                safeClickOn(continueButton);
                initialDraft.enterReasonTeamCannotAnswer();
                safeClickOn(finishButton);
                break;
            case "QA RESPONSE":
                safeClickOn(qaResponse.QARejectRadioButton);
                safeClickOn(qaResponse.continueButton);
                qaResponse.enterQARejectionNote();
                safeClickOn(finishButton);
                break;
            case "PRIVATE OFFICE APPROVAL":
                safeClickOn(privateOfficeApproval.privateOfficeRejectRadioButton);
                safeClickOn(privateOfficeApproval.continueButton);
                privateOfficeApproval.enterPORejectNotes();
                safeClickOn(finishButton);
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
                safeClickOn(finishButton);
                break;
            case "MARKUP":
                markup.selectRejectToDataInput();
                safeClickOn(continueButton);
                markup.enterARejectionReason();
                safeClickOn(finishButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not entered text in the Case Note text box")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotEnteredTextInTheCaseNoteTextbox() {
        workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @And("I click to view the {string} tab")
    public void iClickToViewTheTab(String tab) {
        switch (tab.toUpperCase()) {
            case "SUMMARY":
                summaryTab.selectSummaryTab();
                break;
            case "TIMELINE":
                timelineTab.selectTimelineTab();
                break;
            case "PEOPLE":
                peopleTab.selectPeopleTab();
                break;
            default:
                pendingStep(tab + " is not defined within " + getMethodName());
        }
    }

    @And("the case {string} be allocated to me in the summary")
    public void theCaseShouldBeAllocatedToMeInTheSummary(String input) {
        switch (input.toUpperCase()) {
            case "SHOULD":
                summaryTab.assertAllocatedUserIsMe(true);
                break;
            case "SHOULD NOT":
                summaryTab.assertAllocatedUserIsMe(false);
                break;
            default:
                pendingStep(input + " is not defined within " + getMethodName());
        }
    }

    @And("I record the user who completed the previous stages")
    public void iRecordTheUserWhoCompletedThePreviousStages() {
        originalUser = getCurrentUser();
    }

    @Then("the case should be allocated to the original user")
    public void caseShouldBeAllocatedTo() {
        summaryTab.selectSummaryTab();
        int retest = 0;
        while (retest < 5) {
            try {
                summaryTab.assertAllocatedUserIs(originalUser);
                break;
            } catch (AssertionError a) {
                retest++;
                timelineTab.selectTimelineTab();
                summaryTab.selectSummaryTab();
            }
        }
        summaryTab.assertAllocatedUserIs(originalUser);
    }

    @And("the case should be in the correct MPAM {string} team workstack")
    public void theCaseShouldBeInTheCorrectMPAMTeamWorkstack(String stage) {
        summaryTab.assertAllocatedUKVITeam(stage);
    }

    @Then("the claim should be sent/returned to the correct WCS Casework team")
    public void theClaimShouldBeReturnedToTheCaseworkTeamThatLastWorkedTheClaim() {
        dashboard.getCurrentCase();
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("selectedCaseworkTeam"), "Team");
    }

    @And("I record the case reference of this case as {string}")
    public void iRecordTheCaseReferenceOfThisCaseAs(String sessionVariableName) {
        setSessionVariable(sessionVariableName).to(getCurrentCaseReference());
    }

    @Then("the header tags in the HTML of the page are properly structured")
    public void theOrderOfHeaderTagsInHTMLOfThePageAreProperlyOrdered() {
        checkOrderOfHeaderTagsOnCaseView();
    }

    @And("the accessibility statement link should be visible")
    public void accessibilityStatementLinkShouldBeVisible() {
        assertVisibilityOfAccessibilityLink();
    }

    @And("the summary should display the owning team as {string}")
    public void theSummaryShouldDisplayTheOwningTeamAs(String teamName) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the summary should display {string} for {string}")
    public void theSummaryShouldDisplayFor(String value, String header) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(value, header);
    }
}

