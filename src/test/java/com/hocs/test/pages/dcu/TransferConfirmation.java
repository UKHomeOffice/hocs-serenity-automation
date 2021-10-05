package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TransferConfirmation extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//div[@id='TransferConfirmation-radios']//label[text()='Yes']")
    public WebElementFacade transferCaseYesRadioButton;

    @FindBy(xpath = "//div[@id='TransferConfirmation-radios']//label[text()='No']")
    public WebElementFacade transferCaseNoRadioButton;

    public void selectCaseShouldBeTransferred() {
        recordCaseData.selectSpecificRadioButton("Yes");
    }

    public void selectCaseShouldNotBeTransferred() {
        recordCaseData.selectSpecificRadioButton("No");
    }
}
