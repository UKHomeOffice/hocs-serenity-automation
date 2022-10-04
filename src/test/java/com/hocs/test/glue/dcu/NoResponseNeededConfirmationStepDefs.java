package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.dcu.NoResponseNeededConfirmation;
import io.cucumber.java.en.When;

public class NoResponseNeededConfirmationStepDefs extends BasePage {

    NoResponseNeededConfirmation noResponseNeededConfirmation;

    @When("I agree that there is no need to respond to this correspondence")
    public void iAgreeThatThereIsNoNeedToRespondToThisCorrespondence() {
        noResponseNeededConfirmation.selectAgreeNoResponseNeeded();
        clickFinishButton();
    }

    @When("I disagree that there is no need to respond to this correspondence")
    public void iDisagreeThatThereIsNoNeedToRespondToThisCorrespondence() {
        noResponseNeededConfirmation.selectDisagreeNoResponseNeeded();
        clickFinishButton();
    }
}
