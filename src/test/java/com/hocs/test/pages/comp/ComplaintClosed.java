package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintClosed extends BasePage {

    RecordCaseData recordCaseData;

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
        recordCaseData.selectSpecificRadioButton(action);
        clickTheButton("Continue");
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