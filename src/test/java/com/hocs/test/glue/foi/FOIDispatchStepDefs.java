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
}
