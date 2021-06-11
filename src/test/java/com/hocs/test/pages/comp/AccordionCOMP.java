package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccordionCOMP extends BasePage {

    @FindBy(xpath = "//button[text()='Registration']")
    public WebElementFacade registrationAccordionButton;

    @FindBy(xpath = "//button[text()='Service']")
    public WebElementFacade serviceAccordionButton;

    @FindBy(xpath = "//button[text()='Serious and Minor']")
    public WebElementFacade seriousAndMinorAccordionButton;

    @FindBy(xpath = "//button[text()='Serious']")
    public WebElementFacade seriousAccordionButton;

    @FindBy(xpath = "//label[text()='Delay']")
    public WebElementFacade delayCheckbox;

    @FindBy(xpath = "//strong[text()='Date of Birth']/parent::span")
    public WebElementFacade complainantDateOfBirth;

}
