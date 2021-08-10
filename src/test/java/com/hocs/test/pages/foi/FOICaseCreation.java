package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class FOICaseCreation extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(id = "KimuDateReceived-day")
    public WebElementFacade dateKIMUReceivedDayField;

    @FindBy(id = "KimuDateReceived-month")
    public WebElementFacade dateKIMUReceivedMonthField;

    @FindBy(id = "KimuDateReceived-year")
    public WebElementFacade dateKIMUReceivedYearField;

    public void storeCorrespondenceReceivedInKIMUDate() {
        String correspondenceDay = dateKIMUReceivedDayField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUDay").to(correspondenceDay);
        String correspondenceMonth = dateKIMUReceivedMonthField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUMonth").to(correspondenceMonth);
        String correspondenceYear = dateKIMUReceivedYearField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUYear").to(correspondenceYear);
        setSessionVariable("correspondenceReceivedByKIMUDate").to(correspondenceDay + "/" + correspondenceMonth + "/" +correspondenceYear);
    }

    public void selectCorrespondenceInboundChannel() {
        String channel = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("How was the request received?");
        setSessionVariable("foiInboundChannel").to(channel);
    }

    public void enterCorrespondentDetails() {
        String inboundChannel = sessionVariableCalled("foiInboundChannel");
        if (inboundChannel.equalsIgnoreCase("EMAIL")) {
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test McTester", "Full Name");
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Country");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test.Email@Test.com", "Email Address");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("TST/REF/123", "Requester's Reference (Optional)");
        } else if (inboundChannel.equalsIgnoreCase("POST")) {
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test McTester", "Full Name");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test Building", "Building");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test Street", "Street");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test Town", "Town or City");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("TST PSTCD", "Postcode");
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Country");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test.Email@Test.com", "Email Address (Optional)");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("TST/REF/123", "Requester's Reference (Optional)");
        }
    }

    public void selectFOITopic() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Case Topic");
    }

    public void enterRequestQuestion() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Request Question", "Request Question");
    }

    public void selectValidityOfRequest(String validity) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(validity, "Is this a valid request?");
    }
}