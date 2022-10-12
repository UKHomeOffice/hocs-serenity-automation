package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CaseCreationStage extends BasePage {

    CreateCase createCase;

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//span[text()='Date FOI received']/parent::div/following-sibling::div[1]")
    public WebElementFacade receivedDateValue;

    @FindBy(xpath = "//span[text()='Date received in KIMU']/parent::div/following-sibling::div[1]")
    public WebElementFacade dateReceivedInKIMUValue;

    @FindBy(xpath = "//span[text()='How was the correspondence received?']/parent::div/following-sibling::div[1]")
    public WebElementFacade inboundChannelValue;

    @FindBy(xpath = "//span[text()='FOI Topic']/parent::div/following-sibling::div[1]")
    public WebElementFacade foiTopicValue;

    @FindBy(xpath = "//span[text()='Request Question']/parent::div/following-sibling::div[1]")
    public WebElementFacade requestQuestionValue;

    @FindBy(xpath = "//span[text()='Primary correspondent']/parent::div/following-sibling::div[1]")
    public WebElementFacade primaryCorrespondentValue;

    public void selectValidityOfRequest(String validity) {
        selectSpecificRadioButtonFromGroupWithHeading(validity, "Case Validity");
    }

    public void editCaseDetail(String valueToBeEdited) {
        String xpathText = "";
        String fieldHeader = "";
        switch (valueToBeEdited.toUpperCase()) {
            case "DATE FOI RECEIVED":
                xpathText = "Date FOI received";
                fieldHeader = "When was the correspondence received?";
                break;
            case "DATE RECEIVED IN KIMU":
                xpathText = "Date received in KIMU";
                fieldHeader = "Date received in KIMU";
                break;
            case "INBOUND CHANNEL":
                xpathText = "How was the correspondence received?";
                fieldHeader = "How was the correspondence received?";
                break;
            case "FOI TOPIC":
                xpathText = "FOI Topic";
                fieldHeader = "FOI Topic";
                break;
            case "REQUEST QUESTION":
                xpathText = "Request Question";
                fieldHeader = "Request Question";
                break;
            case "PRIMARY CORRESPONDENT":
                xpathText = "Primary correspondent";
                fieldHeader = "Full Name";
                break;
            default:
                pendingStep(valueToBeEdited + " is not defined within " + getMethodName());
        }
        WebElementFacade changeHyperTextForValue = findBy("//span[text()='" + xpathText + "']/parent::div//following-sibling::div[2]/a");
        safeClickOn(changeHyperTextForValue);
        if (valueToBeEdited.equalsIgnoreCase("DATE FOI RECEIVED") || valueToBeEdited.equalsIgnoreCase("DATE RECEIVED IN KIMU")) {
            recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-10), fieldHeader);
            if (valueToBeEdited.equalsIgnoreCase("DATE FOI RECEIVED")) {
                setSessionVariable("correspondenceReceivedDate").to(getDatePlusMinusNDaysAgo(-10));
            } else {
                setSessionVariable("correspondenceReceivedByKIMUDate").to(getDatePlusMinusNDaysAgo(-10));
            }
        } else if (valueToBeEdited.equalsIgnoreCase("INBOUND CHANNEL")) {
            WebElementFacade unselectedInboundChannelRadioButton = findBy("//div[@id='OriginalChannel-radios']//input[not(@checked)]/following-sibling::label");
            setSessionVariable("foiInboundChannel").to(unselectedInboundChannelRadioButton.getText());
            safeClickOn(unselectedInboundChannelRadioButton);
        } else if (valueToBeEdited.equalsIgnoreCase("FOI TOPIC")) {
            selectSpecificOptionFromTypeaheadWithHeading("Alchohol industry", "Case Topic");
            setSessionVariable("foiTopic").to("Alcohol industry");
        } else if (valueToBeEdited.equalsIgnoreCase("REQUEST QUESTION")) {
            recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Edited Test Request Question", fieldHeader);
            setSessionVariable("requestQuestion").to("Edited Test Request Question");
        } else if (valueToBeEdited.equalsIgnoreCase("PRIMARY CORRESPONDENT")) {
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading("Edited Test McTester", fieldHeader);
            setSessionVariable("correspondentFullName").to("Edited Test McTester");
        }
        if (valueToBeEdited.equalsIgnoreCase("PRIMARY CORRESPONDENT")) {
            clickTheButton("Save");
        } else {
            clickConfirmButton();
        }
    }

    public void assertCaseDetailsAreCorrect() {
        receivedDateValue.waitUntilVisible();
        String displayedReceivedDate = receivedDateValue.getText();
        String enteredReceivedDate = sessionVariableCalled("correspondenceReceivedDate");
        assertThat(displayedReceivedDate.equals(enteredReceivedDate), is(true));

        String displayedReceivedByKIMUDate = dateReceivedInKIMUValue.getText();
        String enteredReceivedByKIMUDate = sessionVariableCalled("correspondenceReceivedByKIMUDate");
        assertThat(displayedReceivedByKIMUDate.equals(enteredReceivedByKIMUDate), is(true));

        String displayedInboundChannel = inboundChannelValue.getText();
        String enteredInboundChannel = sessionVariableCalled("foiInboundChannel");
        assertThat(displayedInboundChannel.equals(enteredInboundChannel), is(true));

        String displayedFOITopic = foiTopicValue.getText();
        String enteredFOITopic = sessionVariableCalled("foiTopic");
        assertThat(displayedFOITopic.equals(enteredFOITopic), is(true));

        String displayedRequestQuestion = requestQuestionValue.getText();
        String enteredRequestQuestion = sessionVariableCalled("requestQuestion");
        assertThat(displayedRequestQuestion.equals(enteredRequestQuestion), is(true));

        String displayedRequesterName = primaryCorrespondentValue.getText();
        String enteredRequesterName = sessionVariableCalled("correspondentFullName");
        assertThat(displayedRequesterName.equals(enteredRequesterName), is(true));
    }

    public void enterAValidRequestAcknowledgementResponseDate() {
        String responseDate = getDatePlusMinusNDaysAgo(-10);
        recordCaseData.enterDateIntoDateFieldsWithHeading(responseDate, "When was the acknowledgement response to the valid request issued?");
    }

    public void enterAnInvalidRequestResponseDate() {
        String responseDate = getDatePlusMinusNDaysAgo(-10);
        recordCaseData.enterDateIntoDateFieldsWithHeading(responseDate, "What day was the response sent?");
    }
}