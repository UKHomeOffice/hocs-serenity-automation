package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.Workstacks;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;

public class QAResponse extends BasePage {

    @Managed

    Dashboard dashboard;

    CreateCase_SuccessPage createCaseSuccessPage;

    Workstacks workstacks;

    @FindBy(css = "label[for='QAResponseDecision-ACCEPT']")
    public WebElementFacade QAAcceptRadioButton;

    @FindBy(css = "label[for='QAResponseDecision-REJECT']")
    public WebElementFacade QARejectRadioButton;

    @FindBy(css = "label[for='QAResponseDecision-MODIFY']")
    public WebElementFacade QAModifyRadioButton;

    @FindBy(id ="CaseNote_QA")
    public WebElementFacade QARejectionNoteField;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade QADoYouApproveTheReponseErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade QAWhatIsYourFeedbackAboutTheResponseErrorMessage;


    // Basic Methods

    public void clickQAResponseAcceptRadioButton() {
        safeClickOn(QAAcceptRadioButton);
        System.out.println("Response accepted");
    }

    public void clickQAResponseRejectRadioButton() {
        safeClickOn(QARejectRadioButton);
        System.out.println("Response rejected");
    }

    public void clickQAResponseModifyRadioButton() {
        safeClickOn(QAModifyRadioButton);
        System.out.println("Modifying Response");
    }

    // Multi Step Methods

    public void rejectCaseWithoutReason() {
        clickQAResponseRejectRadioButton();
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }

    public void enterQARejectionNote() {
        String QARejectionNote = generateRandomString();
        typeInto(QARejectionNoteField, QARejectionNote);
        setSessionVariable("QARejectionNote").to(QARejectionNote);
    }

    public void qaResponseFullFlow() {
        dashboard.getAndClaimCurrentCase();
        clickQAResponseAcceptRadioButton();
        System.out.println("Finished QA Response, returning to home page.");
        clickContinueButton();
    }

    public void getToQAResponseFeedbackScreenPrerequisites() {
        safeClickOn(QARejectRadioButton);
        safeClickOn(continueButton);
    }

    public void moveTROCaseFromQAResponseToDispatch() {
        safeClickOn(QAAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void moveCaseFromQaResponseToPrivateOfficeApproval() {
        safeClickOn(QAAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void completeQAResponseStageAndStoreEnteredInformation() {
        safeClickOn(QAAcceptRadioButton);
        setSessionVariable("chosenQAResponse").to(QAAcceptRadioButton.getTextContent());
        safeClickOn(continueButton);
    }

    //Assertions

    public void assertQADoYouApproveErrorMessage() {
        QADoYouApproveTheReponseErrorMessage.shouldContainText("Do you approve the response? is required");
    }

    public void assertQAWhatIsYourFeedbackErrorMessage() {
        QAWhatIsYourFeedbackAboutTheResponseErrorMessage.shouldContainText("What is your feedback about the response? is required");
    }

}
