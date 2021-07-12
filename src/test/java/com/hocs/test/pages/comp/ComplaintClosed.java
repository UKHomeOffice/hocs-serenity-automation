package com.hocs.test.pages.comp;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintClosed extends BasePage {

    @FindBy(xpath = "//label[text()='Re-open the case, send to CCT Stage 2']")
    public WebElementFacade reopenCaseSendToCCTStage2RadioButton;

    @FindBy(xpath = "//label[text()='Complete the case (close permanently)']")
    public WebElementFacade completeTheCaseClosePermanentlyRadioButton;

    @FindBy(id = "CaseNote_CompleteReason")
    public WebElementFacade caseCompletionReasonTextField;

    @FindBy(xpath = "//input[@value='Complete case']")
    public WebElementFacade completeCaseButton;

    @FindBy(xpath = "//label[contains(text(),'Yes')]")
    public WebElementFacade completeCasePermanentlyCloseYesRadioButton;

    @FindBy(xpath = "//label[contains(text(),'No')]")
    public WebElementFacade completeCasePermanentlyCloseNoRadioButton;

    public void selectActionAtComplaintClosed(String action) {
        if (action.equalsIgnoreCase("RE-OPEN THE CASE")) {
            safeClickOn(reopenCaseSendToCCTStage2RadioButton);
        } else if (action.equalsIgnoreCase("COMPLETE THE CASE")) {
            safeClickOn(completeTheCaseClosePermanentlyRadioButton);
        }
        safeClickOn(continueButton);
    }

    public void enterReasonForCaseCompletion() {
        caseCompletionReasonTextField.sendKeys("Test Completion Reason");
        setSessionVariable("closureReason").to("Test Completion Reason");
        safeClickOn(completeCaseButton);
    }

    public void selectActionAtCompleteConfirmation(String action) {
        if (action.equalsIgnoreCase("YES")) {
            safeClickOn(completeCasePermanentlyCloseYesRadioButton);
        } else if (action.equalsIgnoreCase("NO")) {
            safeClickOn(completeCasePermanentlyCloseNoRadioButton);
        }
        safeClickOn(confirmButton);
    }

    public void moveCaseFromComplaintClosedToCaseClosed() {
        selectActionAtComplaintClosed("Complete the Case");
        enterReasonForCaseCompletion();
        selectActionAtCompleteConfirmation("Yes");
    }
}