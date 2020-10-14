package com.hocs.test.pages.mpam;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QA extends BasePage {

    Homepage homepage;

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

    @FindBy(id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade reasonForEscalationTextField;

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

    @FindBy(xpath = "//label[text()='Close case']")
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

    public void escalateQACaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
        typeInto(reasonForEscalationTextField, "Test");
        safeClickOn(confirmButton);
    }

    public void selectToRejectCaseToDraft() {
        safeClickOn(rejectQAToDraftRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectToRejectCaseToTriage() {
        safeClickOn(rejectQAToTriageRadioButton);
        safeClickOn(confirmButton);
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

    public void submitReasonToCloseEscalatedCase(String closureReason) {
        typeInto(closureReasonTextArea, closureReason);
        safeClickOn(closeCaseButton);
        setSessionVariable("closureReason").to(closureReason);
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