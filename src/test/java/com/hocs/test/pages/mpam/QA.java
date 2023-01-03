package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class QA extends BasePage {

    RecordCaseData recordCaseData;

    //QA Elements
    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Approve']")
    public WebElementFacade approvedAtQARadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Rejected, move back to drafting']")
    public WebElementFacade rejectQAToDraftRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Rejected, move back to triage']")
    public WebElementFacade rejectQAToTriageRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Put on hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_RejectDraft")
    public WebElementFacade draftRejectionTextField;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_RejectTriage")
    public WebElementFacade triageRejectionTextField;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade escalationReasonTextArea;

    //QA (Escalated) Elements
    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Keep escalated']")
    public WebElementFacade keepEscalatedRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    //QA (On Hold) Elements
    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Keep on hold']")
    public WebElementFacade keepOnHoldRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Close duplicate case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_QaClose")
    public WebElementFacade closureReasonTextArea;

    //QA Actions

    public void putQACaseOnHold() {
        safeClickOn(onHoldRadioButton);
        clickConfirmButton();
    }

    public void selectEscalateQACaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        clickConfirmButton();
    }

    public void selectToRejectCaseToDraft() {
        safeClickOn(rejectQAToDraftRadioButton);
        clickConfirmButton();
        setSessionVariable("rejectionStage").to("QA");
    }

    public void selectToRejectCaseToTriage() {
        safeClickOn(rejectQAToTriageRadioButton);
        clickConfirmButton();
        setSessionVariable("rejectionStage").to("QA");
    }

    public void selectToRequestSecretariatClearance() {
        selectSpecificRadioButtonFromGroupWithHeading("Request Secretariat Clearance", "Actions");
        clickConfirmButton();
    }

    public void addAClearanceRequest() {
        String secretariat = recordCaseData.selectRandomOptionFromDropdownWithHeading("Secretariat");
        setSessionVariable("clearanceRequestBusinessArea").to(secretariat);
        setSessionVariable("clearanceRequestDate").to(getTodaysDate());
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(5), "Clearance Due Date");
        setSessionVariable("clearanceDueDate").to(getDatePlusMinusNDaysAgo(5));
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Clearance Request Details", "What are you requesting");
        clickTheButton("Add");
    }

    public void submitReasonToEscalateCase(String escalationReason) {
        escalationReasonTextArea.sendKeys(escalationReason);
        clickConfirmButton();
        setSessionVariable("escalationReason").to(escalationReason);
    }

    public void submitReasonToRejectToDraft(String rejectionReason) {
        draftRejectionTextField.sendKeys(rejectionReason);
        clickConfirmButton();
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void submitReasonToRejectToTriage(String rejectionReason) {
        triageRejectionTextField.sendKeys(rejectionReason);
        clickConfirmButton();
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    //QA (Escalated) Actions
    public void keepCaseEscalated() {
        safeClickOn(keepEscalatedRadioButton);
        clickConfirmButton();
    }

    public void takeCaseOffEscalation() {
        safeClickOn(escalationCompleteRadioButton);
        clickConfirmButton();
    }

    public void selectToCloseEscalatedCase() {
        safeClickOn(closeCaseRadioButton);
        clickConfirmButton();
    }

    //QA (On Hold) Actions
    public void keepCaseOnHold() {
        safeClickOn(keepOnHoldRadioButton);
        clickConfirmButton();
    }

    public void takeCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        clickConfirmButton();
    }

    //QA (Secretariat Clearance Requested) Actions
    public void enterClearanceReceivedDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Clearance Received Date");
        setSessionVariable("clearanceReceivedDate").to(getTodaysDate());
    }

    public void selectApprovedMoveToPrivateOfficeAtQASecretariatClearanceRequested() {
        selectSpecificRadioButtonFromGroupWithHeading("Approve, move to Private Office", "Clearance Status");
        clickConfirmButton();
    }

    public void rejectACaseToDraftAtQASecretariatClearanceRequested() {
        selectSpecificRadioButtonFromGroupWithHeading("Rejected, move to Draft", "Clearance Status");
        String rejectionReason = enterTextIntoTextAreaWithHeading("Reason for Rejection");
        setSessionVariable("rejectionReason").to(rejectionReason);
        clickConfirmButton();
    }

    public void cancelTheClearanceRequestAtQASecretariatClearanceRequested() {
        selectSpecificRadioButtonFromGroupWithHeading("Cancelled", "Clearance Status");
        String cancellationDetails = enterTextIntoTextAreaWithHeading("Details");
        setSessionVariable("cancellationDetails").to(cancellationDetails);
        clickConfirmButton();
    }
}
