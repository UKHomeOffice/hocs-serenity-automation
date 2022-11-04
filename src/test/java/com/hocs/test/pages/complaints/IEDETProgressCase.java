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
            case "REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "DRAFT":
            case "PSU REGISTRATION":
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

    public void completeTheIEDETStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveIEDETCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
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
        System.out.println("Case moved from Registration to Triage");
    }

    //Failing consistently without wait after line 116, assumed to be env instability.
    public void moveIEDETCaseFromTriageToDraft() {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
        waitABit(10000);
        clickContinueButton();
        openOrCloseAccordionSection("Service");
        complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
        clickContinueButton();
        complaintsRegistrationAndDataInput.selectComplaintOrigin();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        clickContinueButton();
        complaintsTriageAndInvestigation.selecIEDetentionComplianceTeam();
        complaintsTriageAndInvestigation.selectIEDETBusinessArea();
        clickFinishButton();
        System.out.println("Case moved from Triage to Draft");
    }

    private void moveIEDETCaseFromTriageToPSURegistration() {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Serious misconduct");
        complaintsRegistrationAndDataInput.openTheSeriousComplaintCategoryAccordion();
        complaintsTriageAndInvestigation.selectAVisibleClaimCategory();
        clickContinueButton();
        complaintsRegistrationAndDataInput.selectComplaintOrigin();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        clickTheButton("Finish and escalate to PSU");
    }

    public void moveIEDETCaseFromDraftToSend() {
        documents.addADocumentOfDocumentType("DRAFT");
        clickTheButton("Proceed to recording outcome");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveIEDETCaseFromSendToCaseClosed() {
        documents.addADocumentOfDocumentType("Final response");
        complaintsDispatchAndSend.selectACaseOutcome();
        complaintsDispatchAndSend.enterADateOfResponse();
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
                dashboard.goToDashboard();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
