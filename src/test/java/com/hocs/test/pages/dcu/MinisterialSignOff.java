package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class MinisterialSignOff extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='MinisterSignOffDecision-radios']//label[text()='Yes']")
    public WebElementFacade ministerSignOffAcceptRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='MinisterSignOffDecision-radios']//label[text()='No']")
    public WebElementFacade ministerSignOffRejectRadioButton;

    @FindBy(timeoutInSeconds = "10", id = "CaseNote_MinisterReject")
    public WebElementFacade ministerRejectionNote;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Not applicable']")
    public WebElementFacade notApplicableRadioButton;

    @FindBy(timeoutInSeconds = "10", id = "CaseNote_MinisterNotApplicable")
    public WebElementFacade whyIsCaseNotApplicableFreeTextField;

    // Basic Methods

    public void selectToApproveResponse(String yesNo) {
        recordCaseData.selectSpecificRadioButton(yesNo);
    }

    public void selectNotApplicableToApproveResponse() {
        recordCaseData.selectSpecificRadioButton("Not applicable");
    }

    public void enterRejectionReason() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("What is your feedback about the response?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void enterNotApplicableReason() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why is this case not applicable?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }
}
