package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class WithdrawACase extends BasePage {

    @FindBy(xpath = "//input[@id='caseReference']")
    public WebElementFacade caseReferenceTextField;

    @FindBy(xpath = "//input[@id='withdrawalDate-day']")
    public WebElementFacade withdrawalDateDayTextbox;

    @FindBy(xpath = "//input[@id='withdrawalDate-month']")
    public WebElementFacade withdrawalDateMonthTextbox;

    @FindBy(xpath = "//input[@id='withdrawalDate-year']")
    public WebElementFacade withdrawalDateYearTextbox;

    @FindBy(xpath = "//textarea[@id='notes']")
    public WebElementFacade notesTextArea;

    public void enterCaseReference(String caseReference) {
        typeInto(caseReferenceTextField, caseReference);
    }

    public void enterWithdrawalDate(String date) {
        enterDateIntoDateFieldsWithHeading(date, "Withdrawal Date");
    }

    public void enterWithdrawalNotes(String notes) {
        typeInto(notesTextArea, notes);
        setSessionVariable("withdrawalNotes").to(notes);
    }

    public void withdrawACase(String caseReference, String date, String withdrawalNotes) {
        enterCaseReference(caseReference);
        enterWithdrawalDate(date);
        enterWithdrawalNotes(withdrawalNotes);
        clickTheButton("Withdraw");
        waitABit(500);
    }
}
