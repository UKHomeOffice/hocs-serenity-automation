package com.hocs.test.pages.comp;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CCH extends BasePage {

    @FindBy(xpath = "//label[text()='CCT']")
    public WebElementFacade transferToCCTRadioButton;

    @FindBy(xpath = "//label[text()='Complete the case (close permanently)']")
    public WebElementFacade completeTheCaseClosePermanentlyRadioButton;

    @FindBy(id = "CaseNote_CompleteReason")
    public WebElementFacade completionReasonTextField;

    @FindBy(xpath = "//label[text()='Yes']")
    public WebElementFacade completeCasePermanentlyCloseCaseYesRadioButton;

    @FindBy(xpath = "//label[text()='No']")
    public WebElementFacade completeCasePermanentlyCloseCaseNoRadioButton;

    public void selectActionAtCCH(String action) {
        if (action.equalsIgnoreCase("TRANSFER TO CCT")) {
            safeClickOn(transferToCCTRadioButton);
        } else if (action.equalsIgnoreCase("COMPLETE THE CASE")) {
            safeClickOn(completeTheCaseClosePermanentlyRadioButton);
        }
        safeClickOn(continueButton);
    }

    public void enterReasonForCaseCompletion() {
        completionReasonTextField.sendKeys("Test Completion Reason");
        setSessionVariable("closureReason").to("Test Completion Reason");
        clickTheButton("Complete case");
    }

    public void selectActionAtCompleteConfirmation(String action) {
        if (action.equalsIgnoreCase("YES")) {
            safeClickOn(completeCasePermanentlyCloseCaseYesRadioButton);
        } else if (action.equalsIgnoreCase("NO")) {
            safeClickOn(completeCasePermanentlyCloseCaseNoRadioButton);
        }
        safeClickOn(confirmButton);
    }
}