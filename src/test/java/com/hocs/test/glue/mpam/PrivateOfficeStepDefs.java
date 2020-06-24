package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.PrivateOffice;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrivateOfficeStepDefs extends BasePage {

    PrivateOffice privateOffice;

    @And("the user triggers the {string} error message at Private Office by not entering the correct information")
    public void triggerErrorMessage(String errorMessage) {
        privateOffice.triggerErrorMessage(errorMessage);
    }

    @And("then the {string} error message should be displayed at Private Office")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                privateOffice.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "DISPATCHED DATE":
                privateOffice.assertDispatchedDateErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @When("I select the {string} action at Private Office stage")
    public void iSelectTheActionAtPrivateOfficeStage(String action) {
        switch (action.toUpperCase()) {
            case "DISPATCHED":
                safeClickOn(privateOffice.dispatchedRadioButton);
                break;
            case "DRAFT REJECTED BY PRIVATE OFFICE":
                safeClickOn(privateOffice.draftRejectedRadioButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
    }

    @And("I submit a reason to reject the case back to Draft stage")
    public void iEnterAReasonToRejectTheCaseBackToDraftStage() {
        privateOffice.submitReasonToRejectToDraft("Test reject to draft at private office");
    }

    @And("the rejection reason entry box should be visible")
    public void theRejectionReasonEntryBoxShouldBeVisible() {
        privateOffice.assertThatRejectionReasonTextAreaVisible();
    }

    @And("I enter a date of dispatch and confirm to close the case")
    public void iEnterADispatchedDateAndConfirmToCloseTheCase() {
        privateOffice.dispatchedDateInput(1, 1, 2001);
        safeClickOn(privateOffice.confirmAndCloseCaseButton);
    }

    @Then("I can see the previous selected response channel is still selected")
    public void iCanSeeThePreviousSelectedResponseChannelIsSelected() {
        privateOffice.assertThatResponseChannelIsPreSelected();
    }
}
