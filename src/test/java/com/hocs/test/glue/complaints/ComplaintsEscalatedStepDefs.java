package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsEscalated;
import io.cucumber.java.en.And;

public class ComplaintsEscalatedStepDefs extends BasePage {

    ComplaintsEscalated complaintsEscalated;

    @And("I select to return the case to Triage")
    public void iSelectToReturnTheCaseToTriage() {
        if (!bfCase() && !bf2Case()) {
            complaintsEscalated.selectActionAtEscalatedStage("Return case to Triage");
        } else {
            complaintsEscalated.selectActionAtEscalatedStage("Return to triage");
        }
    }

    @And("I select to send the case to drafting")
    public void iSelectToSendTheCaseToDrafting() {
        complaintsEscalated.selectActionAtEscalatedStage("Case ready for drafting");
    }

    @And("I select to return the case to Draft")
    public void iSelectToReturnTheCaseToDraft() {
        complaintsEscalated.selectActionAtEscalatedStage("Return case to Draft");
    }

    @And("I select to return the case to Investigation")
    public void iSelectToReturnTheCaseToInvestigation() {
        complaintsEscalated.selectActionAtEscalatedStage("Return case to Investigation");
    }
}
