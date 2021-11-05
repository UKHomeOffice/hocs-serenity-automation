package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.User;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class SummaryTab extends BasePage {

    Workdays workdays;

    @FindBy(xpath = "//a[text()='Summary']")
    public WebElementFacade summaryTab;

    @FindBy(xpath = "//th[text()='Deadline']/following-sibling::td")
    private WebElementFacade deadline;

    @FindBy(xpath = "//th[text()='Primary topic']/following-sibling::td")
    public WebElementFacade primaryTopic;

    @FindBy(xpath = "//th[text()='Primary correspondent']/following-sibling::td")
    private WebElementFacade primaryCorrespondent;

    @FindBy(xpath = "//th[text()='How was the correspondence received?']/following-sibling::td")
    private WebElementFacade howCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='Should the response be copied to Number 10?']/following-sibling::td")
    private WebElementFacade shouldResponseCopyToN10;

    @FindBy(xpath = "//th[text()='When was the correspondence received?']/following-sibling::td")
    public WebElementFacade correspondenceReceivedDate;

    @FindBy(xpath = "//th[text()='When was the correspondence sent?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceSent;

    @FindBy(xpath = "//h2[text()='Active stage']")
    public WebElementFacade activeStageHeader;

    @FindBy(xpath = "//h2[text()='Active stage']/following-sibling::table[1]/caption[not(contains(text(),'Summary'))]")
    public WebElementFacade activeStage;

    @FindBy(xpath = "//th[text()='Team']/following-sibling::td")
    public WebElementFacade currentTeam;

    @FindBy(xpath = "//th[text()='Private Office Team']/following-sibling::td")
    public WebElementFacade privateOfficeTeam;

    @FindBy(xpath = "//th[text()='Override Private Office Team']/following-sibling::td")
    public WebElementFacade overridePrivateOfficeTeam;

    @FindBy(xpath = "//th[text()='User']/following-sibling::td")
    private WebElementFacade allocatedUser;

    @FindBy(xpath = "//th[text()='Data Input']/following-sibling::td")
    private WebElementFacade dataInputDeadlineDate;

    @FindBy(xpath = "//th[text()='Markup']/following-sibling::td")
    private WebElementFacade markupDeadlineDate;

    @FindBy(xpath = "//th[text()='Initial Draft']/following-sibling::td")
    private WebElementFacade initialDraftDeadlineDate;

    @FindBy(xpath = "//th[text()='QA Response']/following-sibling::td")
    private WebElementFacade qaResponseDeadlineDate;

    @FindBy(xpath = "//th[text()='Private Office Approval']/following-sibling::td")
    private WebElementFacade privateOfficeApprovalDeadlineDate;

    @FindBy(xpath = "//th[text()='Ministerial Sign off']/following-sibling::td")
    private WebElementFacade ministerialSignOffDeadlineDate;

    @FindBy(xpath = "//th[text()='Transfer Confirmation']/following-sibling::td")
    private WebElementFacade transferConfirmationDeadlineDate;

    @FindBy(xpath = "//th[text()='No Response Needed Confirmation']/following-sibling::td")
    private WebElementFacade noResponseNeededConfirmationDeadlineDate;

    @FindBy(xpath = "//th[text()='Dispatch']/following-sibling::td")
    private WebElementFacade dispatchDeadlineDate;

    @FindBy(xpath = "//th[text()='Copy to Number 10']/following-sibling::td")
    private WebElementFacade copyToNumber10DeadlineDate;

    @FindBy(xpath = "//th[text()='Deadline']/following-sibling::td")
    private WebElementFacade mpamDeadlineDate;

    @FindBy(xpath = "//th[contains(text(), 'Ministerial response')]/following-sibling::td")
    public WebElementFacade isMinisterialResponseRequired;

    @FindBy(xpath = "//th[text()='Deadline for contribution request']/following-sibling::td")
    private WebElementFacade contributionRequestDeadline;

    @FindBy(xpath = "//th[text()='Follow-up due by']/following-sibling::td")
    private WebElementFacade followUpDueDate;

    @FindBy(xpath = "//th[text()='Campaign']/following-sibling::td")
    private WebElementFacade campaign;

    @FindBy(xpath = "//th[contains(text(), 'Home Secretary')]/following-sibling::td")
    public WebElementFacade homeSecInterest;

    @FindBy(xpath = "//th[contains(text(), 'FOI Topic')]/following-sibling::td")
    public WebElementFacade foiTopic;

    @FindBy(xpath = "//caption[text()='Previous Case']/following-sibling::tbody//a")
    public WebElementFacade previousCOMPCaseReference;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Details']/following-sibling::td")
    public WebElementFacade appealDetails;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Completed Date']/following-sibling::td")
    public WebElementFacade appealCompletionDate;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Complete']/following-sibling::td")
    public WebElementFacade appealComplete;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Outcome']/following-sibling::td")
    public WebElementFacade appealOutcome;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Complex case']/following-sibling::td")
    public WebElementFacade appealComplexity;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Officer Name']/following-sibling::td")
    public WebElementFacade appealOfficerName;

    @FindBy(xpath = "//h2[text()='Appeal']/following-sibling::table//th[text()='Directorate']/following-sibling::td")
    public WebElementFacade appealDirectorate;

    public void selectSummaryTab() {
        if(!summaryTabIsActiveTab()) {
            safeClickOn(summaryTab);
        }
    }

    public boolean summaryTabIsActiveTab() {
        return summaryTab.getAttribute("class").contains("active");
    }

    public void assertSummaryContainsExpectedValueForGivenHeader(String value, String header) {
        String displayedValue = getSummaryTabValueForGivenHeader(header);
        String expectedDisplayValue = value.replace("\n", " ");
        if (!displayedValue.contains(expectedDisplayValue)) {
            Assert.fail("Summary Tab value incorrect for: "+ header + "\nExpected value was: " + value + "\nDisplayed value was: " + displayedValue);
        }
    }

    public String getSummaryTabValueForGivenHeader(String header) {
        selectSummaryTab();
        WebElementFacade displayedValueElement = findBy("//th[text()='"+ header +"']/following-sibling::td");
        return displayedValueElement.getText();
    }

    public String getPrimaryCorrespondent() {
        return primaryCorrespondent.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().getText();
    }

    public String getActiveStage() {
        return activeStage.getText();
    }

    public boolean checkDeadline(String displayedDeadline, String deadlineStartDate, int expectedNumberOfWorkdaysTillDeadline) {
        String expectedDeadline = workdays.getDateXWorkdaysFromSetDate(expectedNumberOfWorkdaysTillDeadline, deadlineStartDate);
        return displayedDeadline.equals(expectedDeadline);
    }

    public void selectPreviousCaseReference() {
        safeClickOn(previousCOMPCaseReference);
    }

    public void assertCampaignInSummaryTabIsCorrect(String input) {
        String displayedCampaign = campaign.getText().toUpperCase();
        assertThat(input.toUpperCase().equals(displayedCampaign), is(true));
    }

    public void assertDeadlineDateOfStage(String caseType, String stage) {
        int expectedNumberOfWorkdaysTillDeadline = 0;
        String displayedDeadline = null;
        String correspondenceReceivedDate  = sessionVariableCalled("correspondenceReceivedDate");
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        displayedDeadline = dataInputDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "MARKUP":
                        displayedDeadline = markupDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "QA RESPONSE":
                        displayedDeadline = qaResponseDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        displayedDeadline = privateOfficeApprovalDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 19;
                        break;
                    case "MINISTERIAL SIGN OFF":
                        displayedDeadline = ministerialSignOffDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 19;
                        break;
                    case "TRANSFER CONFIRMATION":
                        displayedDeadline = transferConfirmationDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        displayedDeadline = noResponseNeededConfirmationDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                    }
                assertThat(checkDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline), is(true));
                break;
            case "DTEN":
                String inputDeadline = null;
                switch (stage.toUpperCase()) {
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.getText();
                        inputDeadline = sessionVariableCalled("dtenDispatchDeadline");
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.getText();
                        inputDeadline = sessionVariableCalled("dtenInitialDraftDeadline");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertThat(displayedDeadline.equals(inputDeadline), is(true));
                break;
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        displayedDeadline = dataInputDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "MARKUP":
                        displayedDeadline = markupDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "QA RESPONSE":
                        displayedDeadline = qaResponseDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "TRANSFER CONFIRMATION":
                        displayedDeadline = transferConfirmationDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        displayedDeadline = noResponseNeededConfirmationDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "COPY TO NUMBER 10":
                        displayedDeadline = copyToNumber10DeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertThat(checkDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline), is(true));
                break;
            case "MPAM":
                displayedDeadline = mpamDeadlineDate.getText();
                expectedNumberOfWorkdaysTillDeadline = 20;
                assertThat(checkDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline), is(true));
                break;
            case "HOME SECRETARY SIGN-OFF":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        displayedDeadline = dataInputDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "MARKUP":
                        displayedDeadline = markupDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 7;
                        break;
                    case "QA RESPONSE":
                        displayedDeadline = qaResponseDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 7;
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        displayedDeadline = privateOfficeApprovalDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 9;
                        break;
                    case "MINISTERIAL SIGN OFF":
                        displayedDeadline = ministerialSignOffDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 9;
                        break;
                    case "TRANSFER CONFIRMATION":
                        displayedDeadline = transferConfirmationDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        displayedDeadline = noResponseNeededConfirmationDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertThat(checkDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline), is(true));
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void assertCaseStage(String stage) {
        activeStage.waitUntilVisible();
        assertThat(getActiveStage().toUpperCase(), is(stage.toUpperCase()));
    }

    public void assertAllocatedUserIs(User user) {
        allocatedUser.shouldContainText(user.getUsername());
    }

    public void assertAllocatedUserIsMe(boolean trueFalse) {
        assertThat(allocatedUser.getText().contains(getCurrentUser().getUsername()), is(trueFalse));
    }

    public void assertAllocatedTeam(String team) {
        currentTeam.shouldContainText(team);
    }

    public void assertAllocatedMPAMTeam(String stage) {
        if(!currentTeam.isVisible()) {
            selectSummaryTab();
        }
        String activeTeam = currentTeam.getText();
        switch (stage) {
            case "Private Office":
                assertThat(activeTeam.contains("PO"), is(true));
                break;
            case "Awaiting Dispatch":
                assertThat(activeTeam.contains("Draft"), is(true));
                break;
            default:
                assertThat(activeTeam.contains(stage), is(true));
        }
        String businessArea = sessionVariableCalled("businessArea");
        String refType = sessionVariableCalled("refType");
        assertThat(activeTeam.contains(businessArea) && activeTeam.contains(refType), is(true));
    }

    public void assertFollowUpDueDateVisible() {
        String dueDate = sessionVariableCalled("dueDate");
        assertThat(followUpDueDate.getText().contains(dueDate), is(true));
    }

    public void assertHomeSecInterestMatchesDecisionAtDataInput() {
        String homeSecInterestInput = sessionVariableCalled("homeSecInterest");
        assertThat(homeSecInterestInput.equals(homeSecInterest.getText()), is(true));
    }

    public void assertComplianceMeasures(String inputComplianceMeasures) {
        String displayedComplianceMeasures = getSummaryTabValueForGivenHeader("Compliance Measures");
        assertThat(displayedComplianceMeasures.toUpperCase().contains(inputComplianceMeasures.toUpperCase()), is(true));
    }

    public void assertComplianceMeasureDetails() {
        String inputComplianceMeasureDetails = sessionVariableCalled("complianceMeasureDetails");
        String displayedComplianceMeasureDetails = getSummaryTabValueForGivenHeader("Compliance measures other details");
        assertThat(displayedComplianceMeasureDetails.toUpperCase().contains(inputComplianceMeasureDetails.toUpperCase()), is(true));
    }

    public void assertDeadlineOfExtendedFOICase() {
        String displayedDeadline = deadline.getText();
        checkDeadline(displayedDeadline, getTodaysDate(), sessionVariableCalled("numberOfDays"));
    }

    public void assertAppealInformationIsDisplayed() {
        String appealType = sessionVariableCalled("appealType");
        WebElementFacade appealTypeHeader = findBy("//h2[text()='Appeal']/following-sibling::table/caption");
        appealTypeHeader.shouldContainText(appealType);
        if (appealType.equalsIgnoreCase("Internal Review")) {
            appealDirectorate.shouldContainText(sessionVariableCalled("appealOfficerDirectorate"));
            appealOfficerName.shouldContainText(sessionVariableCalled("appealOfficerName"));
        }
        appealComplete.shouldContainText(sessionVariableCalled("appealComplete"));
        appealCompletionDate.shouldContainText(sessionVariableCalled("appealCompletionDate"));
        appealOutcome.shouldContainText(sessionVariableCalled("appealOutcome"));
        appealComplexity.shouldContainText(sessionVariableCalled("appealComplexity"));
        appealDetails.shouldContainText(sessionVariableCalled("appealDetails"));
    }

    public void assertThereIsNoActiveStage() {
        selectSummaryTab();
        if(activeStage.isVisible()) {
            Assert.fail("Case is at " + activeStage.getText() + " stage when expected to be closed");
        }
    }
}