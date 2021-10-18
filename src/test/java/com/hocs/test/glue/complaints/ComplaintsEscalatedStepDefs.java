package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsEscalated;
import io.cucumber.java.en.And;

public class ComplaintsEscalatedStepDefs extends BasePage {

    ComplaintsEscalated complaintsEscalated;

    @And("I select to return the case to Triage")
    public void iSelectToReturnTheCaseToTriage() {
        complaintsEscalated.selectActionAtServiceEscalated("Return case to Triage");
    }

    @And("I select to send the case to drafting")
    public void iSelectToSendTheCaseToDrafting() {
        complaintsEscalated.selectActionAtServiceEscalated("Case ready for drafting");
    }
}
