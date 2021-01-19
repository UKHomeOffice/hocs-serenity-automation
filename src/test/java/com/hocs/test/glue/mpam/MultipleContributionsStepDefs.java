package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.MultipleContributions;
import com.hocs.test.pages.mpam.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MultipleContributionsStepDefs extends BasePage {

    MultipleContributions multipleContributions;

    @And("I choose to {string} the contribution request at the multiple contribution stage")
    public void iChooseToTheContributionRequestAtTheMultipleContributionStage(String action) {
        multipleContributions.selectActionForIndividualContributionRequest(action);
    }

    @And("I add {int} contribution requests to the case and move the case to the Contribution Request stage")
    public void iAddContributionRequestsToTheCase(int numberOfRequests) {
        multipleContributions.addMultipleContributionRequests(numberOfRequests);
    }

    @And("I select the {string} action at the contributions requested stage")
    public void iCompleteTheContributionsRequestedStage(String action) {
        multipleContributions.selectActionAtContributionRequestedStage(action);
    }

    @And("I select to {string} the case that has been completed at the Draft-Contribution Request stage")
    public void iSelectToTheCaseThatHasBeenCompletedAtTheDraftContributionRequestStage(String action) {
        multipleContributions.retainOrUnallocateDraftContributionRequestedCase(action);
    }

    @And("I edit the due date of a contribution request")
    public void iEditTheDueDateOfAContributionRequest() {
        multipleContributions.editContributionDueDate();
    }

    @And("I test the validation at the {string} screen")
    public void iTestTheValidationAtTheScreen(String screen) {
        multipleContributions.triggerValidationAtContributionRequestScreens(screen);
        switch (screen.toUpperCase()) {
            case "ADD CONTRIBUTION REQUEST":
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Business Area");
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Business Unit");
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution request date");
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution due date");
                multipleContributions.assertRequiredErrorMessageIsDisplayed("What you are requesting");
                break;
            case "CONTRIBUTIONS REQUESTED":
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Actions");
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Case Contributions Must be Completed or Cancelled");
                break;
            case "CONTRIBUTION REQUEST FULFILLMENT":
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution Received Date");
                multipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution Completion Notes");
                break;
            default:
                pendingStep(screen + " is not defined within " + getMethodName());
        }
    }

    @Then("the contribution request should be displayed as {string}")
    public void theContributionRequestShouldBeDisplayedAs(String action) {
        multipleContributions.assertThatContributionRequestHasBeen(action);
    }

    @Then("there are {int} contribution requests added to the case")
    public void thereAreContributionRequestsAddedToTheCase(int numberOfRequests) {
        multipleContributions.assertNumberOfContributionsAddedToCase(numberOfRequests);
    }
}