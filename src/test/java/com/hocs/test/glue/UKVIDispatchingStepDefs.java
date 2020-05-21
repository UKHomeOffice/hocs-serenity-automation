package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.ukvi.CaseDispatching;
import com.hocs.test.pages.ukvi.CasePrivateOffice;
import io.cucumber.java.en.And;

public class UKVIDispatchingStepDefs {

    CaseDispatching dispatching;

    @And("the user triggers the {string} error message at Dispatch by not entering the correct information")
    public void triggerErrorMessage(String errorMessage) {
        dispatching.triggerErrorMessage(errorMessage);
    }

    @And("then the {string} error message should be displayed at Dispatch")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                dispatching.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "OUTBOUND CHANNEL":
                dispatching.assertOutboundChannelErrorMessageDisplayed();
                break;
            case "DISPATCHED DATE":
                dispatching.assertDispatchedDateErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

}
