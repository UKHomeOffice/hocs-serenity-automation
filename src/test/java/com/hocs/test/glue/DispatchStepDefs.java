package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs extends Page {

    @Managed

    Page page;

    Dispatch dispatch;

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    @When("^I complete the dispatch stage$")
    public void completeTheDispatchStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(dispatch.dispatchAcceptRadioButton);
        clickOn(dispatch.continueButton);
    }

    @When("^I click the continue button on the are you able to dispatch screen$")
    public void clickContinueButtonOnAreYouAbleToDispatchScreen() {
        page.clickOn(dispatch.continueButton);
    }

    @Then("^an error message should be displayed as I have selected whether the case can be dispatched$")
    public void assertThatAreYouAbleToDispatchErrorMessage() {
        dispatch.assertAreYouAbleToDispatchErrorMessageIsShown();
    }

    @When("^I click the finish button on the unable to dispatch screen$")
    public void clickContinueButtonOnUnableToDispatchScreen() {
        clickOn(dispatch.dispatchRejectRadioButton);
        clickOn(dispatch.continueButton);
        clickOn(dispatch.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered a reason for not dispatching in the text box$")
    public void assertThatWhyAreYouUnableToDispatchErrorMessageIsShown() {
        dispatch.assertWhyAreYouUnableToDispatchErrorMessageIsShown();
    }
}
