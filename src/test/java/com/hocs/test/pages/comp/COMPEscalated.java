package com.hocs.test.pages.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class COMPEscalated extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[text()='Return case to Triage']")
    public WebElementFacade returnCaseToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Case ready for drafting']")
    public WebElementFacade caseReadyForDraftingRadioButton;

    public void selectActionAtServiceEscalated(String action) {
        switch (action.toUpperCase()) {
            case "RETURN CASE TO TRIAGE":
                recordCaseData.selectSpecificRadioButton("Return case to Triage");
                break;
            case "CASE READY FOR DRAFTING":
                recordCaseData.selectSpecificRadioButton("Case ready for drafting");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickTheButton("Confirm");
    }
}
