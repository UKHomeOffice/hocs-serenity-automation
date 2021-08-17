package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

public class FOIDispatch extends BasePage {

    RecordCaseData recordCaseData;

    public void selectDoYouWantToDispatch(String response) {
        if (response.equalsIgnoreCase("YES")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Are you sure you want to dispatch this case?");
        } else if (response.equalsIgnoreCase("NO")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No", "Are you sure you want to dispatch this case?");
        }
    }
}
