package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPEscalated;
import io.cucumber.java.en.And;

public class COMPEscalatedStepDefs extends BasePage {

    COMPEscalated compEscalated;

    @And("I select to return the case to Triage")
    public void iSelectToReturnTheCaseToTriage() {
        compEscalated.selectActionAtServiceEscalated("Return case to Triage");
    }

    @And("I select to send the case to drafting")
    public void iSelectToSendTheCaseToDrafting() {
        compEscalated.selectActionAtServiceEscalated("Case ready for drafting");
    }
}
