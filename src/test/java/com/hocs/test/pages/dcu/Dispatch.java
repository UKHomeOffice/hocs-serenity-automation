package com.hocs.test.pages.dcu;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.BasePage;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class Dispatch extends BasePage {

    @FindBy(xpath = "//div[@id='DispatchDecision-radios']//label[text()='Yes']")
    public WebElementFacade dispatchAcceptRadioButton;

    @FindBy(xpath = "//div[@id='DispatchDecision-radios']//label[text()='No']")
    public WebElementFacade dispatchRejectRadioButton;

    @FindBy(xpath = "//a[text()='Are you able to dispatch this? is required']")
    public WebElementFacade areYouAbleToDispatchErrorMessage;

    @FindBy(xpath = "//a[text()='Why are you unable to dispatch this? is required']")
    public WebElementFacade whyAreYouUnableToDispatchErrorMessage;

    @FindBy(xpath = "//textarea[@name='CaseNote_DispatchDecisionReject']")
    public WebElementFacade dispatchRejectNoteField;

    @FindBy(css = "input[id='ResponseChannel']")
    public WebElementFacade responseTypeField;

    public void moveCaseFromDispatchToCaseClosed() {
        safeClickOn(dispatchAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void selectDispatchRejectButton() {
        safeClickOn(dispatchRejectRadioButton);
    }

    public void dispatchTheCase() {
        safeClickOn(dispatchAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void getToUnableToDispatchScreenPrerequisites() {
        safeClickOn(dispatchRejectRadioButton);
        safeClickOn(continueButton);
    }

    public void rejectCaseWithoutReason() {
        safeClickOn(dispatchRejectRadioButton);
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }

    public void enterDispatchRejectionNotes() {
        waitFor(dispatchRejectNoteField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        dispatchRejectNoteField.sendKeys(rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void completeDispatchStageAndStoreEnteredInformation() {
        String chosenResponseMethod = responseTypeField.getValue();
        setSessionVariable("chosenResponseMethod").to(chosenResponseMethod);
        safeClickOn(dispatchAcceptRadioButton);
        String dispatchAbleDecision = dispatchAcceptRadioButton.getAttribute("for").substring(17);
        setSessionVariable("dispatchAbleDecision").to(dispatchAbleDecision);
        safeClickOn(continueButton);
    }

    //assertions

    public void assertAreYouAbleToDispatchErrorMessageIsShown() {
        areYouAbleToDispatchErrorMessage.shouldContainText("Are you able to dispatch this? is required");
    }

    public void assertWhyAreYouUnableToDispatchErrorMessageIsShown() {
        whyAreYouUnableToDispatchErrorMessage.shouldContainText("Why are you unable to dispatch this? is required");
    }
}
