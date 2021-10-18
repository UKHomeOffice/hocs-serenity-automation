package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintsDraft extends BasePage {

    Documents documents;

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[contains(text(),'Add a')]")
    public WebElementFacade addADocumentHypertext;

    @FindBy(xpath = "//label[text()='Response is ready to send']")
    public WebElementFacade responseIsReadyToSendRadioButton;

    @FindBy(xpath = "//label[text()='Send case to QA']")
    public WebElementFacade sendCaseToQARadioButton;

    @FindBy(xpath = "//label[text()='Escalate case to WFM']")
    public WebElementFacade escalateCaseToWFMRadioButton;

    @FindBy(id = "CaseNote_DraftEscalate")
    public WebElementFacade reasonForEscalationTextField;

    @FindBy(xpath = "//a[text()='Primary draft document is required']")
    public WebElementFacade primaryDraftDocumentIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Action is required']")
    public WebElementFacade actionIsRequired;

    public void selectActionAtServiceDraft(String action) {
        switch (action.toUpperCase()) {
            case "RESPONSE IS READY TO SEND":
                recordCaseData.selectSpecificRadioButton("Response is ready to send");
                break;
            case "SEND CASE TO QA":
                recordCaseData.selectSpecificRadioButton("Send case to QA");
                break;
            case "ESCALATE CASE TO WFM":
                recordCaseData.selectSpecificRadioButton("Escalate case to WFM");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(continueButton);
    }

    public void submitEscalationReason() {
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter reason for escalation");
        setSessionVariable("escalationReason").to(enteredText);
        clickTheButton("Escalate case");
    }

    public void assertErrorMessageIsDisplayed(String errorMessage) {
        if (errorMessage.equalsIgnoreCase("ACTION")) {
            actionIsRequired.shouldBeVisible();
        } else if (errorMessage.equalsIgnoreCase("PRIMARY DRAFT DOCUMENT")) {
            primaryDraftDocumentIsRequiredErrorMessage.shouldBeVisible();
        }
    }
}
