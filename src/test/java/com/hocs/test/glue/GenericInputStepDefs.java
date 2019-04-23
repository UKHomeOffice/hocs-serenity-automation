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
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
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

    Page page;

    Homepage homepage;

    CreateCase createCase;

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

    @Then("^\"([^\"]*)\" dropdown defaults to \"([^\"]*)\"$")
    public void dropdownDefaultsTo(String dropdown, String expectedText) {
        switch (dropdown.toUpperCase()) {
            case "":
                break;
            default:
                pendingStep(expectedText + " is not defined within " + getMethodName());
        }
    }

    @Given("^I click the \"([^\"]*)\" button$")
    public void clickTheButton(String buttonName) {
        clickThisButton(buttonName);
    }

    @When("^I click the \"([^\"]*)\" link")
    public void clickLink(String name) {
        switch (name.toUpperCase()) {
            case "UPDATE":
                page.clickUpdateLink();
                break;
            case "NEW":
                page.clickNewLink();
                break;
            case "DELETE":
                page.clickDeleteLink();
                break;
            case "VIEW":
                page.clickViewLink();
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
    }


    @Then("^The page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        page.assertTitle(title);
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
                page.assertErrorMessageText("");
                break;
            case "CORRESPONDENCE RECEIVED":
                page.assertErrorMessageText("When was the correspondence received? is required");
                break;
            case "CORRESPONDENCE SENT":
                page.assertErrorMessageText("When was the correspondence sent? is required");
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
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "MARKUP":
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                dataInput.selectN10ResponseNoRadioButton();
                dataInput.clickContinueButton();
                dataInput.sleep(500);
                dataInput.clickAddCorrespondentLink();
                dataInput.clickCorrespondentIsNotAMember();
                dataInput.clickContinueButton();
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                recordCorrespondentDetails.clickAddButton();
                dataInput.clickFinishButton();
                homepage.selectCentralDraftingTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "DRAFT":
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                dataInput.selectN10ResponseYesRadioButton();
                dataInput.clickContinueButton();
                dataInput.sleep(500);
                dataInput.clickAddCorrespondentLink();
                dataInput.clickCorrespondentIsNotAMember();
                dataInput.clickContinueButton();
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                recordCorrespondentDetails.clickAddButton();
                dataInput.clickFinishButton();
                homepage.selectCentralDraftingTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                markUpDecision.clickPolicyResponseRadioButton();
                markUpDecision.clickContinueButton();
                markUpDecision.sleep(500);
                markUpDecision.clickAddTopic();
                topics.enterRealTopic();
                topics.clickAddButton();
                markUpDecision.clickContinueButton();
                markUpDecision.clickFinishButton();
                homepage.selectAnimalsInScienceTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "QA":
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                dataInput.selectN10ResponseYesRadioButton();
                dataInput.clickContinueButton();
                dataInput.sleep(500);
                dataInput.clickAddCorrespondentLink();
                dataInput.clickCorrespondentIsNotAMember();
                dataInput.clickContinueButton();
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                recordCorrespondentDetails.clickAddButton();
                dataInput.clickFinishButton();
                homepage.selectCentralDraftingTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                markUpDecision.clickPolicyResponseRadioButton();
                markUpDecision.clickContinueButton();
                markUpDecision.sleep(500);
                markUpDecision.clickAddTopic();
                topics.enterRealTopic();
                topics.clickAddButton();
                markUpDecision.clickContinueButton();
                markUpDecision.clickFinishButton();
                homepage.selectAnimalsInScienceTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                draft.clickAnsweredByMyTeamYesRadioButton();
                draft.clickContinueButton();
                draft.clickLetterReplyRadioButton();
                draft.clickContinueButton();
                draft.clickAddDocumentsButton();
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                addDocuments.clickAddButton();
                draft.clickContinueButton();
                qa.clickOfflineQANoRadioButton();
                draft.clickContinueButton();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "PO SIGNOFF":
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                dataInput.selectN10ResponseYesRadioButton();
                dataInput.clickContinueButton();
                dataInput.sleep(500);
                dataInput.clickAddCorrespondentLink();
                dataInput.clickCorrespondentIsNotAMember();
                dataInput.clickContinueButton();
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                recordCorrespondentDetails.clickAddButton();
                dataInput.clickFinishButton();
                homepage.selectCentralDraftingTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                markUpDecision.clickPolicyResponseRadioButton();
                markUpDecision.clickContinueButton();
                markUpDecision.sleep(500);
                markUpDecision.clickAddTopic();
                topics.enterRealTopic();
                topics.clickAddButton();
                markUpDecision.clickContinueButton();
                markUpDecision.clickFinishButton();
                homepage.selectAnimalsInScienceTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                draft.clickAnsweredByMyTeamYesRadioButton();
                draft.clickContinueButton();
                draft.clickLetterReplyRadioButton();
                draft.clickContinueButton();
                draft.clickAddDocumentsButton();
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                addDocuments.clickAddButton();
                draft.clickContinueButton();
                qa.clickOfflineQAYesRadioButton();
                draft.clickContinueButton();
                draft.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
                draft.clickFinishButton();
                homepage.selectMinisterForLordsTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "MINISTER SIGNOFF":
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                dataInput.selectN10ResponseYesRadioButton();
                dataInput.clickContinueButton();
                dataInput.sleep(500);
                dataInput.clickAddCorrespondentLink();
                dataInput.clickCorrespondentIsNotAMember();
                dataInput.clickContinueButton();
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                recordCorrespondentDetails.clickAddButton();
                dataInput.clickFinishButton();
                homepage.selectCentralDraftingTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                markUpDecision.clickPolicyResponseRadioButton();
                markUpDecision.clickContinueButton();
                markUpDecision.sleep(500);
                markUpDecision.clickAddTopic();
                topics.enterRealTopic();
                topics.clickAddButton();
                markUpDecision.clickContinueButton();
                markUpDecision.clickFinishButton();
                homepage.selectAnimalsInScienceTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                draft.clickAnsweredByMyTeamYesRadioButton();
                draft.clickContinueButton();
                draft.clickLetterReplyRadioButton();
                draft.clickContinueButton();
                draft.clickAddDocumentsButton();
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                addDocuments.clickAddButton();
                draft.clickContinueButton();
                qa.clickOfflineQAYesRadioButton();
                draft.clickContinueButton();
                draft.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
                draft.clickFinishButton();
                homepage.selectMinisterForLordsTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                privateOffice.clickPrivateOfficeAcceptRadioButton();
                privateOffice.clickContinueButton();
                homepage.selectMinisterForLordsTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            case "DISPATCH":
                homepage.clickCreateSingleCase();
                createCase.createDCUMinSingleCase();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                dataInput.enterDayOfCorrespondenceSent("01");
                dataInput.enterMonthOfCorrespondenceSent("01");
                dataInput.enterYearOfCorrespondenceSent("2019");
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                dataInput.selectN10ResponseYesRadioButton();
                dataInput.clickContinueButton();
                dataInput.sleep(500);
                dataInput.clickAddCorrespondentLink();
                dataInput.clickCorrespondentIsNotAMember();
                dataInput.clickContinueButton();
                recordCorrespondentDetails.selectCorrespondentType();
                recordCorrespondentDetails.enterCorrespondentFullName("Bob");
                recordCorrespondentDetails.clickAddButton();
                dataInput.clickFinishButton();
                homepage.selectCentralDraftingTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                markUpDecision.clickPolicyResponseRadioButton();
                markUpDecision.clickContinueButton();
                markUpDecision.sleep(500);
                markUpDecision.clickAddTopic();
                topics.enterRealTopic();
                topics.clickAddButton();
                markUpDecision.clickContinueButton();
                markUpDecision.clickFinishButton();
                homepage.selectAnimalsInScienceTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                draft.clickAnsweredByMyTeamYesRadioButton();
                draft.clickContinueButton();
                draft.clickLetterReplyRadioButton();
                draft.clickContinueButton();
                draft.clickAddDocumentsButton();
                draft.selectDocumentTypeByIndex(2);
                addDocuments.uploadDocument();
                addDocuments.clickAddButton();
                draft.clickContinueButton();
                qa.clickOfflineQAYesRadioButton();
                draft.clickContinueButton();
                draft.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
                draft.clickFinishButton();
                homepage.selectMinisterForLordsTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                privateOffice.clickPrivateOfficeAcceptRadioButton();
                privateOffice.clickContinueButton();
                homepage.selectMinisterForLordsTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                workstacks.clickAllocateToMeButton();
                minister.clickMinisterSignOffAcceptRadioButton();
                minister.clickContinueButton();
                homepage.selectPerformanceProcessTeam();
                successfulCaseCreation.selectCaseReferenceNumberViaXpath();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }


    @When("^I set the date to \"([^\"]*)\"$")
    public void iSetTheDate(String date) {
        switch (date.toUpperCase()) {
            case "TODAY":
                page.getCurrentDay();
                page.getCurrentMonth();
                page.getCurrentYear();
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
        String day = page.todayPlusNDaysGetDay(days);
        String month = page.todayPlusNDaysGetMonth(days);
        String year = page.todayPlusNDaysGetYear(days);
    }

    @When("^I set a deadline of -1 days$")
    public void setDeadlineToDaysDays(int days) {
        dataInput.setDateMinusOneDay();
    }

    @Then("^an error message is displayed$")
    public void anErrorMessageIsDisplayed() {
        page.errorMessageIsDisplayed();
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
                dataInput.clickAddCorrespondentLink();
                clickThisButton(button);
                break;
            case "ADD MEMBER OF PARLIAMENT" :
                dataInput.getToAddMemberOfParliamentPrerequisites();
                clickThisButton(button);
                break;
            case "PRIMARY CORRESPONDENT" :
                clickThisButton(button);
                break;
            case "RECORD CORRESPONDENT DETAILS" :
                dataInput.getToRecordCorrespondentDetailsPrerequisites();
                clickThisButton(button);
                break;
            default:
                pendingStep(page + " is not defined within " + getMethodName());
        }
    }

    @When("^I attempt to reject a case without reason$")
    public void iAttemptToRejectACaseWithoutReason() {
        page.clickRejectButton();
        while (page.isElementDisplayed(page.nextButton)) {
            page.clickNextButton();
        }
    }

    @When("^I \"([^\"]*)\" the case$")
    public void iActionTheCase(String action) {
        setSessionVariable("caseId").to(page.getCaseId());
        switch (action.toUpperCase()) {
            case "ACCEPT":
                page.clickAcceptButton();
                page.clickContinueButton();
                break;
            case "ALLOCATE":
                break;
            case "DISPATCH":
                break;
            case "REJECT":
                page.clickRejectButton();
                page.clickContinueButton();
                page.enterRejectionNotes();
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
                break;
            default:
                pendingStep(fieldName + " is not defined within " + getMethodName());
        }
    }

    @Then("^the case is completed$")
    public void theCaseIsCompleted() {
        homepage.assertCaseIsComplete();
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
                page.enterRejectionNotes();
                page.clickFinishButton();
                break;
            case "DO NOT COMPLETE":
                page.clickFinishButton();
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
}

