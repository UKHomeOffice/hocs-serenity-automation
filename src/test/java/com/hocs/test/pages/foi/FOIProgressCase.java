package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.CreateCase_SuccessPage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class FOIProgressCase extends BasePage {

    CreateCase_SuccessPage createCaseSuccessPage;

    CreateCase createCase;

    Dashboard dashboard;

    RecordCaseData recordCaseData;

    CaseCreationStage caseCreationStage;

    Approval approval;

    Documents documents;

    public void moveCaseFromCaseCreationToAllocation() {
        String responseDate = getTodaysDate();
        clickTheButton("Confirm");
        caseCreationStage.selectValidityOfRequest("Valid");
        safeClickOn(continueButton);
        waitABit(250);
        documents.addInitialResponseDocument();
        responseDate = getDatePlusMinusNDaysAgo(-10);
        recordCaseData.enterDateIntoDateFieldsWithHeading(responseDate, "When was the acknowledgement response to the valid request issued?");
        clickTheButton("Complete Create");
        waitABit(250);
    }

    public void moveCaseFromAllocationToAcceptance() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Directorate");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Acceptance Team");
        clickTheButton("Allocate Case");
        waitABit(250);
        clickTheButton("Confirm Allocation");
    }

    public void moveCaseFromAcceptanceToConsiderAndDraft() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Does this case belong to your Directorate?");
        clickTheButton("Continue");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Please select the team required for drafting the response");
        clickTheButton("Complete Acceptance");
    }

    public void moveCaseFromConsiderAndDraftToApproval() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No", "Do you need to request contributions?");
        clickTheButton("Continue");
        safeClickOn(documents.addDocumentsButton);
        recordCaseData.selectSpecificOptionFromDropdownWithHeading("Draft response", "Document type");
        documents.uploadDocumentOfType("docx");
        clickTheButton("Add");
        clickTheButton("Complete Draft");
    }

    public void moveCaseFromApprovalToDispatch() {
        approval.addAnApprovalRequestWithStatus("Complete");
        clickTheButton("Complete Approval");
    }

    public void moveCaseFromDispatchToSoftClose() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading( "What type of case is this?");
        recordCaseData.selectRandomOptionFromDropdownWithHeading( "How will the response be sent?");
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Information released in full","What was the outcome of this case?");
        clickTheButton("Continue");
        clickTheButton("Confirm");
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Are you sure you want to dispatch this case?");
        clickTheButton("Complete Dispatch");
        waitABit(500);
    }
}