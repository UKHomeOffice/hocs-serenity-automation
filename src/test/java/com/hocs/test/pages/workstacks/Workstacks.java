package com.hocs.test.pages.workstacks;

import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import org.hamcrest.core.Is;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.WebDriver;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Workstacks extends Page {

    @FindBy(xpath = "//span[@class='govuk-hint'][1]")
    public WebElementFacade totalNumberOfItems;

    @FindBy(linkText = "Allocate to me")
    public WebElementFacade allocateToMeButton;

    @FindBy(xpath = "//button[text()='Allocate selected to me']")
    public WebElementFacade allocateCheckboxCaseToMeButton;

    @FindBy(xpath = "//button[text()='Unallocate selected']")
    public WebElementFacade unallocateFromMeButton;

    @FindBy(id = "workstack-filter")
    public WebElementFacade selectWorkstackFilter;

    @FindBy(xpath = "//span[text()='DCU Ministerial']/preceding-sibling::span")
    public WebElementFacade dcuMINCardNumber;

    @FindBy(xpath = "//tbody[@class='govuk-table__body']")
    public WebElementFacade caseResultsTable;

    @FindBy(css = "[value = 'Allocate']")
    public WebElementFacade allocateButton;

    @FindBy(id = "selected_user")
    public WebElementFacade allocateDropdown;

    @FindBy(xpath = "//a[@class='govuk-breadcrumbs__link']")
    public WebElementFacade dashboardBreadcrumb;

    @FindBy(xpath = "//a[@class='govuk-breadcrumbs__link'][text()='Team']")
    public WebElementFacade teamBreadcrumb;

    @FindBy(xpath = "//a[@class='govuk-breadcrumbs__link'][text()='Workflow']")
    public WebElementFacade workflowBreadcrumb;

    @FindBy(xpath = "//span[text()='DCU Ministerial']")
    public WebElementFacade dcuMINFilterCard;

    @FindBy(xpath = "//span[text()='DCU Treat Official']")
    public WebElementFacade dcuTROFilterCard;

    @FindBy(xpath = "//span[text()='DCU Number 10']")
    public WebElementFacade dcuN10FilterCard;

    @FindBy(xpath = "//span[text()='Data Input']")
    public WebElementFacade dataInputFilterCard;

    @FindBy(xpath = "///span[text()='Dispatch']")
    public WebElementFacade dispatchInputFilterCard;

    @FindBy(xpath = "//span[text()='QA Response']")
    public WebElementFacade qaResponseFilterCard;

    @FindBy(xpath = "//a[@class='tab'][text()='Documents']")
    public WebElementFacade caseDocumentsTab;

    @FindBy(xpath = "//a[@class='tab'][text()='Summary']")
    public WebElementFacade caseSummaryTab;

    @FindBy(xpath = "//a[@class='tab'][text()='Timeline']")
    public WebElementFacade caseTimelineTab;

    @FindBy(xpath = "//span[@class='govuk-details__summary-text']")
    public WebElementFacade addCaseNoteButton;

    @FindBy(xpath = "//span[@id='case-note-error']")
    public WebElementFacade caseNoteMustNotBeBlankErrorMessage;

    @FindBy(xpath = "//a[@class='govuk-back-link'][text()='Back to dashboard']")
    public WebElementFacade backToDashboardButton;

    @FindBy(xpath = "//span[@class='govuk-hint'][text()='0']")
    public WebElementFacade zeroItemsInWorkstackCount;

    @FindBy(xpath = "//h1[@class='govuk-heading-l']")
    public WebElementFacade caseReferenceOnAllocationScreen;

    @FindBy(xpath = "//span[@class='govuk-caption-l']")
    public WebElementFacade caseReferenceOnAlreadyAllocatedCase;

    @FindBy(xpath = "(//td[@class='govuk-table__cell'])[2]")
    public WebElementFacade primaryCorrespondentName;

    @FindBy(xpath = "(//td[@class='govuk-table__cell'])[2]")
    public WebElementFacade primaryTopicName;

    @FindBy(xpath = "//a[@class='govuk-back-link']")
    public WebElementFacade allocateScreenCancelButton;

    // Basic Methods

    public void clickAllocateToMeButton() {
        allocateToMeButton.click();
    }

    public void selectAllocationUserByVisibleText(String allocationUser) {
        allocateDropdown.selectByVisibleText(allocationUser);
        allocateButton.click();

    }

    public int getTotalOfCases() {
        String tempNumberOfItems = totalNumberOfItems.getText().split(" ")[0];

        return Integer.parseInt(tempNumberOfItems);
    }

    public void selectTimeLineTab() {
        clickOn(caseTimelineTab);
    }

    public void selectSummaryTab() {
        clickOn(caseSummaryTab);
    }

    public void clickCheckboxRelevantToCaseReference() {
        String caseReference =
                sessionVariableCalled("caseReference").toString();
        WebDriver webDriver = getDriver();

        webDriver.findElement(
                By.xpath("//a[contains(text(), '" + caseReference + "')]/../..//input")).click();
    }

    public void clickAllWorkstackCheckboxes() {
        List<WebElementFacade> checkboxList = findAll(By.xpath("//td//input[@class='govuk-checkboxes__input']"));

        for (WebElementFacade boxes : checkboxList) {

            boxes.click();
        }
    }

    public void unallocatedAllCases() {
        clickAllWorkstackCheckboxes();
        clickOn(unallocateFromMeButton);
    }

    // Assertions

    public void assertThatDCUMinFilterCardIsVisible() {
        assertThat(dcuMINFilterCard.getText(), is("DCU Ministerial"));
    }

    public void assertThatDataInputFilterCardIsVisible() {
        assertThat(dataInputFilterCard.getText(), is("Data Input"));
    }

    public void assertCasesAreFilteredByRef(String caseReference) {
        int totalNumberOfCases = getTotalOfCases();

        List<WebElementFacade> listOfReferences = findAll(
                "//tbody[@class='govuk-table__body']/tr/td[2]/a[contains(text(), '" + caseReference
                        + "')]");
        assertThat(listOfReferences.size(), is(totalNumberOfCases));
    }

    public void assertCaseNoteMustNotBeBlankErrorMessage() {
        assertThat(caseNoteMustNotBeBlankErrorMessage.getText(), is("Case note must not be blank"));
    }

    public void assertCasesAreFilteredByStage(String currentStage) {
        int totalNumberOfCases = getTotalOfCases();
        int dispatchCount = 0;
        int dataInputCount = 0;
        int qaResponseCount = 0;

        List<WebElementFacade> listOfReference = findAll(
                "//tbody[@class='govuk-table__body']/tr/td[3]");
        for (WebElementFacade element : listOfReference) {
            String elementText = element.getText();

            switch (elementText.toUpperCase()) {
                case "DISPATCH":
                    dispatchCount++;
                    break;
                case "DATA INPUT":
                    dataInputCount++;
                    break;
                case "QA RESPONSE":
                    qaResponseCount++;
                    break;
                default:
                    pendingStep(elementText + " is not defined within " + getMethodName());
            }
        }

    }

    public void assertCaseReferenceIsVisible() {
        String caseReferenceNumber
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebElement thisReference = getDriver().findElement(By.linkText(caseReferenceNumber));
        System.out.println(thisReference);
        assertThat(isElementDisplayed(thisReference), is(true));
    }

    public void assertCaseIdIsVisible() {
        String caseReferenceNumber
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebElement thisReference = getDriver().findElement(By.linkText(caseReferenceNumber));
        System.out.println(thisReference);
        assertThat(isElementDisplayed(thisReference), is(true));
    }


    public void assertCaseReferenceIsNotVisible() {
        sleep(1000);

        String caseReferenceNumber
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebDriver driver = getDriver();
        WebElement element = null;

        try {
            element = driver.findElement(By.linkText(caseReferenceNumber));
        } catch (NoSuchElementException e) {
            // we expect this exception to be caught
        }

        assertThat(isElementDisplayed(element), is(false));
    }

    public void assertThatThereAreNoCasesInWorkstack() {
        assertThat(zeroItemsInWorkstackCount.getText(), is("0 Items"));
    }

    public void assertCaseReferenceBeforeAllocation(){
        String searchCaseReference = sessionVariableCalled("searchByCaseReferenceQuery").toString();
        assertThat(caseReferenceOnAllocationScreen.getText(), is(searchCaseReference));

    }

    public void assertCaseReferenceAfterAllocation() {
        String searchCaseReference = sessionVariableCalled("searchByCaseReferenceQuery").toString();
        assertThat(caseReferenceOnAlreadyAllocatedCase.getText(), is(searchCaseReference));
    }

    public void assertAllAllocatedUsers() {
        WebElementFacade eamonAllocated = findAll("//td[contains(text(), 'eamon')]").get(0);
        String thisAllocatedUserIs = eamonAllocated.getText();
        System.out.println(thisAllocatedUserIs);
        assertThat(thisAllocatedUserIs, is("eamon.droko@ten10.com"));
    }
}
