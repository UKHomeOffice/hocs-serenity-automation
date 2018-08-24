package com.hocs.test.pages.homepage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import com.hocs.test.pages.Page;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
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

    public void assertWorkstackTableContainsCaseReference() {
        assertThat(getWorktackTableContents(),
                hasItem(containsString(sessionVariableCalled("caseReference"))));
    }

    public void clickCreateBulkCases() { createBulkCases.click(); }

    public void clickCreateSingleCase() {
        createSingleCase.click();
    }

    public void clickTestFormLink() {
        testFormLink.click();
    }

    public List<String> getWorktackTableContents() {
        return workstackTable.findElements(By.tagName("td")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        assertThat(getHeaderText(), is("Main"));
    }

}
