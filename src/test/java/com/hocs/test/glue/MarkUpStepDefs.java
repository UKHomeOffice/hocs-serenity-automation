package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MarkUpStepDefs {

    Homepage homepage;

    Page page;

    Topics topics;

    MarkUpDecision markUpDecision;

    @When("^I complete the markup stage$")
    public void completeTheMarkupStage() {
        homepage.selectTeam1111Markup();
        homepage.clickFirstMarkupAllocate();
        markUpDecision.clickPolicyResponseRadioButton();
        page.clickContinueButton();
        System.out.println("I have found the Add topic button, smashing left click repeatedly....");
        topics.clickAddTopicButton();
        topics.enterRealTopic();
        page.clickAddButton();
        page.clickContinueButton();
        //Answering Stage has no inputs available therefore enter a minister and clickContinueButton again
        markUpDecision.selectSecondSignOffMinisterFromDropdown();
        page.clickContinueButton();
        page.enterAllocationNote();
        page.clickFinishButton();
    }

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
