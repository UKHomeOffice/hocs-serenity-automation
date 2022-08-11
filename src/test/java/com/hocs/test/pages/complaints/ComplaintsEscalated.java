package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ComplaintsEscalated extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[text()='Return case to Triage']")
    public WebElementFacade returnCaseToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Case ready for drafting']")
    public WebElementFacade caseReadyForDraftingRadioButton;

    public void selectActionAtEscalatedStage(String action) {
        recordCaseData.selectSpecificRadioButton(action);
        if (continueButton.isVisible()) {
            safeClickOn(continueButton);
        } else {
            safeClickOn(confirmButton);
        }

    }
}
