package com.hocs.test.pages;

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
    public WebElementFacade totalNumberOfItems;

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

    // Basic Methods

    public void clickAllocateSelectedToMeButton() {
        safeClickOn(allocateSelectedToMeButton);
    }

    public void selectAllocationUserByVisibleText(String allocationUser) {
        safeClickOn(allocateDropdown);
        allocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(allocateButton);
    }

    public void goToCurrentCaseFromWorkstack() {
        WebElementFacade caseReference = find(By.xpath("//a[text()='"+ sessionVariableCalled("caseReference")+"']"));
        safeClickOn(caseReference);
    }

    public void caseDetailsSelectAllocationUserByVisibleText(String allocationUser) {
        safeClickOn(caseDetailsAllocateDropdown);
        caseDetailsAllocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(caseDetailsAllocateButton);
    }

    public int getTotalOfCases() {
        waitFor(totalNumberOfItems);
        String numberOfItems = totalNumberOfItems.getText().split(" ")[0];
        return Integer.parseInt(numberOfItems);
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
        safeClickOn(workstackFilter);
        typeInto(workstackFilter, workstackInput);
    }

    public void selectTakeNextCase() {
        int n = 1;
        List<WebElementFacade> allocatedUsers = findAll("//th[text()='Owner']/ancestor::thead/following-sibling::tbody//td[4]");
        boolean isCaseUnallocated = false;
        while (n <= getTotalOfCases() && !isCaseUnallocated) {
            if (allocatedUsers.get(n).getText().equals("")) {
                WebElementFacade caseReference = findBy("//th[text()='Reference']/ancestor::thead/following-sibling::tbody/tr[" + (n + 1) + "]/td[2"
                        + "]");
                setSessionVariable("nextCaseCaseReference").to(caseReference.getText());
                isCaseUnallocated = true;
            }
            n++;
        }
        safeClickOn(takeNextCaseButton);
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
        WebElement selectedCaseCheckBox = getDriver().findElement(By.xpath("//a[text()='" + caseRef + "']/parent::td/preceding-sibling::td//input"));
        typeInto(workstackFilter, caseRef);
        selectedCaseCheckBox.click();
        safeClickOn(unallocateButton);
    }

    // Assertions

    public void assertCorrectCaseIsTaken() {
        waitFor(allocatedCaseReference);
        assertThat(allocatedCaseReference.getText().equals(sessionVariableCalled("nextCaseCaseReference")), is(true));
    }

    public void assertCaseIsInTheCorrectCampaign() {
        WebElementFacade campaignOfCurrentCase = findBy("//a[text()='" + sessionVariableCalled("caseReference")
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
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebElementFacade thisReference = findBy("//a[text()='" + caseReferenceNumber + "']");
        System.out.println(thisReference);
        assertThat(isElementDisplayed(thisReference), is(trueFalse));
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
        workstackFilter.waitUntilVisible();
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
        typeInto(workstackFilter, sessionVariableCalled("caseReference"));
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
        workstackFilter.clear();
        refineWorkstackSearchResults("DTEN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        workstackFilter.clear();
        refineWorkstackSearchResults("TRO");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        workstackFilter.clear();
    }

    public void assertThatDCUTENisOnlyVisibleCaseType() {
        int totalCases = getTotalOfCases();
        refineWorkstackSearchResults("DTEN");
        assertThat(totalCases != 0, is(true));
        workstackFilter.clear();
        refineWorkstackSearchResults("MIN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        workstackFilter.clear();
        refineWorkstackSearchResults("TRO");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        workstackFilter.clear();
    }

    public void assertThatDCUTROisOnlyVisibleCaseType() {
        int totalCases = getTotalOfCases();
        refineWorkstackSearchResults("TRO");
        assertThat(totalCases != 0, is(true));
        workstackFilter.clear();
        refineWorkstackSearchResults("MIN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        workstackFilter.clear();
        refineWorkstackSearchResults("DTEN");
        totalCases = getTotalOfCases();
        assertThat(totalCases == 0, is(true));
        workstackFilter.clear();
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
                                 assertThat(deadlineOne.before(deadlineTwo)||deadlineOne.equals(deadlineTwo), is(true));
                                 break;
                             case "HIGHEST TO LOWEST":
                                 assertThat(deadlineOne.after(deadlineTwo)||deadlineOne.equals(deadlineTwo), is(true));
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
        WebElementFacade deadlineOfCurrentCase = find(By.xpath("//a[text()='"+ sessionVariableCalled("caseReference") + "']/../following-sibling::td"
                + "[contains(@class,'date-warning')]"));
        assertThat(deadlineOfCurrentCase.isVisible(), is(condition));
    }

    public void assertHigherPriorityCaseIsFirstInWorkstack(String highPriorityCase, String lowPriorityCase) {
        workstackFilter.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        String highPriorityReference = sessionVariableCalled(highPriorityCase);
        String lowPriorityReference = sessionVariableCalled(lowPriorityCase);

        List<WebElementFacade> caseReferenceElements = findAll(By.xpath("//tr/td[2]/a"));
        Boolean highPriorityFirst = false;
        Boolean lowPrioritySecond = false;
        for(WebElementFacade caseReferenceElement: caseReferenceElements) {
            if (caseReferenceElement.getText().equals(highPriorityReference)) {
                highPriorityFirst = true;
            }
            if(caseReferenceElement.getText().equals(lowPriorityReference) && highPriorityFirst) {
                lowPrioritySecond = true;
            }
        }
        assertThat(highPriorityFirst && lowPrioritySecond, is(true));
    }

    public void assertCaseStageContains(String contents) {
            assertThat(getStageFromWorkstacksTable().toUpperCase().contains(contents), is(true));
    }

    public void assertMinisterSignOffTeam() {
        String caseRef = sessionVariableCalled("caseReference");
        String signOffTeam = sessionVariableCalled("signOffTeam");
        WebElementFacade caseSignOffTeamField = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[text()='" + signOffTeam +
                "']");
        caseSignOffTeamField.shouldBeVisible();
    }

    public void assertDueDateOfContributionRequest() {
        String caseRef = sessionVariableCalled("caseReference");
        WebElementFacade caseWithDueDate = findBy("//a[text()='" + caseRef + "']/parent::td/following-sibling::td[contains(text(), '(Contribution "
                + "Requested) due:')]");
        String dueDate = caseWithDueDate.getText().split("due: ")[1];
        assertThat(dueDate.equals(sessionVariableCalled("contributionDueDate")), is(true));
    }
}
