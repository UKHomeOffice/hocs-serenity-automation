package com.hocs.test.glue.dcu;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.dcu.TransferConfirmation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TransferConfirmationStepDefs extends BasePage {

    TransferConfirmation transferConfirmation;

    @When("I click the confirm transfer yes radio button")
    public void iClickTheConfirmTransferYesRadioButton() {
        safeClickOn(transferConfirmation.transferCaseYesRadioButton);
    }

    @When("I click the confirm transfer no radio button")
    public void iClickTheConfirmTransferNoRadioButton() {
        safeClickOn(transferConfirmation.transferCaseNoRadioButton);
    }

    @Then("an error message should be displayed as I have not selected a response on the Transfer Confirmation screen")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedAResponseOnTheTransferConfirmationScreen() {
    }
}
