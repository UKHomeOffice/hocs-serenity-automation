package com.hocs.test.pages.login;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import config.Environments;
import config.Passwords;
import config.Services;
import config.Usernames;
import config.Users;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends Page {

    @FindBy(id = "username")
    public WebElementFacade usernameField;

    @FindBy(id = "password")
    public WebElementFacade passwordField;

    @FindBy(xpath = "//li[text()='Invalid username or password.']")
    private WebElementFacade invalidUsernameOrPasswordErrorMessage;

    //Basic methods

    public void enterUsername(String username) {
        typeInto(usernameField, username);
    }

    public void enterPassword(String password) {
        typeInto(passwordField, password);
    }

    public void enterHocsUsername(Usernames username) {
        enterUsername(username.getUsername());
    }

    public void enterHocsPassword(Passwords password) {
        enterPassword(password.getPassword());
    }

    //Multi Step Methods

    public void enterHocsLoginDetails(Users user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
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
                    pendingStep(env + " is not defined within " + getMethodName());
            }
        }
        getDriver().get(baseUrl);
    }

    public void navigateToManagementUI() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environments.MANAGEMENTUIQA.getEnvironmentURL();
        } else {
            baseUrl = Environments.MANAGEMENTUIDEV.getEnvironmentURL();
        }
        getDriver().get(baseUrl);
    }

    // Assertions

    public void assertLoginErrorMessage() {
        assertThat(invalidUsernameOrPasswordErrorMessage.getText(), is("Invalid username or password."));
    }
}
