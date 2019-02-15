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
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class MarkUpStepDefs {

    Homepage homepage;

    Page page;

    Topics topics;

    MarkUpDecision markUpDecision;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;


    @When("^I complete the markup stage$")
    public void completeTheMarkupStage() {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
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
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();

    }

    @When("^I click the continue button on the markup response screen$")
    public void clickContinueButtonOnMarkupResponseScreen() {
        workstacks.clickAllocateToMeButton();
        markUpDecision.clickContinueButton();
    }

    @When("^I click the continue button on the add a topic screen$")
    public void clickContinueButtonOnAddATopicScreen() {
        workstacks.clickAllocateToMeButton();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        markUpDecision.sleep(500);
        markUpDecision.clickContinueButton();
    }

    @When("^I click the add button on the add topic screen")
    public void clickAddButtonOnAddTopicScreen() {
        workstacks.clickAllocateToMeButton();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        markUpDecision.sleep(500);
        markUpDecision.clickAddTopic();
        markUpDecision.clickAddButton();
    }

    @Then("^an error message should be displayed as I have not selected a topic$")
    public void assertThatTopicIsRequiredErrorMessageIsShown() {
        markUpDecision.assertTopicIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected a response$")
    public void assertThatMarkupResponseErrorMessageIsShown() {
        markUpDecision.assertSortOfResponseErrorMessage();
    }

    @Then("^an error message should be displayed as I have not added a topic$")
    public void assertThatAddATopicErrorMessageIsShown() {
        markUpDecision.assertAddATopicErrorMessage();
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
