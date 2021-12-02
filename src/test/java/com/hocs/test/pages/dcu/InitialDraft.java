package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import config.User;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InitialDraft extends BasePage {

    Documents documents;

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//div[@id='InitialDraftDecision-radios']//label[text()='No']")
    public WebElementFacade answeredByMyTeamNoRadioButton;

    @FindBy(xpath = "//div[@id='InitialDraftDecision-radios']//label[text()='Yes']")
    public WebElementFacade answeredByMyTeamYesRadioButton;

    @FindBy(xpath = "//div[@id='ReturnToResponseChannelDecision-radios']//label[text()='No']")
    public WebElementFacade chooseAnotherResponseTypeNoButton;

    @FindBy(xpath = "//div[@id='ReturnToResponseChannelDecision-radios']//label[text()='Yes']")
    public WebElementFacade chooseAnotherResponseTypeYesButton;

    @FindBy(xpath = "//label[text()='Email']")
    public WebElementFacade emailReplyRadioButton;

    @FindBy(xpath = "//label[text()='Letter']")
    public WebElementFacade letterReplyRadioButton;

    @FindBy(xpath = "//label[text()='Phone']")
    public WebElementFacade phoneReplyRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_PhonecallNote']")
    public WebElementFacade summariseCallTextBox;

    @FindBy(xpath = "//div[@id='OfflineQA-radios']//label[text()='Yes']")
    public WebElementFacade offlineQaYesRadioButton;

    @FindBy(xpath = "//div[@id='OfflineQA-radios']//label[text()='No']")
    public WebElementFacade offlineQaNoRadioButton;

    @FindBy(id = "OfflineQaUser")
    public WebElementFacade allocateToOfflineQaDropdown;

    @FindBy(xpath = "//h2[text()='Available Standard line']/following-sibling::table[1]//td")
    public WebElementFacade standardLineDocuments;

    //Basic Methods

    public void selectIfCaseCanBeAnsweredByTeam(String yesNo) {
        recordCaseData.selectSpecificRadioButton(yesNo);
    }

    public void enterReasonTeamCannotAnswer() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Why should this not be answered by your team?");
    }

    public void selectSpecificResponseChannel(String responseChannel) {
        recordCaseData.selectSpecificRadioButton(responseChannel);
    }

    public void enterCallSummary() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Please summarise your call.");
    }

    public void selectIfAnotherResponseTypeRequired(String yesNo) {
        selectSpecificRadioButton(yesNo);
    }

    public void selectQAOfflineDecision(String yesNo) {
        recordCaseData.selectSpecificRadioButton(yesNo);
    }

    public void selectSpecificOfflineQAIndividual(String individual) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(individual, "Who has done the Offline QA for this case?");
    }

    public void selectAOfflineQAIndividual() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Who has done the Offline QA for this case?");
    }

    public void assertStandardLineDocumentIsVisible() {
        String standardLineFileName = sessionVariableCalled("standardLineFileName");
        assertThat(standardLineDocuments.getText().contains(standardLineFileName), is(true));
    }
}
