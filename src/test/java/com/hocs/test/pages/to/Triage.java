package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.List;
import java.util.Random;


public class Triage extends BasePage {

    RecordCaseData recordCaseData;

    public void selectToChangeTheBusinessArea() {
        clickTheLink("Change business area");
        waitForPageWithTitle("Transfer To Business Area");
    }

    public void selectADifferentBusinessArea() {
        String newBusinessArea = selectDifferentRadioButtonFromGroupWithHeading("Business Area");
        setSessionVariable("businessArea").to(newBusinessArea);
    }

    public void selectSetEnquirySubjectAndReasonLink() {
        clickTheLink("Set enquiry subject & reason");
        waitForPageWithTitle("Set Enquiry Subject");
    }

    public void selectAnEnquirySubject() {
        String enquirySubject = recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Subject");
        setSessionVariable("enquirySubject").to(enquirySubject);
    }

    public void selectAnEnquiryReason() {
        String enquiryReason = recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Reason");
        setSessionVariable("enquiryReason").to(enquiryReason);
    }

    public void selectABusinessUnitType() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Unit Type");
    }

    public void selectABusinessUnit() {
        String businessUnit = recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Unit");
        setSessionVariable("businessUnit").to(businessUnit);
    }

    public void selectADifferentChannelReceived() {
        String newChannelReceived = selectDifferentRadioButtonFromGroupWithHeading("Channel Received");
        setSessionVariable("channelReceived").to(newChannelReceived);
    }

    public void selectAClosureReason() {
        String closureReason = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Why should this case be closed?");
        setSessionVariable("closureReason").to(closureReason);
    }

    public void enterClosureDetails() {
        String closureDetails = recordCaseData.enterTextIntoTextAreaWithHeading("Please enter details of why the case is being closed");
        setSessionVariable("closureDetails").to(closureDetails);
    }
}
