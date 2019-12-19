package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class MinisterSignOffStepDefs extends Page {

    Homepage homepage;

    MinisterSignOff minister;

    Workstacks workstacks;

    @When("^I complete the minister sign off stage$")
    public void completeTheMinisterSignOffStage(){
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(minister.ministerSignOffAcceptRadioButton);
        clickOn(minister.continueButton);
    }

    @When("^I complete the minister sign off stage for \"([^\"]*)\"$")
    public void completeTheMinisterSignOffStagePerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                clickOn(minister.ministerSignOffAcceptRadioButton);
                clickOn(minister.continueButton);
                break;
            case "DCU TRO" :
                homepage.goHome();
                break;
            case "DCU N10" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("^an error message should be displayed as I have not selected a radio button on the approve response screen$")
    public void assertThatApproveResponseErrorMessageIsShown() {
        minister.assertDoYouApproveTheResponseErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered feedback in the text box$")
    public void assertThatFeedbackResponseMinisterSignOffErrorMessageIsShown() {
        minister.assertFeedbackResponseMinisterSignOffErrorMessage();
    }

}
