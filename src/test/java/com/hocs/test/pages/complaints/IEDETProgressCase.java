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
import config.CaseType;

public class IEDETProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Correspondents correspondents;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

    Documents documents;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheIEDETStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "IE DETENTION REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "IE DETENTION TRIAGE":
                precedingStage = "IE Detention REGISTRATION";
                break;
            case "IE DETENTION DRAFT":
            case "PSU REGISTRATION":
                precedingStage = "IE DETENTION TRIAGE";
                break;
            case "PSU TRIAGE":
                precedingStage = "PSU REGISTRATION";
                break;
            case "PSU COMPLAINT OUTCOME":
                precedingStage = "PSU TRIAGE";
                break;
            case "PSU CASE CLOSED":
                precedingStage = "PSU COMPLAINT OUTCOME";
                break;
            case "IE DETENTION OUTCOME":
                precedingStage = "IE DETENTION DRAFT";
                break;
            case "CASE CLOSED":
                precedingStage = "IE DETENTION OUTCOME";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheIEDETStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "IE DETENTION REGISTRATION":
                moveIEDETCaseFromRegistrationToTriage();
                break;
            case "IE DETENTION TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "IE DETENTION DRAFT":
                    case "HAPPY PATH":
                        moveIEDETCaseFromTriageToDraft();
                        break;
                    case "PSU REGISTRATION":
                        moveIEDETCaseFromTriageToPSURegistration();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "IE DETENTION DRAFT":
                moveIEDETCaseFromDraftToSend();
                break;
            case "PSU REGISTRATION":
                moveIEDETCaseFromPSURegistrationToPSUTriage();
                break;
            case "IE DETENTION OUTCOME":
                moveIEDETCaseFromSendToCaseClosed();
                break;
            case "PSU TRIAGE":
                moveIEDETCaseFromPSUTriageToPSUComplaintOutcome();
                break;
            case "PSU COMPLAINT OUTCOME":
                moveIEDETCaseFromPSUComplaintOutcomeToPSUCaseClosed();
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
        System.out.println("Case moved from Registration to Triage");
    }

    public void moveIEDETCaseFromTriageToDraft() {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
        complaintsTriageAndInvestigation.selectIEDETClaimCategory("Service");
        clickContinueButton();
        complaintsRegistrationAndDataInput.selectComplaintOrigin();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        complaintsTriageAndInvestigation.selectIEDETBusinessArea();
        clickContinueButton();
        complaintsTriageAndInvestigation.selecIEDetentionComplianceTeam();
        clickFinishButton();
        System.out.println("Case moved from Triage to Draft");
    }

    private void moveIEDETCaseFromTriageToPSURegistration() {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Serious misconduct");
        complaintsTriageAndInvestigation.selectIEDETClaimCategory("Serious misconduct");
        clickContinueButton();
        complaintsRegistrationAndDataInput.selectComplaintOrigin();
        complaintsTriageAndInvestigation.selectIEDETBusinessArea();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        clickTheButton("Finish and escalate to PSU");
    }

    private void moveIEDETCaseFromPSURegistrationToPSUTriage() {
        complaintsRegistrationAndDataInput.switchToPSUUser();
        complaintsRegistrationAndDataInput.enterAPSUReference();
        clickTheButton("Submit");
    }

    private void moveIEDETCaseFromPSUTriageToPSUComplaintOutcome() {
        complaintsRegistrationAndDataInput.selectYesForSeriousCase();
        clickTheButton("Submit");
        waitABit(1000);
        clickTheButton("Finish");
    }

    private void moveIEDETCaseFromPSUComplaintOutcomeToPSUCaseClosed() {
        complaintsRegistrationAndDataInput.selectRandomCaseOutcomeToProgress();
        clickTheButton("Submit");
        documents.addADocumentOfDocumentType("Final response");
        complaintsDispatchAndSend.enterFinalResponseSentDate();
        clickTheButton("Close case");
        setCurrentCaseType(CaseType.valueOf("IEDETPSU"));

    }

    public void moveIEDETCaseFromDraftToSend() {
        documents.addADocumentOfDocumentType("DRAFT");
        clickTheButton("Send to record response");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveIEDETCaseFromSendToCaseClosed() {
        documents.addADocumentOfDocumentType("Final response");
        complaintsDispatchAndSend.selectACaseOutcome();
        complaintsDispatchAndSend.enterADateOfResponse();
        selectSpecificRadioButton("Close the case");
        clickTheButton("Complete");
        System.out.println("Case moved from Send to Closed");
    }

    public void generateIEDETSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
            case "CASE REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB(infoValue);
                complaintsRegistrationAndDataInput.selectANationality();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickFinishButton();
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
                complaintsRegistrationAndDataInput.selectANationality();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(infoValue);
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickFinishButton();
                break;
            case "PSU REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
                complaintsRegistrationAndDataInput.selectANationality();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(infoValue);
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickFinishButton();
                dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
                moveIEDETCaseFromTriageToPSURegistration();
                dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
                complaintsRegistrationAndDataInput.enterASpecificPSUReference("123456789");
                clickTheButton("Submit");
                break;
            case "ALL":
                createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB("01/01/2001");
                complaintsRegistrationAndDataInput.selectANationality();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference("Test entry for HO Reference");
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickFinishButton();
                dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                complaintsTriageAndInvestigation.selectIEDETClaimCategory("Service");
                clickContinueButton();
                complaintsRegistrationAndDataInput.selectASpecificComplaintOrigin("Heathrow Airport (Cayley House)");
                complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
                complaintsRegistrationAndDataInput.enterAThirdPartyReference();
                complaintsTriageAndInvestigation.selectASpecificIEDETBusinessArea("Gatwick North Terminal");
                clickContinueButton();
                complaintsTriageAndInvestigation.selecIEDetentionComplianceTeam();
                clickFinishButton();
                dashboard.goToDashboard();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
