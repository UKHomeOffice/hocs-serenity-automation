package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsDraft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import static net.serenitybdd.core.Serenity.setSessionVariable;

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

    @Then("an error message is displayed as I have not uploaded a document")
    public void anErrorMessageIsDisabledAsIHaveNotUploadedADocument() {
        complaintsDraft.assertErrorMessageIsDisplayed("Primary Draft Document");
    }

    @And("I select to return the case back to Triage stage")
    public void iSelectToReturnTheCaseBackToTriageStage() {
        complaintsDraft.selectActionAtDraft("Reject - return to Triage");
    }

    @And("I submit a rejection reason")
    public void iSubmitARejectionReason() {
        String rejectionReason = enterTextIntoTextAreaWithHeading("Enter reason for rejection");
        setSessionVariable("rejectionReason").to(rejectionReason);
        clickTheButton("Reject");
    }

    @And("I select that the case was resolved by the phone call")
    public void iSelectThatTheCaseWasResolved() {
        complaintsDraft.selectIfResolvedByPhoneCall("Yes");
    }

    @And("I select that the case was not resolved by the phone call")
    public void iSelectThatTheCaseWasNotResolved() {
        complaintsDraft.selectIfResolvedByPhoneCall("No");
    }

    @And("I submit details of the phone call")
    public void iSubmitDetailsOfTheCall() {
        complaintsDraft.enterDateOfPhoneCall();
        complaintsDraft.enterDetailsOfPhoneCall();
        clickTheButton("Continue");
    }
}
