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

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    private CreateCase createCase;

    private DataInput dataInput;

    private Homepage homepage;

    private Teamqueue teamqueue;

    private fetch fetch;

    Page page;

    Workstacks workstacks;

    private RecordCorrespondentDetails recordCorrespondentDetails;

    @When("^I navigate to the \"([^\"]*)\" page$")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "HOME":
                homepage.goHome();
                break;
            case "TEST FORM":
                homepage.clickTestFormLink();
                break;
            case "CREATE SINGLE CASE":
                homepage.clickCreateSingleCase();
                break;
            case "CREATE BULK CASES":
                homepage.clickCreateBulkCases();
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                homepage.selectAnimalsInScienceTeam();
            case "PERFORMANCE AND PROCESS TEAM":
                homepage.selectPerformanceProcessTeam();
                break;
            case "TRANSFERS AND NO10 TEAM":
                homepage.selectTransfersN10Team();
                break;
            case "CENTRAL DRAFTING TEAM":
                homepage.selectCentralDraftingTeam();
                break;
            case "MINISTER FOR LORDS":
                homepage.selectMinisterForLordsTeam();
                break;
            case "EXTREMISM ANALYSIS UNIT":
                homepage.selectExtremismAnalysisUnit();
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                homepage.selectPoliceWorkforceProfessionalismTeam();
                break;
            case "UNDER SECRETARY OF STATE FOR CRIME SAFEGUARDING AND VULNERABILITY":
                homepage.selectUnderSecretaryCrimeSafeguardingVulerabilityTeam();
                break;
            case "CRIMINAL AND FINANCIAL INVESTIGATIONS":
                homepage.selectCriminalAndFinacialInvestigationsTeam();
                break;
            case "CHEMICAL BIOLOGICAL RADIOLOGICAL NUCLEAR EXPLOSIVES":
                homepage.selectChemBioRadioNuclearExplosivesTeam();
                break;
            case "PRESS OFFICE":
                homepage.selectPressOffice();
                break;
            case "COUNTER EXTREMISM UNIT":
                homepage.selectCounterExtremismUnit();
                break;
            case "FINANCE":
                homepage.selectFinanceTeam();
                break;
            case "COUNTERTERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                homepage.selectCounterTerrorismLegislationInvestigatoryPowersUnit();
                break;
            case "MY CASES":
                homepage.selectMyCases();
                break;
            case "ADD STANDARD LINE":
                homepage.selectAddStandardLine();
                break;
            default:
                pendingStep(hocsPage + " is not defined within " + getMethodName());
        }
    }

    @When("^I navigate to the \"([^\"]*)\" team page$")
    public void navigateToTeamPage(String teamPage) {
        switch (teamPage.toUpperCase()) {
            case "PERFORMANCE AND PROCESS TEAM":
                homepage.selectPerformanceProcessTeam();
                break;
            case "TRANSFERS AND NO10 TEAM":
                homepage.selectTransfersN10Team();
                break;
            default:
                pendingStep(teamPage + " is not defined within " + getMethodName());
        }

    }

    @When("^I get a \"([^\"]*)\" case at \"([^\"]*)\" stage$")
    public void reallyGiveMeACase(String caseType, String stage) {
        fetch.giveMeACase(caseType, stage);
    }

    @When("^I click the back to dashboard button$")
    public void clickBackToDashboardButtonOnAllocateCasePage() {
        workstacks.clickBackToDashboardButton();
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void navigateToPage(String onHocsPage) {
        switch (onHocsPage.toUpperCase()) {
            case "HOME":
                navigateToHocs();
                break;
            case "TEST FORM":
                homepage.clickTestFormLink();
                break;
            case "CREATE SINGLE CASE":
                homepage.clickCreateSingleCase();
                break;
            case "CREATE BULK CASES":
                homepage.clickCreateBulkCases();
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                homepage.selectAnimalsInScienceTeam();
            case "PERFORMANCE AND PROCESS TEAM":
                homepage.selectPerformanceProcessTeam();
                break;
            case "CENTRAL DRAFTING TEAM":
                homepage.selectCentralDraftingTeam();
                break;
            case "MY CASES":
                homepage.selectMyCases();
                break;
            case "ADD STANDARD LINE":
                homepage.selectAddStandardLine();
            case "SEARCH":
                homepage.selectSearchPage();
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
           /* case "TEAMQUEUES":
                teamqueue.assertPageTitle(); */
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

    private void navigateToHocs() {
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
