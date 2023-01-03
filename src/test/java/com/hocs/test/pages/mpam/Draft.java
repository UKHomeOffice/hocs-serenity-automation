package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends BasePage {

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Move to QA']")
    public WebElementFacade moveToQARadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Ready for dispatch (bypass QA)']")
    public WebElementFacade readyForDispatchBypassQARadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Put on hold']")
    public WebElementFacade putOnHoldRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Close duplicate case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[text()='Change reference type']")
    public WebElementFacade changeReferenceTypeLink;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@value='Correction']")
    public WebElementFacade correctionTickBox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@name='RefType'][@checked]")
    public WebElementFacade selectedRefType;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade escalationReasonTextArea;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Return to Triage']")
    public WebElementFacade returnToTriageRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_DraftReturnToTriage")
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
