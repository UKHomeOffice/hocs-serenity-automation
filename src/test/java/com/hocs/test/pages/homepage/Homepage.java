package com.hocs.test.pages.homepage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends Page {

    @Managed
    WebDriver driver;

    @FindBy(linkText = "Create single case")
    private WebElementFacade createSingleCase;

    @FindBy(linkText = "Create cases in bulk")
    private WebElementFacade createBulkCases;

    @FindBy(linkText = "View test form")
    private WebElementFacade testFormLink;

    @FindBy(className = "card__body")
    private WebElementFacade myWorkstacks;

    @FindBy(className = "govuk-table")
    private WebElementFacade workstackTable;

    @FindBy(className = "govuk-table__cell")
    private WebElementFacade workstackTableCell;

    @FindBy(xpath = "//td[text()='Data Input']/following-sibling::td/a")
    private WebElementFacade firstDataInput;

    @FindBy(xpath = "//td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstDataInputAllocate;

    @FindBy(xpath = "//td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Casework')]")
    private WebElementFacade firstDataInputCasework;

    @FindBy(xpath = "//td[contains(text(),'MIN')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[text()='Allocate']")
    private WebElementFacade firstMinDataInputAllocate;

    @FindBy(xpath = "//td[contains(text(),'MIN')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[text()='Casework']")
    private WebElementFacade firstMinDataInputCasework;

    @FindBy(xpath = "//td[contains(text(),'DTEN')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[text()='Allocate']")
    private WebElementFacade firstDtenDataInputAllocate;

    @FindBy(xpath = "//td[contains(text(),'DTEN')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[text()='Casework']")
    private WebElementFacade firstDtenDataInputCasework;

    @FindBy(xpath = "//td[contains(text(),'TRO')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[text()='Allocate']")
    private WebElementFacade firstTroDataInputAllocate;

    @FindBy(xpath = "//td[contains(text(),'TRO')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[text()='Casework']")
    private WebElementFacade firstTroDataInputCasework;

    @FindBy(xpath = "//td[text()='Data Input QA']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstDataInputQaAllocate;

    @FindBy(xpath = "//td[text()='Data Input QA']/following-sibling::td/a[contains(text(), 'Casework')]")
    private WebElementFacade firstDataInputQaCasework;

    @FindBy(xpath = "//td[text()='Markup']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstMarkupAllocate;

    @FindBy(xpath = "//td[text()='Markup']/following-sibling::td/a[contains(text(), 'Casework')]")
    private WebElementFacade firstMarkupCasework;

    @FindBy(xpath = "//td[contains(text(),'MIN')]/following-sibling::td[text()='Markup']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstMinMarkupAllocate;

    @FindBy(xpath = "//td[contains(text(),'DTEN')]/following-sibling::td[text()='Markup']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstDtenMarkupAllocate;

    @FindBy(xpath = "//td[contains(text(),'TRO')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstTroMarkupAllocate;

    public void assertCaseIsComplete() {
        WebElementFacade caseReference = (WebElementFacade) driver.findElement(
                By.xpath("//td[contains(text(), '" + sessionVariableCalled("caseId")
                        + "')]"));

        assertThat(isElementDisplayed(caseReference), is(false));
    }

    public void assertCaseStageInWorkstacks(String expectedStage, WebDriver driver) {

        String actualStage = driver.findElement(
                By.xpath("//td[contains(text(), '" + sessionVariableCalled("caseId")
                        + "')]/following-sibling::td[1]")).getText();
        assertThat(actualStage.toUpperCase(), is(expectedStage.toUpperCase()));
    }

    public void assertWorkstackTableContainsCaseReference() {
        assertThat(getWorktackTableContents(),
                hasItem(containsString(sessionVariableCalled("caseReference"))));
    }

    public void clickCreateBulkCases() {
        createBulkCases.click();
    }

    public void clickCreateSingleCase() {
        createSingleCase.click();
    }

    public void clickFirstDataInput() {
        firstDataInput.click();
    }

    public void clickFirstDataInputAllocate() {
        firstDataInputAllocate.click();
    }

    public void clickFirstDataInputCasework() {
        firstDataInputCasework.click();
    }

    public void clickFirstDtenDataInputAllocate() {
        firstDtenDataInputAllocate.click();
    }

    public void clickFirstDtenDataInputCasework() {
        firstDtenDataInputCasework.click();
    }

    public void clickFirstMinDataInputAllocate() {
        firstMinDataInputAllocate.click();
    }

    public void clickFirstMinDataInputCasework() {
        firstMinDataInputCasework.click();
    }

    public void clickFirstTroDataInputAllocate() {
        firstTroDataInputAllocate.click();
    }

    public void clickFirstTroDataInputCasework() {
        firstTroDataInputCasework.click();
    }

    public void clickFirstDataInputQaAllocate() {
        firstDataInputQaAllocate.click();
    }

    public void clickFirstDataInputQaCasework() {
        firstDataInputQaCasework.click();
    }

    public void clickFirstMarkupAllocate() {
        firstMarkupAllocate.click();
    }

    public void clickFirstMarkupCasework() {
        firstMarkupCasework.click();
    }

    public void clickFirstMinMarkupAllocate() {
        firstMinMarkupAllocate.click();
    }

    public void clickFirstDtenMarkupAllocate() {
        firstDtenMarkupAllocate.click();
    }

    public void clickFirstTroMarkupAllocate() {
        firstTroMarkupAllocate.click();
    }

    public void clickMyWorkstack() {
        myWorkstacks.click();
    }

    public void clickTestFormLink() {
        testFormLink.click();
    }

    private List<Map<Object, String>> getWorktackTableContents() {
        return rowsFrom(workstackTable);
    }

    public void assertPageTitle() {
        assertTitle("Main");
    }

}
