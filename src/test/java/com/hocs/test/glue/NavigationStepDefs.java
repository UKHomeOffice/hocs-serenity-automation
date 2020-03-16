package com.hocs.test.glue;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.give_me_a_case.Fetch;

import com.hocs.test.pages.search.Search;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

public class NavigationStepDefs extends Page {

    CreateCase createCase;

    DataInput dataInput;

    Homepage homepage;

    Fetch fetch;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Search search;

    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "HOME":
                clickOn(homepage.home);
                break;
            case "CREATE SINGLE CASE":
                clickOn(homepage.createSingleCase);
                break;
            case "CREATE BULK CASES":
                clickOn(homepage.createBulkCases);
                break;
            case "SEARCH":
                clickOn(homepage.searchPage);
                waitFor(search.searchTopicTextbox);
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                clickOn(homepage.animalsInScienceTeam);
                break;
            case "PERFORMANCE AND PROCESS TEAM":
                clickOn(homepage.performanceProcessTeam);
                break;
            case "TRANSFERS AND NO10 TEAM":
                clickOn(homepage.transferN10Team);
                break;
            case "CENTRAL DRAFTING TEAM":
                clickOn(homepage.centralDraftingTeam);
                break;
            case "MINISTER FOR LORDS":
                clickOn(homepage.ministerForLordsTeam);
                break;
            case "EXTREMISM ANALYSIS UNIT":
                clickOn(homepage.extremismAnalysisUnit);
                break;
            case "MINSTER OF STATE FOR POLICING AND FIRE SERVICE":
                clickOn(homepage.ministerOfStateForPolicingAndFireServiceTeam);
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                clickOn(homepage.policeWorkforceProfessionalismUnit);
                break;
            case "UNDER SECRETARY OF STATE FOR CRIME SAFEGUARDING AND VULNERABILITY":
                clickOn(homepage.underSecretaryCrimeSafeguardVulnerability);
                break;
            case "CRIMINAL AND FINANCIAL INVESTIGATIONS":
                clickOn(homepage.criminalAndFinacialInvestigations);
                break;
            case "CHEMICAL BIOLOGICAL RADIOLOGICAL NUCLEAR EXPLOSIVES":
                clickOn(homepage.chemBioRadioNuclearExplosives);
                break;
            case "PRESS OFFICE":
                clickOn(homepage.pressOffice);
                break;
            case "COUNTER EXTREMISM UNIT":
                clickOn(homepage.counterExtremismUnit);
                break;
            case "FINANCE":
                clickOn(homepage.financeTeam);
                break;
            case "COUNTERTERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                clickOn(homepage.counterTerrorismLegislationInvestigatoryPowersUnit);
                break;
            case "MY CASES":
                clickOn(homepage.myCases);
                break;
            case "ADD STANDARD LINE":
                clickOn(homepage.addStandardLine);
                break;
            default:
                pendingStep(hocsPage + " is not defined within " + getMethodName());
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

    @Then("I am returned to my home screen")
    public void returnedToHomeScreen() {
        homepage.assertElementIsDisplayed(homepage.createSingleCase);
    }

    @When("I get a {string} case at {string} stage")
    public void getMeACase(String caseType, String stage) {
        fetch.giveMeACase(caseType, stage);
        getCaseId();
    }

    @Then("I am taken to the {string} page")
    public void iAmTakenToThePage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "CREATE SINGLE CASE":
                createCase.assertPageTitle();
                break;
            case "HOME":
                homepage.assertHomePageTitle();
                break;
            case "RECORD CORRESPONDENT DETAILS":
                recordCorrespondentDetails.assertPageTitle();
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
