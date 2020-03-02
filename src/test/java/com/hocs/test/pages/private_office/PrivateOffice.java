package com.hocs.test.pages.private_office;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.base_page.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOffice extends Page {

    @FindBy(css = "label[for='PrivateOfficeDecision-ACCEPT']")
    public WebElementFacade privateOfficeAcceptRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-REJECT']")
    public WebElementFacade privateOfficeRejectRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-CHANGE']")
    public WebElementFacade privateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    public WebElementFacade privateOfficeRejectNoteField;

    @FindBy(id = "CaseNote_PrivateOfficeOverride")
    public WebElementFacade privateOfficeOverrideNoteField;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade doYouApproveTheResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Override Private Office Team is required']")
    public WebElementFacade overridePrivateOfficeTeamIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Why should this be approved by this team instead? is required']")
    public WebElementFacade whyShouldThisBeApprovedErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade whatIsYourFeedbackResponseErrorMessage;

    @FindBy(id = "PrivateOfficeOverridePOTeamUUID")
    public WebElementFacade privateOfficeTeamDropdown;

    public void enterPORejectNotes() {
        waitFor(privateOfficeRejectNoteField);
        String poRejectNote = "Rejection Reason: " + generateRandomString();
        privateOfficeRejectNoteField.clear();
        privateOfficeRejectNoteField.sendKeys(poRejectNote);
        setSessionVariable("PORejectNote").to(poRejectNote);
    }

    public void getToChangeMinisterScreenPrerequisites() {
        clickOn(privateOfficeChangeMinisterRadioButton);
        clickOn(continueButton);
    }

    public void getToPOFeedbackResponseScreenPrerequisites() {
        clickOn(privateOfficeRejectRadioButton);
        clickOn(continueButton);
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        doYouApproveTheResponseErrorMessage.shouldContainText("Do you approve the response? is required");
    }

    public void assertChangeMinisterErrorMessages() {
        overridePrivateOfficeTeamIsRequiredErrorMessage.shouldContainText("Override Private Office Team is required");
        whyShouldThisBeApprovedErrorMessage.shouldContainText("Why should this be approved by this team instead? is required");
    }

    public void assertWhatIsYourFeedbackResponse() {
        whatIsYourFeedbackResponseErrorMessage.shouldContainText("What is your feedback about the response? is required");
    }

    public void moveCaseFromPrivateOfficeToMinisterSignOff() {
        clickOn(privateOfficeAcceptRadioButton);
        clickOn(continueButton);
    }

    public void moveDTENCaseFromPrivateOfficeToDispatch() {
        clickOn(privateOfficeAcceptRadioButton);
        clickOn(continueButton);
    }

    public void completePrivateOfficeApprovalStageAndStoreEnteredInformation() {
        clickOn(privateOfficeAcceptRadioButton);
        String privateOfficeAcceptanceDecision = privateOfficeAcceptRadioButton.getAttribute("for").substring(22);
        setSessionVariable("privateOfficeAcceptanceDecision").to(privateOfficeAcceptanceDecision);
        clickOn(continueButton);
    }

    public void selectNewPrivateOfficeTeamFromDropdown(String newPOTeam) {
        privateOfficeTeamDropdown.selectByVisibleText(newPOTeam);
        setSessionVariable("chosenPOTeam").to(newPOTeam);
    }

    public void enterAReasonForChangingPOTeam(String reason) {
        privateOfficeOverrideNoteField.sendKeys(reason);
        setSessionVariable("reasonForOverridePOTeam").to(reason);
    }
}
