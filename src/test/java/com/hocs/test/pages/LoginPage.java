package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import config.Environment;
import config.Service;
import config.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends BasePage {

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

    public void enterHocsUsername(String username) {
        enterUsername(username);
    }

    public void enterHocsPassword(String password) {
        enterPassword(password);
    }

    //Multi Step Methods

    public void enterHocsLoginDetails(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
    }

    public void navigateToHocs() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environment.QA.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environment.DEV.getEnvironmentURL();
                    break;
                case "LOCAL":
                    baseUrl = Environment.LOCAL.getEnvironmentURL() + Service.HOCS.getPort();
                    break;
                case "QA":
                    baseUrl = Environment.QA.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environment.DEMO.getEnvironmentURL();
                    break;
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
            baseUrl = Environment.MANAGEMENTUIQA.getEnvironmentURL();
        } else {
            baseUrl = Environment.MANAGEMENTUIDEV.getEnvironmentURL();
        }
        getDriver().get(baseUrl);
    }

    // Assertions

    public void assertLoginErrorMessage() {
        invalidUsernameOrPasswordErrorMessage.shouldContainText("Invalid username or password.");
    }
}
