package com.hocs.test.glue.foi;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.foi.Allocation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class AllocationStepDefs extends BasePage {

    Allocation allocation;
    SummaryTab summaryTab;

    @And("I select {string} as the Directorate")
    public void iSelectAsTheDirectorate(String directorate) {
        allocation.selectDirectorate(directorate);
    }

    @And("I select {string} as the Acceptance Team")
    public void iSelectAsTheAcceptanceTeam(String team) {
        allocation.selectAcceptanceTeam(team);
        setSessionVariable("acceptanceTeam").to(team);
    }

    @Then("the Requested Question should be displayed in the summary tab")
    public void theRequestedQuestionShouldBeDisplayedInTheSummaryTab() {
        waitABit(500);
        summaryTab.selectSummaryTab();
        allocation.assertRequestQuestionIsCorrect();
    }

    @Then("the Allocation text is displayed")
    public void allocationTextIsDisplayed() {
        allocation.assertAllocationText();
    }
}
