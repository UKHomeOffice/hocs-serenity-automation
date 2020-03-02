package com.hocs.test.pages.workstacks;

import config.Users;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.base_page.Page;
import org.hamcrest.core.Is;
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
    public WebElementFacade allocateSelectedToMeButton;

    @FindBy(xpath = "//button[text()='Unallocate selected']")
    public WebElementFacade unallocateButton;

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
    public WebElementFacade allocateScreenBackButton;

    @FindBy(xpath = "//tbody/tr/td[4]")
    public WebElementFacade displayedOwner;

    @FindBy(xpath = "//th[text()='When was the correspondence received?']/following-sibling::td")
    public WebElementFacade summaryWhenWasTheCorrespondenceReceived;

    @FindBy(xpath = "//*[@id=\"user-id\"]")
    public WebElementFacade caseDetailsAllocateDropdown;

    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div[1]/form/input")
    public WebElementFacade caseDetailsAllocateButton;


    // Basic Methods

    public void clickAllocateToMeButton() {
        allocateToMeButton.click();
    }

    public void clickAllocateSelectedToMeButton() {
        allocateSelectedToMeButton.click();
    }

    public void selectAllocationUserByVisibleText(String allocationUser) {
        allocateDropdown.click();
        allocateDropdown.selectByVisibleText(allocationUser);
        allocateButton.click();
    }

    public void caseDetailsSelectAllocationUserByVisibleText(String allocationUser) {
        caseDetailsAllocateDropdown.click();
        caseDetailsAllocateDropdown.selectByVisibleText(allocationUser);
        caseDetailsAllocateButton.click();
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

    public String getCorrespondenceReceivedDateFromSummary() {
        selectSummaryTab();
        return summaryWhenWasTheCorrespondenceReceived.getText();
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

    public void refineWorkstackSearchResults(String workstackInput) {
        selectWorkstackFilter.click();
        selectWorkstackFilter.sendKeys(workstackInput);
    }

    public void allocateThreeCasesCreated(Users user) {
        refineWorkstackSearchResults("MIN");
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOne = findBy("#main-content > div > div:nth-child(6) > div > form > fieldset > div:nth-child"
                + "(1) > div > div > table > tbody > tr:nth-child(" + (totalCaseNumber - 2) + ") > td > div > div > input");
        caseOne.click();

        WebElementFacade caseTwo = findBy("#main-content > div > div:nth-child(6) > div > form > fieldset > div:nth-child"
                + "(1) > div > div > table > tbody > tr:nth-child(" + (totalCaseNumber - 1) + ") > td > div > div > input");
        caseTwo.click();

        WebElementFacade caseThree = findBy("#main-content > div > div:nth-child(6) > div > form > fieldset > div:nth-child"
                + "(1) > div > div > table > tbody > tr:nth-child(" + (totalCaseNumber) + ") > td > div > div > input");
        caseThree.click();

        selectAllocationUserByVisibleText(user.getAllocationText());
    }

    public void unallocateThreeCasesFromSelectedUser(Users user) {
        refineWorkstackSearchResults("MIN");
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOne = findBy("#main-content > div > div:nth-child(6) > div > form > fieldset > div:nth-child"
                + "(1) > div > div > table > tbody > tr:nth-child(" + (totalCaseNumber - 2) + ") > td > div > div > input");
        caseOne.click();

        WebElementFacade caseTwo = findBy("#main-content > div > div:nth-child(6) > div > form > fieldset > div:nth-child"
                + "(1) > div > div > table > tbody > tr:nth-child(" + (totalCaseNumber - 1) + ") > td > div > div > input");
        caseTwo.click();
        WebElementFacade caseThree = findBy("#main-content > div > div:nth-child(6) > div > form > fieldset > div:nth-child"
                + "(1) > div > div > table > tbody > tr:nth-child(" + (totalCaseNumber) + ") > td > div > div > input");
        caseThree.click();

        unallocateButton.click();
    }

    public void unallocatedAllCases() {
        clickAllWorkstackCheckboxes();
        clickOn(unallocateButton);
    }

    public void clickDCUMINFilterCard() {
        dcuMINFilterCard.click();
    }

    public void clickDCUTENFilterCard() {
        dcuN10FilterCard.click();
    }

    public void clickDCUTROFilterCard() {
        dcuTROFilterCard.click();
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

    public void assertThatThereAreNoCasesInWorkstack() {
        assertThat(zeroItemsInWorkstackCount.getText(), is("0 Items"));
    }

    public void assertCaseReferenceBeforeAllocation(){
        String searchCaseReference = sessionVariableCalled("caseReference").toString();
        assertThat(caseReferenceOnAllocationScreen.getText(), is(searchCaseReference));

    }

    public void assertCaseReferenceAfterAllocation() {
        String searchCaseReference = sessionVariableCalled("caseReference").toString();
        assertThat(caseReferenceOnAlreadyAllocatedCase.getText(), is(searchCaseReference));
    }

    public void assertAllAllocatedUsersAre(Users user) {
        List<WebElementFacade> userAllocated = findAll("//td[contains(text(), '" + user.getUsername() + "')]");
        int totalNumberOfCases = getTotalOfCases();
        assertThat(userAllocated.size() == totalNumberOfCases, is(true));
    }

    public void assertOwnerIs(Users owner) {
        assertThat(displayedOwner.getText(), is(owner.getUsername()));
    }

    private String getStageFromWorkstacksTable() {
        WebElement caseReferenceStage = getDriver().findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseReference")
                        + "']/../following-sibling::td[1]"));
        System.out.println(caseReferenceStage);

        return caseReferenceStage.getText();
    }

    public void assertCaseStage(String stage) {
        assertThat(getStageFromWorkstacksTable().toUpperCase(), Is.is(stage.toUpperCase()));
    }

    public String getAllocatedUserFromWorkstacksTable() {
        WebElement caseOwner = getDriver().findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseReference")
                        + "']/../following-sibling::td[2]"));

        return caseOwner.getText();
    }

    public void selectCurrentCaseAndAllocateToMe() {
        clickCheckboxRelevantToCaseReference();
        clickOn(allocateSelectedToMeButton);
    }

    public void filterByCurrentCaseReference() {
        typeInto(selectWorkstackFilter, sessionVariableCalled("caseReference"));
    }

    public void assertAssignedUser(Users user) {
       waitABit(500);
        assertThat(getAllocatedUserFromWorkstacksTable().equals(user.getUsername()), is(true));
    }

    public void assertAssignedUserOnThreeCases(Users user) {
        int totalCaseNumber = getTotalOfCases();
        WebElement caseOwnerOne = getDriver()
                .findElement(By.cssSelector("tr:nth-child(" + (totalCaseNumber - 2) + ") > td:nth-child(4)"));

        WebElement caseOwnerTwo = getDriver().findElement(By.cssSelector("tr:nth-child(" + (totalCaseNumber - 1) + ") > "
                + "td:nth-child(4)"));

        WebElement caseOwnerThree = getDriver().findElement(By.cssSelector("tr:nth-child(" + (totalCaseNumber) + ") > td:nth-child(4)"));

        waitABit(500);
        assertThat(caseOwnerOne.getText().equals(user.getUsername()), is(true));
        assertThat(caseOwnerOne.getText().equals(user.getUsername()), is(true));
        assertThat(caseOwnerTwo.getText().equals(user.getUsername()), is(true));
    }

    public void assertThatThreeCasesHaveBeenUnassigned() {
        int totalCaseNumber = getTotalOfCases();
        WebElement caseOwnerOne = getDriver()
                .findElement(By.cssSelector("tr:nth-child(" + (totalCaseNumber - 2) + ") > td:nth-child(4)"));

        WebElement caseOwnerTwo = getDriver().findElement(By.cssSelector("tr:nth-child(" + (totalCaseNumber - 1) + ") > "
                + "td:nth-child(4)"));

        WebElement caseOwnerThree = getDriver().findElement(By.cssSelector("tr:nth-child(" + (totalCaseNumber) + ") > td:nth-child(4)"));

        waitABit(500);
        assertThat(caseOwnerOne.getText().equals(""), is(true));
        assertThat(caseOwnerOne.getText().equals(""), is(true));
        assertThat(caseOwnerTwo.getText().equals(""), is(true));
    }

    public void assertCaseIsAssignedToMe() {
        int totalCases = getTotalOfCases();
        WebElement caseOwner = getDriver().findElement(By.cssSelector("#main-content > div > div:nth-child(5) > div > form"
                + " > fieldset > div:nth-child(1) > div > div > table > tbody > tr:nth-child(" + totalCases + ") > "
                + "td:nth-child(4)"));

        assertThat(caseOwner.getText().equals(Users.EAMON.getUsername()), is(true));
    }

    public void assertThatDCUMINisOnlyVisibleCaseType() {
        int totalCases = getTotalOfCases();
        refineWorkstackSearchResults("MIN");
        assertThat(totalCases != 0, is(true));
        selectWorkstackFilter.clear();
        refineWorkstackSearchResults("DTEN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        selectWorkstackFilter.clear();
        refineWorkstackSearchResults("TRO");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
    }

    public void assertThatDCUTENisOnlyVisibleCaseType() {
        int totalCases = getTotalOfCases();
        refineWorkstackSearchResults("DTEN");
        assertThat(totalCases != 0, is(true));
        selectWorkstackFilter.clear();
        refineWorkstackSearchResults("MIN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        selectWorkstackFilter.clear();
        refineWorkstackSearchResults("TRO");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
    }

    public void assertThatDCUTROisOnlyVisibleCaseType() {
        int totalCases = getTotalOfCases();
        refineWorkstackSearchResults("TRO");
        assertThat(totalCases != 0, is(true));
        selectWorkstackFilter.clear();
        refineWorkstackSearchResults("MIN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        selectWorkstackFilter.clear();
        refineWorkstackSearchResults("DTEN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
    }




}
