package com.hocs.test.glue.DCU;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.dcu.NoResponseNeededConfirmation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NoResponseNeededConfirmationStepDefs extends BasePage {

    NoResponseNeededConfirmation noResponseNeededConfirmation;

    @When("I click the NRN yes radio button")
    public void iClickTheNRNYesRadioButton() {
        safeClickOn(noResponseNeededConfirmation.noResponseNeededYesRadioButton);
    }

    @When("I click the NRN no radio button")
    public void iClickTheNRNNoRadioButton() {
        safeClickOn(noResponseNeededConfirmation.noResponseNeededNoRadioButton);
    }

    @Then("an error message should be displayed as I have not selected a response on the NRN Confirmation screen")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedAResponseOnTheNRNConfirmationScreen() {
        noResponseNeededConfirmation.assertDoYouAgreeNRNErrorMessage();
    }
}
