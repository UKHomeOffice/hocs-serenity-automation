package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends BasePage {

    @FindBy(css = "label[for='ChannelOut-Email']")
    public WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade responseChannelLetterRadioButton;

    @FindBy(css = "label[for='ChannelOut-Phone']")
    public WebElementFacade responseChannelPhoneRadioButton;

    @FindBy(css = "label[for='ChannelOut-Outreach']")
    public WebElementFacade responseChannelOutreachRadioButton;

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

    public void moveCaseFromDraftToQA() {
        selectResponseChannel("Email");
        safeClickOn(moveToQARadioButton);
        safeClickOn(confirmButton);
    }

    public void moveBRefCaseFromDraftToDispatch() {
        selectResponseChannel("Email");
        safeClickOn(readyForDispatchBypassQARadioButton);
        safeClickOn(confirmButton);
    }

    public void escalateCaseToWorkflowManager() {
        selectResponseChannel("Letter");
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
    }

    public void putCaseOnHold() {
        selectResponseChannel("Letter");
        safeClickOn(putOnHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void takeTriageCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void deescalateTriageCase() {
        safeClickOn(escalationCompleteRadioButton);
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
}
