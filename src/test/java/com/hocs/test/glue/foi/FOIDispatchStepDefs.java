package com.hocs.test.glue.foi;

import static net.serenitybdd.core.Serenity.setSessionVariable;

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
}
