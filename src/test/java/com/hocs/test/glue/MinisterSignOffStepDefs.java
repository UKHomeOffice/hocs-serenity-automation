package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;

public class MinisterSignOffStepDefs extends Page {

    @Managed
    WebDriver driver;

    Homepage homepage;

    MinisterSignOff minister;

    Workstacks workstacks;

    @When("^I complete the minister sign off stage$")
    public void completeTheMinisterSignOffStage(){
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(minister.minsterSignOffAcceptRadioButton);
        clickOn(minister.continueButton);
    }

    @When("^I complete the minister sign off stage for \"([^\"]*)\"$")
    public void completeTheMinisterSignOffStagePerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                clickOn(minister.minsterSignOffAcceptRadioButton);
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

    @When("^the case is rejected by the Minister$")
    public void rejectAtMinisterSignOff() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(minister.ministerSignOffRejectRadioButton);
        clickOn(minister.continueButton);
        minister.enterMinisterRejectionNote();
        clickOn(minister.continueButton);
    }

    @When("^I click the continue button on the approve response screen$")
    public void clickContinueButtonOnApproveResponseScreen() {
        clickOn(minister.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected a radio button on the approve response screen$")
    public void assertThatApproveResponseErrorMessageIsShown() {
        minister.assertDoYouApproveTheResponseErrorMessage();
    }

    @When("^I click the continue button on the minister sign off feedback response screen$")
    public void clickContinueButtonOnFeedbackResponseMinisterSignOffScreen() {
        clickOn(minister.ministerSignOffRejectRadioButton);
        clickOn(minister.continueButton);
        waitABit(500);
        clickOn(minister.continueButton);
    }

    @Then("^an error message should be displayed as I have not entered feedback in the text box$")
    public void assertThatFeedbackResponseMinisterSignOffErrorMessageIsShown() {
        minister.assertFeedbackResponseMinisterSignOffErrorMessage();
    }
}
