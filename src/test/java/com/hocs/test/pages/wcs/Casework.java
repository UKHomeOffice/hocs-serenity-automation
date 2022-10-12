package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
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
        selectSpecificRadioButton("Send offer to QA");
        clickConfirmButton();
    }

    public void putTheClaimOnHold() {
        selectSpecificRadioButton("On hold");
        clickConfirmButton();
        System.out.println("Case moved from Casework to Casework On Hold");
    }

    public void takeTheClaimOffHold() {
        selectSpecificRadioButton("Off hold");
        clickConfirmButton();
        System.out.println("Case moved from Casework On Hold to Casework");
    }

    public void selectToSendClaimToEligibility() {
        selectSpecificRadioButton("Return to Eligibility team");
        clickConfirmButton();
    }

    public void selectToSendClaimToRegistration() {
        selectSpecificRadioButton("Return to Registration team");
        clickConfirmButton();
    }

    public void selectToSendClaimToTriage() {
        selectSpecificRadioButton("Return to Triage team");
        clickConfirmButton();
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
