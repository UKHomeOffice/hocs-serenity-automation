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


public class PrivateOfficeSignOffStepDefs {

    @Managed
    WebDriver driver;

    Homepage homepage;

    PrivateOffice privateOffice;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    Page page;

    @When("^I complete the Private Office stage$")
    public void completePrivateOfficeStage() {
        page.clickOn(homepage.ministerForLordsTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(privateOffice.privateOfficeAcceptRadioButton);
        page.clickOn(privateOffice.continueButton);
    }

    @When("^the case is rejected at the Private Office stage$")
    public void rejectAtPrivateOffice() {
        page.clickOn(homepage.ministerForLordsTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(privateOffice.privateOfficeRejectRadioButton);
        page.clickOn(privateOffice.continueButton);
        privateOffice.enterPORejectNotes();
        page.clickOn(privateOffice.finishButton);
    }

    @When("^I click the continue button on PO approve response screen$")
    public void clickContinueButtonOnPOApproveResponseScreen() {
        page.clickOn(privateOffice.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected whether I approve the response$")
    public void assertThatApprovedResponseErrorMessageIsShown() {
        privateOffice.assertDoYouApproveTheResponseErrorMessage();
    }

    @When("^I click the finish button on the change minister screen$")
    public void clickFinishButtonOnChangeMinisterScreen() {
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(privateOffice.privateOfficeChangeMinisterRadioButton);
        page.clickOn(privateOffice.continueButton);
        page.clickOn(privateOffice.finishButton);
    }

    @Then("^error messages should be displayed as I have not selected an override team or entered change reasoning$")
    public void assertThatChangeMinisterErrorMessagesAreShown() {
        privateOffice.assertChangeMinisterErrorMessages();
    }

    @When("^I click the finish button on the what is your feedback response screen$")
    public void clickFinishButtonOnWhatIsFeedbackResponseScreen() {
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(privateOffice.privateOfficeRejectRadioButton);
        page.clickOn(privateOffice.continueButton);
        page.clickOn(privateOffice.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered feedback into the text box")
    public void assertThatFeedbackResponseErrorMessageIsShown() {
        privateOffice.assertWhatIsYourFeedbackResponse();
    }

}
