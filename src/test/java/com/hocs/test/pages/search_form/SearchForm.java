package com.hocs.test.pages.search_form;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class SearchForm extends Page {

    @Managed
    WebDriver driver;

    @FindBy(xpath = "//input[@value='MIN']")
    public WebElementFacade searchMINCheckbox;

    @FindBy(xpath = "//input[@value='DTEN']")
    public WebElementFacade searchDTENCheckbox;

    @FindBy(xpath = "//input[@value='TRO']")
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

    public void selectMINCheckbox() {
        searchMINCheckbox.click();
    }

    public void selectDTENCheckbox() {
        searchDTENCheckbox.click();
    }

    public void selectTROCheckbox() {
        searchTROCheckbox.click();
    }

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

    public void enterSearchCorrespondent() {
        searchCorrespondentTextbox.sendKeys(" ");
    }

    public void enterSearchTopic() {
        searchTopicTextbox.sendKeys(" ");
    }

    public void selectSearchSignOffMinister(String signOffMinisterName) {
        searchSignOffMinisterDropdown.sendKeys(signOffMinisterName);
    }

    public void selectCaseStatusActiveCheckbox() {
        caseStatusActiveCheckbox.click();
    }


}
