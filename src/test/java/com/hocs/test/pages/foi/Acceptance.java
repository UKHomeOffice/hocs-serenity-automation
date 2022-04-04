package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class Acceptance extends BasePage {

    RecordCaseData recordCaseData;

    public void selectIfCaseIsInCorrectGroup(String input) {
        if (input.equalsIgnoreCase("YES")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Does this case belong in your group?");
        } else if (input.equalsIgnoreCase("NO")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No", "Does this case belong in your group?");
        }
    }

    public void enterRejectionReason() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Rejection Reason", "Reason");
        setSessionVariable("rejectionReason").to("Test Rejection Reason");
    }

    public void selectAResponsibleTeam() {
        setSessionVariable("responsibleTeam").to(recordCaseData.selectRandomOptionFromDropdownWithHeading("Responsible Team"));
        clickTheButton("Complete Acceptance");
    }
}
