package com.hocs.test.pages.homepage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage extends Page {

    @Managed
    WebDriver driver;

    SuccessfulCaseCreation successfulCaseCreation;

    @FindBy(xpath = "//a[text()='Create Single Case']")
    public WebElementFacade createSingleCase;

    @FindBy(linkText = "Correspondence System")
    public WebElementFacade home;

    @FindBy(xpath = "//span[text()='Performance and Process Team']")
    public WebElementFacade performanceProcessTeam;

    @FindBy(xpath = "//span[text()='Transfers & No10 Team']")
    public WebElementFacade transferN10Team;

    @FindBy(xpath = "//span[text()='Minister of State for Immigration']")
    public WebElementFacade ministerOfStateForImmigrationTeam;

    @FindBy(xpath = "//span[text()='Minister for Lords']")
    public WebElementFacade ministerForLordsTeam;

    @FindBy(xpath = "//span[text()='Animals in Science Regulation Unit']")
    public WebElementFacade animalsInScienceTeam;

    @FindBy(xpath = "//span[text()='Central Drafting Team']")
    public WebElementFacade centralDraftingTeam;

    @FindBy(linkText = "Create cases in bulk")
    private WebElementFacade createBulkCases;

    @FindBy(linkText = "View test form")
    private WebElementFacade testFormLink;

    @FindBy(className = "card__body")
    private WebElementFacade myWorkstacks;

    @FindBy(className = "govuk-table")
    private WebElementFacade workstackTable;

    @FindBy(xpath = "//span[text()='Cases']")
    public WebElementFacade myCases;


    // Basic Methods

    public void selectMyCases() {
        myCases.click();
    }

    public void goHome() {
        home.click();
    }

    public void selectPerformanceProcessTeam() {
        performanceProcessTeam.click();
    }

    public void selectTransfersN10Team() {
        transferN10Team.click();
    }

    public void selectImmigrationMinisterTeam() {
        ministerOfStateForImmigrationTeam.click();
    }

    public void selectMinisterForLordsTeam() {
        ministerForLordsTeam.click();
    }

    public void selectAnimalsInScienceTeam() {
        animalsInScienceTeam.click();
    }

    public void selectCentralDraftingTeam() {
        centralDraftingTeam.click();
    }


    // Multi Step Methods

    public void firstStageFindMyCase() {
        String thisCaseType =
                Serenity.sessionVariableCalled("caseType").toString();
        if (thisCaseType.equals("DCU MIN")) {
            selectPerformanceProcessTeam();
            successfulCaseCreation
                    .selectCaseReferenceNumberViaLinkTextAndStoreResultingElement();
        } else if (thisCaseType.equals("DCU TRO")) {
            selectPerformanceProcessTeam();
            successfulCaseCreation
                    .selectCaseReferenceNumberViaLinkTextAndStoreResultingElement();
        } else if (thisCaseType.equals("DCU N10")) {
            selectTransfersN10Team();
            successfulCaseCreation
                    .selectCaseReferenceNumberViaLinkTextAndStoreResultingElement();
        } else {
            System.out.println("The caseType is " + thisCaseType);
        }
    }

    public WebElement currentCase() {
        WebElement caseReferenceLink
                = Serenity.sessionVariableCalled("assertCase");
        return caseReferenceLink;
    }


    // Assertions

    public void assertCaseIsComplete() {
        WebElementFacade caseReference = (WebElementFacade) driver.findElement(
                By.xpath("//td[contains(text(), '" + sessionVariableCalled("caseId")
                        + "')]"));

        assertThat(isElementDisplayed(caseReference), is(false));
    }

    public void assertCaseStageInWorkstacks(String expectedStage, WebDriver driver) {
        String actualStage = driver.findElement(
                By.xpath("//td[contains(text(), '" + sessionVariableCalled("caseId")
                        + "')]/following-sibling::td[1]")).getText();
        System.out.println("Case is at " + actualStage + " stage");
        assertThat(actualStage.toUpperCase(), is(expectedStage.toUpperCase()));
    }

    public void assertWorkstackTableContainsCaseReference() {
        assertThat(getWorktackTableContents(),
                hasItem(containsString(sessionVariableCalled("caseReference"))));
    }

    public void assertCreateSingleCaseIsDisplayed() {
        assertThat(isElementDisplayed(createSingleCase), is(true));
    }

    public void clickCreateBulkCases() {
        createBulkCases.click();
    }

    public void clickCreateSingleCase() {
        createSingleCase.click();
    }

    public void clickMyWorkstack () {
        myWorkstacks.click();
    }

    public void clickTestFormLink() {
        testFormLink.click();
    }

    private List<Map<Object, String>> getWorktackTableContents() {
        return rowsFrom(workstackTable);
    }

    public void assertPageTitle() {
        assertTitle("Main");
    }

}
