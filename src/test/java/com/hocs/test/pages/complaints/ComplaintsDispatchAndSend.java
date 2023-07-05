package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintsDispatchAndSend extends BasePage {

    RecordCaseData recordCaseData;

    public void selectACaseOutcome() {
        String caseOutcome;
        if(iedetCase()) {
            caseOutcome = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Case outcome");
        }else if (!pogrCase() && !pogr2Case()) {
            caseOutcome = recordCaseData.selectRandomOptionFromDropdownWithHeading("Case Outcome");
        } else {
            caseOutcome = recordCaseData.selectRandomOptionFromDropdownWithHeading("Dispatch Outcome");
            recordCaseData.addValueToAssertSummary("Dispatch Outcome", caseOutcome);
            }
        if (iedetCase() && caseOutcome.equalsIgnoreCase("Other")) {
            recordCaseData.enterTextIntoTextAreaWithHeading("Please provide further details");
        }
    }

    public void selectBFCaseOutcomes() {
        for (int i = 1; i <= 5; i++) {
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint Reason " + i + " Outcome");
        }
    }

    public void selectAResponseChannel() {
        if (!pogrCase() && !pogr2Case()) {
            String responseChannel =  recordCaseData.selectRandomOptionFromDropdownWithHeading("Response Channel");
            recordCaseData.addValueToAssertSummary("Response Channel", responseChannel);
        } else {
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Dispatch Channel");
        }
    }

    public void enterADateOfResponse() {
        String headerText = null;
        if (compCase() || comp2Case() || comp2DirectCase() || bfCase() || bf2Case()) {
            headerText = "Date of Response";
        } else if (iedetCase()) {
            headerText = "Response sent";
        } else if (pogrCase() || pogr2Case()) {
            headerText = "Dispatch Date";
        }
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-1), headerText);
       if(bfCase() || bf2Case()) {
           recordCaseData.addValueToAssertSummary(headerText, getDatePlusMinusNDaysAgo(-1));
       }
    }

    public void enterFinalResponseSentDate(){
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-1),"Final response sent");
    }

    public void assertReasonsForComplaintAreVisible() {
        for (int i = 1; i <= 5; i++) {
            String displayedReasonForComplaint = findBy("//strong[text()='Reason for Complaint " + i + "']/ancestor::div").getText();
            String expectedReasonForComplaint = sessionVariableCalled("reasonForComplaint" + i);
            if (!displayedReasonForComplaint.contains(expectedReasonForComplaint)) {
                Assert.fail("Values do not match.\nExpected Reason for Complaint " + i + ": " + expectedReasonForComplaint + "\nDisplayed "
                        + ": " + displayedReasonForComplaint);
            }
        }
    }

    public void selectIfRefundRequired() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Refund Required");
    }

    public void enterGratisOfferedDetails() {
        String gratisOffered = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gratis Offered");
        recordCaseData.addValueToAssertSummary("Gratis Offered", gratisOffered);
        if (gratisOffered.equalsIgnoreCase("YES")) {
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Document lost or damaged");
            recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Document replaced/replacement ordered");
            String documentType = recordCaseData.selectRandomOptionFromDropdownWithHeading("Type of document lost or damaged");
            if (documentType.equalsIgnoreCase("OTHER")) {
                recordCaseData.enterTextIntoTextAreaWithHeading("Type of document lost or damaged (other)");
            }
        }
    }

    public void selectAction(String action) {
        recordCaseData.selectSpecificRadioButton(action);
    }
}