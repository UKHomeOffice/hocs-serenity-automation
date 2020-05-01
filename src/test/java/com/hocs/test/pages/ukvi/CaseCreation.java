package com.hocs.test.pages.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CaseCreation extends BasePage {

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

    @FindBy(css = "label[for='Channel-Email']")
    public WebElementFacade channelEmailRadioButton;

    @FindBy(css = "label[for='Channel-Phone']")
    public WebElementFacade channelPhoneRadioButton;

    @FindBy(css = "label[for='Channel-Post']")
    public WebElementFacade channelPostRadioButton;

    @FindBy(css = "label[for='Channel-Outreach']")
    public WebElementFacade channelOutreachRadioButton;


    public void completeRequiredQuestions() {
        selectBusinessArea("UKVI");
        selectRefType("M:Ref");
        selectPriority("Standard");
        selectChannel("Email");
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
    }

    public void selectRefType(String refType) {
        switch (refType.toUpperCase()) {
            case "M:REF":
                safeClickOn(refTypeMRefRadioButton);
                setSessionVariable("refType").to(refType);
                break;
            case "B:REF":
                safeClickOn(refTypeBRefRadioButton);
                setSessionVariable("refType").to(refType);
                break;
            default:
                pendingStep(refType + " is not defined within " + getMethodName());
        }
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
    }

    public void selectChannel(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                safeClickOn(channelEmailRadioButton);
                break;
            case "PHONE":
                safeClickOn(channelPhoneRadioButton);
                break;
            case "POST":
                safeClickOn(channelPostRadioButton);
                break;
            case "OUTREACH":
                safeClickOn(channelOutreachRadioButton);
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
    }

    public void moveCaseFromCaseCreationToCaseTriage() {
        completeRequiredQuestions();
        addCorrespondent.addAPublicCorrespondent();
        clickContinueButton();
    }
}
