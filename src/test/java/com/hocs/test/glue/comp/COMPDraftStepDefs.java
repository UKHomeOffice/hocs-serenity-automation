package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPDraft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class COMPDraftStepDefs extends BasePage {

    COMPDraft compDraft;

    @And("I select the {string} action at the Service Draft stage")
    public void iSendTheCaseToQA(String action) {
        compDraft.selectActionAtServiceDraft(action);
    }

    @And("I escalate the case to WFM at Service Draft stage")
    public void iEscalateTheCaseToWFMAtServiceDraftStage() {
        compDraft.selectActionAtServiceDraft("Escalate case to WFM");
        compDraft.submitEscalationReason();
    }

    @Then("an error message is displayed as I have not uploaded a document")
    public void anErrorMessageIsDisabledAsIHaveNotUploadedADocument() {
        compDraft.assertErrorMessageIsDisplayed("Primary Draft Document");
    }
}
