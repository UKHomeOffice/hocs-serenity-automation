package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MTSDataInput extends BasePage {

    Correspondents correspondents;

    RecordCaseData recordCaseData;

    public void selectABusinessArea() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
    }

    public void selectASpecificBusinessArea(String businessArea) {
        recordCaseData.selectSpecificRadioButton(businessArea);
    }

    public void selectABusinessUnit() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business unit");
    }

    public void selectASpecificBusinessUnit(String businessUnit) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(businessUnit, "Business unit");
    }

    public void selectAnUrgency() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Urgency");
    }

    public void selectASpecificUrgency(String urgency) {
        recordCaseData.selectSpecificRadioButton(urgency);
    }

    public void selectAChannelReceived() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel received");
    }

    public void selectASpecificChannelReceived(String channelReceived) {
        recordCaseData.selectSpecificRadioButton(channelReceived);
    }

    public void selectAnEnquirySubject() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Enquiry subject");
    }

    public void selectASpecificEnquirySubject(String enquirySubject) {
        recordCaseData.selectSpecificRadioButton(enquirySubject);
    }

    public void selectAnEnquiryReason() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry reason");
    }

    public void selectASpecificEnquiryReason(String enquiryReason) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(enquiryReason, "Enquiry reason");
    }

    public void enterASupportNote() {
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Note to support case");
        setSessionVariable("supportNote").to(enteredText);
    }

    public void selectYourBusinessArea() {
        selectRandomRadioButtonFromGroupWithHeading("Your Business Area");
    }

    public void completeDataInputStageAndCloseMTSCase() {
        correspondents.addAMemberCorrespondent();
        clickContinueButton();
        selectABusinessArea();
        selectABusinessUnit();
        selectAnUrgency();
        selectAChannelReceived();
        selectAnEnquirySubject();
        selectAnEnquiryReason();
        enterASupportNote();
        selectYourBusinessArea();
        clickTheButton("Complete and Close Case");
    }
}