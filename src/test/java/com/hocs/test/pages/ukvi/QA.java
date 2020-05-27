package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QA extends BasePage {

    //QA Elements
    @FindBy(xpath = "//label[text()='Approve']")
    public WebElementFacade approvedAtQARadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to drafting']")
    public WebElementFacade rejectQAAtDraftRadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to triage']")
    public WebElementFacade rejectQAAtTriageRadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//a[text()='Action menu - select one option is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(id = "CaseNote_RejectDraft")
    public WebElementFacade draftRejectionTextField;

    @FindBy(id = "CaseNote_RejectTriage")
    public WebElementFacade triageRejectionTextField;

    @FindBy(xpath = "//a[text()='Explanation for reject at triage is required']")
    public WebElementFacade explanationForRejectionAtTriageRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Explanation for reject at draft is required']")
    public WebElementFacade explanationForRejectionAtDraftRequiredErrorMessage;

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
    public void moveCaseFromQAToNextStage() {
        safeClickOn(approvedAtQARadioButton);
        safeClickOn(confirmButton);
    }

    public void putQACaseOnHold() {
        safeClickOn(onHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void escalateQACaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
    }

    public void rejectQACaseAtDraft(String rejectionReason) {
        safeClickOn(rejectQAAtDraftRadioButton);
        safeClickOn(confirmButton);
        typeInto(draftRejectionTextField, rejectionReason);
        safeClickOn(confirmButton);
    }

    public void rejectQACaseAtTriage(String rejectionReason) {
        safeClickOn(rejectQAAtTriageRadioButton);
        safeClickOn(confirmButton);
        typeInto(triageRejectionTextField, rejectionReason);
        safeClickOn(confirmButton);
    }

    //QA (Escalated) Actions
    public void keepCaseEscalated() {
        safeClickOn(keepEscalatedRadioButton);
        safeClickOn(confirmButton);
    }

    public void takeCaseOffEscalation() {
        safeClickOn(escalationCompleteRadioButton);
        safeClickOn(confirmButton);
    }

    //QA (On Hold) Actions
    public void keepCaseOnHold() {
        safeClickOn(keepOnHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void takeCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    //Assertions
    public void assertActionsRequiredErrorMessageDisplayed() {
        assertThat(actionsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertRejectAtTriageReasonRequiredErrorMessageDisplayed() {
        assertThat(explanationForRejectionAtTriageRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertRejectAtDraftReasonRequiredErrorMessageDisplayed() {
        assertThat(explanationForRejectionAtDraftRequiredErrorMessage.isVisible(), is(true));
    }
}