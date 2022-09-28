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

    Dashboard dashboard;

    @FindBy(xpath = "//label[@for='caseStatus_active']")
    public WebElementFacade caseStatusActiveCheckbox;

    @FindBy(xpath = "//a[text()='No search criteria specified']")
    public WebElementFacade noSearchCriteriaErrorMessage;

    @FindBy(css = "div span[class='govuk-hint']")
    public WebElementFacade numberOfSearchResults;

    @FindBy(xpath = "//label[text()='MPAM Case']")
    public WebElementFacade mpamCaseCheckbox;

    @FindBy(xpath = "//label[text()='MTS Case']")
    public WebElementFacade mtsCaseCheckbox;

    @FindBy(id = "ComplainantHORef")
    public WebElementFacade complainantHomeOfficeReferenceTextField;

    @FindBy(xpath = "//a[contains(text(), 'Escalate case')]")
    public WebElementFacade escalateCaseHypertext;

    //Enter search criteria

    public void enterSearchCriteria(String criteria, String value) {
        String validatedHeaderText;
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                List<WebElementFacade> listOfCaseTypeCheckboxes = findAll("//fieldset[@id='caseTypes']//input[not(@checked)"
                        + "]/following-sibling::label");
                switch (value.toUpperCase()) {
                    case "MIN":
                        checkSpecificCheckbox("DCU Ministerial");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "DTEN":
                        checkSpecificCheckbox("DCU Number 10");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "TRO":
                        checkSpecificCheckbox("DCU Treat Official");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "MIN + TRO":
                        checkSpecificCheckbox("DCU Ministerial");
                        checkSpecificCheckbox("DCU Treat Official");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "MIN + DTEN":
                        checkSpecificCheckbox("DCU Ministerial");
                        checkSpecificCheckbox("DCU Number 10");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "TRO + DTEN":
                        checkSpecificCheckbox("DCU Treat Official");
                        checkSpecificCheckbox("DCU Number 10");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "ALL DCU CASE TYPES":
                        checkSpecificCheckbox("DCU Ministerial");
                        checkSpecificCheckbox("DCU Treat Official");
                        checkSpecificCheckbox("DCU Number 10");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "MPAM":
                        checkSpecificCheckbox("MPAM Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "MTS":
                        checkSpecificCheckbox("MTS Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "COMP":
                        checkSpecificCheckbox("Complaint Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "COMP2":
                        checkSpecificCheckbox("Complaint Case - Stage 2");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "BF":
                        checkSpecificCheckbox("Border Force Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "BF2":
                        checkSpecificCheckbox("Border Force (Stage 2)");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "IEDET":
                        checkSpecificCheckbox("IE Detention Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "SMC":
                        checkSpecificCheckbox("Serious Misconduct Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "POGR":
                        checkSpecificCheckbox("HMPO/GRO Complaint Case");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "POGR2":
                        checkSpecificCheckbox("HMPO/GRO Complaint Case - Stage 2");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "FOI":
                        checkSpecificCheckbox("FOI Request");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "TO":
                        checkSpecificCheckbox("Treat Official");
                        setSessionVariable("searchCaseType").to(value);
                        break;
                    case "RANDOM":
                        String randomCaseTypeFullText = checkRandomCheckboxFromList(listOfCaseTypeCheckboxes);
                        String randomCaseType = null;
                        switch (randomCaseTypeFullText.toUpperCase()) {
                            case "DCU MINISTERIAL":
                                randomCaseType = "MIN";
                                break;
                            case "DCU TREAT OFFICIAL":
                                randomCaseType = "TRO";
                                break;
                            case "DCU NUMBER 10":
                                randomCaseType = "DTEN";
                                break;
                            case "MPAM CASE":
                                randomCaseType = "MPAM";
                                break;
                            case "MTS CASE":
                                randomCaseType = "MTS";
                                break;
                            case "COMPLAINT CASE":
                                randomCaseType = "COMP";
                                break;
                            case "COMPLAINT CASE - STAGE 2":
                                randomCaseType = "COMP2";
                                break;
                            case "BORDER FORCE CASE":
                                randomCaseType = "BF";
                                break;
                            case "BORDER FORCE (STAGE 2)":
                                randomCaseType = "BF2";
                                break;
                            case "IE DETENTION CASE":
                                randomCaseType = "IEDET";
                                break;
                            case "SERIOUS MISCONDUCT CASE":
                                randomCaseType = "SMC";
                                break;
                            case "HMPO/GRO COMPLAINT CASE":
                                randomCaseType = "POGR";
                                break;
                            case "HMPO/GRO COMPLAINT CASE - STAGE 2":
                                randomCaseType = "POGR2";
                                break;
                            case "FOI REQUEST":
                                randomCaseType = "FOI";
                                break;
                            case "TREAT OFFICIAL":
                                randomCaseType = "TO";
                                break;
                        }
                        setSessionVariable("searchCaseType").to(randomCaseType);
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
        safeClickOn(searchButton);
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

    public void assertSearchResults(String criteria, String config) throws ParseException {
        List<String> searchFieldUsedInCaseTypes;
        String searchValue;
        Date searchDate = null;
        Date caseDate = null;
        List<WebElementFacade> listOfCasesWithValue;
        int numberOfCasesDisplayed = getNumberOfSearchResults();
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed) + 1;
        WebElementFacade unallocatedRandomResultHypertext = findBy("//tr[" + randomNumber + "]/td[not(text()='" + getCurrentUser().getUsername() +
                "')]/preceding-sibling::td/a");
        String randomSearchResult = unallocatedRandomResultHypertext.getText();
        System.out.println("Random case selected for search verification: " + randomSearchResult);
        setSessionVariable("randomCaseRef").to(randomSearchResult);
        waitForResultsPage();
        if (config == null) {
            if (criteria.equalsIgnoreCase("CASE TYPE")) {
                searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM", "COMP", "IEDET", "SMC", "POGR", "BF", "FOI", "TO");
                setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                searchValue = sessionVariableCalled("searchCaseType");
                listOfCasesWithValue = findAll("//a[contains(text(), '" + searchValue + "/')]");
                assertThat(listOfCasesWithValue.size() == numberOfCasesDisplayed, is(true));
            } else if (criteria.equalsIgnoreCase("CASE REFERENCE")) {
                searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM", "COMP", "IEDET", "SMC", "POGR", "BF", "FOI", "TO");
                setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                searchValue = sessionVariableCalled("searchCaseReference");
                assertThat(randomSearchResult.equalsIgnoreCase(searchValue), is(true));
            } else if (criteria.equalsIgnoreCase("ACTIVE CASES ONLY")) {
                searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM", "FOI", "TO");
                setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                listOfCasesWithValue = findAll("//td[2][not(text() = 'Closed')]");
                if (sessionVariableCalled("searchActiveCases").toString().toUpperCase().equals("YES")) {
                    assertThat(!listOfCasesWithValue.isEmpty(), is(true));
                }
            } else {
                safeClickOn(unallocatedRandomResultHypertext);
                caseView.waitForCaseToLoad();
                switch (criteria.toUpperCase()) {
                    case "CORRESPONDENT FULL NAME":
                        searchFieldUsedInCaseTypes = Arrays.asList("COMP", "IEDET", "SMC", "POGR", "BF", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchCorrespondentFullName");
                        if (!randomSearchResult.contains("FOI")) {
                            peopleTab.selectPeopleTab();
                            peopleTab.assertCorrespondentIsAttachedToCase(searchValue);
                        } else {
                            summaryTab.selectSummaryTab();
                            summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                        }
                        break;
                    case "CORRESPONDENT POSTCODE":
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "COMP", "IEDET", "SMC", "POGR", "BF", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchCorrespondentPostcode");
                        if (!randomSearchResult.contains("FOI")) {
                            peopleTab.selectPeopleTab();
                            peopleTab.assertCorrespondentPostcode(searchValue);
                        } else {
                            summaryTab.selectSummaryTab();
                            summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                        }
                        break;
                    case "CORRESPONDENT EMAIL ADDRESS":
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "COMP", "IEDET", "SMC", "POGR", "BF", "FOI", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchCorrespondentEmailAddress");
                        if (!randomSearchResult.contains("FOI")) {
                            peopleTab.selectPeopleTab();
                            peopleTab.assertCorrespondentEmailAddress(searchValue);
                        } else {
                            summaryTab.selectSummaryTab();
                            summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                        }
                        break;
                    case "COMPLAINANT DATE OF BIRTH":
                        searchFieldUsedInCaseTypes = Arrays.asList("COMP", "IEDET", "SMC", "POGR", "BF", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchComplainantDateOfBirth");
                        if (accordionSectionIsVisible("Registration")) {
                            openOrCloseAccordionSection("Registration");
                        } else if (accordionSectionIsVisible("IEDET Registration")) {
                            openOrCloseAccordionSection("IEDET Registration");
                        } else if (accordionSectionIsVisible("Data Input")) {
                            openOrCloseAccordionSection("Data Input");
                        } else if (accordionSectionIsVisible("Case Registration")) {
                            openOrCloseAccordionSection("Case Registration");
                        } else if (accordionSectionIsVisible("Case Registration (Stage 2)")) {
                            openOrCloseAccordionSection("Case Registration (Stage 2)");
                        } else {
                            openOrCloseAccordionSection("Stage 2 Registration");
                        }
                        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Date of Birth").get(0).equalsIgnoreCase(searchValue), is(true));
                        break;
                    case "RECEIVED ON OR AFTER DATE":
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM", "FOI", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchReceivedOnOrAfterDate");
                        try {
                            searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(searchValue);
                            if (!randomSearchResult.contains("TO/")) {
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
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM", "FOI", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchReceivedOnOrBeforeDate");
                        try {
                            searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(searchValue);
                            if (!randomSearchResult.contains("TO/")) {
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
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchMemberOfParliamentName");
                        peopleTab.selectPeopleTab();
                        peopleTab.assertMPCorrespondentIsAddedToTheCase(searchValue);
                        break;
                    case "PUBLIC CORRESPONDENT NAME":
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "MPAM", "FOI");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchCorrespondentName");
                        if (!randomSearchResult.contains("FOI")) {
                            peopleTab.selectPeopleTab();
                            peopleTab.assertCorrespondentIsAttachedToCase(searchValue);
                        } else {
                            summaryTab.selectSummaryTab();
                            summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                        }
                        break;
                    case "TOPIC":
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU", "FOI");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchTopic");
                        summaryTab.selectSummaryTab();
                        summaryTab.primaryTopic.shouldContainText(searchValue);
                        break;
                    case "SIGN OFF TEAM":
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
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
                        searchFieldUsedInCaseTypes = Arrays.asList("DCU");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        summaryTab.selectSummaryTab();
                        assertThat(summaryTab.homeSecInterest.getText().equals("Yes"), is(true));
                        break;
                    case "CORRESPONDENT REFERENCE NUMBER":
                        searchFieldUsedInCaseTypes = Arrays.asList("MPAM", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchCorrespondentReferenceNumber");
                        if (!randomSearchResult.contains("FOI")) {
                            peopleTab.selectPeopleTab();
                            WebElementFacade correspondentReferenceNumber = findBy("//th[text()='Reference']/following-sibling::td");
                            assertThat(correspondentReferenceNumber.getText().equalsIgnoreCase(searchValue), is(true));
                        } else {
                            summaryTab.selectSummaryTab();
                            summaryTab.assertPrimaryCorrespondentDetailMatchValue(searchValue);
                        }
                        break;
                    case "COMPLAINANT HOME OFFICE REFERENCE":
                        searchFieldUsedInCaseTypes = Arrays.asList("COMP", "IEDET", "SMC", "BF");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchComplainantHomeOfficeReference");
                        if (accordionSectionIsVisible("Registration")) {
                            openOrCloseAccordionSection("Registration");
                        } else if (accordionSectionIsVisible("IEDET Registration")) {
                            openOrCloseAccordionSection("IEDET Registration");
                        } else if (accordionSectionIsVisible("Data Input")) {
                            openOrCloseAccordionSection("Data Input");
                        } else if (accordionSectionIsVisible("Case Registration")) {
                            openOrCloseAccordionSection("Case Registration");
                        } else if (accordionSectionIsVisible("Case Registration (Stage 2)")) {
                            openOrCloseAccordionSection("Case Registration (Stage 2)");
                        } else {
                            openOrCloseAccordionSection("Stage 2 Registration");
                        }
                        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Home Office Reference").get(0).equalsIgnoreCase(searchValue), is(true));
                        break;
                    case "PSU REFERENCE":
                        searchFieldUsedInCaseTypes = Arrays.asList("SMC");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchPSUReference");
                        openOrCloseAccordionSection("Triage");
                        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("PSU Reference").get(0).equalsIgnoreCase(searchValue), is(true));
                        break;
                    case "REFERENCE TYPE":
                        searchFieldUsedInCaseTypes = Arrays.asList("MPAM");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchReferenceType");
                        summaryTab.selectSummaryTab();
                        summaryTab.isMinisterialResponseRequired.shouldContainText(searchValue);
                        break;
                    case "MINISTERIAL SIGN OFF TEAM":
                        searchFieldUsedInCaseTypes = Arrays.asList("MPAM");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchMinisterialSignOffTeam");
                        accordionMPAM.openCreationAccordion();
                        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Ministerial sign off team").get(0).equalsIgnoreCase(searchValue), is(true));
                        break;
                    case "CAMPAIGN":
                        searchFieldUsedInCaseTypes = Arrays.asList("MPAM", "TO");
                        setSessionVariable("randomCaseType").to(returnRandomStringFromList(searchFieldUsedInCaseTypes));
                        searchValue = sessionVariableCalled("searchCampaign");
                        summaryTab.selectSummaryTab();
                        summaryTab.assertCampaignInSummaryTabIsCorrect(searchValue);
                        break;
                    default:
                        pendingStep(criteria + " is not defined within " + getMethodName());
                }
            }
        } else {
            switch (config.toUpperCase()) {
                case "DCU":
                    assertDCUInformationRandomSearchResult(criteria);
                    break;
                case "MPAM":
                    assertMPAMInformationRandomSearchResult(criteria);
                    break;
                case "COMP":
                case "IEDET":
                case "SMC":
                case "POGR":
                    assertComplaintsInformationRandomSearchResult(criteria);
                    break;
                case "BF":
                    assertBFInformationRandomSearchResult(criteria);
                    break;
                case "FOI":
                    assertFOIInformationRandomSearchResult(criteria);
                    break;
                case "TO":
                    assertTOInformationRandomSearchResult(criteria);
                    break;
                default:
                    pendingStep(config + " is not defined within " + getMethodName());
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

    private void assertDCUInformationRandomSearchResult(String criteria) {
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

    private void assertMPAMInformationRandomSearchResult(String criteria) {
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
            case "CORRESPONDENT FULL NAME (APPLICANT OR CONSTITUENT)":
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

    private void assertComplaintsInformationRandomSearchResult(String criteria) {
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
                if (dateFieldsWithHeadingAreCurrentlyVisible("Date of Birth")) {
                    displayedValue = getDisplayedDateInDateFieldsWithHeading("Date of Birth");
                } else {
                    if (!accordionSectionIsVisible("Registration") && !accordionSectionIsVisible("Stage 2 Registration") && !accordionSectionIsVisible(
                            "Data Input")) {
                        summaryTab.selectSummaryTab();
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
                }
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

    private void assertBFInformationRandomSearchResult(String criteria) {
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

    private void assertFOIInformationRandomSearchResult(String criteria) {
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
            case "RECEIVED ON OR AFTER DATE":
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
            case "RECEIVED ON OR BEFORE DATE":
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
            case "PUBLIC CORRESPONDENT NAME":
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

    private void assertTOInformationRandomSearchResult(String criteria) throws ParseException {
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
            case "RECEIVED ON OR AFTER DATE":
                safeClickOn(randomSearchResultHypertext);
                summaryTab.selectSummaryTab();
                displayedReceivedDate = randomResultReceivedDateCell.getText();
                searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrAfterDate"));
                caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(displayedReceivedDate);
                assertThat(!caseDate.before(searchDate), is(true));
                break;
            case "RECEIVED ON OR BEFORE DATE":
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
