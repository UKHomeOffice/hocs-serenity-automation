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

    public boolean onLoginPage() { return isElementDisplayed(usernameField);}

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

    public void logInAsUser(User user) {
        enterLoginDetails(user);
        safeClickOn(continueButton);
    }

    public void navigateToPlatform(String platform) {
        switch (platform.toUpperCase()) {
            case "DECS":
                navigateToDECS();
                break;
            case "MANAGEMENT UI":
                navigateToDECSManagementUI();
                break;
            default:
                pendingStep(platform + " is not defined within " + getMethodName());
        }
    }

    public void navigateToDECS() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QAX'");
            baseUrl = Environment.QAX.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "LOCAL":
                    baseUrl = Environment.LOCAL.getEnvironmentURL() + Service.HOCS.getPort();
                    break;
                case "QA":
                    baseUrl = Environment.CS_QA.getEnvironmentURL();
                    break;
                case "DEV":
                    baseUrl = Environment.CS_DEV.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environment.CS_DEMO.getEnvironmentURL();
                    break;
                case "QAX":
                    baseUrl = Environment.QAX.getEnvironmentURL();
                    break;
                case "DELTA":
                    baseUrl = Environment.DELTA.getEnvironmentURL();
                    break;
                case "GAMMA":
                    baseUrl = Environment.GAMMA.getEnvironmentURL();
                    break;
                case "EPSILON":
                    baseUrl = Environment.EPSILON.getEnvironmentURL();
                    break;
                default:
                    pendingStep(env + " is not defined within " + getMethodName());
            }
        }
        getDriver().get(baseUrl);
    }

    public void navigateToDECSManagementUI() {
        String env = System.getProperty("environment");
        String baseUrl = "";
        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QAX'");
            baseUrl = Environment.QAX_MUI.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environment.CS_DEV_MUI.getEnvironmentURL();
                    break;
                case "QA":
                    baseUrl = Environment.CS_QA_MUI.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environment.CS_DEMO_MUI.getEnvironmentURL();
                    break;
                case "QAX":
                    baseUrl = Environment.QAX_MUI.getEnvironmentURL();
                    break;
                case "DELTA":
                    baseUrl = Environment.DELTA_MUI.getEnvironmentURL();
                    break;
                case "GAMMA":
                    baseUrl = Environment.GAMMA_MUI.getEnvironmentURL();
                    break;
                case "EPSILON":
                    baseUrl = Environment.EPSILON_MUI.getEnvironmentURL();
                    break;
                default:
                    pendingStep(env + " is not defined within " + getMethodName());
            }
        }
        getDriver().get(baseUrl);
    }

    public void navigateToWCS() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environment.CS_QA.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "LOCAL":
                    baseUrl = Environment.LOCAL.getEnvironmentURL() + Service.HOCS.getPort();
                    break;
                case "DEV":
                    baseUrl = Environment.WCS_DEV.getEnvironmentURL();
                    break;
                case "QA":
                    baseUrl = Environment.WCS_QA.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environment.WCS_DEMO.getEnvironmentURL();
                case "QAX":
                case "DELTA":
                case "GAMMA":
                case "EPSILON":
                    pendingStep("There is no WCS instance for environment " + env.toUpperCase());
                    break;
                default:
                    pendingStep(env + " is not defined within " + getMethodName());
            }
        }
        getDriver().get(baseUrl);
    }

    public void navigateToWCSManagementUI() {
        String env = System.getProperty("environment");
        String baseUrl = "";
        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environment.CS_QA_MUI.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environment.WCS_DEV_MUI.getEnvironmentURL();
                    break;
                case "QA":
                    baseUrl = Environment.WCS_QA_MUI.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environment.WCS_DEMO_MUI.getEnvironmentURL();
                    break;
                case "QAX":
                case "DELTA":
                case "GAMMA":
                case "EPSILON":
                    pendingStep("There is no WCS instance for environment " + env.toUpperCase());
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
