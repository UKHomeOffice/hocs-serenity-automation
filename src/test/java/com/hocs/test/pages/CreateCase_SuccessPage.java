package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CreateCase_SuccessPage extends BasePage {

    Workstacks workstacks;

    @FindBy(className = "govuk-panel__body")
    private WebElementFacade panelBody;

    @FindBy(className = "govuk-button-panel--link")
    public WebElementFacade newCaseReference;

    public void assertCaseCreatedSuccess() {
        panelBody.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
        panelBody.shouldContainText("Case Created");
        newCaseReference.shouldBeCurrentlyVisible();
    }

    public void assertBulkCasesCreatedSuccess() {
        panelBody.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        panelBody.shouldContainText("Case Created");
    }

    public String getCaseReference() {
        newCaseReference.waitUntilVisible();
        String caseReference = newCaseReference.getAttribute("value");
        System.out.println(caseReference + " is the case reference");
        setSessionVariable("caseReference").to(caseReference);
        return caseReference;
    }

    public void allocateToMeViaSuccessfulCreationScreen() {
        safeClickOn(newCaseReference);
        safeClickOn(workstacks.allocateToMeButton);
    }

    public void goToCaseFromSuccessfulCreationScreen() {
        safeClickOn(newCaseReference);
    }
}
