package com.hocs.test.pages.markup;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.base_page.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MarkUpDecision extends Page {

    @FindBy(css = "label[for='MarkupDecision-PR']")
    public WebElementFacade policyResponseRadioButton;

    @FindBy(css = "label[for='MarkupDecision-OGD']")
    public WebElementFacade referToOgdRadioButton;

    @FindBy(css = "label[for='MarkupDecision-FAQ']")
    public WebElementFacade faqRadioButton;

    @FindBy(css = "label[for='MarkupDecision-NRN']")
    public WebElementFacade noReplyNeededRadioButton;

    @FindBy(css = "label[for='MarkupDecision-REJ']")
    public WebElementFacade rejectToDataInputRadioButton;

    @FindBy(id = "OGDDept")
    public WebElementFacade OGDTitleTextBox;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addATopicButton;

    @FindBy(xpath = "//a[text()='What sort of response is required? is required']")
    public WebElementFacade whatSortOfResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary topic? is required']")
    public WebElementFacade whichIsThePrimaryTopicErrorMessage;

    @FindBy(xpath = "//a[text()='Topic is required']")
    public WebElementFacade topicIsRequiredErrorMessage;

    @FindBy(id = "CaseNote_NRN")
    public WebElementFacade noResponseNeededTextField;

    @FindBy(id = "CaseNote_REJ")
    public WebElementFacade rejectToDataInputTextField;

    //Basic Methods

    public void selectFAQRadioButton() {
        clickOn(faqRadioButton);
    }

    public void selectNoReplyNeededRadioButton() {
        clickOn(noReplyNeededRadioButton);
    }

    public void selectPolicyResponseRadioButton() {
        clickOn(policyResponseRadioButton);
    }

    public void selectReferToOGDRadioButton() {
        clickOn(referToOgdRadioButton);
    }

    public void selectRejectToDataInput() {
        clickOn(rejectToDataInputRadioButton);
    }

    public void clickPolicyResponseRadioButton() {
        policyResponseRadioButton.click();
    }

    //Multi Step Methods

    public void getToMarkupAddATopicScreenPrerequisites() {
        clickOn(policyResponseRadioButton);
        clickOn(continueButton);
        waitABit(500);
    }

    public void getToMarkupEnterANewTopicScreenPrerequisites() {
        clickOn(policyResponseRadioButton);
        clickOn(continueButton);
        clickOn(addATopicButton);
    }

    public void chooseResponseTypeAndRecordDecision() {
        clickOn(policyResponseRadioButton);
        String whatSortOfResponseRadioButton = policyResponseRadioButton.getAttribute("for").substring(15);
        setSessionVariable("selectedWhatSortOfResponseRadioButton").to(whatSortOfResponseRadioButton);
    }

    //Assertions

    public void assertSortOfResponseErrorMessage() {
        assertThat(whatSortOfResponseErrorMessage.getText(), is("What sort of response is required? is required"));
    }

    public void assertAddATopicErrorMessage() {
        assertThat(whichIsThePrimaryTopicErrorMessage.getText(), is("Which is the primary topic? is required"));
    }

    public void assertTopicIsRequiredErrorMessage() {
        assertThat(topicIsRequiredErrorMessage.getText(), is("Topic is required"));
    }

    public void assertNRNTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(noResponseNeededTextField), is(true));
    }

    public void assertRejectTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(rejectToDataInputTextField), is(true));
    }

    public void assertOGDTitleTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(OGDTitleTextBox), is(true));
    }

    public void enterRejectToDataInputReasonIntoTextBox() {
        waitFor(rejectToDataInputTextField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        typeInto(rejectToDataInputTextField, rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }
}
