package com.hocs.test.pages.dcu;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TransferConfirmation extends BasePage {

    @FindBy(xpath = "//div[@id='TransferConfirmation-radios']//label[text()='Yes']")
    public WebElementFacade transferCaseYesRadioButton;

    @FindBy(xpath = "//div[@id='TransferConfirmation-radios']//label[text()='No']")
    public WebElementFacade transferCaseNoRadioButton;

    @FindBy(xpath = "//a[@href='#TransferConfirmation-error']")
    public WebElementFacade shouldCaseBeTransferIsRequiredErrorMessage;

    public void assertShouldCaseBeTransferredIsRequiredErrorMessage() {
        shouldCaseBeTransferIsRequiredErrorMessage.shouldContainText("Should this case be transferred to the OGD? is required");
    }

}
