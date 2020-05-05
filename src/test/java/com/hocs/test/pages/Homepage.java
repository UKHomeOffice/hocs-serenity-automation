package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Homepage extends BasePage {

    Workstacks workstacks;

    UnassignedCaseView unassignedCaseView;

    @FindBy(xpath = "//a[text()='Create Single Case']")
    public WebElementFacade createSingleCase;

    @FindBy(xpath = "//a[text()='Create Bulk Cases']")
    public WebElementFacade createBulkCases;

    @FindBy(xpath = "//a[text()='Add Standard Line']")
    public WebElementFacade addStandardLine;

    @FindBy(xpath = "//a[text()='Search']")
    public WebElementFacade searchPage;

    @FindBy(xpath = "//input[@id='case-reference']")
    public WebElementFacade caseReferenceSearchBar;

    @FindBy(xpath = "//span[text()='Case reference is required']")
    public WebElementFacade caseReferenceIsRequiredErrorMessage;

    @FindBy(xpath = "//span[text()='Case reference is invalid format']")
    public WebElementFacade caseReferenceIsInvalidFormatErrorMessage;

    @FindBy(xpath = "//span[text()='No active workflows for case']")
    public WebElementFacade noActiveWorkflowsForCaseErrorMessage;

    @FindBy(xpath = "//span[text()='Cases']")
    public WebElementFacade myCases;

    // DCU Teams

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

    @FindBy(xpath = "//span[text()='Under Secretary of State for Crime, Safeguarding and Vulnerability']")
    public WebElementFacade underSecretaryCrimeSafeguardVulnerability;

    @FindBy(xpath = "//span[text()='Criminal & Financial Investigations']")
    public WebElementFacade criminalAndFinacialInvestigations;

    @FindBy(xpath = "//span[text()='Press Office']")
    public WebElementFacade pressOffice;

    @FindBy(xpath = "//span[text()='Finance']")
    public WebElementFacade financeTeam;

    //UKVI Teams

    @FindBy(xpath = "//span[text()='UKVI Creation']")
    public WebElementFacade UKVICreationTeam;

    // Basic Methods

    public void enterCaseReferenceIntoSearchBar(String caseReference) {
        typeInto(caseReferenceSearchBar, caseReference);
    }

    public void getValidCaseReferenceAndEnterIntoSearchBar() {
        safeClickOn(performanceProcessTeam);
        String getFirstCaseReferenceForSearch = find(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText();
        setSessionVariable("caseReference").to(getFirstCaseReferenceForSearch);
        safeClickOn(home);
        typeInto(caseReferenceSearchBar, getFirstCaseReferenceForSearch);
    }

    public void hitEnterCaseReferenceSearchBar() {
        caseReferenceSearchBar.sendKeys(Keys.ENTER);
    }

    public void assertCaseReferenceIsRequiredErrorMessage() {
        caseReferenceIsRequiredErrorMessage.shouldContainText("Case reference is required");
    }

    public void assertNoActiveWorkflowsForCaseErrorMessage() {
        noActiveWorkflowsForCaseErrorMessage.shouldContainText("No active workflows for case");
    }

    public void waitForPerformanceProcessTeam() {
        performanceProcessTeam.waitUntilEnabled();
    }

    public void selectMyCases() {
        safeClickOn(myCases);
    }

    public void selectPerformanceProcessTeam() {
        safeClickOn(performanceProcessTeam);
    }

    public void selectCentralDraftingTeam() {
        safeClickOn(centralDraftingTeam);
    }

    public void selectUKVITriageTeam() {
        WebElementFacade requiredTriageTeam = find(By.xpath("//span[text()='Triage: " + sessionVariableCalled(
                "businessArea") + " " + sessionVariableCalled("refType") +"']"));
        safeClickOn(requiredTriageTeam);
    }

    public void selectUKVIDraftingTeam() {
        WebElementFacade requiredTriageTeam = find(By.xpath("//span[text()='Drafting " + sessionVariableCalled(
                "businessArea") + " " + sessionVariableCalled("refType") +"']"));
        safeClickOn(requiredTriageTeam);
    }

    public void selectUKVIQATeam() {
        WebElementFacade requiredTriageTeam = find(By.xpath("//span[text()='QA: " + sessionVariableCalled(
                "businessArea") + " " + sessionVariableCalled("refType") +"']"));
        safeClickOn(requiredTriageTeam);
    }

    public void selectUKVIPrivateOfficeTeam() {
        WebElementFacade requiredTriageTeam = find(By.xpath("//span[text()='PO: " + sessionVariableCalled(
                "businessArea") + " " + sessionVariableCalled("ukviRefType") +"']"));
        safeClickOn(requiredTriageTeam);
    }

    // Assertions

    public void assertOnHomePage() {
        assertThat(myCases.isVisible(), is(true));
    }

    public void assertCaseIsCompleteViaSearch() {
        caseReferenceSearchBar.clear();
        String thisCaseId = sessionVariableCalled("caseReference").toString();
        typeInto(caseReferenceSearchBar, thisCaseId);
        caseReferenceSearchBar.sendKeys(Keys.RETURN);
        unassignedCaseView.assertCaseCannotBeAssigned();
    }

    public void getCurrentCase() {
        try {
            caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
            caseReferenceSearchBar.clear();
        } catch (NoSuchElementException e) {
            goHome();
            caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
            caseReferenceSearchBar.clear();
        }
        String currentCase = sessionVariableCalled("caseReference").toString();
        typeInto(caseReferenceSearchBar, currentCase);
        caseReferenceSearchBar.sendKeys(Keys.RETURN);
    }

    public void claimCurrentCase() {
        int attempts = 0;
        while (attempts < 3 && !unassignedCaseView.checkAllocateToMeLinkVisible()) {
            waitABit(2000);
            setCaseReferenceFromUnassignedCase();
            goHome();
            getCurrentCase();
            attempts++;
        }
        unassignedCaseView.clickAllocateToMeLink();
    }

    public void getAndClaimCurrentCase() {
        getCurrentCase();
        claimCurrentCase();
    }
}
