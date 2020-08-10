package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    AddCorrespondent dataInputAddCorrespondent;

    Homepage homepage;

    Workstacks workstacks;

    AccordionDCU accordionDCU;

    SummaryTab summary;

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
                clickTheButton("Continue");
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
        accordionDCU.assertThePrimaryContactName(sessionVariableCalled("primaryCorrespondent"));


    }

    @And("I complete the Data Input stage of the displayed case")
    public void iCompleteTheDataInputStageOfTheDisplayedCase() {
        dataInput.moveCaseFromDataInputToMarkup();
    }

    @And("I complete the Data Input stage adding 3 member correspondents")
    public void iCompleteTheDataInputStageWithMultipleMemberCorrespondents() {
        dataInput.completeDataInputStageWithThreeMPCorrespondents();
    }

    @And("I complete the Data Input stage adding 3 public correspondents")
    public void iCompleteDataInputStageWithThreePublicCorrespondents() {
        dataInput.completeDataInputWithThreePublicCorrespondents();
    }

    @And("I add the member of parliament {string}")
    public void iAddTheMemberOfParliament(String member) {
        setSessionVariable("correspondentFullName").to(member);
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

    @Then("the submitted correspondent should be visible in the list of correspondents")
    public void theSubmittedCorrespondentShouldBeVisibleInTheListOfCorrespondents() {
        dataInputAddCorrespondent.assertPrimaryCorrespondent();
    }

    @And("I remove the primary correspondent")
    public void removePrimaryCorrespondent() {
        dataInputAddCorrespondent.removePrimaryCorrespondent();
    }

    @Then("there shouldn't be a primary correspondent displayed")
    public void thereShouldntBeAPrimaryCorrespondentDisplayed() {
        dataInputAddCorrespondent.assertNoPrimaryCorrespondentDisplayed();
    }

    @And("I edit the primary correspondents name")
    public void iEditThePrimaryCorrespondent() {
        dataInputAddCorrespondent.editPrimaryCorrespondent();
    }

    @Then("the correspondents name should be updated")
    public void theCorrespondentsNameShouldBeUpdated() {
        dataInputAddCorrespondent.assertPrimaryCorrespondent();
    }

    @And("the stage deadline dates for a {string} case are correct")
    public void checkStageDeadlineDatesCorrect(String caseType) {
        safeClickOn(summaryTab);
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "HOME SECRETARY SIGN-OFF":
                summary.assertDeadlineDateOfStage(caseType, "Data Input");
                summary.assertDeadlineDateOfStage(caseType, "Markup");
                summary.assertDeadlineDateOfStage(caseType, "Initial Draft");
                summary.assertDeadlineDateOfStage(caseType, "QA Response");
                summary.assertDeadlineDateOfStage(caseType, "Private Office Approval");
                summary.assertDeadlineDateOfStage(caseType, "Ministerial Sign Off");
                summary.assertDeadlineDateOfStage(caseType, "Transfer Confirmation");
                summary.assertDeadlineDateOfStage(caseType, "No Response Needed Confirmation");
                summary.assertDeadlineDateOfStage(caseType, "Dispatch");
                break;
            case "DTEN":
                summary.assertDeadlineDateOfStage(caseType, "Dispatch");
                summary.assertDeadlineDateOfStage(caseType, "Initial Draft");
                break;
            case "TRO":
                summary.assertDeadlineDateOfStage(caseType, "Data Input");
                summary.assertDeadlineDateOfStage(caseType, "Markup");
                summary.assertDeadlineDateOfStage(caseType, "Initial Draft");
                summary.assertDeadlineDateOfStage(caseType, "QA Response");
                summary.assertDeadlineDateOfStage(caseType, "Transfer Confirmation");
                summary.assertDeadlineDateOfStage(caseType, "No Response Needed Confirmation");
                summary.assertDeadlineDateOfStage(caseType, "Dispatch");
                summary.assertDeadlineDateOfStage(caseType, "Copy To Number 10");
                break;
            case "MPAM":
                summary.assertDeadlineDateOfStage(caseType, "GENERAL");
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I select {string} for Home Secretary interest and complete the data input stage")
    public void completeDataInputStageWithSpecifiedHomeSecInterest(String interest) {
        setSessionVariable("homeSecInterest").to(interest);
        switch (interest.toUpperCase()) {
            case "YES":
                dataInput.moveCaseFromDataInputToMarkup();
                break;
            case "NO":
                dataInput.completeDataInputStageWithHomeSecInterestNo();
                break;
            default:
                pendingStep(interest + " is not defined within " + getMethodName());
        }
    }

    @Then("the Home Secretary interest decision should match the one displayed in the summary tab")
    public void assertHomeSecInterestInputMatchesSummaryTab() {
        safeClickOn(summaryTab);
        summary.assertHomeSecInterestMatchesDecisionAtDataInput();
    }
}
