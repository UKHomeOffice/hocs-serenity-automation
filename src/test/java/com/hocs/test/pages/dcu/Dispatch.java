package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.decs.BasePage;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class Dispatch extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//div[@id='DispatchDecision-radios']//label[text()='Yes']")
    public WebElementFacade dispatchAcceptRadioButton;

    @FindBy(xpath = "//div[@id='DispatchDecision-radios']//label[text()='No']")
    public WebElementFacade dispatchRejectRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_DispatchDecisionReject']")
    public WebElementFacade dispatchRejectNoteField;

    @FindBy(css = "input[id='ResponseChannel']")
    public WebElementFacade responseTypeField;

    public void selectAbleToDispatch(String yesNo) {
        recordCaseData.selectSpecificRadioButton(yesNo);
    }

    public void enterReasonUnableToDispatch() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why are you unable to dispatch this?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }
}
