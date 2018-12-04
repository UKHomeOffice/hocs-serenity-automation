package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import config.Environments;
import config.Services;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.TestCase;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    private CreateCase createCase;

    private DataInput dataInput;

    private Homepage homepage;

    Page page;

    private RecordCorrespondentDetails recordCorrespondentDetails;

    @Given("^I navigate to the \"([^\"]*)\" page$")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "TEST FORM":
                homepage.clickTestFormLink();
                break;
            case "CREATE SINGLE CASE":
                homepage.clickCreateSingleCase();
                break;
            case "TEAMQUEUE":
                homepage.clickTeamQueueLink();
            default:
                fail(hocsPage + " is not defined with NavigationStepDefs.iNavigateToThePage()");
        }
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void iAmOnThePage(String onHocsPage) {
        switch (onHocsPage.toUpperCase()) {
            case "HOME":
                navigateToHocs();
                break;
            case "TEAMQUEUES":
                navigateToTeamqueues();
                break;
            case "WORKSTACKS":
                navigateToWorkstacks();
                break;
            default:
                fail(onHocsPage + " is not defined within NavigationStepDefs.iAmOnThePage");
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
            case "TEAMQUEUES":
                teamqueues.assertPageTitle();
            case "RECORD CORRESPONDENT DETAILS":
                recordCorrespondentDetails.assertPageTitle();
                break;
            case "RECORD CORRESPONDENCE DETAILS":
                dataInput.assertPageTitle();
                break;
            default:
                fail(pageName + " is not defined with NavigationStepDefs.iAmTakenToThePage()");
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
