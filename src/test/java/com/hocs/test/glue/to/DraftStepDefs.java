package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.to.Draft;
import com.hocs.test.pages.to.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DraftStepDefs extends BasePage {

    Documents documents;

    Draft draft;

    Triage triage;

    @And("I send the Treat Official case to Dispatch")
    public void iSendTheTreatOfficialCaseToDispatch() {
        selectTheStageAction("Send to Dispatch");
        clickFinishButton();
    }

    @And("I send the Treat Official case to QA")
    public void iSendTheTreatOfficialCaseToQA() {
        selectTheStageAction("Move to QA");
        clickFinishButton();
    }

    @When("I select to return the TO case to Triage")
    public void iReturnTheCaseToTriage() {
        selectTheStageAction("Return to Triage");
        clickFinishButton();
    }

    @And("I change the Business Unit Type and Business Unit of the case")
    public void iChangeTheBusinessUnitOfTheCase() {
        draft.selectADifferentBusinessUnitType();
        waitABit(500);
        triage.selectABusinessUnit();
    }

    @And("I submit a reason to return the case to Triage")
    public void iSubmitAReasonToReturnTheCaseToTriage() {
        draft.enterReasonToReturnCasetoTriage();
        clickContinueButton();
    }
}
