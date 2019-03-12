package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInputQADecision;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    Homepage homepage;

    DataInputQADecision dataInputQADecision;

    Page page;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    @When("^I complete the Data Input stage$")
    public void completeDataInputStage() {
        dataInput.dataInputFullFlow();
    }

    @When("^the Data Input Stage is completed for \"([^\"]*)\" caseType$")
    public void completeDataInputPerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                homepage.selectPerformanceProcessTeam();
                break;
            case "DCU N10":
                homepage.selectTransfersN10Team();
                break;
            case "DCU TRO":
                homepage.selectPerformanceProcessTeam();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        dataInput.dataInputFullFlow();
    }

    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {
        recordCorrespondentDetails.clickAdditionalCorrespondentYes();
        dataInput.clickContinueButton();
        dataInput.clickCorrespondentIsNotAMember();
        dataInput.clickContinueButton();
        recordCorrespondentDetails.fillMandatoryFields();
        dataInput.clickContinueButton();
    }

    @When("^I enter correspondence data manually$")
    public void iEnterCorrespondenceDataManually() {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        dataInput.clickContinueButton();
        dataInput.clickAddCorrespondentLink();
        recordCorrespondentDetails.fillMandatoryFields();
        dataInput.clickAddButton();
    }

    @When("^I select to correspond with a member from the dropdown$")
    public void iSelectToCorrespondWithAMemberFromTheDropdown() {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        dataInput.clickContinueButton();
        dataInput.clickCorrespondentIsAMember();
        dataInput.clickContinueButton();
        recordCorrespondentDetails.selectMemberFromDropdownByIndex(1);
        dataInput.clickContinueButton();
    }

    @When("^I select to correspond with \"([^\"]*)\" from the search function$")
    public void iSelectToCorrespondWithAMemberFromTheSearchFunction(String minister) {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        dataInput.clickContinueButton();
        dataInput.clickCorrespondentIsAMember();
        dataInput.clickContinueButton();
        recordCorrespondentDetails.selectMemberFromDropdownByName(minister);
        dataInput.clickContinueButton();
    }

    @When("^I select the primary correspondent radio button for a different correspondent$")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
    }

    @When("^I select a Data Input QA decision of \"([^\"]*)\"$")
    public void iSelectADataInputQADecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "ACCEPT":
                dataInputQADecision.acceptDataInputQa();
                break;
            case "REJECT":
                dataInputQADecision.rejectDataInputQa();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        dataInput.clickFinishButton();
    }

    @When("^I do not select a Data Input QA response$")
    public void iDoNotSelectADataInputQAResponse() {
        dataInput.clickFinishButton();
    }

    @And("^I set the correspondence channel to \"([^\"]*)\"$")
    public void iSetTheCorrespondenceChannelTo(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                dataInput.clickEmailCorrespondenceChannelRadioButton();
                break;
            case "POST":
                dataInput.clickPostCorrespondenceChannelRadioButton();
                break;
            case "PHONE":
                dataInput.clickPhoneCorrespondenceChannelRadioButton();
                break;
            case "NO. 10":
                dataInput.clickNo10CorrespondenceChannelRadioButton();
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
    }

    @When("^I select to add a correspondent that \"([^\"]*)\" a member$")
    public void SelectToAddACorrespondent(String member) {
        switch (member.toUpperCase()) {
            case "IS":
                break;
            case "IS NOT":
                break;
            default:
                pendingStep(member + " is not defined within " + getMethodName());
        }

    }

    @When("^they complete the first data input screen$")
    public void completeFirstDataInputScreen() {
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        dataInput.clickEmailCorrespondenceChannelRadioButton();
        dataInput.clickContinueButton();
        dataInput.clickContinueButton();
        dataInput.clickFinishButton();
    }

    @When("^they click the continue button$")
    public void clickContinueButton() {
        dataInput.clickContinueButton();
    }

    @When("^I click the continue button at the data input stage$")
    public void userDoesNotEnterDateCorrespondenceWasSentDataInputStage() {
        workstacks.clickAllocateToMeButton();
        dataInput.clickContinueButton();
    }



    @When("^I click the finish button on the which is the primary correspondent screen$")
    public void userDoesNotAddPrimaryCorrespondentDataInputStage() {
        workstacks.clickAllocateToMeButton();
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        dataInput.clickEmailCorrespondenceChannelRadioButton();
        dataInput.clickContinueButton();
        dataInput.clickFinishButton();
    }

    @When("^I click the continue button on the is the correspondent an MP screen$")
    public void userDoesNotSelectPrimaryCorrespondentTypeRadioButton() {
        workstacks.clickAllocateToMeButton();
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        dataInput.clickEmailCorrespondenceChannelRadioButton();
        dataInput.clickContinueButton();
        dataInput.clickAddCorrespondentLink();
        dataInput.clickContinueButton();

    }

    @When("^I click the add button on the add member of parliament screen$")
    public void userDoesNotSelectMPFromDownDownBox() {
        workstacks.clickAllocateToMeButton();
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        dataInput.clickEmailCorrespondenceChannelRadioButton();
        dataInput.clickContinueButton();
        dataInput.clickAddCorrespondentLink();
        dataInput.clickCorrespondentIsAMember();
        dataInput.clickContinueButton();
        dataInput.clickAddButton();
    }

    @When("^I click the add button on the record correspondent details screen$")
    public void userDoesNotSelectCorrespondentTypeFromDropDownBox() {
        workstacks.clickAllocateToMeButton();
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        dataInput.clickEmailCorrespondenceChannelRadioButton();
        dataInput.clickContinueButton();
        dataInput.clickAddCorrespondentLink();
        dataInput.clickCorrespondentIsNotAMember();
        dataInput.clickContinueButton();
        dataInput.clickAddButton();

    }

    @Then("^an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        dataInput.assertCorrespondentFullNameErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected the correspondent type$")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("^an error message should be displayed as I must select a member of parliament from the drop down$")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        dataInput.assertMemberIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I must select a radio button on this screen$")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("^they should be added to the list of correspondents$")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        recordCorrespondentDetails.assertPrimaryCorrespondent();
    }

    @Then("^an error message should be displayed as I have not entered a correspondence date$")
    public void assertThatCorrespondenceDateErrorMessageIsShown() {
        dataInput.assertCorrespondenceDateErrorMessage();

    }

    @Then("^an error message should be displayed as I have not selected a radio button$")
    public void assertThatHowCorrespondenceWasSentErrorMessageIsShown() {
        dataInput.assertHowWasCorrespondenceReceivedErrorMessage();

    }

    @Then("^an error message should be displayed as I have not added a primary correspondent$")
    public void assertThatWhichIsPrimaryCorrespondentErrorMessageIsShown() {
        dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
    }
}
