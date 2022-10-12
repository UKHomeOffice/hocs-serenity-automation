package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AwaitingPaymentConfirmation extends BasePage {

    @FindBy(css = "label[for='ClaimComplete-Yes']")
    public WebElementFacade paymentConfirmedRadioButton;

    @FindBy(xpath = "//a[@href='#ClaimComplete-error']")
    public WebElementFacade claimCompleteIsRequiredErrorMessage;

    @FindBy(xpath = "//button[text()='Awaiting payment confirmation']")
    public WebElementFacade awaitingPaymentConfirmationAccordion;

    @FindBy(xpath = "//label[text()='Yes - close the claim']")
    public WebElementFacade closeClaimYesRadioButton;

    public void assertClaimCompleteIsRequiredErrorMessage() {
        claimCompleteIsRequiredErrorMessage.shouldContainText("Claim complete is required");
    }

    public void selectClaimCompletePaymentConfirmed() {
        selectSpecificRadioButton("Payment confirmed - close claim");
        clickConfirmButton();
    }

    public void yesCloseClaim() {
        selectSpecificRadioButton("Yes - close the claim");
        clickConfirmButton();
    }

}
