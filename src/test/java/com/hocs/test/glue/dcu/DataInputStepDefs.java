package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    Correspondents correspondents;

    Dashboard dashboard;

    CaseView caseView;

    SummaryTab summaryTab;

    @When("I add an additional correspondent")
    public void iAddAnAdditionalCorrespondent() {
        addACorrespondentThatIsOrIsNotAnMP("Is not");
        correspondents.fillMandatoryCorrespondentFieldsForSecondaryContact();
        clickAddButton();
    }

    @When("I select to add a correspondent that {string} a member of parliament")
    public void addACorrespondentThatIsOrIsNotAnMP(String isOrIsNot) {
        waitABit(2000);
        correspondents.selectToAddACorrespondent();
        if (isOrIsNot.equalsIgnoreCase("IS")) {
            correspondents.selectCorrespondentIsMP();
        } else if (isOrIsNot.equalsIgnoreCase("IS NOT")) {
            correspondents.selectCorrespondentIsNotMP();
        }
    }

    @Then("an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        correspondents.assertCorrespondentFullNameErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected the correspondent type")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        correspondents.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("an error message should be displayed as I must select a member of parliament from the drop down")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        correspondents.assertMemberIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I must select a correspondent type on this screen")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        correspondents.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @And("a case has a {string} correspondent")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                dataInput.fillAllMandatoryCorrespondenceFields();
                clickTheButton("Continue");
                addACorrespondentThatIsOrIsNotAnMP("Is not");
                correspondents.selectCorrespondentTypeFromDropdown("Constituent");
                correspondents.fillCorrespondentFields();
                dataInput.clickAddButton();
                correspondents.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                aCaseHasACorrespondent("PRIMARY");
                iAddAnAdditionalCorrespondent();
                correspondents.assertSecondaryCorrespondent();
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @When("I enter an invalid date")
    public void enterAnInvalidDate() {
        dataInput.overwriteCorrespondenceReceivedDate("29/02/2019");
    }

    @Then("both correspondents are listed")
    public void bothCorrespondentsAreListed() {
        correspondents.assertPrimaryCorrespondent();
        correspondents.assertSecondaryCorrespondent();
    }

    @When("I select the primary correspondent radio button for a different correspondent")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
        correspondents.setSecondCorrespondentAsPrimaryCorrespondent();
    }

    @Then("the correct correspondent is recorded as the primary correspondent")
    public void theCorrectCorrespondentIsRecordedAsTheCorrespondent() {
        dashboard.getCurrentCase();
        caseView.openOrCloseAccordionSection("Data Input");
        caseView.assertExpectedValueIsVisibleInCaseDetailsAccordionForGivenHeading(sessionVariableCalled("primaryCorrespondent"), "Which is the "
                        + "primary correspondent?");
    }

    @And("I complete the Data Input stage adding 3 member correspondents")
    public void iCompleteTheDataInputStageWithMultipleMemberCorrespondents() {
        dataInput.fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        correspondents.addASpecificMemberCorrespondent("Boris Johnson");
        correspondents.addASpecificMemberCorrespondent("Nicola Sturgeon");
        correspondents.addASpecificMemberCorrespondent("Theresa May");
        safeClickOn(finishButton);
    }

    @And("I complete the Data Input stage adding 3 public correspondents")
    public void iCompleteDataInputStageWithThreePublicCorrespondents() {
        dataInput.fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    @And("I add the member of parliament {string}")
    public void iAddTheMemberOfParliament(String member) {
        setSessionVariable("correspondentFullName").to(member);
        correspondents.selectSpecificMemberOfParliament(member);
        waitABit(2000);
        correspondents.clickAddButton();
    }

    @When("I fill all mandatory fields on the {string} page with valid data")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "DATA INPUT":
                dataInput.fillAllMandatoryCorrespondenceFields();
                break;
            case "CORRESPONDENT DETAILS":
                correspondents.selectCorrespondentTypeFromDropdown("Constituent");
                correspondents.fillCorrespondentFields();
                dataInput.clickAddButton();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
    }

    @Then("the submitted correspondent should be visible in the list of correspondents")
    public void theSubmittedCorrespondentShouldBeVisibleInTheListOfCorrespondents() {
        correspondents.assertPrimaryCorrespondent();
    }

    @And("I remove the primary correspondent")
    public void removePrimaryCorrespondent() {
        correspondents.removePrimaryCorrespondent();
    }

    @Then("there shouldn't be a primary correspondent displayed")
    public void thereShouldntBeAPrimaryCorrespondentDisplayed() {
        correspondents.assertNoPrimaryCorrespondentDisplayed();
    }

    @And("I edit the primary correspondents name")
    public void iEditThePrimaryCorrespondent() {
        correspondents.editPrimaryCorrespondent();
    }

    @Then("the correspondents name should be updated")
    public void theCorrespondentsNameShouldBeUpdated() {
        correspondents.assertPrimaryCorrespondent();
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
        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        dataInput.selectACorrespondenceReceivedChannel();
        dataInput.selectASpecificCopyToNoTenOption("No");
        dataInput.selectASpecificHomeSecInterestOption(interest);
        if (minCase()) {
            dataInput.selectAHomeSecReplyOption();
        }
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    @Then("the Home Secretary interest decision should match the one displayed in the summary tab")
    public void assertHomeSecInterestInputMatchesSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.assertHomeSecInterestMatchesDecisionAtDataInput();
    }

    @And("I complete the Data Input stage, selecting that the case is a potential Home Secretary Reply case")
    public void iCompleteTheDataInputStageSelectingThatTheCaseIsAPotentialHomeSecretaryReplyCase() {
        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        dataInput.selectACorrespondenceReceivedChannel();
        dataInput.selectASpecificCopyToNoTenOption("No");
        dataInput.selectAHomeSecInterestOption();
        dataInput.selectASpecificHomeSecReplyOption("Yes");
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    @When("I submit an invalid {string} date")
    public void iSubmitAnInvalidDate(String dateField) {
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
        safeClickOn(continueButton);
    }

    @But("I do not enter a {string} date")
    public void iDoNotEnterA(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED":
                dataInput.clearDateCorrespondenceReceived();
                break;
            case "CORRESPONDENCE SENT":
                dataInput.clearDateCorrespondenceSent();
                break;
            default:
                pendingStep(fieldName + " is not defined within " + getMethodName());
        }
        safeClickOn(continueButton);
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

    @Then("the Add a correspondent link is displayed")
    public void linkIsDisplayed() {
        correspondents.assertAddACorrespondentLinkIsDisplayed();
    }
}
