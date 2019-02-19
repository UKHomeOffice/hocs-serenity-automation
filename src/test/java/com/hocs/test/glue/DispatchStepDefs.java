package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.dispatch.Dispatch;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs {

    @Managed

    Page page;

    Dispatch dispatch;

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    @When("^I complete the dispatch stage$")
    public void completeTheDispatchStage() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpathDoubleClick();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkTextAndStoreResultingElement();
        dispatch.clickDispatchAcceptRadioButton();
        dispatch.clickContinueButton();
    }

    @When("^I click the continue button on the are you able to dispatch screen$")
    public void clickContinueButtonOnAreYouAbleToDispatchScreen() {
        dispatch.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have selected whether the case can be dispatched$")
    public void assertThatAreYouAbleToDispatchErrorMessage() {
        dispatch.assertAreYouAbleToDispatchErrorMessageIsShown();
    }

    @When("^I click the finish button on the unable to dispatch screen$")
    public void clickContinueButtonOnUnableToDispatchScreen() {
        dispatch.clickDispatchRejectRadioButton();
        dispatch.clickContinueButton();
        dispatch.clickFinishButton();
    }

    @Then("^an error message should be displayed as I have not entered a reason for not dispatching in the text box$")
    public void assertThatWhyAreYouUnableToDispatchErrorMessageIsShown() {
        dispatch.assertWhyAreYouUnableToDispatchErrorMessageIsShown();
    }
}
