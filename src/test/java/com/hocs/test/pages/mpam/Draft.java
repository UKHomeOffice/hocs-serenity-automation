package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends BasePage {

    @FindBy(xpath = "//label[text()='Move to QA']")
    public WebElementFacade moveToQARadioButton;

    @FindBy(xpath = "//label[text()='Ready for dispatch (bypass QA)']")
    public WebElementFacade readyForDispatchBypassQARadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade putOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    @FindBy(xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    @FindBy(xpath = "//label[text()='Close duplicate case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(xpath = "//a[text()='Change reference type']")
    public WebElementFacade changeReferenceTypeLink;

    @FindBy(xpath = "//input[@value='Correction']")
    public WebElementFacade correctionTickBox;

    @FindBy(xpath = "//input[@name='RefType'][@checked]")
    public WebElementFacade selectedRefType;

    @FindBy(id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade escalationReasonTextArea;

    @FindBy(xpath = "//label[text()='Return to Triage']")
    public WebElementFacade returnToTriageRadioButton;

    @FindBy(id = "CaseNote_DraftReturnToTriage")
    public WebElementFacade returnToTriageReasonTextArea;

    public void moveBRefCaseFromDraftToDispatch() {
        safeClickOn(readyForDispatchBypassQARadioButton);
        clickConfirmButton();
    }

    public void selectEscalateDraftCaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        setSessionVariable("action").to("Escalate to workflow manager");
        clickConfirmButton();
    }

    public void submitReasonToEscalateCase(String escalationReason) {
        escalationReasonTextArea.sendKeys(escalationReason);
        clickConfirmButton();
        setSessionVariable("escalationReason").to(escalationReason);
    }

    public void putCaseOnHold() {
        safeClickOn(putOnHoldRadioButton);
        setSessionVariable("action").to("Put on hold");
        clickConfirmButton();
    }

    public void takeDraftCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        clickConfirmButton();
    }

    public void deescalateDraftCase() {
        safeClickOn(escalationCompleteRadioButton);
        clickConfirmButton();
    }

    public void selectToCloseEscalatedCase() {
        safeClickOn(closeCaseRadioButton);
        clickConfirmButton();
    }

    public void sendDraftCaseBackToTriage() {
        safeClickOn(returnToTriageRadioButton);
        clickConfirmButton();
        returnToTriageReasonTextArea.sendKeys("Test");
        setSessionVariable("rejectionReason").to("Test");
        setSessionVariable("rejectionStage").to("Draft");
        clickConfirmButton();
    }
}
