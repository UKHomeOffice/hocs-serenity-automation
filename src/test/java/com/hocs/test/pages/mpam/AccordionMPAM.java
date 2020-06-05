package com.hocs.test.pages.mpam;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccordionMPAM extends BasePage {

    @FindBy(xpath = "//button[text()= 'Case details']")
    public WebElementFacade caseDetailsAccordionButton;
}
