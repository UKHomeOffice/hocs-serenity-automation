package com.hocs.test.glue.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.ContributionRequests;
import com.hocs.test.pages.ukvi.MPAMMultipleContributions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MultipleContributionsStepDefs extends BasePage {

    MPAMMultipleContributions MPAMMultipleContributions;

    ContributionRequests contributionRequests;

    @And("I choose to {string} the contribution request at the multiple contribution stage")
    public void iChooseToTheContributionRequestAtTheMultipleContributionStage(String action) {
        contributionRequests.selectActionForIndividualContributionRequest(action);
    }

    @And("I add {int} contribution requests to the case and move the case to the Contribution Request stage")
    public void iAddContributionRequestsToTheCase(int numberOfRequests) {
        MPAMMultipleContributions.addMultipleContributionRequests(numberOfRequests);
    }

    @And("I select the {string} action at the contributions requested stage")
    public void iCompleteTheContributionsRequestedStage(String action) {
        MPAMMultipleContributions.selectActionAtContributionRequestedStage(action);
    }

    @And("I select to {string} the case that has been completed at the Draft-Contribution Request stage")
    public void iSelectToTheCaseThatHasBeenCompletedAtTheDraftContributionRequestStage(String action) {
        MPAMMultipleContributions.retainOrUnallocateDraftContributionRequestedCase(action);
    }

    @And("I edit the due date of a contribution request")
    public void iEditTheDueDateOfAContributionRequest() {
        contributionRequests.editContributionDueDate();
    }

    @And("I test the validation at the {string} screen")
    public void iTestTheValidationAtTheScreen(String screen) {
        MPAMMultipleContributions.triggerValidationAtContributionRequestScreens(screen);
        switch (screen.toUpperCase()) {
            case "ADD CONTRIBUTION REQUEST":
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Business Area");
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Business Unit");
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution request date");
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution due date");
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("What you are requesting");
                break;
            case "CONTRIBUTIONS REQUESTED":
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Actions");
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Case Contributions Must be Completed or Cancelled");
                break;
            case "CONTRIBUTION REQUEST FULFILLMENT":
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution Received Date");
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Contribution Completion Notes");
                break;
            case "UNALLOCATE CASE":
                MPAMMultipleContributions.assertRequiredErrorMessageIsDisplayed("Case Actions");
                break;
            default:
                pendingStep(screen + " is not defined within " + getMethodName());
        }
    }

    @Then("there are {int} contribution requests added to the case")
    public void thereAreContributionRequestsAddedToTheCase(int numberOfRequests) {
        MPAMMultipleContributions.assertNumberOfContributionsAddedToCase(numberOfRequests);
    }

    @And("I add a {string} contribution request")
    public void iAddAContribution(String contributionType) {
        contributionRequests.addAContribution(contributionType, getDatePlusMinusNDaysAgo(-1), getDatePlusMinusNDaysAgo(5));
    }

    @And("I {string} the contribution request")
    public void iTheContribution(String action) {
        contributionRequests.selectActionForIndividualContributionRequest(action);
    }

    @Then("the {string} contribution request should be marked as {string}")
    public void theContributionRequestShouldBeMarkedAs(String contributionType, String action) {
        contributionRequests.assertThatContributionRequestOfTypeIsMarkedAs(contributionType, action);
    }

    @And("I add a {string} contribution with a due date in the past")
    public void iAddAContributionWithADueDateInThePast(String contributionType) {
        contributionRequests.addAContribution(contributionType, getDatePlusMinusNDaysAgo(-1), getDatePlusMinusNDaysAgo(-5));
    }
}