package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateCaseStepDefs {

    AddDocuments addDocuments;

    CreateCase createCase;

    SuccessfulCaseCreation successfulCaseCreation;

    DataInput dataInput;

    Homepage homepage;

    Topics topics;

    Page page;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;


    @Given("^I am presented with \"([^\"]*)\"$")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                createCase.assertNoOptionsAvailable();
                break;
            default:
                pendingStep(userView + " is not defined within " + getMethodName());
        }
    }

    @Given("^I create a single case \"([^\"]*)\"$")
    public void createACaseTypeSpecificCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                setSessionVariable("caseType").to(caseType);
                break;
            case "DCU N10":
                createCase.createDC10SingleCase();
                setSessionVariable("caseType").to(caseType);
                break;
            case "DCU TRO":
                createCase.createDCTROSingleCase();
                setSessionVariable("caseType").to(caseType);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I create a case$")
    public void aCaseIsCreated() {
        createCase.clickDcuMinRadioButton();
        createCase.clickNextButton();
        addDocuments.uploadDocument();
        createCase.clickSubmitButton();
        page.sleep(500);
    }

    @When("^I create a \"([^\"]*)\" case with \"([^\"]*)\"$")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                setSessionVariable("caseType").to(caseType);
                homepage.waitForPerformanceProcessTeam();
                page.clickOn(homepage.performanceProcessTeam);
                dataInput.dataInputFullFlow();
                topics.fromMarkupStartSelectATopic(topic);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I create a \"([^\"]*)\" case with \"([^\"]*)\" and view the primary topics$")
    public void aCaseWithSpecificTopic(String caseType, String topic) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                setSessionVariable("caseType").to(caseType);
                dataInput.dataInputFullFlow();
                topics.fromMarkupStartSelectATopicAndStayOnPrimaryTopicsPage(topic);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I create a case with a <Primary Correspondent>$")
    public void aCaseWithSpecifiedPrimaryCorrespondantIsCreated() {
    }

    @When("^I create a single MIN case$")
    public void createNewMinCase() {
        createCase.createDCUMinSingleCase();
    }

    @When("^I allocate the case to myself via the successful case creation screen$")
    public void allocateToMe() {
        successfulCaseCreation.allocateToMeViaSuccessfulCreationScreen();
    }

    @Then("^the case should be visible in my workstack$")
    public void assertThatCaseIsAddedToMyWorkstack() {
        homepage.goHome();
        page.clickOn(homepage.myCases);
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("^the case should be visible in the Performance and Process Team workstack$")
    public void assertThatNewMinCaseIsInPerformanceAndProcessTeam() {
        homepage.goHome();
        homepage.selectPerformanceProcessTeam();
        workstacks.assertCaseReferenceIsVisible();
    }

    @When("^I navigate to the Performance and Process Team and select the check box against the newly created case and allocate it to myself$")
    public void allocateCaseUsingCheckbox() {
        page.clickOn(createCase.$("//input[@id='submit']"));
        String newCaseReference = workstacks.$("//h1").getText();
        setSessionVariable("caseReference").to(newCaseReference);
        page.clickOn(homepage.home);
        page.clickOn(homepage.performanceProcessTeam);
        workstacks.clickCheckboxRelevantToCaseReference();
        page.clickOn(workstacks.allocateCheckboxCaseToMeButton);
        page.clickOn(workstacks.home);
    }

    @Then("^the correspondence type is the \"([^\"]*)\" correspondent$")
    public void theCorrespondenceTypeIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                recordCorrespondentDetails.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @And("^a case has a \"([^\"]*)\" correspondent$")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @Then("^the member is the \"([^\"]*)\" correspondent$")
    public void theMemberIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":

                break;
            case "SECONDARY":
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @When("^I bulk create (\\d+) \"([^\"]*)\" cases$")
    public void bulkCreateCases(int cases, String caseType) {
        page.clickOn(homepage.createBulkCases);
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                page.clickOn(createCase.dcuMinRadioButton);
                break;
            case "DCU TRO":
                page.clickOn(createCase.dcuTroRadioButton);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        page.clickOn(createCase.nextButton);
        addDocuments.bulkUploadDocuments(cases);
        page.clickOn(createCase.finishButton);
    }

    @When("^I create a \"([^\"]*)\" case \"([^\"]*)\" a document$")
    public void createCaseWithDocument(String caseType, String document) {
        page.clickOn(homepage.createSingleCase);
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                page.clickOn(createCase.dcuMinRadioButton);
                page.clickOn(createCase.nextButton);
                break;
            case "DCU TRO":
                page.clickOn(createCase.dcuTroRadioButton);
                page.clickOn(createCase.nextButton);
                break;
            case "DCU TEN":
                page.clickOn(createCase.dcuDtenRadioButton);
                page.clickOn(createCase.nextButton);
                addDocuments.enterDispatchDeadlineDay(10);
                addDocuments.enterDispatchDeadlineMonth(10);
                addDocuments.enterDispatchDeadlineYear(10);
                addDocuments.enterDraftDeadlineDay(10);
                addDocuments.enterDraftDeadlineMonth(10);
                addDocuments.enterDraftDeadlineYear(10);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (document.toUpperCase()) {
            case "WITH":
                addDocuments.uploadDocument();
                page.clickOn(createCase.finishButton);
                break;
            case "WITHOUT":
                page.clickOn(createCase.finishButton);
                break;
            default:
                pendingStep(document + " is not defined within " + getMethodName());
        }
    }

    @Then("^A case is created successfully$")
    public void aCaseIsCreatedSuccessfully() {
        successfulCaseCreation.assertCaseCreatedSuccess();
        successfulCaseCreation.getCaseReference();
    }

    @Then("^an error message should be displayed as I have not selected the case type$")
    public void assertThatCaseTypeErrorMessageIsDisplayed() {
        createCase.assertCaseTypeErrorMessage();
    }

    @When("^they do not enter a date into the date received text boxes$")
    public void deleteDefaultDateFromTextBoxes() {
        dataInput.clearDateCorrespondenceReceived();
    }

    @When("^they enter an invalid date into the date received text boxes$")
    public void enterInvalidDateIntoTextBoxes() {
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
    }

    @When("^they select the case type")
    public void userClicksCaseRadioButtonAndClicksNextButton() {
        page.clickOn(createCase.dcuMinRadioButton);
        page.clickOn(createCase.nextButton);
    }

    @When("^I click the finish button on the create single case screen$")
    public void userCreatesCaseWithoutEnteringDateReceived() {
        page.clickOn(createCase.dcuMinRadioButton);
        page.clickOn(createCase.nextButton);
        dataInput.clearDateCorrespondenceReceived();
        page.clickOn(createCase.finishButton);
    }

    @When("^I click the finish button after entering an invalid date on the create single case screen$")
    public void userCreatesCaseWithInvalidDate() {
        page.clickOn(createCase.dcuMinRadioButton);
        page.clickOn(createCase.nextButton);
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
        page.clickOn(createCase.finishButton);
    }

    @When("^they create the bulk cases without adding a document$")
    public void userCreatesBulkCasesWithoutAddingADocument() {
        page.clickOn(createCase.dcuMinRadioButton);
        page.clickOn(createCase.nextButton);
        page.clickOn(createCase.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered the correspondence received date")
    public void assertThatDateReceivedErrorMessageIsShown() {
        createCase.assertDateReceivedNotEnteredErrorMessage();

    }

    @Then("^an error message should be displayed as I have entered an invalid date$")
    public void assertThatDateReceivedIsInvalidErrorMessageIsShown() {
        createCase.assertDateReceivedIsInvalidErrorMessage();
    }

    @Then("^an error message should be displayed informing the user that documents are mandatory$")
    public void assertThatDocumentsAreMandatoryErrorMessageIsShown() {
        createCase.assertDocumentsAreMandatoryErrorMessage();

    }

    @When("^I click the cancel button on the what type of correspondence page$")
    public void clickCancelButtonOnWhatTypeOfCorrespondencePage() {
        createCase.cancelAtWhatTypeOfCor();
    }

    @When("^I move to the When Was Correspondence Received Page$")
    public void clickCancelButtonOnWhenWasCorrespondenceReceivedPage() {
        createCase.getToWhenWasCorReceived();
    }

    @When("^I enter a blank date$")
    public void enterInvalidDateOnCaseCreateScreen() {
        createCase.enterNoDate();
    }

    @When("^I enter an invalid date$")
    public void enterAnInvalidDate() {
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
    }
}

