package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

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

    Dispatch dispatch;

    Homepage homepage;

    Workstacks workstacks;

    @When("^I complete the dispatch stage$")
    public void completeTheDispatchStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(dispatch.dispatchAcceptRadioButton);
        clickOn(dispatch.continueButton);
    }

    @When("^I complete the dispatch stage for \"([^\"]*)\"$")
    public void completeTheDispatchStagePerCaseType(String caseType) {
        switch(caseType.toUpperCase()) {
            case "DCU MIN" :
                break;
            case "DCU TRO" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I reject the case at Dispatch stage$")
    public void rejectAtDispatchStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        dispatch.selectDispatchRejectButton();
        clickOn(continueButton);
        typeInto(dispatch.dispatchRejectNoteField, "I reject this nothing");
        clickOn(finishButton);
    }

    @When("^I click the continue button on the are you able to dispatch screen$")
    public void clickContinueButtonOnAreYouAbleToDispatchScreen() {
        clickOn(dispatch.continueButton);
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
