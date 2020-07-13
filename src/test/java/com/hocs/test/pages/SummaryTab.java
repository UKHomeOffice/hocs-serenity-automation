package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import config.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Switch;
import org.hamcrest.core.Is;

public class SummaryTab extends BasePage {

    Workdays workdays;

    @FindBy(xpath = "//a[text()='Summary']")
    public WebElementFacade summaryTab;

    @FindBy(xpath = "//th[text()='Deadline']/following-sibling::td")
    private WebElementFacade deadline;

    @FindBy(xpath = "//th[text()='Primary topic']/following-sibling::td")
    private WebElementFacade primaryTopic;

    @FindBy(xpath = "//th[text()='Primary correspondent']/following-sibling::td")
    private WebElementFacade primaryCorrespondent;

    @FindBy(xpath = "//th[text()='How was the correspondence received?']/following-sibling::td")
    private WebElementFacade howCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='Should the response be copied to Number 10?']/following-sibling::td")
    private WebElementFacade shouldResponseCopyToN10;

    @FindBy(xpath = "//th[text()='When was the correspondence received?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='When was the correspondence sent?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceSent;

    @FindBy(xpath = "//h2[text()='Active stage']/following-sibling::table[1]/caption")
    private WebElementFacade activeStage;

    @FindBy(xpath = "//th[text()='Team']/following-sibling::td")
    private WebElementFacade currentTeam;

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

    @FindBy(xpath = "//th[text()='Deadline for contribution request']/following-sibling::td")
    private WebElementFacade contributionRequestDeadline;

    public void selectSummaryTab() {
        safeClickOn(summaryTab);
    }

    public String getPrimaryCorrespondent() {
        return primaryCorrespondent.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().getText();
    }

    public String getActiveStage() {
        return activeStage.getText();
    }

    public boolean checkCalculatedDeadline(String deadlineString, int expectedNumberOfDays) {
        int workingDaysAfterReceived = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String receivedDateString  = sessionVariableCalled("correspondenceReceivedDate");
        LocalDate receivedDate = LocalDate.parse(receivedDateString, formatter);
        LocalDate displayedDeadlineDate = LocalDate.parse(deadlineString, formatter);
        LocalDate newDate = receivedDate;
        assertThat(newDate.isBefore(displayedDeadlineDate), is(true));
        while (newDate.isBefore(displayedDeadlineDate) && workingDaysAfterReceived <= expectedNumberOfDays) {
            newDate = newDate.plusDays(1);
            if (workdays.isWorkday(newDate)) {
                workingDaysAfterReceived += 1;
            }
        }
        boolean areDatesEqual = newDate.equals(displayedDeadlineDate);
        boolean areDaysEqual = workingDaysAfterReceived == expectedNumberOfDays;
        return areDatesEqual && areDaysEqual;
    }

    public void assertDeadlineDateOfStage(String caseType, String stage) {
        int expectedNumberOfDays = 0;
        String deadlineString = null;
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        deadlineString = dataInputDeadlineDate.getText();
                        expectedNumberOfDays = 2;
                        break;
                    case "MARKUP":
                        deadlineString = markupDeadlineDate.getText();
                        expectedNumberOfDays = 2;
                        break;
                    case "INITIAL DRAFT":
                        deadlineString = initialDraftDeadlineDate.getText();
                        expectedNumberOfDays = 10;
                        break;
                    case "QA RESPONSE":
                        deadlineString = qaResponseDeadlineDate.getText();
                        expectedNumberOfDays = 10;
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        deadlineString = privateOfficeApprovalDeadlineDate.getText();
                        expectedNumberOfDays = 19;
                        break;
                    case "MINISTERIAL SIGN OFF":
                        deadlineString = ministerialSignOffDeadlineDate.getText();
                        expectedNumberOfDays = 19;
                        break;
                    case "TRANSFER CONFIRMATION":
                        deadlineString = transferConfirmationDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        deadlineString = noResponseNeededConfirmationDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "DISPATCH":
                        deadlineString = dispatchDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertThat(checkCalculatedDeadline(deadlineString, expectedNumberOfDays), is(true));
                break;
            case "DTEN":
                String inputDeadline = null;
                switch (stage.toUpperCase()) {
                    case "DISPATCH":
                        deadlineString = dispatchDeadlineDate.getText();
                        inputDeadline = sessionVariableCalled("dtenDispatchDeadline");
                        break;
                    case "INITIAL DRAFT":
                        deadlineString = initialDraftDeadlineDate.getText();
                        inputDeadline = sessionVariableCalled("dtenInitialDraftDeadline");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertThat(deadlineString.equals(inputDeadline), is(true));
                break;
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        deadlineString = dataInputDeadlineDate.getText();
                        expectedNumberOfDays = 2;
                        break;
                    case "MARKUP":
                        deadlineString = markupDeadlineDate.getText();
                        expectedNumberOfDays = 2;
                        break;
                    case "INITIAL DRAFT":
                        deadlineString = initialDraftDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "QA RESPONSE":
                        deadlineString = qaResponseDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "TRANSFER CONFIRMATION":
                        deadlineString = transferConfirmationDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        deadlineString = noResponseNeededConfirmationDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "DISPATCH":
                        deadlineString = dispatchDeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    case "COPY TO NUMBER 10":
                        deadlineString = copyToNumber10DeadlineDate.getText();
                        expectedNumberOfDays = 20;
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                assertThat(checkCalculatedDeadline(deadlineString, expectedNumberOfDays), is(true));
                break;
            case "MPAM":
                deadlineString = mpamDeadlineDate.getText();
                expectedNumberOfDays = 20;
                assertThat(checkCalculatedDeadline(deadlineString, expectedNumberOfDays), is(true));
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void assertCaseStage(String stage) {
        assertThat(getActiveStage().toUpperCase(), is(stage.toUpperCase()));
    }

    public void assertAllocatedUserIs(User user) {
        allocatedUser.shouldContainText(user.getUsername());
    }

    public void assertAllocatedUKVITeam(String stage) {
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

    public void assertContributionRequestDeadlineVisible() {
        String deadline = sessionVariableCalled("requestDeadline");
        assertThat(contributionRequestDeadline.getText().contains(deadline), is(true));
    }
}