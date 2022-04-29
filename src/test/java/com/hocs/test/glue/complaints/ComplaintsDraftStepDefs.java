package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsDraft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ComplaintsDraftStepDefs extends BasePage {

    ComplaintsDraft complaintsDraft;

    @And("I select the {string} action at the (Service )Draft stage")
    public void iSelectTheActionAtTheDraftStage(String action) {
        complaintsDraft.selectActionAtDraft(action);
    }

    @And("I escalate the case to WFM at( Service) Draft stage")
    public void iEscalateTheCaseToWFMAtServiceDraftStage() {
        complaintsDraft.selectActionAtDraft("Escalate case to WFM");
        complaintsDraft.submitEscalationReason();
    }

    @And("I complete the Complaint Telephone Response screen")
    public void iCompleteTheComplaintTelephoneResponseScreen() {
        complaintsDraft.completePOGRComplaintTelephoneResponseScreen();
    }

    @Then("an error message is displayed as I have not uploaded a document")
    public void anErrorMessageIsDisabledAsIHaveNotUploadedADocument() {
        complaintsDraft.assertErrorMessageIsDisplayed("Primary Draft Document");
    }
}
