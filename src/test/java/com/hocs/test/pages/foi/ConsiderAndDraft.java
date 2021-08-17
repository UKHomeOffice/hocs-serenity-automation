package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ConsiderAndDraft extends BasePage {

    RecordCaseData recordCaseData;

    public void selectDraftAcceptance(String input) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(input, "Is this case for your drafting team?");
    }

    public void enterRejectionReason() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Rejection Reason", "Reason");
        setSessionVariable("rejectionReason").to("Test Rejection Reason");
    }

    public void isContributionRequestNeeded(String input) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(input, "Do you need to request contributions?");
    }

    public void selectResponseType(String responseType) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(responseType, "What is the response type?");
    }

    public void selectExemptionReason() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("What is the reason for the exemption?");
    }
}