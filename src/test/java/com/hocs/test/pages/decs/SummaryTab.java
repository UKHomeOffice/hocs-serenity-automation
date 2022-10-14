package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.User;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class SummaryTab extends BasePage {

    Workdays workdays;

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
    public WebElementFacade allocatedTeam;

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

    @FindBy(xpath = "//th[contains(text(), 'Campaign')]/following-sibling::td")
    private WebElementFacade campaign;

    @FindBy(xpath = "//th[contains(text(), 'Home Secretary')]/following-sibling::td")
    public WebElementFacade homeSecInterest;

    @FindBy(xpath = "//th[contains(text(), 'FOI Topic')]/following-sibling::td")
    public WebElementFacade foiTopic;

    @FindBy(xpath = "//caption[text()='Previous Case']/following-sibling::tbody//a")
    public WebElementFacade previousCOMPCaseReference;


    public void selectSummaryTab() {
        selectTheTab("Summary");
    }

    public void assertSummaryContainsExpectedValueForGivenHeader(String value, String header) {
        String expectedDisplayValue = value.replace("\n", " ");
        String displayedValue = getSummaryTabValueForGivenHeader(header);
        try {
            assertThat(stringContainsCheckIgnoringCase(displayedValue, expectedDisplayValue), is(true));
        } catch (AssertionError e) {
            waitABit(100);
            refreshTheTab("Summary");
            displayedValue = getSummaryTabValueForGivenHeader(header);
            if (!stringContainsCheckIgnoringCase(displayedValue, expectedDisplayValue)) {
                Assert.fail("Summary Tab value incorrect for: " + header + "\nExpected value was: \"" + value + "\"\nDisplayed value was: \"" +
                        displayedValue + "\"");
            }
        }
    }

    public void assertPrimaryCorrespondentDetailMatchValue(String correspondentDetail) {
        int n = 0;
        List<WebElementFacade> primaryCorrespondentDetails = findAll("//th[text()='Primary correspondent']/following-sibling::td/span");
        while (n < primaryCorrespondentDetails.size()) {
            String displayedDetail = primaryCorrespondentDetails.get(n).getText();
            if (displayedDetail.equalsIgnoreCase(correspondentDetail)) {
                assertThat(displayedDetail.equalsIgnoreCase(correspondentDetail), is(true));
            }
            n++;
        }
    }

    public String getSummaryTabValueForGivenHeader(String header) {
        selectSummaryTab();
        WebElementFacade displayedValueElement = findBy("//th[text()='" + header + "']/following-sibling::td");
        return displayedValueElement.getText();
    }

    public String getActiveStage() {
        return activeStage.getText();
    }

    public void assertDisplayedDeadlineMatchesCalculatedDeadline(String displayedDeadline, String deadlineStartDate, int expectedNumberOfWorkdaysTillDeadline) {
        String expectedDeadline = workdays.getDateXWorkdaysFromSetDateForGivenCaseType(expectedNumberOfWorkdaysTillDeadline, deadlineStartDate,
                getCurrentCaseType());
        if (!displayedDeadline.equals(expectedDeadline)) {
            Assert.fail("Displayed deadline did not match deadline calculated for this case.\nExpected deadline was: " + expectedDeadline +
                    "\nDisplayed deadline was: " + displayedDeadline);
        }
    }

    public void selectPreviousCaseReference() {
        safeClickOn(previousCOMPCaseReference);
    }

    public void assertCampaignInSummaryTabIsCorrect(String input) {
        String displayedCampaign = campaign.getText().toUpperCase();
        assertThat(input.toUpperCase().equals(displayedCampaign), is(true));
    }

    public void assertDeadlineDateOfCaseIsCorrect(String deadlineDecidingFactor) {
        String displayedDeadline = getSummaryTabValueForGivenHeader("Deadline");
        if (deadlineDecidingFactor.equalsIgnoreCase("DTEN")) {
            assertThat(displayedDeadline.equalsIgnoreCase(sessionVariableCalled("dtenDispatchDeadline")), is(true));
        } else {
            int expectedNumberOfWorkdaysTillDeadline = 0;
            String correspondenceReceivedDate = sessionVariableCalled("correspondenceReceivedDate");
            switch (deadlineDecidingFactor.toUpperCase()) {
                case "PRIORITY GRO COMPLAINT":
                    expectedNumberOfWorkdaysTillDeadline = 1;
                    break;
                case "NON-PRIORITY, NON-POST GRO COMPLAINT":
                    expectedNumberOfWorkdaysTillDeadline = 5;
                    break;
                case "HOME SECRETARY SIGN-OFF":
                case "POGR":
                case "NON-PRIORITY, POST GRO COMPLAINT":
                    expectedNumberOfWorkdaysTillDeadline = 10;
                    break;
                case "MIN":
                case "TRO":
                case "MPAM":
                case "MTS":
                case "COMP":
                case "COMP2":
                case "IEDET":
                case "FOI":
                case "BF":
                case "BF2":
                case "TO":
                    expectedNumberOfWorkdaysTillDeadline = 20;
                    break;
                case "EX-GRATIA":
                    expectedNumberOfWorkdaysTillDeadline = 60;
                    break;
                default:
                    pendingStep(deadlineDecidingFactor + " is not defined within " + getMethodName());
            }
            assertDisplayedDeadlineMatchesCalculatedDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline);
        }
    }

    public void assertDeadlineDateOfStage(String deadlineDecidingFactor, String stage) {
        int expectedNumberOfWorkdaysTillDeadline = 0;
        String displayedDeadline = null;
        String correspondenceReceivedDate = sessionVariableCalled("correspondenceReceivedDate");
        switch (deadlineDecidingFactor.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        displayedDeadline = dataInputDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "MARKUP":
                        displayedDeadline = markupDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "QA RESPONSE":
                        displayedDeadline = qaResponseDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        displayedDeadline = privateOfficeApprovalDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 19;
                        break;
                    case "MINISTERIAL SIGN OFF":
                        displayedDeadline = ministerialSignOffDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 19;
                        break;
                    case "TRANSFER CONFIRMATION":
                        displayedDeadline = transferConfirmationDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        displayedDeadline = noResponseNeededConfirmationDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertDisplayedDeadlineMatchesCalculatedDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline);
                break;
            case "DTEN":
                String inputDeadline = null;
                switch (stage.toUpperCase()) {
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.waitUntilVisible().getText();
                        inputDeadline = sessionVariableCalled("dtenDispatchDeadline");
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.waitUntilVisible().getText();
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
                        displayedDeadline = dataInputDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "MARKUP":
                        displayedDeadline = markupDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "QA RESPONSE":
                        displayedDeadline = qaResponseDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "TRANSFER CONFIRMATION":
                        displayedDeadline = transferConfirmationDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        displayedDeadline = noResponseNeededConfirmationDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    case "COPY TO NUMBER 10":
                        displayedDeadline = copyToNumber10DeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 20;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertDisplayedDeadlineMatchesCalculatedDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline);
                break;
            case "HOME SECRETARY SIGN-OFF":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        displayedDeadline = dataInputDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "MARKUP":
                        displayedDeadline = markupDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 2;
                        break;
                    case "INITIAL DRAFT":
                        displayedDeadline = initialDraftDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 7;
                        break;
                    case "QA RESPONSE":
                        displayedDeadline = qaResponseDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 7;
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        displayedDeadline = privateOfficeApprovalDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 9;
                        break;
                    case "MINISTERIAL SIGN OFF":
                        displayedDeadline = ministerialSignOffDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 9;
                        break;
                    case "TRANSFER CONFIRMATION":
                        displayedDeadline = transferConfirmationDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        displayedDeadline = noResponseNeededConfirmationDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    case "DISPATCH":
                        displayedDeadline = dispatchDeadlineDate.waitUntilVisible().getText();
                        expectedNumberOfWorkdaysTillDeadline = 10;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertDisplayedDeadlineMatchesCalculatedDeadline(displayedDeadline, correspondenceReceivedDate, expectedNumberOfWorkdaysTillDeadline);
                break;
            default:
                pendingStep(deadlineDecidingFactor + " is not defined within " + getMethodName());
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
        allocatedTeam.shouldContainText(team);
    }

    public void assertPreviousCaseReferenceIsVisible(String prevCaseRef) {
        selectSummaryTab();
        previousCOMPCaseReference.shouldContainText(prevCaseRef);
    }

    public void assertAllocatedMPAMTeam(String stage) {
        if (!allocatedTeam.isVisible()) {
            selectSummaryTab();
        }
        String activeTeam = allocatedTeam.getText();
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

        if (businessArea.equalsIgnoreCase("Coronavirus (COVID-19)")){
            businessArea = "Coronavirus";
        }
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
        assertDisplayedDeadlineMatchesCalculatedDeadline(displayedDeadline, getTodaysDate(), sessionVariableCalled("numberOfDays"));
    }

    public void assertAppealInformationIsDisplayed() {
        String appealType = sessionVariableCalled("appealType");
        WebElementFacade appealTypeHeader = findBy("//h2[text()='Appeals']/following-sibling::table/caption");
        Assert.assertTrue(appealTypeHeader.getText().equalsIgnoreCase(appealType));
        if (appealType.equalsIgnoreCase("Internal Review")) {
            assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealOfficerDirectorate"), "Directorate");
            assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealOfficerName"), "Officer Name");
        }
        assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealComplete"), "Completed");
        assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealCompletionDate"), "Completion date");
        assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealOutcome"), "Outcome");
        assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealComplexity"), "Complex case");
        assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("appealDetails"), "Details");
    }

    public void assertNoActiveStageVisible() {
        selectSummaryTab();
        if (activeStage.isVisible()) {
            Assert.fail("Expected no active stage to be visible, but active stage " + activeStage.getText() + " is visible");
        }
    }

    public void assertNoAllocatedTeamVisible() {
        selectSummaryTab();
        if (allocatedTeam.isVisible()) {
            Assert.fail("Expected no allocated team to be visible, but allocated team " + allocatedTeam.getText() + " is visible");
        }
    }

    public void assertNoAllocatedUserVisible() {
        selectSummaryTab();
        if (allocatedUser.isVisible()) {
            Assert.fail("Expected no allocated user to be visible, but allocated user " + allocatedUser.getText() + " is visible");
        }
    }

    public void assertRecipientIsAddedToTOCase(String recipient) {
        assertSummaryContainsExpectedValueForGivenHeader(recipient, "Recipient");
    }

    public void assertSummaryContainsOnlyExpectedHeaders(List<String> expectedSummarySectionHeaders) {
        List<String> remainingHeadersToCheck = new LinkedList<>(expectedSummarySectionHeaders);
        List<WebElementFacade> allVisibleSummarySectionHeaderElements = findAll("//caption[text()='Summary']/ancestor::table//th");
        List<String> allVisibleSummarySectionHeaders = new ArrayList<>();
        for (WebElementFacade visibleSummarySectionHeaderElement : allVisibleSummarySectionHeaderElements) {
            allVisibleSummarySectionHeaders.add(visibleSummarySectionHeaderElement.getText());
        }
        for (String visibleSummarySectionHeader : allVisibleSummarySectionHeaders) {
            if (!remainingHeadersToCheck.contains(visibleSummarySectionHeader)) {
                Assert.fail("Summary was only expected to contain " + expectedSummarySectionHeaders.toString() + ", but also contained "
                        + visibleSummarySectionHeader);
            }
            remainingHeadersToCheck.remove(visibleSummarySectionHeader);
        }
        if (!(remainingHeadersToCheck.size() == 0)) {
            Assert.fail(
                    "Summary was expected to contain " + expectedSummarySectionHeaders.toString() + ", but did not contain " + remainingHeadersToCheck
                            .toString());
        }
    }
}