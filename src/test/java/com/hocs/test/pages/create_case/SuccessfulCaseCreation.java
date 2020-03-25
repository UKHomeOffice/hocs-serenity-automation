package com.hocs.test.pages.create_case;

import com.hocs.test.pages.base_page.Page;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.workstacks.Workstacks;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SuccessfulCaseCreation extends Page {

    @Managed

    Workstacks workstacks;

    @FindBy(className = "govuk-panel__title")
    private WebElementFacade panelTitle;

    @FindBy(className = "govuk-back-link")
    private WebElementFacade successBackButton;

    @FindBy(className = "govuk-button-panel--link")
    public WebElementFacade newCaseReference;

    public void clickSuccessfulCaseBackButton() {
        clickOn(successBackButton);
    }

    public void assertCaseCreatedSuccess() {
        panelTitle.shouldContainText("Success");
    }

    public void assertBulkCasesCreatedSuccess() {
        waitABit(3000);
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
        clickOn(newCaseReference);
        clickOn(workstacks.allocateToMeButton);
    }

    public void goToCaseFromSuccessfulCreationScreen() {
        clickOn(newCaseReference);
    }

    public void selectCaseReferenceNumberViaXpath() {
        WebElementFacade referenceElement = findAll("//a[text()='" + sessionVariableCalled("caseReference")
                + "']").get(0);
        waitFor(referenceElement).waitUntilClickable();
        javascriptScrollToElem(referenceElement);
        System.out.println(referenceElement);
        referenceElement.click();
    }
}
