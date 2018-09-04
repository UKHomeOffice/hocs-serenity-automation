package com.hocs.test.pages.homepage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.thucydides.core.pages.components.HtmlTable.filterRows;
import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import com.hocs.test.pages.Page;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.matchers.BeanMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage extends Page {

    @Managed
    WebDriver driver;

    @FindBy(linkText = "Create single case")
    private WebElementFacade createSingleCase;

    @FindBy(linkText = "Create cases in bulk")
    private WebElementFacade createBulkCases;

    @FindBy(linkText = "View test form")
    private WebElementFacade testFormLink;

    @FindBy(className = "govuk-table")
    private WebElementFacade workstackTable;

    @FindBy(className = "govuk-table__cell")
    private WebElementFacade workstackTableCell;

    @FindBy(xpath = "//td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstDataInputAllocate;

    @FindBy(xpath = "//td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Casework')]")
    private WebElementFacade firstDataInputCasework;

    @FindBy(xpath = "//td[contains(text(),'MIN')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstMinDataInputAllocate;

    @FindBy(xpath = "//td[contains(text(),'DTEN')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstDtenDataInputAllocate;

    @FindBy(xpath = "//td[contains(text(),'TRO')]/following-sibling::td[text()='Data Input']/following-sibling::td/a[contains(text(), 'Allocate')]")
    private WebElementFacade firstTroDataInputAllocate;

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

    public void clickFirstDataInputAllocate() {
        firstDataInputAllocate.click();
    }

    public void clickFirstDataInputCasework() { firstDataInputCasework.click(); }

    public void clickTestFormLink() {
        testFormLink.click();
    }

    public void selectCaseTypeFromWorkstacks(String caseType, BeanMatcher... matchers) {
        List<WebElement> matchingRows = filterRows(workstackTable, matchers);
        WebElement targetRow = matchingRows.get(0);
        WebElement detailsLink = targetRow.findElement(
                By.xpath("//td[contains(text(), '" + caseType
                        + "')]/following-sibling::td/a[contains(text(), 'Allocate')]"));
        detailsLink.click();
    }

    public void selectCaseTypeAndStageFromWorkstacks(String stage, BeanMatcher... matchers) {
        List<WebElement> matchingRows = filterRows(workstackTable, matchers);
        WebElement targetRow = matchingRows.get(0);
        WebElement detailsLink = targetRow.findElement(By.xpath(
                "//td[text(), '" + stage + "']/following-sibling::td[contains(text(), '"
                        + stage + "')]/following-sibling::td/a[contains(text(), 'Allocate')]"));
        detailsLink.click();
    }

    public void selectStageFromWorkstacks(String stage, BeanMatcher... matchers) {
        List<WebElement> matchingRows = filterRows(workstackTable, matchers);
        WebElement targetRow = matchingRows.get(0);
        WebElement detailsLink = targetRow.findElement(
                By.xpath("//td[text()='" + stage
                        + "']/following-sibling::td/a[contains(text(), 'Allocate')]"));
        detailsLink.click();
    }

    public List<Map<Object, String>> getWorktackTableContents() {
        return rowsFrom(workstackTable);
    }

    public void assertPageTitle() {
        assertTitle("Main");
    }

}
