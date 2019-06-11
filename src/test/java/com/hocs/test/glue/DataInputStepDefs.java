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

import static net.serenitybdd.core.Serenity.pendingStep;

public class DataInputStepDefs extends Page {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    Homepage homepage;

    DataInputQADecision dataInputQADecision;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    @When("^I complete the Data Input stage$")
    public void completeDataInputStage() {
        dataInput.dataInputFullFlow();
    }

    @When("^I complete the Data Input stage and send a copy to Number Ten$")
    public void completeDataInputStageWCopyToN10() {
        dataInput.dataInputFullFlowWithCopyToN10();
    }

    @When("^the Data Input Stage is completed for \"([^\"]*)\" caseType$")
    public void completeDataInputPerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                clickOn(homepage.home);
                dataInput.dataInputFullFlowMIN();
                break;
            case "DCU N10":
//                clickOn(homepage.transferN10Team);
                clickOn(homepage.home);
                dataInput.dataInputFullFlowDTEN();
                break;
            case "DCU TRO":
                clickOn(homepage.performanceProcessTeam);
                dataInput.dataInputFullFlow();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {
        dataInput.addAnAdditionalCorrespondent();
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
                clickOn(dataInput.emailOriginalChannelRadioButton);
                break;
            case "POST":
                clickOn(dataInput.postOriginalChannelRadioButton);
                break;
            case "PHONE":
                clickOn(dataInput.phoneOriginalChannelRadioButton);
                break;
            case "NO. 10":
                clickOn(dataInput.numberTenOriginalChannelRadioButton);
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
    }

    @When("^I select to add a correspondent that \"([^\"]*)\" a member of parliament$")
    public void addACorrespondentThatIsOrIsNotAnMP(String isOrIsNot) {
        dataInput.selectAddACorrespondentLink();


        if (isOrIsNot.toUpperCase().equals("IS")) {
            dataInput.selectCorrespondentIsAMemberRadioButton();
        } else if (isOrIsNot.toUpperCase().equals("IS NOT")) {
            dataInput.selectCorrespondentIsNotAMemberRadioButton();
        }
        clickContinueButton();
    }

    @When("^they complete the first data input screen$")
    public void completeFirstDataInputScreen() {
        dataInput.completeTheFirstDataInputFields();
    }

    @Then("^an error message should be displayed as I have not \"([^\"]*)\"$")
    public void assertReasionForErrorMessage(String reason) {
        switch(reason.toUpperCase()) {
            case "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX" :
                workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
                break;
            case "ENTERED TEXT INTO THE FULL NAME FIELD" :
                dataInput.assertCorrespondentFullNameErrorMessage();
                break;
            case "SELECTED THE CORRESPONDENCE TYPE" :
                dataInput.assertCorrespondentTypeDropDownErrorMessage();
                break;
            case "SELECTED A MEMBER OF PARLIAMENT" :
                dataInput.assertMemberIsRequiredErrorMessage();
                break;
            case "SELECTED THIS CORRESPONDENCE TYPE" :
                dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
                break;
            case "ADDED A PRIMARY CORRESPONDENT" :
                dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
                break;
            default:
                pendingStep(reason + " is not defined within " + getMethodName());
        }
    }

    @Then("^an error message should be displayed as I have not added any text into the case note text box$")
    public void assertThatCaseNoteMustNotBeBlankErrorMessageIsShown() {
        workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
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

    @Then("^an error message should be displayed as I must select a correspondent type on this screen$")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("^they should be added to the list of correspondents$")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        recordCorrespondentDetails.assertPrimaryCorrespondent();
    }

    @Then("^an error message should be displayed as I have not added a primary correspondent$")
    public void assertThatWhichIsPrimaryCorrespondentErrorMessageIsDisplayed() {
        dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered a \"([^\"]*)\"$")
    public void assertValidationMessagesOnDataInputForm(String field) {
        switch(field.toUpperCase()){
            case "CORRESPONDENCE DATE" :
                dataInput.assertCorrespondenceDateErrorMessage();
                break;
            case "CORRESPONDENCE TYPE" :
                dataInput.assertHowWasCorrespondenceReceivedErrorMessage();
                break;
            case "COPY TO NUMBER TEN" :
                dataInput.assertShouldResponseBeCopiedN10ErrorMessage();
                break;
            default:
                pendingStep(field + " is not defined within " + getMethodName());
        }
    }
}
