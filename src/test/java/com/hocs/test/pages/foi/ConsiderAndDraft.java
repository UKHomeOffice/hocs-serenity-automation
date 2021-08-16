package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ConsiderAndDraft extends BasePage {

    public void selectDraftAcceptance(String input) {
        selectSpecificRadioButtonFromGroupWithHeading(input, "Is this case for your drafting team?");
    }

    public void enterRejectionReason() {
        enterSpecificTextIntoTextAreaWithHeading("Test Rejection Reason", "Reason");
        setSessionVariable("rejectionReason").to("Test Rejection Reason");
    }

    public void isContributionRequestNeeded(String input) {
        selectSpecificRadioButtonFromGroupWithHeading(input, "Do you need to request contributions?");
    }

    public void selectResponseType(String responseType) {
        selectSpecificRadioButtonFromGroupWithHeading(responseType, "What is the response type?");
    }

    public void selectExemptionReason() {
        selectRandomRadioButtonFromGroupWithHeading("What is the reason for the exemption?");
    }
}