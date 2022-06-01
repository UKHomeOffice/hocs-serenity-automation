package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

public class Draft extends BasePage {

    RecordCaseData recordCaseData;

    public void selectADifferentBusinessUnitType() {
        String newBusinessUnitType = selectDifferentOptionFromDropdownWithHeading("Business Unit Type");
        setSessionVariable("businessUnitType").to(newBusinessUnitType);
    }

    public void enterReasonToReturnCasetoTriage() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why should this case be returned to Triage?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }
}
