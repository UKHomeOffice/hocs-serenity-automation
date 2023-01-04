package com.hocs.test.glue.security;

import com.hocs.test.security.zapConfig.BaseSecurity;
import config.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.zaproxy.clientapi.core.ClientApiException;

public class SecurityStepDef extends BaseSecurity {

    User targetUser;

    private String caseref;

    private String file = getFilePath();


    @Given("I am on the Correspondence System Login Page for security testing")
    public void iAmOnTheCorrespondenceSystemLoginPageForSecurityTesting() {
        getDriver().get(BASE_URL);
    }

    @Before(value = "@security", order = 1)
    public void setUp() {
        getDriver().manage().window().maximize();
    }

    @When("I enter the login credentials for user {string} and click the login button for security testing")
    public void iEnterTheLoginCredentialsForUserAndClickTheLoginButtonForSecurityTesting(String user) {
        targetUser = User.valueOf(user);
        findById("username").sendKeys(targetUser.getUsername());
        findById("password").sendKeys(targetUser.getPassword());
        findByName("login").click();
        setCurrentUser(targetUser);
    }

    @And("I wait for Passive Scan to complete")
    public void iWaitForPassiveScanToComplete() throws ClientApiException {
        waitForPassiveScanToComplete();
        checkRiskCount(BASE_URL);
    }

    @After(value = "@security", order = 1)
    public void tidyUp() throws ClientApiException, IOException {
        waitForPassiveScanToComplete();
        checkRiskCount(BASE_URL);
        getDriver().quit();
        byte[] bytes = clientApi.core.htmlreport();
        String str = new String(bytes, StandardCharsets.UTF_8);
        File newTextFile = new File(securityTestReportPath);
        try (FileWriter fw = new FileWriter(newTextFile)) {
            fw.write(str);
        }

    }

    @And("I create a {string} case for security testing")
    public void iCreateACaseForSecurityTesting(String arg0) {
        // Registration
        findBylinktext("Create Single Case").click();
        
        findById("case-type-1").click();  // COMP
        next();
        next();
        setCaseRef();
        findById("submit").click();
        
        allocateToMe();
        findBylinktext("Add a correspondent").click();
        
        select(findById("type")).selectByValue("COMPLAINANT");
        findById("fullname").sendKeys("security_test_complainant");
        next();
        next();
        next();
        
        findById("CompType-0").click();  // Service radio button
        next();
        findById("Channel-2").click();
        findById("Severity-2").click();
        next();
        findById("accordion-default-heading-0").click();
        findById("CatDelay_Delay").click();
        select(findById("OwningCSU")).selectByVisibleText("UKVI");
        next();
        // Triage
        findAndAllocate();
        findById("CctTriageAccept-0").click();
        next();
        next();
        next();
        select(findById("Directorate")).selectByVisibleText("RASI");
        select(findById("BusAreaBasedOnDirectorate")).selectByVisibleText("Asylum support");
        select(findById("EnqReason")).selectByVisibleText("Accommodation");
        findById("LoaRequired-1").click();
        next();
        
        findById("CctTriageResult-1").click();
        next();
        // Draft
        findAndAllocate();
        findBylinktext("Add a document").click();
        select(findById("document_type")).selectByVisibleText("DRAFT");
        findById("add_document").sendKeys(file);
        next();
        findById("CctDraftResult-1").click();
        next();
        
        // QA
        findAndAllocate();
        findById("CctQaResult-0").click();
        next();
        // Service Send
        
        findById("case-reference").sendKeys(caseref);
        findById("case-reference").sendKeys(Keys.ENTER);

        select(findById("CctCaseOutcome")).selectByVisibleText("Upheld");
        select(findById("ResponseChannel")).selectByVisibleText("Email");
        next();
    }

    private void setCaseRef() {

        caseref = findById("case-reference").getAttribute("value");
        System.out.println("caseref:  " + caseref);
    }

    private void findAndAllocate() {
        
        findById("case-reference").sendKeys(caseref);
        findById("case-reference").sendKeys(Keys.ENTER);
        
        allocateToMe();
    }


    private void allocateToMe() {
        
        findBylinktext("Allocate to me").click();
        
    }

    private void next() {
        findByClass("govuk-button").click();
        
    }

    private WebElement findByClass(String identifier) {
        
        return getDriver().findElement(By.className(identifier));
    }

    private WebElement findById(String identifier) {
        
        return getDriver().findElement(By.id(identifier));
    }

    private WebElement findByXpath(String identifier) {
        
        return getDriver().findElement(By.xpath(identifier));
    }

    private WebElement findByName(String identifier) {
        
        return getDriver().findElement(By.name(identifier));
    }

    private WebElement findBylinktext(String identifier) {
        
        return getDriver().findElement(By.linkText(identifier));
    }

    private Select select(WebElement element) {
        
        return new Select(element);
    }

    @NotNull
    private String getFilePath() {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator +
                "documents" + File.separator + "test.docx";
    }

    @And("I create a foi case for security testing")
    public void iCreateAFoiCaseForSecurityTesting() {
        findBylinktext("Create Single Case").click();
        
        findById("case-type-5").click();  // FOI
        next();
        findById("OriginalChannel-0").click();
        select(findById("Country")).selectByVisibleText("United Kingdom");
        findById("Fullname").sendKeys("Foi_sec_fullName");
        findById("Email").sendKeys("foi_sec_test@test.com");
        findByXpath("//label[contains(text(), 'Topic')]//following-sibling::div//input").sendKeys("Animal alternatives (3Rs)");
        findByXpath("//label[contains(text(), 'Topic')]//following-sibling::div//input").sendKeys(Keys.ENTER);

        findById("RequestQuestion").sendKeys("test request question");
        next();
        
        setCaseRef();
        findById("submit").click();
        

        next();
 // Allocation
        findById("RequestValidity-0").click();
        next();
        
        findBylinktext("Add a document").click();
        select(findById("document_type")).selectByVisibleText("Initial response");
        findById("add_document").sendKeys(file);
        next();
        String responseDate = getDatePlusMinusNDaysAgo(-10);
        System.out.println("resp " + responseDate);
        findById("AcknowledgementDate-day").sendKeys(dayOfDate(responseDate));
        findById("AcknowledgementDate-month").sendKeys(monthOfDate(responseDate));
        findById("AcknowledgementDate-year").sendKeys(yearOfDate(responseDate));
        next();
        select(findById("Directorate")).selectByVisibleText("Border Force");
        select(findById("AcceptanceTeam")).selectByVisibleText("FOI Border Force Operations Acceptance Team");
        select(findById("AccountManager")).selectByIndex(1);
        next();
        next();
        // Acceptance
        findAndAllocate();
        findById("AcceptCase-0").click();
        next();
        select(findById("DraftTeam")).selectByVisibleText("FOI Border Force Operations Draft Team");
        next();

        // Consider and Draft
        findAndAllocate();
        findById("ContributionsRequired-0").click();
        next();
        
        findBylinktext("Add a Contribution").click();
        select(findById("contributionBusinessUnit")).selectByVisibleText("Border Force");
        findById("contributionRequestDate-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("contributionRequestDate-month").sendKeys(monthOfDate(getTodaysDate()));
        findById("contributionRequestDate-year").sendKeys(yearOfDate(getTodaysDate()));

        findById("contributionDueDate-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("contributionDueDate-month").sendKeys(monthOfDate(getTodaysDate()));
        findById("contributionDueDate-year").sendKeys(yearOfDate(getTodaysDate()));
        findById("contributionRequestNote").sendKeys("test");
        next();
        
        findBylinktext("Edit").click();
        findById("contributionStatus-0").click();
        findById("contributionReceivedDate-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("contributionReceivedDate-month").sendKeys(monthOfDate(getTodaysDate()));
        findById("contributionReceivedDate-year").sendKeys(yearOfDate(getTodaysDate()));
        findById("contributionReceivedNote").sendKeys(" test note");
        next();
        next();
        addDocument("Draft response");
        next();

        // Approval
        
        findBylinktext("Add an Approval Request").click();
        select(findById("approvalRequestForBusinessUnit")).selectByVisibleText("Minister");
        findById("approvalRequestCreatedDate-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("approvalRequestCreatedDate-month").sendKeys(monthOfDate((getTodaysDate())));
        findById("approvalRequestCreatedDate-year").sendKeys(yearOfDate(getTodaysDate()));

        findById("approvalRequestDueDate-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("approvalRequestDueDate-month").sendKeys(monthOfDate((getTodaysDate())));
        findById("approvalRequestDueDate-year").sendKeys(yearOfDate(getTodaysDate()));

        next();

        findBylinktext("Edit").click();
        findById("approvalRequestStatus-0").click();
        findById("approvalRequestDecision-0").click();

        findById("approvalRequestResponseReceivedDate-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("approvalRequestResponseReceivedDate-month").sendKeys(monthOfDate((getTodaysDate())));
        findById("approvalRequestResponseReceivedDate-year").sendKeys(yearOfDate(getTodaysDate()));
        findById("approvalRequestResponseBy").sendKeys("respondents name");
        findById("approvalRequestResponseNote").sendKeys("approval note");
        next();
        next();

        // Dispatch
        select(findById("CaseType")).selectByVisibleText("FOI");
        select(findById("ResponseChannel")).selectByVisibleText("Email");
        findById("TransferOutcome-0").click();
        next();
        findById("Exemptions_Fee Threshold Invoked").click();
        next();
        next();
        findById("ShouldDispatch-0").click();
        findById("dateOfResponse-day").sendKeys(dayOfDate(getTodaysDate()));
        findById("dateOfResponse-month").sendKeys(monthOfDate((getTodaysDate())));
        findById("dateOfResponse-year").sendKeys(yearOfDate(getTodaysDate()));
        next();
        addDocument("Final responses");
        next();
    }

    private void addDocument(String documentType) {
        findBylinktext("Add a document").click();
        select(findById("document_type")).selectByVisibleText(documentType);
        findById("add_document").sendKeys(file);
        next();
    }

    public String dayOfDate(String date) {
        return date.split("/")[0];
    }

    public String monthOfDate(String date) {
        return date.split("/")[1];
    }

    public String yearOfDate(String date) {
        return date.split("/")[2];
    }

    @And("I create a DCU-Min case for security testing")
    public void iCreateADCUCaseForSecurityTesting() {
        findBylinktext("Create Single Case").click();
        
        findByXpath("//label[contains(text(), 'DCU Ministerial')]").click();
        next();
        next();
        
        setCaseRef();
        findById("submit").click();
        

        // Data Input
        allocateToMe();
        setTodaysDate("DateOfCorrespondence");
        findById("OriginalChannel-0").click();
        findById("CopyNumberTen-0").click();
        findById("HomeSecInterest-1").click();
        findById("HomeSecReply-1").click();
        next();
        findBylinktext("Add a correspondent").click();
        
        findById("isMember-1").click();
        next();

        select(findById("type")).selectByVisibleText("Constituent");
        findById("fullname").sendKeys("security_test_constituent");
        next();
        next();

        // Markup
        findAndAllocate();
        findById("MarkupDecision-0").click();
        next();
        findBylinktext("Add a topic").click();
        findByXpath("//label[contains(text(), 'Topic')]//following-sibling::div//input").sendKeys("Animal alternatives (3Rs)");
        findByXpath("//label[contains(text(), 'Topic')]//following-sibling::div//input").sendKeys(Keys.ENTER);
        next();
        next();
        next();

        // Initial Draft
        findAndAllocate();
        findById("InitialDraftDecision-0").click();
        next();
        findById("ResponseChannel-1").click();
        next();
        addDocument("DRAFT");
        next();
        findById("OfflineQA-1").click();
        next();

        // QA
        findAndAllocate();
        findById("QAResponseDecision-0").click();
        next();

        // Private Office Approval
        findAndAllocate();
        findById("PrivateOfficeDecision-0").click();
        next();

        // Ministerial Sign off
        findAndAllocate();
        findById("MinisterSignOffDecision-0").click();
        next();

        // Dispatch
        findAndAllocate();
        findById("DispatchDecision-0").click();
        next();

        // Copy To Number 10
        findAndAllocate();
        next();
    }

    private void setTodaysDate(String field) {
        findById(field + "-day").sendKeys(dayOfDate(getTodaysDate()));
        findById(field + "-month").sendKeys(monthOfDate((getTodaysDate())));
        findById(field + "-year").sendKeys(yearOfDate(getTodaysDate()));
    }
}
