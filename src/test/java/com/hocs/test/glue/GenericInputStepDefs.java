package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.forms.TestForm;
import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericInputStepDefs {

    @Managed
    WebDriver driver;

    DataInput dataInput;

    Page page;

    TestForm testForm;

    Homepage homepage;

    @Then("^\"([^\"]*)\" dropdown defaults to \"([^\"]*)\"$")
    public void dropdownDefaultsTo(String dropdown, String expectedText) {
        switch (dropdown.toUpperCase()) {
            case "":
                break;
            default:
                System.out.println(expectedText
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                expectedText = null;
                assumeNotNull(expectedText);
        }
    }

    @Given("^I click the \"([^\"]*)\" button$")
    public void iClickTheButton(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case "CONTINUE":
                page.clickContinueButton();
                break;
            case "NEXT":
                page.clickNextButton();
                break;
            default:
                System.out.println(buttonName
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                buttonName = null;
                assumeNotNull(buttonName);
        }
    }

    @When("^I click the \"([^\"]*)\" link")
    public void clickLink(String name) {
        switch (name.toUpperCase()) {
            case "UPDATE":
                page.clickUpdateLink();
                break;
            case "NEW":
                page.clickNewLink();
                break;
            case "DELETE":
                page.clickDeleteLink();
                break;
            case "VIEW":
                page.clickViewLink();
                break;
            default:
                System.out.println(name
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                name = null;
                assumeNotNull(name);
        }
    }

    @When("^I enter \"([^\"]*)\" in the \"([^\"]*)\" field")
    public void iEnterTextIntoTheNominatedField(String input, String element) {
        switch (element.toUpperCase()) {
            case "":
                break;
            default:
                System.out.println(element
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                element = null;
                assumeNotNull(element);
        }
    }

    @When("^I fill all mandatory fields on the \"([^\"]*)\" page with valid data")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "DATA INPUT":
                dataInput.fillAllMandatoryFields();
                break;
            default:
                System.out.println(pageName
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                pageName = null;
                assumeNotNull(pageName);
        }
    }


    @Then("^The page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        page.assertTitle(title);
    }

    @Then("^I should send the string of the first child to the console$")
    public void sendStageToConsole(){
        WebElement caseReferenceStage = driver.findElement(
                By.xpath("//a[text()='MIN/0120171/19']/../following-sibling::td[1]"));
        System.out.println(caseReferenceStage);
        String thisStage = caseReferenceStage.getText();
        System.out.println("The Stage is " + thisStage );
    }

    @Then("^I should see the \"([^\"]*)\" message$")
    public void iSeeTheMessage(String message) {
        switch (message.toUpperCase()) {
            case "DOCUMENT PENDING":
                break;
            case "DOCUMENT UPLOAD FAILED":
                break;
            case "NO DOCUMENTS":
                break;
            default:
                System.out.println(message
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                message = null;
                assumeNotNull(message);
        }
    }

    @Then("^\"([^\"]*)\" error message is displayed$")
    public void errorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "INVALID DATE":
                page.assertErrorMessageText("");
                break;
            case "CORRESPONDENCE RECEIVED":
                page.assertErrorMessageText("When was the correspondence received? is required");
                break;
            case "CORRESPONDENCE SENT":
                page.assertErrorMessageText("When was the correspondence sent? is required");
                break;
            default:
                System.out.println(errorMessage
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                errorMessage = null;
                assumeNotNull(errorMessage);
        }

    }

    @When("^I enter an invalid \"([^\"]*)\" date$")
    public void iEnterAnInvalidDate(String dateField) {
        switch (dateField.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED":
                dataInput.invalidCorrespondenceReceivedDate();
                break;
            case "CORRESPONDENCE SENT":
                dataInput.invalidCorrespondenceSentDate();
                break;
            default:
                System.out.println(dateField
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                dateField = null;
                assumeNotNull(dateField);
        }
    }

//    @And("^I am at the \"([^\"]*)\" stage$")
//    public void iAmAtTheStage(String stage) {
//        switch (stage.toUpperCase()) {
//            case "DATA INPUT":
//                homepage.clickMyWorkstack();
//                homepage.clickFirstDataInputCasework();
//                break;
//            case "DATA INPUT QA":
//                homepage.clickMyWorkstack();
//                try {
//                    homepage.clickFirstDataInputQaAllocate();
//                } catch (ElementShouldBeEnabledException e) {
//                    homepage.clickFirstDataInputQaCasework();
//                }
//                break;
//            case "MARKUP":
//                homepage.clickFirstMarkupAllocate();
//                break;
//            /*case "DISPATCH":
//                homepage.clickFirstDispatchAllocate();*/
//            default:
//                System.out.println(stage
//                        + " is not defined within " + getClass().getSimpleName()
//                        + " class, " + getMethodName() + " method");
//                stage = null;
//                assumeNotNull(stage);
//        }
//    }


    @When("^I set the date to \"([^\"]*)\"$")
    public void iSetTheDate(String date) {
        switch (date.toUpperCase()) {
            case "TODAY":
                page.getCurrentDay();
                page.getCurrentMonth();
                page.getCurrentYear();
                break;
            case "TOMORROW":
                break;
            case "YESTERDAY":
                break;
            default:
                fail("Please enter TODAY, TOMORROW or YESTERDAY");
        }
    }

    @When("I set a date of (\\d+) days time in the \"([^\"]*)\" field")
    public void iSetADateOfNDaysTimeInTheField(int days, String field) {
        String day = page.todayPlusNDaysGetDay(days);
        String month = page.todayPlusNDaysGetMonth(days);
        String year = page.todayPlusNDaysGetYear(days);
    }

    @When("^I set a deadline of -1 days$")
    public void setDeadlineToDaysDays(int days) {
        dataInput.setDateMinusOneDay();
    }

    @Then("^an error message is displayed$")
    public void anErrorMessageIsDisplayed() {
        page.errorMessageIsDisplayed();
    }

    @Then("^the file is downloaded$")
    public void theFileIsDownloaded() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I cannot exit the case$")
    public void iCannotClickToExitTheCase() {

    }

    @When("^I attempt to reject a case without reason$")
    public void iAttemptToRejectACaseWithoutReason() {
        page.clickRejectButton();
        while (page.isElementDisplayed(page.nextButton)) {
            page.clickNextButton();
        }
    }

    @When("^I \"([^\"]*)\" the case$")
    public void iActionTheCase(String action) {
        setSessionVariable("caseId").to(page.getCaseId());
        switch (action.toUpperCase()) {
            case "ACCEPT":
                page.clickAcceptButton();
                page.clickContinueButton();
                break;
            case "ALLOCATE":
                break;
            case "DISPATCH":
                break;
            case "REJECT":
                page.clickRejectButton();
                page.clickContinueButton();
                page.enterRejectionNotes();
                break;
            default:
                System.out.println(action
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                action = null;
                assumeNotNull(action);
        }

    }

    @But("^I do not enter (?:a|an) \"([^\"]*)\"$")
    public void iDoNotEnterA(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED DATE":
                dataInput.clearDateCorrespondenceReceived();
                break;
            case "CORRESPONDENCE SENT DATE":
                dataInput.clearDateCorrespondenceSent();
                break;
            case "OTHER GOVERNMENT DEPARTMENT":
                break;
            default:
                System.out.println(fieldName
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                fieldName = null;
                assumeNotNull(fieldName);
        }
    }

    @Then("^the case is completed$")
    public void theCaseIsCompleted() {
        homepage.assertCaseIsComplete();
    }

    @Then("^\"([^\"]*)\" link is displayed$")
    public void linkIsDisplayed(String linkText) {
        switch (linkText.toUpperCase()) {
            case "ADD A CORRESPONDENT":
                dataInput.addACorrespondentLinkIsDisplayed();
                break;
            default:
                System.out.println(linkText
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                linkText = null;
                assumeNotNull(linkText);
        }
    }

    @And("^I \"([^\"]*)\" the rejection note$")
    public void iTheRejectionNote(String rejection) {
        switch (rejection.toUpperCase()) {
            case "COMPLETE":
                page.enterRejectionNotes();
                page.clickFinishButton();
                break;
            case "DO NOT COMPLETE":
                page.clickFinishButton();
                break;
            default:
                System.out.println(rejection
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                rejection = null;
                assumeNotNull(rejection);
        }

    }

    @Given("^I add (\\d+) \"([^\"]*)\" to a case$")
    public void iAddToACase(int number, String addition) throws Throwable {
        for (int i = 0; i < number; i++) {
            switch (addition.toUpperCase()) {
                case "TOPICS":
                    break;
                default:
                    System.out.println(addition
                            + " is not defined within " + getClass().getSimpleName()
                            + " class, " + getMethodName() + " method");
                    addition = null;
                    assumeNotNull(addition);
            }
        }
    }
}

