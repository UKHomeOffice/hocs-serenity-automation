package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CaseQA extends BasePage {

    //QA Elements
    @FindBy(xpath = "//label[text()='Approve']")
    public WebElementFacade approvedAtQARadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to draft']")
    public WebElementFacade rejectQAAtDraftRadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to triage']")
    public WebElementFacade rejectQAAtTriageRadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to a workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(id = "CaseNote_RejectDraft")
    public WebElementFacade draftRejectionTextField;

    @FindBy(id = "CaseNote_RejectTriage")
    public WebElementFacade triageRejectionTextField;

    //QA (Escalated) Elements
    @FindBy(xpath = "//label[text()='Keep escalated']")
    public WebElementFacade keepEscalatedRadioButton;

    @FindBy(xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    //QA (On Hold) Elements
    @FindBy(xpath = "//label[text()='Keep on hold']")
    public WebElementFacade keepOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    //QA Actions
    public void moveCaseFromCaseQAToNextStage() {
        safeClickOn(approvedAtQARadioButton);
        clickTheButton("Confirm");
    }

    public void putQACaseOnHold() {
        safeClickOn(onHoldRadioButton);
        clickTheButton("Confirm");
    }

    public void escalateQACaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        clickTheButton("Confirm");
    }

    public void rejectQACaseAtDraft(String rejectionReason) {
        safeClickOn(rejectQAAtDraftRadioButton);
        clickTheButton("Confirm");
        typeInto(draftRejectionTextField, rejectionReason);
        clickTheButton("Confirm");
    }

    public void rejectQACaseAtTriage(String rejectionReason) {
        safeClickOn(rejectQAAtTriageRadioButton);
        clickTheButton("Confirm");
        typeInto(triageRejectionTextField, rejectionReason);
        clickTheButton("Confirm");
    }

    //QA (Escalated) Actions
    public void keepCaseEscalated() {
        safeClickOn(keepEscalatedRadioButton);
        clickTheButton("Confirm");
    }

    public void takeCaseOffEscalation() {
        safeClickOn(escalationCompleteRadioButton);
        clickTheButton("Confirm");
    }

    //QA (On Hold) Actions
    public void keepCaseOnHold() {
        safeClickOn(keepOnHoldRadioButton);
        clickTheButton("Confirm");
    }

    public void takeCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        clickTheButton("Confirm");
    }

    //Assertions
    public void assertActionsRequiredErrorMessageDisplayed() {
        clickTheButton("Confirm");
        actionsRequiredErrorMessage.shouldContainText("Actions is required");
    }
}