package com.hocs.test.pages.dcu;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class MinisterialSignOff extends BasePage {

    @FindBy(xpath = "//div[@id='MinisterSignOffDecision-radios']//label[text()='Yes']")
    public WebElementFacade ministerSignOffAcceptRadioButton;

    @FindBy(xpath = "//div[@id='MinisterSignOffDecision-radios']//label[text()='No']")
    public WebElementFacade ministerSignOffRejectRadioButton;

    @FindBy(id = "CaseNote_MinisterReject")
    public WebElementFacade ministerRejectionNote;

    @FindBy(xpath = "//label[text()='Not applicable']")
    public WebElementFacade notApplicableRadioButton;

    @FindBy(id = "CaseNote_MinisterNotApplicable")
    public WebElementFacade whyIsCaseNotApplicableFreeTextField;

    // Basic Methods

    public void enterMinisterRejectionNote() {
        ministerRejectionNote.sendKeys("Test - rejection at Minister Sign-off reason");
    }

    public void getToMinisterFeedbackResponseScreenPrerequisites() {
        safeClickOn(ministerSignOffRejectRadioButton);
        safeClickOn(continueButton);
        waitABit(1000);
    }

    public void moveCaseFromMinisterToDispatch() {
        safeClickOn(ministerSignOffAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void completeMinisterialSignOffStageAndStoreEnteredInformation() {
        safeClickOn(ministerSignOffAcceptRadioButton);
        setSessionVariable("ministerialSignOffDecision").to(ministerSignOffAcceptRadioButton.getTextContent());
        safeClickOn(continueButton);
    }

    public void moveCaseFromMinisterSignOffToPrivateOfficeApproval() {
        safeClickOn(notApplicableRadioButton);
        safeClickOn(continueButton);
        whyIsCaseNotApplicableFreeTextField.sendKeys("Test");
        setSessionVariable("rejectionReason").to("Test");
        safeClickOn(continueButton);
    }
}
