package com.hocs.test.glue.decs;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Documents;
import com.hocs.test.pages.comp.AccordionCOMP;
import com.hocs.test.pages.comp.CCH;
import com.hocs.test.pages.comp.ComplaintClosed;
import com.hocs.test.pages.comp.Registration;
import com.hocs.test.pages.comp.ServiceDraft;
import com.hocs.test.pages.comp.ServiceQA;
import com.hocs.test.pages.comp.ServiceTriage;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.ukvi.Campaign;
import com.hocs.test.pages.ukvi.CloseCaseTelephone;
import com.hocs.test.pages.ukvi.Creation;
import com.hocs.test.pages.ukvi.DispatchStages;
import com.hocs.test.pages.ukvi.Draft;
import com.hocs.test.pages.ukvi.MTSDataInput;
import com.hocs.test.pages.ukvi.QA;
import com.hocs.test.pages.ukvi.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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

    AddCorrespondent addCorrespondent;

    Registration registration;

    ServiceTriage serviceTriage;

    ServiceDraft serviceDraft;

    ServiceQA serviceQA;

    ComplaintClosed complaintClosed;

    CCH cch;

    AccordionCOMP accordionCOMP;

    @And("I trigger the {string} error message at (the ){string}( stage)")
    public void iTriggerTheErrorMessageAtTheStage(String errorMessage, String stage) {
        String caseType = sessionVariableCalled("caseType");
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "COPY TO NUMBER 10 REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "HOME SECRETARY INTEREST REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                markup.selectPolicyResponseRadioButton();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                markup.selectNoReplyNeededRadioButton();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                safeClickOn(continueButton);
                                typeInto(markup.OGDDestinationTextBox, "TEST");
                                safeClickOn(finishButton);
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                safeClickOn(continueButton);
                                typeInto(markup.OGDReasonTextBox, "TEST");
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            safeClickOn(finishButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            safeClickOn(finishButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.offlineQaYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(qaResponse.QARejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(privateOfficeApproval.privateOfficeRejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            case "OVERRIDE PRIVATE OFFICE TEAM REQUIRED":
                                safeClickOn(privateOfficeApproval.privateOfficeChangeMinisterRadioButton);
                                safeClickOn(continueButton);
                                typeInto(privateOfficeApproval.privateOfficeOverrideNoteField, "Test");
                                safeClickOn(finishButton);
                                break;
                            case "REASON FOR CHANGE MINISTER REQUIRED":
                                safeClickOn(privateOfficeApproval.privateOfficeChangeMinisterRadioButton);
                                safeClickOn(continueButton);
                                privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown("Home Secretary");
                                safeClickOn(finishButton);
                                break;
                            case "REASON FOR TOPIC CHANGE REQUIRED":
                                safeClickOn(privateOfficeApproval.changeTopicRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MINISTERIAL SIGN OFF":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION TO DRAFT NOTE REQUIRED":
                                safeClickOn(ministerialSignOff.ministerSignOffRejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION TO PRIVATE OFFICE NOTE REQUIRED":
                                safeClickOn(ministerialSignOff.notApplicableRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(dispatch.dispatchRejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "COPY TO NUMBER 10 REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "HOME SECRETARY INTEREST REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, "01/01/2021");
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(dataInput.shouldResponseBeCopiedN10YesRadioButton);
                                safeClickOn(dataInput.homeSecInterestYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                markup.selectPolicyResponseRadioButton();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                markup.selectNoReplyNeededRadioButton();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                safeClickOn(continueButton);
                                typeInto(markup.OGDDestinationTextBox, "TEST");
                                safeClickOn(finishButton);
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                safeClickOn(continueButton);
                                typeInto(markup.OGDReasonTextBox, "TEST");
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            safeClickOn(finishButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            safeClickOn(finishButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.offlineQaYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(qaResponse.QARejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(dispatch.dispatchRejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                    }
                break;
            case "DTEN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        switch (errorMessage.toUpperCase()) {
                            case "DRAFTING DEADLINE REQUIRED":
                                typeIntoDateField(dataInput.dtenDispatchDeadlineDayField, dataInput.dtenDispatchDeadlineMonthField,
                                        dataInput.dtenDispatchDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                safeClickOn(continueButton);
                                break;
                            case "DISPATCH DEADLINE REQUIRED":
                                typeIntoDateField(dataInput.dtenDraftingDeadlineDayField, dataInput.dtenDraftingDeadlineMonthField,
                                        dataInput.dtenDraftingDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                safeClickOn(continueButton);
                                break;
                            case "CORRESPONDENCE SENT DATE REQUIRED":
                                typeIntoDateField(dataInput.dtenDraftingDeadlineDayField, dataInput.dtenDraftingDeadlineMonthField,
                                        dataInput.dtenDraftingDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                typeIntoDateField(dataInput.dtenDispatchDeadlineDayField, dataInput.dtenDispatchDeadlineMonthField,
                                        dataInput.dtenDispatchDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                safeClickOn(continueButton);
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "INBOUND CHANNEL REQUIRED":
                                typeIntoDateField(dataInput.dtenDraftingDeadlineDayField, dataInput.dtenDraftingDeadlineMonthField,
                                        dataInput.dtenDraftingDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                typeIntoDateField(dataInput.dtenDispatchDeadlineDayField, dataInput.dtenDispatchDeadlineMonthField,
                                        dataInput.dtenDispatchDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                safeClickOn(continueButton);
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                typeIntoDateField(dataInput.dtenDraftingDeadlineDayField, dataInput.dtenDraftingDeadlineMonthField,
                                        dataInput.dtenDraftingDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                typeIntoDateField(dataInput.dtenDispatchDeadlineDayField, dataInput.dtenDispatchDeadlineMonthField,
                                        dataInput.dtenDispatchDeadlineYearField, getDatePlusMinusNDaysAgo(5));
                                safeClickOn(continueButton);
                                typeIntoDateField(dataInput.dateCorrespondenceReceivedDayField, dataInput.dateCorrespondenceReceivedMonthField,
                                        dataInput.dateCorrespondenceReceivedYearField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dataInput.emailOriginalChannelRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(stage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "MARKUP":
                        switch (errorMessage.toUpperCase()) {
                            case "TYPE OF RESPONSE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY TOPIC REQUIRED":
                                markup.selectPolicyResponseRadioButton();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "WHY IS NO RESPONSE NEEDED REQUIRED":
                                markup.selectNoReplyNeededRadioButton();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            case "WHERE SHOULD THE CASE BE TRANSFERRED REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                safeClickOn(continueButton);
                                typeInto(markup.OGDDestinationTextBox, "TEST");
                                safeClickOn(finishButton);
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                markup.selectReferToOGDRadioButton();
                                safeClickOn(continueButton);
                                typeInto(markup.OGDReasonTextBox, "TEST");
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("NO RESPONSE NEEDED ACTION REQUIRED")) {
                            safeClickOn(finishButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "TRANSFER CONFIRMATION":
                        if (errorMessage.equalsIgnoreCase("SHOULD THIS CASE BE TRANSFERRED RESPONSE REQUIRED")) {
                            safeClickOn(finishButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "INITIAL DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "RESPONSE CHANNEL REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "QA THIS OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "WHO HAS DONE QA OFFLINE REQUIRED":
                                safeClickOn(initialDraft.answeredByMyTeamYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.letterReplyRadioButton);
                                safeClickOn(continueButton);
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                safeClickOn(initialDraft.offlineQaYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA RESPONSE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(qaResponse.QARejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        switch (errorMessage.toUpperCase()) {
                            case "RESPONSE APPROVAL REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(privateOfficeApproval.privateOfficeRejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCH RESPONSE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION NOTE REQUIRED":
                                safeClickOn(dispatch.dispatchRejectRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "MPAM":
                switch (stage.toUpperCase()) {
                    case "CREATION":
                        switch (errorMessage.toUpperCase()) {
                            case "BUSINESS AREA REQUIRED":
                                safeClickOn(creation.refTypeMRefRadioButton);
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                safeClickOn(creation.urgencyStandardRadioButton);
                                safeClickOn(creation.channelEmailRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "IS MINISTERIAL RESPONSE REQUIRED REQUIRED":
                                safeClickOn(creation.businessAreaUKVIRadioButton);
                                safeClickOn(creation.urgencyStandardRadioButton);
                                safeClickOn(creation.channelEmailRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "MINISTERIAL SIGN OFF TEAM REQUIRED":
                                safeClickOn(creation.businessAreaUKVIRadioButton);
                                safeClickOn(creation.refTypeMRefRadioButton);
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                safeClickOn(creation.urgencyStandardRadioButton);
                                safeClickOn(creation.channelEmailRadioButton);
                                safeClickOn(continueButton);
                            case "ADDRESSEE REQUIRED":
                                safeClickOn(creation.businessAreaUKVIRadioButton);
                                safeClickOn(creation.refTypeMRefRadioButton);
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                safeClickOn(creation.urgencyStandardRadioButton);
                                safeClickOn(creation.channelEmailRadioButton);
                                safeClickOn(continueButton);
                            case "URGENCY REQUIRED":
                                safeClickOn(creation.businessAreaUKVIRadioButton);
                                safeClickOn(creation.refTypeMRefRadioButton);
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                safeClickOn(creation.channelEmailRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "CHANNEL RECEIVED REQUIRED":
                                safeClickOn(creation.businessAreaUKVIRadioButton);
                                safeClickOn(creation.refTypeMRefRadioButton);
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                safeClickOn(creation.urgencyStandardRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                safeClickOn(creation.businessAreaUKVIRadioButton);
                                safeClickOn(creation.refTypeMRefRadioButton);
                                creation.ministerialSignOffTeamDropdown.selectByVisibleText("Home Secretary");
                                creation.addresseeDropdown.selectByVisibleText("Home Secretary");
                                safeClickOn(creation.urgencyStandardRadioButton);
                                safeClickOn(creation.channelEmailRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
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
                                safeClickOn(confirmButton);
                                break;
                            case "BUSINESS UNIT REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                safeClickOn(triage.readyToDraftRadioButton);
                                safeClickOn(confirmButton);
                                break;
                            case "ACTIONS REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(confirmButton);
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(triage.escalateToWorkflowManagerRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(triage.closeCaseRadioButton);
                                safeClickOn(confirmButton);
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                safeClickOn(closeCaseButton);
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(triage.closeCaseRadioButton);
                                safeClickOn(confirmButton);
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                safeClickOn(closeCaseButton);
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(triage.setEnquiryHypertext);
                                triage.selectEnquirySubject("Person Specific");
                                triage.selectEnquiryReason("Allowed appeal enquiry update");
                                triage.businessUnitDropdown.selectByIndex(1);
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "REJECTION REASON REQUIRED":
                                safeClickOn(draft.returnToTriageRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(draft.escalateToWorkflowManagerRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                safeClickOn(confirmButton);
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                safeClickOn(closeCaseButton);
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                safeClickOn(confirmButton);
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                safeClickOn(closeCaseButton);
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "QA":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "REJECTION TO TRIAGE REASON REQUIRED":
                                safeClickOn(qa.rejectQAToTriageRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "REJECTION TO DRAFT REASON REQUIRED":
                                safeClickOn(qa.rejectQAToDraftRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(qa.escalateToWorkflowManagerRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                safeClickOn(confirmButton);
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                safeClickOn(closeCaseButton);
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                safeClickOn(confirmButton);
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                safeClickOn(closeCaseButton);
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "PRIVATE OFFICE":
                        switch (errorMessage.toUpperCase()) {
                            case "ACTIONS REQUIRED":
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "DISPATCHED DATE REQUIRED":
                                safeClickOn(dispatchStages.dispatchedRadioButtonAtPrivateOffice);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                clickTheButton("Confirm and close case");
                                break;
                            case "FOLLOW-UP DUE DATE REQUIRED":
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                safeClickOn(confirmButton);
                                typeIntoDateField(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                typeInto(dispatchStages.followUpDetailsTextArea, "Test");
                                safeClickOn(confirmButton);
                                break;
                            case "DETAILS OF FOLLOW-UP REQUIRED":
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                safeClickOn(confirmButton);
                                typeIntoDateField(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                dispatchStages.followUpDateInput(getDatePlusMinusNDaysAgo(0));
                                safeClickOn(confirmButton);
                                break;
                            case "REJECTION REASON":
                                safeClickOn(dispatchStages.draftRejectedRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "AWAITING DISPATCH":
                        switch (errorMessage.toUpperCase()) {
                            case "DISPATCHED DATE REQUIRED":
                                safeClickOn(dispatchStages.dispatchedCloseCaseRadioButton);
                                safeClickOn(confirmButton);
                                break;
                            case "ACTIONS REQUIRED":
                                typeIntoDateField(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(confirmButton);
                                break;
                            case "REJECTION REASON":
                                safeClickOn(dispatchStages.returnToDraftButton);
                                safeClickOn(confirmButton);
                                break;
                            case "REASON FOR CLOSING CASE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                safeClickOn(confirmButton);
                                safeClickOn(closeCaseTelephone.mpHelplineContactRouteRadioButton);
                                safeClickOn(closeCaseButton);
                                break;
                            case "TELEPHONE CONTACT ROUTE REQUIRED":
                                safeClickOn(closeCaseTelephone.closeCaseTelephoneRadioButton);
                                safeClickOn(confirmButton);
                                typeInto(closeCaseTelephone.explanationForClosingCaseTelephoneTextBox, "Test");
                                safeClickOn(closeCaseButton);
                                break;
                            case "CAMPAIGN REQUIRED":
                                safeClickOn(campaign.putCaseIntoCampaignRadioButton);
                                safeClickOn(confirmButton);
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            case "FOLLOW-UP DUE DATE REQUIRED":
                                typeIntoDateField(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                safeClickOn(confirmButton);
                                typeInto(dispatchStages.followUpDetailsTextArea, "Test");
                                safeClickOn(confirmButton);
                                break;
                            case "DETAILS OF FOLLOW-UP REQUIRED":
                                typeIntoDateField(dispatchStages.dispatchedDateDayTextField, dispatchStages.dispatchedDateMonthTextField,
                                        dispatchStages.dispatchedDateYearTextField, getDatePlusMinusNDaysAgo(0));
                                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                                safeClickOn(confirmButton);
                                dispatchStages.followUpDateInput(getDatePlusMinusNDaysAgo(0));
                                safeClickOn(confirmButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "MTS":
                if (stage.equalsIgnoreCase("DATA INPUT")) {
                    switch (errorMessage.toUpperCase()) {
                        case "PRIMARY CORRESPONDENT REQUIRED":
                            waitABit(500);
                            safeClickOn(continueButton);
                            break;
                        case "BUSINESS AREA REQUIRED":
                        case "BUSINESS UNIT REQUIRED":
                            addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                            safeClickOn(continueButton);
                            mtsDataInput.selectUrgency();
                            mtsDataInput.selectChannelReceived();
                            mtsDataInput.selectEnquirySubject();
                            mtsDataInput.enquiryReasonDropdown.selectByIndex(1);
                            typeInto(mtsDataInput.supportNoteTextArea, "Test");
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "URGENCY REQUIRED":
                            addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                            safeClickOn(continueButton);
                            mtsDataInput.selectYourBusinessArea();
                            mtsDataInput.businessUnitDropdown.selectByIndex(1);
                            mtsDataInput.selectChannelReceived();
                            mtsDataInput.selectEnquirySubject();
                            mtsDataInput.enquiryReasonDropdown.selectByIndex(1);
                            typeInto(mtsDataInput.supportNoteTextArea, "Test");
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "CHANNEL RECEIVED REQUIRED":
                            addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                            safeClickOn(continueButton);
                            mtsDataInput.selectYourBusinessArea();
                            mtsDataInput.businessUnitDropdown.selectByIndex(1);
                            mtsDataInput.selectUrgency();
                            mtsDataInput.selectEnquirySubject();
                            mtsDataInput.enquiryReasonDropdown.selectByIndex(1);
                            typeInto(mtsDataInput.supportNoteTextArea, "Test");
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "ENQUIRY SUBJECT REQUIRED":
                        case "ENQUIRY REASON REQUIRED":
                            addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                            safeClickOn(continueButton);
                            mtsDataInput.selectYourBusinessArea();
                            mtsDataInput.businessUnitDropdown.selectByIndex(1);
                            mtsDataInput.selectUrgency();
                            mtsDataInput.selectChannelReceived();
                            typeInto(mtsDataInput.supportNoteTextArea, "Test");
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "SUPPORT CASE NOTE REQUIRED":
                            addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                            safeClickOn(continueButton);
                            mtsDataInput.selectYourBusinessArea();
                            mtsDataInput.businessUnitDropdown.selectByIndex(1);
                            mtsDataInput.selectUrgency();
                            mtsDataInput.selectChannelReceived();
                            mtsDataInput.selectEnquirySubject();
                            mtsDataInput.enquiryReasonDropdown.selectByIndex(1);
                            mtsDataInput.selectYourBusinessArea();
                            clickTheButton("Complete and Close Case");
                            break;
                        case "YOUR BUSINESS AREA REQUIRED":
                            addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                            safeClickOn(continueButton);
                            mtsDataInput.selectYourBusinessArea();
                            mtsDataInput.businessUnitDropdown.selectByIndex(1);
                            mtsDataInput.selectUrgency();
                            mtsDataInput.selectChannelReceived();
                            mtsDataInput.selectEnquirySubject();
                            mtsDataInput.enquiryReasonDropdown.selectByIndex(1);
                            typeInto(mtsDataInput.supportNoteTextArea, "Test");
                            clickTheButton("Complete and Close Case");
                            break;
                        default:
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                    }
                } else {
                    pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "COMP":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        switch (errorMessage.toUpperCase()) {
                            case "PRIMARY CORRESPONDENT REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "COMPLAINT TYPE REQUIRED":
                                addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "CHANNEL REQUIRED":
                                addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                safeClickOn(registration.complaintTypeServiceRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(registration.severityHighRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "SEVERITY REQUIRED":
                                addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                safeClickOn(registration.complaintTypeServiceRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(registration.channelEmailRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "OWNING CSU REQUIRED":
                                addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                safeClickOn(registration.complaintTypeServiceRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(registration.channelEmailRadioButton);
                                safeClickOn(registration.severityHighRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(accordionCOMP.serviceAccordionButton);
                                safeClickOn(accordionCOMP.delayCheckbox);
                                safeClickOn(finishButton);
                                break;
                            case "COMPLAINT TYPE OPTION REQUIRED":
                                addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                safeClickOn(registration.complaintTypeServiceRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(registration.channelEmailRadioButton);
                                safeClickOn(registration.severityHighRadioButton);
                                safeClickOn(continueButton);
                                registration.selectAnOwningCSU();
                                safeClickOn(finishButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE TRIAGE":
                        switch (errorMessage.toUpperCase()) {
                            case "CAN YOUR TEAM RESPOND TO COMPLAINT REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REASON FOR TRANSFER REQUIRED":
                                safeClickOn(serviceTriage.transferTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(serviceTriage.transferToCCHRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "TRANSFER TO REQUIRED":
                                safeClickOn(serviceTriage.transferTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                typeInto(serviceTriage.transferReasonTextArea, "Test");
                                safeClickOn(continueButton);
                                break;
                            case "BUSINESS AREA REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(serviceTriage.loaRequiredYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "ENQUIRY REASON REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.businessAreaDropdown.selectByIndex(1);
                                safeClickOn(serviceTriage.loaRequiredYesRadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "IS LETTER OF AUTHORITY REQUIRED RESPONSE REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.businessAreaDropdown.selectByIndex(1);
                                serviceTriage.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(continueButton);
                                break;
                            case "ACTION REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.businessAreaDropdown.selectByIndex(1);
                                serviceTriage.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(serviceTriage.loaRequiredYesRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "ESCALATION REASON REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.businessAreaDropdown.selectByIndex(1);
                                serviceTriage.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(serviceTriage.loaRequiredYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(serviceTriage.escalateToWFMRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                clickTheButton("Escalate case");
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.businessAreaDropdown.selectByIndex(1);
                                serviceTriage.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(serviceTriage.loaRequiredYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(serviceTriage.noResponseCloseCaseRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                clickTheButton("Complete case");
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                safeClickOn(serviceTriage.acceptTheComplaintRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                safeClickOn(continueButton);
                                serviceTriage.businessAreaDropdown.selectByIndex(1);
                                serviceTriage.enquiryReasonDropdown.selectByIndex(1);
                                safeClickOn(serviceTriage.loaRequiredYesRadioButton);
                                safeClickOn(continueButton);
                                safeClickOn(serviceTriage.noResponseCloseCaseRadioButton);
                                safeClickOn(continueButton);
                                serviceTriage.enterCompletionReason();
                                clickTheButton("Complete case");
                                waitABit(500);
                                safeClickOn(confirmButton);
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE DRAFT":
                        switch (errorMessage.toUpperCase()) {
                            case "PRIMARY DRAFT DOCUMENT REQUIRED":
                                safeClickOn(serviceDraft.sendCaseToQARadioButton);
                                safeClickOn(continueButton);
                                break;
                            case "ACTION REQUIRED":
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(continueButton);
                                break;
                            case "ESCALATION REASON":
                                documents.addADraftDocumentAtDraftStage();
                                safeClickOn(serviceDraft.escalateCaseToWFMRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                clickTheButton("Escalate case");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE ESCALATED":
                        if (errorMessage.equalsIgnoreCase("ACTION REQUIRED")) {
                            waitABit(500);
                            safeClickOn(confirmButton);
                        } else {
                            pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "CCH":
                        switch (errorMessage.toUpperCase()) {
                            case "TRANSFER TO REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                safeClickOn(cch.completeTheCaseClosePermanentlyRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                clickTheButton("Complete case");
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                safeClickOn(cch.completeTheCaseClosePermanentlyRadioButton);
                                safeClickOn(continueButton);
                                cch.enterReasonForCaseCompletion();
                                clickTheButton("Complete case");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE QA":
                        switch (errorMessage.toUpperCase()) {
                            case "QA RESULT REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "REJECTION REASON REQUIRED":
                                safeClickOn(serviceQA.returnResponseToDraftRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                clickTheButton("Reject");
                                break;
                            default:
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                        }
                        break;
                    case "SERVICE SEND":
                            if (errorMessage.equalsIgnoreCase("CASE OUTCOME REQUIRED")) {
                                waitABit(500);
                                safeClickOn(continueButton);
                            } else {
                                pendingStep(errorMessage + " is not defined within " + getMethodName());
                            }
                        break;
                    case "COMPLAINT CLOSED":
                        switch (errorMessage.toUpperCase()) {
                            case "PROGRESS CASE REQUIRED":
                                waitABit(500);
                                safeClickOn(continueButton);
                                break;
                            case "COMPLETE CASE NOTE REQUIRED":
                                safeClickOn(complaintClosed.completeTheCaseClosePermanentlyRadioButton);
                                safeClickOn(continueButton);
                                waitABit(500);
                                clickTheButton("Complete case");
                                break;
                            case "COMPLETE CASE PERMANENTLY RESPONSE REQUIRED":
                                safeClickOn(complaintClosed.completeTheCaseClosePermanentlyRadioButton);
                                safeClickOn(continueButton);
                                complaintClosed.enterReasonForCaseCompletion();
                                clickTheButton("Complete case");
                                waitABit(500);
                                safeClickOn(confirmButton);
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
        String caseType = sessionVariableCalled("caseType");
        switch (caseType.toUpperCase()) {
            case "MIN":
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
            case "TRO":
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
            case "DTEN":
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
            case "MPAM":
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
                            case "REJECTION REASON":
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
            case "MTS":
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
            case "COMP":
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
                            case "IS LETTER OF AUTHORITY REQUIRED":
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