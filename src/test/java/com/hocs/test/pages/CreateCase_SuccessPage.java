package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CreateCase_SuccessPage extends BasePage {

    Workstacks workstacks;

    @FindBy(className = "govuk-panel__title")
    private WebElementFacade panelTitle;

    @FindBy(id = "submit")
    public WebElementFacade newCaseReference;

    public void assertCaseCreatedSuccess() {
        panelTitle.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
        panelTitle.shouldContainText("Success");
        newCaseReference.shouldBeCurrentlyVisible();
    }

    public void assertBulkCasesCreatedSuccess() {
        panelTitle.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        panelTitle.shouldContainText("Success");
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
