package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class Draft extends BasePage {

    Homepage homepage;

    @FindBy(css = "label[for='ChannelOut-Email']")
    private WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    private WebElementFacade responseChannelLetterRadioButton;

    @FindBy(css = "label[for='ChannelOut-Phone']")
    private WebElementFacade responseChannelPhoneRadioButton;

    @FindBy(css = "label[for='ChannelOut-Outreach']")
    private WebElementFacade responseChannelOutreachRadioButton;

    @FindBy(xpath = "//label[text()='Move to QA']")
    public WebElementFacade moveToQARadioButton;

    @FindBy(xpath = "//label[text()='Ready for dispatch (bypass QA)']")
    public WebElementFacade readyForDispatchBypassQARadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade putOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='Contribution requested']")
    public WebElementFacade contributionRequestedRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    @FindBy(xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    @FindBy(xpath = "//label[text()='Close case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(id = "CaseNote_DraftClose")
    public WebElementFacade closureReasonTextArea;

    @FindBy(id = "DueDate-day")
    public WebElementFacade requestContributionDeadlineDayTextField;

    @FindBy(id = "DueDate-month")
    public WebElementFacade requestContributionDeadlineMonthTextField;

    @FindBy(id = "DueDate-year")
    public WebElementFacade requestContributionDeadlineYearTextField;

    @FindBy(id = "CaseNote_DraftRequestContribution")
    public WebElementFacade requestContributionTextArea;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Deadline for contribution request is required']")
    public WebElementFacade contributionRequestDeadlineRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='What you are requesting is required']")
    public WebElementFacade contributionRequestDescriptionRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Response channel is required']")
    public WebElementFacade responseChannelRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Change reference type']")
    public WebElementFacade changeReferenceTypeLink;

    @FindBy(xpath = "//input[@value='Correction']")
    public WebElementFacade correctionTickBox;

    public void moveCaseFromDraftToQA() {
        selectResponseChannel("Email");
        safeClickOn(moveToQARadioButton);
        safeClickOn(confirmButton);
    }

    public void moveBRefCaseFromDraftToDispatch() {
        selectResponseChannel("Email");
        safeClickOn(readyForDispatchBypassQARadioButton);
        safeClickOn(confirmButton);
    }

    public void escalateCaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
    }

    public void putCaseOnHold() {
        safeClickOn(putOnHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void takeTriageCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void deescalateTriageCase() {
        safeClickOn(escalationCompleteRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectToCloseEscalatedCase() {
        safeClickOn(closeCaseRadioButton);
        safeClickOn(confirmButton);
    }

    public void submitReasonToCloseEscalatedCase(String closureReason) {
        typeInto(closureReasonTextArea, closureReason);
        safeClickOn(closeCaseButton);
        setSessionVariable("closureReason").to(closureReason);
    }

    public void selectResponseChannel(String outboundChannel) {
        switch (outboundChannel.toUpperCase()) {
            case "EMAIL":
                safeClickOn(responseChannelEmailRadioButton);
                break;
            case "LETTER":
                safeClickOn(responseChannelLetterRadioButton);
                break;
            case "PHONE":
                safeClickOn(responseChannelPhoneRadioButton);
                break;
            case "OUTREACH":
                safeClickOn(responseChannelOutreachRadioButton);
                break;
            default:
                pendingStep(outboundChannel + " is not defined within " + getMethodName());
        }
        setSessionVariable("responseChannel").to(outboundChannel);
    }

    public void selectContributionRequested() {
        safeClickOn(contributionRequestedRadioButton);
        safeClickOn(confirmButton);
    }

    public void enterContributionRequestDeadlineDate(String dd, String mm, String yyyy) {
        typeInto(requestContributionDeadlineDayTextField, dd);
        typeInto(requestContributionDeadlineMonthTextField, mm);
        typeInto(requestContributionDeadlineYearTextField, yyyy);
        setSessionVariable("requestDeadline").to(dd + "/" + mm + "/" + yyyy);
    }

    public void enterRequestDescription(String requestDescription) {
        typeInto(requestContributionTextArea, requestDescription);
        setSessionVariable("requestDescription").to(requestDescription);
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        assertThat(actionsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertContributionRequestDeadlineRequiredErrorMessageDisplayed()  {
        assertThat(contributionRequestDeadlineRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertContributionRequestDescriptionRequiredErrorMessageDisplayed()  {
        assertThat(contributionRequestDescriptionRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertResponseChannelRequiredErrorMessageDisplayed() {
        assertThat(responseChannelRequiredErrorMessage.isVisible(), is(true));
    }
}
