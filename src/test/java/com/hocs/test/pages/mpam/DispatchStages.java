package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DispatchStages extends BasePage {

    Dashboard dashboard;

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10", id = "DateDispatched-day")
    public WebElementFacade dispatchedDateDayTextField;

    @FindBy(timeoutInSeconds = "10", id = "DateDispatched-month")
    public WebElementFacade dispatchedDateMonthTextField;

    @FindBy(timeoutInSeconds = "10", id = "DateDispatched-year")
    public WebElementFacade dispatchedDateYearTextField;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='ChannelOut-radios']//label[text()='Email']")
    public WebElementFacade responseChannelEmailRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='ChannelOut-radios']//label[text()='Letter']")
    public WebElementFacade responseChannelLetterRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Dispatch, close case']")
    public WebElementFacade dispatchedCloseCaseRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Draft rejected by private office']")
    public WebElementFacade draftRejectedRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Dispatch, close case']")
    public WebElementFacade dispatchedRadioButtonAtDispatch;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Dispatched']")
    public WebElementFacade dispatchedRadioButtonAtPrivateOffice;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Dispatched (follow-up)']")
    public WebElementFacade dispatchedFollowUpRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Return to Draft']")
    public WebElementFacade returnToDraftButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Follow-up completed']")
    public WebElementFacade followUpCompletedRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Close, follow-up action not completed']")
    public WebElementFacade closeFollowUpNotCompletedRadioButton;

    @FindBy(timeoutInSeconds = "10", css = "input[value='Confirm and close case']")
    public WebElementFacade confirmAndCloseCaseButton;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_RejectPfs")
    public WebElementFacade rejectionReasonTextArea;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_DispatchReturnToDraft")
    public WebElementFacade returnReasonTextArea;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_DispatchFollowUpNotCompleted")
    public WebElementFacade followUpNotCompletedReasonTextArea;

    @FindBy(timeoutInSeconds = "10",  id = "DueDate-day")
    public WebElementFacade dueDateDayTextField;

    @FindBy(timeoutInSeconds = "10",  id = "DueDate-month")
    public WebElementFacade dueDateMonthTextField;

    @FindBy(timeoutInSeconds = "10",  id = "DueDate-year")
    public WebElementFacade dueDateYearTextField;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_DispatchFollowUpRequest")
    public WebElementFacade followUpDetailsTextArea;

    public void selectActionAtPrivateOffice(String action) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(action, "Actions");
        if (action.toUpperCase().equals("DRAFT REJECTED BY PRIVATE OFFICE")) {
            setSessionVariable("rejectionStage").to("PO");
        }
        clickConfirmButton();
    }

    public void inputDispatchedDate(String date) {
        enterDateIntoDateFieldsWithHeading(date, "Dispatched date");
    }

    public void triggerAwaitingDispatchErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE REQUIRED":
                safeClickOn(dispatchedCloseCaseRadioButton);
                clickConfirmButton();
                break;
            case "ACTIONS REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                clickConfirmButton();
                break;
            case "FOLLOW-UP DATE REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(dispatchedFollowUpRadioButton);
                clickConfirmButton();
                enterFollowUpDetails("Test follow-up details");
                clickConfirmButton();
                break;
            case "FOLLOW-UP DETAILS REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(dispatchedFollowUpRadioButton);
                clickConfirmButton();
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                clickConfirmButton();
                break;
            case "FOLLOW-UP NOT COMPLETED REASON REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(dispatchedFollowUpRadioButton);
                clickConfirmButton();
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                enterFollowUpDetails("Test follow-up details");
                clickConfirmButton();
                dashboard.getCurrentCase();
                safeClickOn(closeFollowUpNotCompletedRadioButton);
                clickConfirmButton();
                safeClickOn(confirmAndCloseCaseButton);
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    public void triggerPrivateOfficeErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE REQUIRED":
                selectAResponseChannel();
                safeClickOn(dispatchedRadioButtonAtPrivateOffice);
                clickConfirmButton();
                safeClickOn(confirmAndCloseCaseButton);
                break;
            case "ACTIONS REQUIRED":
                selectAResponseChannel();
                clickConfirmButton();
                break;
            case "FOLLOW-UP DATE REQUIRED":
                selectAResponseChannel();
                safeClickOn(dispatchedFollowUpRadioButton);
                clickConfirmButton();
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                enterFollowUpDetails("Test follow-up details");
                clickConfirmButton();
                break;
            case "FOLLOW-UP DETAILS REQUIRED":
                selectAResponseChannel();
                safeClickOn(dispatchedFollowUpRadioButton);
                clickConfirmButton();
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                clickConfirmButton();
                break;
            case "FOLLOW-UP NOT COMPLETED REASON REQUIRED":
                selectAResponseChannel();
                safeClickOn(dispatchedFollowUpRadioButton);
                clickConfirmButton();
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                enterFollowUpDetails("Test follow-up details");
                clickConfirmButton();
                dashboard.getCurrentCase();
                safeClickOn(closeFollowUpNotCompletedRadioButton);
                clickConfirmButton();
                safeClickOn(confirmAndCloseCaseButton);
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    public void submitReasonToRejectToDraft(String rejectionReason) {
        rejectionReasonTextArea.waitUntilVisible().sendKeys(rejectionReason);
        clickConfirmButton();
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void submitReasonToReturnToDraft(String returnReason) {
        returnReasonTextArea.sendKeys(returnReason);
        clickConfirmButton();
        setSessionVariable("rejectionReason").to(returnReason);
    }

    public void assertThatRejectionReasonTextAreaVisible() {
        assertThat(rejectionReasonTextArea.isVisible(), is(true));
    }

    public void followUpDateInput(String date) {
        typeIntoDateFields(dueDateDayTextField, dueDateMonthTextField, dueDateYearTextField, date);
        setSessionVariable("dueDate").to(date);
    }

    public void enterFollowUpDetails(String followUpDetails) {
        followUpDetailsTextArea.sendKeys(followUpDetails);
        setSessionVariable("followUpDetails").to(followUpDetails);
    }

    public void enterReasonForNotCompletingFollowUp(String reason) {
        followUpNotCompletedReasonTextArea.sendKeys(reason);
        setSessionVariable("followUpNotCompletedReason").to(reason);
    }

    public void selectAResponseChannel() {
        selectRandomRadioButtonFromGroupWithHeading("Response channel");
    }

    public void selectDispatchAndCloseCaseAction() {
        selectSpecificRadioButton("Dispatch and close case");
    }

    public void selectSendBackToPrivateOfficeAction() {
        selectSpecificRadioButton("Move back to Private Office");
    }
}
