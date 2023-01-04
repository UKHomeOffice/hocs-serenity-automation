package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Approval extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[text()='Add an Approval Request']")
    public WebElementFacade addAnApprovalRequestHypertext;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElementFacade editHypertext;

    public void selectApproverRole() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Approver Role");
    }

    public void enterApprovalRequestDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-5), "Approval request date");
    }

    public void enterApprovalDueDate(String dueDate) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(dueDate, "Approval due date");
    }

    public void selectApprovalStatus(String status) {
        if (status.equalsIgnoreCase("COMPLETE")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Complete", "Approval Status");
        } else if (status.equalsIgnoreCase("CANCELLED")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Cancelled", "Approval Status");
        }
    }

    public void selectApprovalRequestDecision() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Approve","Decision");
    }

    public void enterApprovalReceivedDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date response received");
    }

    public void enterApproverName() {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Mr Test Approver", "Respondents name");
    }

    public void enterApprovalReceivedDetails() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Approval Received Details", "Details");
    }

    public void enterReasonForCancellation() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Cancellation Reason", "Reason for cancelling");
    }

    public void addAnApprovalRequestWithStatus(String status) {
        String dueDate = getTodaysDate();
        safeClickOn(addAnApprovalRequestHypertext);
        selectApproverRole();
        enterApprovalRequestDate();
        if (status.equalsIgnoreCase("DUE")) {
            dueDate = getDatePlusMinusNDaysAgo(10);
        } else if (status.equalsIgnoreCase("OVERDUE")) {
            dueDate = getDatePlusMinusNDaysAgo(-10);
        }
        enterApprovalDueDate(dueDate);
        clickTheButton("Add");
        if (status.equalsIgnoreCase("COMPLETE") || status.equalsIgnoreCase("CANCELLED")) {
            safeClickOn(editHypertext);
            selectApprovalStatus(status);
            if (status.equalsIgnoreCase("COMPLETE")) {
                selectApprovalRequestDecision();
                enterApprovalReceivedDate();
                enterApproverName();
                enterApprovalReceivedDetails();
            } else if (status.equalsIgnoreCase("CANCELLED")) {
                enterReasonForCancellation();
            }
            clickTheButton("Update");
        }
    }

    public void assertStatusOfApprovalRequest(String status) {
        WebElementFacade requestStatusField = findBy("//legend[@id='ApprovalRequests-legend']/following-sibling::table//td[2]").withTimeoutOf(
                Duration.ofSeconds(10));
        String displayedStatus = requestStatusField.waitUntilVisible().getText();
        assertThat(displayedStatus.contains(status), is(true));
    }
}
