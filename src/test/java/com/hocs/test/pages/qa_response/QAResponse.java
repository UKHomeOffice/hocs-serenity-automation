package com.hocs.test.pages.qa_response;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import net.serenitybdd.core.Serenity;
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


    // Basic Methods

    public void clickQAResponseAcceptRadioButton() {
        QAAcceptRadioButton.click();
    }

    public void clickQAResponseRejectRadioButton() {
        QARejectRadioButton.click();
    }


    // Multi Step Methods

    public void enterDraftDecision() {
        String draftDecisionNote = generateRandomString();
        draftDecisionNoteField.clear();
        draftDecisionNoteField.sendKeys(draftDecisionNote);
        Serenity.setSessionVariable("draftDecisionNote").to(draftDecisionNote);
    }

    
    // Assertions

    public void findAndAssertMyInitialDraftCase(){
        String thisCaseType =
                Serenity.sessionVariableCalled("caseType").toString();
        if(thisCaseType.equals("DCU MIN")) {
            System.out.println("DCUMIN identified, selecting team 1");
            homepage.selectTeam1();
            homepage.assertCaseStageInWorkstacks("Initial Draft", driver);
        } else if (thisCaseType.equals("DCU TRO")) {
            System.out.println("DCUTRO identified, selecting team 3");
            homepage.selectTeam3();
            homepage.assertCaseStageInWorkstacks("Initial Draft", driver);
        } else if (thisCaseType.equals("DCU N10")) {
            System.out.println("DCUN10 identified, selecting team 2");
            homepage.selectTeam3();
            homepage.assertCaseStageInWorkstacks("Initial Draft", driver);
        } else {
            System.out.println("The case could now be found");
        }
    }
}
