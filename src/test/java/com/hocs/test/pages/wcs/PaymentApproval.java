package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PaymentApproval extends BasePage {

    @FindBy(css = "label[for='PaymentApprovalStatus-PNC-Fail']")
    public WebElementFacade pncFailedSendToCaseworkRadioButton;

    @FindBy(css = "label[for='PaymentApprovalStatus-Offer']")
    public WebElementFacade paymentApprovedAndReadyToSendRadioButton;

    @FindBy(xpath = "//a[@href='#PaymentApprovalStatus-error']")
    public WebElementFacade paymentIsRequiredPaymentApprovalErrorMessage;

    public void assertPaymentIsRequiredPaymentApprovalErrorMessage() {
        paymentIsRequiredPaymentApprovalErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentApprovedReadyToSend() {
        selectSpecificRadioButton("Payment approved");
        clickTheButton("Confirm");
    }

    public void returnToCaseworkTeam() {
        selectSpecificRadioButton("PNC failed");
        clickTheButton("Confirm");
    }
}
