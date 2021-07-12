package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class RegistrationTaskForceCheck extends BasePage {

    @FindBy(css = "label[for='IsTaskForce-Yes']")
    public WebElementFacade claimGoneThroughTaskForceYesRadioButton;

    @FindBy(css = "label[for='IsTaskForce-No']")
    public WebElementFacade claimGoneThroughTaskForceNoRadioButton;

    @FindBy(xpath = "//input[@id='TaskForceRef']")
    public WebElementFacade taskForceReferenceTextbox;

    @FindBy(xpath = "//a[@href='#IsTaskForce-error']")
    public WebElementFacade claimGoneThroughTaskForceIsRequiredErrorMessage;

    public void assertClaimGoneThroughTaskForceIsRequiredErrorMessage() {
        assertThat(claimGoneThroughTaskForceIsRequiredErrorMessage.getText(), is("Has the claimant gone through the "
                + "Windrush Scheme? is required"));
    }

    public void selectClaimHasGoneThroughTaskForce(){
        clickOn(claimGoneThroughTaskForceYesRadioButton);
        clickOn(confirmButton);
    }

    public void selectClaimHasNotGoneTroughTaskForce(){
        clickOn(claimGoneThroughTaskForceNoRadioButton);
        clickOn(confirmButton);
    }

    public void enterTaskForceReference(String reference){
        typeInto(taskForceReferenceTextbox, reference);
    }
}
