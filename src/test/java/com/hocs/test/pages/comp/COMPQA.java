package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class COMPQA extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[text()='Accept - send response to complainant']")
    public WebElementFacade sendResponseToComplainantRadioButton;

    @FindBy(xpath = "//label[text()='Reject - return response to draft']")
    public WebElementFacade returnResponseToDraftRadioButton;

    @FindBy(id = "CaseNote_QaReject")
    public WebElementFacade rejectionReasonTextField;

    public void selectActionAtServiceQA(String action) {
        switch (action.toUpperCase()) {
            case "ACCEPT":
                recordCaseData.selectSpecificRadioButton("Accept - send response to complainant");
                break;
            case "REJECT":
                recordCaseData.selectSpecificRadioButton("Reject - return response to draft");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickTheButton("Continue");
    }

    public void submitRejectionReason() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter reason for rejection");
        setSessionVariable("rejectionReason").to(enteredText);
        clickTheButton("Reject");
    }
}