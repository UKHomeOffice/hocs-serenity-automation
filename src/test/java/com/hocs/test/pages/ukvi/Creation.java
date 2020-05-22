package com.hocs.test.pages.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Creation extends BasePage {

    AddCorrespondent addCorrespondent;

    @FindBy(css = "label[for='BusArea-UKVI']")
    public WebElementFacade businessAreaUKVIRadioButton;

    @FindBy(css = "label[for='BusArea-BF']")
    public WebElementFacade businessAreaBFRadioButton;

    @FindBy(css = "label[for='BusArea-IE']")
    public WebElementFacade businessAreaIERadioButton;

    @FindBy(css = "label[for='BusArea-EUSS']")
    public WebElementFacade businessAreaEUSSRadioButton;

    @FindBy(css = "label[for='BusArea-HMPO']")
    public WebElementFacade businessAreaHMPORadioButton;

    @FindBy(css = "label[for='BusArea-Windrush']")
    public WebElementFacade businessAreaWindrushRadioButton;

    @FindBy(css = "label[for='BusArea-Coronavirus']")
    public WebElementFacade businessAreaCoronavirusRadioButton;

    @FindBy(css = "label[for='RefType-M-Ref']")
    public WebElementFacade refTypeMRefRadioButton;

    @FindBy(css = "label[for='RefType-B-Ref']")
    public WebElementFacade refTypeBRefRadioButton;

    @FindBy(css = "label[for='Priority-Standard']")
    public WebElementFacade priorityStandardRadioButton;

    @FindBy(css = "label[for='Priority-Priority']")
    public WebElementFacade priorityPriorityRadioButton;

    @FindBy(css = "label[for='Priority-Immediate']")
    public WebElementFacade priorityImmediateRadioButton;

    @FindBy(css = "label[for='ChannelIn-Email']")
    public WebElementFacade channelEmailRadioButton;

    @FindBy(css = "label[for='ChannelIn-Post']")
    public WebElementFacade channelPostRadioButton;

    @FindBy(css = "label[for='ChannelIn-Phone-replied']")
    public WebElementFacade channelPhoneReplyGivenRadioButton;

    @FindBy(css = "label[for='ChannelIn-Phone-required']")
    public WebElementFacade channelPhoneResponseRequiredRadioButton;

    @FindBy(css = "label[for='ChannelIn-PO']")
    public WebElementFacade channelPrivateOfficeRadioButton;

    @FindBy(css = "label[for='ChannelIn-Outreach']")
    public WebElementFacade channelOutreachRadioButton;

    @FindBy(xpath = "//a[contains(@href, '#BusArea-error')]")
    public WebElementFacade businessAreaIsRequiredErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#RefType-error')]")
    public WebElementFacade referenceTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#Priority-error')]")
    public WebElementFacade priorityIsRequiredErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#ChannelIn-error')]")
    public WebElementFacade channelReceivedIsRequiredErrorMessage;


    public void completeRequiredQuestions() {
        selectBusinessArea("UKVI");
        selectRefType("M:Ref");
        selectPriority("Standard");
        selectInboundChannel("Email");
    }

    public void selectBusinessArea(String businessArea) {
        switch (businessArea.toUpperCase()) {
            case "UKVI":
                safeClickOn(businessAreaUKVIRadioButton);
                setSessionVariable("businessArea").to("UKVI/BF/IE");
                break;
            case "BF":
                safeClickOn(businessAreaBFRadioButton);
                setSessionVariable("businessArea").to("UKVI/BF/IE");
                break;
            case "IE":
                safeClickOn(businessAreaIERadioButton);
                setSessionVariable("businessArea").to("UKVI/BF/IE");
                break;
            case "EUSS":
                safeClickOn(businessAreaEUSSRadioButton);
                setSessionVariable("businessArea").to(businessArea);
                break;
            case "HMPO":
                safeClickOn(businessAreaHMPORadioButton);
                setSessionVariable("businessArea").to(businessArea);
                break;
            case "WINDRUSH":
                safeClickOn(businessAreaWindrushRadioButton);
                setSessionVariable("businessArea").to(businessArea);
                break;
            case "CORONAVIRUS":
                safeClickOn(businessAreaCoronavirusRadioButton);
                setSessionVariable("businessArea").to(businessArea);
                break;
            default:
                pendingStep(businessArea + " is not defined within " + getMethodName());
        }
        System.out.println(businessArea + " is the business area");
    }

    public void selectRefType(String refType) {
        switch (refType.toUpperCase()) {
            case "M:REF":
                safeClickOn(refTypeMRefRadioButton);
                break;
            case "B:REF":
                safeClickOn(refTypeBRefRadioButton);
                break;
            default:
                pendingStep(refType + " is not defined within " + getMethodName());
        }
        setSessionVariable("refType").to(refType);
        System.out.println(refType + " is the reference type");
    }

    public void selectPriority(String priority) {
        switch (priority.toUpperCase()) {
            case "STANDARD":
                safeClickOn(priorityStandardRadioButton);
                break;
            case "PRIORITY":
                safeClickOn(priorityPriorityRadioButton);
                break;
            case "IMMEDIATE":
                safeClickOn(priorityImmediateRadioButton);
                break;
            default:
                pendingStep(priority + " is not defined within " + getMethodName());
        }
        setSessionVariable("priority").to(priority);
        System.out.println(priority + " is the priority level");
    }

    public void selectInboundChannel(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                safeClickOn(channelEmailRadioButton);
                break;
            case "PHONE ROUTED":
                safeClickOn(channelPhoneResponseRequiredRadioButton);
                break;
            case "PHONE COMPLETED":
                safeClickOn(channelPhoneReplyGivenRadioButton);
                break;
            case "POST":
                safeClickOn(channelPostRadioButton);
                break;
            case "OUTREACH":
                safeClickOn(channelOutreachRadioButton);
                break;
            case "PRIVATE OFFICE":
                safeClickOn(channelPrivateOfficeRadioButton);
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
        setSessionVariable("inboundChannel").to(channel);
        System.out.println(channel + " is the inbound channel");
    }

    public void moveCaseFromCreationToTriage() {
        completeRequiredQuestions();
        clickTheButton("Continue");
        addCorrespondent.addAPublicCorrespondent();
        clickTheButton("Move to Triage");
    }

    public void moveCaseWithSpecifiedBusinessAreaAndRefTypeToCaseTriageStage(String businessArea, String refType) {
        selectBusinessArea(businessArea);
        selectRefType(refType);
        selectInboundChannel("Email");
        selectPriority("Standard");
        clickTheButton("Continue");
        addCorrespondent.addAPublicCorrespondent();
        clickTheButton("Move to Triage");
    }

    public void assertCaseCreationRequiredQuestionErrorMessages() {
        businessAreaIsRequiredErrorMessage.shouldContainText("Business Area is required");
        referenceTypeIsRequiredErrorMessage.shouldContainText("Does this correspondence need a Ministerial response? is required");
        priorityIsRequiredErrorMessage.shouldContainText("Priority is required");
        channelReceivedIsRequiredErrorMessage.shouldContainText("Channel received is required");
    }
}
