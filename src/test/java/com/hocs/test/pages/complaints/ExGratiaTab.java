package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExGratiaTab extends BasePage {

    @FindBy(xpath = "//span[text()='Update Ex-Gratia details']")
    public WebElementFacade updateExGratiaDetailsHypertext;

    @FindBy(xpath = "//a[text()='Ex-Gratia']")
    public WebElementFacade exGratiaTab;

    public void selectExGratiaTab() {
        safeClickOn(exGratiaTab);
    }

    public void selectUpdateExGratiaDetailsHypertext() {
        safeClickOn(updateExGratiaDetailsHypertext);
    }

    public void selectConsolatoryPaymentTypeCheckbox() {
        checkSpecificCheckbox("Consolatory");
        setSessionVariable("consolatoryPaymentTypeInput").to("Yes");
    }

    public void selectExGratiaPaymentTypeCheckbox() {
        checkSpecificCheckbox("Ex-Gratia");
        setSessionVariable("exGratiaPaymentTypeInput").to("Yes");
    }

    public void enterAmountRequestedByComplainant(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Amount requested by complainant");
        setSessionVariable("amountRequestedByComplainantInput").to(amount);
    }

    public void enterAmountRequestedFromBusiness(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Amount requested from business/port");
        setSessionVariable("amountRequestedFromBusiness").to(amount);
    }

    public void enterConsolatoryPaymentOfferSentToComplainant(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Consolatory payment offer sent to the complainant");
        setSessionVariable("consolatoryPaymentOfferSentToComplainant").to(amount);
    }

    public void enterExGratiaPaymentOfferSentToComplainant(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Ex-Gratia payment offer sent to the complainant");
        setSessionVariable("exGratiaPaymentOfferSentToComplainant").to(amount);
    }

    public void enterTotalPaymentOfferSentToComplainant(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Total payment offer sent to the complainant");
        setSessionVariable("totalPaymentOfferSentToComplainant").to(amount);
    }

    public void selectComplaintIsAccepted() {
        checkSpecificCheckbox("Complaint has accepted");
        setSessionVariable("complainantHasAccepted").to("Yes");
    }

    public void assertExGratiaTabSummary() {
        int n = 0;
        String exGratiaSummaryInputValue = "";
        List<WebElementFacade> listOfSummaryHeaderElements = findAll("//caption[text()='Summary']/following-sibling::tbody//th");
        List<WebElementFacade> listOfSummaryValueElements = findAll("//caption[text()='Summary']/following-sibling::tbody//td");
        while (n < listOfSummaryHeaderElements.size()) {
            String exGratiaTabSummaryHeader = listOfSummaryHeaderElements.get(n).getText();
            String exGratiaTabSummaryValue = listOfSummaryValueElements.get(n).getText();
            switch (exGratiaTabSummaryHeader.toUpperCase()) {
                case "CONSOLATORY PAYMENT TYPE":
                    exGratiaSummaryInputValue = "consolatoryPaymentTypeInput";
                    if (sessionVariableCalled(exGratiaSummaryInputValue) == null) {
                        setSessionVariable(exGratiaSummaryInputValue).to("No");
                    }
                    break;
                case "EX-GRATIA PAYMENT TYPE":
                    exGratiaSummaryInputValue = "exGratiaPaymentTypeInput";
                    if (sessionVariableCalled(exGratiaSummaryInputValue) == null) {
                        setSessionVariable(exGratiaSummaryInputValue).to("No");
                    }
                    break;
                case "AMOUNT REQUESTED BY COMPLAINANT:":
                    exGratiaSummaryInputValue = "amountRequestedByComplainantInput";
                    break;
                case "AMOUNT REQUESTED FROM BUSINESS/PORT:":
                    exGratiaSummaryInputValue = "amountRequestedFromBusiness";
                    break;
                case "CONSOLATORY PAYMENT OFFER SENT TO THE COMPLAINANT:":
                    exGratiaSummaryInputValue = "consolatoryPaymentOfferSentToComplainant";
                    break;
                case "EX-GRATIA PAYMENT OFFER SENT TO THE COMPLAINANT:":
                    exGratiaSummaryInputValue = "exGratiaPaymentOfferSentToComplainant";
                    break;
                case "TOTAL PAYMENT OFFER SENT TO THE COMPLAINANT:":
                    exGratiaSummaryInputValue = "totalPaymentOfferSentToComplainant";
                    break;
                case "COMPLAINANT HAS ACCEPTED:":
                    exGratiaSummaryInputValue = "complainantHasAccepted";
                    if (sessionVariableCalled(exGratiaSummaryInputValue) == null) {
                        setSessionVariable(exGratiaSummaryInputValue).to("No");
                    }
                    break;
                default:
                    pendingStep(exGratiaTabSummaryHeader + " is not defined within " + getMethodName());
            }
            String expectedSummaryValue = sessionVariableCalled(exGratiaSummaryInputValue);
            assertThat(exGratiaTabSummaryValue.equalsIgnoreCase(expectedSummaryValue), is(true));
            n++;
        }
    }
}