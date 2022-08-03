package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.mpam.AccordionMPAM;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class Search extends BasePage {

    Workstacks workstacks;

    PeopleTab peopleTab;

    SummaryTab summaryTab;

    AccordionMPAM accordionMPAM;

    CaseView caseView;

    Dashboard dashboard;

    @FindBy(xpath = "//label[text()='DCU Ministerial']")
    public WebElementFacade searchMINCheckbox;

    @FindBy(xpath = "//label[text()='DCU Number 10']")
    public WebElementFacade searchDTENCheckbox;

    @FindBy(xpath = "//label[text()='DCU Treat Official']")
    public WebElementFacade searchTROCheckbox;

    @FindBy(xpath = "//label[@for='caseStatus_active']")
    public WebElementFacade caseStatusActiveCheckbox;

    @FindBy(xpath = "//a[text()='No search criteria specified']")
    public WebElementFacade noSearchCriteriaErrorMessage;

    @FindBy(css = "div span[class='govuk-hint']")
    public WebElementFacade numberOfSearchResults;

    @FindBy(id = "reference")
    public WebElementFacade caseReferenceSearchBox;

    @FindBy(id = "RefType")
    public WebElementFacade mpamRefTypeDropdown;

    @FindBy(xpath = "//input[@id='correspondentExternalKey']")
    public WebElementFacade memberOfParliamentSearchBox;

    @FindBy(xpath = "//label[text()='MPAM Case']")
    public WebElementFacade mpamCaseCheckbox;

    @FindBy(xpath = "//label[text()='MTS Case']")
    public WebElementFacade mtsCaseCheckbox;

    @FindBy(xpath = "//label[text()='Include Home Secretary Interest Cases only']")
    public WebElementFacade includeHomeSecInterestCasesOnlyCheckbox;

    @FindBy(id = "ComplainantHORef")
    public WebElementFacade complainantHomeOfficeReferenceTextField;

    @FindBy(xpath = "//label[text()='FOI Request']")
    public WebElementFacade foiRequestCheckbox;

    @FindBy(xpath = "//a[contains(text(), 'Escalate case')]")
    public WebElementFacade escalateCaseHypertext;

    //Enter search criteria

    public void enterDCUSearchCriteria(String criteria, String value) {
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
                    case "MIN + TRO":
                        safeClickOn(searchMINCheckbox);
                        safeClickOn(searchTROCheckbox);
                        break;
                    case "MIN + DTEN":
                        safeClickOn(searchMINCheckbox);
                        safeClickOn(searchDTENCheckbox);
                        break;
                    case "TRO + DTEN":
                        safeClickOn(searchTROCheckbox);
                        safeClickOn(searchDTENCheckbox);
                        break;
                    case "ALL DCU CASE TYPES":
                        safeClickOn(searchMINCheckbox);
                        safeClickOn(searchTROCheckbox);
                        safeClickOn(searchDTENCheckbox);
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                setSessionVariable("searchCaseType").to(value);
                break;
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
                break;
            case "RECEIVED ON OR AFTER DATE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or after");
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                safeClickOn(searchMINCheckbox);
                enterDateIntoDateFieldsWithHeading(value, "Received on or before");
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(memberOfParliamentSearchBox);
                memberOfParliamentSearchBox.sendKeys(value);
                waitABit(5000);
                memberOfParliamentSearchBox.sendKeys(Keys.ENTER);
                setSessionVariable("searchMemberOfParliamentName").to(value);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent (non-MP)");
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "CORRESPONDENT POSTCODE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent Postcode");
                setSessionVariable("searchCorrespondentPostcode").to(value);
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent Email Address");
                setSessionVariable("searchCorrespondentEmailAddress").to(value);
                break;
            case "TOPIC":
                enterSpecificTextIntoTextFieldWithHeading(value, "Topic");
                setSessionVariable("searchTopic").to(value);
                break;
            case "SIGN OFF TEAM":
                selectSpecificOptionFromDropdownWithHeading(value, "Sign-off team");
                setSessionVariable("searchSignOffTeam").to(value);
                break;
            case "ACTIVE CASES ONLY":
                if (value.equalsIgnoreCase("YES")) {
                    safeClickOn(caseStatusActiveCheckbox);
                }
                setSessionVariable("searchActiveCases").to(value);
                break;
            case "HOME SECRETARY INTEREST":
                if (value.equalsIgnoreCase("YES")) {
                    safeClickOn(includeHomeSecInterestCasesOnlyCheckbox);
                }
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void enterMPAMSearchCriteria(String criteria, String value) {
        if (mpamCaseCheckbox.isCurrentlyVisible()) {
            safeClickOn(mpamCaseCheckbox);
        }
        if (mtsCaseCheckbox.isCurrentlyVisible()) {
            safeClickOn(mtsCaseCheckbox);
        }
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
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
                selectSpecificOptionFromDropdownWithHeading(value, "Ministerial sign off team");
                setSessionVariable("searchMinisterialSignOffTeam").to(value);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                selectSpecificOptionFromTypeaheadWithHeading(value, "Member of parliament name");
                setSessionVariable("searchMemberOfParliamentName").to(value);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent reference number");
                setSessionVariable("searchCorrespondentReferenceNumber").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or before");
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "RECEIVED ON OR AFTER DATE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or after");
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "CAMPAIGN":
                selectSpecificOptionFromTypeaheadWithHeading(value, "Campaign");
                setSessionVariable("searchCampaign").to(value);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent full name (applicant or constituent)");
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "ACTIVE CASES ONLY":
                if (value.equalsIgnoreCase("YES")) {
                    safeClickOn(caseStatusActiveCheckbox);
                }
                setSessionVariable("searchActiveCases").to(value);
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void enterComplaintsSearchCriteria(String criteria, String value) {
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                switch (value.toUpperCase()) {
                    case "COMP":
                        checkSpecificCheckbox("Complaint Case");
                        break;
                    case "COMP2":
                        checkSpecificCheckbox("Complaint Case - Stage 2");
                        break;
                    case "IEDET":
                        checkSpecificCheckbox("IE Detention Case");
                        break;
                    case "SMC":
                        checkSpecificCheckbox("Serious Misconduct");
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                setSessionVariable("searchCaseType").to(value);
                break;
            case "CORRESPONDENT FULL NAME":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent full name");
                setSessionVariable("searchCorrespondentFullName").to(value);
                break;
            case "CORRESPONDENT POSTCODE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent postcode");
                setSessionVariable("searchCorrespondentPostcode").to(value);
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent email address");
                setSessionVariable("searchCorrespondentEmailAddress").to(value);
                break;
            case "COMPLAINANT DATE OF BIRTH":
                enterDateIntoDateFieldsWithHeading(value, "Complainant date of birth");
                setSessionVariable("searchComplainantDateOfBirth").to(value);
                break;
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
                setSessionVariable("searchCaseReference").to(value);
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Complainant Home Office Reference");
                setSessionVariable("searchComplainantHomeOfficeReference").to(value);
                break;
            case "PSU REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "PSU Reference");
                setSessionVariable("searchPSUReference").to(value);
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void enterBFSearchCriteria(String criteria, String value) {
        switch (criteria.toUpperCase()) {
            case "CORRESPONDENT FULL NAME":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent full name");
                setSessionVariable("searchCorrespondentFullName").to(value);
                break;
            case "CORRESPONDENT POSTCODE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent postcode");
                setSessionVariable("searchCorrespondentPostcode").to(value);
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent email address");
                setSessionVariable("searchCorrespondentEmailAddress").to(value);
                break;
            case "COMPLAINANT DATE OF BIRTH":
                enterDateIntoDateFieldsWithHeading(value, "Complainant date of birth");
                setSessionVariable("searchComplainantDateOfBirth").to(value);
                break;
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
                setSessionVariable("searchCaseReference").to(value);
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                complainantHomeOfficeReferenceTextField.sendKeys(value);
                setSessionVariable("searchComplainantHomeOfficeReference").to(value);
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void enterFOISearchCriteria(String criteria, String value) {
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                safeClickOn(foiRequestCheckbox);
                break;
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
                setSessionVariable("searchCaseReference").to(value);
                break;
            case "RECEIVED ON OR AFTER":
                enterDateIntoDateFieldsWithHeading(value, "Received on or after");
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "RECEIVED ON OR BEFORE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or before");
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "CORRESPONDENT (NON-MP)":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent (non-MP)");
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "TOPIC":
                enterSpecificTextIntoTextFieldWithHeading(value, "Topic");
                setSessionVariable("searchTopic").to(value);
                break;
            case "ACTIVE CASES ONLY":
                //This doesn't really work since the 'soft closed' cases are still technically active
                safeClickOn(caseStatusActiveCheckbox);
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void enterTOSearchCriteria(String criteria, String value) {
        checkSpecificCheckbox("Treat Official");
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
                setSessionVariable("searchCaseReference").to(value);
                break;
            case "RECEIVED ON OR AFTER":
                enterDateIntoDateFieldsWithHeading(value, "Received on or after");
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "RECEIVED ON OR BEFORE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or before");
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "CORRESPONDENT FULL NAME":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent full name");
                setSessionVariable("searchCorrespondentFullName").to(value);
                break;
            case "CORRESPONDENT POSTCODE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent postcode");
                setSessionVariable("searchCorrespondentPostcode").to(value);
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent email address");
                setSessionVariable("searchCorrespondentEmailAddress").to(value);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent reference number");
                setSessionVariable("searchCorrespondentReferenceNumber").to(value);
                break;
            case "ACTIVE CASES ONLY":
                checkSpecificCheckbox("Include Active Cases only");
                setSessionVariable("searchActiveCases").to(true);
                break;
            case "CAMPAIGN":
                selectSpecificOptionFromTypeaheadWithHeading(value, "Campaign");
                setSessionVariable("searchCampaign").to(value);
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void searchByCaseReference(String caseReference) {
        enterSpecificTextIntoTextFieldWithHeading(caseReference, "Case reference");
        safeClickOn(searchButton);
    }


    public void searchBySubstringOfCaseReference() {
        int n = 0;
        String substring = null;
        String firstCharOfSubstring = "";
        String caseRef = getCurrentCaseReference();
        String split = caseRef.split("/01")[1];
        int randomStringLength = (int) (Math.random() * ((5 - 1) + 1)) + 1;
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
        searchByCaseReference(randomCaseRefString);
    }

    public boolean checkVisibilityOfEscalationHypertext() {
        return escalateCaseHypertext.isVisible();
    }

    public void clickEscalateComplaintsCaseToStage2() {
        safeClickOn(escalateCaseHypertext);
    }

    //Assertions

    public void assertCurrentCaseIsDisplayed() {
        int n = 0;
        List<WebElementFacade> listOfCaseRefs = findAll("//tr[@class='govuk-radios govuk-table__row']//a");
        while (n < listOfCaseRefs.size()) {
            String caseRef = listOfCaseRefs.get(n).getText();
            if (caseRef.contains(getCurrentCaseReference())) {
                listOfCaseRefs.get(n).shouldContainText(getCurrentCaseReference());
                break;
            }
            n++;
        }
    }

    public void assertNoSearchCriteriaErrorMessage() {
        noSearchCriteriaErrorMessage.shouldContainText("No search criteria specified");
    }

    public void assertAllDisplayedCaseRefsContainSubstring() {
        List<WebElementFacade> listOfCaseRefs = findAll("//tr/td[1]");
        String substringInput = sessionVariableCalled("caseReferenceSubstring");
        for (WebElementFacade caseRef : listOfCaseRefs) {
            caseRef.shouldContainText(substringInput);
        }
    }

    public void assertDCUInformationRandomSearchResult(String criteria) {
        waitForResultsPage();
        Date searchDate = null;
        Date caseDate = null;
        boolean trueFalse;
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed) + 1;
        WebElementFacade randomSearchResult = findBy("//tr[" + randomNumber + "]/td/a");
        setSessionVariable("randomCaseRef").to(randomSearchResult.getText());
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                List<WebElementFacade> searchResultCaseRefs = findAll("//tr/td/a");
                assertThat(searchResultCaseRefs.size() == numberOfCasesDisplayed, is(true));
                break;
            case "RECEIVED ON OR AFTER DATE":
                safeClickOn(randomSearchResult);
                caseView.waitForCaseToLoad();
                try {
                    searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrAfterDate"));
                    caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(summaryTab.getSummaryTabValueForGivenHeader("When was the correspondence "
                            + "received?"));
                } catch (ParseException pE) {
                    System.out.println("Could not parse dates");
                }
                assert caseDate != null;
                trueFalse = (caseDate.after(searchDate) || caseDate.equals(searchDate));
                assertThat(trueFalse, is(true));
                break;
            case "RECEIVED ON OR BEFORE DATE":
                safeClickOn(randomSearchResult);
                caseView.waitForCaseToLoad();
                searchDate = null;
                caseDate = null;
                try {
                    searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrBeforeDate"));
                    caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(summaryTab.getSummaryTabValueForGivenHeader("When was the correspondence "
                            + "received?"));
                } catch (ParseException pE) {
                    System.out.println("Could not parse dates");
                }
                assert caseDate != null;
                trueFalse = (caseDate.before(searchDate) || caseDate.equals(searchDate));
                assertThat(trueFalse, is(true));
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertMPCorrespondentIsAddedToTheCase(sessionVariableCalled("searchMemberOfParliamentName"));
                break;
            case "PUBLIC CORRESPONDENT NAME":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertPublicCorrespondentAddedToTheCase(sessionVariableCalled("searchCorrespondentName"));
                break;
            case "CORRESPONDENT POSTCODE":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertCorrespondentPostcode(sessionVariableCalled("searchCorrespondentPostcode"));
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertCorrespondentEmailAddress(sessionVariableCalled("searchCorrespondentEmailAddress"));
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                WebElementFacade correspondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                correspondentRefNumber.shouldContainText(sessionVariableCalled("searchCorrespondentReferenceNumber"));
                break;
            case "TOPIC":
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                summaryTab.primaryTopic.shouldContainText(sessionVariableCalled("searchTopic"));
                break;
            case "SIGN OFF TEAM":
                String signOffTeam;
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                if (summaryTab.overridePrivateOfficeTeam.isVisible()) {
                    signOffTeam = summaryTab.overridePrivateOfficeTeam.getText();
                } else {
                    signOffTeam = summaryTab.privateOfficeTeam.getText();
                }
                String expectedSignOffTeam = sessionVariableCalled("searchSignOffTeam");
                if (!signOffTeam.equalsIgnoreCase(expectedSignOffTeam)) {
                    Assert.fail("Displayed sign off team '" + signOffTeam + "' did not match expected sign off team '" + expectedSignOffTeam + "'");
                }
                break;
            case "ACTIVE CASES ONLY":
                List<WebElementFacade> activeCases = findAll("//td[2][not(text() = 'Closed')]");
                if (sessionVariableCalled("searchActiveCases").toString().toUpperCase().equals("YES")) {
                    assertThat(!activeCases.isEmpty(), is(true));
                }
                break;
            case "HOME SECRETARY INTEREST":
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                assertThat(summaryTab.homeSecInterest.getText().equals("Yes"), is(true));
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void assertMPAMInformationRandomSearchResult(String criteria) {
        waitForResultsPage();
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = (new Random().nextInt(numberOfCasesDisplayed)) + 1;
        WebElementFacade randomSearchResult = findBy("//tr[" + randomNumber + "]/td/a");
        System.out.print(randomSearchResult.getText() + " is the case reference of the randomly selected case");
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                String caseRef;
                safeClickOn(randomSearchResult);
                if (caseView.allocateToMeLink.isVisible()) {
                    caseRef = header1.getText();
                } else {
                    caseRef = headerCaption1.getText();
                }
                assertThat(caseRef.equals("searchCaseReference"), is(true));
                break;
            case "REFERENCE TYPE":
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                summaryTab.isMinisterialResponseRequired.shouldContainText(sessionVariableCalled("searchReferenceType"));
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                String displayedSignOffTeam = "";
                List<WebElementFacade> listOfUnallocatedSearchResults = findAll("//td[3][not(contains(text(), '" + getCurrentUser().getUsername() +
                        "'))]/preceding-sibling::td/a");
                int randomListValue = new Random().nextInt(listOfUnallocatedSearchResults.size());
                WebElementFacade randomUnallocatedSearchResult = listOfUnallocatedSearchResults.get(randomListValue);
                safeClickOn(randomUnallocatedSearchResult);
                if (accordionMPAM.creationAccordionButton.isVisible()) {
                    accordionMPAM.openCreationAccordion();
                    displayedSignOffTeam = accordionMPAM.creationMinisterialSignOffTeam.getText().split(": ")[1];
                }
                String ministerialSignOffTeam = sessionVariableCalled("searchMinisterialSignOffTeam");
                assertThat(displayedSignOffTeam.contains(ministerialSignOffTeam), is(true));
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertMPCorrespondentIsAddedToTheCase(sessionVariableCalled("searchMemberOfParliamentName"));
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                WebElementFacade correspondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                correspondentRefNumber.shouldContainText(sessionVariableCalled("searchCorrespondentReferenceNumber"));
                break;
            case "RECEIVED ON OR BEFORE DATE":
            case "RECEIVED ON OR AFTER DATE":
                assertDCUInformationRandomSearchResult(criteria);
                break;
            case "CAMPAIGN":
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                summaryTab.assertCampaignInSummaryTabIsCorrect(sessionVariableCalled("searchCampaign"));
                break;
            case "PUBLIC CORRESPONDENT NAME":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertPublicCorrespondentAddedToTheCase(sessionVariableCalled("searchCorrespondentName"));
                break;
            case "ACTIVE CASES ONLY":
                List<WebElementFacade> activeCases = findAll("//td[2][not(text() = 'Closed')]");
                if (sessionVariableCalled("searchActiveCases").toString().equalsIgnoreCase("YES")) {
                    assertThat(!activeCases.isEmpty(), is(true));
                }
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void assertComplaintsInformationRandomSearchResult(String criteria) {
        waitForResultsPage();
        WebElementFacade cell = null;
        String displayedValue = null;
        String expectedValue = null;
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed) + 1;
        WebElementFacade randomSearchResultHypertext = findBy("//tr[" + randomNumber + "]/td/a");
        String randomSearchResult = randomSearchResultHypertext.getText();
        String caseType = randomSearchResult.split("/")[0];
        setSessionVariable("randomCaseRef").to(randomSearchResult);
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                expectedValue = sessionVariableCalled("searchCaseType");
                List<WebElementFacade> listOfCaseRefs = findAll("//td[2]/a[contains(text(), '" + expectedValue + "/')]");
                assertThat(numberOfCasesDisplayed == listOfCaseRefs.size(), is(true));
                displayedValue = expectedValue;
                break;
            case "CORRESPONDENT FULL NAME":
                if (!caseType.equalsIgnoreCase("IEDET")) {
                    cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/preceding-sibling::td");
                } else {
                    safeClickOn(randomSearchResultHypertext);
                    peopleTab.selectPeopleTab();
                    cell = findBy("//th[text()='Name']/following-sibling::td");
                }
                expectedValue = sessionVariableCalled("searchCorrespondentFullName");
                break;
            case "CORRESPONDENT POSTCODE":
                if (!caseType.equalsIgnoreCase("IEDET")) {
                    if (!caseType.equalsIgnoreCase("SMC") && !caseType.equalsIgnoreCase("POGR")) {
                        cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/following-sibling::td[4]");
                    } else {
                        cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/following-sibling::td[3]");
                    }
                } else {
                    safeClickOn(randomSearchResultHypertext);
                    peopleTab.selectPeopleTab();
                    cell = findBy("//th[text()='Address']/following-sibling::td/span[4]");
                }
                expectedValue = sessionVariableCalled("searchCorrespondentPostcode");
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                safeClickOn(randomSearchResultHypertext);
                peopleTab.selectPeopleTab();
                cell = findBy("//th[text()='Email address']/following-sibling::td");
                expectedValue = sessionVariableCalled("searchCorrespondentEmailAddress");
                break;
            case "COMPLAINANT DATE OF BIRTH":
                safeClickOn(randomSearchResultHypertext);
                caseView.waitForCaseToLoad();
                summaryTab.selectSummaryTab();
                if (!accordionSectionIsVisible("Registration") && !accordionSectionIsVisible("Stage 2 Registration") && summaryTab.activeStage.isVisible()) {
                    summaryTab.assertSummaryContainsExpectedValueForGivenHeader(getCurrentUser().getUsername(), "User");
                    String assignedTeam = summaryTab.getSummaryTabValueForGivenHeader("Team");
                    dashboard.goToDashboard();
                    dashboard.selectWorkstackByTeamName(assignedTeam);
                    workstacks.unallocateSelectedCase(randomSearchResult);
                    workstacks.selectSpecificCaseReferenceLink(randomSearchResult);
                }
                if (caseType.equalsIgnoreCase("COMP") || caseType.equalsIgnoreCase("IEDET") || caseType.equalsIgnoreCase("SMC")) {
                    openOrCloseAccordionSection("Registration");
                } else if (caseType.equalsIgnoreCase("POGR")) {
                    openOrCloseAccordionSection("Data Input");
                } else {
                    openOrCloseAccordionSection("Stage 2 Registration");
                }
                displayedValue = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Date of Birth").get(0);
                expectedValue = sessionVariableCalled("searchComplainantDateOfBirth");
                break;
            case "CASE REFERENCE":
                cell = randomSearchResultHypertext;
                expectedValue = sessionVariableCalled("searchCaseReference");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                if (!caseType.equalsIgnoreCase("IEDET")) {
                    if (!caseType.equalsIgnoreCase("SMC")) {
                        cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/following-sibling::td[5]");
                    } else {
                        cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/following-sibling::td[4]");
                    }
                } else {
                    safeClickOn(randomSearchResultHypertext);
                    caseView.waitForCaseToLoad();
                    if (!accordionSectionIsVisible("Registration")) {
                        summaryTab.selectSummaryTab();
                        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(getCurrentUser().getUsername(), "User");
                        String assignedTeam = summaryTab.getSummaryTabValueForGivenHeader("Team");
                        dashboard.goToDashboard();
                        dashboard.selectWorkstackByTeamName(assignedTeam);
                        workstacks.unallocateSelectedCase(randomSearchResult);
                        workstacks.selectSpecificCaseReferenceLink(randomSearchResult);
                    }
                    openOrCloseAccordionSection("Registration");
                    displayedValue = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Home Office Reference").get(0);
                }
                expectedValue = sessionVariableCalled("searchComplainantHomeOfficeReference");
                break;
            case "PSU REFERENCE":
                expectedValue = sessionVariableCalled("searchPSUReference");
                List<WebElementFacade> listOfPSUReference = findAll("//a/parent::td/following-sibling::td[5]");
                assertThat(numberOfCasesDisplayed == listOfPSUReference.size(), is(true));
                displayedValue = expectedValue;
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
        if (displayedValue == null) {
            assert cell != null;
            displayedValue = cell.getText();
        }
        assertThat(displayedValue.equalsIgnoreCase(expectedValue), is(true));
    }

    public void assertBFInformationRandomSearchResult(String criteria) {
        waitForResultsPage();
        WebElementFacade cell = null;
        String displayedValue = "";
        String expectedValue = null;
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed) + 1;
        WebElementFacade randomSearchResultHypertext = findBy("//tr[" + randomNumber + "]/td/a");
        String randomSearchResult = randomSearchResultHypertext.getText();
        setSessionVariable("randomCaseRef").to(randomSearchResult);
        switch (criteria.toUpperCase()) {
            case "CORRESPONDENT FULL NAME":
                cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/preceding-sibling::td");
                expectedValue = sessionVariableCalled("searchCorrespondentFullName");
                break;
            case "CORRESPONDENT POSTCODE":
                cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/following-sibling::td[3]");
                expectedValue = sessionVariableCalled("searchCorrespondentPostcode");
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                safeClickOn(randomSearchResultHypertext);
                caseView.waitForCaseToLoad();
                peopleTab.selectPeopleTab();
                cell = findBy("//th[text()='Email address']/following-sibling::td");
                expectedValue = sessionVariableCalled("searchCorrespondentEmailAddress");
                break;
            case "COMPLAINANT DATE OF BIRTH":
                safeClickOn(randomSearchResultHypertext);
                caseView.waitForCaseToLoad();
                if (!accordionSectionIsVisible("Case Registration") && !accordionSectionIsVisible("Case Registration (Stage 2)") ) {
                    summaryTab.selectSummaryTab();
                    summaryTab.assertSummaryContainsExpectedValueForGivenHeader(getCurrentUser().getUsername(), "User");
                    String assignedTeam = summaryTab.getSummaryTabValueForGivenHeader("Team");
                    dashboard.goToDashboard();
                    dashboard.selectWorkstackByTeamName(assignedTeam);
                    workstacks.unallocateSelectedCase(randomSearchResult);
                    workstacks.selectSpecificCaseReferenceLink(randomSearchResult);
                    caseView.waitForCaseToLoad();
                }
                if (accordionSectionIsVisible("Case Registration")) {
                    openOrCloseAccordionSection("Case Registration");
                } else if (accordionSectionIsVisible("Case Registration (Stage 2)")) {
                    openOrCloseAccordionSection("Case Registration (Stage 2)");
                }
                displayedValue = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Date of Birth").get(0);
                expectedValue = sessionVariableCalled("searchComplainantDateOfBirth");
                break;
            case "CASE REFERENCE":
                cell = randomSearchResultHypertext;
                expectedValue = sessionVariableCalled("searchCaseReference");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                cell = findBy("//a[text()='" + randomSearchResult + "']/parent::td/following-sibling::td[4]");
                expectedValue = sessionVariableCalled("searchComplainantHomeOfficeReference");
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
        if (!criteria.equalsIgnoreCase("COMPLAINANT DATE OF BIRTH")) {
            displayedValue = cell.getText();
        }
        assertThat(displayedValue.equalsIgnoreCase(expectedValue), is(true));
    }

    public void assertFOIInformationRandomSearchResult(String criteria) {
        int n = 0;
        Date searchDate = null;
        Date caseDate = null;
        boolean trueFalse;
        List<WebElementFacade> listOfReceivedDates = findAll("//td[3]");
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed) + 1;
        WebElementFacade randomSearchResultHypertext = findBy("//tr[" + randomNumber + "]/td/a");
        String randomSearchResult = randomSearchResultHypertext.getText();
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                List<WebElementFacade> listOfCaseRefs = findAll("//a[contains(text(), 'FOI')]");
                assertThat(listOfCaseRefs.size() == numberOfCasesDisplayed, is(true));
                break;
            case "CASE REFERENCE":
                String caseRef = sessionVariableCalled("searchCaseReference");
                assertThat(randomSearchResult.equals(caseRef), is(true));
                break;
            case "RECEIVED ON OR AFTER":
                while (n < numberOfCasesDisplayed) {
                    try {
                        searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrAfterDate"));
                        caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(listOfReceivedDates.get(n).getText());
                    } catch (ParseException pE) {
                        System.out.println("Could not parse dates");
                    }
                    assert caseDate != null;
                    trueFalse = (caseDate.after(searchDate) || caseDate.equals(searchDate));
                    assertThat(trueFalse, is(true));
                    n++;
                }
                break;
            case "RECEIVED ON OR BEFORE":
                while (n < numberOfCasesDisplayed) {
                    try {
                        searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrBeforeDate"));
                        caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(listOfReceivedDates.get(n).getText());
                    } catch (ParseException pE) {
                        System.out.println("Could not parse dates");
                    }
                    assert caseDate != null;
                    trueFalse = (caseDate.before(searchDate) || caseDate.equals(searchDate));
                    assertThat(trueFalse, is(true));
                    n++;
                }
                break;
            case "CORRESPONDENT (NON-MP)":
                String correspondentName = sessionVariableCalled("searchCorrespondentName").toString().toUpperCase();
                List<WebElementFacade> listOfCasesWithCorrespondent = findAll("//td[contains(text(), '" + correspondentName + "')]");
                while (n < listOfCasesWithCorrespondent.size()) {
                    assertThat(listOfCasesWithCorrespondent.get(n).equals(numberOfSearchResults), is(true));
                    n++;
                }
                break;
            case "TOPIC":
                safeClickOn(randomSearchResultHypertext);
                summaryTab.selectSummaryTab();
                String displayedTopic = summaryTab.foiTopic.getText();
                String searchedTopic = sessionVariableCalled("searchTopic");
                assertThat(displayedTopic.equals(searchedTopic), is(true));
                break;
            case "ACTIVE CASES ONLY":
                //This doesn't really work since the 'soft closed' cases are still technically active
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void assertTOInformationRandomSearchResult(String criteria) throws ParseException {
        int n = 0;
        Date searchDate = null;
        Date caseDate = null;
        WebElementFacade randomResultReceivedDateCell = findBy("//th[text()='Date Received']/following-sibling::td");
        String displayedReceivedDate;
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed) + 1;
        WebElementFacade randomSearchResultHypertext = findBy("//tr[" + randomNumber + "]/td/a");
        String randomSearchResult = randomSearchResultHypertext.getText();
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                String caseRef = sessionVariableCalled("searchCaseReference");
                assertThat(randomSearchResult.equals(caseRef), is(true));
                break;
            case "RECEIVED ON OR AFTER":
                safeClickOn(randomSearchResultHypertext);
                summaryTab.selectSummaryTab();
                displayedReceivedDate = randomResultReceivedDateCell.getText();
                searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrAfterDate"));
                caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(displayedReceivedDate);
                assertThat(!caseDate.before(searchDate), is(true));
                break;
            case "RECEIVED ON OR BEFORE":
                safeClickOn(randomSearchResultHypertext);
                summaryTab.selectSummaryTab();
                displayedReceivedDate = randomResultReceivedDateCell.getText();
                searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrBeforeDate"));
                caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(displayedReceivedDate);
                assertThat(!caseDate.after(searchDate), is(true));
                break;
            case "CORRESPONDENT FULL NAME":
                String searchCorrespondentFullName = sessionVariableCalled("searchCorrespondentFullName");
                safeClickOn(randomSearchResultHypertext);
                peopleTab.selectPeopleTab();
                WebElementFacade primaryCorrespondentFullName = findBy(
                        "//h2[text()='Correspondent (primary)']/following-sibling::table//th[text()='Name']/following-sibling::td");
                primaryCorrespondentFullName.shouldContainText(searchCorrespondentFullName);
                break;
            case "CORRESPONDENT POSTCODE":
                String searchCorrespondentPostcode = sessionVariableCalled("searchCorrespondentPostcode");
                safeClickOn(randomSearchResultHypertext);
                peopleTab.selectPeopleTab();
                WebElementFacade primaryCorrespondentPostcode = findBy(
                        "//h2[text()='Correspondent (primary)']/following-sibling::table//th[text()='Address']/following-sibling::td/span[4]");
                primaryCorrespondentPostcode.shouldContainText(searchCorrespondentPostcode);
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                String searchCorrespondentEmailAddress = sessionVariableCalled("searchCorrespondentEmailAddress");
                safeClickOn(randomSearchResultHypertext);
                peopleTab.selectPeopleTab();
                WebElementFacade primaryCorrespondentEmailAddress = findBy(
                        "//h2[text()='Correspondent (primary)']/following-sibling::table//th[text()='Email address']/following-sibling::td");
                primaryCorrespondentEmailAddress.shouldContainText(searchCorrespondentEmailAddress);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                String searchCorrespondentReferenceNumber = sessionVariableCalled("searchCorrespondentReferenceNumber");
                safeClickOn(randomSearchResultHypertext);
                peopleTab.selectPeopleTab();
                WebElementFacade primaryCorrespondentReferenceNumber = findBy(
                        "//h2[text()='Correspondent (primary)']/following-sibling::table//th[text()='Reference']/following-sibling::td");
                primaryCorrespondentReferenceNumber.shouldContainText(searchCorrespondentReferenceNumber);
                break;
            case "ACTIVE CASES ONLY":
                List<WebElementFacade> listOfSearchResultCurrentStage = findAll("//a/parent::td/following-sibling::td[1]");
                while (n < listOfSearchResultCurrentStage.size()) {
                    String currentStage = listOfSearchResultCurrentStage.get(n).getText();
                    if (currentStage.equalsIgnoreCase("Closed")) {
                        Assert.fail("Closed case displayed in 'Active only' search result");
                        break;
                    }
                    assertThat(!currentStage.equalsIgnoreCase("Closed"), is(true));
                    n++;
                }
                break;
            case "CAMPAIGN":
                String searchCampaign = sessionVariableCalled("searchCampaign");
                safeClickOn(randomSearchResultHypertext);
                summaryTab.selectSummaryTab();
                WebElementFacade randomCaseCampaignName = findBy("//th[text()='Campaign name']/following-sibling::td");
                String displayedCampaignName = randomCaseCampaignName.getText();
                assertThat(displayedCampaignName.toUpperCase().contains(searchCampaign.toUpperCase()), is(true));
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void waitForResultsPage() {
        numberOfSearchResults.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
    }

    public void waitForSearchCriteriaPage() {
        searchButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
    }

    public int getNumberOfSearchResults() {
        return Integer.parseInt(numberOfSearchResults.getText().split("\\s+")[0]);
    }

    public boolean zeroSearchResultsReturned() {
        return getNumberOfSearchResults() == 0;
    }

    public void selectComplaintsStage2CaseRefOfEscalatedComplaintsCase(String stage1CaseRef) {
        WebElementFacade stage2CaseRef = findBy("//a[text()='" + stage1CaseRef + "']/parent::td/following-sibling::td/a");
        safeClickOn(stage2CaseRef);
    }
}
