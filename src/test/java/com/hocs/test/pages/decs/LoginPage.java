package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import config.Environment;
import config.Service;
import config.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("page:login.page")
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    public WebElementFacade usernameField;

    @FindBy(id = "password")
    public WebElementFacade passwordField;

    @FindBy(xpath = "//li[text()='Invalid username or password.']")
    private WebElementFacade invalidUsernameOrPasswordErrorMessage;

    //Basic methods

    public void navigateToCS() {
        open();
    }

    public boolean onLoginPage() {
        return isElementDisplayed(usernameField);
    }

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
        clickContinueButton();
    }

    public void assertLoginErrorMessage() {
        invalidUsernameOrPasswordErrorMessage.shouldContainText("Invalid username or password.");
    }
}
