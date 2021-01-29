package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.mpam.AccordionMPAM;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Search extends BasePage {

    Workstacks workstacks;

    Homepage homepage;

    SummaryTab summaryTab;

    AccordionDCU accordionDCU;

    UnallocatedCaseView unallocatedCaseView;

    AccordionMPAM accordionMPAM;

    Markup markup;

    PeopleTab peopleTab;

    @FindBy(xpath = "//h1[text()='Search']")
    public WebElementFacade searchPageTitle;

    @FindBy(css = "label[for='caseTypes_MIN']")
    public WebElementFacade searchMINCheckbox;

    @FindBy(css = "label[for='caseTypes_DTEN']")
    public WebElementFacade searchDTENCheckbox;

    @FindBy(css = "label[for='caseTypes_TRO']")
    public WebElementFacade searchTROCheckbox;

    @FindBy(css = "label[for='caseTypes_MPAM']")
    public WebElementFacade searchMPAMCheckbox;

    @FindBy(css = "label[for='caseTypes_MTS']")
    public WebElementFacade searchMTSCheckbox;

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

    @FindBy(xpath = "//label[text()='MPAM Case']")
    public WebElementFacade mpamCaseCheckbox;

    @FindBy(xpath = "//label[text()='MTS Case']")
    public WebElementFacade mtsCaseCheckbox;

    @FindBy(xpath = "//label[text()='Include Home Secretary Interest Cases only']")
    public WebElementFacade includeHomeSecInterestCasesOnlyCheckbox;

    @FindBy(xpath = "//div[@id='CampaignType']//input")
    public WebElementFacade campaignSearchField;

    @FindBy(id = "MinSignOffTeam")
    public WebElementFacade ministerialSignOffDropdown;

    @FindBy(id = "correspondentNameNotMember")
    public WebElementFacade applicantOrConstituentFullNameTextField;

    @FindBy(id = "OfficialEngagement")
    public WebElementFacade telephoneSurgeryOfficialEngagementDropdown;

    public void performSearch() {
        safeClickOn(searchButton);
        assertOnSearchPage();
    }

    //DCU Methods

    public void enterDCUSearchCriteria(String criteria, String value) {
        String dd;
        String mm;
        String yyyy;
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                switch (value.toUpperCase()) {
                    case "MIN":
                        safeClickOn(searchMINCheckbox);
                        break;
                    case "DTEN":
                        safeClickOn(searchDTENCheckbox);
                        break;
                    case "TRO":
                        safeClickOn(searchTROCheckbox);
                        break;
                    case "MPAM":
                        safeClickOn(searchMPAMCheckbox);
                        break;
                    case "MTS":
                        safeClickOn(searchMTSCheckbox);
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                setSessionVariable("searchCaseType").to(value);
                break;
            case "RECEIVED ON OR AFTER DATE":
                dd = value.split("/")[0];
                mm = value.split("/")[1];
                yyyy = value.split("/")[2];
                typeInto(receivedAfterDayTextbox, dd);
                typeInto(receivedAfterMonthTextbox, mm);
                typeInto(receivedAfterYearTextbox, yyyy);
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                dd = value.split("/")[0];
                mm = value.split("/")[1];
                yyyy = value.split("/")[2];
                typeInto(receivedBeforeDayTextbox, dd);
                typeInto(receivedBeforeMonthTextbox, mm);
                typeInto(receivedBeforeYearTextbox, yyyy);
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "CORRESPONDENT NAME":
                safeClickOn(searchCorrespondentTextbox);
                typeInto(searchCorrespondentTextbox, value);
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "TOPIC":
                safeClickOn(searchTopicTextbox);
                typeInto(searchTopicTextbox, value);
                setSessionVariable("searchTopic").to(value);
                break;
            case "SIGN OFF TEAM":
                searchSignOffTeamDropdown.selectByVisibleText(value);
                setSessionVariable("searchSignOffTeam").to(value);
                break;
            case "ACTIVE CASES ONLY":
                if (value.toUpperCase().equals("YES")) {
                    safeClickOn(caseStatusActiveCheckbox);
                }
                break;
            case "HOME SECRETARY INTEREST":
                if (value.toUpperCase().equals("YES")) {
                    safeClickOn(includeHomeSecInterestCasesOnlyCheckbox);
                }
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
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
       String checkSignOff;
        goHome();
        homepage.enterCaseReferenceIntoSearchBar(caseRef);
        homepage.hitEnterCaseReferenceSearchBar();
        if (unallocatedCaseView.allocateToMeLink.isVisible()) {
            safeClickOn(accordionDCU.markupAccordionButton);
            checkSignOff = accordionDCU.privateOfficeTeam.getText();
        } else if (markup.privateOfficeTeamTextField.isVisible()) {
            checkSignOff = markup.privateOfficeTeamTextField.getValue();
        } else {
            goHome();
            safeClickOn(homepage.myCases);
            workstacks.unallocateSelectedCase(caseRef);
            goHome();
            homepage.enterCaseReferenceIntoSearchBar(caseRef);
            homepage.hitEnterCaseReferenceSearchBar();
            safeClickOn(accordionDCU.markupAccordionButton);
            checkSignOff = accordionDCU.privateOfficeTeam.getText();
        }
        return checkSignOff.contains(signOffTeam);
    }

    //MPAM Methods

    public void enterMPAMSearchCriteria(String criteria, String value) {
        String dd;
        String mm;
        String yyyy;
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                typeInto(caseReferenceSearchBox, value);
                setSessionVariable("searchCaseReference").to(value);
                break;
            case "REFERENCE TYPE":
                switch (value.toUpperCase()) {
                    case "MINISTERIAL":
                        mpamRefTypeDropdown.selectByIndex(1);
                        break;
                    case "OFFICIAL":
                        mpamRefTypeDropdown.selectByIndex(2);
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                setSessionVariable("searchReferenceType").to(value);
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                ministerialSignOffDropdown.selectByVisibleText(value);
                setSessionVariable("searchMinisterialSignOffTeam").to(value);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(memberOfParliamentSearchBox);
                memberOfParliamentSearchBox.sendKeys(value);
                waitABit(5000);
                memberOfParliamentSearchBox.sendKeys(Keys.ENTER);
                setSessionVariable("searchMemberOfParliamentName").to(value);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                typeInto(correspondentReferenceNumber, value);
                setSessionVariable("searchCorrespondentReferenceNumber").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                dd = value.split("/")[0];
                mm = value.split("/")[1];
                yyyy = value.split("/")[2];
                typeInto(receivedBeforeDayTextbox, dd);
                typeInto(receivedBeforeMonthTextbox, mm);
                typeInto(receivedBeforeYearTextbox, yyyy);
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "RECEIVED ON OR AFTER DATE":
                dd = value.split("/")[0];
                mm = value.split("/")[1];
                yyyy = value.split("/")[2];
                typeInto(receivedAfterDayTextbox, dd);
                typeInto(receivedAfterMonthTextbox, mm);
                typeInto(receivedAfterYearTextbox, yyyy);
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "CAMPAIGN":
                campaignSearchField.sendKeys(value);
                campaignSearchField.sendKeys(Keys.RETURN);
                setSessionVariable("searchCampaign").to(value);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                typeInto(applicantOrConstituentFullNameTextField, value);
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "ACTIVE CASES ONLY":
                if (value.toUpperCase().equals("YES")) {
                    safeClickOn(caseStatusActiveCheckbox);
                }
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                telephoneSurgeryOfficialEngagementDropdown.selectByVisibleText(value);
                setSessionVariable(value).to("searchTelephoneSurgeryOfficialEngagement");
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void searchBySubstringOfCaseReference() {
        int n = 0;
        String substring = null;
        String firstCharOfSubstring = "";
        String caseRef = sessionVariableCalled("caseReference");
        String split = caseRef.split("/01")[1];
        int randomStringLength = (int)(Math.random() * ((5 - 1) + 1)) + 1;
        while (n <= randomStringLength) {
            substring = split.substring(n, randomStringLength);
            firstCharOfSubstring = String.valueOf(substring.charAt(0));
            if (!firstCharOfSubstring.equals("0")) {
                break;
            }
            n++;
        }
        int caseNumberUpperBound = Integer.parseInt(substring);
        int randomCaseInteger = new Random().nextInt(caseNumberUpperBound);
        String randomCaseIntToString = Integer.toString(randomCaseInteger);
        if (randomCaseInteger < 1000) {
            randomCaseIntToString = "0" + randomCaseIntToString;
        }
        if (randomCaseInteger < 100) {
            randomCaseIntToString = "0" + randomCaseIntToString;
        }
        if (randomCaseInteger < 10) {
            randomCaseIntToString = "0" + randomCaseIntToString;
        }
        String randomCaseRefString = "/012" + randomCaseIntToString;
        setSessionVariable("caseReferenceSubstring").to(randomCaseRefString);
        typeInto(caseReferenceSearchBox, randomCaseRefString);
        safeClickOn(searchButton);
    }

    //Assertions

    public void assertCaseTypeIsOnlyTypeVisible(String caseType) {
        List<WebElementFacade> caseList = findAll("//a[contains(text(), '" + caseType + "')]");
        int numberOfDisplayedCases = workstacks.getTotalOfCases();
        int test = caseList.size();
        assertThat(caseList.size() == numberOfDisplayedCases, is(true));
    }

    public void assertThatSearchedCorrespondentNameIsShownInPeopleTab() {
        String correspondentName = sessionVariableCalled("searchCorrespondentName").toString().toUpperCase();
        peopleTab.assertCorrespondentIsAttachedToCase(correspondentName);
    }

    public void assertThatSearchedTopicNameIsShownInCaseSummary() {
        String topicNameInSummary = sessionVariableCalled("searchTopic").toString();
        summaryTab.primaryTopic.shouldContainText(topicNameInSummary);
    }

    public void assertNoSearchCriteriaErrorMessage() {
        noSearchCriteriaErrorMessage.shouldContainText("No search criteria specified");
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

    public void assertOnSearchPage() {
        numberOfSearchResults.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
        assertPageTitle("Search Results");
    }

    public void assertCurrentCaseIsDisplayedInSearchResults() {
        WebElementFacade currentCase = findBy("//a[text()='" + sessionVariableCalled("caseReference") + "']");
        assertThat(currentCase.isVisible(), is(true));
    }

    public void assertAllDisplayedCaseRefsContainSubstring() {
        List<WebElementFacade> listOfCaseRefs = findAll("//tr/td[1]");
        String substringInput = sessionVariableCalled("caseReferenceSubstring");
        for (WebElementFacade caseRef : listOfCaseRefs) {
            caseRef.shouldContainText(substringInput);
        }
    }

    public void waitUntilSearchPageLoaded() {
        searchPageTitle.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
    }

    private void assertMinisterialSignOffTeamIsCorrect() {
        String ministerialSignOffTeam = sessionVariableCalled("searchMinisterialSignOffTeam");
        if (unallocatedCaseView.allocateToMeLink.isVisible()) {
            accordionMPAM.openCreationAccordion();
            accordionMPAM.getQuestionResponse("Ministerial Sign Off Team");
            String displayedResponse = sessionVariableCalled("response");
            assertThat(displayedResponse.contains(ministerialSignOffTeam), is(true));
        }
        else {
            accordionMPAM.openCaseDetailsAccordion();
            String chosenSelection = new Select(getDriver().findElement(By.xpath("//select"))).getFirstSelectedOption().getText();
            assertThat(chosenSelection.contains(ministerialSignOffTeam), is(true));
        }
    }

    public void assertFirstAndLastResultOf(String criteria) {
        WebElementFacade topSearchResult = findBy("//tr[1]/td/a");
        setSessionVariable("topSearchResult").to(topSearchResult.getText());
        WebElementFacade bottomSearchResult = findBy("//tr[" + workstacks.getTotalOfCases() + "]/td/a");
        setSessionVariable("bottomSearchResult").to(bottomSearchResult.getText());
        switch (criteria.toUpperCase()) {
            case "CORRESPONDENT NAME":
                safeClickOn(topSearchResult);
                peopleTab.selectPeopleTab();
                assertThatSearchedCorrespondentNameIsShownInPeopleTab();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                peopleTab.selectPeopleTab();
                assertThatSearchedCorrespondentNameIsShownInPeopleTab();
                break;
            case "TOPIC":
                safeClickOn(topSearchResult);
                summaryTab.selectSummaryTab();
                assertThatSearchedTopicNameIsShownInCaseSummary();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                summaryTab.selectSummaryTab();
                assertThatSearchedTopicNameIsShownInCaseSummary();
                break;
            case "HOME SEC INTEREST":
                safeClickOn(topSearchResult);
                summaryTab.selectSummaryTab();
                assertThat(summaryTab.homeSecInterest.getText().equals("Yes"), is(true));
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                summaryTab.selectSummaryTab();
                assertThat(summaryTab.homeSecInterest.getText().equals("Yes"), is(true));
                break;
            case "SIGN OFF TEAM":
                getCaseReferenceOfFirstAndLastSearchResults();
                assertThat(checkSignOffTeam(sessionVariableCalled("firstSearchResultCaseReference"), sessionVariableCalled("searchSignOffTeam")),
                        is(true));
                assertThat(checkSignOffTeam(sessionVariableCalled("lastSearchResultCaseReference"), sessionVariableCalled("searchSignOffTeam")), is(true));
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                getCaseReferenceOfFirstAndLastSearchResults();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("firstSearchResultCaseReference"));
                homepage.hitEnterCaseReferenceSearchBar();
                assertMinisterialSignOffTeamIsCorrect();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("lastSearchResultCaseReference"));
                homepage.hitEnterCaseReferenceSearchBar();
                assertMinisterialSignOffTeamIsCorrect();
                break;
        }
    }
}
