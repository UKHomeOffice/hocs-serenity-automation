package com.hocs.test.pages.mpam;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends BasePage {

    @FindBy(css = "label[for='ChannelOut-Email']")
    public WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade responseChannelLetterRadioButton;

    @FindBy(xpath = "//label[text()='Move to QA']")
    public WebElementFacade moveToQARadioButton;

    @FindBy(xpath = "//label[text()='Ready for dispatch (bypass QA)']")
    public WebElementFacade readyForDispatchBypassQARadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade putOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    public void moveCaseFromDraftToQA() {
        safeClickOn(responseChannelEmailRadioButton);
        safeClickOn(moveToQARadioButton);
        safeClickOn(confirmButton);
    }

    public void moveBRefCaseFromDraftToDispatch() {
        safeClickOn(responseChannelEmailRadioButton);
        safeClickOn(readyForDispatchBypassQARadioButton);
        safeClickOn(confirmButton);
    }

    public void escalateCaseToWorkflowManager() {
        safeClickOn(responseChannelLetterRadioButton);
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
    }

    public void putCaseOnHold() {
        safeClickOn(responseChannelLetterRadioButton);
        safeClickOn(putOnHoldRadioButton);
        safeClickOn(confirmButton);
    }
}
