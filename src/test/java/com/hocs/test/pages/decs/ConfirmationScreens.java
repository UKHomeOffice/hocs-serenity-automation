package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfirmationScreens extends BasePage {

    @FindBy(className = "govuk-panel__title")
    private WebElementFacade panelTitle;

    @FindBy(className = "govuk-button-panel--link")
    public WebElementFacade caseReference;

    public void goToCaseFromConfirmationScreen() {
        safeClickOn(caseReference);
    }

    public String storeCaseReference() {
        caseReference.waitUntilVisible();
        String caseReference = this.caseReference.getAttribute("value");
        System.out.println("Case created: " + caseReference);
        setSessionVariable("caseReference").to(caseReference);
        return caseReference;
    }

    public void assertCaseCreatedConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Case Created");
        caseReference.shouldBeCurrentlyVisible();
    }

    public void assertBulkCasesCreatedConfirmationDisplayed() {
        int numberOfCases = sessionVariableCalled("bulkCaseNumber");
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Created " + numberOfCases + " new case");
    }

    public void assertCaseExtensionConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Case: " + getCurrentCaseReference() + " extended");
    }

    public void assertAppealRegisteredConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Appeal for " + getCurrentCaseReference() + " registered");
    }

    public void assertAppealUpdatedConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Appeal for " + getCurrentCaseReference() + " updated");
    }

    public void assertExternalInterestRegisteredConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("External Interest for " + getCurrentCaseReference() + " registered");
    }

    public void assertExternalInterestUpdatedConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("External Interest for " + getCurrentCaseReference() + " updated");
    }

    public void assertCaseSuspendedConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Case " + getCurrentCaseReference() + " has been suspended");
    }

    public void assertCaseSuspensionRemovedConfirmationDisplayed() {
        panelTitle.waitUntilVisible();
        panelTitle.shouldContainText("Suspension for case " + getCurrentCaseReference() + " has been removed");
    }
}
