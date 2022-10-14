package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PaymentApproval extends BasePage {

    public void assertPaymentIsRequiredPaymentApprovalErrorMessage() {
        assertExpectedErrorMessageIsDisplayed("Payment is required");
    }

    public void selectPaymentApprovedReadyToSend() {
        selectSpecificRadioButton("Payment approved and ready to send");
        clickConfirmButton();
    }

    public void returnToCaseworkTeam() {
        selectSpecificRadioButton("PNC failed, send to casework");
        clickConfirmButton();
    }
}
