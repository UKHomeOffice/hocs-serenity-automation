package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.foi.FOIDispatch;
import io.cucumber.java.en.And;

public class FOIDispatchStepDefs extends BasePage {

    FOIDispatch foiDispatch;

    Documents documents;

    @And("I select that I {string} want to dispatch the case")
    public void iSelectToDoYouWantToDispatchTheCase(String response) {
        if (response.equalsIgnoreCase("DO")) {
            foiDispatch.selectDoYouWantToDispatch("Yes");
        } else if (response.equalsIgnoreCase("DO NOT")) {
            foiDispatch.selectDoYouWantToDispatch("No");
            clickTheButton("Continue");
        }
    }

    @And("I select {string} as the case type")
    public void iSelectAsCaseType(String foiOutcomeCaseType) {
        foiDispatch.selectASpecificFOIOutcomeCaseType(foiOutcomeCaseType);
    }

    @And("I select {string} as the response channel")
    public void iSelectAsTheResponseChannel(String response) {
        foiDispatch.selectASpecificResponseChannel(response);
    }

    @And("I submit {string} as the outcome of the case")
    public void iSelectAsOutcomeOfTheCase(String outcome) {
        foiDispatch.selectOutcomeOfTheCase(outcome);
        clickTheButton("Continue");
    }

    @And("I select an Exception")
    public void iSelectRandomException() {
        waitForDECSPageWithTitle("Which exceptions were applied?");
        foiDispatch.selectRandomException();
        clickTheButton("Continue");
    }

    @And("I select an Exemption")
    public void iSelectRandomExemption() {
        waitForDECSPageWithTitle("Which exemptions were applied?");
        foiDispatch.selectRandomExemption();
        clickTheButton("Continue");
    }

    @And("I select an Exception and an Exemption")
    public void iSelectRandomExceptionAndExemption() {
        waitForDECSPageWithTitle("Which exemptions and exceptions were applied?");
        foiDispatch.selectRandomException();
        foiDispatch.selectRandomExemption();
        clickTheButton("Continue");
    }

    @And("I submit a non-dispatch option as the outcome of the case")
    public void iSelectNonDispatchOptionAsOutcomeOfTheCase() {
        foiDispatch.selectNonDispatchOutcomeOfTheCase();
        clickTheButton("Continue");
    }

    @And("I confirm my answers for the outcome of the case")
    public void iConfirmMyAnswersForTheOutcomeOfTheCase() {
        clickTheButton("Confirm");
    }

    @And("I submit the date the Final Response was sent")
    public void iSubmitTheDateTheFinalResponseWasSent() {
        foiDispatch.enterFinalResponseDate();
        clickTheButton("Continue");
    }

    @And("I upload a copy of the Final Response")
    public void iUploadACopyOfTheFinalResponse() {
        if (foiCase()) {
            documents.addADocumentOfDocumentType("Final responses");
        } else if (complaintCase()) {
            documents.addADocumentOfDocumentType("Final Response");
        }
    }
}
