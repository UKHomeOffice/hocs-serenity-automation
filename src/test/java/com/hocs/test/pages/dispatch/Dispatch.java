package com.hocs.test.pages.dispatch;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class Dispatch extends Page {

    @FindBy(css = "label[for='DispatchDecision-ACCEPT']")
    private WebElementFacade dispatchAcceptRadioButton;

    @FindBy(css = "label[for='DispatchDecision-REJECT']")
    private WebElementFacade dispatchRejectRadioButton;

    @FindBy(xpath = "//a[text()='Are you able to dispatch this? is required']")
    private WebElementFacade areYouAbleToDispatchErrorMessage;

    @FindBy(xpath = "//a[text()='Why are you unable to dispatch this? is required']")
    private WebElementFacade whyAreYouUnableToDispatchErrorMessage;

    public void clickDispatchAcceptRadioButton() {
        dispatchAcceptRadioButton.click();
    }

    public void clickDispatchRejectRadioButton() { dispatchRejectRadioButton.click();}

    public void assertAreYouAbleToDispatchErrorMessageIsShown() {
        assertThat(areYouAbleToDispatchErrorMessage.getText(), is("Are you able to dispatch this? is required"));
    }

    public void assertWhyAreYouUnableToDispatchErrorMessageIsShown() {
        assertThat(whyAreYouUnableToDispatchErrorMessage.getText(), is("Why are you unable to dispatch this? is required"));
    }

}
