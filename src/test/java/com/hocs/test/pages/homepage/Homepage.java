package com.hocs.test.pages.homepage;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
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
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends Page {

    @Managed
    WebDriver driver;

    SuccessfulCaseCreation successfulCaseCreation;


    @FindBy(xpath = "//a[text()='Create Single Case']")
    public WebElementFacade createSingleCase;

    @FindBy(xpath = "//a[text()='Add Standard Line']")
    public WebElementFacade addStandardLine;

    @FindBy(xpath = "//a[text()='Search']")
    public WebElementFacade searchPage;

    @FindBy(xpath = "//h2[text()='My Cases']")
    public WebElementFacade homePageMyCasesAssertion;

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

    @FindBy(xpath = "//span[text()='Police Workforce and Professionalism Unit']")
    public WebElementFacade policeWorkforceProfessionalismUnit;

    @FindBy(xpath = "//span[text()='Minister of State for Policing and Fire Service']")
    public WebElementFacade ministerOfStateForPolicingAndFireServiceTeam;

    @FindBy(xpath = "//span[text()='Minister of State for Security and Economic Crime']")
    public WebElementFacade ministerOfStateForSecurityAndEconomicCrime;

    @FindBy(xpath = "//span[text()='Public Protection Unit']")
    public WebElementFacade publicProtectionUnit;

    @FindBy(xpath = "//span[text()='Counter Extremism Unit']")
    public WebElementFacade counterExtremismUnit;

    @FindBy(xpath = "//span[text()='Extremism Analysis Unit']")
    public WebElementFacade extremismAnalysisUnit;

    @FindBy(xpath = "//span[text()='Counter-Terrorism Legislation and Investigatory Powers Unit']")
    public WebElementFacade counterTerrorismLegislationInvestigatoryPowersUnit;

    @FindBy(xpath = "//span[text()='Chemical, Biological, Radiological, Nuclear & Explosives']")
    public WebElementFacade chemBioRadioNuclearExplosives;

    @FindBy(xpath = "//span[text()='Press Office']")
    public WebElementFacade pressOffice;

    @FindBy(xpath = "//span[text()='Finance']")
    public WebElementFacade financeTeam;

    @FindBy(xpath = "//a[text()='Create Bulk Cases']")
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

    public void waitForPerformanceProcessTeam() {
        performanceProcessTeam.waitUntilEnabled();
    }

    public void waitForMyCases() {
        myCases.waitUntilEnabled();
    }

    public void selectMyCases() {
        myCases.click();
    }

    public void selectPerformanceProcessTeam() {
        performanceProcessTeam.click();
    }

    public void selectPublicProtectionUnit() {
        publicProtectionUnit.click();
    }

    public void waitForProcessTeam() {
        performanceProcessTeam.waitUntilVisible();
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

    public void selectPoliceWorkforceProfessionalismTeam() { policeWorkforceProfessionalismUnit.click(); }

    public void selectMinisterOfStatePolicingFireTeam() { ministerOfStateForPolicingAndFireServiceTeam.click(); }

    public void selectMinisterOfStateForSecurityEconomicCrimeTeam() {ministerOfStateForSecurityAndEconomicCrime.click(); }

    public void selectExtremismAnalysisUnit() {extremismAnalysisUnit.click(); }

    public void selectCounterExtremismUnit() { counterExtremismUnit.click(); }

    public void selectCounterTerrorismLegislationInvestigatoryPowersUnit() {
        counterTerrorismLegislationInvestigatoryPowersUnit.click();
    }

    public void selectPressOffice() {
        pressOffice.click();
    }

    public void selectFinanceTeam() {
        financeTeam.click();
    }

    public void selectChemBioRadioNuclearExplosivesTeam() {
        chemBioRadioNuclearExplosives.click();
    }

//    public void selectAllocationUserByVisibleText(String allocationUser) {
//        allocateDropdown.selectByVisibleText(allocationUser);
//        allocateButton.click();
//
//    }

//    public void selectAllocationUserByIndex(int index) {
//        allocateDropdown.selectByIndex(index);
//    }

    // Multi Step Methods

    public void firstStageFindMyCase() {
        String thisCaseType =
                sessionVariableCalled("caseType").toString();
        switch (thisCaseType.toUpperCase()) {
            case "DCU MIN":
                selectPerformanceProcessTeam();
                successfulCaseCreation
                        .selectCaseReferenceNumberViaXpathStoreResultingElement();
                break;
            case "DCU TRO":
                selectPerformanceProcessTeam();
                successfulCaseCreation
                        .selectCaseReferenceNumberViaXpathStoreResultingElement();
                break;
            case "DCU N10":
                selectTransfersN10Team();
                successfulCaseCreation
                        .selectCaseReferenceNumberViaXpathStoreResultingElement();
                break;
            default:
                pendingStep(thisCaseType + " is not defined within " + getMethodName());
        }

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

    public void selectSearchPage() { searchPage.click();}

    public void clickMyWorkstack() {
        myWorkstacks.click();
    }

    public void clickTestFormLink() {
        testFormLink.click();
    }

    public void selectAddStandardLine() {
        addStandardLine.click();
    }

    private List<Map<Object, String>> getWorktackTableContents() {
        return rowsFrom(workstackTable);
    }

    public void assertPageTitle() {
        assertThat(homePageMyCasesAssertion.getText(), is("My Cases"));
    }

}
