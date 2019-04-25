package com.hocs.test.pages.qa_response;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QAResponse extends Page{

    @Managed
    WebDriver driver;

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    @FindBy(css = "label[for='QAResponseDecision-ACCEPT']")
    public WebElementFacade QAAcceptRadioButton;

    @FindBy(css = "label[for='QAResponseDecision-REJECT']")
    public WebElementFacade QARejectRadioButton;

    @FindBy(id = "CaseNote_RejectionNote")
    public WebElementFacade draftDecisionNoteField;

    @FindBy(id ="CaseNote_QA")
    public WebElementFacade draftDecisionNoteFieldTheSecond;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade QADoYouApproveTheReponseErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade QAWhatIsYourFeedbackAboutTheResponseErrorMessage;


    // Basic Methods

    public void clickQAResponseAcceptRadioButton() {
        QAAcceptRadioButton.click();
    }

    public void clickQAResponseRejectRadioButton() {
        QARejectRadioButton.click();
        System.out.println("Case rejected, entering decision note");
    }


    // Multi Step Methods

    public void enterDraftDecision() {
        String draftDecisionNote = generateRandomString();
        draftDecisionNoteFieldTheSecond.clear();
        draftDecisionNoteFieldTheSecond.sendKeys(draftDecisionNote);
        setSessionVariable("draftDecisionNote").to(draftDecisionNote);
    }

    public void qaResponseFullFlow() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        clickQAResponseAcceptRadioButton();
        System.out.println("Finished QA Response, returning to home page.");
        clickContinueButton();
    }

    public void assertQADoYouApproveErrorMessage() {
        assertThat(QADoYouApproveTheReponseErrorMessage.getText(), is("Do you approve the response? is required"));
    }

    public void assertQAWhatIsYourFeedbackErrorMessage() {
        assertThat(QAWhatIsYourFeedbackAboutTheResponseErrorMessage.getText(), is("What is your feedback about the response? is required"));
    }

    public void moveCaseFromQaResponseToPrivateOfficeApproval() {
        clickQAResponseAcceptRadioButton();
        clickContinueButton();
    }

}
