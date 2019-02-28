package com.hocs.test.pages.login;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends Page {

    @FindBy(id = "username")
    private WebElementFacade usernameField;

    @FindBy(id = "password")
    private WebElementFacade passwordField;

    @FindBy(xpath = "//div[@class='error-summary']//ul//li[text()='Invalid username or password.']")
    private WebElementFacade invalidUsernameOrPasswordErrorMessage;

    public void assertInvalidUsernamePassword() {
        assertThat(getErrorDetails(), is("Invalid username or password."));
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void assertLoginErrorMessage() {
        assertThat(invalidUsernameOrPasswordErrorMessage.getText(), is("Invalid username or password.") );
    }

}
