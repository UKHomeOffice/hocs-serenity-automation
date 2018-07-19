package com.hocs.test.glue;

import static config.Environments.DEV;
import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import config.Users;
import cucumber.api.java.en.Given;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs {

    @Managed
    WebDriver driver;

    @Steps(shared = true)
    NavigationStepDefs navigationStepDefs;

    LoginPage loginPage;

    Page page;

    @Given("^I login as \"([^\"]*)\" on \"([^\"]*)\"$")
    public void iLoginas(String user, String environment) {
        switch (environment.toUpperCase()) {
            case "HOCS DEV":
                driver.get(DEV.getEnvironmentURL());
                Serenity.setSessionVariable("homePage").to(DEV.getEnvironmentURL());
                break;
            default:
                fail(environment + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
        Serenity.setSessionVariable("user").to(user);
        switch (user.toUpperCase()) {
            case "DOM":
                enterHocsLoginDetails(Users.DOM);
                break;
            default:
                fail(user + " is not defined with LoginStepDefs.iLoginAs()");
        }

        page.clickSubmitButton();
    }

    private void enterHocsLoginDetails(Users user) {
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
    }

}
