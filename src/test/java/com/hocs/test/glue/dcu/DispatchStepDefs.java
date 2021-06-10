package com.hocs.test.glue.dcu;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.dcu.Dispatch;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs extends BasePage {

    @Managed

    Dispatch dispatch;

    Dashboard dashboard;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the dispatch stage")
    public void completeTheDispatchStage() {
        if (!dispatch.dispatchAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        safeClickOn(dispatch.dispatchAcceptRadioButton);
        safeClickOn(dispatch.continueButton);
    }

    @Then("an error message should be displayed as I have selected whether the case can be dispatched")
    public void assertThatAreYouAbleToDispatchErrorMessage() {
        dispatch.assertAreYouAbleToDispatchErrorMessageIsShown();
    }

    @Then("an error message should be displayed as I have not entered a reason for not dispatching in the text box")
    public void assertThatWhyAreYouUnableToDispatchErrorMessageIsShown() {
        dispatch.assertWhyAreYouUnableToDispatchErrorMessageIsShown();
    }
}
