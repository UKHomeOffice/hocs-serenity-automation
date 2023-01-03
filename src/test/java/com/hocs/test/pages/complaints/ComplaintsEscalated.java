package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ComplaintsEscalated extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Return case to Triage']")
    public WebElementFacade returnCaseToTriageRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Case ready for drafting']")
    public WebElementFacade caseReadyForDraftingRadioButton;

    public void selectActionAtEscalatedStage(String action) {
        recordCaseData.selectSpecificRadioButton(action);
        waitForDECSPageWithTitle("Complaint Escalated");
        if (buttonIsCurrentlyVisible("Continue")) {
            clickContinueButton();
        } else {
            clickConfirmButton();
        }

    }
}
