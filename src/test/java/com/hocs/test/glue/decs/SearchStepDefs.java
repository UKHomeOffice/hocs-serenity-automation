package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.complaints.BFProgressCase;
import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.complaints.IEDETProgressCase;
import com.hocs.test.pages.complaints.POGRProgressCase;
import com.hocs.test.pages.complaints.SMCProgressCase;
import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Search;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.to.TOProgressCase;
import config.CaseType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;


public class SearchStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Search search;

    CreateCase createCase;

    CaseView caseView;

    DCUProgressCase dcuProgressCase;

    MPAMProgressCase mpamProgressCase;

    COMPProgressCase compProgressCase;

    BFProgressCase bfProgressCase;

    FOIProgressCase foiProgressCase;

    TOProgressCase toProgressCase;

    IEDETProgressCase iedetProgressCase;

    SMCProgressCase smcProgressCase;

    POGRProgressCase pogrProgressCase;

    @And("I enter {string} into the {string} search field")
    public void iEnterIntoTheSearchField(String value, String criteria) {
        setSessionVariable("searchValue").to(value);
        setSessionVariable("searchCriteria").to(criteria);
        search.enterSearchCriteria(criteria, value);
    }

    @And("I select {string} from the {string} dropdown menu")
    public void iSelectTheDropdownSearchField(String value, String criteria) {
        setSessionVariable("searchValue").to(value);
        setSessionVariable("searchCriteria").to(criteria);
        search.enterSearchCriteria(criteria, value);
    }

    @And("I check that the search results have the correct {string}")
    public void iCheckThatTheSearchResultsHaveTheCorrect(String criteria) throws ParseException {
        String infoValue = sessionVariableCalled("searchValue");
        if (search.zeroSearchResultsReturned()) {
            CaseType caseTypeToGenerate = search.getRandomCaseTypeCriteriaCompatibleWith(criteria);
            switch (caseTypeToGenerate) {
                case MIN:
                case DTEN:
                case TRO:
                    dcuProgressCase.generateDCUSearchCaseData(infoValue, criteria);
                    break;
                case MPAM:
                    mpamProgressCase.generateMPAMSearchCaseData(infoValue, criteria);
                    break;
                case COMP:
                    compProgressCase.generateCOMPSearchCaseData(infoValue, criteria);
                    break;
                case IEDET:
                    iedetProgressCase.generateIEDETSearchCaseData(infoValue, criteria);
                    break;
                case SMC:
                    smcProgressCase.generateSMCSearchCaseData(infoValue, criteria);
                    break;
                case POGR:
                    pogrProgressCase.generatePOGRSearchCaseData(infoValue, criteria);
                    break;
                case FOI:
                    foiProgressCase.generateFOISearchCaseData(infoValue, criteria);
                    break;
                case BF:
                    bfProgressCase.generateBFSearchCaseData(infoValue, criteria);
                    break;
                case TO:
                    toProgressCase.generateTOSearchCaseData(infoValue, criteria);
                    break;
                default:
                    pendingStep(caseTypeToGenerate + " is not defined within " + getMethodName());
            }
            dashboard.goToDashboard();
            waitABit(1000);
            dashboard.selectSearchLinkFromMenuBar();
            search.waitForSearchCriteriaPage();
            iEnterIntoTheSearchField(infoValue, criteria);
            clickSearchButton();
            search.waitForResultsPage();
        }
        search.assertSearchResults(criteria);
    }

    @When("I click the search button on the search page")
    public void clickSearchButtonOnSearchPageWithNoCriteria() {
        clickSearchButton();
        search.waitForResultsPage();
    }

    @When("I enter a non-hocs format case reference")
    public void iEnterANonHocsFormatCaseReference() {
        dashboard.enterCaseReferenceIntoSearchBar("MIN0000000019");
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that the case reference entered is an invalid format")
    public void anErrorMessageShouldBeDisplayedStatingThatTheCaseReferenceEnteredIsAnInvalidFormat() {
        assertExpectedErrorMessageIsDisplayed("Case reference is invalid format");
    }

    @Then("an error message should be displayed as I have not entered any search criteria")
    public void assertThatNoSearchCriteriaErrorMessageIsShown() {
        search.assertNoSearchCriteriaErrorMessage();
    }

    @When("I enter a valid case reference into the load case search bar")
    public void enterValidCaseReferenceForSearch() {
        createCase.createCSCaseOfTypeWithDocument(createCase.getRandomCSCaseType());
        dashboard.goToDashboard();
        dashboard.enterCaseReferenceIntoSearchBar(getCurrentCaseReference());
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("I should be taken directly to the case")
    public void assertThatCaseReferenceSearchTakesUserToCase() {
        waitABit(500);
        if (!caseView.currentCaseIsLoaded()) {
            Assert.fail("Expected case " + getCurrentCaseReference() + " to be loaded, but it was not");
        }
    }

    @When("I enter a non-existent (case )reference")
    public void enterInvalidCaseReferenceForSearch() {
        dashboard.enterCaseReferenceIntoSearchBar("MIN/0000000/19");
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that there are no active workflows for the case")
    public void assertThatNoActiveWorkflowsErrorMessageIsShown() {
        assertExpectedErrorMessageIsDisplayed("No active workflows for case");
    }

    @When("I press enter in the Load Case search bar")
    public void PressEnterInTheLoadCaseBar() {
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that a case reference is required")
    public void assertThatCaseReferenceIsRequiredMessageIsShown() {
        assertExpectedErrorMessageIsDisplayed("Case reference is required");
    }

    @Then("the created DCU case should be visible in the search results")
    public void theCreatedDCUCaseShouldBeVisibleInTheSearchResults() {
        int retest = 0;
        while (retest < 5) {
            try {
                search.assertCurrentCaseIsDisplayed();
                break;
            } catch (AssertionError a) {
                retest ++;
                dashboard.selectSearchLinkFromMenuBar();
                search.enterSearchCriteria(sessionVariableCalled("searchCriteria"), sessionVariableCalled("searchValue"));
                clickSearchButton();
            }
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("the created case should be the only case visible in the search results")
    public void createdCaseShouldBeVisibleInTheSearchResults(){
        waitABit(1000);
        int numberOfResults = search.getNumberOfSearchResults();
        int retest = 0;
        while (retest < 5) {
             numberOfResults = search.getNumberOfSearchResults();
            if (numberOfResults < 1) {
                retest ++;
                dashboard.selectSearchLinkFromMenuBar();
                iEnterIntoTheSearchField(getCurrentCaseReference(), "Case Reference");
                clickSearchButton();
                waitABit(1000);
            } else if (numberOfResults > 1) {
                Assert.fail("More than one case has matching case reference");
            } else {
                break;
            }
        }
        if (numberOfResults < 1){
            Assert.fail("No cases found ");
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("I click the (case )reference of the case/claim in search results")
    public void iClickTheReferenceOfARandomSearchResult() {
        WebElementFacade caseReference = findBy("//a[text()='" + getCurrentCaseReference() + "']");
        safeClickOn(caseReference);
    }

    @And("I search for a case using a random substring of a case reference")
    public void searchForCaseBySubstringOfCaseReference() {
        search.searchBySubstringOfCaseReference();
    }

    @Then("the displayed cases all contain the input substring case reference")
    public void displayedCasesAllContainSubstringCaseRef() {
        search.assertAllDisplayedCaseRefsContainSubstring();
    }

    @Then("{int} cases should be displayed")
    public void numberOfCasesShouldBeDisplayed(int number) {
        int numberOfCasesDisplayed = search.getNumberOfSearchResults();
        assertThat(number == numberOfCasesDisplayed, is(true));
    }

    @And("I enter the current case reference into the case reference search field")
    public void iEnterTheCurrentCaseReferenceIntoTheCaseReferenceSearchField() {
        search.enterSearchCriteria("Case Reference", getCurrentCaseReference());
    }

    @And("I enter the current case reference into the Case Reference field on the search screen")
    public void iEnterTheCurrentCaseReferenceIntoTheCaseReferenceFieldOnTheSearchScreen() {
        search.enterSearchCriteria("Case Reference", getCurrentCaseReference());
    }

    @And("I search for the case by its case reference")
    public void iSearchForTheCaseByItsCaseReference() {
        int i = 0;
        while (i < 6) {
            dashboard.selectSearchLinkFromMenuBar();
            iEnterTheCurrentCaseReferenceIntoTheCaseReferenceFieldOnTheSearchScreen();
            clickSearchButton();
            search.waitForResultsPage();
            if(!search.zeroSearchResultsReturned()) {
                break;
            }
            waitABit(10000);
            i++;
        }
    }

    @And("I search for the complaints case escalated to stage 2 by it's case reference")
    public void iSearchForTheEscalatedComplaintsCaseByCaseReference() {
        String complaintCaseRef = sessionVariableCalled("stage1CaseReference");
        search.enterSearchCriteria("Case Reference", complaintCaseRef);
        clickSearchButton();
        search.waitForResultsPage();
    }

    @And("I load the stage 2 complaints case by selecting its case reference from the Escalate Case column")
    public void iLoadTheStage2CaseBySelectingTheCaseReferenceInTheEscalateCaseColumn() {
        search.selectComplaintsStage2CaseRefOfEscalatedComplaintsCase(sessionVariableCalled("stage1CaseReference"));
    }

    @And("I search for the case by the newly updated primary correspondent")
    public void iSearchForTheCaseByTheNewlyUpdatedPrimaryCorrespondent() {
        String correspondent = sessionVariableCalled("correspondentFullName");
        search.enterSearchCriteria("Member of Parliament Name", correspondent);
        clickSearchButton();
    }

    @And("I search for the case by the Application Reference")
    public void iSearchForTheCaseByTheApplicationReference() {
        String applicationReference = sessionVariableCalled("searchApplicationReference");
        search.enterSearchCriteria("Application Reference", applicationReference);
        clickSearchButton();
    }

    @Then("The Pogr case should be visible in the search results")
    public void thePogrCaseShouldBeVisibleInTheSearchResults() {
        int retest = 0;
        while (retest < 5) {
            try {
                search.assertCurrentCaseIsDisplayed();
                break;
            } catch (AssertionError a) {
                retest ++;
                dashboard.selectSearchLinkFromMenuBar();
                String applicationReference = sessionVariableCalled("searchApplicationReference");
                search.enterSearchCriteria("Application reference", applicationReference);
                clickSearchButton();
            }
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("I select the {string} case type checkbox")
    public void iSelectTheCaseTypeCheckbox(String CaseType) {

        switch (CaseType.toUpperCase()) {
            //Incomplete list
            case "MPAM CASE":
            case "COMPLAINT CASE":
            case "COMPLAINT CASE - STAGE 2":
            case "MTS CASE":
            case "TREAT OFFICIAL":
            case "BORDER FORCE CASE":
            case "BORDER FORCE (STAGE 2)":
            case "IE DETENTION CASE":
                createCase.checkSpecificCheckbox(CaseType);
                break;

            default:
                pendingStep(CaseType + " is not defined within " + getMethodName());
        }
    }
}