package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class IEDETProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Correspondents correspondents;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsSend complaintsSend;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

    Documents documents;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("IEDET");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheIEDETStage(precedingStage);
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

    public void completeTheIEDETStage(String stageToComplete) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveIEDETCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                moveIEDETCaseFromTriageToDraft();
                break;
            case "DRAFT":
                moveIEDETCaseFromDraftToSend();
                break;
            case "SEND":
                moveIEDETCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveIEDETCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
        complaintsRegistrationAndDataInput.selectAChannel();
        complaintsRegistrationAndDataInput.selectComplaintOrigin();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        clickTheButton("Continue");
        complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
        complaintsRegistrationAndDataInput.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Triage");
    }

    public void moveIEDETCaseFromTriageToDraft() {
        complaintsTriageAndInvestigation.selectTransferredToIEDetentionComplianceTeam();
        clickTheButton("Continue");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        safeClickOn(continueButton);
        System.out.println("Case moved from Triage to Draft");
    }

    public void moveIEDETCaseFromDraftToSend() {
        clickTheButton("Proceed to recording outcome");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveIEDETCaseFromSendToCaseClosed() {
        documents.addADocumentOfDocumentType("Final Response");
        complaintsSend.selectACaseOutcome();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send to Closed");
    }

    public void generateIEDETSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("IEDET");
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("IEDET");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("IEDET");
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
                createCase.createCSCaseOfType("IEDET");
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
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
