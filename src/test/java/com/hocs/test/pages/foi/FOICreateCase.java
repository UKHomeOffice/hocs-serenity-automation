package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.CreateCase_SuccessPage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class FOICreateCase extends BasePage {

    CreateCase createCase;

    CreateCase_SuccessPage createCaseSuccessPage;

    Dashboard dashboard;

    Documents documents;

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[contains(text(), 'Topic')]//following-sibling::div//input")
    public WebElementFacade caseTopicTypeahead;

    @FindBy(id = "KimuDateReceived-day")
    public WebElementFacade dateKIMUReceivedDayField;

    @FindBy(id = "KimuDateReceived-month")
    public WebElementFacade dateKIMUReceivedMonthField;

    @FindBy(id = "KimuDateReceived-year")
    public WebElementFacade dateKIMUReceivedYearField;

    @FindBy(id = "fullname")
    public WebElementFacade fullNameTextField;

    @FindBy(id = "RequestQuestion")
    public WebElementFacade requestQuestionTextArea;

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
            setSessionVariable("requesterFullName").to("Test McTester");
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Country");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test.Email@Test.com", "Email Address");
            enterSpecificTextIntoTextFieldWithHeading("TST/REF/123", "Requester's Reference (Optional)");
        } else if (inboundChannel.equalsIgnoreCase("POST")) {
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test McTester", "Full Name");
            setSessionVariable("requesterFullName").to("Test McTester");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test Building", "Building");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test Street", "Street");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test Town", "Town or City");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("TST PSTCD", "Postcode");
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Country");
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Test.Email@Test.com", "Email Address (Optional)");
            enterSpecificTextIntoTextFieldWithHeading("TST/REF/123", "Requester's Reference (Optional)");
        }
    }

    public void selectFOITopic(String topic) {
        caseTopicTypeahead.sendKeys(topic);
        caseTopicTypeahead.sendKeys(Keys.RETURN);
        setSessionVariable("foiTopic").to(topic);
    }

    public void enterRequestQuestion() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Request Question", "Request Question");
        setSessionVariable("requestQuestion").to("Test Request Question");
    }

    public void createFOICase() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        createCase.selectCaseType("FOI");
        clickTheButton("Next");
        createCase.storeCorrespondenceReceivedDate();
        storeCorrespondenceReceivedInKIMUDate();
        documents.uploadDocumentOfType("docx");
        selectCorrespondenceInboundChannel();
        enterCorrespondentDetails();
        selectFOITopic("Animal alternatives (3Rs)");
        enterRequestQuestion();
        clickTheButton("Submit");
        createCaseSuccessPage.storeCaseReference();
    }
}