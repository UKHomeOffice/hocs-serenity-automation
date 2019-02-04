package com.hocs.test.pages.workstacks;

import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.webelements.Checkbox;
import org.apache.maven.wagon.events.WagonEvent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static jnr.posix.util.MethodName.getMethodName;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yecht.Data.Str;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.junit.Assume.assumeNotNull;

public class Workstacks extends Page {


    Homepage homepage;

    WebDriver driver;


    @FindBy(xpath = "//span[@class='govuk-hint'][1]")
    public WebElementFacade totalNumberOfItems;

    @FindBy(linkText = "Allocate to me")
    public WebElementFacade allocateToMeButton;

    @FindBy(css = "[type='submit']")
    public WebElementFacade unallocateFromMeButton;

    @FindBy(id = "workstack-filter")
    public WebElementFacade selectWorkstackFilter;

    @FindBy(xpath = "//span[text()='DCU Ministerial']/preceding-sibling::span")
    public WebElementFacade dcuMINCardNumber;

    @FindBy(xpath = "//tbody[@class='govuk-table__body']")
    public WebElementFacade caseResultsTable;

    public static void assertTeamHasNoCases() {
    }

    public static void assignCaseToTeamMember() {
    }

    public static void assignMultipleTeamSpecificCasesToTeammate() {
    }

    public void clickWorkstackFilterField(){
        selectWorkstackFilter.click();
    }

    public void displayMINCardNumber(){
    }

    public void assignCaseToMyself() {
        homepage.firstStageFindMyCase();
    }

    public void clickAllocateToMeButton() {
        allocateToMeButton.click();
    }


    public void assertCasesAreFilteredByRef(String caseReference) {
        int totalNumberOfCases = getTotalOfCases();

        List<WebElementFacade> listOfReferences = findAll("//tbody[@class='govuk-table__body']/tr/td[2]/a[contains(text(), '" + caseReference + "')]");
        assertThat(listOfReferences.size(), is(totalNumberOfCases));
    }

    public void assertCasesAreFilteredByStage(String currentStage) {
        int totalNumberOfCases = getTotalOfCases();
        int dispatchCount = 0;
        int dataInputCount = 0;
        int qaResponseCount = 0;

        List<WebElementFacade> listOfReference = findAll("//tbody[@class='govuk-table__body']/tr/td[3]");
        for (WebElementFacade element : listOfReference) {
            String elementText = element.getText();


            switch (elementText.toUpperCase()) {
                case "DISPATCH" :
                    dispatchCount++;
                    break;
                case "DATA INPUT" :
                    dataInputCount++;
                    break;
                case "QA RESPONSE" :
                    qaResponseCount++;
                    break;
                default: System.out.println(elementText
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                    elementText = null;
                    assumeNotNull(elementText);
            }
        }

    }

    public int getTotalOfCases() {
        String tempNumberOfItems = totalNumberOfItems.getText().split(" ")[0];

        return Integer.parseInt(tempNumberOfItems);
    }


    public void clickCheckboxRelevantToCaseReference() {
        String caseReference =
                sessionVariableCalled("caseReference").toString();
        WebDriver webDriver = getDriver();

        webDriver.findElement(
                By.xpath("//a[contains(text(), '" + caseReference + "')]/../..//input")).click();

    }

    public void clickUnallocateCasesButton() {
        unallocateFromMeButton.click();
    }


    public void assertCaseReferenceIsVisible() {
        String caseReferenceNumber
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebElement thisReference = getDriver().findElement(By.linkText(caseReferenceNumber));
        System.out.println(thisReference);
//        element(thisReference).isDisplayed();
        assertThat(isElementDisplayed(thisReference), is(true));

    }

    public void assertCaseReferenceIsNotVisible() {
        sleep(1000);

        String caseReferenceNumber
                = sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebDriver driver = getDriver();
        WebElement element = null;

        try {
            element = driver.findElement(By.linkText(caseReferenceNumber));
        } catch (NoSuchElementException e) {
            // we expect this excpetion to be caught
        }

        assertThat(isElementDisplayed(element), is(false));
    }



    public static void assignTeamSpecificCasesToMe() {
    }

    public static void assertThatCaselessTeamNotVisible() {
    }

    public static void assertThatCasesAssignedToMe() {
    }

    public static void assertThatXCasesAreUnassigned() {
    }

    public static void assertThatXCasesAreAssignedToMe() {
    }

    public static void assertThatTeamHasXTotalCases() {
    }

    public static void assertThatTeamHasXUnassignedCases() {
    }

    public static void assertOverdueCases() {
    }

    public static void assertThatMyCaseIsPriority() {
    }

    public static void assertUnassignedCaseIsPriority() {
    }
}
