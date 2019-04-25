package com.hocs.test.pages.search_form;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.workstacks.Workstacks;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class SearchForm extends Page {

    @Managed
    WebDriver driver;

    Workstacks workstacks;

    Page page;

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

    public void enterSearchReceivedAfterDate() {
        receivedAfterDayTextbox.sendKeys(" ");
        receivedAfterMonthTextbox.sendKeys(" ");
        receivedAfterYearTextbox.sendKeys(" ");
    }

    public void enterSearchReceivedBeforeDate() {
        receivedBeforeDayTextbox.sendKeys(" ");
        receivedBeforeMonthTextbox.sendKeys(" ");
        receivedBeforeYearTextbox.sendKeys(" ");
    }

    public void enterSearchCorrespondent(String correspondentNameQuery) {
        searchCorrespondentTextbox.click();
        searchCorrespondentTextbox.sendKeys(correspondentNameQuery);
        setSessionVariable("correspondentNameQuery").to(correspondentNameQuery);
    }

    public void enterSearchTopic(String topicQuery) {
        searchTopicTextbox.click();
        searchTopicTextbox.sendKeys(topicQuery);
        setSessionVariable("topicQuery").to(topicQuery);
    }

    public void selectSearchSignOffMinister(String signOffMinisterName) {
        searchSignOffMinisterDropdown.sendKeys(signOffMinisterName);
    }

    public void viewFirstSearchResultCaseSummary() {
        WebElementFacade firstSearchResult = findAll("//td//a").get(0);
        firstSearchResult.click();
        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            page.clickOn(workstacks.allocateToMeButton);
        }
        page.clickOn(workstacks.caseSummaryTab);

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
        String correspondentNameInSummary = sessionVariableCalled("correspondentNameQuery").toString();
        assertThat(workstacks.primaryCorrespondentName.getText(), is(correspondentNameInSummary));

    }

    public void assertThatSearchedTopicNameIsShownInCaseSummary() {
        String topicNameInSummary = sessionVariableCalled("topicQuery").toString();
        assertThat(workstacks.primaryTopicName.getText(), is(topicNameInSummary));
    }

    public void assertNoSearchCriteriaErrorMessage() {
        assertThat(noSearchCriteriaErrorMessage.getText(), is("No search criteria specified"));
    }
}
