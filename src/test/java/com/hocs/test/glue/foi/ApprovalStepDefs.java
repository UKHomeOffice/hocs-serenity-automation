package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.Approval;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ApprovalStepDefs extends BasePage {

    Approval approval;

    @And("I add an Approval request to the case with the {string} status")
    public void iAddAnApprovalRequestToTheCaseWithTheStatus(String status) {
        approval.addAnApprovalRequestWithStatus(status);
    }

    @Then("the status of the approval request should be displayed as {string}")
    public void thhStatusOfTheApprovalRequestShouldBeDisplayedAs(String status) {
        approval.assertStatusOfApprovalRequest(status);
    }
}
