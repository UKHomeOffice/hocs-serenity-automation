package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;

public class SearchStepDefs {

    @When("^I perform a valid search on the \"([^\"]*)\" page")
    public void validSearchOnPage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "":
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());

        }
    }

}
