package com.hocs.test.pages.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccordionCOMP extends BasePage {

    @FindBy(xpath = "//button[text()='Registration']")
    public WebElementFacade registrationAccordionButton;

    @FindBy(xpath = "//button[text()='Service Triage']")
    public WebElementFacade serviceTriageAccordionButton;

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

    @FindBy(xpath = "//strong[contains(text(), 'Has Letter of Authority been received')]/parent::span")
    public WebElementFacade hasLOABeenReceivedResponse;

    @FindBy(xpath = "//strong[contains(text(), 'Date of Letter of Authority')]/parent::span")
    public WebElementFacade dateOfLOA;

    public void openAccordion(String accordion) {
        WebElementFacade selectedAccordion = null;
        switch (accordion.toUpperCase()) {
            case "REGISTRATION":
                selectedAccordion = registrationAccordionButton;
                break;
            case "SERVICE TRIAGE":
                selectedAccordion = serviceTriageAccordionButton;
                break;
            default:
                pendingStep(accordion + " is not defined within " + getMethodName());
        }
        safeClickOn(selectedAccordion);
        setSessionVariable("accordion").to(accordion.toUpperCase());
    }

    public void getQuestionResponse(String responseType) {
        String response = null;
        switch (responseType.toUpperCase()) {
            case "HAS LOA BEEN RECEIVED":
                String hasLOABeenReceivedFullLine = hasLOABeenReceivedResponse.getText();
                response = hasLOABeenReceivedFullLine.split(": ")[1];
                break;
            case "DATE OF LOA":
                String dateOfLOAFullLine = dateOfLOA.getText();
                response = dateOfLOAFullLine.split(": ")[1];
                break;
            default:
                pendingStep(responseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("response").to(response);
    }

    public void assertInputMatchesCaseDetailsResponse(String responseType) {
        String inputResponse = null;
        String displayedResponse;
        switch (responseType.toUpperCase()) {
            case "HAS LOA BEEN RECEIVED":
                inputResponse = sessionVariableCalled("loaReceived");
                break;
            case "DATE OF LOA":
                inputResponse = sessionVariableCalled("loaReceivedDate");
                break;
            default:
                pendingStep(responseType + " is not defined within " + getMethodName());
        }
        displayedResponse = sessionVariableCalled("response");
        assertThat(displayedResponse.contains(inputResponse), is(true));
    }
}
