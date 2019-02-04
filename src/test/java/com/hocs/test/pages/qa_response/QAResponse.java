package com.hocs.test.pages.qa_response;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import net.serenitybdd.core.Serenity;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.pages.WebElementFacade;

public class QAResponse extends Page{

    @Managed
    WebDriver driver;

    Homepage homepage;

    @FindBy(css = "label[for='QAResponseDecision-ACCEPT']")
    private WebElementFacade QAAcceptRadioButton;

    @FindBy(css = "label[for='QAResponseDecision-REJECT']")
    private WebElementFacade QARejectRadioButton;

    @FindBy(id = "CaseNote_RejectionNote")
    private WebElementFacade draftDecisionNoteField;

    @FindBy(id ="CaseNote_QA")
    private WebElementFacade draftDecisionNoteFieldTheSecond;


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

}
