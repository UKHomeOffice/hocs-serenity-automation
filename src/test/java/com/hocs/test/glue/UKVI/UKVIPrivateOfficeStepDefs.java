package com.hocs.test.glue.UKVI;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.ukvi.PrivateOffice;
import io.cucumber.java.en.And;

public class UKVIPrivateOfficeStepDefs {

    PrivateOffice privateOffice;

    @And("the user triggers the {string} error message at Private Office by not entering the correct information")
    public void triggerErrorMessage(String errorMessage) {
        privateOffice.triggerErrorMessage(errorMessage);
    }

    @And("then the {string} error message should be displayed at Private Office")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                privateOffice.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "RESPONSE CHANNEL":
                privateOffice.assertResponseChannelErrorMessageDisplayed();
                break;
            case "DISPATCHED DATE":
                privateOffice.assertDispatchedDateErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }
}
