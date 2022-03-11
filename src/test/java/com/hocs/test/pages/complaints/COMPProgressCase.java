package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.Search;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class COMPProgressCase extends BasePage {

    CaseView caseView;

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Correspondents correspondents;

    Documents documents;

    Dashboard dashboard;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsDraft complaintsDraft;

    ComplaintsQA compQA;

    ComplaintsSend complaintsSend;

    Search search;

    String complaintType = "Service";

    public void moveCaseOfTypeFromCurrentStageToTargetStage(String caseType, String currentStage, String targetStage) {
        setComplaintTypeFromStageName(targetStage);
        targetStage = getSimplifiedStageName(targetStage);
        currentStage = getSimplifiedStageName(currentStage);
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            if (caseType.equals("COMP2")) {
                escalateAStage1CaseToStage2();
            }
            createCase.createCSCaseOfType(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheCOMPStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getSimplifiedStageName(String targetStage) {
        String[] words = targetStage.split(" ");
        String simplifiedStage = words[words.length - 1];
        if (simplifiedStage.equalsIgnoreCase("Escalate")) {
            simplifiedStage = "Escalated";
        }
        return simplifiedStage;
    }

    private void setComplaintTypeFromStageName(String targetStage) {
        if (containsIgnoreCase(targetStage, "Service")) {
            complaintType = "Service";
        } else if (containsIgnoreCase(targetStage, "Ex-Gratia")) {
            complaintType = "Ex-Gratia";
        } else if (containsIgnoreCase(targetStage, "Minor Misconduct") || containsIgnoreCase(targetStage, "MM")) {
            complaintType = "Minor Misconduct";
        }
    }

    public void escalateAStage1CaseToStage2() {
        if (!checkIfRandomStage1CaseEligibleForEscalationCanBeFound()) {
            getStage1CaseEligibleForEscalation();
        }
        escalateEligibleStage1CaseToStage2();
        setSessionVariable("caseType").to("COMP2");
        waitABit(500);
    }

    private boolean checkIfRandomStage1CaseEligibleForEscalationCanBeFound() {
        dashboard.selectSearchLinkFromMenuBar();
        checkSpecificCheckbox("Complaint Case");
        search.enterComplaintsSearchCriteria("Complainant Home Office Reference", getCurrentMonth() + "/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        return search.checkVisibilityOfEscalationHypertext();
    }

    private void getStage1CaseEligibleForEscalation() {
        createCase.createAndWithDrawACSCaseOfType("COMP");
        dashboard.selectSearchLinkFromMenuBar();
        checkSpecificCheckbox("Complaint Case");
        search.searchByCaseReference(getCurrentCaseReference());
        search.waitForResultsPage();
    }

    private void escalateEligibleStage1CaseToStage2() {
        WebElementFacade compCaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
        String compCaseRef = compCaseRefField.getText();
        setSessionVariable("stage2CaseReference").to(compCaseRef);
        System.out.print("Case reference of case being escalated: " + compCaseRef + "\n");
        search.clickEscalateComplaintsCaseToStage2();
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
            case "ESCALATED":
            case "CCH":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(String caseType, String complaintType, String targetStage) {
        this.complaintType = complaintType;
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    public void completeTheCOMPStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "ESCALATED":
                        moveCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveCaseFromDraftToQA();
                break;
            case "QA":
                moveCaseFromQAToSend();
                break;
            case "SEND":
                moveCaseFromSendToClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        registration.enterComplainantDetails();
        registration.selectASpecificComplaintType(complaintType);
        registration.enterComplaintDetails();
        if (complaintType.equalsIgnoreCase("SERVICE")) {
            clickTheButton("Continue");
            registration.openTheServiceComplaintCategoryAccordion();
            waitABit(1000);
            registration.selectAVisibleClaimCategory();
            registration.selectAnOwningCSU();
        }
        clickTheButton("Finish");
    }

    public void moveCaseFromTriageToDraft() {
        complaintsTriage.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriage.enterDateOfAcceptance();
        }
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        complaintsTriage.enterDetailsOnComplaintCategoryPage();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Capture Reason");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
    }

    public void moveCaseFromTriageToEscalated() {
        complaintsTriage.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriage.enterDateOfAcceptance();
        }
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        complaintsTriage.enterDetailsOnComplaintCategoryPage();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Capture Reason");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.escalateCaseToWFM();
    }

    public void moveCaseFromTriageToCCH() {
        complaintsTriage.selectTransferComplaint();
        complaintsTriage.enterTransferReason();
        complaintsTriage.selectTransferToCCH();
    }

    public void moveCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("DRAFT");
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
    }


    public void moveCaseFromQAToSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
    }

    public void moveCaseFromSendToClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
    }

    public void generateCOMPSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
                if (infoValue.equals("COMP2")) {
                    escalateAStage1CaseToStage2();
                }
                createCase.createCSCaseOfType(infoValue);
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("COMP");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("COMP");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                registration.enterComplainantDOB(infoValue);
                registration.selectAGender();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference("Test entry for Home Office Reference");
                registration.enterAPortReference();
                safeClickOn(continueButton);
                registration.selectASpecificComplaintType("Service");
                registration.selectAChannel();
                registration.selectASeverity();
                safeClickOn(continueButton);
                registration.openTheServiceComplaintCategoryAccordion();
                registration.selectAVisibleClaimCategory();
                registration.selectAnOwningCSU();
                safeClickOn(finishButton);
                break;
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("COMP");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("COMP");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                registration.enterComplainantDOB("01/01/2001");
                registration.selectAGender();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference(infoValue);
                registration.enterAPortReference();
                safeClickOn(continueButton);
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
