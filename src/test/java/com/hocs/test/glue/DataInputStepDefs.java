package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static junit.framework.TestCase.fail;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.DataInputQADecision;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.draft.Qa;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    DataInputQADecision dataInputQADecision;

    MarkUpDecision markUpDecision;

    Page page;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Qa qa;


    @When("^I complete the Data Input stage$")
    public void completeDataInputStage(){ dataInput.dataInputFullFlow(); }


    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {
        recordCorrespondentDetails.clickAdditionalCorrespondentYes();
        page.clickContinueButton();
        dataInput.clickCorrespondentIsNotAMember();
        page.clickContinueButton();
        recordCorrespondentDetails.fillMandatoryFields();
        page.clickContinueButton();
    }

    @When("^I enter correspondence data manually$")
    public void iEnterCorrespondenceDataManually() {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        page.clickContinueButton();
        dataInput.clickAddCorrespondentLink();
        recordCorrespondentDetails.fillMandatoryFields();
        page.clickAddButton();
    }

    @When("^I select to correspond with a member from the dropdown$")
    public void iSelectToCorrespondWithAMemberFromTheDropdown() {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        page.clickContinueButton();
        dataInput.clickCorrespondentIsAMember();
        page.clickContinueButton();
        recordCorrespondentDetails.selectMemberFromDropdownByIndex(1);
        page.clickContinueButton();
    }

    @When("^I select to correspond with \"([^\"]*)\" from the search function$")
    public void iSelectToCorrespondWithAMemberFromTheSearchFunction(String minister) {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        page.clickContinueButton();
        dataInput.clickCorrespondentIsAMember();
        page.clickContinueButton();
        recordCorrespondentDetails.selectMemberFromDropdownByName(minister);
        page.clickContinueButton();
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
                System.out.println(decision
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                decision = null;
                assumeNotNull(decision);
        }
        page.clickFinishButton();
    }

    @When("^I do not select a Data Input QA response$")
    public void iDoNotSelectADataInputQAResponse() {
        page.clickFinishButton();
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
                System.out.println(channel
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                channel = null;
                assumeNotNull(channel);
        }
    }

    @When("^I select to add a correspondent that \"([^\"]*)\" a member$")
    public void iSelectToAddACorrespondent(String member) {
        switch (member.toUpperCase()) {
            case "IS":
                break;
            case "IS NOT":
                break;
                default:
                    System.out.println(member
                            + " is not defined within " + getClass().getSimpleName()
                            + " class, " + getMethodName() + " method");
                    member = null;
                    assumeNotNull(member);
        }

    }

    @Then("^they should be added to the list of correspondents$")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        recordCorrespondentDetails.assertPrimaryCorrespondent();
    }
}
