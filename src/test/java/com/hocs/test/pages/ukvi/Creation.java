package com.hocs.test.pages.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
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

    @FindBy(css = "label[for='BusArea-TransferToOgd']")
    public WebElementFacade businessAreaTransferToOGDRadioButton;

    @FindBy(css = "label[for='BusArea-TransferToOther']")
    public WebElementFacade businessAreaTransferToOtherRadioButton;

    @FindBy(css = "label[for='RefType-Ministerial']")
    public WebElementFacade refTypeMRefRadioButton;

    @FindBy(css = "label[for='RefType-Official']")
    public WebElementFacade refTypeBRefRadioButton;

    @FindBy(css = "label[for='Priority-Standard']")
    public WebElementFacade urgencyStandardRadioButton;

    @FindBy(css = "label[for='Priority-Priority']")
    public WebElementFacade urgencyPriorityRadioButton;

    @FindBy(css = "label[for='Priority-Immediate']")
    public WebElementFacade urgencyImmediateRadioButton;

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
    public WebElementFacade urgencyIsRequiredErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#ChannelIn-error')]")
    public WebElementFacade channelReceivedIsRequiredErrorMessage;

    @FindBy(id = "MinSignOffTeam")
    public WebElementFacade ministerialSignOffTeamDropdown;

    @FindBy(id = "Addressee")
    public WebElementFacade addresseeDropdown;

    public void completeRequiredQuestions() {
        selectBusinessArea("UKVI");
        selectRefType("Ministerial");
        selectMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency("Standard");
        selectInboundChannel("Email");
    }

    public void selectBusinessArea(String businessArea) {
        safeClickRadioButtonByVisibleText(businessArea);
        setSessionVariable("businessArea").to(businessArea);
        System.out.println(businessArea + " is the business area");
    }

    public void selectRefType(String refType) {
        safeClickRadioButtonByVisibleText(refType);
        setSessionVariable("refType").to(refType);
        System.out.println(refType + " is the reference type");
    }

    public void selectMinisterialSignOffTeam(String signOffTeam) {
        if (sessionVariableCalled("refType").toString().toUpperCase().equals("MINISTERIAL")) {
            ministerialSignOffTeamDropdown.selectByVisibleText(signOffTeam);
            setSessionVariable("ministerialSignOffTeam").to(signOffTeam);
            setSessionVariable("signOffTeam").to(signOffTeam);
        }
    }

    public void selectAddressee(String addressee) {
        if (sessionVariableCalled("refType").toString().toUpperCase().equals("MINISTERIAL")) {
            addresseeDropdown.selectByVisibleText(addressee);
            setSessionVariable("addressee").to(addressee);
        }
    }

    public void selectUrgency(String urgency) {
        safeClickRadioButtonByVisibleText(urgency);
        setSessionVariable("urgency").to(urgency);
    }

    public void selectInboundChannel(String inboundChannel) {
        safeClickRadioButtonByVisibleText(inboundChannel);
        setSessionVariable("inboundChannel").to(inboundChannel);
    }

    public void moveCaseFromCreationToTriage() {
        completeRequiredQuestions();
        clickTheButton("Continue");
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        clickTheButton("Move to Triage");
    }

    public void moveCaseWithCorrespondentReferenceNumber(String refNumber) {
        completeRequiredQuestions();
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondentWithAReferenceNumber(refNumber);
        clickTheButton("Move to Triage");
    }

    public void moveCaseWithSpecifiedBusinessAreaAndRefTypeToTriageStage(String businessArea, String refType) {
        selectBusinessArea(businessArea);
        selectRefType(refType);
        selectMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency("Standard");
        selectInboundChannel("Email");
        clickTheButton("Continue");
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        clickTheButton("Move to Triage");
    }

    public void moveCaseWithSpecifiedUrgencyAndRefTypeToTriageStage(String urgency, String refType) {
        selectBusinessArea("Windrush");
        selectRefType(refType);
        selectMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency(urgency);
        selectInboundChannel("Email");
        clickTheButton("Continue");
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        clickTheButton("Move to Triage");
    }

    public void moveCaseWithSpecificMinisterialSignOffTeamToTriageStage(String signOffTeam) {
        selectBusinessArea("UKVI");
        selectRefType("Ministerial");
        selectMinisterialSignOffTeam(signOffTeam);
        selectAddressee(signOffTeam);
        selectUrgency("Standard");
        selectInboundChannel("Email");
        safeClickOn(continueButton);
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        clickTheButton("Move to Triage");
    }

    public void triggerMPCorrespondentIsMandatoryScreen() {
        selectBusinessArea("UKVI");
        selectRefType("Official");
        selectUrgency("Standard");
        selectInboundChannel("Email");
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        clickTheButton("Move to Triage");
    }

    public void moveCaseWithSpecifiedMPCorrespondentToTriageStage(String correspondent) {
        selectBusinessArea("UKVI");
        selectRefType("Ministerial");
        selectMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency("Standard");
        selectInboundChannel("Email");
        safeClickOn(continueButton);
        addCorrespondent.addAMemberCorrespondent(correspondent);
        clickTheButton("Move to Triage");
    }

    public void assertMPCorrespondentIsRequiredScreenIsDisplayed() {
        waitForAnyTextToAppear("A Member of Parliament is mandatory");
        pageTitle.shouldContainText("A Member of Parliament is mandatory");
    }

    public void assertCaseCreationRequiredQuestionErrorMessages() {
        businessAreaIsRequiredErrorMessage.shouldContainText("Business Area is required");
        referenceTypeIsRequiredErrorMessage.shouldContainText("Does this correspondence need a Ministerial response? is required");
        urgencyIsRequiredErrorMessage.shouldContainText("Urgency is required");
        channelReceivedIsRequiredErrorMessage.shouldContainText("Channel received is required");
    }
}
