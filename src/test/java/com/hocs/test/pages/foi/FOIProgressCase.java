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
        safeClickOn(continueButton);
        caseCreationStage.selectValidityOfRequest("Yes");
        safeClickOn(continueButton);
        waitABit(250);
        clickTheButton("Finish");
    }

    public void moveCaseFromAllocationToAcceptance() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Directorate");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Acceptance Team");
        clickTheButton("Allocate Case");
        waitABit(250);
        clickTheButton("Return to Dashboard");
    }

    public void moveCaseFromAcceptanceToConsiderAndDraft() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Does this case belong to your Directorate?");
        clickTheButton("Continue");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Please select the team required for drafting the response");
        clickTheButton("Finish");
    }

    public void moveCaseFromConsiderAndDraftToApproval() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Is this case for your drafting team?");
        clickTheButton("Continue");
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No", "Do you need to request contributions?");
        clickTheButton("Continue");
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Full disclosure", "What is the response type?");
        clickTheButton("Continue");
        safeClickOn(documents.addDocumentsButton);
        recordCaseData.selectSpecificOptionFromDropdownWithHeading("Draft response", "Document type");
        documents.uploadDocumentOfType("docx");
        clickTheButton("Add");
        clickTheButton("Continue");
    }

    public void moveCaseFromApprovalToDispatch() {
        approval.addAnApprovalRequestWithStatus("Complete5");
        clickTheButton("Continue");
    }

    public void moveCaseFromDispatchToSoftClose() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Are you sure you want to dispatch this case?");
        clickTheButton("Continue");
    }
}