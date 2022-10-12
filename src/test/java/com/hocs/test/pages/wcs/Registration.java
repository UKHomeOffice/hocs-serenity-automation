package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Registration extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//h1[text()='Registration Windrush Scheme check']")
    public WebElementFacade registrationSchemeCheckTitle;

    @FindBy(xpath = "//a[@href='#IdentityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantStatusErrorMessage;

    @FindBy(xpath = "//a[@href='#FormSubmitted-error']")
    public WebElementFacade howWasTheFormSubmittedIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#IsAssistAdviser-error']")
    public WebElementFacade formSentThroughClaimantAdviserIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ClaimedFrom-error']")
    public WebElementFacade whereIsTheClaimFromIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ClaimType-error']")
    public WebElementFacade typeOfClaimIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#RegistrationNextTeamOutput-error']")
    public WebElementFacade whereShouldTheCaseProgressToIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ReceivedDate-error']")
    public WebElementFacade dateClaimReceivedIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#IsTaskForce-error']")
    public WebElementFacade claimGoneThroughTaskForceIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#IdentityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantIdentityErrorMessage;

    @FindBy(xpath = "//a[@href='#IdentityFailConfirm-error']")
    public WebElementFacade cannotIdentifyCloseClaimErrorMessage;

    // Registration Windrush Scheme check page

    public void selectIfClaimHasGoneThroughTaskForce(String yesOrNo){
        selectSpecificRadioButton(yesOrNo);
        recordCaseData.addHeadingAndValueRecord("Has the claimant had their status confirmed by the Windrush Scheme (Task Force)?", yesOrNo);
        clickConfirmButton();
    }

    public void assertClaimGoneThroughTaskForceIsRequiredErrorMessage() {
        assertThat(claimGoneThroughTaskForceIsRequiredErrorMessage.getText(), is("Has the claimant gone through the "
                + "Windrush Scheme? is required"));
    }

    // Registration Identity Confirmation page

    public void confirmClaimantsIdentity(){
        selectSpecificRadioButton("Yes, send to next team");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's identity", "Yes");
        clickConfirmButton();
    }

    public void assertCanYouConfirmClaimantIdentityErrorMessage() {
        assertThat(canYouConfirmClaimantIdentityErrorMessage.isVisible(), is(true));
    }

    public void assertCannotIdentifyCloseClaimErrorMessage() {
        assertThat(cannotIdentifyCloseClaimErrorMessage.isVisible(), is(true));
    }

    public void putTheClaimOnHold(){
        selectSpecificRadioButton("Put on hold");
        clickConfirmButton();
    }

    public void passTheClaimToStage1IdentityCheck() {
        selectSpecificRadioButton("No, send to stage 1 ID checks");
        clickConfirmButton();
        waitABit(1000);
    }

    // Registration Identity Stage 1 page

    public void failIdentityCheck() {
        selectSpecificRadioButton("No, cannot confirm identity");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's identity", "No");
        clickConfirmButton();
    }

    public void passClaimToEligibilityTeam() {
        waitABit(500);
        selectSpecificRadioButton("Yes, send to next team");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's identity", "Yes");
        clickConfirmButton();
    }

    // Registration page

    public void selectConfirmTheClaimantsIDRadioButton() {
        selectSpecificRadioButton("ID confirmed, progress case");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's identity", "Yes");
    }

    public void confirmClaimantsID() {
        selectConfirmTheClaimantsIDRadioButton();
        clickConfirmButton();
    }

    // Registration - Choose next team page

    public void selectIDConfirmedEligibilityNotConfirmed() {
        selectSpecificRadioButton("ID confirmed, eligibility not confirmed - send to eligibility");
        clickConfirmButton();
    }

    public void selectIDAndEligibilityConfirmed() {
        selectSpecificRadioButton("ID and eligibility confirmed - send to Triage");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's eligibility", "Yes");
        clickConfirmButton();
    }

    public void assertCanYouConfirmClaimantStatusErrorMessage() {
        assertThat(canYouConfirmClaimantStatusErrorMessage.isVisible(), is(true));
    }

    public void assertTypeOfClaimErrorMessage() {
        assertThat(typeOfClaimIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertHowWasFormSubmittedErrorMessage() {
        assertThat(howWasTheFormSubmittedIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertFormSentThroughClaimantAssistanceErrorMessage() {
        assertThat(formSentThroughClaimantAdviserIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertWhereIsTheClaimFromErrorMessage() {
        assertThat(whereIsTheClaimFromIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertDateClaimReceivedErrorMessage() {
        assertThat(dateClaimReceivedIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertWhereShouldTheCaseProgressToErrorMessage() {
        assertThat(whereShouldTheCaseProgressToIsRequiredErrorMessage.isVisible(), is(true));
    }
}
