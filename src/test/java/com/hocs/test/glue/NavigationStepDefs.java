package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.teamqueue.Teamqueue;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.give_me_a_case.fetch;
import config.Environments;
import config.Services;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import net.thucydides.core.annotations.Managed;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import org.openqa.selenium.WebDriver;

public class NavigationStepDefs extends Page {

    @Managed
    WebDriver driver;

    private CreateCase createCase;

    private DataInput dataInput;

    private Homepage homepage;

    private Teamqueue teamqueue;

    private fetch fetch;

    Workstacks workstacks;

    private RecordCorrespondentDetails recordCorrespondentDetails;

    @When("^I navigate to the \"([^\"]*)\" page$")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "HOME":
                clickOn(homepage.home);
                break;
            case "TEST FORM":
                clickOn(homepage.testFormLink);
                break;
            case "CREATE SINGLE CASE":
                clickOn(homepage.createSingleCase);
                break;
            case "CREATE BULK CASES":
                clickOn(homepage.createBulkCases);
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                clickOn(homepage.animalsInScienceTeam);
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

    @When("^I navigate to the \"([^\"]*)\" team page$")
    public void navigateToTeamPage(String teamPage) {
        switch (teamPage.toUpperCase()) {
            case "PERFORMANCE AND PROCESS TEAM":
                clickOn(homepage.performanceProcessTeam);
                break;
            case "TRANSFERS AND NO10 TEAM":
                clickOn(homepage.transferN10Team);
                break;
            default:
                pendingStep(teamPage + " is not defined within " + getMethodName());
        }
    }

    @When("^I get a \"([^\"]*)\" case at \"([^\"]*)\" stage$")
    public void getMeACase(String caseType, String stage) {
        fetch.giveMeACase(caseType, stage);
    }

    @When("^I click the back to dashboard button$")
    public void clickBackToDashboardButton() {
        clickOn(workstacks.backToDashboardButton);
    }

    @When("^I click the cancel button$")
    public void clickCancelButtonOnAllocateCasePage() {
        clickOn(workstacks.allocateScreenCancelButton);
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void navigateToPage(String onHocsPage) {
        switch (onHocsPage.toUpperCase()) {
            case "HOME":
                navigateToHocs();
                break;
            case "TEST FORM":
                clickOn(homepage.testFormLink);
                break;
            case "CREATE SINGLE CASE":
                clickOn(homepage.createSingleCase);
                break;
            case "CREATE BULK CASES":
                clickOn(homepage.createBulkCases);
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                clickOn(homepage.animalsInScienceTeam);
            case "PERFORMANCE AND PROCESS TEAM":
                clickOn(homepage.performanceProcessTeam);
                break;
            case "CENTRAL DRAFTING TEAM":
                clickOn(homepage.centralDraftingTeam);
                break;
            case "MY CASES":
                clickOn(homepage.myCases);
                break;
            case "ADD STANDARD LINE":
                clickOn(homepage.addStandardLine);
            case "SEARCH":
                clickOn(homepage.searchPage);
                break;
            default:
                pendingStep(onHocsPage + " is not defined within " + getMethodName());
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" page$")
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
            case "RECORD CORRESPONDENCE DETAILS":
                dataInput.assertPageTitle();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
        System.out.println("I have been taken to " + pageName);
    }

    public void navigateToHocs() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environments.QA.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environments.DEV.getEnvironmentURL();
                    break;
                case "LOCAL":
                    baseUrl = Environments.LOCAL.getEnvironmentURL() + Services.HOCS.getPort();
                    break;
                case "QA":
                    baseUrl = Environments.QA.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environments.DEMO.getEnvironmentURL();
                default:
                    TestCase.fail("Environment must be set to LOCAL, DEV or QA");
            }
        }
        driver.get(baseUrl);
    }
}
