package com.hocs.test.pages.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends BasePage {

    @FindBy(css = "label[for='ChannelOut-Email']")
    private WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    private WebElementFacade responseChannelLetterRadioButton;

    @FindBy(css = "label[for='ChannelOut-Phone']")
    private WebElementFacade responseChannelPhoneRadioButton;

    @FindBy(css = "label[for='ChannelOut-Outreach']")
    private WebElementFacade responseChannelOutreachRadioButton;

    @FindBy(xpath = "//label[text()='Move to QA']")
    public WebElementFacade moveToQARadioButton;

    @FindBy(xpath = "//label[text()='Ready for dispatch (bypass QA)']")
    public WebElementFacade readyForDispatchBypassQARadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade putOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    @FindBy(xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    @FindBy(xpath = "//label[text()='Close duplicate case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Response channel is required']")
    public WebElementFacade responseChannelRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Change reference type']")
    public WebElementFacade changeReferenceTypeLink;

    @FindBy(xpath = "//input[@value='Correction']")
    public WebElementFacade correctionTickBox;

    @FindBy(xpath = "//input[@name='RefType'][@checked]")
    public WebElementFacade selectedRefType;

    @FindBy(id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade escalationReasonTextArea;

    @FindBy(xpath = "//label[text()='Return to Triage']")
    public WebElementFacade returnToTriageRadioButton;

    @FindBy(id = "CaseNote_DraftReturnToTriage")
    public WebElementFacade returnToTriageReasonTextArea;

    public void moveCaseFromDraftToQA() {
        safeClickOn(moveToQARadioButton);
        setSessionVariable("action").to("Move to QA");
        safeClickOn(confirmButton);
    }

    public void moveBRefCaseFromDraftToDispatch() {
        selectResponseChannel("Email");
        safeClickOn(readyForDispatchBypassQARadioButton);
        safeClickOn(confirmButton);
    }

    public void selectEscalateDraftCaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        setSessionVariable("action").to("Escalate to workflow manager");
        safeClickOn(confirmButton);
    }

    public void submitReasonToEscalateCase(String escalationReason) {
        escalationReasonTextArea.sendKeys(escalationReason);
        safeClickOn(confirmButton);
        setSessionVariable("escalationReason").to(escalationReason);
    }

    public void putCaseOnHold() {
        safeClickOn(putOnHoldRadioButton);
        setSessionVariable("action").to("Put on hold");
        safeClickOn(confirmButton);
    }

    public void takeDraftCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void deescalateDraftCase() {
        safeClickOn(escalationCompleteRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectToCloseEscalatedCase() {
        safeClickOn(closeCaseRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectResponseChannel(String outboundChannel) {
        switch (outboundChannel.toUpperCase()) {
            case "EMAIL":
                safeClickOn(responseChannelEmailRadioButton);
                break;
            case "LETTER":
                safeClickOn(responseChannelLetterRadioButton);
                break;
            case "PHONE":
                safeClickOn(responseChannelPhoneRadioButton);
                break;
            case "OUTREACH":
                safeClickOn(responseChannelOutreachRadioButton);
                break;
            default:
                pendingStep(outboundChannel + " is not defined within " + getMethodName());
        }
        setSessionVariable("responseChannel").to(outboundChannel);
    }

    public void sendDraftCaseBackToTriage() {
        safeClickOn(returnToTriageRadioButton);
        safeClickOn(confirmButton);
        returnToTriageReasonTextArea.sendKeys("Test");
        setSessionVariable("rejectionReason").to("Test");
        setSessionVariable("rejectionStage").to("Draft");
        safeClickOn(confirmButton);
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        assertThat(actionsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertResponseChannelRequiredErrorMessageDisplayed() {
        assertThat(responseChannelRequiredErrorMessage.isVisible(), is(true));
    }
}
