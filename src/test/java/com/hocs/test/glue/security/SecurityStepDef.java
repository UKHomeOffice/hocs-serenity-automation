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
        //Registration
        findBylinktext("Create Single Case").click();
        waitABit(500);
        findById("case-type-1").click(); // comp
        next();
        next();
        findById("submit").click();
        waitABit(500);
        caseref = getDriver().findElement(By.className("govuk-heading-l")).getText();
        allocateToMe();
        findBylinktext("Add a correspondent").click();
        waitABit(500);
        select(findById("type")).selectByValue("COMPLAINANT");
        findById("fullname").sendKeys("security_test_complainant");
        next();
        next();
        next();
        findById("CompType-0").click(); // Service radio button
        next();
        findById("Channel-2").click();
        findById("Severity-2").click();
        next();
        findById("accordion-default-heading-0").click();
        findById("CatDelay_Delay").click();
        select(findById("OwningCSU")).selectByVisibleText("UKVI");
        next();
        System.out.println(caseref);
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
        waitABit(500);
        findById("CctTriageResult-1").click();
        next();
        //Draft
        findAndAllocate();
        findBylinktext("Add a document").click();
        select(findById("document_type")).selectByVisibleText("DRAFT");
        findById("add_document").sendKeys(file);
        next();
        findById("CctDraftResult-1").click();
        next();
        waitABit(1000);
        // QA
        findAndAllocate();
        findById("CctQaResult-0").click();
        next();
        // Service Send
        waitABit(500);
        findById("case-reference").sendKeys(caseref);
        findById("case-reference").sendKeys(Keys.ENTER);

        select(findById("CctCaseOutcome")).selectByVisibleText("Upheld");
        select(findById("ResponseChannel")).selectByVisibleText("Email");
        next();
    }

    private void findAndAllocate() {
        findById("case-reference").sendKeys(caseref);
        findById("case-reference").sendKeys(Keys.ENTER);
        waitABit(500);
        allocateToMe();
    }


    private void allocateToMe() {
        waitABit(1000);
        findBylinktext("Allocate to me").click();
        waitABit(1000);
    }

    private void next() {
        findByClass("govuk-button").click();
        waitABit(1000);
    }

    private WebElement findByClass(String identifier) {
        waitABit(500);
        return getDriver().findElement(By.className(identifier));
    }

    private WebElement findById(String identifier) {
        waitABit(500);
        return getDriver().findElement(By.id(identifier));
    }

    private WebElement findByName(String identifier) {
        waitABit(500);
        return getDriver().findElement(By.name(identifier));
    }

    private WebElement findBylinktext(String identifier) {
        waitABit(500);
        return getDriver().findElement(By.linkText(identifier));
    }

    private Select select(WebElement element) {
        waitABit(500);
        return new Select(element);
    }

    @NotNull
    private String getFilePath() {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator +
                "documents" + File.separator + "test.docx";
    }

}

