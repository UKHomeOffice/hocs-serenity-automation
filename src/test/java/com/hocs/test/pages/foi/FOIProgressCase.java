package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class FOIProgressCase extends BasePage {

    RecordCaseData recordCaseData;

    CaseCreationStage caseCreationStage;

    Allocation allocation;

    Acceptance acceptance;

    Approval approval;

    ConsiderAndDraft considerAndDraft;

    FOIDispatch foiDispatch;

    Documents documents;

    public void moveCaseFromCaseCreationToAllocation() {
        clickTheButton("Confirm");
        caseCreationStage.selectValidityOfRequest("Valid");
        safeClickOn(continueButton);
        waitABit(250);
        documents.addADocumentOfType("Initial response");
        caseCreationStage.enterAValidRequestAcknowledgementResponseDate();
        clickTheButton("Complete Create");
        waitABit(250);
    }

    public void moveCaseFromAllocationToAcceptance() {
        allocation.selectADirectorate();
        allocation.selectAnAcceptanceTeam();
        clickTheButton("Allocate Case");
        waitABit(250);
        clickTheButton("Confirm Allocation");
    }

    public void moveCaseFromAcceptanceToConsiderAndDraft() {
        acceptance.selectIfCaseIsInCorrectDirectorate("Yes");
        clickTheButton("Continue");
        acceptance.selectDraftTeam();
        clickTheButton("Complete Acceptance");
    }

    public void moveCaseFromConsiderAndDraftToApproval() {
        considerAndDraft.isContributionRequestNeeded("No");
        clickTheButton("Continue");
        documents.addADocumentOfType("Draft response");
        clickTheButton("Complete Draft");
    }

    public void moveCaseFromApprovalToDispatch() {
        approval.addAnApprovalRequestWithStatus("Complete");
        clickTheButton("Complete Approval");
    }

    public void moveCaseFromDispatchToSoftClose() {
        foiDispatch.selectACaseType();
        foiDispatch.selectAResponseChannel();
        foiDispatch.selectOutcomeOfTheCase("Information released in full");
        clickTheButton("Continue");
        clickTheButton("Confirm");
        foiDispatch.selectDoYouWantToDispatch("Yes");
        foiDispatch.enterFinalResponseDate();
        clickTheButton("Continue");
        documents.uploadDocumentOfType("Final responses");
        clickTheButton("Complete Dispatch");
        waitABit(500);
    }
}