package com.hocs.test.pages.qa_response;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Managed;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QAResponse extends Page{

    @Managed

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

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
        clickOn(continueButton);
        clickOn(finishButton);
    }

    public void enterQARejectionNote() {
        String QARejectionNote = generateRandomString();
        typeInto(QARejectionNoteField, QARejectionNote);
        setSessionVariable("QARejectionNote").to(QARejectionNote);
    }

    public void qaResponseFullFlow() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        clickQAResponseAcceptRadioButton();
        System.out.println("Finished QA Response, returning to home page.");
        clickContinueButton();
    }

    public void getToQAResponseFeedbackScreenPrerequisites() {
        clickOn(QARejectRadioButton);
        clickOn(continueButton);
    }

    public void moveTROCaseFromQAResponseToDispatch() {
        clickOn(QAAcceptRadioButton);
        clickOn(continueButton);
    }

    public void moveCaseFromQaResponseToPrivateOfficeApproval() {
        clickOn(QAAcceptRadioButton);
        clickOn(continueButton);
    }

    public void completeQAResponseStageAndStoreEnteredInformation() {
        clickOn(QAAcceptRadioButton);
        String chosenQAResponse = QAAcceptRadioButton.getAttribute("for").substring(19);
        setSessionVariable("chosenQAResponse").to(chosenQAResponse);
        clickOn(continueButton);
    }

    //Assertions

    public void assertQADoYouApproveErrorMessage() {
        assertThat(QADoYouApproveTheReponseErrorMessage.getText(), is("Do you approve the response? is required"));
    }

    public void assertQAWhatIsYourFeedbackErrorMessage() {
        assertThat(QAWhatIsYourFeedbackAboutTheResponseErrorMessage.getText(),
                is("What is your feedback about the response? is required"));
    }

}
