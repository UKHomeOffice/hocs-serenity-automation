package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class SMCProgressCase extends BasePage {

    CaseView caseView;

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Dashboard dashboard;

    Documents documents;

    Correspondents correspondents;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsSend complaintsSend;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("SMC");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheSMCStage(precedingStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "DRAFT":
                precedingStage = "TRIAGE";
                break;
            case "SEND":
                precedingStage = "DRAFT";
                break;
            case "CASE CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheSMCStage(String stageToComplete) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveSMCCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                moveSMCCaseFromTriageToDraft();
                break;
            case "DRAFT":
                moveSMCCaseFromDraftToSend();
                break;
            case "SEND":
                moveSMCCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveSMCCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectAChannel();
        registration.selectComplaintOrigin();
        registration.selectAdditionalInformation();
        registration.enterADescriptionOfTheComplaint();
        registration.enterAPreviousUKVIComplaintReference();
        registration.enterAThirdPartyReference();
        clickTheButton("Continue");
        registration.openTheSeriousComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Triage");

    }

    public void moveSMCCaseFromTriageToDraft() {
        complaintsTriage.selectAcceptCase();
        clickTheButton("Continue");
        complaintsTriage.enterPSUReference();
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Contributions");
        if(sessionVariableCalled("isLoARequired").equals("Yes")) {
            complaintsTriage.enterLoAReceivedDetails();
        }
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Draft");
    }

    public void moveSMCCaseFromDraftToSend() {
        documents.addADocumentOfDocumentType("DRAFT");
        clickTheButton("Response Ready");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveSMCCaseFromSendToCaseClosed() {
        documents.addADocumentOfDocumentType("Final Response");
        complaintsSend.selectACaseOutcome();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send to Closed");
    }

    public void generateSMCSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("SMC");
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("SMC");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("SMC");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                registration.enterComplainantDOB(infoValue);
                registration.selectAGender();
                registration.selectANationality();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
                registration.enterAPortReference();
                clickTheButton("Continue");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("SMC");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                registration.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
                registration.selectAGender();
                registration.selectANationality();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference(infoValue);
                registration.enterAPortReference();
                clickTheButton("Continue");
                break;
            case "PSU REFERENCE":
                moveCaseFromCurrentStageToTargetStage("N/A", "TRIAGE");
                dashboard.getAndClaimCurrentCase();
                complaintsTriage.selectAcceptCase();
                clickTheButton("Continue");
                complaintsTriage.enterSpecificPSUReference(infoValue);
                clickTheButton("Continue");
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}