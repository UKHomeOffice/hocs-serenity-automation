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
        targetStage = getSimplifiedTargetStage(targetStage);
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            switch (caseType) {
                case "COMP":
                    createCase.createCSCaseOfType("COMP");
                    dashboard.goToDashboard();
                    break;
                case "COMP2":
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
                    createCase.createCOMP2Case();
                    dashboard.goToDashboard();
                    break;
                default:
                    pendingStep(caseType + " is not defined within " + getMethodName());
            }
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheCOMPStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getSimplifiedTargetStage(String targetStage) {
        String[] words = targetStage.split(" ");
        String simplifiedStage = words[words.length-1];

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

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "REGISTRATION":
            case "STAGE 2 REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "SERVICE TRIAGE":
                complaintType = "Service";
                precedingStage = "REGISTRATION";
                break;
            case "EX-GRATIA TRIAGE":
                complaintType = "Ex-Gratia";
                precedingStage = "REGISTRATION";
                break;
            case "MINOR MISCONDUCT TRIAGE":
                complaintType = "Minor Misconduct";
                precedingStage = "REGISTRATION";
                break;
            case "SERVICE DRAFT":
            case "SERVICE ESCALATED":
                precedingStage = "SERVICE TRIAGE";
                break;
            case "EX-GRATIA RESPONSE DRAFT":
            case "EX-GRATIA ESCALATE":
                precedingStage = "EX-GRATIA TRIAGE";
                break;
            case "MINOR MISCONDUCT RESPONSE DRAFT":
            case "MINOR MISCONDUCT ESCALATE":
                precedingStage = "MINOR MISCONDUCT TRIAGE";
                break;
            case "CCH":
                precedingStage = complaintType.toUpperCase() + " TRIAGE";
                break;
            case "SERVICE QA":
                precedingStage = "SERVICE DRAFT";
                break;
            case "EX-GRATIA QA":
                precedingStage = "EX-GRATIA RESPONSE DRAFT";
                break;
            case "MINOR MISCONDUCT QA":
                precedingStage = "MINOR MISCONDUCT RESPONSE DRAFT";
                break;
            case "SERVICE SEND":
                precedingStage = "SERVICE QA";
                break;
            case "EX-GRATIA SEND":
                precedingStage = "EX-GRATIA QA";
                break;
            case "MINOR MISCONDUCT SEND":
                precedingStage = "MINOR MISCONDUCT QA";
                break;
            case "COMPLAINT CLOSED":
                precedingStage = complaintType.toUpperCase() + " SEND";
                break;
            case "STAGE 2 SERVICE TRIAGE":
                complaintType = "Service";
                precedingStage = "STAGE 2 REGISTRATION";
                break;
            case "STAGE 2 EX-GRATIA TRIAGE":
                complaintType = "Ex-Gratia";
                precedingStage = "STAGE 2 REGISTRATION";
                break;
            case "STAGE 2 MM TRIAGE":
                complaintType = "Minor Misconduct";
                precedingStage = "STAGE 2 REGISTRATION";
                break;
            case "STAGE 2 SERVICE DRAFT":
            case "STAGE 2 SERVICE ESCALATED":
                precedingStage = "STAGE 2 SERVICE TRIAGE";
                break;
            case "STAGE 2 EX-GRATIA RESPONSE DRAFT":
                precedingStage = "STAGE 2 EX-GRATIA TRIAGE";
                break;
            case "STAGE 2 MM RESPONSE DRAFT":
                precedingStage = "STAGE 2 MM TRIAGE";
                break;
            case "STAGE 2 SERVICE QA":
                precedingStage = "STAGE 2 SERVICE DRAFT";
                break;
            case "STAGE 2 EX-GRATIA QA":
                precedingStage = "STAGE 2 EX-GRATIA RESPONSE DRAFT";
                break;
            case "STAGE 2 MM QA":
                precedingStage = "STAGE 2 MM RESPONSE DRAFT";
                break;
            case "STAGE 2 SERVICE SEND":
                precedingStage = "STAGE 2 SERVICE QA";
                break;
            case "STAGE 2 EX-GRATIA SEND":
                precedingStage = "STAGE 2 EX-GRATIA QA";
                break;
            case "STAGE 2 MM SEND":
                precedingStage = "STAGE 2 MM QA";
                break;
            case "STAGE 2 COMPLAINT CLOSED":
                switch (complaintType) {
                    case "SERVICE":
                    case "EX-GRATIA":
                        precedingStage = "STAGE 2 " + complaintType + " SEND";
                        break;
                    case "MINOR MISCONDUCT":
                        precedingStage = "STAGE 2 MM SEND";
                        break;
                }
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
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
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "DRAFT":
            case "ESCALATED":
                precedingStage = "TRIAGE";
                break;
            case "DRAFT":
            case "ESCALATE":
                precedingStage = "TRIAGE";
                break;
            case "DRAFT":
            case "ESCALATE":
                precedingStage = "TRIAGE";
                break;
            case "CCH":
                precedingStage = complaintType.toUpperCase() + " TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "CLOSED":
                precedingStage = "SEND";
                break;
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "STAGE 2 EX-GRATIA TRIAGE":
                complaintType = "Ex-Gratia";
                precedingStage = "STAGE 2 REGISTRATION";
                break;
            case "STAGE 2 MM TRIAGE":
                complaintType = "Minor Misconduct";
                precedingStage = "STAGE 2 REGISTRATION";
                break;
            case "STAGE 2 SERVICE DRAFT":
            case "STAGE 2 SERVICE ESCALATED":
                precedingStage = "STAGE 2 SERVICE TRIAGE";
                break;
            case "STAGE 2 EX-GRATIA RESPONSE DRAFT":
                precedingStage = "STAGE 2 EX-GRATIA TRIAGE";
                break;
            case "STAGE 2 MM RESPONSE DRAFT":
                precedingStage = "STAGE 2 MM TRIAGE";
                break;
            case "STAGE 2 SERVICE QA":
                precedingStage = "STAGE 2 SERVICE DRAFT";
                break;
            case "STAGE 2 EX-GRATIA QA":
                precedingStage = "STAGE 2 EX-GRATIA RESPONSE DRAFT";
                break;
            case "STAGE 2 MM QA":
                precedingStage = "STAGE 2 MM RESPONSE DRAFT";
                break;
            case "STAGE 2 SERVICE SEND":
                precedingStage = "STAGE 2 SERVICE QA";
                break;
            case "STAGE 2 EX-GRATIA SEND":
                precedingStage = "STAGE 2 EX-GRATIA QA";
                break;
            case "STAGE 2 MM SEND":
                precedingStage = "STAGE 2 MM QA";
                break;
            case "STAGE 2 COMPLAINT CLOSED":
                switch (complaintType) {
                    case "SERVICE":
                    case "EX-GRATIA":
                        precedingStage = "STAGE 2 " + complaintType + " SEND";
                        break;
                    case "MINOR MISCONDUCT":
                        precedingStage = "STAGE 2 MM SEND";
                        break;
                }
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

    public void completeTheCOMPStageSoThatCaseMovesToTargetStage(String stage, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stage.toUpperCase()) {
            case "REGISTRATION":
            case "STAGE 2 REGISTRATION":
                moveCaseFromRegistrationToTriage();
                break;
            case "SERVICE TRIAGE":
            case "STAGE 2 SERVICE TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "SERVICE DRAFT":
                    case "STAGE 2 SERVICE DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "SERVICE ESCALATED":
                    case "STAGE 2 SERVICE ESCALATED":
                        moveCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "EX-GRATIA TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "EX-GRATIA RESPONSE DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "EX-GRATIA ESCALATE":
                        moveCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "MINOR MISCONDUCT TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "MINOR MISCONDUCT RESPONSE DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "MINOR MISCONDUCT ESCALATE":
                        moveCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "SERVICE DRAFT":
            case "EX-GRATIA RESPONSE DRAFT":
            case "MINOR MISCONDUCT RESPONSE DRAFT":
                moveCaseFromDraftToQA();
                break;
            case "SERVICE QA":
            case "EX-GRATIA QA":
            case "MINOR MISCONDUCT QA":
                moveCaseFromQAToSend();
                break;
            case "SERVICE SEND":
            case "EX-GRATIA SEND":
            case "MINOR MISCONDUCT SEND":
                moveCaseFromSendToComplaintClosed();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        RecordCaseData.resetDataRecords();
    }

    public void attemptEscalateCOMPCaseToStage2() throws Exception {
        dashboard.selectSearchLinkFromMenuBar();
        search.enterCOMPSearchCriteria("Complainant Home Office Reference", getCurrentMonth() +"/" + getCurrentYear());
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
        System.out.println(complaintType + " complaint moved from Registration to Triage");
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
        System.out.println(complaintType + " complaint moved from Triage to Draft/Response Draft");
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
        System.out.println(complaintType + " complaint moved from Triage to Escalate/Escalated");
    }

    public void moveCaseFromTriageToCCH() {
        complaintsTriage.selectTransferComplaint();
        complaintsTriage.enterTransferReason();
        complaintsTriage.selectTransferToCCH();
        System.out.println(complaintType + " complaint moved from Triage to CCH");
    }

    public void moveCaseFromDraftToQA() {
        documents.addADraftDocumentAtDraftStage();
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println(complaintType + " complaint moved from Draft/Response Draft to QA");
    }


    public void moveCaseFromQAToSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println(complaintType + " complaint moved from QA to Send");
    }

    public void moveCaseFromSendToComplaintClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println(complaintType + " complaint moved from Send to Case Closed");
    }
}
