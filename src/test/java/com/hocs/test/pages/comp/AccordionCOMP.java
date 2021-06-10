package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccordionCOMP extends BasePage {

    @FindBy(xpath = "//button[text()='Registration']")
    public WebElementFacade registrationAccordionButton;

    @FindBy(xpath = "//strong[text()='Date of Birth']/parent::span")
    public WebElementFacade complainantDateOfBirth;

}
