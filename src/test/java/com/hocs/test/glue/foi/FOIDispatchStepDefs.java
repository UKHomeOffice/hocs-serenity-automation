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
            clickContinueButton();
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
        clickContinueButton();
    }

    @And("I select an Exception")
    public void iSelectRandomException() {
        waitForDECSPageWithTitle("Which exceptions were applied?");
        foiDispatch.selectRandomException();
        clickContinueButton();
    }

    @And("I select an Exemption")
    public void iSelectRandomExemption() {
        waitForDECSPageWithTitle("Which exemptions were applied?");
        foiDispatch.selectRandomExemption();
        clickContinueButton();
    }

    @And("I select an Exception and an Exemption")
    public void iSelectRandomExceptionAndExemption() {
        waitForDECSPageWithTitle("Which exemptions and exceptions were applied?");
        foiDispatch.selectRandomException();
        foiDispatch.selectRandomExemption();
        clickContinueButton();
    }

    @And("I submit a non-dispatch option as the outcome of the case")
    public void iSelectNonDispatchOptionAsOutcomeOfTheCase() {
        foiDispatch.selectNonDispatchOutcomeOfTheCase();
        clickContinueButton();
    }

    @And("I confirm my answers for the outcome of the case")
    public void iConfirmMyAnswersForTheOutcomeOfTheCase() {
        clickConfirmButton();
    }

    @And("I submit the date the Final Response was sent")
    public void iSubmitTheDateTheFinalResponseWasSent() {
        foiDispatch.enterFinalResponseDate();
        clickContinueButton();
    }

    @And("I upload a copy of the Final Response")
    public void iUploadACopyOfTheFinalResponse() {
        if (foiCase()) {
            documents.addADocumentOfDocumentType("Final responses");
        } else if (iedetCase()) {
            documents.addADocumentOfDocumentType("Final response");
        } else {
            documents.addADocumentOfDocumentType("Final Response");
        }
    }
}
