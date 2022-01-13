package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;


public class Triage extends BasePage {

    RecordCaseData recordCaseData;

    public void selectSetEnquirySubjectAndReasonLink() {
        clickTheLink("Set enquiry subject & reason");
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
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Unit");
    }

    public void selectTheAction(String action) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(action, "Actions");
    }
}
