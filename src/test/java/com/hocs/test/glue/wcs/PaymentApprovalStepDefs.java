package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.PaymentApproval;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaymentApprovalStepDefs {

    PaymentApproval paymentApproval;

    @When("I select to return claim to the Casework team due to PNC failure")
    public void iSelectToReturnClaimToTheCaseworkTeamDueToPNCFailure() {
        paymentApproval.returnToCaseworkTeam();
    }

    @When("I select that the payment was approved and is ready to be sent")
    public void iSelectThatThePaymentWasApprovedAndIsReadyToBeSent() {
        paymentApproval.selectPaymentApprovedReadyToSend();
    }

    @Then("an error message is displayed as I have not selected an option at the Payment Approval stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtThePaymentApprovalStage() {
        paymentApproval.assertPaymentIsRequiredPaymentApprovalErrorMessage();
    }
}
