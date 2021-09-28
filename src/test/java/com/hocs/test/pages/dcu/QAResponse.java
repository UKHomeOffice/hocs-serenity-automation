package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class QAResponse extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[text()='Approve primary draft']")
    public WebElementFacade QAAcceptRadioButton;

    @FindBy(xpath = "//label[text()='Return case to drafting team']")
    public WebElementFacade QARejectRadioButton;

    @FindBy(xpath = "//label[text()='Modify primary draft']")
    public WebElementFacade QAModifyRadioButton;

    @FindBy(id ="CaseNote_QA")
    public WebElementFacade QARejectionNoteField;

    // Basic Methods

    public void selectApprovePrimaryDraftRadioButton() {
        recordCaseData.selectSpecificRadioButton("Approve primary draft");
    }

    public void selectReturnCaseToDraftingTeamRadioButton() {
        recordCaseData.selectSpecificRadioButton("Return case to drafting team");
    }

    public void selectModifyPrimaryDraftRadioButton() {
        recordCaseData.selectSpecificRadioButton("Modify primary draft");
    }

    public void enterRejectionReason() {
        String rejectionNote = recordCaseData.enterTextIntoTextAreaWithHeading("What is your feedback about the response?");
        setSessionVariable("QARejectionNote").to(rejectionNote);
    }
}
