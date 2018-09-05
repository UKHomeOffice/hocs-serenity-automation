package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.markup.MarkUpDecision;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs {

    @Steps(shared = true)
    private
    GenericInputStepDefs genericInputStepDefs;

    private DataInput dataInput;

    MarkUpDecision markUpDecision;

    private Page page;

    private RecordCorrespondentDetails recordCorrespondentDetails;


    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {

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
}
