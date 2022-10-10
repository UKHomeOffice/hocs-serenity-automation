package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.mpam.AccordionMPAM;
import config.CaseType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class Search extends BasePage {

    Workstacks workstacks;

    PeopleTab peopleTab;

    SummaryTab summaryTab;

    AccordionMPAM accordionMPAM;

    CaseView caseView;

    @FindBy(xpath = "//label[@for='caseStatus_active']")
    public WebElementFacade caseStatusActiveCheckbox;

    @FindBy(xpath = "//a[text()='No search criteria specified']")
    public WebElementFacade noSearchCriteriaErrorMessage;

    @FindBy(xpath = "//div[@class='govuk-hint'][1]")
    public WebElementFacade numberOfSearchResults;

    @FindBy(xpath = "//label[text()='MPAM Case']")
    public WebElementFacade mpamCaseCheckbox;

    @FindBy(xpath = "//a[contains(text(), 'Escalate case')]")
    public WebElementFacade escalateCaseHypertext;

    // Simple methods

    public void waitForResultsPage() {
        waitForDECSPageWithTitle("Search Results");
    }

    public void waitForSearchCriteriaPage() {
        getButtonElementFromDisplayedText("Search").withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
    }

    public int getNumberOfSearchResults() {
        return Integer.parseInt(workstacks.totalNumberOfCases.getText().split("\\s+")[0]);
    }

    public boolean zeroSearchResultsReturned() {
        return getNumberOfSearchResults() == 0;
    }

    public void selectComplaintsStage2CaseRefOfEscalatedComplaintsCase(String stage1CaseRef) {
        WebElementFacade stage2CaseRef = findBy("//a[text()='" + stage1CaseRef + "']/parent::td/following-sibling::td/a");
        safeClickOn(stage2CaseRef);
    }

    public void selectCaseTypeSearchCheckboxForCaseType(CaseType caseType) {
        checkSpecificCheckbox(caseType.getCorrespondenceTypeLabel());
    }

    //Enter search criteria

    public void enterSearchCriteria(String criteria, String value) {
        String validatedHeaderText;
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                switch (value.toUpperCase()) {
                    case "MIN":
                    case "DTEN":
                    case "TRO":
                    case "MPAM":
                    case "MTS":
                    case "COMP":
                    case "COMP2":
                    case "BF":
                    case "BF2":
                    case "IEDET":
                    case "SMC":
                    case "POGR":
                    case "POGR2":
                    case "FOI":
                    case "TO":
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.valueOf(value));
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "MIN + TRO":
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.MIN);
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.TRO);
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "MIN + DTEN":
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.MIN);
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.DTEN);
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "TRO + DTEN":
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.TRO);
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.DTEN);
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "ALL DCU CASE TYPES":
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.MIN);
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.TRO);
                        selectCaseTypeSearchCheckboxForCaseType(CaseType.DTEN);
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "RANDOM":
                        List<WebElementFacade> listOfCaseTypeCheckboxes = findAll("//fieldset[@id='caseTypes']//input[not(@checked)"
                                + "]/following-sibling::label");
                        String caseTypeLabel = checkRandomCheckboxFromList(listOfCaseTypeCheckboxes);
                        CaseType randomCaseType = CaseType.fromLabel(caseTypeLabel);
                        setSessionVariable("searchCaseType").to(randomCaseType.toString());
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                break;
            case "CORRESPONDENT FULL NAME":
                validatedHeaderText = getValidFieldLabelCase("Correspondent full name");
                enterSpecificTextIntoTextFieldWithHeading(value, validatedHeaderText);
                setSessionVariable("searchCorrespondentFullName").to(value);
                break;
            case "CORRESPONDENT POSTCODE":
                validatedHeaderText = getValidFieldLabelCase("Correspondent Postcode");
                enterSpecificTextIntoTextFieldWithHeading(value, validatedHeaderText);
                setSessionVariable("searchCorrespondentPostcode").to(value);
                break;
            case "CORRESPONDENT EMAIL ADDRESS":
                validatedHeaderText = getValidFieldLabelCase("Correspondent Email Address");
                enterSpecificTextIntoTextFieldWithHeading(value, validatedHeaderText);
                setSessionVariable("searchCorrespondentEmailAddress").to(value);
                break;
            case "COMPLAINANT DATE OF BIRTH":
                enterDateIntoDateFieldsWithHeading(value, "Complainant date of birth");
                setSessionVariable("searchComplainantDateOfBirth").to(value);
                break;
            case "CASE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Case reference");
                break;
            case "RECEIVED ON OR AFTER DATE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or after");
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                enterDateIntoDateFieldsWithHeading(value, "Received on or before");
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                validatedHeaderText = getValidFieldLabelCase("Member of Parliament name");
                selectSpecificOptionFromTypeaheadWithHeading(value, validatedHeaderText);
                setSessionVariable("searchMemberOfParliamentName").to(value);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent (non-MP)");
                setSessionVariable("searchCorrespondentName").to(value);
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
                    checkSpecificCheckbox("Include Active Cases only");
                }
                setSessionVariable("searchActiveCases").to(value);
                break;
            case "HOME SECRETARY INTEREST":
                if (value.equalsIgnoreCase("YES")) {
                    checkSpecificCheckbox("Include Home Secretary Interest Cases only");
                }
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent reference number");
                setSessionVariable("searchCorrespondentReferenceNumber").to(value);
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "Complainant Home Office Reference");
                setSessionVariable("searchComplainantHomeOfficeReference").to(value);
                break;
            case "PSU REFERENCE":
                enterSpecificTextIntoTextFieldWithHeading(value, "PSU Reference");
                setSessionVariable("searchPSUReference").to(value);
                break;
            case "REFERENCE TYPE":
                selectSpecificOptionFromDropdownWithHeading(value, "Reference type");
                setSessionVariable("searchReferenceType").to(value);
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                selectSpecificOptionFromDropdownWithHeading(value, "Ministerial sign off team");
                setSessionVariable("searchMinisterialSignOffTeam").to(value);
                break;
            case "CORRESPONDENT FULL NAME (APPLICANT OR CONSTITUENT)":
                enterSpecificTextIntoTextFieldWithHeading(value, "Correspondent full name (applicant or constituent)");
                setSessionVariable("searchCorrespondentName").to(value);
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
        clickSearchButton();
    }

    public void searchBySubstringOfCaseReference() {
        String caseRef = getCurrentCaseReference();
        String currentMaxCaseNumber = caseRef.split("/")[1];
        int randomStringPosition = (int) (Math.random() * ((6 - 4) + 1)) + 4;
        String leadingCaseRefValues = currentMaxCaseNumber.substring(0, 3);
        String substring = currentMaxCaseNumber.substring(3, randomStringPosition);
        String randomCaseRefString = "/" + leadingCaseRefValues + substring;
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

    public void assertSearchResults(String criteria) {
        List<String> caseTypesSearchFieldIsUsedIn;
        String searchValue;
        Date searchDate = null;
        Date caseDate = null;
        List<WebElementFacade> caseTypesSearchFieldIsAvailableFor;
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int numberOfUnallocatedCases = findAll("//td[3][not(text()='" + getCurrentUser().getUsername() + "')]/ancestor::tbody/tr/td/a").size();
        int randomNumber = new Random().nextInt(numberOfUnallocatedCases) + 1;
        WebElementFacade unallocatedRandomResultHypertext =
                findBy("//td[3][not(text()='" + getCurrentUser().getUsername() + "')]/ancestor::tbody/tr[" + randomNumber + "]/td/a");
        String searchValidationCaseReference = unallocatedRandomResultHypertext.getText();
        System.out.println("Random case selected for search verification: " + searchValidationCaseReference);
        setSessionVariable("randomCaseRef").to(searchValidationCaseReference);
        waitForResultsPage();
        if (criteria.equalsIgnoreCase("CASE TYPE") || criteria.equalsIgnoreCase("CASE REFERENCE") || criteria.equalsIgnoreCase("ACTIVE CASES ONLY")) {
            switch (criteria.toUpperCase()) {
                case "CASE TYPE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM", "COMP", "IEDET", "SMC", "POGR", "BF", "FOI", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCaseType");
                    caseTypesSearchFieldIsAvailableFor = findAll("//a[contains(text(), '" + searchValue + "/')]");
                    assertThat(caseTypesSearchFieldIsAvailableFor.size() == numberOfCasesDisplayed, is(true));
                    break;
                case "CASE REFERENCE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM", "COMP", "IEDET", "SMC", "POGR", "BF", "FOI", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCaseReference");
                    assertThat(searchValidationCaseReference.equalsIgnoreCase(searchValue), is(true));
                    break;
                case "ACTIVE CASES ONLY":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM", "FOI", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    caseTypesSearchFieldIsAvailableFor = findAll("//td[2][not(text() = 'Closed')]");
                    if (sessionVariableCalled("searchActiveCases").toString().toUpperCase().equals("YES")) {
                        assertThat(!caseTypesSearchFieldIsAvailableFor.isEmpty(), is(true));
                    }
                    break;
            }
        } else {
            safeClickOn(unallocatedRandomResultHypertext);
            caseView.waitForCaseToLoad();
            switch (criteria.toUpperCase()) {
                case "CORRESPONDENT FULL NAME":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("COMP", "IEDET", "SMC", "POGR", "BF", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCorrespondentFullName");
                    if (!searchValidationCaseReference.contains("FOI")) {
                        peopleTab.selectPeopleTab();
                        peopleTab.assertCorrespondentIsAttachedToCase(searchValue);
                    } else {
                        summaryTab.selectSummaryTab();
                        summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                    }
                    break;
                case "CORRESPONDENT POSTCODE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "COMP", "IEDET", "SMC", "POGR", "BF", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCorrespondentPostcode");
                    if (!searchValidationCaseReference.contains("FOI")) {
                        peopleTab.selectPeopleTab();
                        peopleTab.assertValueIsPresentInPeopleTabForGivenHeader(searchValue, "Address");
                    } else {
                        summaryTab.selectSummaryTab();
                        summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                    }
                    break;
                case "CORRESPONDENT EMAIL ADDRESS":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "COMP", "IEDET", "SMC", "POGR", "BF", "FOI", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCorrespondentEmailAddress");
                    if (!searchValidationCaseReference.contains("FOI")) {
                        peopleTab.selectPeopleTab();
                        peopleTab.assertValueIsPresentInPeopleTabForGivenHeader(searchValue, "Email address");
                    } else {
                        summaryTab.selectSummaryTab();
                        summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                    }
                    break;
                case "COMPLAINANT DATE OF BIRTH":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("COMP", "IEDET", "SMC", "POGR", "BF", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchComplainantDateOfBirth");
                    caseView.expandAllCaseDetailsAccordionSections();
                    assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Date of Birth").get(0).equalsIgnoreCase(searchValue), is(true));
                    break;
                case "RECEIVED ON OR AFTER DATE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM", "FOI", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchReceivedOnOrAfterDate");
                    try {
                        searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(searchValue);
                        if (!searchValidationCaseReference.contains("TO/")) {
                            caseDate = new SimpleDateFormat("dd/MM/yyyy")
                                    .parse(summaryTab.getSummaryTabValueForGivenHeader("When was the correspondence "
                                            + "received?"));
                        } else {
                            caseDate = new SimpleDateFormat("dd/MM/yyyy")
                                    .parse(summaryTab.getSummaryTabValueForGivenHeader("Date Received"));
                        }
                    } catch (ParseException pE) {
                        System.out.println("Could not parse dates");
                    }
                    assert caseDate != null;
                    assertThat(caseDate.after(searchDate) || caseDate.equals(searchDate), is(true));
                    break;
                case "RECEIVED ON OR BEFORE DATE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM", "FOI", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchReceivedOnOrBeforeDate");
                    try {
                        searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(searchValue);
                        if (!searchValidationCaseReference.contains("TO/")) {
                            caseDate = new SimpleDateFormat("dd/MM/yyyy")
                                    .parse(summaryTab.getSummaryTabValueForGivenHeader("When was the correspondence "
                                            + "received?"));
                        } else {
                            caseDate = new SimpleDateFormat("dd/MM/yyyy")
                                    .parse(summaryTab.getSummaryTabValueForGivenHeader("Date Received"));
                        }
                    } catch (ParseException pE) {
                        System.out.println("Could not parse dates");
                    }
                    assert caseDate != null;
                    assertThat(caseDate.before(searchDate) || caseDate.equals(searchDate), is(true));
                    break;
                case "MEMBER OF PARLIAMENT NAME":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchMemberOfParliamentName");
                    peopleTab.selectPeopleTab();
                    peopleTab.assertMPCorrespondentIsAddedToTheCase(searchValue);
                    break;
                case "PUBLIC CORRESPONDENT NAME":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "MPAM", "FOI");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCorrespondentName");
                    if (!searchValidationCaseReference.contains("FOI")) {
                        peopleTab.selectPeopleTab();
                        peopleTab.assertCorrespondentIsAttachedToCase(searchValue);
                    } else {
                        summaryTab.selectSummaryTab();
                        summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                    }
                    break;
                case "TOPIC":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU", "FOI");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchTopic");
                    summaryTab.selectSummaryTab();
                    summaryTab.primaryTopic.shouldContainText(searchValue);
                    break;
                case "SIGN OFF TEAM":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    String signOffTeam;
                    searchValue = sessionVariableCalled("searchSignOffTeam");
                    summaryTab.selectSummaryTab();
                    if (summaryTab.overridePrivateOfficeTeam.isVisible()) {
                       signOffTeam = summaryTab.overridePrivateOfficeTeam.getText();
                    } else {
                       signOffTeam = summaryTab.privateOfficeTeam.getText();
                    }
                    assertThat(signOffTeam.equalsIgnoreCase(searchValue), is(true));
                    break;
                case "HOME SECRETARY INTEREST":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("DCU");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    summaryTab.selectSummaryTab();
                    assertThat(summaryTab.homeSecInterest.getText().equals("Yes"), is(true));
                    break;
                case "CORRESPONDENT REFERENCE NUMBER":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("MPAM", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCorrespondentReferenceNumber");
                    if (!searchValidationCaseReference.contains("FOI")) {
                        peopleTab.selectPeopleTab();
                        WebElementFacade correspondentReferenceNumber = findBy("//th[text()='Reference']/following-sibling::td");
                        assertThat(correspondentReferenceNumber.getText().equalsIgnoreCase(searchValue), is(true));
                    } else {
                        summaryTab.selectSummaryTab();
                        summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                    }
                    break;
                case "COMPLAINANT HOME OFFICE REFERENCE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("COMP", "IEDET", "SMC", "BF");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchComplainantHomeOfficeReference");
                    caseView.expandAllCaseDetailsAccordionSections();
                    assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Home Office Reference").get(0).equalsIgnoreCase(searchValue), is(true));
                    break;
                case "PSU REFERENCE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("SMC");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchPSUReference");
                    openOrCloseAccordionSection("Triage");
                    assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("PSU Reference").get(0).equalsIgnoreCase(searchValue), is(true));
                    break;
                case "REFERENCE TYPE":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("MPAM");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchReferenceType");
                    summaryTab.selectSummaryTab();
                    summaryTab.isMinisterialResponseRequired.shouldContainText(searchValue);
                    break;
                case "MINISTERIAL SIGN OFF TEAM":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("MPAM");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchMinisterialSignOffTeam");
                    accordionMPAM.openCreationAccordion();
                    assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Ministerial sign off team").get(0).equalsIgnoreCase(searchValue), is(true));
                    break;
                case "CAMPAIGN":
                    caseTypesSearchFieldIsUsedIn = Arrays.asList("MPAM", "TO");
                    setSessionVariable("randomCaseType").to(returnRandomStringFromList(caseTypesSearchFieldIsUsedIn));
                    searchValue = sessionVariableCalled("searchCampaign");
                    summaryTab.selectSummaryTab();
                    summaryTab.assertCampaignInSummaryTabIsCorrect(searchValue);
                    break;
                default:
                    pendingStep(criteria + " is not defined within " + getMethodName());
            }
        }
    }

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
}
