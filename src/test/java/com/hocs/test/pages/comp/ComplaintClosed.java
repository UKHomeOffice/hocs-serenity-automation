package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ComplaintClosed extends BasePage {

    @FindBy(xpath = "//label[text()='Re-open the case, send to CCT Stage 2']")
    public WebElementFacade reopenCaseSendToCCTStage2RadioButton;

    @FindBy(xpath = "//label[text()='Complete the case (close permanently)']")
    public WebElementFacade completeTheCaseClosePermanentlyRadioButton;

    @FindBy(id = "CaseNote_CompleteReason")
    public WebElementFacade caseCompletionReasonTextField;

    @FindBy(xpath = "//input[@value='Complete case']")
    public WebElementFacade completeCaseButton;

    @FindBy(xpath = "//label[@for='CompleteResult-Yes']")
    public WebElementFacade completeCasePermanentlyCloseYesRadioButton;

    @FindBy(xpath = "//label[@for='CompleteResult-No']")
    public WebElementFacade completeCasePermanentlyCloseNoRadioButton;

    public void moveCaseFromComplaintClosedToCaseClosed() {
        safeClickOn(completeTheCaseClosePermanentlyRadioButton);
        safeClickOn(continueButton);
        caseCompletionReasonTextField.sendKeys("Test Completion Reason");
        safeClickOn(completeCaseButton);
        safeClickOn(completeCasePermanentlyCloseYesRadioButton);
        safeClickOn(confirmButton);
    }
}
