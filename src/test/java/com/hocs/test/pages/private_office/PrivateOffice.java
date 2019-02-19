package com.hocs.test.pages.private_office;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;

public class PrivateOffice extends Page {

    @Managed
    WebDriver driver;

    @FindBy(css = "label[for='PrivateOfficeDecision-ACCEPT']")
    private WebElementFacade privateOfficeAcceptRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-REJECT']")
    private WebElementFacade privateOfficeRejectRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-CHANGE']")
    private WebElementFacade privateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    private WebElementFacade privateOfficeRejectNoteField;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    private WebElementFacade doYouApproveTheResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Override Private Office Team is required']")
    private WebElementFacade overridePrivateOfficeTeamIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Why should this be approved by this team instead? is required']")
    private WebElementFacade whyShouldThisBeApprovedErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    private WebElementFacade whatIsYourFeedbackResponseErrorMessage;

    public void clickPrivateOfficeAcceptRadioButton(){
        privateOfficeAcceptRadioButton.click();
    }

    public void clickPrivateOfficeRejectRadioButton(){
        privateOfficeRejectRadioButton.click();
    }

    public void clickPrivateOfficeChangeMinisterRadioButton(){
        privateOfficeChangeMinisterRadioButton.click();
    }

    public void enterPORejectNotes() {
        waitFor(privateOfficeRejectNoteField);

        String poRejectNote = "Rejection Reason: " + generateRandomString();
        privateOfficeRejectNoteField.clear();
        privateOfficeRejectNoteField.sendKeys(poRejectNote);
        setSessionVariable("PORejectNote").to(poRejectNote);
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        assertThat(doYouApproveTheResponseErrorMessage.getText(), is("Do you approve the response? is required"));
    }

    public void assertChangeMinisterErrorMessages() {
        assertThat(overridePrivateOfficeTeamIsRequiredErrorMessage.getText(), is ("Override Private Office Team is required"));
        assertThat(whyShouldThisBeApprovedErrorMessage.getText(), is("Why should this be approved by this team instead? is required"));
    }

    public void assertWhatIsYourFeedbackResponse() {
        assertThat(whatIsYourFeedbackResponseErrorMessage.getText(), is("What is your feedback about the response? is required"));
    }
}
