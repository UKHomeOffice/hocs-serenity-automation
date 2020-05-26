package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccordionUKVI extends BasePage {

    @FindBy(xpath = "//button[text()= 'Case details']")
    public WebElementFacade caseDetailsAccordionButton;
}
