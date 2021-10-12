package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.FOIDispatch;
import io.cucumber.java.en.And;

public class FOIDispatchStepDefs extends BasePage {

    FOIDispatch foiDispatch;

    @And("I select {string} to do you want to dispatch the case")
    public void iSelectToDoYouWantToDispatchTheCase(String response) {
        foiDispatch.selectDoYouWantToDispatch(response);
    }

    @And("I select {string} as the case type")
    public void iSelectAsCaseType(String caseType) {
        foiDispatch.selectCaseType(caseType);
    }

    @And("I select {string} as the response")
    public void iSelectAsTheResponse(String response) {
        foiDispatch.selectResponse(response);
    }

    @And("I select {string} as outcome of the case")
    public void iSelectAsOutcomeOfTheCase(String outcome) {
        foiDispatch.selectOutcomeOfTheCase(outcome);
    }

    @And("I select an Exception")
    public void iSelectRandomException() {
        foiDispatch.selectRandomException();
    }

    @And("I select an Exemption")
    public void iSelectRandomExemption() {
        foiDispatch.selectRandomExemption();
    }

    @And("I select an Exception and an Exemption")
    public void iSelectRandomExceptionAndExemption() {
        foiDispatch.selectRandomException();
        foiDispatch.selectRandomExemption();
    }

    @And("I select non dispatch option as outcome of the case")
    public void iSelectNonDispatchOptionAsOutcomeOfTheCase() {
        foiDispatch.selectNonDispatchOutcomeOfTheCase();
    }

    @And("I {string} record the case outcome")
    public void iRecordTheCaseOutcome(String buttonLabel) {
        clickTheButton(buttonLabel);
    }

    @And("I {string} Dispatch stage check your answers")
    public void iCheckYourAnswers(String buttonLabel) {
        clickTheButton(buttonLabel);
    }

    @And("I {string} selection of Exemptions")
    public void iSelectionOfExemptions(String buttonLabel) {
        clickTheButton(buttonLabel);
    }

    @And("I {string} selection of Exceptions")
    public void iSelectionOfExceptions(String buttonLabel) {
        clickTheButton(buttonLabel);
    }

    @And("I {string} selection of Exemptions and Exceptions")
    public void iSelectionOfExemptionsAndExceptions(String buttonLabel) {
        clickTheButton(buttonLabel);
    }
}
