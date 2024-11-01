package com.hocs.test.pages.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

public class Dispatch extends BasePage {

    RecordCaseData recordCaseData;

    public void enterDateOfDispatch() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date the final response was dispatched");
        recordCaseData.addValueToAssertSummary("Date the final response was dispatched",getTodaysDate());
    }

    public void selectAFinalDispatchChannel() {
        String finalResponseChannel = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Final response channel");
        recordCaseData.addValueToAssertSummary("Final response channel",finalResponseChannel);
    }
}
