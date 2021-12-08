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
    @FindBy(xpath = "//label[text()='Approve']")
    public WebElementFacade approvedAtQARadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to drafting']")
    public WebElementFacade rejectQAToDraftRadioButton;

    @FindBy(xpath = "//label[text()='Rejected, move back to triage']")
    public WebElementFacade rejectQAToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(id = "CaseNote_RejectDraft")
    public WebElementFacade draftRejectionTextField;

    @FindBy(id = "CaseNote_RejectTriage")
    public WebElementFacade triageRejectionTextField;

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

    public void selectToRequestSecretariatClearance() {
        selectSpecificRadioButtonFromGroupWithHeading("Request Secretariat Clearance", "Actions");
        safeClickOn(confirmButton);
    }

    public void addAClearanceRequest() {
        String businessArea = recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Area");
        setSessionVariable("clearanceRequestBusinessArea").to(businessArea);
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Clearance Request Date");
        setSessionVariable("clearanceRequestDate").to(getTodaysDate());
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(5), "Clearance Due Date");
        setSessionVariable("clearanceDueDate").to(getDatePlusMinusNDaysAgo(5));
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Clearance Request Details", "What are you requesting");
        clickTheButton("Add");
    }

    public void submitReasonToEscalateCase(String escalationReason) {
        escalationReasonTextArea.sendKeys(escalationReason);
        safeClickOn(confirmButton);
        setSessionVariable("escalationReason").to(escalationReason);
    }

    public void submitReasonToRejectToDraft(String rejectionReason) {
        draftRejectionTextField.sendKeys(rejectionReason);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void submitReasonToRejectToTriage(String rejectionReason) {
        triageRejectionTextField.sendKeys(rejectionReason);
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
}