package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;

public class FOIProgressCase extends BasePage {

    FOIApproval foiApproval;

    Documents documents;

    public void moveCaseFromCaseCreationToKIMUAllocation() {
        clickTheButton("Confirm");
        selectSpecificRadioButtonFromGroupWithHeading("Yes", "Is this a valid request?");
        safeClickOn(documents.addDocumentLink);
        documents.selectDocumentTypeByText("Initial response");
        documents.uploadDocumentOfType("docx");
        clickTheButton("Add");
        clickTheButton("Continue");
    }

    public void moveCaseFromKIMUAllocationToAcceptance() {
        selectRandomOptionFromDropdownWithHeading("Type of request");
        clickTheButton("Continue");
        selectRandomOptionFromDropdownWithHeading("Directorate");
        selectRandomOptionFromDropdownWithHeading("Acceptance Team");
        enterSpecificTextIntoTextAreaWithHeading("Test support transfer comment", "Comment to support transfer");
        clickTheButton("Finish");
    }

    public void moveCaseFromAcceptanceToConsiderAndDraft() {
        selectSpecificRadioButtonFromGroupWithHeading("Yes", "Does this case belong to your Directorate?");
        clickTheButton("Continue");
        selectRandomOptionFromDropdownWithHeading("Please select the team required for drafting the response");
        clickTheButton("Finish");
    }

    public void moveCaseFromConsiderAndDraftToApproval() {
        selectSpecificRadioButtonFromGroupWithHeading("Yes", "Is this case for your drafting team?");
        clickTheButton("Continue");
        selectSpecificRadioButtonFromGroupWithHeading("No", "Do you need to request contributions?");
        clickTheButton("Continue");
        selectRandomRadioButtonFromGroupWithHeading("What is the response type?");
        if (!documents.addDocumentLink.isVisible()) {
            selectRandomRadioButtonFromGroupWithHeading("What is the reason for the exemption?");
            clickTheButton("Continue");
        }
        safeClickOn(documents.addDocumentLink);
        documents.selectDocumentTypeByText("Draft response");
        documents.uploadDocumentOfType("docx");
        clickTheButton("Add");
        clickTheButton("Continue");
    }

    public void moveCaseFromApprovalToDispatch() {
        safeClickOn(foiApproval.addAnApprovalRequestHypertext);
        selectRandomOptionFromDropdownWithHeading("Approver Role");
        enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Approval request date");
        enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(10), "Approval due date");
        clickTheButton("Add");
        safeClickOn(foiApproval.editHypertext);
        selectSpecificRadioButtonFromGroupWithHeading("Complete", "Approval Status");
        selectRandomRadioButtonFromGroupWithHeading("Decision");
        enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Approval received date");
        enterSpecificTextIntoTextAreaWithHeading("Test Approver", "Approver Name");
        enterSpecificTextIntoTextAreaWithHeading("Test Details Text", "Details");
        clickTheButton("Update");
        waitABit(250);
        clickTheButton("Continue");
    }

    public void moveCaseFromDispatchToSoftClose() {
        selectSpecificRadioButtonFromGroupWithHeading("Yes", "Are you sure you want to dispatch this case?");
        clickTheButton("Continue");
    }
}
