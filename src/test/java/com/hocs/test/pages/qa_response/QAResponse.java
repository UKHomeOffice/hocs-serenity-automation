package com.hocs.test.pages.qa_response;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.pages.WebElementFacade;

public class QAResponse extends Page{

    @Managed
    WebDriver driver;

    @FindBy(id = "QAResponseDecision-ACCEPT")
    private WebElementFacade QAAcceptRadioButton;

    @FindBy(id = "QAResponseDecision-REJECT")
    private WebElementFacade QARejectRadioButton;

    // Basic Methods

    public void clickQAResponseAcceptRadioButton() {
        QAAcceptRadioButton.click();
    }

    public void clickQAResponseRejectRadioButton() {
        QARejectRadioButton.click();
    }


    // Multi Step Methods

    // Assertions


}
