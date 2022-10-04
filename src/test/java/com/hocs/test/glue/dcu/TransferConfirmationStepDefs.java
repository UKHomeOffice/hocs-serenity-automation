package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.dcu.TransferConfirmation;
import io.cucumber.java.en.When;

public class TransferConfirmationStepDefs extends BasePage {

    TransferConfirmation transferConfirmation;

    @When("I confirm the case should be transferred")
    public void iConfirmTheCaseShouldBeTransferred() {
        transferConfirmation.selectCaseShouldBeTransferred();
        clickFinishButton();
    }

    @When("I confirm the case should not be transferred")
    public void iConfirmTheCaseShouldNotBeTransferred() {
        transferConfirmation.selectCaseShouldNotBeTransferred();
        clickFinishButton();
    }
}
