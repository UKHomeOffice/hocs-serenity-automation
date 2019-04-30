package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs {

    @Managed

    Page page;

    Dispatch dispatch;

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    @When("^I complete the dispatch stage$")
    public void completeTheDispatchStage() {
        page.clickOn(homepage.performanceProcessTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpathStoreResultingElement();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(dispatch.dispatchAcceptRadioButton);
        page.clickOn(dispatch.continueButton);
    }

    @When("^I click the continue button on the are you able to dispatch screen$")
    public void clickContinueButtonOnAreYouAbleToDispatchScreen() {
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(dispatch.continueButton);
    }

    @Then("^an error message should be displayed as I have selected whether the case can be dispatched$")
    public void assertThatAreYouAbleToDispatchErrorMessage() {
        dispatch.assertAreYouAbleToDispatchErrorMessageIsShown();
    }

    @When("^I click the finish button on the unable to dispatch screen$")
    public void clickContinueButtonOnUnableToDispatchScreen() {
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(dispatch.dispatchRejectRadioButton);
        page.clickOn(dispatch.continueButton);
        page.clickOn(dispatch.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered a reason for not dispatching in the text box$")
    public void assertThatWhyAreYouUnableToDispatchErrorMessageIsShown() {
        dispatch.assertWhyAreYouUnableToDispatchErrorMessageIsShown();
    }
}
