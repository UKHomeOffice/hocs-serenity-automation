package com.hocs.test.glue;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.workstacks.Workstacks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs extends Page {

    @Managed

    Dispatch dispatch;

    Homepage homepage;

    Workstacks workstacks;

    @When("I complete the dispatch stage")
    public void completeTheDispatchStage() {
        if (homepage.myCases.isVisible()) {
            homepage.getCurrentCase();
            clickOn(workstacks.allocateToMeButton);
        }
        clickOn(dispatch.dispatchAcceptRadioButton);
        clickOn(dispatch.continueButton);
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
