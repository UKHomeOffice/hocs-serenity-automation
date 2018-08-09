package com.hocs.test.glue;

import static org.junit.Assert.fail;

import cucumber.api.java.en.When;

public class SearchStepDefs {

    @When("^I perform a valid search on the \"([^\"]*)\" page")
    public void validSearchOnPage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "":
                break;
            default:
                fail(pageName + " is not defined in SearchStepDefs.validSearchOnPage");
        }
    }

}
