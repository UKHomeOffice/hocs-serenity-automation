package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

public class CaseCreationStage extends BasePage {

    RecordCaseData recordCaseData;

    public void selectValidityOfRequest(String validity) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(validity, "Is this a valid request?");
    }

}
