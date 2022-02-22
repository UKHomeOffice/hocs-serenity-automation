package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.complaints.ExGratiaTab;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ExGratiaTabStepDefs extends BasePage {

    CaseView caseView;

    ExGratiaTab exGratiaTab;

    @And("I select the Ex-Gratia tab")
    public void iSelectTheExGratiaTab() {
        caseView.waitForCaseToLoad();
        exGratiaTab.selectExGratiaTab();
    }

    @And("I select the update Ex-Gratia details hypertext")
    public void iSelectTheUpdateExGratiaDetailsHypertext() {
        exGratiaTab.selectUpdateExGratiaDetailsHypertext();
    }

    @And("I select the {string} payment type checkbox")
    public void iSelectThePaymentTypeCheckbox(String paymentType) {
        switch (paymentType.toUpperCase()) {
            case "CONSOLATORY":
                exGratiaTab.selectConsolatoryPaymentTypeCheckbox();
                break;
            case "EX-GRATIA":
                exGratiaTab.selectExGratiaPaymentTypeCheckbox();
                break;
            default:
                pendingStep(paymentType + " is not defined within " + getMethodName());
        }
    }

    @And("I enter {string} into the amount requested by the complainant field")
    public void iEnterIntoTheAmountRequestedByTheComplainantField(String amount) {
        exGratiaTab.enterAmountRequestedByComplainant(amount);
    }

    @And("I enter {string} into the amount requested from business\\/port field")
    public void iEnterIntoTheAmountRequestedFromBusinessPortField(String amount) {
        exGratiaTab.enterAmountRequestedFromBusiness(amount);
    }

    @And("I enter {string} into the consolatory payment offer sent to the complainant field")
    public void iEnterIntoTheConsolatoryPaymentOfferSentToTheComplainantField(String amount) {
        exGratiaTab.enterConsolatoryPaymentOfferSentToComplainant(amount);
    }

    @And("I enter {string} into the Ex-Gratia payment offer sent to the complainant field")
    public void iEnterIntoTheExGratiaPaymentOfferSentToTheComplainantField(String amount) {
        exGratiaTab.enterExGratiaPaymentOfferSentToComplainant(amount);
    }

    @And("I enter {string} into the total payment offer sent to the complainant field")
    public void iEnterIntoTheTotalPaymentOfferSentToTheComplainantField(String amount) {
        exGratiaTab.enterTotalPaymentOfferSentToComplainant(amount);
    }

    @And("I select that the complainant has accepted the offer")
    public void iSelectThatTheComplainantHasAcceptedTheOffer() {
        exGratiaTab.selectComplainantHasAccepted();
    }

    @Then("the Ex-Gratia tab summary should contain the correct values")
    public void theExGratiaTabSummaryShouldContainTheCorrectValues() {
        exGratiaTab.assertExGratiaTabSummary();
    }
}