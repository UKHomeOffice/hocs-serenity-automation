package com.hocs.test.glue;

import static org.junit.Assert.fail;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MarkUpStepDefs {

    @Then("^the topic should be set as the \"([^\"]*)\" topic$")
    public void theTopicShouldBeSetAsTheOrdinalTopic(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                fail(ordinal
                        + " is not defined in MarkUpStepDefs, theTopicShouldBeSetAsTheOrdinalTopic method");
        }
    }

    @When("^I select a primary topic$")
    public void iSelectAPrimaryTopic() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
