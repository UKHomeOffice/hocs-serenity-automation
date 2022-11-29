package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.CaseType;
import config.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Workstacks extends BasePage {

    @FindBy(css = "div[class='govuk-hint']")
    public WebElementFacade totalNumberOfCases;

    @FindBy(xpath = "//button[text()='Allocate selected to me']")
    public WebElementFacade allocateSelectedToMeButton;

    @FindBy(xpath = "//button[text()='Unallocate selected']")
    public WebElementFacade unallocateButton;

    @FindBy(id = "workstack-filter")
    public WebElementFacade caseFilter;

    @FindBy(css = "[value = 'Allocate']")
    public WebElementFacade allocateButton;

    @FindBy(id = "selected_user")
    public WebElementFacade allocateDropdown;

    @FindBy(xpath = "//span[text()='DCU Ministerial']")
    public WebElementFacade dcuMINFilterCard;

    @FindBy(xpath = "//span[text()='DCU Treat Official']")
    public WebElementFacade dcuTROFilterCard;

    @FindBy(xpath = "//span[text()='DCU Number 10']")
    public WebElementFacade dcuN10FilterCard;

    @FindBy(xpath = "//span[text()='Data Input']")
    public WebElementFacade dataInputFilterCard;

    @FindBy(xpath = "//span[@class='govuk-hint'][text()='0']")
    public WebElementFacade zeroItemsInWorkstackCount;

    @FindBy(xpath = "//th[text() = 'Primary topic']/following-sibling::td")
    public WebElementFacade primaryTopicName;

    @FindBy(xpath = "//tbody/tr/td[4]")
    public WebElementFacade ownerOfTopCaseInWorkstack;

    @FindBy(xpath = "//*[@id=\"user-id\"]")
    public WebElementFacade caseDetailsAllocateDropdown;

    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div[1]/form/input")
    public WebElementFacade caseDetailsAllocateButton;

    @FindBy(xpath = "//tr[1]/td[2]/a")
    public WebElementFacade topCaseReferenceHypertext;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Reference']")
    public WebElementFacade referenceColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Current Stage']")
    public WebElementFacade currentStageColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Owner']")
    public WebElementFacade ownerColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[contains(text(), 'Deadline')]")
    public WebElementFacade deadlineColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Urgency']")
    public WebElementFacade urgencyColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Days']")
    public WebElementFacade daysColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Ref Type']")
    public WebElementFacade refTypeColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Business Area']")
    public WebElementFacade businessAreaColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Team']")
    public WebElementFacade teamColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Campaign']")
    public WebElementFacade campaignColumnHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Minister Sign Off']")
    public WebElementFacade ministerSignOffHeader;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Telephone Surgery Official Engagement']")
    public WebElementFacade telephoneSurgeryOfficialEngagementHeader;

    @FindBy(xpath = "//button[text()='Take next case']")
    public WebElementFacade takeNextCaseButton;

    @FindBy(xpath = "//thead/tr[1]/th[text()='Rejected']")
    public WebElementFacade rejectedHeader;

    List visibleColumns;

    List<String> caseReferencesList = new ArrayList<>();

    public void filterByCurrentCaseReference() {
        caseFilter.sendKeys(getCurrentCaseReference());
    }

    public String getValueFromSpecifiedColumnForSpecifiedCase(String columnTitle, String caseReference) {
        filterWorkstackBy(caseReference);
        waitForOnlyOneCaseToBeDisplayed();
        int i = getSpecificColumnsPositionInTable(columnTitle);
        String displayedValue = "";
        WebElementFacade cell = findBy("//tbody/tr[1]/td[" + i + "]");
        WebElementFacade strongInCell = findBy("//tbody/tr[1]/td[" + i + "]/strong");
        WebElementFacade divInCell = findBy("//tbody/tr[1]/td[" + i + "]/div");
        switch (columnTitle.toUpperCase()) {
            case "OWNER":
                if (divInCell.isCurrentlyVisible()) {
                    displayedValue = divInCell.getText();
                } else {
                    displayedValue = cell.getText();
                }
                break;
            case "MP":
            case "Correspondent":
                if (strongInCell.isCurrentlyVisible()) {
                    displayedValue = strongInCell.getText();
                } else {
                    displayedValue = cell.getText();
                }
                break;
            default:
                displayedValue = cell.getText();
                break;
        }
        return displayedValue;
    }

    private int getSpecificColumnsPositionInTable(String columnTitle) {
        int i = 0;
        boolean columnFound = false;
        while (!columnFound) {
            i++;
            WebElementFacade coloumnHeader = findBy("//thead/tr/th[" + i + "]");
            columnFound = coloumnHeader.getText().contains(columnTitle);
        }
        return i;
    }

    public void clickAllocateSelectedToMeButton() {
        safeClickOn(allocateSelectedToMeButton);
    }

    public boolean ownerOfTopCaseInWorkstackIs(User user) {
        return getValueFromSpecifiedColumnForSpecifiedCase("Owner", topCaseReferenceHypertext.getText()).contains(user.getUsername());
    }

    private String getOwnerOfTopCaseInWorkstack() {
        int i = 0;
        boolean ownerHeaderFound = false;
        while (!ownerHeaderFound) {
            i++;
            WebElementFacade header = findBy("//thead/tr/th[" + i + "]");
            ownerHeaderFound = header.getText().contains("Owner");
        }
        String ownerName;
        WebElementFacade ownerNameInCellDiv = findBy("//tbody/tr[1]/td[" + i + "]/div");
        WebElementFacade ownerNameInCell = findBy("//tbody/tr[1]/td[" + i + "]");
        if (ownerNameInCellDiv.isCurrentlyVisible()) {
            ownerName = ownerNameInCellDiv.getText();
        } else {
            ownerName = ownerNameInCell.getText();
        }
        return ownerName;
    }

    public void selectAllocationUserByVisibleText(String allocationUser) {
        safeClickOn(allocateDropdown);
        allocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(allocateButton);
    }

    public void goToCurrentCaseFromWorkstack() {
        selectSpecificCaseReferenceLink(getCurrentCaseReference());
    }

    public void selectSpecificCaseReferenceLink(String caseReference) {
        WebElementFacade caseReferenceLink = find(By.xpath("//a[text()='" + caseReference + "']"));
        safeClickOn(caseReferenceLink);
    }

    public int getTotalOfCases() {
        totalNumberOfCases.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        String numberOfCases = totalNumberOfCases.getText().split(" ")[0];
        return Integer.parseInt(numberOfCases);
    }

    private void waitForOnlyOneCaseToBeDisplayed() {
        int attempts = 0;
        while(!(getTotalOfCases() == 1) && attempts < 10) {
            waitABit(500);
            attempts ++;
        }
    }

    public void clickCheckboxRelevantToCaseReference() {
        waitForWorkstackToLoad();
        String caseReference =
                getCurrentCaseReference();
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

    public void filterWorkstackBy(String filterString) {
        safeClickOn(caseFilter);
        caseFilter.clear();
        caseFilter.sendKeys(filterString);
    }

    public void recordHighestPriorityCases() {
        int n = 1;
        int totalOfCases = getTotalOfCases();
        while (n <= totalOfCases) {
            if (getNthMPAMCasesOwner(n).equals("")) {
                caseReferencesList.add(getNthMPAMCasesReference(n));
                break;
            }
            n++;
        }
        String highestPriorityUrgency = getNthMPAMCasesUrgency(n);
        String highestPriorityDays = getNthMPAMCasesDays(n);
        if (totalOfCases > 1) {
            n++;
            while (n <= totalOfCases && getNthMPAMCasesUrgency(n).equals(highestPriorityUrgency) && getNthMPAMCasesDays(n)
                    .equals(highestPriorityDays)) {
                if (getNthMPAMCasesOwner(n).equals("")) {
                    caseReferencesList.add(getNthMPAMCasesReference(n));
                }
                n++;
            }
        }
    }

    public void selectTakeNextCase() {
        safeClickOn(takeNextCaseButton);
    }

    private String getNthMPAMCasesReference(int n) {
        return findBy("//tbody/tr[" + n + "]/td[2]/a").getText();
    }

    private String getNthMPAMCasesOwner(int n) {
        return findBy("//tbody/tr[" + n + "]/td[4]/div").getText();
    }

    private String getNthMPAMCasesUrgency(int n) {
        return findBy("//tbody/tr[" + n + "]/td[7]").getText();
    }

    private String getNthMPAMCasesDays(int n) {
        return findBy("//tbody/tr[" + n + "]/td[8]").getText();
    }

    public void allocateThreeCasesCreated(User user) {
        waitABit(1000);
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOne = findBy("//tr[" + (totalCaseNumber - 2) + "]//input");
        clickOn(caseOne);
        WebElementFacade caseTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]//input");
        clickOn(caseTwo);
        WebElementFacade caseThree = findBy("//tr[" + totalCaseNumber + "]//input");
        clickOn(caseThree);
        selectAllocationUserByVisibleText(user.getAllocationText());
    }

    public void unallocateThreeCasesFromSelectedUser() {
        waitABit(1500);
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOne = findBy("//tr[" + (totalCaseNumber - 2) + "]//input");
        clickOn(caseOne);
        WebElementFacade caseTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]//input");
        clickOn(caseTwo);
        WebElementFacade caseThree = findBy("//tr[" + totalCaseNumber + "]//input");
        clickOn(caseThree);
        safeClickOn(unallocateButton);
    }

    public void selectAFilterCard() {
        WebElementFacade workflowFilterCardCaseTotal = findBy("//li[contains(@class, 'card')]/a/span[1]");
        setSessionVariable("filterCardCaseTotal").to(workflowFilterCardCaseTotal.getText());
        safeClickOn(workflowFilterCardCaseTotal);
        workflowFilterCardCaseTotal.waitUntilNotVisible();
    }

    public void waitForWorkstackToLoad() {
        caseFilter.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
    }

    public void orderMPAMWorkstackColumn(String column, String order) {
        WebElementFacade selectedHeader = null;
        switch (column.toUpperCase()) {
            case "REFERENCE":
                selectedHeader = referenceColumnHeader;
                break;
            case "CURRENT STAGE":
                selectedHeader = currentStageColumnHeader;
                break;
            case "OWNER":
                selectedHeader = ownerColumnHeader;
                break;
            case "DEADLINE":
                selectedHeader = deadlineColumnHeader;
                break;
            case "URGENCY":
                selectedHeader = urgencyColumnHeader;
                break;
            case "DAYS":
                selectedHeader = daysColumnHeader;
                break;
            case "REF TYPE":
                selectedHeader = refTypeColumnHeader;
                break;
            case "BUSINESS AREA":
                selectedHeader = businessAreaColumnHeader;
                break;
            case "TEAM":
                selectedHeader = teamColumnHeader;
                break;
            case "CAMPAIGN":
                selectedHeader = campaignColumnHeader;
                break;
            case "MINISTER SIGN OFF":
                selectedHeader = ministerSignOffHeader;
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                selectedHeader = telephoneSurgeryOfficialEngagementHeader;
                break;
            case "REJECTED":
                selectedHeader = rejectedHeader;
                break;
            default:
                pendingStep(column + " is not defined within " + getMethodName());
        }
        switch (order.toUpperCase()) {
            case "LOWEST TO HIGHEST":
                safeClickOn(selectedHeader);
                break;
            case "HIGHEST TO LOWEST":
                safeClickOn(selectedHeader);
                waitABit(1000);
                safeClickOn(selectedHeader);
                break;
            default:
                pendingStep(order + " is not defined within " + getMethodName());
        }
        List<WebElementFacade> listOfColumnHeaderElements = findAll("//thead/tr[1]/th");
        int lengthOfElementList = listOfColumnHeaderElements.size();
        int n = 0;
        List<String> listOfColumnHeaders = new ArrayList<>();
        while (n < lengthOfElementList) {
            String addition = listOfColumnHeaderElements.get(n).getText();
            listOfColumnHeaders.add(addition);
            n += 1;
        }
        int headerIndex = listOfColumnHeaders.indexOf(column);
        setSessionVariable("headerIndex").to(headerIndex + 1);
    }

    public void unallocateSelectedCase(String caseRef) {
        waitForWorkstackToLoad();
        caseFilter.sendKeys(caseRef);
        waitABit(500);
        WebElementFacade selectedCaseCheckBox = findBy("//a[text()='" + caseRef + "']/parent::td/preceding-sibling::td//label");
        safeClickOn(selectedCaseCheckBox);
        safeClickOn(unallocateButton);
        waitForTopCaseToNotBeAllocated();
    }

    public void waitForTopCaseToNotBeAllocated() {
        boolean allocated = !getOwnerOfTopCaseInWorkstack().isEmpty();
        int attempts = 0;
        while (allocated && attempts<20) {
            String owner = getOwnerOfTopCaseInWorkstack();
            allocated = !owner.isEmpty();
            waitABit(500);
            attempts++;
        }
    }

    // Assertions

    public void assertCorrectCaseIsTaken() {
        waitFor(headerCaption1);
        assertThat(caseReferencesList.contains(headerCaption1.getText()), is(true));
    }

    public void assertCaseIsInTheCorrectCampaign() {
        WebElementFacade campaignOfCurrentCase = findBy("//a[text()='" + getCurrentCaseReference()
                + "']/../following-sibling::td[4]");
        assertThat(campaignOfCurrentCase.getText().equals(sessionVariableCalled("campaign")), is(true));
    }

    public void assertCasesAreFilteredByRef(String caseReference) {
        int totalNumberOfCases = getTotalOfCases();

        List<WebElementFacade> listOfReferences = findAll(
                "//tbody[@class='govuk-table__body']/tr/td[2]/a[contains(text(), '" + caseReference
                        + "')]");
        assertThat(listOfReferences.size(), is(totalNumberOfCases));
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

    public void assertVisibilityOfCaseReference(boolean trueFalse) {
        String caseReferenceNumber
                = getCurrentCaseReference();
        assertVisibilityOfSpecificCaseReference(trueFalse,caseReferenceNumber);
    }

    public void assertVisibilityOfSpecificCaseReference(boolean trueFalse, String caseRefNumber) {
        WebElementFacade thisReference = findBy("//a[text()='" + caseRefNumber + "']");
        assertThat(isElementDisplayed(thisReference), is(trueFalse));
    }

    public void assertThatThereAreNoCasesInWorkstack() {
        zeroItemsInWorkstackCount.shouldContainText("0 Items");
    }

    public void assertAllAllocatedUsersAre(User user) {
        List<WebElementFacade> userAllocated = findAll("//td[contains(text(), '" + user.getUsername() + "')]");
        int totalNumberOfCases = getTotalOfCases();
        assertThat(userAllocated.size() == totalNumberOfCases, is(true));
    }

    public void assertOwnerOfCurrentCaseIs(User owner) {
        filterByCurrentCaseReference();
        assert (getOwnerOfTopCaseInWorkstack().contains(owner.getUsername()));
    }

    public void assertAssignedUser(User user) {
        WebElementFacade caseOwner = findBy("//a[text()='" + getCurrentCaseReference()
                + "']/../following-sibling::td[2]");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until((ExpectedCondition<Boolean>) driver -> (caseOwner.getText().equals(user.getUsername())));
    }

    public void assertAssignedUserOnThreeCases(User user) {
        waitABit(6000);
        int n = 1;
        while (n <= 3) {
            WebElementFacade selectedCase = findBy("//a[text()='" + sessionVariableCalled("caseReference" + n) + "']/parent::td"
                    + "/following-sibling::td[2]");
            waitABit(4000);
            selectedCase.waitUntilVisible().shouldContainText(user.getUsername());
            n++;
        }
    }

    public void assertThatThreeCasesHaveBeenUnassigned() {
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOne = findBy("//tr[" + (totalCaseNumber - 2) + "]/td[4]");
        caseOne.shouldContainText("");
        WebElementFacade caseTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]/td[4]");
        caseTwo.shouldContainText("");
        WebElementFacade caseThree = findBy("//tr[" + totalCaseNumber + "]/td[4]");
        caseThree.shouldContainText("");
    }

    public void assertCaseIsAssignedToMe() {
        WebElementFacade caseOwner = findBy("//a[text()='" + getCurrentCaseReference() + "']/parent::td/following-sibling::td[2][text"
                + "()='" + getCurrentUser().getUsername() + "']");
        caseOwner.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
        assert caseOwner.isVisible();
    }

    private boolean areCasesOfCaseTypePresent(CaseType caseType) {
        filterWorkstackBy(caseType.toString());
        waitABit(1000);
        int totalCases = getTotalOfCases();
        caseFilter.clear();
        return (totalCases != 0);
    }

    public double getPointsOfCurrentCase() {
        WebElementFacade casePoints = find(By.xpath("//a[text()='" + getCurrentCaseReference() + "']/parent::td"
                + "/following-sibling::td[6]"));
        return Double.parseDouble(casePoints.getText());
    }

    public void assertPointsOfCurrentCaseEqual(String expectedPoints) {
        assertThat(getPointsOfCurrentCase(), is(Double.parseDouble(expectedPoints)));
    }

    public void assertColumnIsOrderedProperly(String column, String order) throws ParseException {
        int currentCase = 2;
        int totalCases = getTotalOfCases();
        WebElement cellOne =
                find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(" + sessionVariableCalled("headerIndex") + ")"));
        WebElement cellTwo =
                find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(" + sessionVariableCalled("headerIndex") + ")"));
        while (currentCase <= totalCases) {
            switch (column.toUpperCase()) {
                case "REFERENCE":
                    String refNumberOne = cellOne.getText();
                    String refNumberTwo = cellTwo.getText();
                    String numberAsStringOne = refNumberOne.substring(6, 11);
                    String numberAsStringTwo = refNumberTwo.substring(6, 11);
                    int numberOne = Integer.parseInt(numberAsStringOne);
                    int numberTwo = Integer.parseInt(numberAsStringTwo);
                    switch (order.toUpperCase()) {
                        case "LOWEST TO HIGHEST":
                            assertThat(numberOne <= numberTwo, is(true));
                            break;
                        case "HIGHEST TO LOWEST":
                            assertThat(numberOne >= numberTwo, is(true));
                            break;
                        default:
                            pendingStep(order + " is not defined within " + getMethodName());
                    }
                    break;
                case "CURRENT STAGE":
                case "OWNER":
                case "REF TYPE":
                case "BUSINESS AREA":
                case "TEAM":
                case "CAMPAIGN":
                case "MINISTER SIGN OFF":
                case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                case "REJECTED":
                    String currentStageOne = cellOne.getText();
                    String currentStageTwo = cellTwo.getText();
                    switch (order.toUpperCase()) {
                        case "LOWEST TO HIGHEST":
                            assertThat(currentStageOne.compareTo(currentStageTwo) <= 0, is(true));
                            break;
                        case "HIGHEST TO LOWEST":
                            assertThat(currentStageOne.compareTo(currentStageTwo) >= 0, is(true));
                            break;
                        default:
                            pendingStep(order + " is not defined within " + getMethodName());
                    }
                    break;
                case "DEADLINE":
                    String deadlineOneString = cellOne.getText();
                    String deadlineTwoString = cellTwo.getText();
                    if (!deadlineOneString.equals("") && !deadlineTwoString.equals("")) {
                        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                        Date deadlineOne = format.parse(deadlineOneString);
                        Date deadlineTwo = format.parse(deadlineTwoString);
                        switch (order.toUpperCase()) {
                            case "LOWEST TO HIGHEST":
                                assertThat(deadlineOne.before(deadlineTwo) || deadlineOne.equals(deadlineTwo), is(true));
                                break;
                            case "HIGHEST TO LOWEST":
                                assertThat(deadlineOne.after(deadlineTwo) || deadlineOne.equals(deadlineTwo), is(true));
                                break;
                            default:
                                pendingStep(order + " is not defined within " + getMethodName());
                        }
                    }
                    break;
                case "URGENCY":
                    Hashtable<String, Integer> urgencyDictionary = new Hashtable<String, Integer>();
                    urgencyDictionary.put("", 0);
                    urgencyDictionary.put("Standard", 1);
                    urgencyDictionary.put("Priority", 2);
                    urgencyDictionary.put("Immediate", 3);
                    String urgencyOneString = cellOne.getText();
                    String urgencyTwoString = cellTwo.getText();
                    int urgencyOne = urgencyDictionary.get(urgencyOneString);
                    int urgencyTwo = urgencyDictionary.get(urgencyTwoString);
                    switch (order.toUpperCase()) {
                        case "LOWEST TO HIGHEST":
                            assertThat(urgencyOne >= urgencyTwo, is(true));
                            break;
                        case "HIGHEST TO LOWEST":
                            assertThat(urgencyOne <= urgencyTwo, is(true));
                            break;
                        default:
                            pendingStep(order + " is not defined within " + getMethodName());
                    }
                    break;
                case "DAYS":
                    String daysOneString = cellOne.getText();
                    String daysTwoString = cellTwo.getText();
                    int daysOne = Integer.parseInt(daysOneString);
                    int daysTwo = Integer.parseInt(daysTwoString);
                    switch (order.toUpperCase()) {
                        case "LOWEST TO HIGHEST":
                            assertThat(daysOne <= daysTwo, is(true));
                            break;
                        case "HIGHEST TO LOWEST":
                            assertThat(daysOne >= daysTwo, is(true));
                            break;
                        default:
                            pendingStep(order + " is not defined within " + getMethodName());
                    }
                    break;
                default:
                    pendingStep(column + " is not defined within " + getMethodName());
            }
            currentCase += 1;
        }
    }

    public void assertThatDeadlineHighlightedIs(boolean condition) {
        waitForWorkstackToLoad();
        WebElementFacade deadlineOfCurrentCase =
                findBy("//a[text()='" + getCurrentCaseReference() + "']/parent::td/following-sibling::td/span[contains(text(), '"
                        + getCurrentYear() + "')"
                        + "]");
        assertThat(deadlineOfCurrentCase.isVisible(), is(condition));
    }

    public void assertThatDeadlineHighlightedIsYellow() {
        WebElement label = find(
                By.xpath("//a[text()='" + getCurrentCaseReference() + "']/parent::td/following-sibling::td/span[contains(text(), '"
                        + getCurrentYear() + "')"
                        + "]"));
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(255, 221, 0, 1)"), is(true));
    }

    public void assertThatDeadlineHighlightedIsRed() {
        WebElement label = find(
                By.xpath("//a[text()='" + getCurrentCaseReference() + "']/parent::td/following-sibling::td/span[contains(text(), '"
                        + getCurrentYear() + "')"
                        + "]"));
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(212, 53, 28, 1)"), is(true));
    }

    public void assertHigherPriorityCaseIsFirstInWorkstack(String highPriorityCase, String lowPriorityCase) {
        caseFilter.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        String highPriorityReference = sessionVariableCalled(highPriorityCase);
        String lowPriorityReference = sessionVariableCalled(lowPriorityCase);

        List<WebElementFacade> caseReferenceElements = findAll(By.xpath("//tr/td[2]/a"));
        Boolean highPriorityFirst = false;
        Boolean lowPrioritySecond = false;
        for (WebElementFacade caseReferenceElement : caseReferenceElements) {
            if (caseReferenceElement.getText().equals(highPriorityReference)) {
                highPriorityFirst = true;
            }
            if (caseReferenceElement.getText().equals(lowPriorityReference) && highPriorityFirst) {
                lowPrioritySecond = true;
            }
        }
        assertThat(highPriorityFirst && lowPrioritySecond, is(true));
    }

    private List<String> getTableHeadersContent() {
        waitFor(caseFilter);
        List<WebElement> tableHeaders = getDriver().findElements(By.cssSelector(("th[class*='govuk-table__header']")));
        List<String> tableHeadersContent = new ArrayList<>();
        for (WebElement tableHeader : tableHeaders) {
            tableHeadersContent.add(tableHeader.getText());
        }
               return tableHeadersContent;
    }

    private void checkColumnsArePresent(List<String> columns) {
        for (String column : columns) {
            if (!visibleColumns.contains(column)) {
                Assert.fail(column + " column is not visible in workstack");
            }
            visibleColumns.remove(column);
        }
    }

    public void assertExpectedColumnsPresent(String workstack) {
        waitForWorkstackToLoad();
        visibleColumns = getTableHeadersContent();
        List<String> requiredColumns = new ArrayList<>();
        switch (workstack.toUpperCase()) {
            case "DCU MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Stage", "Team", "Primary topic", "Deadline"));
                break;
            case "DCU TEAM":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Stage", "Owner", "Primary topic", "Deadline"));
                break;
            case "DCU SEARCH":
                requiredColumns.addAll(Arrays.asList("Correspondent/Reference", "Stage", "Owner", "Team", "Primary topic", "Deadline"));
                break;
            case "MPAM MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Ref type", "Business area", "Stage", "Deadline",
                        "Urgency", "Days"));
                break;
            case "MPAM SEARCH":
                requiredColumns.addAll(Arrays.asList("Reference", "Stage", "Owner", "Team", "Deadline", "Members of parliament",
                        "Constituents/Applicants"));
                break;
            case "MPAM CAMPAIGN":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Ref type", "Business area", "MP/Owner", "Campaign",
                        "Days"));
                break;
            case "MPAM TRIAGE":
            case "MPAM DRAFT":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Stage", "MP/Owner", "Minister sign off",
                        "Deadline", "Urgency", "Days", "Rejected"));
                break;
            case "MPAM CREATION":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Stage", "Owner", "Minister sign off", "Deadline",
                        "Urgency", "Days"));
                break;
            case "MTS TEAM":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Business area", "Stage", "Owner", "Deadline", "Urgency",
                        "Telephone surgery official engagement"));
                break;
            case "COMPLAINT REGISTRATION":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Stage", "Owner", "Deadline", "Severity"));
                break;
            case "COMP SEARCH":
                requiredColumns.addAll(Arrays.asList("Name", "Reference", "Deadline", "Stage", "Severity", "Postcode", "HO ref",
                        "Escalate case"));
                break;
            case "CCT TRIAGE":
            case "EX-GRATIA":
            case "MINOR MISCONDUCT":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Stage", "Contributions", "Owner", "Deadline", "Severity"));
                break;
            case "IE DETENTION":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Current stage", "Complaint type", "Business area", "Deadline"));
                break;
            case "PSU COMPLAINTS":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Name", "Current stage", "PSU reference", "Owner", "Received",
                        "Deadline"));
                break;
            case "SERIOUS MISCONDUCT":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Current stage", "Owner", "Deadline","PSU reference"));
                break;
            case "SERIOUS MISCONDUCT MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "PSU reference", "Current stage", "Team", "Deadline"));
                break;
            case "BORDER FORCE":
            case "BORDER FORCE (STAGE 2)":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Name", "Complaint type", "Stage", "Owner", "Contribution due "
                                + "date",
                        "Deadline"));
                break;
            case "BF SEARCH":
                requiredColumns.addAll(Arrays.asList("Name", "Reference", "Deadline", "Stage", "Postcode", "HO ref", "Escalate case"));
                break;
            case "FOI TEAM":
                requiredColumns.addAll(Arrays.asList("Select", "Requester/Reference", "Current stage", "Owner", "Team", "Deadline", "Rejected",
                        "Extended"));
                break;
            case "POGR REGISTRATION":
            case "POGR REGISTRATION (STAGE 2)":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Stage", "Owner", "Deadline",
                        "Urgency", "Days", "Rejected"));
                break;
            case "POGR MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Stage", "Team", "Deadline",
                        "Urgency", "Days", "Rejected"));
                break;
            case "TREAT OFFICIAL UKVI":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Name", "Stage", "Owner", "Enquiry reason", "Campaign",
                        "Deadline"));
                break;
            case "TREAT OFFICIAL BF":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Name", "Stage", "Owner", "Contribution due date",
                        "Deadline"));
                break;
            case "TREAT OFFICIAL MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Name", "Stage", "Enquiry reason", "Contribution due date",
                        "Deadline"));
                break;
            case "TREAT OFFICIAL SEARCH":
                requiredColumns.addAll(Arrays.asList( "Reference", "Name", "Stage", "Owner", "Team",
                        "Deadline"));
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
        checkColumnsArePresent(requiredColumns);
        assertThat(visibleColumns.isEmpty(), is(true));
    }

    public void assertOverdueContributionRequestIsHighlighted() {
        WebElementFacade label = findBy("//a[text()='" + getCurrentCaseReference() + "']/parent::td/following-sibling::td//span[contains(text(), 'Overdue')]");
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(212, 53, 28, 1)"), is(true));
    }

    public void assertHomeSecretarySymbolVisibleForCase(String caseReference) {
        waitForWorkstackToLoad();
        WebElementFacade homeSecSymbol = findBy("//a[text()='" + caseReference + "']/preceding-sibling::span[text()='HS']");
        assertThat(homeSecSymbol.isCurrentlyVisible(), is(true));
    }

    public void assertSecretariatClearanceRequestedDueDate() {
        waitForWorkstackToLoad();
        String inputClearanceDueDate = sessionVariableCalled("clearanceDueDate");
        assertSpecifiedColumnContainsValueForCurrentCase("Current stage", inputClearanceDueDate);
    }

    public void assertOverdueSecretariatClearanceRequestIsHighlighted() throws ParseException {
        int n = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        List<WebElementFacade> allCasesAtSecretariatClearance = findAll("//span[contains(text(), 'Secretariat Clearance Requested')]");
        while (n < allCasesAtSecretariatClearance.size()) {
            WebElementFacade highlightCheck = allCasesAtSecretariatClearance.get(n);
            String backgroundColour = highlightCheck.getCssValue("background-color");
            if (backgroundColour.equalsIgnoreCase("rgba(212, 53, 28, 1)")) {
                Date dueDate = formatter.parse(highlightCheck.getText().split("due ")[1]);
                Date todaysDate = formatter.parse(getTodaysDate());
                assertThat(dueDate.before(todaysDate), is(true));
                break;
            }
            n++;
        }
        if (n == allCasesAtSecretariatClearance.size()) {
            System.out.println("No Overdue Secretariat Clearance Requests exist, manually verify at later date");
        }
    }

    public void assertSpecifiedColumnContainsValueForCurrentCase(String columnTitle, String expectedValue) {
        waitForWorkstackToLoad();
        String displayedValue = getValueFromSpecifiedColumnForSpecifiedCase(columnTitle, getCurrentCaseReference());
        if (!displayedValue.contains(expectedValue)) {
            Assert.fail("Expected '" + columnTitle + "' column to contain '" + expectedValue + "', but column value was '" + displayedValue + "'");
        }
    }

    public void assertCaseTotalIs(int expectedTotalOfCases) {
        int totalOfCases = getTotalOfCases();
        if(!(totalOfCases == expectedTotalOfCases)) {
           Assert.fail("Expected total of cases was " + expectedTotalOfCases + ", but displayed total of cases was " + totalOfCases);
        }
    }
}
