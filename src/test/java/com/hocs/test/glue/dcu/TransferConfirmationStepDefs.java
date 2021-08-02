package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.dcu.TransferConfirmation;
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
}
