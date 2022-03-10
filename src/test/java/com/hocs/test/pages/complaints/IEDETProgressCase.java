package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;

public class IEDETProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Correspondents correspondents;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsSend complaintsSend;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

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
        registration.enterComplainantDetails();
        registration.selectASpecificComplaintType("Service");
        registration.selectAChannel();
        registration.selectComplaintOrigin();
        registration.enterADescriptionOfTheComplaint();
        registration.enterAThirdPartyReference();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Triage");
    }

    public void moveIEDETCaseFromTriageToDraft() {
        complaintsTriage.selectTransferredToIEDetentionComplianceTeam();
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        safeClickOn(continueButton);
        System.out.println("Case moved from Triage to Draft");
    }

    public void moveIEDETCaseFromDraftToSend() {
        clickTheButton("Proceed to recording outcome");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveIEDETCaseFromSendToCaseClosed() {
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
                registration.enterComplainantDOB(infoValue);
                registration.selectAGender();
                registration.selectANationality();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
                registration.enterAPortReference();
                clickTheButton("Continue");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("IEDET");
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
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
