package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.to.Draft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DraftStepDefs extends BasePage {

    Documents documents;

    Draft draft;

    @And("I send the Treat Official case to Dispatch")
    public void iSendTheTreatOfficialCaseToDispatch() {
        documents.recordPrimaryDraftDocument();
        draft.selectTheAction("Send to Dispatch");
        clickTheButton("Finish");
    }

    @And("I send the Treat Official case to QA")
    public void iSendTheTreatOfficialCaseToQA() {
        documents.recordPrimaryDraftDocument();
        draft.selectTheAction("Move to QA");
        clickTheButton("Finish");
    }

    @When("I return the case to Triage")
    public void iReturnTheCaseToTriage() {
        draft.selectTheAction("Return to Triage");
        clickTheButton("Finish");
    }

    @And("I change the business unit of the case")
    public void iChangeTheBusinessUnitOfTheCase() {
        draft.selectADifferentBusinessUnit();
    }
}