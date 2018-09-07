package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.DataInputQADecision;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.markup.MarkUpDecision;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs {

    @Steps(shared = true)
    private
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    DataInputQADecision dataInputQADecision;

    MarkUpDecision markUpDecision;

    Page page;

    RecordCorrespondentDetails recordCorrespondentDetails;


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
        dataInput.clickCorrespondentIsNotAMember();
        page.clickContinueButton();
        recordCorrespondentDetails.fillMandatoryFields();
        page.clickContinueButton();
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
        }
        page.clickFinishButton();
    }

    @When("^I do not select a Data Input QA response$")
    public void iDoNotSelectADataInputQAResponse() {
        page.clickFinishButton();
    }
}
