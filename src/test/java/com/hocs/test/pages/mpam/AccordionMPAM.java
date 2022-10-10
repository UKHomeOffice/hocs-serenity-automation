package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccordionMPAM extends BasePage {

    SummaryTab summaryTab;

    @FindBy(xpath = "//button[text()='Case details']/parent::h2/following-sibling::span")
    public WebElementFacade caseDetailsAccordionButton;

    @FindBy(xpath = "//span[text()='Creation']/parent::span/following-sibling::span")
    public WebElementFacade creationAccordionButton;

    @FindBy(xpath = "//span[text()='Triage']/parent::span/following-sibling::span")
    public WebElementFacade triageAccordionButton;

    @FindBy(xpath = "//span[text()='Draft']/parent::span/following-sibling::span")
    public WebElementFacade draftAccordionButton;

    @FindBy(xpath = "//strong[contains(text(), 'Business Area')]/parent::span")
    public WebElementFacade creationAccordionBusinessArea;

    @FindBy(xpath = "//strong[contains(text(), 'Ministerial response')]/parent::span")
    public WebElementFacade creationAccordionMinisterialResponse;

    @FindBy(xpath = "//strong[contains(text(), 'Ministerial sign off team')]/parent::span")
    public WebElementFacade creationMinisterialSignOffTeam;

    @FindBy(xpath = "//strong[contains(text(), 'Urgency')]/parent::span")
    public WebElementFacade creationAccordionUrgency;

    @FindBy(xpath = "//strong[contains(text(), 'Channel received')]/parent::span")
    public WebElementFacade creationAccordionChannelReceived;

    @FindBy(xpath = "//strong[contains(text(), 'primary correspondent')]/parent::span")
    public WebElementFacade creationAccordionPrimaryCorrespondent;

    @FindBy(xpath = "//strong[contains(text(), 'correspondence received')]/parent::span")
    public WebElementFacade creationAccordionCorrespondenceReceivedDate;

    @FindBy(xpath = "//strong[contains(text(), 'Enquiry subject')]/parent::span")
    public WebElementFacade triageAccordionEnquirySubject;

    @FindBy(xpath = "//strong[contains(text(), 'Enquiry reason')]/parent::span")
    public WebElementFacade triageAccordionEnquiryReason;

    @FindBy(xpath = "//strong[contains(text(), 'Business unit')]/parent::span")
    public WebElementFacade triageAccordionBusinessUnit;

    @FindBy(xpath = "//button[text()='Triage']/ancestor::div//div[@id='accordion-default-content-1']//strong[contains(text(), 'Actions')]/parent::span")
    public WebElementFacade triageAccordionActions;

    @FindBy(xpath = "//button[text()='Draft']/ancestor::div//div[@id='accordion-default-content-2']//strong[contains(text(), 'Actions')]/parent::span")
    public WebElementFacade draftAccordionActions;

    @FindBy(xpath = "//a[text()='Change business area']")
    public WebElementFacade changeBusinessAreaHypertext;

    @FindBy(id = "BusUnit")
    public WebElementFacade businessUnitDropdown;

    @FindBy(xpath = "//a[text()='Business unit is required']")
    public WebElementFacade businessUnitRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//label[text()='Confirm']")
    public WebElementFacade confirmRadioButton;

    @FindBy(id = "MinSignOffTeam")
    public WebElementFacade ministerialSignOffTeamDropdown;

    @FindBy(xpath = "//a[text()='Change reference type']")
    public WebElementFacade changeReferenceTypeLink;

    @FindBy(xpath = "//label[contains(text(), 'Correcting an error')]")
    public WebElementFacade correctionTickBox;

    @FindBy(xpath = "//input[@value='Save Changes']")
    public WebElementFacade saveChangesButton;

    @FindBy(xpath = "//input[@name='BusArea'][@checked]/following-sibling::label")
    public WebElementFacade selectedBusinessArea;

    @FindBy(xpath = "//textarea[@id='CaseNote_TriageChangeCaseType']")
    public WebElementFacade changeReferenceTypeTextArea;

    public void openCaseDetailsAccordion() {
        openOrCloseAccordionSection("Case details");
    }

    public void openCreationAccordion() {
        safeClickOn(creationAccordionButton);
    }

    public void openTriageAccordion() {
        safeClickOn(triageAccordionButton);
        setSessionVariable("accordion").to("TRIAGE");
    }

    public void openDraftAccordion() {
        safeClickOn(draftAccordionButton);
        setSessionVariable("accordion").to("DRAFT");
    }

    public void getQuestionResponse(String responseType) {
        String response = null;
        switch (responseType.toUpperCase()) {
            case "ACTIONS":
                String actionsFullLine = "";
                if (sessionVariableCalled("accordion").equals("TRIAGE")) {
                    actionsFullLine = triageAccordionActions.getText();
                } else if (sessionVariableCalled("accordion").equals("DRAFT")) {
                    actionsFullLine = draftAccordionActions.getText();
                }
                response = actionsFullLine.split(": ")[1];
                break;
            case "BUSINESS AREA":
                String businessAreaFullLine = creationAccordionBusinessArea.getText();
                response = businessAreaFullLine.split(": ")[1];
                break;
            case "REFERENCE TYPE":
                String ministerialResponseFullLine = creationAccordionMinisterialResponse.getText();
                response = ministerialResponseFullLine.split(": ")[1];
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                String ministerialSignOffTeamFullLine = creationMinisterialSignOffTeam.getText();
                response = ministerialSignOffTeamFullLine.split(": ")[1];
                break;
            case "URGENCY":
                String urgencyFullLine = creationAccordionUrgency.getText();
                response = urgencyFullLine.split(": ")[1];
                break;
            case "CHANNEL RECEIVED":
                String channelReceivedFullLine = creationAccordionChannelReceived.getText();
                response = channelReceivedFullLine.split(": ")[1];
                break;
            case "PRIMARY CORRESPONDENT":
                String primaryCorrespondentFullLine = creationAccordionPrimaryCorrespondent.getText();
                response = primaryCorrespondentFullLine.split(": ")[1];
                break;
            case "RECEIVED DATE":
                String receivedDateFullLine = creationAccordionCorrespondenceReceivedDate.getText();
                response = receivedDateFullLine.split(": ")[1];
                break;
            case "ENQUIRY SUBJECT":
                String enquirySubjectFullLine = triageAccordionEnquirySubject.getText();
                response = enquirySubjectFullLine.split(": ")[1];
                break;
            case "ENQUIRY REASON":
                String enquiryReasonFullLine = triageAccordionEnquiryReason.getText();
                response = enquiryReasonFullLine.split(": ")[1];
                break;
            case "BUSINESS UNIT":
                String businessUnitFullLine = triageAccordionBusinessUnit.getText();
                response = businessUnitFullLine.split(": ")[1];
                break;
            default:
                pendingStep(responseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("response").to(response);
    }

    public void selectBusinessArea(String businessArea) {
        WebElementFacade businessAreaLabel = findBy("//label[contains(text(), '" + businessArea + "')]");
        waitABit(500);
        safeClickOn(businessAreaLabel);
    }

    public void changeBusinessAreaAndUnit(String businessArea) {
        safeClickOn(changeBusinessAreaHypertext);
        waitABit(1000);
        selectBusinessArea(businessArea);
        businessUnitDropdown.selectByIndex(1);
        safeClickOn(continueButton);
    }

    public void changeRefTypeConvertingACase() {
        safeClickOn(changeReferenceTypeLink);
        WebElementFacade newRefTypeInHeader = findBy("//h1[contains(text(), 'Change reference type')]");
        String newRefType = newRefTypeInHeader.getText().split("\\W")[4];
        enterTextForConversionTo(newRefType);
        setSessionVariable("refType").to(newRefType);
        safeClickOn(saveChangesButton);
    }

    public void changeRefTypeCorrectingAnError() {
        safeClickOn(changeReferenceTypeLink);
        WebElementFacade newRefTypeInHeader = findBy("//h1[contains(text(), 'Change reference type')]");
        String newRefType = newRefTypeInHeader.getText().split("\\W")[4];
        enterTextForConversionTo(newRefType);
        setSessionVariable("refType").to(newRefType);
        safeClickOn(correctionTickBox);
        safeClickOn(saveChangesButton);
    }

    private void enterTextForConversionTo(String newRefType) {
        String conversionNotes = "Test convert reference type from " + sessionVariableCalled("refType") + " to " + newRefType;
        changeReferenceTypeTextArea.sendKeys(conversionNotes);
        setSessionVariable("conversionNotes").to(conversionNotes);
    }

    public void changeMinisterialSignOffTeam(String newSignOffTeam) {
        ministerialSignOffTeamDropdown.selectByVisibleText(newSignOffTeam);
    }

    public void assertBusinessAreaHasChanged(String newBusinessArea) {
        summaryTab.selectSummaryTab();
        waitABit(2500);
        assertThat(summaryTab.allocatedTeam.getText().contains(newBusinessArea), is(true));
    }

    public void assertInputMatchesCaseDetailsResponse(String responseType) {
        String inputResponse = null;
        String displayedResponse;
        switch (responseType.toUpperCase()) {
            case "ACTIONS":
                inputResponse = sessionVariableCalled("action");
                break;
            case "BUSINESS AREA":
                inputResponse = sessionVariableCalled("businessArea");
                break;
            case "REFERENCE TYPE":
                inputResponse = sessionVariableCalled("refType");
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                inputResponse = sessionVariableCalled("ministerialSignOffTeam");
                break;
            case "URGENCY":
                inputResponse = sessionVariableCalled("urgency");
                break;
            case "CHANNEL RECEIVED":
                inputResponse = sessionVariableCalled("inboundChannel");
                break;
            case "PRIMARY CORRESPONDENT":
                inputResponse = sessionVariableCalled("correspondentFullName");
                break;
            case "RECEIVED DATE":
                inputResponse = sessionVariableCalled("correspondenceReceivedDate");
                break;
            case "ENQUIRY SUBJECT":
                inputResponse = sessionVariableCalled("enquirySubject");
                break;
            case "ENQUIRY REASON":
                inputResponse = sessionVariableCalled("enquiryReason");
                break;
            case "BUSINESS UNIT":
                inputResponse = sessionVariableCalled("businessUnit");
                break;
            case "RESPONSE CHANNEL":
                inputResponse = sessionVariableCalled("responseChannel");
                break;
            default:
                pendingStep(responseType + " is not defined within " + getMethodName());
        }
        displayedResponse = sessionVariableCalled("response");
        assertThat(displayedResponse.contains(inputResponse), is(true));
    }

    public void assertAllCreationResponsesMatchInput() {
        getQuestionResponse("Business Area");
        assertInputMatchesCaseDetailsResponse("Business Area");
        getQuestionResponse("Reference Type");
        assertInputMatchesCaseDetailsResponse("Reference Type");
        getQuestionResponse("Urgency");
        assertInputMatchesCaseDetailsResponse("Urgency");
        getQuestionResponse("Channel Received");
        assertInputMatchesCaseDetailsResponse("Channel Received");
        getQuestionResponse("Primary Correspondent");
        assertInputMatchesCaseDetailsResponse("Primary Correspondent");
        getQuestionResponse("Received Date");
        assertInputMatchesCaseDetailsResponse("Received Date");
    }

    public void assertAllTriageResponsesMatchInput() {
        getQuestionResponse("Enquiry Subject");
        assertInputMatchesCaseDetailsResponse("Enquiry Subject");
        getQuestionResponse("Enquiry Reason");
        assertInputMatchesCaseDetailsResponse("Enquiry Reason");
        getQuestionResponse("Business Unit");
        assertInputMatchesCaseDetailsResponse("Business Unit");
        getQuestionResponse("Actions");
        assertInputMatchesCaseDetailsResponse("Actions");
    }

    public void assertAllDraftResponsesMatchInput() {
        getQuestionResponse("Actions");
        assertInputMatchesCaseDetailsResponse("Actions");
    }

    public void assertChangeBusinessAreaErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "BUSINESS UNIT REQUIRED":
                businessUnitRequiredErrorMessage.shouldContainText("Business unit is required");
                break;
            case "ACTIONS REQUIRED":
                actionsRequiredErrorMessage.shouldContainText("Actions is required");
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    public void assertRefTypeHasChanged(String refType) {
        WebElementFacade checkedRefType = findBy("//legend[@id='RefType-legend']/following-sibling::div//input[@checked]/following-sibling::label");
        checkedRefType.shouldContainText(refType);
    }

    public void assertChangeBusinessAreaHyperTextIsAtStage(String stage) {
        boolean isAtThisStage = false;
        switch (stage.toUpperCase()) {
            case "TRIAGE":
            case "DRAFT":
                isAtThisStage = true;
                break;
            case "QA":
            case "PRIVATE OFFICE":
            case "AWAITING DISPATCH":
                isAtThisStage = false;
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        assertThat(changeBusinessAreaHypertext.isVisible(), is(isAtThisStage));
    }

    public void assertCorrectBusinessAreaSelected() {
        assertThat(selectedBusinessArea.getText().equals(sessionVariableCalled("businessArea")), is(true));
    }
}