package com.hocs.test.pages.foi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class FOIProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    CaseCreationStage caseCreationStage;

    Allocation allocation;

    Acceptance acceptance;

    Approval approval;

    ConsiderAndDraft considerAndDraft;

    FOIDispatch foiDispatch;

    Documents documents;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("FOI");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheFOIStage(precedingStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "CASE CREATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "ALLOCATION":
                precedingStage = "CASE CREATION";
                break;
            case "ACCEPTANCE":
                precedingStage = "ALLOCATION";
                break;
            case "CONSIDER AND DRAFT":
                precedingStage = "ACCEPTANCE";
                break;
            case "APPROVAL":
                precedingStage = "CONSIDER AND DRAFT";
                break;
            case "DISPATCH":
                precedingStage = "APPROVAL";
                break;
            case "SOFT CLOSE":
                precedingStage = "DISPATCH";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheFOIStage(String stageToComplete) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "CASE CREATION":
                moveCaseFromCaseCreationToAllocation();
                break;
            case "ALLOCATION":
                moveCaseFromAllocationToAcceptance();
                break;
            case "ACCEPTANCE":
                moveCaseFromAcceptanceToConsiderAndDraft();
                break;
            case "CONSIDER AND DRAFT":
                moveCaseFromConsiderAndDraftToApproval();
                break;
            case "APPROVAL":
                moveCaseFromApprovalToDispatch();
                break;
            case "DISPATCH":
                moveCaseFromDispatchToSoftClose();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        if (stageToComplete.equalsIgnoreCase("ACCEPTANCE") || stageToComplete.equalsIgnoreCase("ALLOCATION")) {
            dashboard.waitForDashboard();
        }
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.resetDataRecords();
    }

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
        allocation.selectAnAccountManager();
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
        documents.addADocumentOfType("Final responses");
        clickTheButton("Complete Dispatch");
        waitABit(500);
    }
}