package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CreateCaseSuccessPage extends BasePage {

    CaseView caseView;

    @FindBy(className = "govuk-panel__body")
    private WebElementFacade panelBody;

    @FindBy(className = "govuk-button-panel--link")
    public WebElementFacade newCaseReference;

    public void assertCaseCreatedSuccess() {
        panelBody.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        panelBody.shouldContainText("Case Created");
        newCaseReference.shouldBeCurrentlyVisible();
    }

    public void assertBulkCasesCreatedSuccess() {
        int numberOfCases = sessionVariableCalled("bulkCaseNumber");
        panelBody.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        panelBody.shouldContainText("Created " + numberOfCases + " new case");
    }

    public String storeCaseReference() {
        newCaseReference.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
        String caseReference = newCaseReference.getAttribute("value");
        System.out.println(caseReference + " is the case reference");
        setSessionVariable("caseReference").to(caseReference);
        return caseReference;
    }

    public void allocateToMeViaSuccessfulCreationScreen() {
        safeClickOn(newCaseReference);
        safeClickOn(caseView.allocateToMeLink);
    }

    public void goToCaseFromSuccessfulCreationScreen() {
        safeClickOn(newCaseReference);
    }
}