package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NoResponseNeededConfirmation extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//div[@id='NoReplyNeededConfirmation-radios']//label[text()='Yes']")
    public WebElementFacade noResponseNeededYesRadioButton;

    @FindBy(xpath = "//div[@id='NoReplyNeededConfirmation-radios']//label[text()='No']")
    public WebElementFacade noResponseNeededNoRadioButton;

    public void selectAgreeNoResponseNeeded() {
        recordCaseData.selectSpecificRadioButton("Yes");
    }

    public void selectDisagreeNoResponseNeeded() {
        recordCaseData.selectSpecificRadioButton("No");
    }
}
