package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Creation extends BasePage {

    Correspondents correspondents;

    RecordCaseData recordCaseData;

    @FindBy(id = "MinSignOffTeam")
    public WebElementFacade ministerialSignOffTeamDropdown;

    @FindBy(id = "Addressee")
    public WebElementFacade addresseeDropdown;

    public void completeRequiredQuestions() {
        selectASpecificBusinessArea("UKVI");
        selectASpecificRefType("Ministerial");
        selectASpecificMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency("Standard");
        selectInboundChannel("Email");
    }

    public void selectASpecificBusinessArea(String businessArea) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(businessArea, "Business Area");
        setSessionVariable("businessArea").to(businessArea);
        System.out.println(businessArea + " is the business area");
    }

    public void selectASpecificRefType(String refType) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(refType, "Does this correspondence need a Ministerial response?");
        setSessionVariable("refType").to(refType);
        System.out.println(refType + " is the reference type");
    }

    public void selectAMinisterialSignOffTeam() {
        String signOffTeam = recordCaseData.selectRandomOptionFromDropdownWithHeading("Minsiterial sign off team");
        setSessionVariable("ministerialSignOffTeam").to(signOffTeam);
        setSessionVariable("signOffTeam").to(signOffTeam);
    }

    public void selectASpecificMinisterialSignOffTeam(String signOffTeam) {
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
        selectASpecificBusinessArea("UKVI");
        selectASpecificRefType("Ministerial");
        selectASpecificMinisterialSignOffTeam("Home Secretary");
        selectAddressee("Home Secretary");
        selectUrgency("Standard");
        selectInboundChannel("Email");
        safeClickOn(continueButton);
        correspondents.addASpecificMemberCorrespondent(correspondent);
        clickTheButton("Move to Triage");
    }

    public void triggerMPCorrespondentIsMandatoryScreen() {
        selectASpecificBusinessArea("UKVI");
        selectASpecificRefType("Official");
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
