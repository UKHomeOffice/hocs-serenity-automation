package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.Search;
import net.serenitybdd.core.pages.WebElementFacade;

public class BFProgressCase extends BasePage {

    CaseView caseView;

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Dashboard dashboard;

    Documents documents;

    Correspondents correspondents;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsSend complaintsSend;

    ComplaintsQA compQA;

    Search search;

    ComplaintsDraft complaintsDraft;

    String complaintType = "Service";

    public void moveCaseOfTypeFromCurrentStageToTargetStage(String caseType, String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            if (caseType.equals("BF2")) {
                escalateAStage1CaseToStage2();
            }
            createCase.createCSCaseOfType(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheBFStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(String caseType, String complaintType, String targetStage) {
        this.complaintType = complaintType;
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    public void escalateAStage1CaseToStage2() {
        if (!checkIfRandomStage1CaseEligibleForEscalationCanBeFound()) {
            getStage1CaseEligibleForEscalation();
        }
        escalateEligibleStage1CaseToStage2();
        setSessionVariable("caseType").to("BF2");
        waitABit(500);
    }

    private boolean checkIfRandomStage1CaseEligibleForEscalationCanBeFound() {
        dashboard.selectSearchLinkFromMenuBar();
        if (checkboxWithLabelIsCurrentlyVisible("Border Force Case")) {
            checkSpecificCheckbox("Border Force Case");
        }
        search.enterComplaintsSearchCriteria("Complainant Home Office Reference", getCurrentMonth() + "/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        return search.checkVisibilityOfEscalationHypertext();
    }

    private void getStage1CaseEligibleForEscalation() {
        createCase.createAndWithDrawACSCaseOfType("BF");
        dashboard.selectSearchLinkFromMenuBar();
        if (checkboxWithLabelIsCurrentlyVisible("Border Force Case")) {
            checkSpecificCheckbox("Border Force Case");
        }
        search.searchByCaseReference(getCurrentCaseReference());
        search.waitForResultsPage();
        if (search.getNumberOfSearchResults() == 0) {
            waitABit(5000);
            dashboard.selectSearchLinkFromMenuBar();
            if (checkboxWithLabelIsCurrentlyVisible("Border Force Case")) {
                checkSpecificCheckbox("Border Force Case");
            }
            search.searchByCaseReference(getCurrentCaseReference());
            search.waitForResultsPage();
        }
    }

    private void escalateEligibleStage1CaseToStage2() {
        WebElementFacade bfCaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
        String bfCaseRef = bfCaseRefField.getText();
        setSessionVariable("stage2CaseReference").to(bfCaseRef);
        System.out.print("Case reference of case being escalated: " + bfCaseRef + "\n");
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
            case "ESCALATED TO WFM":
                precedingStage = "TRIAGE";
                break;
            case "CCH":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "CASE CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheBFStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveBFCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
                        moveBFCaseFromTriageToDraft();
                        break;
                    case "ESCALATED TO WFM":
                        moveBFCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveBFCaseFromDraftToQA();
                break;
            case "QA":
                moveBCaseFromQAToSend();
                break;
            case "SEND":
                moveBFCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromTriageToCCH() {
        complaintsTriage.selectTransferComplaint();
        complaintsTriage.enterTransferReason();
        complaintsTriage.selectTransferToCCH();
    }

    public void moveBFCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        if (bfCase()) {
            registration.selectAComplaintType();
            clickTheButton("Continue");
        }
        registration.selectAChannel();
        registration.enterADescriptionOfTheComplaint();
        registration.enterAPreviousComplaintReference();
        registration.enterAThirdPartyReference();
        clickTheButton("Finish");
        System.out.println("Case moved from Case Registration to Case Triage");
    }

    public void moveBFCaseFromTriageToDraft() {
        complaintsTriage.selectAcceptCase();
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnBFTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Case Triage to Draft");
    }

    public void moveBFCaseFromTriageToEscalated() {
        complaintsTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Capture Reason");
        complaintsTriage.enterDetailsOnBFTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.escalateCaseToWFM();
        System.out.println("Case moved from Case Triage to Escalated to WFM");
    }

    public void moveBFCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("DRAFT");
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Draft to QA");
    }

    public void moveBCaseFromQAToSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from QA to Send draft response");
    }

    public void moveBFCaseFromSendToCaseClosed() {
        complaintsSend.selectBFCaseOutcomes();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send draft response to Case Closed");
    }

    public void generateBFSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("BF");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("BF");
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
                break;
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("BF");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("BF");
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