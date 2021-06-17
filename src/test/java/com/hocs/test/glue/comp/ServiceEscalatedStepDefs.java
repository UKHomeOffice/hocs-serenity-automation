package com.hocs.test.glue.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.comp.ServiceEscalated;
import io.cucumber.java.en.And;

public class ServiceEscalatedStepDefs extends BasePage {

    ServiceEscalated serviceEscalated;

    @And("I select to return the case to Triage")
    public void iSelectToReturnTheCaseToTriage() {
        safeClickOn(serviceEscalated.returnCaseToTriageRadioButton);
        clickTheButton("Confirm");
    }

    @And("I select to send the case to drafting")
    public void iSelectToSendTheCaseToDrafting() {
        safeClickOn(serviceEscalated.caseReadyForDraftingRadioButton);
        clickTheButton("Confirm");
    }
}
