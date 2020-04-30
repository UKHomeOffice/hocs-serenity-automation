package com.hocs.test.pages.dcu;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Workstacks;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Managed;
import net.serenitybdd.core.pages.WebElementFacade;

public class QAResponse extends BasePage {

    @Managed

    Homepage homepage;

    CreateCase_SuccessPage createCaseSuccessPage;

    Workstacks workstacks;

    @FindBy(css = "label[for='QAResponseDecision-ACCEPT']")
    public WebElementFacade QAAcceptRadioButton;

    @FindBy(css = "label[for='QAResponseDecision-REJECT']")
    public WebElementFacade QARejectRadioButton;

    @FindBy(id ="CaseNote_QA")
    public WebElementFacade QARejectionNoteField;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade QADoYouApproveTheReponseErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade QAWhatIsYourFeedbackAboutTheResponseErrorMessage;


    // Basic Methods

    public void clickQAResponseAcceptRadioButton() {
        QAAcceptRadioButton.click();
        System.out.println("Response accepted");
    }

    public void clickQAResponseRejectRadioButton() {
        QARejectRadioButton.click();
        System.out.println("Response rejected");
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
        homepage.selectPerformanceProcessTeam();
        createCaseSuccessPage.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
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
        String chosenQAResponse = QAAcceptRadioButton.getAttribute("for").substring(19);
        setSessionVariable("chosenQAResponse").to(chosenQAResponse);
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
