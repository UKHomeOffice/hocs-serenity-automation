package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OfferApproval extends BasePage {

    @FindBy(timeoutInSeconds = "10",  css = "label[for='OfferApprovalStatus-Offer']")
    public WebElementFacade offerApprovedRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='OfferApprovalStatus-QA")
    public WebElementFacade returnToQARadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='OfferApprovalStatus-PNC-Fail")
    public WebElementFacade returnToCaseworkDueToPNCResultRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[@href='#OfferApprovalStatus-error']")
    public WebElementFacade paymentIsRequiredOfferApprovalErrorMessage;

    public void assertPaymentIsRequiredOfferApprovalErrorMessage() {
        paymentIsRequiredOfferApprovalErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentOfferApproved(){
        selectSpecificRadioButton("Offer approved");
        clickConfirmButton();
    }

    public void returnToCaseworkTeam() {
        selectSpecificRadioButton("Return to casework for amount changes due to PNC result");
        clickConfirmButton();
    }

    public void returnToQa() {
        selectSpecificRadioButton("Return to QA for corrections");
        clickConfirmButton();
    }
}
