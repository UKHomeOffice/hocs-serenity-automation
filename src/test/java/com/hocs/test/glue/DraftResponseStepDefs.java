package com.hocs.test.glue;

import static org.junit.Assert.fail;

import cucumber.api.java.en.When;

public class DraftResponseStepDefs {

    @When("^I click to answer by \"([^\"]*)\"$")
    public void iClickToAnswerBy(String method) {
        switch (method.toUpperCase()) {
            case "EMAIL":
                break;
            case "PHONE":
                break;
            case "POST":
                break;
                default: fail("Please enter EMAIL, PHONE or POST");
        }
    }

}
