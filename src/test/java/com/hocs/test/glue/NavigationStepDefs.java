package com.hocs.test.glue;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.dcu.fetchExistingDCUCases;

import com.hocs.test.pages.Search;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

public class NavigationStepDefs extends BasePage {

    CreateCase createCase;

    DataInput dataInput;

    Homepage homepage;

    fetchExistingDCUCases fetchExistingDCUCases;

    AddCorrespondent initialDraftRecordCorrespondentDetails;

    Search search;

    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "HOME":
                safeClickOn(homepage.home);
                break;
            case "CREATE SINGLE CASE":
                safeClickOn(homepage.createSingleCase);
                break;
            case "CREATE BULK CASES":
                safeClickOn(homepage.createBulkCases);
                break;
            case "SEARCH":
                safeClickOn(homepage.searchPage);
                search.waitUntilSearchPageLoaded();
                break;
            default:
                pendingStep(hocsPage + " is not defined within " + getMethodName());
        }
    }

    @And ("I click to view the {string} workstack")
    public void iClickToViewTheWorkstack(String team) {
        if (!homepage.myCases.isVisible()) {
            homepage.goHome();
        }
        switch (team.toUpperCase()) {
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                safeClickOn(homepage.animalsInScienceTeam);
                break;
            case "PERFORMANCE AND PROCESS TEAM":
                safeClickOn(homepage.performanceProcessTeam);
                break;
            case "TRANSFERS AND NO10 TEAM":
                safeClickOn(homepage.transferN10Team);
                break;
            case "CENTRAL DRAFTING TEAM":
                safeClickOn(homepage.centralDraftingTeam);
                break;
            case "MINISTER FOR LORDS":
                safeClickOn(homepage.ministerForLordsTeam);
                break;
            case "EXTREMISM ANALYSIS UNIT":
                safeClickOn(homepage.extremismAnalysisUnit);
                break;
            case "MINSTER OF STATE FOR POLICING AND FIRE SERVICE":
                safeClickOn(homepage.ministerOfStateForPolicingAndFireServiceTeam);
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                safeClickOn(homepage.policeWorkforceProfessionalismUnit);
                break;
            case "UNDER SECRETARY OF STATE FOR CRIME SAFEGUARDING AND VULNERABILITY":
                safeClickOn(homepage.underSecretaryCrimeSafeguardVulnerability);
                break;
            case "CRIMINAL AND FINANCIAL INVESTIGATIONS":
                safeClickOn(homepage.criminalAndFinacialInvestigations);
                break;
            case "CHEMICAL BIOLOGICAL RADIOLOGICAL NUCLEAR EXPLOSIVES":
                safeClickOn(homepage.chemBioRadioNuclearExplosives);
                break;
            case "PRESS OFFICE":
                safeClickOn(homepage.pressOffice);
                break;
            case "COUNTER EXTREMISM UNIT":
                safeClickOn(homepage.counterExtremismUnit);
                break;
            case "FINANCE":
                safeClickOn(homepage.financeTeam);
                break;
            case "COUNTERTERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                safeClickOn(homepage.counterTerrorismLegislationInvestigatoryPowersUnit);
                break;
            case "MY CASES":
                safeClickOn(homepage.myCases);
                break;
            case "MPAM CREATION":
                safeClickOn((homepage.MPAMCreationTeam));
                break;
            default:
                pendingStep(team + " is not defined within " + getMethodName());
        }
    }

    @Given("I load the current case")
    public void loadCase() {
        homepage.getCurrentCase();
    }

    @And("I load and claim the current case")
    public void loadAndClaimCase() {
        homepage.getAndClaimCurrentCase();
    }

    @And("I claim the current case")
    public void claimCase() {
        homepage.claimCurrentCase();
    }

    @Then("I am returned to my home screen")
    public void returnedToHomeScreen() {
        homepage.assertElementIsDisplayed(homepage.createSingleCase);
    }

    @When("I get a {string} case at {string} stage")
    public void getMeACase(String caseType, String stage) {
        fetchExistingDCUCases.giveMeACase(caseType, stage);
        setCaseReferenceFromAssignedCase();
    }

    @Then("I am taken to the {string} page")
    public void iAmTakenToThePage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "CREATE SINGLE CASE":
                createCase.assertPageTitle();
                break;
            case "HOME":
                homepage.assertOnHomePage();
                break;
            case "RECORD CORRESPONDENT DETAILS":
                initialDraftRecordCorrespondentDetails.assertPageTitle();
                break;
            case "DATA INPUT":
                dataInput.assertPageTitle();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
        System.out.println("I have been taken to " + pageName);
    }
}
