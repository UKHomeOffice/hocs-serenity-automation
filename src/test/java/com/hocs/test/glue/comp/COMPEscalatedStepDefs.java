package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPEscalated;
import io.cucumber.java.en.And;

public class COMPEscalatedStepDefs extends BasePage {

    COMPEscalated COMPEscalated;

    @And("I select to return the case to Triage")
    public void iSelectToReturnTheCaseToTriage() {
        COMPEscalated.selectActionAtServiceEscalated("Return case to Triage");
    }

    @And("I select to send the case to drafting")
    public void iSelectToSendTheCaseToDrafting() {
        COMPEscalated.selectActionAtServiceEscalated("Case ready for drafting");
    }
}
