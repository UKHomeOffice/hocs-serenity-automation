package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
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
        clickOn(paymentConfirmedRadioButton);
        clickOn(confirmButton);
    }

    public void yesCloseClaim() {
        clickOn(closeClaimYesRadioButton);
        clickOn(confirmButton);
    }

}
