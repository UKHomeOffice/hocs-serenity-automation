package com.hocs.test.glue.comp;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.comp.ServiceDraft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ServiceDraftStepDefs extends BasePage {

    ServiceDraft serviceDraft;

    @And("I select the {string} action at the Service Draft stage")
    public void iSendTheCaseToQA(String action) {
        serviceDraft.selectActionAtServiceDraft(action);
    }

    @And("I escalate the case to WFM at Service Draft stage")
    public void iEscalateTheCaseToWFMAtServiceDraftStage() {
        serviceDraft.moveCaseFromServiceDraftToServiceEscalated();
    }

    @Then("an error message is displayed as I have not uploaded a document")
    public void anErrorMessageIsDisabledAsIHaveNotUploadedADocument() {
        serviceDraft.assertErrorMessageIsDisplayed("Primary Draft Document");
    }
}
