package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ServiceSend extends BasePage {

    @FindBy(id = "CctCaseOutcome")
    public WebElementFacade caseOutcomeDropdown;

    @FindBy(xpath = "//input[@value='Complete']")
    public WebElementFacade completeButton;

    public void moveCaseFromServiceSendToComplaintClosed() {
        caseOutcomeDropdown.selectByVisibleText("Upheld");
        safeClickOn(completeButton);
    }
}