package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Creation extends BasePage {

    Correspondents correspondents;

    @FindBy(xpath = "//label[text()='UKVI']")
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

    @FindBy(xpath = "//label[text()='Yes (Ministerial)']")
    public WebElementFacade refTypeMRefRadioButton;

    @FindBy(xpath = "//label[text()='No (Official)']")
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
        selectSpecificRadioButton(businessArea);
        setSessionVariable("businessArea").to(businessArea);
        System.out.println(businessArea + " is the business area");
    }

    public void selectRefType(String refType) {
        selectSpecificRadioButton(refType);
        setSessionVariable("refType").to(refType);
        System.out.println(refType + " is the reference type");
    }

    public void selectMinisterialSignOffTeam(String signOffTeam) {
        ministerialSignOffTeamDropdown.selectByVisibleText(signOffTeam);
        setSessionVariable("ministerialSignOffTeam").to(signOffTeam);
        setSessionVariable("signOffTeam").to(signOffTeam);
    }

    public void selectAddressee(String addressee) {
        addresseeDropdown.selectByVisibleText(addressee);
        setSessionVariable("addressee").to(addressee);
    }

    public void selectUrgency(String urgency) {
        selectSpecificRadioButton(urgency);
        setSessionVariable("urgency").to(urgency);
    }

    public void selectInboundChannel(String inboundChannel) {
        selectSpecificRadioButton(inboundChannel);
        setSessionVariable("inboundChannel").to(inboundChannel);
    }

    public void addCorrespondentWithSpecificReferenceToCase(String refNumber) {
        completeRequiredQuestions();
        safeClickOn(continueButton);
        correspondents.addAPublicCorrespondentWithAReferenceNumber(refNumber);
    }

    public void moveCaseWithSpecifiedMPCorrespondentToTriageStage(String correspondent) {
        selectBusinessArea("UKVI");
        selectRefType("Ministerial");
        selectMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency("Standard");
        selectInboundChannel("Email");
        safeClickOn(continueButton);
        correspondents.addASpecificMemberCorrespondent(correspondent);
        clickTheButton("Move to Triage");
    }

    public void triggerMPCorrespondentIsMandatoryScreen() {
        selectBusinessArea("UKVI");
        selectRefType("Official");
        selectUrgency("Standard");
        selectInboundChannel("Email");
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    public void assertMPCorrespondentIsRequiredScreenIsDisplayed() {
        waitForAnyTextToAppear("A Member of Parliament is mandatory");
        header1.shouldContainText("A Member of Parliament is mandatory");
    }
}
