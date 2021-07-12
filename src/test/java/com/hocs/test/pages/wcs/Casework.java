package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Casework extends BasePage {

    @FindBy(css = "label[for='CaseworkStatus-OnHold']")
    public WebElementFacade caseworkingOnHoldRadioButton;

    @FindBy(css = "label[for='CaseworkStatus-QA']")
    public WebElementFacade offerReadyToQARadioButton;

    @FindBy(css = "label[for='CaseworkStatus-Pending']")
    public WebElementFacade caseworkingOffHoldRadioButton;

    @FindBy(css = "label[for='CaseworkStatus-ReturnToEligibility']")
    public WebElementFacade sendToEligibilityRadioButton;

    @FindBy(css = "label[for='CaseworkStatus-ReturnToRegistration']")
    public WebElementFacade sendToRegistrationRadioButton;

    @FindBy(css = "label[for='CaseworkStatus-ReturnToTriage']")
    public WebElementFacade sendToTriageRadioButton;

    @FindBy(xpath = "//a[@href='#CaseworkStatus-error']")
    public WebElementFacade caseworkClaimStatusIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#CaseNote_CaseworkRTN_Eligibility-error']")
    public WebElementFacade eligibilityReturnReasonRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#CaseNote_CaseworkRTN_Registration-error']")
    public WebElementFacade registrationReturnReasonRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#CaseNote_CaseworkRTN_Triage-error']")
    public WebElementFacade triageReturnReasonRequiredErrorMessage;

    @FindBy(xpath = "//textarea")
    private WebElementFacade reasonTextBox;

    public void enterReasonInTextBox() {
        waitFor(reasonTextBox);
        clickOn(reasonTextBox);
        reasonTextBox.sendKeys("Test Reason");
    }

    public void selectClaimStatusReadyToQA() {
        clickOn(offerReadyToQARadioButton);
        clickOn(confirmButton);
    }

    public void putTheClaimOnHold() {
        clickOn(caseworkingOnHoldRadioButton);
        clickOn(confirmButton);
        System.out.println("Case moved from Casework to Casework On Hold");
    }

    public void takeTheClaimOffHold() {
        clickOn(caseworkingOffHoldRadioButton);
        clickOn(confirmButton);
        System.out.println("Case moved from Casework On Hold to Casework");
    }

    public void selectToSendClaimToEligibility() {
        clickOn(sendToEligibilityRadioButton);
        clickOn(confirmButton);
    }

    public void selectToSendClaimToRegistration() {
        clickOn(sendToRegistrationRadioButton);
        clickOn(confirmButton);
    }

    public void selectToSendClaimToTriage() {
        clickOn(sendToTriageRadioButton);
        clickOn(confirmButton);
    }

    public void assertCaseworkClaimStatusErrorMessage() {
        waitFor(caseworkClaimStatusIsRequiredErrorMessage);
        caseworkClaimStatusIsRequiredErrorMessage.shouldContainText("Claim status is required");
    }

    public void assertEligibilityReturnReasonErrorMessage() {
        eligibilityReturnReasonRequiredErrorMessage.shouldContainText("What is the reason for returning the case? is required");
    }

    public void assertRegistrationReturnReasonErrorMessage() {
        registrationReturnReasonRequiredErrorMessage.shouldContainText("What is the reason for returning the case? is required");
    }

    public void assertTriageReturnReasonErrorMessage() {
        triageReturnReasonRequiredErrorMessage.shouldContainText("What is the reason for returning the case? is required");
    }
}
