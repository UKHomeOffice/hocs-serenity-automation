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
        selectASpecificAddressee("Home Secretary");
        selectASpecificUrgency("Standard");
        selectASpecificInboundChannel("Email");
    }

    public void selectABusinessArea() {
        String businessArea = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
        setSessionVariable("businessArea").to(businessArea);
    }
    public void selectASpecificBusinessArea(String businessArea) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(businessArea, "Business Area");
        setSessionVariable("businessArea").to(businessArea);
    }

    public void selectASpecificRefType(String refType) {
        recordCaseData.selectSpecificRadioButton(refType);
        setSessionVariable("refType").to(refType);
    }

    public void selectAMinisterialSignOffTeam() {
        String signOffTeam = recordCaseData.selectRandomOptionFromDropdownWithHeading("Ministerial sign off team");
        setSessionVariable("ministerialSignOffTeam").to(signOffTeam);
        setSessionVariable("signOffTeam").to(signOffTeam);
    }

    public void selectASpecificMinisterialSignOffTeam(String signOffTeam) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(signOffTeam, "Ministerial sign off team");
        setSessionVariable("ministerialSignOffTeam").to(signOffTeam);
        setSessionVariable("signOffTeam").to(signOffTeam);
    }

    public void selectAnAddressee() {
        String addressee = recordCaseData.selectRandomOptionFromDropdownWithHeading("Addressee");
        setSessionVariable("addressee").to(addressee);
    }

    public void selectASpecificAddressee(String addressee) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(addressee, "Addressee");
        setSessionVariable("addressee").to(addressee);
    }

    public void selectAnUrgency() {
        String urgency = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Urgency");
        setSessionVariable("urgency").to(urgency);
    }

    public void selectASpecificUrgency(String urgency) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(urgency, "Urgency");
        setSessionVariable("urgency").to(urgency);
    }

    public void selectAnInboundChannel() {
        String inboundChannel = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel received");
        setSessionVariable("inboundChannel").to(inboundChannel);
    }

    public void selectASpecificInboundChannel(String inboundChannel) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(inboundChannel, "Channel received");
        setSessionVariable("inboundChannel").to(inboundChannel);
    }

    public void addCorrespondentWithSpecificReferenceToCase(String refNumber) {
        completeRequiredQuestions();
        clickContinueButton();
        correspondents.addANonMemberCorrespondentWithASpecificReferenceNumber(refNumber);
    }

    public void moveCaseWithSpecifiedMPCorrespondentToTriageStage(String correspondent) {
        selectASpecificBusinessArea("UKVI");
        selectASpecificRefType("Ministerial");
        selectASpecificMinisterialSignOffTeam("Home Secretary");
        selectASpecificAddressee("Home Secretary");
        selectASpecificUrgency("Standard");
        selectASpecificInboundChannel("Email");
        clickContinueButton();
        correspondents.addASpecificMemberCorrespondent(correspondent);
        clickTheButton("Move to Triage");
    }

    public void triggerMPCorrespondentIsMandatoryScreen() {
        selectASpecificBusinessArea("UKVI");
        selectASpecificRefType("Official");
        selectASpecificUrgency("Standard");
        selectASpecificInboundChannel("Email");
        clickContinueButton();
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    public void assertMPCorrespondentIsRequiredScreenIsDisplayed() {
        waitForAnyTextToAppear("A Member of Parliament is mandatory");
        header1.shouldContainText("A Member of Parliament is mandatory");
    }
}
