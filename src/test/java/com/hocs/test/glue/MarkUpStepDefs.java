package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MarkUpStepDefs {

    Homepage homepage;

    Page page;

    Topics topics;

    MarkUpDecision markUpDecision;

    DataInput dataInput;

    SuccessfulCaseCreation successfulCaseCreation;

    @When("^I complete the markup stage$")
    public void completeTheMarkupStage() {
        homepage.findMyMarkupCase();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
        markUpDecision.clickPolicyResponseRadioButton();
        page.clickContinueButton();
        topics.clickAddTopicButton();
        topics.enterRealTopic();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.clickAddButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.clickContinueButton();
        page.clickContinueButton();
       // markUpDecision.selectFirstSignOffMinisterFromDropdown();
       // page.clickContinueButton();
       // page.enterAllocationNote();
       // page.clickFinishButton();
    }

    @Then("^the topic should be set as the \"([^\"]*)\" topic$")
    public void theTopicShouldBeSetAsTheOrdinalTopic(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                System.out.println(ordinal
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                ordinal = null;
                assumeNotNull(ordinal);
        }
    }

    @When("^I select a primary topic$")
    public void iSelectAPrimaryTopic() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
