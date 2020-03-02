package com.hocs.test.pages.dispatch;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.base_page.Page;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class Dispatch extends Page {

    @FindBy(css = "label[for='DispatchDecision-ACCEPT']")
    public WebElementFacade dispatchAcceptRadioButton;

    @FindBy(css = "label[for='DispatchDecision-REJECT']")
    public WebElementFacade dispatchRejectRadioButton;

    @FindBy(xpath = "//a[text()='Are you able to dispatch this? is required']")
    public WebElementFacade areYouAbleToDispatchErrorMessage;

    @FindBy(xpath = "//a[text()='Why are you unable to dispatch this? is required']")
    public WebElementFacade whyAreYouUnableToDispatchErrorMessage;

    @FindBy(xpath = "//textarea[@name='CaseNote_DispatchDecisionReject']")
    public WebElementFacade dispatchRejectNoteField;

    @FindBy(css = "input[id='ResponseChannel']")
    public WebElementFacade responseTypeField;

    public void enterTextInWhyAreYouUnableToDispatchTextbox() {
        typeInto(whyAreYouUnableToDispatchErrorMessage, "");
    }

    public void selectDispatchRejectButton() {
        clickOn(dispatchRejectRadioButton);
    }

    public void dispatchTheCase() {
        clickOn(dispatchAcceptRadioButton);
        clickOn(continueButton);
    }

    public void getToUnableToDispatchScreenPrerequisites() {
        clickOn(dispatchRejectRadioButton);
        clickOn(continueButton);
    }

    public void rejectCaseWithoutReason() {
        clickOn(dispatchRejectRadioButton);
        clickOn(continueButton);
        clickOn(finishButton);
    }

    public void enterDispatchRejectionNotes() {
        waitFor(dispatchRejectNoteField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        typeInto(dispatchRejectNoteField, rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void completeDispatchStageAndStoreEnteredInformation() {
        String chosenResponseMethod = responseTypeField.getValue();
        setSessionVariable("chosenResponseMethod").to(chosenResponseMethod);
        clickOn(dispatchAcceptRadioButton);
        String dispatchAbleDecision = dispatchAcceptRadioButton.getAttribute("for").substring(17);
        setSessionVariable("dispatchAbleDecision").to(dispatchAbleDecision);
        clickOn(continueButton);
    }

    //assertions

    public void assertAreYouAbleToDispatchErrorMessageIsShown() {
        areYouAbleToDispatchErrorMessage.shouldContainText("Are you able to dispatch this? is required");
    }

    public void assertWhyAreYouUnableToDispatchErrorMessageIsShown() {
        whyAreYouUnableToDispatchErrorMessage.shouldContainText("Why are you unable to dispatch this? is required");
    }
}
