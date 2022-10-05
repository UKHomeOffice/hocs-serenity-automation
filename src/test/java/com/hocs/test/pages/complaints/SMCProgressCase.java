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

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

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
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        complaintsRegistrationAndDataInput.selectAComplaintChannel();
        complaintsRegistrationAndDataInput.selectComplaintOrigin();
        complaintsRegistrationAndDataInput.selectAdditionalInformation();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAPreviousUKVIComplaintReference();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        clickTheButton("Continue");
        complaintsRegistrationAndDataInput.openTheSeriousComplaintCategoryAccordion();
        waitABit(1000);
        complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
        complaintsRegistrationAndDataInput.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Triage");

    }

    public void moveSMCCaseFromTriageToDraft() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        clickTheButton("Continue");
        complaintsTriageAndInvestigation.enterPSUReference();
        clickTheButton("Continue");
        waitForDECSPageWithTitle("Complaint Category");
        clickTheButton("Continue");
        waitForDECSPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        waitForDECSPageWithTitle("Triage Contributions");
        if(sessionVariableCalled("isLoARequired").equals("Yes")) {
            complaintsTriageAndInvestigation.enterLoAReceivedDetails();
        }
        complaintsTriageAndInvestigation.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Draft");
    }

    public void moveSMCCaseFromDraftToSend() {
        documents.addADocumentOfDocumentType("DRAFT");
        clickTheButton("Response Ready");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveSMCCaseFromSendToCaseClosed() {
        documents.addADocumentOfDocumentType("Final Response");
        complaintsDispatchAndSend.selectACaseOutcome();
        complaintsDispatchAndSend.enterADateOfResponse();
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
                complaintsRegistrationAndDataInput.enterComplainantDOB(infoValue);
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.selectANationality();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickTheButton("Continue");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("SMC");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.selectANationality();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(infoValue);
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickTheButton("Continue");
                break;
            case "PSU REFERENCE":
                moveCaseFromCurrentStageToTargetStage("N/A", "TRIAGE");
                dashboard.getAndClaimCurrentCase();
                complaintsTriageAndInvestigation.selectAcceptCase();
                clickTheButton("Continue");
                complaintsTriageAndInvestigation.enterSpecificPSUReference(infoValue);
                clickTheButton("Continue");
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}