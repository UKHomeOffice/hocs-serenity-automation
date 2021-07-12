package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.platform.AddCorrespondent;
import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.Dashboard;
import com.hocs.test.pages.platform.SummaryTab;
import com.hocs.test.pages.platform.UnallocatedCaseView;
import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    AddCorrespondent addCorrespondent;

    Dashboard dashboard;

    UnallocatedCaseView unallocatedCaseView;

    AccordionDCU accordionDCU;

    SummaryTab summaryTab;

    @When("I complete the Data Input stage and send a copy to Number Ten")
    public void completeDataInputStageWCopyToN10() {
        dataInput.dataInputFullFlowWithCopyToN10();
    }

    @When("I complete the Data Input Stage")
    public void completeDataInputPerCaseType() {
        if (!dataInput.continueButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        dataInput.moveCaseFromDataInputToMarkup();
    }

    @When("I add an additional correspondent")
    public void iAddAnAdditionalCorrespondent() {
        addACorrespondentThatIsOrIsNotAnMP("Is not");
        addCorrespondent.fillMandatoryCorrespondentFieldsForSecondaryContact();
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
        addCorrespondent.selectToAddACorrespondent();
        if (isOrIsNot.equalsIgnoreCase("IS")) {
            addCorrespondent.selectCorrespondentIsMP();
        } else if (isOrIsNot.equalsIgnoreCase("IS NOT")) {
            addCorrespondent.selectCorrespondentIsNotMP();
        }
    }

    @Then("an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        addCorrespondent.assertCorrespondentFullNameErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected the correspondent type")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        addCorrespondent.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("an error message should be displayed as I must select a member of parliament from the drop down")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        addCorrespondent.assertMemberIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I must select a correspondent type on this screen")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        addCorrespondent.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @And("a case has a {string} correspondent")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                dataInput.fillAllMandatoryCorrespondenceFields();
                clickTheButton("Continue");
                addACorrespondentThatIsOrIsNotAnMP("Is not");
                addCorrespondent.selectCorrespondentTypeFromDropdown("Constituent");
                addCorrespondent.fillCorrespondentFields();
                dataInput.clickAddButton();
                addCorrespondent.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                aCaseHasACorrespondent("PRIMARY");
                iAddAnAdditionalCorrespondent();
                addCorrespondent.assertSecondaryCorrespondent();
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @When("I enter an invalid date")
    public void enterAnInvalidDate() {
        dataInput.typeIntoDateFields(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                dataInput.dateCorrespondenceReceivedYearField,
                "29/02/2019");
    }

    @Then("both correspondents are listed")
    public void bothCorrespondentsAreListed() {
        addCorrespondent.assertPrimaryCorrespondent();
        addCorrespondent.assertSecondaryCorrespondent();
    }

    @When("I select the primary correspondent radio button for a different correspondent")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
        addCorrespondent.setSecondCorrespondentAsPrimaryCorrespondent();
    }

    @Then("the correct correspondent is recorded as the primary correspondent")
    public void theCorrectCorrespondentIsRecordedAsTheCorrespondent() {
        dashboard.getCurrentCase();
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
        addCorrespondent.selectMemberOfParliament(member);
        waitABit(2000);
        addCorrespondent.clickAddButton();
    }

    @When("I fill all mandatory fields on the {string} page with valid data")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "DATA INPUT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                break;
            case "CORRESPONDENT DETAILS":
                addCorrespondent.selectCorrespondentTypeFromDropdown("Constituent");
                addCorrespondent.fillCorrespondentFields();
                dataInput.clickAddButton();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
    }

    @Then("the submitted correspondent should be visible in the list of correspondents")
    public void theSubmittedCorrespondentShouldBeVisibleInTheListOfCorrespondents() {
        addCorrespondent.assertPrimaryCorrespondent();
    }

    @And("I remove the primary correspondent")
    public void removePrimaryCorrespondent() {
        addCorrespondent.removePrimaryCorrespondent();
    }

    @Then("there shouldn't be a primary correspondent displayed")
    public void thereShouldntBeAPrimaryCorrespondentDisplayed() {
        addCorrespondent.assertNoPrimaryCorrespondentDisplayed();
    }

    @And("I edit the primary correspondents name")
    public void iEditThePrimaryCorrespondent() {
        addCorrespondent.editPrimaryCorrespondent();
    }

    @Then("the correspondents name should be updated")
    public void theCorrespondentsNameShouldBeUpdated() {
        addCorrespondent.assertPrimaryCorrespondent();
    }

    @And("the stage deadline dates for a {string} case are correct")
    public void checkStageDeadlineDatesCorrect(String caseType) {
        summaryTab.selectSummaryTab();
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "HOME SECRETARY SIGN-OFF":
                summaryTab.assertDeadlineDateOfStage(caseType, "Data Input");
                summaryTab.assertDeadlineDateOfStage(caseType, "Markup");
                summaryTab.assertDeadlineDateOfStage(caseType, "Initial Draft");
                summaryTab.assertDeadlineDateOfStage(caseType, "QA Response");
                summaryTab.assertDeadlineDateOfStage(caseType, "Private Office Approval");
                summaryTab.assertDeadlineDateOfStage(caseType, "Ministerial Sign Off");
                summaryTab.assertDeadlineDateOfStage(caseType, "Transfer Confirmation");
                summaryTab.assertDeadlineDateOfStage(caseType, "No Response Needed Confirmation");
                summaryTab.assertDeadlineDateOfStage(caseType, "Dispatch");
                break;
            case "DTEN":
                summaryTab.assertDeadlineDateOfStage(caseType, "Dispatch");
                summaryTab.assertDeadlineDateOfStage(caseType, "Initial Draft");
                break;
            case "TRO":
                summaryTab.assertDeadlineDateOfStage(caseType, "Data Input");
                summaryTab.assertDeadlineDateOfStage(caseType, "Markup");
                summaryTab.assertDeadlineDateOfStage(caseType, "Initial Draft");
                summaryTab.assertDeadlineDateOfStage(caseType, "QA Response");
                summaryTab.assertDeadlineDateOfStage(caseType, "Transfer Confirmation");
                summaryTab.assertDeadlineDateOfStage(caseType, "No Response Needed Confirmation");
                summaryTab.assertDeadlineDateOfStage(caseType, "Dispatch");
                summaryTab.assertDeadlineDateOfStage(caseType, "Copy To Number 10");
                break;
            case "MPAM":
                summaryTab.assertDeadlineDateOfStage(caseType, "GENERAL");
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
                dataInput.completeDataInputStageSpecifyingHomeSecInterest(true);
                break;
            case "NO":
                dataInput.completeDataInputStageSpecifyingHomeSecInterest(false);
                break;
            default:
                pendingStep(interest + " is not defined within " + getMethodName());
        }
    }

    @Then("the Home Secretary interest decision should match the one displayed in the summary tab")
    public void assertHomeSecInterestInputMatchesSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.assertHomeSecInterestMatchesDecisionAtDataInput();
    }
}
