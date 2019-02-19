package com.hocs.test.glue;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInput;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import junit.framework.TestCase;
import net.thucydides.core.annotations.Managed;

import org.jruby.RubyProcess.Sys;
import org.openqa.selenium.WebDriver;



public class PrivateOfficeSignOffStepDefs {

    @Managed
    WebDriver driver;

    Homepage homepage;

    DataInput dataInput;

    PrivateOffice privateOffice;

    SuccessfulCaseCreation successfulCaseCreation;

    Page page;

    @When("^I complete the Private Office stage$")
    public void completePrivateOfficeStage() {
        homepage.selectMinisterForLordsTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        privateOffice.clickPrivateOfficeAcceptRadioButton();
        privateOffice.clickContinueButton();
    }

    @When("^The case is rejected at the Private Office stage$")
    public void rejectAtPrivateOffice() {
        homepage.selectMinisterForLordsTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        privateOffice.clickPrivateOfficeRejectRadioButton();
        privateOffice.clickContinueButton();
        privateOffice.enterPORejectNotes();
        privateOffice.clickFinishButton();
    }

    @When("^I click the continue button on PO approve response screen$")
    public void clickContinueButtonOnPOApproveResponseScreen() {
        privateOffice.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not selected whether I approve the response$")
    public void assertThatApprovedResponseErrorMessageIsShown() {
        privateOffice.assertDoYouApproveTheResponseErrorMessage();
    }

    @When("^I click the finish button on the change minister screen$")
    public void clickFinishButtonOnChangeMinisterScreen() {
        privateOffice.clickPrivateOfficeChangeMinisterRadioButton();
        privateOffice.clickContinueButton();
        privateOffice.clickFinishButton();
    }

    @Then("^error messages should be displayed as I have not selected an override team or entered change reasoning$")
    public void assertThatChangeMinisterErrorMessagesAreShown() {
        privateOffice.assertChangeMinisterErrorMessages();
    }

    @When("^I click the finish button on the what is your feedback response screen$")
    public void clickFinishButtonOnWhatIsFeedbackResponseScreen() {
        privateOffice.clickPrivateOfficeRejectRadioButton();
        privateOffice.clickContinueButton();
        privateOffice.clickFinishButton();
    }

    @Then("^an error message should be displayed as I have not entered feedback into the text box")
    public void assertThatFeedbackResponseErrorMessageIsShown() {
        privateOffice.assertWhatIsYourFeedbackResponse();
    }

}
