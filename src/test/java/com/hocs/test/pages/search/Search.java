package com.hocs.test.pages.search;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.summary.CaseSummaryPage;
import com.hocs.test.pages.workstacks.Workstacks;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.jruby.RubyBoolean.True;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Search extends Page {

    Workstacks workstacks;

    Homepage homepage;

    CaseSummaryPage caseSummaryPage;

    @FindBy(css = "label[for='caseTypes_MIN']")
    public WebElementFacade searchMINCheckbox;

    @FindBy(css = "label[for='caseTypes_DTEN']")
    public WebElementFacade searchDTENCheckbox;

    @FindBy(css = "label[for='caseTypes_TRO']")
    public WebElementFacade searchTROCheckbox;

    @FindBy(xpath = "//input[@id='dateReceivedFrom-day']")
    public WebElementFacade receivedAfterDayTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedFrom-month']")
    public WebElementFacade receivedAfterMonthTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedFrom-year']")
    public WebElementFacade receivedAfterYearTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedTo-day']")
    public WebElementFacade receivedBeforeDayTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedTo-month']")
    public WebElementFacade receivedBeforeMonthTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedTo-year']")
    public WebElementFacade receivedBeforeYearTextbox;

    @FindBy(xpath = "//input[@id='correspondent']")
    public WebElementFacade searchCorrespondentTextbox;

    @FindBy(xpath = "//input[@id='topic']")
    public WebElementFacade searchTopicTextbox;

    @FindBy(xpath = "//select[@id='signOffMinister']")
    public WebElementFacade searchSignOffMinisterDropdown;

    @FindBy(xpath = "//input[@id='caseStatus_active']")
    public WebElementFacade caseStatusActiveCheckbox;

    @FindBy(xpath = "//a[contains(text(), 'MIN')]")
    public WebElementFacade searchResultsMINCases;

    @FindBy(xpath = "//a[contains(text(), 'TRO')]")
    public WebElementFacade searchResultsTROCases;

    @FindBy(xpath = "//a[contains(text(), 'DTEN')]")
    public WebElementFacade searchResultsDTENCases;

    @FindBy(xpath = "//a[text()='No search criteria specified']")
    public WebElementFacade noSearchCriteriaErrorMessage;

    @FindBy(css = "tr:first-child a[class*='govuk-link']")
    public WebElementFacade topSearchResultCaseReference;

    @FindBy(css = "div span[class='govuk-hint']")
    public WebElementFacade numberOfSearchResults;

    public void enterSearchCorrespondent(String correspondentNameQuery) {
        searchCorrespondentTextbox.click();
        typeInto(searchCorrespondentTextbox, correspondentNameQuery);
        setSessionVariable("correspondentNameQuery").to(correspondentNameQuery);
    }

    public void enterSearchTopic(String topicQuery) {
        searchTopicTextbox.click();
        typeInto(searchTopicTextbox, topicQuery);
        setSessionVariable("topicQuery").to(topicQuery);
    }

    public void enterSearchSignOffMinister(String signOffMinisterName) {
        typeInto(searchSignOffMinisterDropdown, signOffMinisterName);
    }

    public void viewFirstSearchResultCaseSummary() {
        WebElementFacade firstSearchResult = findAll("//td//a").get(0);
        firstSearchResult.click();
        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            clickOn(workstacks.allocateToMeButton);
        }
        caseSummaryPage.selectSummaryTab();

    }

    public void assertThatMINCaseIsNotVisible() {
        assertThat(isElementDisplayed(searchResultsMINCases), is(false));
    }

    public void assertThatDTENCaseIsNotVisible() {
        assertThat(isElementDisplayed(searchResultsDTENCases), is(false));
    }

    public void assertThatTROCaseIsNotVisible() {
        assertThat(isElementDisplayed(searchResultsTROCases), is(false));
    }

    public void assertThatSearchedCorrespondentNameIsShownInCaseSummary() {
        String correspondentName = sessionVariableCalled("correspondentNameQuery").toString().toUpperCase();
        assertThat(caseSummaryPage.getPrimaryCorrespondent().toUpperCase(), containsString(correspondentName));
    }

    public void assertThatSearchedTopicNameIsShownInCaseSummary() {
        String topicNameInSummary = sessionVariableCalled("topicQuery").toString();
        assertThat(workstacks.primaryTopicName.getText(), is(topicNameInSummary));
    }

    public void assertNoSearchCriteriaErrorMessage() {
        assertThat(noSearchCriteriaErrorMessage.getText(), is("No search criteria specified"));
    }

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

    public void enterRecievedOnOrAfterDate(String dd, String mm, String yyyy) {
        typeInto(receivedAfterDayTextbox, dd);
        typeInto(receivedAfterMonthTextbox, mm);
        typeInto(receivedAfterYearTextbox, yyyy);
    }

    public void enterRecievedOnOrBeforeDate(String dd, String mm, String yyyy) {
        typeInto(receivedBeforeDayTextbox, dd);
        typeInto(receivedBeforeMonthTextbox, mm);
        typeInto(receivedBeforeYearTextbox, yyyy);
    }

    public void getCaseReferenceOfFirstAndLastSearchResults() {
        List<WebElement> allCaseReferences = getDriver().findElements(By.cssSelector("a[class*='govuk-link']"));
        setSessionVariable("firstSearchResultCaseReference").to(allCaseReferences.get(0).getText());
        setSessionVariable("lastSearchResultCaseReference")
                .to(allCaseReferences.get(allCaseReferences.size() - 1).getText());
    }

    private boolean checkCaseReceivedDate(String beforeOrAfter, String caseRef, String date) {

        goHome();
        homepage.enterCaseReferenceIntoSearchBar(caseRef);
        homepage.hitEnterCaseReferenceSearchBar();
        Date searchDate = null;
        Date caseDate = null;
        try {
            searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(workstacks.getCorrespondenceReceivedDateFromSummary());
        } catch (ParseException pE) {
            System.out.println("Could not parse dates");
        }
        assert caseDate != null;
        if (beforeOrAfter.toUpperCase().equals("BEFORE")) {
            return (caseDate.before(searchDate) || caseDate.equals(searchDate));
        } else {
            return (caseDate.after(searchDate) || caseDate.equals(searchDate));
        }
    }

    public void assertFirstAndLastSearchResultsMatchSearchCriteria(String beforeOrAfter, String date) {
        getCaseReferenceOfFirstAndLastSearchResults();
        assertThat(checkCaseReceivedDate(beforeOrAfter, sessionVariableCalled("firstSearchResultCaseReference"),
                date), is(true));
        assertThat(checkCaseReceivedDate(beforeOrAfter, sessionVariableCalled("lastSearchResultCaseReference"),
                date), is(true));
    }

    public void assertNumberOfCasesDisplayed(int number) {
        String numberOfCasesDisplayed = numberOfSearchResults.getText().split("\\s+")[0];
        System.out.println(numberOfCasesDisplayed);
    }

    public void assertClosedCaseVisibleIs(Boolean condition) {
        WebElementFacade closedCase = findAll("//td[2][not(.//text())]").get(0);
        assertThat(closedCase.isVisible(), is(condition));
    }

    public void assertActiveCaseVisibleIs(Boolean condition) {
        WebElementFacade activeCase = findAll("//td[2][.//text()]").get(0);
        assertThat(activeCase.isVisible(), is(condition));
    }
}
