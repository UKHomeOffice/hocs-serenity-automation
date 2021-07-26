package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Workstacks extends BasePage {

    @FindBy(xpath = "//span[@class='govuk-hint'][1]")
    public WebElementFacade totalNumberOfCases;

    @FindBy(xpath = "//button[text()='Allocate selected to me']")
    public WebElementFacade allocateSelectedToMeButton;

    @FindBy(xpath = "//button[text()='Unallocate selected']")
    public WebElementFacade unallocateButton;

    @FindBy(id = "workstack-filter")
    public WebElementFacade workstackFilter;

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
    public WebElementFacade ownerOfTopCaseInWorkstack;

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

    // Basic Methods

    public void clickAllocateSelectedToMeButton() {
        safeClickOn(allocateSelectedToMeButton);
    }

    public boolean ownerOfTopCaseInWorkstackIs(User user) {
        return ownerOfTopCaseInWorkstack.getText().contains(user.getUsername());
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

    public void caseDetailsSelectAllocationUserByVisibleText(String allocationUser) {
        safeClickOn(caseDetailsAllocateDropdown);
        caseDetailsAllocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(caseDetailsAllocateButton);
    }

    public int getTotalOfCases() {
       totalNumberOfCases.withTimeoutOf(Duration.ofSeconds(150)).waitUntilVisible();
        String numberOfCases = totalNumberOfCases.getText().split(" ")[0];
        return Integer.parseInt(numberOfCases);
    }

    public void selectSummaryTab() {
        safeClickOn(caseSummaryTab);
    }

    public String getCorrespondenceReceivedDateFromSummary() {
        selectSummaryTab();
        return summaryWhenWasTheCorrespondenceReceived.getText();
    }

    public void clickCheckboxRelevantToCaseReference() {
        workstackFilter.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
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

    public void refineWorkstackSearchResults(String workstackInput) {
        safeClickOn(workstackFilter);
        workstackFilter.sendKeys(workstackInput);
    }

    public void recordHighestPriorityCases() {
        int n = 1;
        int totalOfCases = getTotalOfCases();
        while (n <= totalOfCases) {
            if (getNthCasesOwner(n).equals("")) {
                caseReferencesList.add(getNthCasesReference(n));
                break;
            }
            n++;
        }
        String highestPriorityUrgency = getNthCasesUrgency(n);
        String highestPriorityDays = getNthCasesDays(n);
        if (totalOfCases > 1) {
            n++;
            while (n <= totalOfCases && getNthCasesUrgency(n).equals(highestPriorityUrgency) && getNthCasesDays(n).equals(highestPriorityDays)) {
                if (getNthCasesOwner(n).equals("")) {
                    caseReferencesList.add(getNthCasesReference(n));
                }
                n++;
            }
        }
    }

    public void selectTakeNextCase() {
        safeClickOn(takeNextCaseButton);
    }

    private String getNthCasesReference(int n) {
        return findBy("//th[text()='Owner']/ancestor::thead/following-sibling::tbody/tr[" + n + "]/td[2]").getText();
    }

    private String getNthCasesOwner(int n) {
        return findBy("//th[text()='Owner']/ancestor::thead/following-sibling::tbody/tr[" + n + "]/td[4]").getText();
    }

    private String getNthCasesUrgency(int n) {
        return findBy("//th[text()='Owner']/ancestor::thead/following-sibling::tbody/tr[" + n + "]/td[7]").getText();
    }

    private String getNthCasesDays(int n) {
        return findBy("//th[text()='Owner']/ancestor::thead/following-sibling::tbody/tr[" + n + "]/td[8]").getText();
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
        workstackFilter.sendKeys(caseRef);
        waitABit(500);
        WebElementFacade selectedCaseCheckBox = findBy("//a[text()='" + caseRef + "']/parent::td/preceding-sibling::td//label");
        safeClickOn(selectedCaseCheckBox);
        safeClickOn(unallocateButton);
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

    public void assertVisibilityOfCaseReference(boolean trueFalse) {
        String caseReferenceNumber
                = getCurrentCaseReference().toString();
        System.out.println(caseReferenceNumber);
        WebElementFacade thisReference = findBy("//a[text()='" + caseReferenceNumber + "']");
        System.out.println(thisReference);
        assertThat(isElementDisplayed(thisReference), is(trueFalse));
    }

    public void assertThatThereAreNoCasesInWorkstack() {
        zeroItemsInWorkstackCount.shouldContainText("0 Items");
    }

    public void assertCaseReferenceBeforeAllocation() {
        String searchCaseReference = getCurrentCaseReference().toString();
        caseReferenceOnAllocationScreen.shouldContainText(searchCaseReference);

    }

    public void assertCaseReferenceAfterAllocation() {
        String searchCaseReference = getCurrentCaseReference().toString();
        caseReferenceOnAlreadyAllocatedCase.shouldContainText(searchCaseReference);
    }

    public void assertAllAllocatedUsersAre(User user) {
        List<WebElementFacade> userAllocated = findAll("//td[contains(text(), '" + user.getUsername() + "')]");
        int totalNumberOfCases = getTotalOfCases();
        assertThat(userAllocated.size() == totalNumberOfCases, is(true));
    }

    public void assertOwnerIs(User owner) {
        ownerOfTopCaseInWorkstack.shouldContainText(owner.getUsername());
    }

    private String getStageFromWorkstacksTable() {
        workstackFilter.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        WebElement caseReferenceStage = getDriver().findElement(
                By.xpath("//a[text()='" + getCurrentCaseReference()
                        + "']/../following-sibling::td[1]"));
        System.out.println("The case is at the " + caseReferenceStage.getText() + " stage");
        return caseReferenceStage.getText();
    }

    public void assertCaseStage(String stage) {
        assertThat(getStageFromWorkstacksTable().toUpperCase(), is(stage.toUpperCase()));
    }

    public String getAllocatedUserFromWorkstacksTable() {
        WebElementFacade caseOwner = findBy("//a[text()='" + getCurrentCaseReference()
                + "']/../following-sibling::td[2]");

        return caseOwner.getText();
    }

    public void filterByCurrentCaseReference() {
        workstackFilter.sendKeys(getCurrentCaseReference());
    }

    public void assertAssignedUser(User user) {
        WebElementFacade caseOwner = findBy("//a[text()='" + getCurrentCaseReference()
                + "']/../following-sibling::td[2]");
        waitForAnyTextToAppear(caseOwner, user.getUsername());
        assertThat(getAllocatedUserFromWorkstacksTable().equals(user.getUsername()), is(true));
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
                + "()='"+ getCurrentUser().getUsername()+ "']");
        caseOwner.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
        assert caseOwner.isVisible();
    }

    private boolean areCasesOfCaseTypePresent(String caseType) {
        refineWorkstackSearchResults(caseType);
        waitABit(1000);
        int totalCases = getTotalOfCases();
        workstackFilter.clear();
        return (totalCases!=0);
    }

    public void assertThatTheOnlyDCUCaseTypePresentIs(String caseType) {
        assertThat(areCasesOfCaseTypePresent("MIN")==(caseType.equals("MIN")), is(true));
        assertThat(areCasesOfCaseTypePresent("DTEN")==(caseType.equals("DTEN")), is(true));
        assertThat(areCasesOfCaseTypePresent("TRO")==(caseType.equals("TRO")), is(true));
    }

    public void assertPrimaryCorrespondentIs(String name) {
        summaryPrimaryCorrespondent.shouldContainText(name);
    }

    public void summaryPrintActiveStage() {
        System.out.println(summaryActiveStage.getText());
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
        workstackFilter.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
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

    public void assertCaseStageContains(String contents) {
        assertThat(getStageFromWorkstacksTable().toUpperCase().contains(contents), is(true));
    }

    public void assertMinisterSignOffTeam() {
        String caseRef = getCurrentCaseReference();
        String signOffTeam = sessionVariableCalled("signOffTeam");
        WebElementFacade caseSignOffTeamField = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[text()='" + signOffTeam +
                "']");
        caseSignOffTeamField.shouldBeVisible();
    }

    public void assertDueDateOfContributionRequest() {
        workstackFilter.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
        String caseRef = getCurrentCaseReference();
        WebElementFacade caseWithDueDate = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[contains(text(), '(Contribution "
                + "Requested) due:')]");
        String dueDate = caseWithDueDate.getText().split("due: ")[1];
        assertThat(dueDate.equals(sessionVariableCalled("contributionDueDate")), is(true));
    }

    public void assertRejectedFieldOfCurrentCase() {
        String caseRef = getCurrentCaseReference();
        WebElementFacade rejectedStageField = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[contains(text(), 'By ')]");
        waitFor(rejectedStageField).withTimeoutOf(Duration.ofSeconds(30));
        assertThat(rejectedStageField.getText().contains(sessionVariableCalled("rejectionStage")), is(true));
    }

    private List<String> getTableHeadersContent() {
        waitFor(workstackFilter);
        List<WebElement> tableHeaders = getDriver().findElements(By.cssSelector(("th[class*='govuk-table__header']")));
        List<String> tableHeadersContent = new ArrayList<>();
        for (WebElement tableHeader : tableHeaders) {
            tableHeadersContent.add(tableHeader.getText());
        }
        return tableHeadersContent;
    }

    private void checkColumnsArePresent(List<String> columns) {
        for (String column : columns) {
            assertThat(visibleColumns.contains(column), is(true));
            visibleColumns.remove(column);
        }
    }

    public void assertExpectedColumnsPresent(String workstack) {
        visibleColumns = getTableHeadersContent();
        List<String> requiredColumns = new ArrayList<>();
        switch (workstack.toUpperCase()) {
            case "DCU MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Current Stage", "Team", "Primary Topic", "Deadline"));
                break;
            case "DCU TEAM":
                requiredColumns.addAll(Arrays.asList("Select", "Correspondent/Reference", "Current Stage", "Owner", "Primary Topic", "Deadline"));
                break;
            case "DCU SEARCH":
                requiredColumns.addAll(Arrays.asList("Correspondent/Reference", "Current Stage", "Owner", "Team", "Primary Topic", "Deadline"));
                break;
            case "UKVI MY CASES":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Ref Type", "Business Area", "Current Stage", "Deadline", "Urgency",
                        "Days"));
                break;
            case "UKVI SEARCH":
                requiredColumns.addAll(Arrays.asList("Reference", "Current Stage", "Owner", "Team", "Deadline", "Members of Parliament",
                        "Constituents/Applicants"));
                break;
            case "MTS TEAM":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Business Area", "Current Stage", "Owner", "Deadline", "Urgency",
                        "Telephone Surgery Official Engagement"));
                break;
            case "CAMPAIGN":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Ref Type", "Business Area", "Owner", "Campaign", "Days"));
                break;
            case "COMPLAINT REGISTRATION":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Current Stage", "Owner", "Deadline", "Severity"));
                break;
            case "COMP SEARCH":
                requiredColumns.addAll(Arrays.asList("Full Name", "Reference", "Deadline", "Current Stage", "Severity", "Postcode", "HO Ref"));
                break;
            case "TRIAGE":
            case "DRAFT":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Current Stage", "Owner", "Minister Sign Off", "Deadline", "Urgency",
                        "Days", "Rejected"));
                break;
            case "CREATION":
                requiredColumns.addAll(Arrays.asList("Select", "Reference", "Current Stage", "Owner", "Minister Sign Off", "Deadline", "Urgency",
                        "Days"));
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
        checkColumnsArePresent(requiredColumns);
        assertThat(visibleColumns.isEmpty(), is(true));
    }

    public void assertTransferDueDateOfCurrentCase() {
        String caseRef = getCurrentCaseReference();
        WebElementFacade deadline = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[4]");
        waitABit(1000);
        assertThat(deadline.getText().equals(sessionVariableCalled("transferDueDate")), is(true));
    }

    public void assertOverdueContributionRequestIsHighlighted() {
        String caseRef = sessionVariableCalled("caseReference");
        WebElementFacade label = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td//span[contains(text(), 'Overdue')]");
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(212, 53, 28, 1)"), is(true));
    }

    public void assertContributionRequestStatus() {
        String caseRef = sessionVariableCalled("caseReference");
        WebElementFacade contributionRequestField = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[2]");
        contributionRequestField.shouldContainText(sessionVariableCalled("expectedWorkstackCRStatus"));
    }
}
