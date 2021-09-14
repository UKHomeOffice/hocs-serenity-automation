package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class Markup extends BasePage {

    Markup_AddTopics markupAddTopics;

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[text()='Policy Response']")
    public WebElementFacade policyResponseRadioButton;

    @FindBy(xpath = "//label[text()='Refer To OGD']")
    public WebElementFacade referToOgdRadioButton;

    @FindBy(xpath = "//label[text()='FAQ Response']")
    public WebElementFacade faqRadioButton;

    @FindBy(xpath = "//label[text()='No Response Needed']")
    public WebElementFacade noReplyNeededRadioButton;

    @FindBy(xpath = "//label[text()='Reject To Data Input']")
    public WebElementFacade rejectToDataInputRadioButton;

    @FindBy(id = "OGDDept")
    public WebElementFacade OGDDestinationTextBox;

    @FindBy(id = "CaseNote_OGD")
    public WebElementFacade OGDReasonTextBox;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addATopicLink;

    @FindBy(id = "CaseNote_NRN")
    public WebElementFacade noResponseNeededTextField;

    @FindBy(id = "CaseNote_REJ")
    public WebElementFacade rejectToDataInputTextField;

    @FindBy(id = "POTeamName")
    public WebElementFacade privateOfficeTeamTextField;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicLink;

    //Basic Methods

    public void selectASpecificResponseType(String responseType) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(responseType, "What sort of response is required?");
    }

    public void selectFAQRadioButton() {
        selectASpecificResponseType("FAQ Response");
    }

    public void selectNoResponseNeededRadioButton() {
        selectASpecificResponseType("No Response Needed");
    }

    public void selectPolicyResponseRadioButton() {
        selectASpecificResponseType("Policy Response");
    }

    public void selectReferToOGDRadioButton() {
        selectASpecificResponseType("Refer to OGD");
    }

    public void selectRejectToDataInput() {
        selectASpecificResponseType("Reject To Data Input");;
    }

    public void enterAOGDDestination() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Where should this case be transferred to?");
    }

    public void enterAOGDReason() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Why should this case be transferred here?");
    }

    public void enterANoResponseNeededReason() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Why is no response needed?");
    }

    public void enterARejectionReason() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why should this case be rejected?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void clickAddTopicLink() {
        safeClickOn(addTopicLink);
    }

    //Assertions

    public void assertNRNTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(noResponseNeededTextField), is(true));
    }

    public void assertRejectTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(rejectToDataInputTextField), is(true));
    }

    public void assertOGDDestinationTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(OGDDestinationTextBox), is(true));
    }
}
