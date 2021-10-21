package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintsSend extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(id = "CctCaseOutcome")
    public WebElementFacade caseOutcomeDropdown;

    public void selectACaseOutcome() {
        String caseOutcome = recordCaseData.selectRandomOptionFromDropdownWithHeading("Case Outcome");
        if (iedetCase() && caseOutcome.equalsIgnoreCase("Other")) {
            recordCaseData.enterTextIntoTextAreaWithHeading("Please provide further details");
        }
    }

    public void selectAResponseChannel() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Response Channel");
    }

    public void enterADateOfResponse() {
        String headerText = null;
        if (compCase() || comp2Case()) {
            headerText = "Date of Response";
        } else if (iedetCase()) {
            headerText = "Response date";
        }
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-1), headerText);
    }
}