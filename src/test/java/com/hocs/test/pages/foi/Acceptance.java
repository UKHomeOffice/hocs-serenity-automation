package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class Acceptance extends BasePage {

    RecordCaseData recordCaseData;

    public void selectIfCaseIsInCorrectDirectorate(String input) {
        if (input.equalsIgnoreCase("YES")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Does this case belong to your Directorate?");
        } else if (input.equalsIgnoreCase("NO")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No", "Does this case belong to your Directorate?");
        }
    }

    public void selectDraftTeam() {
        String draftTeam = recordCaseData.selectRandomOptionFromDropdownWithHeading("Please select the team required for drafting the response");
        setSessionVariable("selectedDraftTeam").to(draftTeam);
    }

    public void enterRejectionReason() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Rejection Reason", "Reason");
        setSessionVariable("rejectionReason").to("Test Rejection Reason");
    }
}
