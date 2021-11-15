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
        confirmationScreens.goToCaseFromSuccessfulCreationScreen();
        actionsTab.selectActionsTab();
    }

    @And("I select to extend the deadline of the FOI case")
    public void iSelectToExtendTheDeadlineOfTheFOICase() {
        actionsTab.clickExtendCaseHypertext();
    }

    @And("I select a type of extension")
    public void iSelectATypeOfExtension() {
        actionsTab.selectAnExtensionType();
        waitABit(500);
    }

    @And("I select how many days to extend the deadline by and when to extend it from")
    public void iSelectHowManyDaysToExtendTheDeadlineByAndWhenToExtendItFrom() {
        actionsTab.selectHowManyDayToExtendDeadlineBy();
        actionsTab.selectWhenToExtendDeadlineFrom();
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
        actionsTab.completeAppeal();
    }

    @Then("I should see a confirmation message stating that the appeal has been updated")
    public void iShouldSeeAConfirmationMessageStatingThatTheAppealHasBeenUpdated() {
        confirmationScreens.assertAppealUpdatedConfirmationDisplayed();
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    @And("I select to extend the case by {string} day from {string}")
    public void iSelectToExtendTheCaseByDayFrom(String amountOfDays, String startPoint) {
        actionsTab.selectASpecificAmountOfDaysToExtendDeadlineBy(amountOfDays);
        actionsTab.selectASpecificStartPointToExtendDeadlineFrom(startPoint);
    }

    @Then("I am unable to extend the case by this amount")
    public void iAmUnableToExtendTheCaseByThisAmount() {
    }
}
