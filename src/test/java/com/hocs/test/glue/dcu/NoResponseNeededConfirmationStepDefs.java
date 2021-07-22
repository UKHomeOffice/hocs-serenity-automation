package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.dcu.NoResponseNeededConfirmation;
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
}
