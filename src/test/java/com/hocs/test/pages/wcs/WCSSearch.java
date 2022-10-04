package com.hocs.test.pages.wcs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.Workstacks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WCSSearch extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    SummaryTab summaryTab;

    @FindBy(xpath = "//input[@id='reference']")
    public WebElementFacade referenceTextbox;

    @FindBy(xpath = "//input[@id='claimantName']")
    public WebElementFacade claimantNameTextbox;

    @FindBy(xpath = "//input[@id='claimantDOB-day']")
    public WebElementFacade claimantDOBDayTextbox;

    @FindBy(xpath = "//input[@id='claimantDOB-month']")
    public WebElementFacade claimantDOBMonthTextbox;

    @FindBy(xpath = "//input[@id='claimantDOB-year']")
    public WebElementFacade claimantDOBYearTextbox;

    @FindBy(xpath = "//input[@id='niNumber']")
    public WebElementFacade nationalInsuranceNoTextbox;

    @FindBy(xpath = "//input[@id='PrevHocsRef']")
    public WebElementFacade prevHocsRefTextbox;

    @FindBy(css = "tr:first-child a[class*='govuk-link']")
    public WebElementFacade topSearchResultCaseReference;

    @FindBy(css = "div span[class='govuk-hint']")
    public WebElementFacade numberOfSearchResults;

    @FindBy(xpath = "//a[text()='No search criteria specified']")
    public WebElementFacade noSearchCriteriaErrorMessage;

    public void searchByWCSReference(String reference) {
        referenceTextbox.click();
        typeInto(referenceTextbox, reference);
        clickSearchButton();
    }

    public void searchByClaimantName(String name) {
        claimantNameTextbox.click();
        typeInto(claimantNameTextbox, name);
        clickSearchButton();
    }

    public void searchByClaimantsDOB(String date) {
        typeIntoDateFields(claimantDOBDayTextbox, claimantDOBMonthTextbox, claimantDOBYearTextbox, date);
        clickSearchButton();
    }

    public void searchByNationalInsuranceNo(String niNo) {
        nationalInsuranceNoTextbox.click();
        typeInto(nationalInsuranceNoTextbox, niNo);
        clickSearchButton();
    }

    public void searchByPreviousHOCSRef(String ref) {
        prevHocsRefTextbox.click();
        typeInto(prevHocsRefTextbox, ref);
        clickSearchButton();
    }

    public void assertNoSearchCriteriaErrorMessage() {
        noSearchCriteriaErrorMessage.shouldContainText("No search criteria specified");
    }

    //Methods

    public void getCaseReferenceOfFirstAndLastSearchResults() {
        List<WebElement> allCaseReferences = getDriver().findElements(By.cssSelector("a[class*='govuk-link']"));
        setSessionVariable("firstSearchResultCaseReference").to(allCaseReferences.get(0).getText());
        setSessionVariable("lastSearchResultCaseReference")
                .to(allCaseReferences.get(allCaseReferences.size() - 1).getText());
    }

    //Assertions

    public void assertExpectedTablesHeadersPresent() {
        List<WebElement> tableHeaders = getDriver().findElements(By.cssSelector(("th[class*='govuk-table__header']")));
        List<String> tableHeadersContent = new ArrayList<>();
        for (WebElement tableHeader : tableHeaders) {
            tableHeadersContent.add(tableHeader.getText());
        }
        assertThat(tableHeadersContent.contains("Reference"), is(true));
        assertThat(tableHeadersContent.contains("Current Stage"), is(true));
        assertThat(tableHeadersContent.contains("Owner"), is(true));
        assertThat(tableHeadersContent.contains("Team"), is(true));
        assertThat(tableHeadersContent.contains("Deadline"), is(true));
    }

    public void assertNumberOfCasesDisplayed(int number) {
        String numberOfCasesDisplayed = numberOfSearchResults.getText().split("\\s+")[0];
        System.out.println(numberOfCasesDisplayed);
    }

    public void assertCaseIsVisibleInSearchResults() {
        WebElementFacade currentCaseSearchResult = findBy("//td/a[text() = '" + getCurrentCaseReference() +"']");
        assertThat(currentCaseSearchResult.isVisible(), is(true));

    }

    public void assertAllResultsContainClaimantName(String searchedName) {
        List<WebElement> allClaimantNames = getDriver().findElements(By.xpath(("//tr/td[2]")));
        for (WebElement claimantName : allClaimantNames) {
            List<String> fullName = Arrays.asList(claimantName.getText().split(" "));
            if (!fullName.contains(searchedName)) {
                assert(false);
            }
        }
        assert(true);
    }
}
