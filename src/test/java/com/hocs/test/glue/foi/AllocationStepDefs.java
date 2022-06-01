package com.hocs.test.glue.foi;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.foi.Allocation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllocationStepDefs extends BasePage {

    Allocation allocation;

    @When("I select a Group")
    public void iSelectAGroup() {
        allocation.selectAGroup();
    }

    @And("I select an Account Manager")
    public void iSelectAnAccountManager() {
        allocation.selectAnAccountManager();
    }

    @Then("the Allocation text is displayed")
    public void allocationTextIsDisplayed() {
        allocation.assertAllocationText();
    }
}
