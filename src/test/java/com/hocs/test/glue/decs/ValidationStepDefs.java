package com.hocs.test.glue.decs;

import com.hocs.test.pages.complaints.ComplaintsDispatchAndSend;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.complaints.CCH;
import com.hocs.test.pages.complaints.ComplaintsRegistrationAndDataInput;
import com.hocs.test.pages.complaints.ComplaintsDraft;
import com.hocs.test.pages.complaints.ComplaintsQA;
import com.hocs.test.pages.complaints.ComplaintsTriageAndInvestigation;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.mpam.Campaign;
import com.hocs.test.pages.mpam.CloseCaseTelephone;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.DispatchStages;
import com.hocs.test.pages.mpam.Draft;
import com.hocs.test.pages.mpam.MTSDataInput;
import com.hocs.test.pages.mpam.QA;
import com.hocs.test.pages.mpam.Triage;
import config.CaseType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.time.Duration;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class ValidationStepDefs extends BasePage {

    Documents documents;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff ministerialSignOff;

    Dispatch dispatch;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    MTSDataInput mtsDataInput;

    CloseCaseTelephone closeCaseTelephone;

    Campaign campaign;

    Correspondents correspondents;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation ComplaintsTriageAndInvestigation;

    ComplaintsDraft ComplaintsDraft;

    ComplaintsQA ComplaintsQA;

    CCH cch;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    @And("I trigger the {string} error message at (the ){string}( stage)")
    public void iTriggerTheErrorMessageAtTheStage(String errorMessage, String stage) {
        CaseType caseType = getCurrentCaseType();
        switch (caseType) {
            case MIN:
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                break;
                            case "COPY TO NUMBER 10 REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                break;
                            case "HOME SECRETARY INTEREST REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                clickContinueButton();
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            case "CORRESPONDENT TYPE MUST BE PROVIDED":
                                dataInput.fillAllMandatoryCorrespondenceFields();
                                dataInput.clickContinueButton();
                                correspondents.selectToAddACorrespondent();
                                clickContinueButton();
                                break;
                            case "MEMBER IS REQUIRED":
                                dataInput.fillAllMandatoryCorrespondenceFields();
                                dataInput.clickContinueButton();
                                correspondents.selectToAddACorrespondent();
                                correspondents.selectCorrespondentIsMP();
                                clickAddButton();
                                break;
                            case "CORRESPONDENT MUST HAVE TYPE":
                                dataInput.fillAllMandatoryCorrespondenceFields();
                                dataInput.clickContinueButton();
                                correspondents.selectToAddACorrespondent();
                                correspondents.selectCorrespondentIsNotMP();
                                correspondents.enterCorrespondentFullName("Han Solo");
                                clickAddButton();
                                break;
                            case "CORRESPONDENT NAME REQUIRED":
                                dataInput.fillAllMandatoryCorrespondenceFields();
                                dataInput.clickContinueButton();
                                correspondents.selectToAddACorrespondent();
                                correspondents.selectCorrespondentIsNotMP();
                                correspondents.selectASpecificCorrespondentType("Applicant");
                                clickAddButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                markup.selectPolicyResponseRadioButton();
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                markup.selectNoResponseNeededRadioButton();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                clickContinueButton();
                                markup.enterAOGDReason();
                                clickFinishButton();
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                clickContinueButton();
                                markup.enterAOGDDestination();
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            clickFinishButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            clickFinishButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                safeClickOn(initialDraft.offlineQaYesRadioButton);
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                qaResponse.selectReturnCaseToDraftingTeamRadioButton();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                privateOfficeApproval.selectIfApproveResponse("No");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            case "OVERRIDE PRIVATE OFFICE TEAM REQUIRED":
                                privateOfficeApproval.selectToChangeMinister();
                                clickContinueButton();
                                privateOfficeApproval.enterAReasonForChangingPOTeam();
                                clickFinishButton();
                                break;
                            case "REASON FOR CHANGE MINISTER REQUIRED":
                                privateOfficeApproval.selectToChangeMinister();
                                clickContinueButton();
                                privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown("Home Secretary");
                                clickFinishButton();
                                break;
                            case "REASON FOR TOPIC CHANGE REQUIRED":
                                privateOfficeApproval.selectToChangeTopic();
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MINISTERIAL SIGN OFF":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION TO DRAFT NOTE REQUIRED":
                                ministerialSignOff.selectToApproveResponse("No");
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "REJECTION TO PRIVATE OFFICE NOTE REQUIRED":
                                ministerialSignOff.selectNotApplicableToApproveResponse();
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                dispatch.selectAbleToDispatch("No");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case TRO:
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                break;
                            case "COPY TO NUMBER 10 REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                clickContinueButton();
                                break;
                            case "HOME SECRETARY INTEREST REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                clickContinueButton();
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                dataInput.selectASpecificHomeSecInterestOption("Yes");
                                dataInput.selectASpecificCopyToNoTenOption("Yes");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                markup.selectPolicyResponseRadioButton();
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                markup.selectNoResponseNeededRadioButton();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                clickContinueButton();
                                markup.enterAOGDReason();
                                clickFinishButton();
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                clickContinueButton();
                                markup.enterAOGDDestination();
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            clickFinishButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            clickFinishButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                safeClickOn(initialDraft.offlineQaYesRadioButton);
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                qaResponse.selectReturnCaseToDraftingTeamRadioButton();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                dispatch.selectAbleToDispatch("No");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case DTEN:
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "DRAFTING DEADLINE REQUIRED":
                                dataInput.enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(5));
                                clickContinueButton();
                                break;
                            case "DISPATCH DEADLINE REQUIRED":
                                dataInput.enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(5));
                                clickContinueButton();
                                break;
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                dataInput.enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(5));
                                dataInput.enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(5));
                                clickContinueButton();
                                dataInput.selectACorrespondenceReceivedChannel();
                                clickContinueButton();
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                dataInput.enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(5));
                                dataInput.enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(5));
                                clickContinueButton();
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                clickContinueButton();
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                dataInput.enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(5));
                                dataInput.enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(5));
                                clickContinueButton();
                                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                                dataInput.selectACorrespondenceReceivedChannel();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(stage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                markup.selectPolicyResponseRadioButton();
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                markup.selectNoResponseNeededRadioButton();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                clickContinueButton();
                                markup.enterAOGDReason();
                                clickFinishButton();
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                clickContinueButton();
                                markup.enterAOGDDestination();
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            clickFinishButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            clickFinishButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                clickContinueButton();
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                clickContinueButton();
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                safeClickOn(initialDraft.offlineQaYesRadioButton);
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                qaResponse.selectReturnCaseToDraftingTeamRadioButton();
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                privateOfficeApproval.selectIfApproveResponse("No");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION NOTE REQUIRED":
                                dispatch.selectAbleToDispatch("No");
                                clickContinueButton();
                                
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case MPAM:
                switch (stage.toUpperCase()) {
                    case "CREATION":
                        switch (errorMessage.toUpperCase()) {
                            case "BUSINESS AREA REQUIRED":
                                creation.selectASpecificRefType("Ministerial");
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                creation.selectASpecificUrgency("Standard");
                                creation.selectASpecificInboundChannel("Email");
                                clickContinueButton();
                                break;
                            case "IS MINISTERIAL RESPONSE REQUIRED REQUIRED":
                                creation.selectASpecificUrgency("Standard");
                                creation.selectASpecificInboundChannel("Email");
                                clickContinueButton();
                                break;
                            case "MINISTERIAL SIGN OFF TEAM REQUIRED":
                                creation.selectASpecificBusinessArea("UKVI");
                                creation.selectASpecificRefType("Ministerial");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                creation.selectASpecificUrgency("Standard");
                                creation.selectASpecificInboundChannel("Email");
                                clickContinueButton();
                                break;
                            case "ADDRESSEE REQUIRED":
                                creation.selectASpecificBusinessArea("UKVI");
                                creation.selectASpecificRefType("Ministerial");
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.selectASpecificUrgency("Standard");
                                creation.selectASpecificInboundChannel("Email");
                                clickContinueButton();
                                break;
                            case "URGENCY REQUIRED":
                                creation.selectASpecificBusinessArea("UKVI");
                                creation.selectASpecificRefType("Ministerial");
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                creation.selectASpecificInboundChannel("Email");
                                clickContinueButton();
                                break;
                            case "CHANNEL RECEIVED REQUIRED":
                                creation.selectASpecificBusinessArea("UKVI");
                                creation.selectASpecificRefType("Ministerial");
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                creation.selectASpecificUrgency("Standard");
                                clickContinueButton();
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                creation.selectASpecificBusinessArea("UKVI");
                                creation.selectASpecificRefType("Ministerial");
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                creation.selectASpecificUrgency("Standard");
                                creation.selectASpecificInboundChannel("Email");
                                clickContinueButton();
                                
                                clickTheButton("Move to Triage");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRIAGE":
                        switch (errorMessage.toUpperCase()) {
                            case "ENQUIRY SUBJECT REQUIRED":
                            case "ENQUIRY REASON REQUIRED":
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(triage.readyToDraftRadioButton);
                                clickConfirmButton();
                                break;
                            case "BUSINESS UNIT REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                safeClickOn(triage.readyToDraftRadioButton);
                                clickConfirmButton();
                                break;
                            case "ACTIONS REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                clickConfirmButton();
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(triage.escalateToWorkflowManagerRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                clickTheButton("Close Case");
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                clickTheButton("Close Case");
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                
                                clickConfirmButton();
                                break;
                            case "REJECTION REASON REQUIRED":
                                safeClickOn(draft.returnToTriageRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(draft.escalateToWorkflowManagerRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                clickTheButton("Close Case");
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                clickTheButton("Close Case");
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                
                                clickConfirmButton();
                                break;
                            case "REJECTION TO TRIAGE REASON REQUIRED":
                                safeClickOn(qa.rejectQAToTriageRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "REJECTION TO DRAFT REASON REQUIRED":
                                safeClickOn(qa.rejectQAToDraftRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(qa.escalateToWorkflowManagerRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                clickTheButton("Close Case");
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                clickTheButton("Close Case");
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE CHANNEL REQUIRED":
                                safeClickOn(dispatchStages.dispatchedRadioButtonAtPrivateOffice);
                                clickConfirmButton();
                                break;
                            case "ACTIONS REQUIRED":
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                clickConfirmButton();
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "DISPATCHED DATE REQUIRED":
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(dispatchStages.dispatchedRadioButtonAtPrivateOffice);
                                clickConfirmButton();
                                
                                clickTheButton("Confirm and close case");
                                break;
                            case "FOLLOW-UP DUE DATE REQUIRED":
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                clickConfirmButton();
                                typeIntoDateFields(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                typeInto(dispatchStages.followUpDetailsTextArea, "Test");
                                clickConfirmButton();
                                break;
                            case "DETAILS OF FOLLOW-UP REQUIRED":
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                clickConfirmButton();
                                typeIntoDateFields(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                dispatchStages.followUpDateInput(getDatePlusMinusNDaysAgo(0));
                                clickConfirmButton();
                                break;
                            case "REJECTION REASON REQUIRED":
                                safeClickOn(dispatchStages.draftRejectedRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "AWAITING DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCHED DATE REQUIRED":
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(dispatchStages.dispatchedCloseCaseRadioButton);
                                clickConfirmButton();
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                typeIntoDateFields(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dispatchStages.dispatchedCloseCaseRadioButton);
                                clickConfirmButton();
                                break;
                            case "ACTIONS REQUIRED":
                                typeIntoDateFields(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                clickConfirmButton();
                                break;
                            case "REJECTION REASON REQUIRED":
                                safeClickOn(dispatchStages.returnToDraftButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                clickTheButton("Close Case");
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                clickConfirmButton();
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                clickTheButton("Close Case");
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                clickConfirmButton();
                                
                                clickConfirmButton();
                                break;
                            case "FOLLOW-UP DUE DATE REQUIRED":
                                typeIntoDateFields(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                clickConfirmButton();
                                typeInto(dispatchStages.followUpDetailsTextArea, "Test");
                                clickConfirmButton();
                                break;
                            case "DETAILS OF FOLLOW-UP REQUIRED":
                                typeIntoDateFields(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dispatchStages.responseChannelEmailRadioButton);
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                clickConfirmButton();
                                dispatchStages.followUpDateInput(getDatePlusMinusNDaysAgo(0));
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case MTS:
                if (stage.equalsIgnoreCase("DATA INPUT")) {
                    switch (errorMessage.toUpperCase()) {
                        case "PRIMARY CORRESPONDENT REQUIRED":
                            
                            clickContinueButton();
                            break;
                        case "BUSINESS AREA REQUIRED":
                        case "BUSINESS UNIT REQUIRED":
                            correspondents.addAMemberCorrespondent();
                            clickContinueButton();
                            mtsDataInput.selectAnUrgency();
                            mtsDataInput.selectAChannelReceived();
                            mtsDataInput.selectAnEnquirySubject();
                            mtsDataInput.selectAnEnquiryReason();
                            mtsDataInput.enterASupportNote();
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "URGENCY REQUIRED":
                            correspondents.addAMemberCorrespondent();
                            clickContinueButton();
                            mtsDataInput.selectABusinessArea();
                            mtsDataInput.selectABusinessUnit();
                            mtsDataInput.selectAChannelReceived();
                            mtsDataInput.selectAnEnquirySubject();
                            mtsDataInput.selectAnEnquiryReason();
                            mtsDataInput.enterASupportNote();
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "CHANNEL RECEIVED REQUIRED":
                            correspondents.addAMemberCorrespondent();
                            clickContinueButton();
                            mtsDataInput.selectABusinessArea();
                            mtsDataInput.selectABusinessUnit();
                            mtsDataInput.selectAnUrgency();
                            mtsDataInput.selectAnEnquirySubject();
                            mtsDataInput.selectAnEnquiryReason();
                            mtsDataInput.enterASupportNote();
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "ENQUIRY SUBJECT REQUIRED":
                        case "ENQUIRY REASON REQUIRED":
                            correspondents.addAMemberCorrespondent();
                            clickContinueButton();
                            mtsDataInput.selectABusinessArea();
                            mtsDataInput.selectABusinessUnit();
                            mtsDataInput.selectAnUrgency();
                            mtsDataInput.selectAChannelReceived();
                            mtsDataInput.enterASupportNote();
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "SUPPORT CASE NOTE REQUIRED":
                            correspondents.addAMemberCorrespondent();
                            clickContinueButton();
                            mtsDataInput.selectABusinessArea();
                            mtsDataInput.selectABusinessUnit();
                            mtsDataInput.selectAnUrgency();
                            mtsDataInput.selectAChannelReceived();
                            mtsDataInput.selectAnEnquirySubject();
                            mtsDataInput.selectAnEnquiryReason();
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "YOUR BUSINESS AREA REQUIRED":
                            correspondents.addAMemberCorrespondent();
                            clickContinueButton();
                            mtsDataInput.selectABusinessArea();
                            mtsDataInput.selectABusinessUnit();
                            mtsDataInput.selectAnUrgency();
                            mtsDataInput.selectAChannelReceived();
                            mtsDataInput.selectAnEnquirySubject();
                            mtsDataInput.selectAnEnquiryReason();
                            mtsDataInput.enterASupportNote();
                            clickTheButton("Complete and Close Case");
                            break;
                        default:
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                    }
                } else {
                    pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case COMP:
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        switch (errorMessage.toUpperCase()) {
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "COMPLAINT TYPE REQUIRED":
                                correspondents.addANonMemberCorrespondentOfType("Complainant");
                                correspondents.confirmPrimaryCorrespondent();
                                
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "CHANNEL REQUIRED":
                                correspondents.addANonMemberCorrespondentOfType("Complainant");
                                correspondents.confirmPrimaryCorrespondent();
                                
                                clickContinueButton();
                                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                                complaintsRegistrationAndDataInput.selectASeverity();
                                clickContinueButton();
                                break;
                            case "SEVERITY REQUIRED":
                                correspondents.addANonMemberCorrespondentOfType("Complainant");
                                correspondents.confirmPrimaryCorrespondent();
                                
                                clickContinueButton();
                                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                                complaintsRegistrationAndDataInput.selectAComplaintChannel();
                                clickContinueButton();
                                break;
                            case "OWNING CSU REQUIRED":
                                correspondents.addANonMemberCorrespondentOfType("Complainant");
                                correspondents.confirmPrimaryCorrespondent();
                                
                                clickContinueButton();
                                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                                complaintsRegistrationAndDataInput.selectAComplaintChannel();
                                complaintsRegistrationAndDataInput.selectASeverity();
                                clickContinueButton();
                                complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
                                complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
                                clickFinishButton();
                                break;
                            case "COMPLAINT TYPE OPTION REQUIRED":
                                correspondents.addANonMemberCorrespondentOfType("Complainant");
                                correspondents.confirmPrimaryCorrespondent();
                                
                                clickContinueButton();
                                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                                complaintsRegistrationAndDataInput.selectAComplaintChannel();
                                complaintsRegistrationAndDataInput.selectASeverity();
                                clickContinueButton();
                                complaintsRegistrationAndDataInput.selectAnOwningCSU();
                                clickFinishButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE TRIAGE":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN YOUR TEAM RESPOND TO COMPLAINT REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.transferTheComplaintRadioButton);
                                clickContinueButton();
                                safeClickOn(ComplaintsTriageAndInvestigation.transferToCCHRadioButton);
                                clickContinueButton();
                                break;
                            case "TRANSFER TO REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.transferTheComplaintRadioButton);
                                clickContinueButton();
                                typeInto(ComplaintsTriageAndInvestigation.transferReasonTextArea, "Test");
                                clickContinueButton();
                                break;
                            case "BUSINESS AREA REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(ComplaintsTriageAndInvestigation.loaRequiredYesRadioButton);
                                clickContinueButton();
                                break;
                            case "ENQUIRY REASON REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.businessAreaDropdown.selectByIndex(1);
                                safeClickOn(ComplaintsTriageAndInvestigation.loaRequiredYesRadioButton);
                                clickContinueButton();
                                break;
                            case "IS LETTER OF AUTHORITY REQUIRED RESPONSE REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.businessAreaDropdown.selectByIndex(1);
                                ComplaintsTriageAndInvestigation.enquiryReasonDropdown.selectByIndex(1);
                                clickContinueButton();
                                break;
                            case "ACTION REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.businessAreaDropdown.selectByIndex(1);
                                ComplaintsTriageAndInvestigation.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(ComplaintsTriageAndInvestigation.loaRequiredYesRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.businessAreaDropdown.selectByIndex(1);
                                ComplaintsTriageAndInvestigation.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(ComplaintsTriageAndInvestigation.loaRequiredYesRadioButton);
                                clickContinueButton();
                                safeClickOn(ComplaintsTriageAndInvestigation.escalateToWFMRadioButton);
                                clickContinueButton();
                                
                                clickTheButton("Escalate case");
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.businessAreaDropdown.selectByIndex(1);
                                ComplaintsTriageAndInvestigation.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(ComplaintsTriageAndInvestigation.loaRequiredYesRadioButton);
                                clickContinueButton();
                                safeClickOn(ComplaintsTriageAndInvestigation.noResponseCloseCaseRadioButton);
                                clickContinueButton();
                                
                                clickTheButton("Complete case");
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                safeClickOn(ComplaintsTriageAndInvestigation.acceptTheComplaintRadioButton);
                                clickContinueButton();
                                
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.businessAreaDropdown.selectByIndex(1);
                                ComplaintsTriageAndInvestigation.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(ComplaintsTriageAndInvestigation.loaRequiredYesRadioButton);
                                clickContinueButton();
                                safeClickOn(ComplaintsTriageAndInvestigation.noResponseCloseCaseRadioButton);
                                clickContinueButton();
                                ComplaintsTriageAndInvestigation.enterCompletionReason();
                                clickTheButton("Complete case");
                                
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(ComplaintsDraft.sendCaseToQARadioButton);
                                clickContinueButton();
                                break;
                            case "ACTION REQUIRED":
                                documents.addADocumentOfDocumentType("DRAFT");
                                clickContinueButton();
                                break;
                            case "ESCALATION REASON":
                                documents.addADocumentOfDocumentType("DRAFT");
                                safeClickOn(ComplaintsDraft.escalateCaseToWFMRadioButton);
                                clickContinueButton();
                                
                                clickTheButton("Escalate case");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE ESCALATED":
                        if (errorMessage.equalsIgnoreCase("ACTION REQUIRED")) {
                            
                            clickConfirmButton();
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "CCH":
                        switch (errorMessage.toUpperCase()) {
                            case "TRANSFER TO REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                safeClickOn(cch.completeTheCaseClosePermanentlyRadioButton);
                                clickContinueButton();
                                
                                clickTheButton("Complete case");
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                safeClickOn(cch.completeTheCaseClosePermanentlyRadioButton);
                                clickContinueButton();
                                cch.submitReasonForCaseCompletion();
                                clickConfirmButton();
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE QA":
                        switch (errorMessage.toUpperCase()) {
                            case "QA RESULT REQUIRED":
                                
                                clickContinueButton();
                                break;
                            case "REJECTION REASON REQUIRED":
                                safeClickOn(ComplaintsQA.returnResponseToDraftRadioButton);
                                clickContinueButton();
                                
                                clickTheButton("Reject");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE SEND":
                        switch (errorMessage.toUpperCase()) {
                            case "CASE OUTCOME REQUIRED":
                                
                                complaintsDispatchAndSend.selectAResponseChannel();
                                complaintsDispatchAndSend.enterADateOfResponse();
                                clickTheButton("Complete");
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                
                                complaintsDispatchAndSend.selectACaseOutcome();
                                complaintsDispatchAndSend.enterADateOfResponse();
                                clickTheButton("Complete");
                                break;
                            case "DATE OF RESPONSE REQUIRED":
                                
                                complaintsDispatchAndSend.selectACaseOutcome();
                                complaintsDispatchAndSend.selectAResponseChannel();
                                clickTheButton("Complete");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} error message is displayed at (the ){string}( stage)")
    public void theErrorMessageIsDisplayedAtTheStage(String errorMessage, String stage) {
        String errorText = null;
        CaseType caseType = getCurrentCaseType();
        switch (caseType) {
            case MIN:
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                errorText = "When was the correspondence sent?";
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                errorText = "How was the correspondence received?";
                                break;
                            case "COPY TO NUMBER 10 REQUIRED":
                                errorText = "Should the response be copied to Number 10?";
                                break;
                            case "HOME SECRETARY INTEREST REQUIRED":
                                errorText = "Select yes if the Home Secretary has an interest in this case";
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                errorText = "Which is the primary correspondent?";
                                break;
                            case "CORRESPONDENT TYPE MUST BE PROVIDED":
                                errorText = "The correspondent type must be provided";
                                break;
                            case "MEMBER IS REQUIRED":
                                errorText = "Member is required";
                                break;
                            case "CORRESPONDENT MUST HAVE TYPE":
                                errorText = "The correspondent must have a type";
                                break;
                            case "CORRESPONDENT NAME REQUIRED":
                                errorText = "The correspondent's full name is required";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                errorText = "What sort of response is required?";
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                errorText = "Which is the primary topic?";
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                errorText = "Why is no response needed?";
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                errorText = "Where should this case be transferred to?";
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                errorText = "Why should this case be transferred here?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            errorText = "Do you agree that no response is needed?";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            errorText = "Should this case be transferred to the OGD?";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                errorText = "Can this correspondence be answered by your team?";
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                errorText = "How do you intend to respond?";
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                errorText = "Primary draft document";
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                errorText = "Do you want to QA this offline?";
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                errorText = "Who has done the Offline QA for this case?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "What is your feedback about the response?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                errorText = "Do you approve the response?";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "What is your feedback about the response?";
                                break;
                            case "OVERRIDE PRIVATE OFFICE TEAM REQUIRED":
                                errorText = "Override Private Office Team";
                                break;
                            case "REASON FOR CHANGE MINISTER REQUIRED":
                                errorText = "Why should this be approved by this team instead?";
                                break;
                            case "REASON FOR TOPIC CHANGE REQUIRED":
                                errorText = "Why is the case topic being changed?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MINISTERIAL SIGN OFF":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                errorText = "Do you approve the response?";
                                break;
                            case "REJECTION TO DRAFT NOTE REQUIRED":
                                errorText = "What is your feedback about the response?";
                                break;
                            case "REJECTION TO PRIVATE OFFICE NOTE REQUIRED":
                                errorText = "Why is this case not applicable?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                errorText = "Are you able to dispatch this?";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "Why are you unable to dispatch this?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case TRO:
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                errorText = "When was the correspondence sent?";
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                errorText = "How was the correspondence received?";
                                break;
                            case "COPY TO NUMBER 10 REQUIRED":
                                errorText = "Should the response be copied to Number 10?";
                                break;
                            case "HOME SECRETARY INTEREST REQUIRED":
                                errorText = "Select yes if the Home Secretary has an interest in this case";
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                errorText = "Which is the primary correspondent?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                errorText = "What sort of response is required?";
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                errorText = "Which is the primary topic?";
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                errorText = "Why is no response needed?";
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                errorText = "Where should this case be transferred to?";
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                errorText = "Why should this case be transferred here?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            errorText = "Do you agree that no response is needed?";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            errorText = "Should this case be transferred to the OGD?";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                errorText = "Can this correspondence be answered by your team?";
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                errorText = "How do you intend to respond?";
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                errorText = "Primary draft document";
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                errorText = "Do you want to QA this offline?";
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                errorText = "Who has done the Offline QA for this case?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "What is your feedback about the response?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                errorText = "Are you able to dispatch this?";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "Why are you unable to dispatch this?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case DTEN:
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "DRAFTING DEADLINE REQUIRED":
                                errorText = "What is the drafting deadline?";
                                break;
                            case "DISPATCH DEADLINE REQUIRED":
                                errorText = "What is the dispatch deadline?";
                                break;
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                errorText = "When was the correspondence sent?";
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                errorText = "How was the correspondence received?";
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                errorText = "Which is the primary correspondent?";
                                break;
                            default:
                                pendingStep(stage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                errorText = "What sort of response is required?";
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                errorText = "Which is the primary topic?";
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                errorText = "Why is no response needed?";
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                errorText = "Where should this case be transferred to?";
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                errorText = "Why should this case be transferred here?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            errorText = "Do you agree that no response is needed?";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            errorText = "Should this case be transferred to the OGD?";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                errorText = "Can this correspondence be answered by your team?";
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                errorText = "How do you intend to respond?";
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                errorText = "Primary draft document";
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                errorText = "Do you want to QA this offline?";
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                errorText = "Who has done the Offline QA for this case?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "What is your feedback about the response?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                errorText = "Do you approve the response?";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "What is your feedback about the response?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                errorText = "Are you able to dispatch this?";
                                break;
                            case "REJECTION NOTE REQUIRED":
                                errorText = "Why are you unable to dispatch this?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case MPAM:
                switch (stage.toUpperCase()) {
                    case "CREATION":
                        switch (errorMessage.toUpperCase()) {
                            case "BUSINESS AREA REQUIRED":
                                errorText = "Business Area";
                                break;
                            case "IS MINISTERIAL RESPONSE REQUIRED REQUIRED":
                                errorText = "Does this correspondence need a Ministerial response?";
                                break;
                            case "MINISTERIAL SIGN OFF TEAM REQUIRED":
                                errorText = "Ministerial sign off team";
                                break;
                            case "ADDRESSEE REQUIRED":
                                errorText = "Addressee";
                                break;
                            case "URGENCY REQUIRED":
                                errorText = "Urgency";
                                break;
                            case "CHANNEL RECEIVED REQUIRED":
                                errorText = "Channel received";
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                errorText = "Which is the primary correspondent?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRIAGE":
                        switch (errorMessage.toUpperCase()) {
                            case "ENQUIRY SUBJECT REQUIRED":
                                errorText = "Enquiry subject";
                                break;
                            case "ENQUIRY REASON REQUIRED":
                                errorText = "Enquiry reason";
                                break;
                            case "BUSINESS UNIT REQUIRED":
                                errorText = "Business unit";
                                break;
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "ESCALATION REASON REQUIRED":
                                errorText = "Reason for escalation";
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                errorText = "Explanation for closing case (Telephone)";
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                errorText = "Telephone Contact Route";
                                break;
                            case "CAMPAIGN REQUIRED":
                                errorText = "Campaign";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "REJECTION REASON REQUIRED":
                                errorText = "Return to Triage reason";
                                break;
                            case "ESCALATION REASON REQUIRED":
                                errorText = "Reason for escalation";
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                errorText = "Explanation for closing case (Telephone)";
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                errorText = "Telephone Contact Route";
                                break;
                            case "CAMPAIGN REQUIRED":
                                errorText = "Campaign";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "REJECTION TO TRIAGE REASON REQUIRED":
                                errorText = "Explanation for rejection, move back to triage";
                                break;
                            case "REJECTION TO DRAFT REASON REQUIRED":
                                errorText = "Explanation for rejection, move back to drafting";
                                break;
                            case "ESCALATION REASON REQUIRED":
                                errorText = "Reason for escalation";
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                errorText = "Explanation for closing case (Telephone)";
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                errorText = "Telephone Contact Route";
                                break;
                            case "CAMPAIGN REQUIRED":
                                errorText = "Campaign";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE CHANNEL REQUIRED":
                                errorText = "Response channel";
                                break;
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "CAMPAIGN REQUIRED":
                                errorText = "Campaign";
                                break;
                            case "DISPATCHED DATE REQUIRED":
                                errorText = "Dispatched date";
                                break;
                            case "FOLLOW-UP DUE DATE REQUIRED":
                                errorText = "Follow-up due by";
                                break;
                            case "DETAILS OF FOLLOW-UP REQUIRED":
                                errorText = "Details of follow up";
                                break;
                            case "REJECTION REASON REQUIRED":
                                errorText = "Explanation for rejection";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "AWAITING DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCHED DATE REQUIRED":
                                errorText = "Dispatched date";
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                errorText = "Response channel";
                                break;
                            case "ACTIONS REQUIRED":
                                errorText = "Actions";
                                break;
                            case "REJECTION REASON REQUIRED":
                                errorText = "Return to Draft reason";
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                errorText = "Explanation for closing case (Telephone)";
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                errorText = "Telephone Contact Route";
                                break;
                            case "CAMPAIGN REQUIRED":
                                errorText = "Campaign";
                                break;
                            case "FOLLOW-UP DUE DATE REQUIRED":
                                errorText = "Follow-up due by";
                                break;
                            case "DETAILS OF FOLLOW-UP REQUIRED":
                                errorText = "Details of follow up";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case MTS:
                if (stage.equalsIgnoreCase("DATA INPUT")) {
                    switch (errorMessage.toUpperCase()) {
                        case "PRIMARY CORRESPONDENT REQUIRED":
                            errorText = "Which is the primary correspondent?";
                            break;
                        case "BUSINESS AREA REQUIRED":
                            errorText = "Business Area";
                            break;
                        case "BUSINESS UNIT REQUIRED":
                            errorText = "Business unit";
                            break;
                        case "URGENCY REQUIRED":
                            errorText = "Urgency";
                            break;
                        case "CHANNEL RECEIVED REQUIRED":
                            errorText = "Channel received";
                            break;
                        case "ENQUIRY SUBJECT REQUIRED":
                            errorText = "Enquiry subject";
                            break;
                        case "ENQUIRY REASON REQUIRED":
                            errorText = "Enquiry reason";
                            break;
                        case "SUPPORT CASE NOTE REQUIRED":
                            errorText = "Note to support case";
                            break;
                        case "YOUR BUSINESS AREA REQUIRED":
                            errorText = "Your Business Area";
                            break;
                        default:
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                    }
                } else {
                    pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case COMP:
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        switch (errorMessage.toUpperCase()) {
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                errorText = "Which is the primary correspondent?";
                                break;
                            case "COMPLAINT TYPE REQUIRED":
                                errorText = "Complaint Type";
                                break;
                            case "CHANNEL REQUIRED":
                                errorText = "Channel";
                                break;
                            case "SEVERITY REQUIRED":
                                errorText = "Severity";
                                break;
                            case "OWNING CSU REQUIRED":
                                errorText = "Owning CSU";
                                break;
                            case "COMPLAINT TYPE OPTION REQUIRED":
                                errorText = "Select at least one complaint type option";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE TRIAGE":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN YOUR TEAM RESPOND TO COMPLAINT REQUIRED":
                                errorText = "Can your team respond to this complaint?";
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                errorText = "Enter reason for transfer";
                                break;
                            case "TRANSFER TO REQUIRED":
                                errorText = "Transfer to";
                                break;
                            case "BUSINESS AREA REQUIRED":
                                errorText = "Business Area";
                                break;
                            case "ENQUIRY REASON REQUIRED":
                                errorText = "Enquiry Reason";
                                break;
                            case "IS LETTER OF AUTHORITY REQUIRED RESPONSE REQUIRED":
                                errorText = "Is a Letter of Authority required?";
                                break;
                            case "ACTION REQUIRED":
                                errorText = "Action";
                                break;
                            case "ESCALATION REASON REQUIRED":
                                errorText = "Enter reason for escalation";
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                errorText = "Enter note for case completion";
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                errorText = "Complete the case and permanently close?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                errorText = "Primary draft document";
                                break;
                            case "ACTION REQUIRED":
                                errorText = "Action";
                                break;
                            case "ESCALATION REASON":
                                errorText = "Enter reason for escalation";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE ESCALATED":
                        if (errorMessage.equalsIgnoreCase("ACTION REQUIRED")) {
                            errorText = "Action";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "CCH":
                        switch (errorMessage.toUpperCase()) {
                            case "TRANSFER TO REQUIRED":
                                errorText = "Transfer to";
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                errorText = "Enter note for case completion";
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                errorText = "Complete the case and permanently close?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE QA":
                        switch (errorMessage.toUpperCase()) {
                            case "QA RESULT REQUIRED":
                                errorText = "QA Result";
                                break;
                            case "REJECTION REASON REQUIRED":
                                errorText = "Enter reason for rejection";
                                break;

                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE SEND":
                        if (errorMessage.equalsIgnoreCase("CASE OUTCOME REQUIRED")) {
                            errorText = "Case Outcome";
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "COMPLAINT CLOSED":
                        switch (errorMessage.toUpperCase()) {
                            case "PROGRESS CASE REQUIRED":
                                errorText = "Progress case";
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                errorText = "Enter note for case completion";
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                errorText = "Complete the case and permanently close?";
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        WebElementFacade displayedError = findBy("//a[contains(text(), '" + errorText + "')]");
        displayedError.shouldBeVisible();
    }
}
