package com.hocs.test.pages.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Tier2Coordination extends BasePage {

    ClaimSchema claimSchema;

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='Tier2Coord-Revise-Accept']")
    public WebElementFacade revisedOfferAcceptedByTheClaimantRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='Tier2Coord-Revise-Reject']")
    public WebElementFacade revisedOfferRejectedByTheClaimantRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='Tier2Coord-Upheld-Accept']")
    public WebElementFacade upheldOfferIsAcceptedByTheClaimantRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='Tier2Coord-Upheld-Reject']")
    public WebElementFacade upheldOfferIsRejectedByTheClaimantRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[@href='#Tier2Coord-error']")
    public WebElementFacade tier2ReviewResultIsRequiredErrorMessage;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Upheld']")
    public WebElementFacade tier2AdjudicatorDecisionUpheldRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Revised']")
    public WebElementFacade tier2AdjudicatorDecisionRevisedRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "Tier2AdjudicatorsOfficeDecisionDate-day")
    public WebElementFacade tier2AdjudicatorDecisionDateDayTextField;

    @FindBy(timeoutInSeconds = "10",  id = "Tier2AdjudicatorsOfficeDecisionDate-month")
    public WebElementFacade tier2AdjudicatorDecisionDateMonthTextField;

    @FindBy(timeoutInSeconds = "10",  id = "Tier2AdjudicatorsOfficeDecisionDate-year")
    public WebElementFacade tier2AdjudicatorDecisionYearTextField;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='Tier2Withdrawn-NA']")
    public WebElementFacade reviewOfferNARadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='Tier2Withdrawn-Withdrawn']")
    public WebElementFacade reviewOfferWithdrawnRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='Tier2WithdrawnDate-day']")
    public WebElementFacade reviewOfferWithdrawnDateDayTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='Tier2WithdrawnDate-month']")
    public WebElementFacade reviewOfferWithdrawnDateMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='Tier2WithdrawnDate-year']")
    public WebElementFacade reviewOfferWithdrawnDateYearTextbox;

    public void selectAWithdrawDecision() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Review/Offer withdrawn at Tier 2");
    }

    public void enterOfferWithdrawnDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Review/Offer withdrawn at Tier 2 date");
    }

    public void selectAAdjudicatorsOfficeDecision() {
        String selectedDecision = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Tier 2 adjudicator's office decision");
        setSessionVariable("adjudicatorsOfficeDecision").to(selectedDecision);
    }

    public void enterTier2AdjudicatorOfficeDecisionDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "Tier 2 adjudicator's office decision date");
        setSessionVariable("adjudicatorsOfficeDecisionDate").to(date);
    }

    public void selectClaimantAcceptsRevisedOffer() {
        selectSpecificRadioButton("Revised offer accepted by the claimant, or offer withdrawn");
        clickConfirmButton();
    }

    public void selectClaimantRejectsRevisedOffer() {
        selectSpecificRadioButton("Revised offer rejected by the claimant");
        clickConfirmButton();
    }

    public void selectClaimantAcceptsUpheldOffer() {
        selectSpecificRadioButton("Upheld offer is accepted by the claimant");
        clickConfirmButton();
    }

    public void selectClaimantRejectsUpheldOffer() {
        selectSpecificRadioButton("Upheld offer is rejected by the claimant");
        clickConfirmButton();
    }

    public void assertTier2ReviewResultIsRequiredErrorMessage() {
        tier2ReviewResultIsRequiredErrorMessage.shouldContainText("Tier 2 review result is required");
    }

    public void assertTier2AdjudicatorsOfficeDecisionInformationIsCorrectInAccordion() {
        String decision = claimSchema.tier2AdjudicatorsOfficeDecision.getValue();
        String decisionDateDay = claimSchema.tier2AdjudicatorsOfficeDecisionDateDay.getValue();
        String decisionDateMonth = claimSchema.tier2AdjudicatorOfficeDecisionDateMonth.getValue();
        String decisionDateYear = claimSchema.tier2AdjudicatorOfficeDecisionDateYear.getValue();
        String decisionDate = decisionDateDay + "/" + decisionDateMonth + "/" + decisionDateYear;
        assertThat(decisionDate.equals(sessionVariableCalled("adjudicatorsOfficeDecisionDate")), is(true));
        assertThat(decision.equals(sessionVariableCalled("adjudicatorsOfficeDecision")), is(true));
    }
}
