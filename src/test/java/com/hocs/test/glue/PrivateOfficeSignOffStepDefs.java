package com.hocs.test.glue;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;


public class PrivateOfficeSignOffStepDefs extends Page {

    @Managed
    WebDriver driver;

    Homepage homepage;

    PrivateOffice privateOffice;

    Workstacks workstacks;

    @When("^I complete the Private Office stage$")
    public void completePrivateOfficeStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(privateOffice.privateOfficeAcceptRadioButton);
        clickOn(privateOffice.continueButton);
    }

    @When("^the case is rejected at the Private Office stage$")
    public void rejectAtPrivateOffice() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(privateOffice.privateOfficeRejectRadioButton);
        clickOn(privateOffice.continueButton);
        privateOffice.enterPORejectNotes();
        clickOn(privateOffice.finishButton);
    }

    @When("^I click the continue button on PO approve response screen$")
    public void clickContinueButtonOnPOApproveResponseScreen() {
        clickOn(privateOffice.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected whether I approve the response$")
    public void assertThatApprovedResponseErrorMessageIsShown() {
        privateOffice.assertDoYouApproveTheResponseErrorMessage();
    }

    @When("^I click the finish button on the change minister screen$")
    public void clickFinishButtonOnChangeMinisterScreen() {
        clickOn(privateOffice.privateOfficeChangeMinisterRadioButton);
        clickOn(privateOffice.continueButton);
        clickOn(privateOffice.finishButton);
    }

    @Then("^error messages should be displayed as I have not selected an override team or entered change reasoning$")
    public void assertThatChangeMinisterErrorMessagesAreShown() {
        privateOffice.assertChangeMinisterErrorMessages();
    }

    @When("^I click the finish button on the what is your feedback response screen$")
    public void clickFinishButtonOnWhatIsFeedbackResponseScreen() {
        clickOn(privateOffice.privateOfficeRejectRadioButton);
        clickOn(privateOffice.continueButton);
        clickOn(privateOffice.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered feedback into the text box")
    public void assertThatFeedbackResponseErrorMessageIsShown() {
        privateOffice.assertWhatIsYourFeedbackResponse();
    }

}
