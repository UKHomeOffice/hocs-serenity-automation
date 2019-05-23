package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.workstacks.Workstacks;
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

    WebDriver driver;

    Page page;

    Workstacks workstacks;

    @FindBy(className = "govuk-panel--confirmation")
    private WebElementFacade confirmationPanel;

    @FindBy(className = "govuk-panel__title")
    private WebElementFacade panelTitle;

    @FindBy(className = "govuk-panel__body")
    private WebElementFacade createdCaseMessage;

    @FindBy(className = "govuk-back-link")
    private WebElementFacade successBackButton;

    @FindBy(className = "govuk-button-panel--link")
    public WebElementFacade newCaseReference;

    public void clickSuccessfulCaseBackButton() {
        successBackButton.click();
    }

    public void assertCaseCreatedSuccess() {
        assertThat(panelTitle.getText(), is("Success"));
    }

    public String getCaseReference() {
        String caseReference = newCaseReference.getAttribute("value");
        System.out.println(caseReference);
        setSessionVariable("caseReference").to(caseReference);

        return caseReference;
    }

    public void allocateToMeViaSuccessfulCreationScreen() {
        clickOn(newCaseReference);
        clickOn(workstacks.allocateToMeButton);
    }

    public void selectCaseReferenceNumberViaLinkText() {
        String caseReferenceNumber
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebElement thisReference = getDriver().findElement(By.linkText(caseReferenceNumber));
        System.out.println(thisReference);
        Actions actions = new Actions (getDriver());
        actions.moveToElement(thisReference);
        actions.perform();
        sleep(1000);
        thisReference.click();
    }

    public void selectCaseReferenceNumberViaXpath() {
        WebElementFacade referenceElement = findAll("//a[text()='" + sessionVariableCalled("caseReference")
                + "']").get(0);
        waitFor(referenceElement).waitUntilClickable();
        javascriptScrollToElem(referenceElement);
        System.out.println(referenceElement);
        referenceElement.click();
    }

    public void selectCaseReferenceNumberViaXpathDoubleClick() {
        WebElementFacade referenceElement = findAll("//a[text()='" + sessionVariableCalled("caseReference")
                + "']").get(0);
        waitFor(referenceElement).waitUntilClickable();
//        sleep(500);
        System.out.println(referenceElement);
        referenceElement.click();
        referenceElement.click();
    }

    public void selectCaseReferenceNumberViaXpathStoreResultingElement(){
        WebElementFacade referenceElement = findAll("//a[text()='" + sessionVariableCalled("caseReference")
                + "']").get(0);
        waitFor(referenceElement).waitUntilClickable();
        sleep(500);
        javascriptScrollToElem(referenceElement);
        System.out.println(referenceElement);
        setSessionVariable("assertCase").to(referenceElement);
        referenceElement.click();
    }
}
