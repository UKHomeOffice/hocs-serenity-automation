package com.hocs.test.pages;

import config.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.WebDriver;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Workstacks extends BasePage {

    Homepage homepage;

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

    @FindBy(xpath = "//a[@class='tab'][text()='Summary']")
    public WebElementFacade caseSummaryTab;

    @FindBy(xpath = "//a[@class='tab'][text()='Timeline']")
    public WebElementFacade caseTimelineTab;

    @FindBy(xpath = "//span[@class='govuk-details__summary-text']")
    public WebElementFacade addCaseNoteButton;

    @FindBy(xpath = "//span[@id='case-note-error']")
    public WebElementFacade caseNoteMustNotBeBlankErrorMessage;

    @FindBy(xpath = "//span[@class='govuk-hint'][text()='0']")
    public WebElementFacade zeroItemsInWorkstackCount;

    @FindBy(xpath = "//h1[@class='govuk-heading-l']")
    public WebElementFacade caseReferenceOnAllocationScreen;

    @FindBy(xpath = "//span[@class='govuk-caption-l']")
    public WebElementFacade caseReferenceOnAlreadyAllocatedCase;

    @FindBy(xpath = "//th[text() = 'Primary topic']/following-sibling::td")
    public WebElementFacade primaryTopicName;

    @FindBy(xpath = "//tbody/tr/td[4]")
    public WebElementFacade displayedOwner;

    @FindBy(xpath = "//th[text()='When was the correspondence received?']/following-sibling::td")
    public WebElementFacade summaryWhenWasTheCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='Primary correspondent']/following-sibling::td")
    public WebElementFacade summaryPrimaryCorrespondent;

    @FindBy(xpath = "//h2[text() = 'Active stage']/following-sibling::table[1]/caption")
    public WebElementFacade summaryActiveStage;

    @FindBy(xpath = "//*[@id=\"user-id\"]")
    public WebElementFacade caseDetailsAllocateDropdown;

    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div[1]/form/input")
    public WebElementFacade caseDetailsAllocateButton;

    @FindBy(xpath = "//tbody/tr[1]/td[8]")
    public WebElementFacade topCasePoints;

    @FindBy(xpath = "//th[text()='Reference']")
    public WebElementFacade referenceColumnHeader;

    @FindBy(xpath = "//th[text()='Current Stage']")
    public WebElementFacade currentStageColumnHeader;

    @FindBy(xpath = "//th[text()='Owner']")
    public WebElementFacade ownerColumnHeader;

    @FindBy(xpath = "//th[text()='Owner']/following-sibling::th[1]")
    public WebElementFacade deadlineColumnHeader;

    @FindBy(xpath = "//th[text()='Urgency']")
    public WebElementFacade urgencyColumnHeader;

    @FindBy(xpath = "//th[text()='Days']")
    public WebElementFacade daysColumnHeader;

    // Basic Methods

    public void clickAllocateToMeButton() {
        safeClickOn(allocateToMeButton);
    }

    public void clickAllocateSelectedToMeButton() {
        safeClickOn(allocateSelectedToMeButton);
    }

    public void selectAllocationUserByVisibleText(String allocationUser) {
        safeClickOn(allocateDropdown);
        allocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(allocateButton);
    }

    public void caseDetailsSelectAllocationUserByVisibleText(String allocationUser) {
        safeClickOn(caseDetailsAllocateDropdown);
        caseDetailsAllocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(caseDetailsAllocateButton);
    }

    public int getTotalOfCases() {
        String tempNumberOfItems = totalNumberOfItems.getText().split(" ")[0];

        return Integer.parseInt(tempNumberOfItems);
    }

    public void selectSummaryTab() {
        safeClickOn(caseSummaryTab);
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
        safeClickOn(selectWorkstackFilter);
        typeInto(selectWorkstackFilter, workstackInput);
    }

    public void allocateThreeCasesCreated(User user) {
        refineWorkstackSearchResults("MIN");
        waitABit(500);
        int totalCaseNumber = getTotalOfCases();

        WebElementFacade caseOne = findBy("//tr[" + (totalCaseNumber - 2) + "]//input");
        clickOn(caseOne);
        WebElementFacade caseTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]//input");
        clickOn(caseTwo);
        WebElementFacade caseThree = findBy("//tr[" + totalCaseNumber + "]//input");
        clickOn(caseThree);

        selectAllocationUserByVisibleText(user.getAllocationText());
    }

    public void unallocateThreeCasesFromSelectedUser(User user) {
        refineWorkstackSearchResults("MIN");
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOne = findBy("//tr[" + (totalCaseNumber - 2) + "]//input");
        clickOn(caseOne);
        WebElementFacade caseTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]//input");
        clickOn(caseTwo);
        WebElementFacade caseThree = findBy("//tr[" + totalCaseNumber + "]//input");
        clickOn(caseThree);

        safeClickOn(unallocateButton);
    }

    public void clickDCUMINFilterCard() {
        safeClickOn(dcuMINFilterCard);
    }

    public void clickDCUTENFilterCard() {
        safeClickOn(dcuN10FilterCard);
    }

    public void clickDCUTROFilterCard() {
        safeClickOn(dcuTROFilterCard);
    }

    public void waitForWorkstackToLoad() {
        allocateSelectedToMeButton.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
    }

    public void orderMPAMWorkstackColumn(String team, String column, String order) {
        WebElementFacade selectedHeader = null;
        homepage.selectCorrectMPAMTeamByStage(team);
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
    }

    // Assertions


    public void assertCasesAreFilteredByRef(String caseReference) {
        int totalNumberOfCases = getTotalOfCases();

        List<WebElementFacade> listOfReferences = findAll(
                "//tbody[@class='govuk-table__body']/tr/td[2]/a[contains(text(), '" + caseReference
                        + "')]");
        assertThat(listOfReferences.size(), is(totalNumberOfCases));
    }

    public void assertCaseNoteMustNotBeBlankErrorMessage() {
        caseNoteMustNotBeBlankErrorMessage.shouldContainText("Case note must not be blank");
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

    public void assertThatThereAreNoCasesInWorkstack() {
        zeroItemsInWorkstackCount.shouldContainText("0 Items");
    }

    public void assertCaseReferenceBeforeAllocation() {
        String searchCaseReference = sessionVariableCalled("caseReference").toString();
        caseReferenceOnAllocationScreen.shouldContainText(searchCaseReference);

    }

    public void assertCaseReferenceAfterAllocation() {
        String searchCaseReference = sessionVariableCalled("caseReference").toString();
        caseReferenceOnAlreadyAllocatedCase.shouldContainText(searchCaseReference);
    }

    public void assertAllAllocatedUsersAre(User user) {
        List<WebElementFacade> userAllocated = findAll("//td[contains(text(), '" + user.getUsername() + "')]");
        int totalNumberOfCases = getTotalOfCases();
        assertThat(userAllocated.size() == totalNumberOfCases, is(true));
    }

    public void assertOwnerIs(User owner) {
        displayedOwner.shouldContainText(owner.getUsername());
    }

    private String getStageFromWorkstacksTable() {
        WebElement caseReferenceStage = getDriver().findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseReference")
                        + "']/../following-sibling::td[1]"));
        System.out.println("The case is at the " + caseReferenceStage.getText() + " stage");
        return caseReferenceStage.getText();
    }

    public void assertCaseStage(String stage) {
        assertThat(getStageFromWorkstacksTable().toUpperCase(), is(stage.toUpperCase()));
    }

    public String getAllocatedUserFromWorkstacksTable() {
        WebElementFacade caseOwner = findBy("//a[text()='" + sessionVariableCalled("caseReference")
                + "']/../following-sibling::td[2]");

        return caseOwner.getText();
    }

    public void filterByCurrentCaseReference() {
        typeInto(selectWorkstackFilter, sessionVariableCalled("caseReference"));
    }

    public void assertAssignedUser(User user) {
        WebElementFacade caseOwner = findBy("//a[text()='" + sessionVariableCalled("caseReference")
                + "']/../following-sibling::td[2]");
        waitForAnyTextToAppear(caseOwner, user.getUsername());
        assertThat(getAllocatedUserFromWorkstacksTable().equals(user.getUsername()), is(true));
    }

    public void assertAssignedUserOnThreeCases(User user) {
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOwnerOne = findBy("//tr[" + (totalCaseNumber - 2) + "]/td[4]");

        WebElementFacade caseOwnerTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]/td[4]");

        WebElementFacade caseOwnerThree = findBy("//tr[" + totalCaseNumber + "]/td[4]");

        waitForAnyTextToAppear(caseOwnerOne, user.getUsername());
        caseOwnerOne.shouldContainText(user.getUsername());
        caseOwnerTwo.shouldContainText(user.getUsername());
        caseOwnerThree.shouldContainText(user.getUsername());
    }

    public void assertThatThreeCasesHaveBeenUnassigned() {
        int totalCaseNumber = getTotalOfCases();
        WebElementFacade caseOwnerOne = findBy("//tr[" + (totalCaseNumber - 2) + "]/td[4]");

        WebElementFacade caseOwnerTwo = findBy("//tr[" + (totalCaseNumber - 1) + "]/td[4]");

        WebElementFacade caseOwnerThree = findBy("//tr[" + totalCaseNumber + "]/td[4]");

        waitForAnyTextToAppear(caseOwnerOne, "");
        caseOwnerOne.shouldContainText("");
        caseOwnerTwo.shouldContainText("");
        caseOwnerThree.shouldContainText("");
    }

    public void assertCaseIsAssignedToMe() {
        int totalCases = getTotalOfCases();
        WebElementFacade caseOwner = findBy("//tr[" + totalCases + "]/td[4]");
        caseOwner.shouldContainText(User.AUTOMATION_USER.getUsername());
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
        selectWorkstackFilter.clear();
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
        selectWorkstackFilter.clear();
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
        selectWorkstackFilter.clear();
    }

    public void assertPrimaryCorrespondentIs(String name) {
        summaryPrimaryCorrespondent.shouldContainText(name);
    }

    public void summaryPrintActiveStage() {
        System.out.println(summaryActiveStage.getText());
    }

    public double getPointsOfCurrentCase() {
        WebElementFacade casePoints = find(By.xpath("//a[text()='" + sessionVariableCalled("caseReference") + "']/parent::td"
                + "/following-sibling::td[6]"));
        return Double.parseDouble(casePoints.getText());
    }

    public void assertPointsOfCurrentCaseEqual(String expectedPoints) {
        assertThat(getPointsOfCurrentCase(), is(Double.parseDouble(expectedPoints)));
    }

    public void assertColumnIsOrderedProperly(String column, String order) throws ParseException {
        int currentCase = 2;
        int totalCases = getTotalOfCases();
        while (currentCase <= (totalCases - 1)) {
            switch (column.toUpperCase()) {
                case "REFERENCE":
                    WebElement refNumberCellOne = find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(2)"));
                    WebElement refNumberCellTwo = find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(2)"));
                    String refNumberOne = refNumberCellOne.getText();
                    String refNumberTwo = refNumberCellTwo.getText();
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
                    currentCase += 1;
                    break;
                case "CURRENT STAGE":
                    WebElement currentStageCellOne = find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(3)"));
                    WebElement currentStageCellTwo = find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(3)"));
                    String currentStageOne = currentStageCellOne.getText();
                    String currentStageTwo = currentStageCellTwo.getText();
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
                    currentCase += 1;
                    break;
                case "OWNER":
                    WebElement ownerCellOne = find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(4)"));
                    WebElement ownerCellTwo = find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(4)"));
                    String ownerOne = ownerCellOne.getText();
                    String ownerTwo = ownerCellTwo.getText();
                    switch (order.toUpperCase()) {
                        case "LOWEST TO HIGHEST":
                            assertThat(ownerOne.compareTo(ownerTwo) <= 0, is(true));
                            break;
                        case "HIGHEST TO LOWEST":
                            assertThat(ownerOne.compareTo(ownerTwo) >= 0, is(true));
                            break;
                        default:
                            pendingStep(order + " is not defined within " + getMethodName());
                    }
                    currentCase += 1;
                    break;
                case "DEADLINE":
                     WebElement deadlineCellOne = find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(5)"));
                     WebElement deadlineCellTwo = find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(5)"));
                     String deadlineOneString = deadlineCellOne.getText();
                     String deadlineTwoString = deadlineCellTwo.getText();
                     if (!deadlineOneString.equals("") && !deadlineTwoString.equals("")) {
                         DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                         Date deadlineOne = format.parse(deadlineOneString);
                         Date deadlineTwo = format.parse(deadlineTwoString);
                         switch (order.toUpperCase()) {
                             case "LOWEST TO HIGHEST":
                                 assertThat(deadlineOne.before(deadlineTwo)||deadlineOne.equals(deadlineTwo), is(true));
                                 break;
                             case "HIGHEST TO LOWEST":
                                 assertThat(deadlineOne.after(deadlineTwo)||deadlineOne.equals(deadlineTwo), is(true));
                                 break;
                             default:
                                 pendingStep(order + " is not defined within " + getMethodName());
                         }
                     }
                     currentCase += 1;
                     break;
                case "URGENCY":
                    Hashtable<String, Integer> urgencyDictionary = new Hashtable<String, Integer>();
                    urgencyDictionary.put("Standard", 1);
                    urgencyDictionary.put("Priority", 2);
                    urgencyDictionary.put("Immediate", 3);
                    WebElement urgencyCellOne = find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(6)"));
                    WebElement urgencyCellTwo = find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(6)"));
                    String urgencyOneString = urgencyCellOne.getText();
                    String urgencyTwoString = urgencyCellTwo.getText();
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
                    currentCase += 1;
                    break;
                case "DAYS":
                    WebElement daysCellOne = find(By.cssSelector("tbody > tr:nth-child(" + (currentCase - 1) + ") > td:nth-child(7)"));
                    WebElement daysCellTwo = find(By.cssSelector("tbody > tr:nth-child(" + currentCase + ") > td:nth-child(7)"));
                    String daysOneString = daysCellOne.getText();
                    String daysTwoString = daysCellTwo.getText();
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
                    currentCase += 1;
                    break;
                default:
                    pendingStep(column + " is not defined within " + getMethodName());
            }
        }
    }
}
