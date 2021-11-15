package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfirmationScreens extends BasePage {

    @FindBy(className = "govuk-panel__body")
    private WebElementFacade panelBody;

    @FindBy(className = "govuk-button-panel--link")
    public WebElementFacade caseReference;

    public void assertCaseCreatedConfirmationDisplayed() {
        panelBody.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        panelBody.shouldContainText("Case Created");
        caseReference.shouldBeCurrentlyVisible();
    }

    public void assertBulkCasesCreatedConfirmationDisplayed() {
        int numberOfCases = sessionVariableCalled("bulkCaseNumber");
        panelBody.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        panelBody.shouldContainText("Created " + numberOfCases + " new case");
    }

    public void assertCaseExtensionConfirmationDisplayed() {
        panelBody.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        panelBody.shouldContainText("Case: " + sessionVariableCalled("caseReference") + " extended");
    }

    public void assertAppealRegisteredConfirmationDisplayed() {
        panelBody.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        panelBody.shouldContainText("Appeal for " + sessionVariableCalled("caseReference") + " registered");
    }

    public void assertAppealUpdatedConfirmationDisplayed() {
        panelBody.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        panelBody.shouldContainText("Appeal for " + sessionVariableCalled("caseReference") + " updated");
    }

    public String storeCaseReference() {
        caseReference.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
        String caseReference = this.caseReference.getAttribute("value");
        System.out.println("Case created: " + caseReference);
        setSessionVariable("caseReference").to(caseReference);
        return caseReference;
    }

    public void goToCaseFromConfirmationScreen() {
        safeClickOn(caseReference);
    }

    public void goToCaseFromSuccessfulCreationScreen() {
        safeClickOn(caseReference);
    }
}
