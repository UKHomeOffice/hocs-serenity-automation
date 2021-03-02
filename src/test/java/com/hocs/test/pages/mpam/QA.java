package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class QA extends BasePage {

    Dashboard dashboard;

    //QA Elements
    @FindBy(xpath = "//label[text()='Approve']")
    public WebElementFacade approvedAtQARadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to drafting']")
    public WebElementFacade rejectQAToDraftRadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to triage']")
    public WebElementFacade rejectQAToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(id = "CaseNote_RejectDraft")
    public WebElementFacade draftRejectionTextField;

    @FindBy(id = "CaseNote_RejectTriage")
    public WebElementFacade triageRejectionTextField;

    @FindBy(xpath = "//a[text()='Explanation for rejection, move back to triage is required']")
    public WebElementFacade explanationForRejectionBacktoTriageRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Explanation for rejection, move back to drafting is required']")
    public WebElementFacade explanationForRejectionBackToDraftRequiredErrorMessage;

    @FindBy(id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade escalationReasonTextArea;

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

    @FindBy(xpath = "//label[text()='Close duplicate case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(id = "CaseNote_QaClose")
    public WebElementFacade closureReasonTextArea;

    //QA Actions
    public void moveCaseFromQAToNextStage() {
        safeClickOn(approvedAtQARadioButton);
        safeClickOn(confirmButton);
    }

    public void putQACaseOnHold() {
        safeClickOn(onHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectEscalateQACaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectToRejectCaseToDraft() {
        safeClickOn(rejectQAToDraftRadioButton);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionStage").to("QA");
    }

    public void selectToRejectCaseToTriage() {
        safeClickOn(rejectQAToTriageRadioButton);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionStage").to("QA");
    }

    public void submitReasonToEscalateCase(String escalationReason) {
        typeInto(escalationReasonTextArea, escalationReason);
        safeClickOn(confirmButton);
        setSessionVariable("escalationReason").to(escalationReason);
    }

    public void submitReasonToRejectToDraft(String rejectionReason) {
        typeInto(draftRejectionTextField, rejectionReason);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void submitReasonToRejectToTriage(String rejectionReason) {
        typeInto(triageRejectionTextField, rejectionReason);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionReason").to(rejectionReason);
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

    public void selectToCloseEscalatedCase() {
        safeClickOn(closeCaseRadioButton);
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

    public void assertRejectBackToTriageReasonRequiredErrorMessageDisplayed() {
        assertThat(explanationForRejectionBacktoTriageRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertRejectBackToDraftReasonRequiredErrorMessageDisplayed() {
        assertThat(explanationForRejectionBackToDraftRequiredErrorMessage.isVisible(), is(true));
    }
}