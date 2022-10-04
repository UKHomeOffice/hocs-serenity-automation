package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CCH extends BasePage {

    RecordCaseData recordCaseData;

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
        switch (action.toUpperCase()) {
            case "TRANSFER TO CCT":
                recordCaseData.selectSpecificRadioButton("CCT");
                break;
            case "TRANSFER TO EX-GRATIA":
                recordCaseData.selectSpecificRadioButton("Ex-Gratia");
                break;
            case "TRANSFER TO MINOR MISCONDUCT":
                recordCaseData.selectSpecificRadioButton("Minor Misconduct");
                break;
            case "COMPLETE THE CASE":
                recordCaseData.selectSpecificRadioButton("Complete the case");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickContinueButton();
    }

    public void submitReasonForCaseCompletion() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter note for case completion");
        setSessionVariable("closureReason").to(enteredText);
        clickTheButton("Complete case");
    }

    public void selectActionAtCompleteConfirmation(String action) {
        selectSpecificRadioButton(action);
        clickTheButton("Confirm");
    }
}