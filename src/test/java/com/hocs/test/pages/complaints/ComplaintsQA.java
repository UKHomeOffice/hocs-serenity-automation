package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintsQA extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Accept - send response to complainant']")
    public WebElementFacade sendResponseToComplainantRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Reject - return response to draft']")
    public WebElementFacade returnResponseToDraftRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_QaReject")
    public WebElementFacade rejectionReasonTextField;

    public void selectActionAtServiceQA(String action) {
        switch (action.toUpperCase()) {
            case "ACCEPT":
                if (pogrCase() || pogr2Case()) {
                    recordCaseData.selectSpecificRadioButton("Accept");
                }else {
                    recordCaseData.selectSpecificRadioButton("Accept - send response to complainant");
                }
                break;
            case "REJECT":
                if (pogrCase() || pogr2Case()) {
                    recordCaseData.selectSpecificRadioButton("Reject");
                }else {
                    recordCaseData.selectSpecificRadioButton("Reject - return response to draft");
                }
                break;
            case "REJECT RETURN TO DRAFT":
                recordCaseData.selectSpecificRadioButton("Reject - return to draft");
                break;
            case "REJECT RETURN TO TRIAGE":
                recordCaseData.selectSpecificRadioButton("Reject - return to triage");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickContinueButton();
    }

    public void submitRejectionReason() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter reason for rejection");
        setSessionVariable("rejectionReason").to(enteredText);
        clickTheButton("Reject");
    }
}
