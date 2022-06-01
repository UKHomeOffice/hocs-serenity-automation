package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

public class QA extends BasePage {

    RecordCaseData recordCaseData;

    public void enterARejectionReason() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why are you rejecting the case?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }
}
