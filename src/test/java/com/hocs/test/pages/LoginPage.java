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
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    //Multi Step Methods

    public void enterLoginDetails(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
    }

    public void navigateToPlatform(String platform) {
        switch (platform.toUpperCase()) {
            case "DECS":
                navigateToDECS();
                break;
            case "MANAGEMENT UI":
                navigateToManagementUI();
                break;
            default:
                pendingStep(platform + " is not defined within " + getMethodName());
        }
    }

    public void navigateToDECS() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environment.QA.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "LOCAL":
                    baseUrl = Environment.LOCAL.getEnvironmentURL() + Service.HOCS.getPort();
                    break;
                case "QA":
                    baseUrl = Environment.QA.getEnvironmentURL();
                    break;
                case "QAX":
                    baseUrl = Environment.QAX.getEnvironmentURL();
                    break;
                case "DEV":
                    baseUrl = Environment.DEV.getEnvironmentURL();
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
            switch (env.toUpperCase()) {
                case "QA":
                    baseUrl = Environment.MANAGEMENTUIQA.getEnvironmentURL();
                    break;
                case "QAX":
                    baseUrl = Environment.MANAGEMENTUIQAX.getEnvironmentURL();
                    break;
                case "DEV":
                    baseUrl = Environment.MANAGEMENTUIDEV.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environment.MANAGEMENTUIDEMO.getEnvironmentURL();
                    break;
                default:
                    pendingStep(env + " is not defined within " + getMethodName());
            }
        }
        getDriver().get(baseUrl);
    }

    // Assertions

    public void assertLoginErrorMessage() {
        invalidUsernameOrPasswordErrorMessage.shouldContainText("Invalid username or password.");
    }
}
