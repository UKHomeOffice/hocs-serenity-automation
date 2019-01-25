package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.google.common.annotations.GwtIncompatible;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.teamqueue.Teamqueue;
import config.Environments;
import config.Services;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import net.thucydides.core.annotations.Managed;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assume.assumeNotNull;

import org.openqa.selenium.WebDriver;

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    private CreateCase createCase;

    private DataInput dataInput;

    private Homepage homepage;

    private Teamqueue teamqueue;

    Page page;

    private RecordCorrespondentDetails recordCorrespondentDetails;

    @Given("^I navigate to the \"([^\"]*)\" page$")
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
            case "TEAMQUEUE":
                homepage.clickTeamQueueLink();
            case "TEAM 1":
                homepage.selectTeam1();
                break;
            case "MY CASES":
                homepage.selectMyCases();
                break;
            default:
                System.out.println(hocsPage
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                hocsPage = null;
                assumeNotNull(hocsPage);
        }
    }

    @When("^The current user navigates to the \"([^\"]*)\" team page$")
    public void navigateToTeamPage(String teamPage) {
        switch (teamPage.toUpperCase()) {
            case "PERFORMANCE AND PROCESS TEAM":
                homepage.performanceAndProcessTeam.click();
                break;
            case "TRANSFERS AND NO10 TEAM":
                homepage.transfersAndNo10Team.click();
                break;
            default:
                System.out.println(teamPage
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                teamPage = null;
                assumeNotNull(teamPage);
        }

    }


    @Given("^I am on the \"([^\"]*)\" page$")
    public void navigateToPage(String onHocsPage) {
        switch (onHocsPage.toUpperCase()) {
            case "HOME":
                navigateToHocs();
                break;
    /*        case "TEAMQUEUES":
                navigateToTeamqueues();
                break;
            case "WORKSTACKS":
                navigateToWorkstacks();
                break;*/
            default:
                System.out.println(onHocsPage
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method.");
                onHocsPage = null;
                assumeNotNull(onHocsPage);
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" page$")
    public void iAmTakenToThePage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "CREATE SINGLE CASE":
                createCase.assertPageTitle();
                break;
            case "HOME":
                homepage.assertPageTitle();
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
                System.out.println(pageName
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                pageName = null;
                assumeNotNull(pageName);
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
                default:
                    TestCase.fail("Environment must be set to LOCAL, DEV or QA");
            }
        }
        driver.get(baseUrl);
    }

}
