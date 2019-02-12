package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MarkUpStepDefs {

    Homepage homepage;

    Page page;

    Topics topics;

    Workstacks workstacks;

    MarkUpDecision markUpDecision;

    SuccessfulCaseCreation successfulCaseCreation;


    @When("^I complete the markup stage$")
    public void completeTheMarkupStage() {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        topics.clickAddTopicButton();
        topics.enterRealTopic();
        page.sleep(1000);
        markUpDecision.clickAddButton();
        page.sleep(1000);
        markUpDecision.clickContinueButton();
        page.sleep(1000);
        markUpDecision.clickFinishButton();
    }

    @When("^I assign the Topic \"([^\"]*)\"$")
    public void enterSpecificMarkupTopic(String topic) {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();

    }

    @When("^I click the continue button without adding a topic at the markup stage$")
    public void clickAddButtonAtMarkupStage() {
        markUpDecision.clickAddButton();
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
