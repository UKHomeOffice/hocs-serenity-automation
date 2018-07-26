package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.create_case.SingleCase;
import cucumber.api.java.en.Given;

public class CreateCaseStepDefs {

    SingleCase singleCase;

    @Given("^I am presented with \"([^\"]*)\"")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                singleCase.radioButtonsNotDisplayed();
                break;
            default:
                fail(userView + " is not defined with CreateCaseStepDefs.iAmPresentedWith");
        }
    }

}
