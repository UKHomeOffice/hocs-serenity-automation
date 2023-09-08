package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OfferAcceptance extends BasePage {

    @FindBy(css = "label[for='OfferReplyStatus-Offer']")
    public WebElementFacade offerAcceptedReadyForProcessingRadioButton;

    @FindBy(css = "label[for='OfferReplyStatus-Reject']")
    public WebElementFacade offerRejectRadioButton;

    @FindBy(xpath = "//a[@href='#OfferReplyStatus-error']")
    public WebElementFacade paymentIsRequiredOfferAcceptanceErrorMessage;

    public void assertPaymentIsRequiredOfferAcceptanceErrorMessage() {
        paymentIsRequiredOfferAcceptanceErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentOfferAccepted() {
        selectSpecificRadioButton("Offer accepted, or preliminary payment");
        clickConfirmButton();
    }

    public void selectMoveToEligibility() {
        selectSpecificRadioButton("Move to Eligibility");
        clickConfirmButton();
    }

    public void selectMoveToTriage() {
        selectSpecificRadioButton("Move to Triage");
        clickConfirmButton();
    }

    public void selectMoveToCasework() {
        selectSpecificRadioButton("Move to Casework");
        clickConfirmButton();
    }

    public void selectPaymentOfferRejected() {
        selectSpecificRadioButton("Offer rejected");
        clickConfirmButton();
    }
}
