package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccordionMPAM extends BasePage {

    @FindBy(xpath = "//button[text()='Case details']/parent::h2/following-sibling::span")
    public WebElementFacade caseDetailsAccordionButton;

    @FindBy(xpath = "//button[text()='Creation']/parent::h2/following-sibling::span")
    public WebElementFacade creationAccordionButton;

    @FindBy(xpath = "//button[text()='Triage']/parent::h2/following-sibling::span")
    public WebElementFacade triageAccordionButton;

    @FindBy(xpath = "//button[text()='Draft']/parent::h2/following-sibling::span")
    public WebElementFacade draftAccordionButton;

    @FindBy(xpath = "//strong[contains(text(), 'Business Area')]/parent::span")
    public WebElementFacade creationAccordionBusinessArea;

    @FindBy(xpath = "//strong[contains(text(), 'Ministerial response')]/parent::span")
    public WebElementFacade creationAccordionMinisterialResponse;

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

    @FindBy(xpath = "//strong[contains(text(), 'Response channel')]/parent::span")
    public WebElementFacade draftAccordionResponseChannel;

    public void openCreationAccordion() {
        safeClickOn(creationAccordionButton);
    }

    public void openTriageAccordion() {
        safeClickOn(triageAccordionButton);
    }

    public void openDraftAccordion() {
        safeClickOn(draftAccordionButton);
    }

    public void getQuestionResponse(String responseType) {
        String response = null;
        switch (responseType.toUpperCase()) {
            case "BUSINESS AREA":
                String businessAreaFullLine = creationAccordionBusinessArea.getText();
                response = businessAreaFullLine.split(": ")[1];
                break;
            case "REFERENCE TYPE":
                String ministerialResponseFullLine = creationAccordionMinisterialResponse.getText();
                response = ministerialResponseFullLine.split(": ")[1];
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
            case "RESPONSE CHANNEL":
                String responseChannelFullLine = draftAccordionResponseChannel.getText();
                response = responseChannelFullLine.split(": ")[1];
                break;
            default:
                pendingStep(responseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("response").to(response);
    }

    public void assertInputMatchesCaseDetailsResponse(String responseType) {
        String inputResponse = null;
        String displayedResponse;
        switch (responseType.toUpperCase()) {
            case "BUSINESS AREA":
                inputResponse = sessionVariableCalled("businessArea");
                break;
            case "REFERENCE TYPE":
                inputResponse = sessionVariableCalled("refType");
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
}
