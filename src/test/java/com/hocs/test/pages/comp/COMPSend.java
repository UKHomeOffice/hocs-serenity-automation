package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class COMPSend extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(id = "CctCaseOutcome")
    public WebElementFacade caseOutcomeDropdown;

    public void submitASelectedOutcome() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Case Outcome");
    }

    public void selectResponseChannel() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Response Channel");
    }
}