package com.hocs.test.pages.foi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import config.CaseType;

public class FOIProgressCase extends BasePage {

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

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
            createCase.createCSCaseOfTypeWithDocument(CaseType.FOI);
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
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromCaseCreationToAllocation() {
        clickConfirmButton();
        caseCreationStage.selectValidityOfRequest("Valid");
        clickContinueButton();
        waitABit(250);
        documents.addADocumentOfDocumentType("Initial response");
        caseCreationStage.enterAValidRequestAcknowledgementResponseDate();
        clickTheButton("Complete Create");
        waitABit(250);
    }

    public void moveCaseFromAllocationToAcceptance() {
        allocation.selectAGroup();
        allocation.selectAnAccountManager();
        clickTheButton("Allocate Case");
        waitForDECSPageWithTitle("FOI Allocation");
        clickTheButton("Confirm Allocation");
    }

    public void moveCaseFromAcceptanceToConsiderAndDraft() {
        acceptance.selectIfCaseIsInCorrectGroup("Yes");
        clickContinueButton();
        acceptance.selectAResponsibleTeam();
        clickTheButton("Complete Acceptance");
    }

    public void moveCaseFromConsiderAndDraftToApproval() {
        considerAndDraft.isContributionRequestNeeded("No");
        clickContinueButton();
        documents.addADocumentOfDocumentType("Draft response");
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
        clickContinueButton();
        clickConfirmButton();
        foiDispatch.selectDoYouWantToDispatch("Yes");
        foiDispatch.enterFinalResponseDate();
        clickContinueButton();
        documents.addADocumentOfDocumentType("Final responses");
        clickTheButton("Complete Dispatch");
        waitABit(500);
    }

    public void generateFOISearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
            case "CORRESPONDENT (NON-MP)":
            case "TOPIC":
            case "ACTIVE CASES ONLY":
                createCase.createCSCaseOfTypeWithDocument(CaseType.FOI);
                dashboard.goToDashboard();
                break;
            case "RECEIVED ON OR AFTER":
            case "RECEIVED ON OR BEFORE":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                if (!buttonIsVisible("Next")) {
                    dashboard.selectCreateSingleCaseLinkFromMenuBar();
                }
                createCase.selectCaseType(CaseType.FOI);
                clickNextButton();
                createCase.editReceivedDate(infoValue);
                createCase.storeCorrespondenceReceivedDate();
                createCase.storeCorrespondenceReceivedInKIMUDate();
                documents.uploadFileOfType("docx");
                createCase.selectCorrespondenceInboundChannel();
                createCase.enterCorrespondentDetails();
                createCase.selectFOITopic("Animal alternatives (3Rs)");
                createCase.enterRequestQuestion();
                clickTheButton("Submit");
                confirmationScreens.storeCaseReference();
                dashboard.goToDashboard();
                break;
            case "ALL":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                if (!buttonIsVisible("Next")) {
                    dashboard.selectCreateSingleCaseLinkFromMenuBar();
                }
                createCase.selectCaseType(CaseType.FOI);
                clickNextButton();
                createCase.editReceivedDate("01/01/2022");
                createCase.storeCorrespondenceReceivedDate();
                createCase.storeCorrespondenceReceivedInKIMUDate();
                documents.uploadFileOfType("docx");
                createCase.selectCorrespondenceInboundChannel();
                createCase.enterCorrespondentDetails();
                createCase.selectFOITopic("Animal alternatives (3Rs)");
                createCase.enterRequestQuestion();
                clickTheButton("Submit");
                confirmationScreens.storeCaseReference();
                dashboard.goToDashboard();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}