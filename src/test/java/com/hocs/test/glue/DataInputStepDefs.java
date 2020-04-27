package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.DCUCaseDetailsAccordion;
import com.hocs.test.pages.DCU_Workflow.DataInput;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.DCU_Workflow.InitialDraft_RecordCorrespondentDetails;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs extends BasePage {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    Homepage homepage;

    InitialDraft_RecordCorrespondentDetails initialDraftRecordCorrespondentDetails;

    Workstacks workstacks;

    DCUCaseDetailsAccordion DCUCaseDetailsAccordion;

    @When("I complete the Data Input stage and send a copy to Number Ten")
    public void completeDataInputStageWCopyToN10() {
        dataInput.dataInputFullFlowWithCopyToN10();
    }

    @When("I complete the Data Input Stage for {string} case type")
    public void completeDataInputPerCaseType(String caseType) {
        if (homepage.myCases.isVisible()) {
            homepage.getCurrentCase();
            workstacks.clickAllocateToMeButton();
        }
        switch (caseType.toUpperCase()) {
            case "MIN":
                dataInput.dataInputFullFlowMIN();
                break;
            case "DTEN":
                dataInput.dataInputFullFlowDTEN();
                break;
            case "TRO":
                dataInput.dataInputFullFlowTRO();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I add an additional correspondent")
    public void iAddAnAdditionalCorrespondent() {
        addACorrespondentThatIsOrIsNotAnMP("Is not");
        initialDraftRecordCorrespondentDetails.fillMandatoryCorrespondentFieldsForSecondaryContact();
        dataInput.clickAddButton();
    }

    @And("I set the correspondence channel to {string}")
    public void iSetTheCorrespondenceChannelTo(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                break;
            case "POST":
                safeClickOn(dataInput.postOriginalChannelRadioButton);
                break;
            case "PHONE":
                safeClickOn(dataInput.phoneOriginalChannelRadioButton);
                break;
            case "NO. 10":
                safeClickOn(dataInput.numberTenOriginalChannelRadioButton);
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
    }

    @When("I select to add a correspondent that {string} a member of parliament")
    public void addACorrespondentThatIsOrIsNotAnMP(String isOrIsNot) {
        waitABit(2000);
        dataInput.selectAddACorrespondentLink();
        if (isOrIsNot.toUpperCase().equals("IS")) {
            dataInput.selectCorrespondentIsAMemberRadioButton();
        } else if (isOrIsNot.toUpperCase().equals("IS NOT")) {
            dataInput.selectCorrespondentIsNotAMemberRadioButton();
        }
        safeClickOn(dataInput.continueButton);
    }

    @Then("an error message should be displayed as I have not {string}")
    public void assertReasonForErrorMessage(String reason) {
        switch (reason.toUpperCase()) {
            case "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX":
                workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
                break;
            case "ENTERED TEXT INTO THE FULL NAME FIELD":
                dataInput.assertCorrespondentFullNameErrorMessage();
                break;
            case "SELECTED THE CORRESPONDENCE TYPE":
                dataInput.assertCorrespondentTypeDropDownErrorMessage();
                break;
            case "SELECTED A MEMBER OF PARLIAMENT":
                dataInput.assertMemberIsRequiredErrorMessage();
                break;
            case "SELECTED THIS CORRESPONDENCE TYPE":
                dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
                break;
            case "ADDED A PRIMARY CORRESPONDENT":
                dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
                break;
            default:
                pendingStep(reason + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        dataInput.assertCorrespondentFullNameErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected the correspondent type")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("an error message should be displayed as I must select a member of parliament from the drop down")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        dataInput.assertMemberIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I must select a correspondent type on this screen")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("they should be added to the list of correspondents")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        initialDraftRecordCorrespondentDetails.assertPrimaryCorrespondent();
    }

    @Then("an error message should be displayed as I have not entered a {string}")
    public void assertValidationMessagesOnDataInputForm(String field) {
        switch (field.toUpperCase()) {
            case "CORRESPONDENCE DATE":
                dataInput.assertCorrespondenceDateErrorMessage();
                break;
            case "CORRESPONDENCE TYPE":
                dataInput.assertHowWasCorrespondenceReceivedErrorMessage();
                break;
            case "COPY TO NUMBER TEN":
                dataInput.assertShouldResponseBeCopiedN10ErrorMessage();
                break;
            default:
                pendingStep(field + " is not defined within " + getMethodName());
        }
    }

    @And("a case has a {string} correspondent")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                dataInput.fillAllMandatoryCorrespondenceFields();
                genericInputStepDefs.clickTheButton("Continue");
                addACorrespondentThatIsOrIsNotAnMP("Is not");
                initialDraftRecordCorrespondentDetails.fillMandatoryCorrespondentFields();
                dataInput.clickAddButton();
                initialDraftRecordCorrespondentDetails.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                aCaseHasACorrespondent("PRIMARY");
                iAddAnAdditionalCorrespondent();
                initialDraftRecordCorrespondentDetails.assertSecondaryCorrespondent();
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @When("I enter an invalid date")
    public void enterAnInvalidDate() {
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
    }

    @Then("both correspondents are listed")
    public void bothCorrespondentsAreListed() {
        initialDraftRecordCorrespondentDetails.assertPrimaryCorrespondent();
        initialDraftRecordCorrespondentDetails.assertSecondaryCorrespondent();
    }

    @When("I select the primary correspondent radio button for a different correspondent")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
        initialDraftRecordCorrespondentDetails.setSecondCorrespondentAsPrimaryCorrespondent();
    }

    @Then("the correct correspondent is recorded as the primary correspondent")
    public void theCorrectCorrespondentIsRecordedAsTheCorrespondent() {
        homepage.getCurrentCase();
        DCUCaseDetailsAccordion.assertThePrimaryContactName(sessionVariableCalled("secondCorrespondentFullName"));


    }

    @And("I complete the Data Input stage of the displayed case")
    public void iCompleteTheDataInputStageOfTheDisplayedCase() {
        dataInput.moveCaseFromDataInputToMarkup();
    }
}
