package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;

public class MinisterSignOffStepDefs {

    @Managed
    WebDriver driver;

    Page page;

    DataInput dataInput;

    Homepage homepage;

    MinisterSignOff minister;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    @When("^I complete the minister sign off stage$")
    public void completeTheMinisterSignOffStage(){
        homepage.selectMinisterForLordsTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        minister.clickMinisterSignOffAcceptRadioButton();
        minister.clickContinueButton();
    }

    @When("^the case is rejected by the Minister$")
    public void rejectAtMinisterSignOff() {
        homepage.selectMinisterForLordsTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        minister.clickMinisterSignOffRejectRadioButton();
        minister.clickContinueButton();
        minister.enterMinisterRejectionNote();
        minister.clickContinueButton();
    }

    @When("^I click the continue button on the approve response screen$")
    public void clickContinueButtonOnApproveReponseScreen() {
        workstacks.clickAllocateToMeButton();
        minister.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not selected a radio button on the approve response screen$")
    public void assertThatApproveResponseErrorMessageIsShown() {
        minister.assertDoYouApproveTheResponseErrorMessage();
    }

    @When("^I click the continue button on the minister sign off feedback response screen$")
    public void clickContinueButtonOnFeedbackResponseMinisterSignOffScreen() {
        workstacks.clickAllocateToMeButton();
        minister.clickMinisterSignOffRejectRadioButton();
        minister.clickContinueButton();
        minister.sleep(500);
        minister.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not entered feedback in the text box$")
    public void assertThatFeedbackResponseMinisterSignOffErrorMessageIsShown() {
        minister.assertFeedbackResponseMinisterSignOffErrorMessage();
    }
}
