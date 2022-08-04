package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintsDraft extends BasePage {

    Documents documents;

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[contains(text(),'Add a')]")
    public WebElementFacade addADocumentHypertext;

    @FindBy(xpath = "//label[text()='Response is ready to send']")
    public WebElementFacade responseIsReadyToSendRadioButton;

    @FindBy(xpath = "//label[text()='Send case to QA']")
    public WebElementFacade sendCaseToQARadioButton;

    @FindBy(xpath = "//label[text()='Escalate case to WFM']")
    public WebElementFacade escalateCaseToWFMRadioButton;

    @FindBy(id = "CaseNote_DraftEscalate")
    public WebElementFacade reasonForEscalationTextField;

    @FindBy(xpath = "//a[text()='Primary draft document is required']")
    public WebElementFacade primaryDraftDocumentIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Action is required']")
    public WebElementFacade actionIsRequired;

    @FindBy(xpath = "//input[@id='TelephoneResponse_Yes']/following-sibling::label[text()='Yes']")
    public WebElementFacade resolvedByTelephoneCheckbox;

    public void selectActionAtDraft(String action) {
        recordCaseData.selectSpecificRadioButton(action);
        if (pogrCase()) {
            safeClickOn(finishButton);
        } else {
            safeClickOn(continueButton);
        }
    }

    public void submitEscalationReason() {
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter reason for escalation");
        setSessionVariable("escalationReason").to(enteredText);
        if (pogrCase()) {
            safeClickOn(continueButton);
        } else {
            clickTheButton("Escalate case");
        }
    }

    public void assertErrorMessageIsDisplayed(String errorMessage) {
        if (errorMessage.equalsIgnoreCase("ACTION")) {
            actionIsRequired.shouldBeVisible();
        } else if (errorMessage.equalsIgnoreCase("PRIMARY DRAFT DOCUMENT")) {
            primaryDraftDocumentIsRequiredErrorMessage.shouldBeVisible();
        }
    }

    public void selectIfResolvedByPhoneCall(String yesNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesNo, "Was the case resolved by phone call?");
        setSessionVariable("resolvedByPhone").to(yesNo);
    }


    public void enterDateOfPhoneCall() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-1),"Date of Call");
        setSessionVariable("callDate").to(getDatePlusMinusNDaysAgo(-1));
    }

    public void enterDetailsOfPhoneCall() {
        String callDetails = recordCaseData.enterTextIntoTextAreaWithHeading("Details of Phone Call");
        setSessionVariable("callDetails").to(callDetails);
    }
}
