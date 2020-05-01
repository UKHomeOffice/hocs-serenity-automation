package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.UnassignedCaseView;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs extends BasePage {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    AddCorrespondent dataInputAddCorrespondent;

    Homepage homepage;

    Workstacks workstacks;

    UnassignedCaseView UnassignedCaseView;

    @When("I complete the Data Input stage and send a copy to Number Ten")
    public void completeDataInputStageWCopyToN10() {
        dataInput.dataInputFullFlowWithCopyToN10();
    }

    @When("I complete the Data Input Stage")
    public void completeDataInputPerCaseType() {
        if (homepage.myCases.isVisible()) {
            homepage.getCurrentCase();
            workstacks.clickAllocateToMeButton();
        }
        dataInput.moveCaseFromDataInputToMarkup();
    }

    @When("I add an additional correspondent")
    public void iAddAnAdditionalCorrespondent() {
        addACorrespondentThatIsOrIsNotAnMP("Is not");
        dataInputAddCorrespondent.fillMandatoryCorrespondentFieldsForSecondaryContact();
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
        dataInputAddCorrespondent.selectToAddACorrespondent();
        if (isOrIsNot.toUpperCase().equals("IS")) {
            dataInputAddCorrespondent.selectCorrespondentIsMP();
        } else if (isOrIsNot.toUpperCase().equals("IS NOT")) {
            dataInputAddCorrespondent.selectCorrespondentIsNotMP();
        }
    }

    @Then("an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        dataInputAddCorrespondent.assertCorrespondentFullNameErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected the correspondent type")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        dataInputAddCorrespondent.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("an error message should be displayed as I must select a member of parliament from the drop down")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        dataInputAddCorrespondent.assertMemberIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I must select a correspondent type on this screen")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        dataInputAddCorrespondent.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("they should be added to the list of correspondents")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        dataInputAddCorrespondent.assertPrimaryCorrespondent();
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
                dataInputAddCorrespondent.fillMandatoryPublicCorrespondentFields();
                dataInput.clickAddButton();
                dataInputAddCorrespondent.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                aCaseHasACorrespondent("PRIMARY");
                iAddAnAdditionalCorrespondent();
                dataInputAddCorrespondent.assertSecondaryCorrespondent();
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
        dataInputAddCorrespondent.assertPrimaryCorrespondent();
        dataInputAddCorrespondent.assertSecondaryCorrespondent();
    }

    @When("I select the primary correspondent radio button for a different correspondent")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
        dataInputAddCorrespondent.setSecondCorrespondentAsPrimaryCorrespondent();
    }

    @Then("the correct correspondent is recorded as the primary correspondent")
    public void theCorrectCorrespondentIsRecordedAsTheCorrespondent() {
        homepage.getCurrentCase();
        UnassignedCaseView.assertThePrimaryContactName(sessionVariableCalled("secondCorrespondentFullName"));


    }

    @And("I complete the Data Input stage of the displayed case")
    public void iCompleteTheDataInputStageOfTheDisplayedCase() {
        dataInput.moveCaseFromDataInputToMarkup();
    }

    @And("I add the member of parliament {string}")
    public void iAddTheMemberOfParliament(String member) {
        setSessionVariable("fullName").to(member);
        dataInputAddCorrespondent.selectMemberOfParliament(member);
        waitABit(2000);
        dataInputAddCorrespondent.clickAddButton();
    }

    @When("I fill all mandatory fields on the {string} page with valid data")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "DATA INPUT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                break;
            case "CORRESPONDENT DETAILS":
                dataInputAddCorrespondent.fillMandatoryPublicCorrespondentFields();
                dataInput.clickAddButton();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
    }
}
