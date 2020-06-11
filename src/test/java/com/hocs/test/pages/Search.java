package com.hocs.test.pages;

import com.hocs.test.pages.dcu.AccordionDCU;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Search extends BasePage {

    Workstacks workstacks;

    Homepage homepage;

    SummaryTab summaryTab;

    AccordionDCU accordionDCU;

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
    public WebElementFacade searchSignOffTeamDropdown;

    @FindBy(xpath = "//label[@for='caseStatus_active']")
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

    @FindBy(id = "reference")
    public WebElementFacade caseReferenceSearchBox;

    @FindBy(id = "RefType")
    public WebElementFacade mpamRefTypeDropdown;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    public WebElementFacade memberOfParliamentSearchBox;

    @FindBy(id = "correspondentReference")
    public WebElementFacade correspondentReferenceNumber;

    //DCU Methods

    public void enterSearchCorrespondent(String correspondentNameQuery) {
        safeClickOn(searchCorrespondentTextbox);
        typeInto(searchCorrespondentTextbox, correspondentNameQuery);
        setSessionVariable("correspondentNameQuery").to(correspondentNameQuery);
    }

    public void enterSearchTopic(String topicQuery) {
        safeClickOn(searchTopicTextbox);
        typeInto(searchTopicTextbox, topicQuery);
        setSessionVariable("topicQuery").to(topicQuery);
    }

    public void selectSignOffTeam(String team) {
        searchSignOffTeamDropdown.selectByVisibleText(team);
        setSessionVariable("searchedSignOffTeam").to(team);
    }

    public void enterReceivedOnOrAfterDate(String dd, String mm, String yyyy) {
        typeInto(receivedAfterDayTextbox, dd);
        typeInto(receivedAfterMonthTextbox, mm);
        typeInto(receivedAfterYearTextbox, yyyy);
    }

    public void enterReceivedOnOrBeforeDate(String dd, String mm, String yyyy) {
        typeInto(receivedBeforeDayTextbox, dd);
        typeInto(receivedBeforeMonthTextbox, mm);
        typeInto(receivedBeforeYearTextbox, yyyy);
    }

    public void viewFirstSearchResultCaseSummary() {
        WebElementFacade firstSearchResult = findAll("//td//a").get(0);
        safeClickOn(firstSearchResult);
        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            safeClickOn(workstacks.allocateToMeButton);
        }
        summaryTab.selectSummaryTab();
    }

    public void viewSummaryOfFirstSearchResultAdvancedPastDataInput() {
        WebElementFacade firstSearchResult = findAll("//td[2][not(contains(text(),'Data Input'))]/preceding-sibling::td/a").get(0);
        clickOn(firstSearchResult);
        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            safeClickOn(workstacks.allocateToMeButton);
        }
        summaryTab.selectSummaryTab();
    }

    public void getCaseReferenceOfFirstAndLastSearchResults() {
        List<WebElement> allCaseReferences = getDriver().findElements(By.cssSelector("a[class*='govuk-link']"));
        setSessionVariable("firstSearchResultCaseReference").to(allCaseReferences.get(0).getText());
        setSessionVariable("lastSearchResultCaseReference").to(allCaseReferences.get(allCaseReferences.size() - 1).getText());
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

    public boolean checkSignOffTeam(String caseRef, String signOffTeam) {
        goHome();
        homepage.enterCaseReferenceIntoSearchBar(caseRef);
        homepage.hitEnterCaseReferenceSearchBar();
        safeClickOn(accordionDCU.markupAccordionButton);
        return (accordionDCU.privateOfficeTeam.getText().contains(signOffTeam));
    }

    //MPAM Methods

    public void searchByCaseReference(String caseRef) {
        typeInto(caseReferenceSearchBox, caseRef);
    }

    public void searchByRefType(String refType) {
        switch (refType.toUpperCase()) {
            case "M:REF":
                mpamRefTypeDropdown.selectByIndex(1);
                break;
            case "B:REF":
                mpamRefTypeDropdown.selectByIndex(2);
                break;
            default:
                pendingStep(refType + " is not defined within " + getMethodName());
        }
    }

    public void searchByMemberOfParliament(String member) {
        safeClickOn(memberOfParliamentSearchBox);
        memberOfParliamentSearchBox.sendKeys(member);
        waitABit(5000);
        memberOfParliamentSearchBox.sendKeys(Keys.ENTER);
    }

    public void searchByCorrespondentRefNumber(String refNumber) {
        typeInto(correspondentReferenceNumber, refNumber);
    }

    //Assertions

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
        assertThat(summaryTab.getPrimaryCorrespondent().toUpperCase(), containsString(correspondentName));
    }

    public void assertThatSearchedTopicNameIsShownInCaseSummary() {
        String topicNameInSummary = sessionVariableCalled("topicQuery").toString();
        workstacks.primaryTopicName.shouldContainText(topicNameInSummary);
    }

    public void assertNoSearchCriteriaErrorMessage() {
        noSearchCriteriaErrorMessage.shouldContainText("No search criteria specified");
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

    public void assertFirstAndLastSearchResultsMatchDateSearchCriteria(String beforeOrAfter, String date) {
        getCaseReferenceOfFirstAndLastSearchResults();
        assertThat(checkCaseReceivedDate(beforeOrAfter, sessionVariableCalled("firstSearchResultCaseReference"),
                date), is(true));
        assertThat(checkCaseReceivedDate(beforeOrAfter, sessionVariableCalled("lastSearchResultCaseReference"),
                date), is(true));
    }

    public void assertNumberOfCasesDisplayed(int number) {
        String numberOfCasesDisplayed = numberOfSearchResults.getText().split("\\s+")[0];
        System.out.println("There are " + numberOfCasesDisplayed + " search results");
        assertThat(number == Integer.parseInt(numberOfCasesDisplayed), is(true));
    }

    public void assertClosedCaseVisibleIs(Boolean condition) {
        List closedCases = findAll("//td[2][text() = 'Closed']");
        assertThat(!closedCases.isEmpty(), is(condition));
    }

    public void assertActiveCaseVisibleIs(Boolean condition) {
        List activeCases = findAll("//td[2][not(text() = 'Closed')]");
        assertThat(!activeCases.isEmpty(), is(condition));
    }

    public void assertFirstAndLastSearchResultsMatchSignOffTeam() {
        getCaseReferenceOfFirstAndLastSearchResults();
        String signOffTeam = sessionVariableCalled("searchedSignOffTeam");
        assertThat(checkSignOffTeam(sessionVariableCalled("firstSearchResultCaseReference"), signOffTeam), is(true));
        assertThat(checkSignOffTeam(sessionVariableCalled("lastSearchResultCaseReference"), signOffTeam), is(true));
    }

    public void assertOnSearchPage() {
        topSearchResultCaseReference.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        assertPageTitle("Search Results");
    }

    public void assertCurrentCaseIsDisplayedInSearchResults() {
        WebElementFacade currentCase = findBy("//a[text()='" + sessionVariableCalled("caseReference") + "']");
        assertThat(currentCase.isVisible(), is(true));
    }

    public void waitUntilSearchPageLoaded() {
        receivedAfterDayTextbox.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
    }
}
