package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;

public class ContributionRequests extends BasePage {

    @FindBy(xpath = "//a[text()='Add a Contribution']")
    public WebElementFacade addAContributionHypertext;

    @FindBy(xpath = "//a[text()='Add complainant contribution']")
    public WebElementFacade addComplainantContributionHypertext;

    @FindBy(xpath = "//a[text()='Add business contribution']")
    public WebElementFacade addBusinessContributionHypertext;

    @FindBy(id = "contributionBusinessArea")
    public WebElementFacade contributionRequestBusinessArea;

    @FindBy(id = "contributionBusinessUnit")
    public WebElementFacade contributionRequestBusinessUnit;

    @FindBy(id = "contributionRequestDate-day")
    public WebElementFacade contributionRequestDateDayField;

    @FindBy(id = "contributionRequestDate-month")
    public WebElementFacade contributionRequestDateMonthField;

    @FindBy(id = "contributionRequestDate-year")
    public WebElementFacade contributionRequestDateYearField;

    @FindBy(id = "contributionDueDate-day")
    public WebElementFacade contributionDueDateDayField;

    @FindBy(id = "contributionDueDate-month")
    public WebElementFacade contributionDueDateMonthField;

    @FindBy(id = "contributionDueDate-year")
    public WebElementFacade contributionDueDateYearField;

    @FindBy(id = "contributionRequestNote")
    public WebElementFacade whatYouAreRequestingTextField;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElementFacade editHypertext;

    @FindBy(xpath = "//label[text()='Complete']")
    public WebElementFacade completeRadioButton;

    @FindBy(xpath = "//label[text()='Cancelled']")
    public WebElementFacade cancelledRadioButton;

    @FindBy(id = "contributionReceivedDate-day")
    public WebElementFacade contributionReceivedDateDayField;

    @FindBy(id = "contributionReceivedDate-month")
    public WebElementFacade contributionReceivedDateMonthField;

    @FindBy(id = "contributionReceivedDate-year")
    public WebElementFacade contributionReceivedDateYearField;

    @FindBy(id = "contributionReceivedNote")
    public WebElementFacade contributionReceivedDetailsTextField;

    @FindBy(id = "contributionCancellationNote")
    public WebElementFacade reasonForCancellingTextField;

    @FindBy(xpath = "//input[@value='Update']")
    public WebElementFacade updateButton;

    public void addAContribution(String contributionType, String requestDate, String dueDate) {
        Date contributionDueDate = null;
        Date currentDate = null;
        String expectedStatus = "";
        switch (contributionType.toUpperCase()) {
            case "CASE":
                safeClickOn(addAContributionHypertext);
                contributionRequestBusinessArea.selectByIndex(1);
                contributionRequestBusinessUnit.selectByIndex(1);
                break;
            case "COMPLAINANT":
                safeClickOn(addComplainantContributionHypertext);
                contributionRequestBusinessArea.selectByIndex(1);
                break;
            case "BUSINESS":
                safeClickOn(addBusinessContributionHypertext);
                contributionRequestBusinessArea.selectByIndex(1);
                break;
            case "FOI":
                safeClickOn(addAContributionHypertext);
                selectRandomOptionFromDropdownWithHeading("Business Unit");
                break;
            default:
                pendingStep(contributionType + " is not defined within " + getMethodName());
        }
        typeIntoDateFields(contributionRequestDateDayField, contributionRequestDateMonthField, contributionRequestDateYearField,
                requestDate);
        typeIntoDateFields(contributionDueDateDayField, contributionDueDateMonthField, contributionDueDateYearField, dueDate);
        setSessionVariable("contributionDueDate").to(dueDate);
        if (compCase()) {
            try {
                contributionDueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDate);
                currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(getDatePlusMinusNDaysAgo(0));
            } catch (ParseException pE) {
                System.out.println("Could not parse dates");
            }
            assert contributionDueDate != null;
            if ((contributionDueDate.after(currentDate) || contributionDueDate.equals(currentDate)) && sessionVariableCalled(
                    "expectedContributionRequestStatus") != ("Overdue")) {
                expectedStatus = "Due";
            } else if (contributionDueDate.before(currentDate)) {
                expectedStatus = "Overdue";
            }
            setSessionVariable("expectedWorkstackCRStatus").to(expectedStatus);
        }
        whatYouAreRequestingTextField.sendKeys("Test - details of request");
        safeClickOn(addButton);
    }

    public void editContributionDueDate() {
        safeClickOn(editHypertext);
        typeIntoDateFields(contributionDueDateDayField, contributionDueDateMonthField, contributionDueDateYearField, getDatePlusMinusNDaysAgo(1));
        setSessionVariable("contributionDueDate").to(getDatePlusMinusNDaysAgo(1));
        safeClickOn(updateButton);
    }

    public void selectActionForIndividualContributionRequest(String action) {
        safeClickOn(editHypertext);
        switch (action.toUpperCase()) {
            case "COMPLETE":
                safeClickOn(completeRadioButton);
                typeIntoDateFields(contributionReceivedDateDayField, contributionReceivedDateMonthField, contributionReceivedDateYearField,
                        getDatePlusMinusNDaysAgo(-1));
                contributionReceivedDetailsTextField.sendKeys("Test - contribution received details");
                break;
            case "CANCEL":
                safeClickOn(cancelledRadioButton);
                reasonForCancellingTextField.sendKeys("Test - contribution cancelled details");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(updateButton);
    }

    public void addMultipleContributionRequests(int numberOfContributionRequests, String contributionType) {
        int count = 0;
        while (count < numberOfContributionRequests) {
            addAContribution(contributionType, getDatePlusMinusNDaysAgo(-1), getDatePlusMinusNDaysAgo(5));
            count++;
        }
        if (mpamCase()) {
            safeClickOn(continueButton);
        }
        setSessionVariable("numberOfContributions").to(numberOfContributionRequests);
    }

    public void assertThatContributionRequestOfTypeIsMarkedAs(String contributionType, String action) {
        contributionType = contributionType.toLowerCase();
        contributionType = contributionType.substring(0, 1).toUpperCase() + contributionType.substring(1);
        WebElementFacade contributionRequestStatus = findBy("//span[contains(text(),'" + contributionType + "')]/parent::legend/following-sibling::table//tr/td"
                + "[2]");
        assertThat(contributionRequestStatus.getText().toUpperCase().contains(action.toUpperCase()), is(true));
    }
}
