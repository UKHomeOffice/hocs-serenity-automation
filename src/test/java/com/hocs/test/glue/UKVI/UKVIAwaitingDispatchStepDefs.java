package com.hocs.test.glue.UKVI;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.ukvi.AwaitingDispatch;
import com.hocs.test.pages.ukvi.PrivateOffice;
import io.cucumber.java.en.And;

public class UKVIAwaitingDispatchStepDefs {

    AwaitingDispatch awaitingDispatch;

    @And("the user triggers the {string} error message at Dispatch by not entering the correct information")
    public void triggerErrorMessage(String errorMessage) {
        awaitingDispatch.triggerErrorMessage(errorMessage);
    }

    @And("then the {string} error message should be displayed at Dispatch")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                awaitingDispatch.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "RESPONSE CHANNEL":
                awaitingDispatch.assertResponseChannelErrorMessageDisplayed();
                break;
            case "DISPATCHED DATE":
                awaitingDispatch.assertDispatchedDateErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

}
