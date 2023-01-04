package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.wcs.Casework;
import com.hocs.test.pages.wcs.Registration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CaseworkStepDefs extends BasePage {

    Casework casework;

    Dashboard dashboard;

    Registration registration;

    @Then("an error message is displayed as I have not selected a Claim status")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAClaimStatus() {
        casework.assertCaseworkClaimStatusErrorMessage();
    }

    @When("I put the claim on hold at the Casework stage")
    public void iPutTheClaimOnHoldAtTheCaseworkStage() {
        casework.putTheClaimOnHold();
        
    }

    @When("I take the claim off of hold at the Casework stage")
    public void iTakeTheClaimOffOfHoldAtTheCaseworkStage() {
        casework.takeTheClaimOffHold();
    }

    @And("I select to send the claim back to Eligibility from Casework stage")
    public void iSelectToSendTheClaimBackToEligibilityFromCaseworkStage() {
        casework.selectToSendClaimToEligibility();
    }

    @And("I select to send the claim back to Registration from Casework stage")
    public void iSelectToSendTheClaimBackToRegistrationFromCaseworkStage() {
        casework.selectToSendClaimToRegistration();
    }

    @Then("an error message is displayed as I have not entered a reason for returning the claim to Eligibility")
    public void anErrorMessageIsDisplayedAsIHaveNotEnteredAReasonForReturningTheClaimToEligibility() {
        casework.assertEligibilityReturnReasonErrorMessage();
    }

    @Then("an error message is displayed as I have not entered a reason for returning the claim to Registration")
    public void anErrorMessageIsDisplayedAsIHaveNotEnteredAReasonForReturningTheClaimToRegistration() {
        casework.assertRegistrationReturnReasonErrorMessage();
    }

    @And("I select that the offer is ready to QA")
    public void iSelectThatTheOfferIsReadyToQA() {
        casework.selectClaimStatusReadyToQA();
    }

    @And("I select to send the claim back to Triage from Casework stage")
    public void iSelectToSendTheClaimBackToTriageFromCaseworkStage() {
        casework.selectToSendClaimToTriage();
    }

    @Then("an error message is displayed as I have not entered a reason for returning the claim to Triage")
    public void anErrorMessageIsDisplayedAsIHaveNotEnteredAReasonForReturningTheClaimToTriage() {
        casework.assertTriageReturnReasonErrorMessage();
    }

    @And("I enter a reason")
    public void iEnterAReason() {
        casework.enterReasonInTextBox();
    }
}
