package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Documents;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ServiceDraft extends BasePage {

    Documents documents;

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
                safeClickOn(responseIsReadyToSendRadioButton);
                break;
            case "SEND CASE TO QA":
                safeClickOn(sendCaseToQARadioButton);
                break;
            case "ESCALATE CASE TO WFM":
                safeClickOn(escalateCaseToWFMRadioButton);
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(continueButton);
    }

    public void moveCaseFromServiceDraftToServiceQA() {
        documents.addADraftDocumentAtDraftStage();
        selectActionAtServiceDraft("Send Case to QA");
    }

    public void moveCaseFromServiceDraftToServiceEscalated() {
        selectActionAtServiceDraft("Escalate case to WFM");
        reasonForEscalationTextField.sendKeys("Test Escalation Reason");
        setSessionVariable("escalationReason").to("Test Escalation Reason");
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
