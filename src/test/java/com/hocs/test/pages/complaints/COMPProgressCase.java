package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.Search;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class COMPProgressCase extends BasePage {

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
                escalateACOMPCaseToCOMP2();
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

    public void escalateACOMPCaseToCOMP2() {
        try {
            attemptEscalateCOMPCaseToStage2();
        } catch (Exception a) {
            moveCaseOfTypeFromCurrentStageToTargetStage("COMP", "N/A", "COMPLAINT CLOSED");
            try {
                attemptEscalateCOMPCaseToStage2();
            } catch (Exception e) {
                Assert.fail("Escalation hypertext not visible on retry");
            }
        }
        waitABit(500);
    }

    public void attemptEscalateCOMPCaseToStage2() throws Exception {
        dashboard.selectSearchLinkFromMenuBar();
        search.enterCOMPSearchCriteria("Complainant Home Office Reference", getCurrentMonth() + "/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        if (search.checkVisibilityOfEscalationHypertext()) {
            WebElementFacade compCaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
            String compCaseRef = compCaseRefField.getText();
            setSessionVariable("compCaseReference").to(compCaseRef);
            System.out.print("Case reference of case being escalated: " + compCaseRef + "\n");
            search.clickEscalateCOMPCaseToCOMP2();
        } else {
            throw new Exception("Escalation hypertext not visible");
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
        RecordCaseData.resetDataRecords();
    }

    public void moveCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        registration.enterComplainantDetails();
        registration.selectComplaintType(complaintType);
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
        documents.addADraftDocumentAtDraftStage();
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
}
