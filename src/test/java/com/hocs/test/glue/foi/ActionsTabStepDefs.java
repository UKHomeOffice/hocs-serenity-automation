package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.foi.ActionsTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActionsTabStepDefs extends BasePage {

    ActionsTab actionsTab;

    ConfirmationScreens confirmationScreens;

    @And("I view the actions tab of the case")
    public void iSelectTheActionsTab() {
        confirmationScreens.goToCaseFromConfirmationScreen();
        actionsTab.selectActionsTab();
    }

    // Extensions

    @And("I select to extend the deadline of the FOI case")
    public void iSelectToExtendTheDeadlineOfTheFOICase() {
        actionsTab.clickExtendThisCase();
    }

    @And("I select a type of extension")
    public void iSelectATypeOfExtension() {
        actionsTab.selectAnExtensionType();
        waitABit(500);
    }

    @And("I select how many days to extend the deadline by")
    public void iSelectHowManyDaysToExtendTheDeadlineByAndWhenToExtendItFrom() {
        actionsTab.selectHowManyDayToExtendDeadlineBy();
    }

    @And("I select to extend the case by {string} day(s) from {string}")
    public void iSelectToExtendTheCaseByDayFrom(String amountOfDays, String startPoint) {
        actionsTab.selectASpecificStartPointToExtendDeadlineFrom(startPoint);
        actionsTab.selectASpecificAmountOfDaysToExtendDeadlineBy(amountOfDays);
    }

    @And("I submit a reason for the extension")
    public void iSubmitAReasonForTheExtension() {
        actionsTab.enterAReasonForTheExtension();
        clickTheButton("Extend Case");
    }

    @Then("I should see a confirmation message stating that the case has been extended")
    public void iShouldSeeAConfirmationMessageStatingThatTheCaseHasBeenExtended() {
        confirmationScreens.assertCaseExtensionConfirmationDisplayed();
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    @And("I select that the case should be extended from {string}")
    public void iSelectThatTheCaseShouldBeExtendedFrom(String extensionStartPoint) {
        actionsTab.selectASpecificStartPointToExtendDeadlineFrom(extensionStartPoint);
    }

    @Then("I am unable to select an amount of days to extend the case by")
    public void iAmUnableToSelectAnAmountOfDaysToExtendTheCaseBy() {
        actionsTab.assertThatNoSelectableOptionsPresentInAmountOfWorkingsDaysDropdown();
    }

    // Appeals

    @And("I select to add an appeal to the case")
    public void iSelectToAddAnAppealToTheCase() {
        actionsTab.clickAddAnAppeal();
    }

    @And("I submit details of the appeal type")
    public void iSubmitDetailsOfTheAppealType()  {
        actionsTab.addAnAppealToTheCase();
    }

    @Then("I should see a confirmation message stating that the appeal has been registered")
    public void iShouldSeeAConfirmationMessageStatingThatTheAppealHasBeenRegistered() {
        confirmationScreens.assertAppealRegisteredConfirmationDisplayed();
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    @And("the registered appeal should have the status {string} in the actions tab")
    public void theRegisteredAppealShouldHaveTheStatusInTheActionsTab(String appealStatus) {
        actionsTab.assertStatusOfAppealIs(appealStatus);
    }

    @When("I update and complete the registered appeal")
    public void iUpdateAndCompleteTheRegisteredAppeal() {
        actionsTab.selectActionsTab();
        actionsTab.completeAppeal();
    }

    @Then("I should see a confirmation message stating that the appeal has been updated")
    public void iShouldSeeAConfirmationMessageStatingThatTheAppealHasBeenUpdated() {
        confirmationScreens.assertAppealUpdatedConfirmationDisplayed();
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    // Record Interest

    @When("I select to record an interest in the case")
    public void iSelectToRecordAnInterestInTheCase() {
        actionsTab.clickRecordInterest();
    }

    @And("I submit details of the interest the external party has in the case")
    public void iSubmitDetailsOfTheInterestTheExternalPartyHasInTheCase() {
        actionsTab.selectSpecificTypeOfInterest("External Interest");
        actionsTab.selectAInterestedParty();
        actionsTab.enterDetailsOfInterest("Test Details of Interest");
        clickTheButton("Add");
    }

    @Then("I should see a confirmation message stating that the external interest has been registered")
    public void iShouldSeeAConfirmationMessageStatingThatTheExternalInterestHasBeenRegistered() {
        confirmationScreens.assertExternalInterestRegisteredConfirmationDisplayed();
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    @And("the( updated) details of the interest should be visible in the actions tab")
    public void theDetailsOfTheInterestShouldBeVisibleInTheActionsTab() {
        actionsTab.selectActionsTab();
        actionsTab.assertDetailsOfRecordedInterestVisible();
    }

    @When("I update the registered interest")
    public void iUpdateTheRegisteredInterest() {
        actionsTab.selectToUpdateRecordedInterest();
        actionsTab.enterDetailsOfInterest("Test Details of Interest - Amended");
        clickTheButton("Update");
    }

    @Then("I should see a confirmation message stating that the external interest has been updated")
    public void iShouldSeeAConfirmationMessageStatingThatTheExternalInterestHasBeenUpdated() {
        confirmationScreens.assertExternalInterestUpdatedConfirmationDisplayed();
        confirmationScreens.goToCaseFromConfirmationScreen();
    }
}
