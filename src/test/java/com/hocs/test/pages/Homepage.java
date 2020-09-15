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

    UnallocatedCaseView unallocatedCaseView;

    @FindBy(xpath = "//a[text()='Create Single Case']")
    public WebElementFacade createSingleCase;

    @FindBy(xpath = "//a[text()='Create Bulk Cases']")
    public WebElementFacade createBulkCases;

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

    @FindBy(xpath = "//span[text()='Direct Communications Unit Central Drafting Team']")
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

    //MPAM Teams

    @FindBy(xpath = "//span[text()='MPAM Creation']")
    public WebElementFacade MPAMCreationTeam;

    // Basic Methods

    public void enterCaseReferenceIntoSearchBar(String caseReference) {
        typeInto(caseReferenceSearchBar, caseReference);
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

    public void selectCorrectMPAMTeamByStage(String stage) {
        WebElementFacade requiredTeam;
        if (stage.toUpperCase().equals("CREATION")) {
            requiredTeam = MPAMCreationTeam;
        } else {
            if (stage.toUpperCase().equals("PRIVATE OFFICE")) {
                stage = "PO";
            }
            requiredTeam =
                    find(By.xpath("//span[contains(text(), '" + stage + "') and contains(text(), '" + sessionVariableCalled(
                            "businessArea") + "') and contains(text(), '" + sessionVariableCalled("refType") + "')]"));
        }
        safeClickOn(requiredTeam);
    }

    // Assertions

    public void assertOnHomePage() {
        assertThat(myCases.isVisible(), is(true));
    }

    public void assertCaseIsClosedViaLoadCase() {
        caseReferenceSearchBar.clear();
        String thisCaseId = sessionVariableCalled("caseReference").toString();
        typeInto(caseReferenceSearchBar, thisCaseId);
        caseReferenceSearchBar.sendKeys(Keys.RETURN);
        unallocatedCaseView.assertCaseCannotBeAssigned();
        System.out.println("The case is closed");
    }

    public void getCurrentCase() {
        try {
            caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
        } catch (NoSuchElementException e) {
            goHome();
            caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible();
        }
        caseReferenceSearchBar.clear();
        String currentCase = sessionVariableCalled("caseReference").toString();
        try {
            typeInto(caseReferenceSearchBar, currentCase);
            assertThat(caseReferenceSearchBar.getValue().equals(currentCase), is(true));
            caseReferenceSearchBar.sendKeys(Keys.RETURN);
        } catch (AssertionError a) {
            caseReferenceSearchBar.clear();
            typeInto(caseReferenceSearchBar, currentCase);
            caseReferenceSearchBar.sendKeys(Keys.RETURN);
        }
    }

    public void claimCurrentCase() {
        int attempts = 0;
        while (attempts < 3 && !unallocatedCaseView.checkAllocateToMeLinkVisible()) {
            waitABit(2000);
            setCaseReferenceFromUnassignedCase();
            goHome();
            getCurrentCase();
            attempts++;
        }
        unallocatedCaseView.clickAllocateToMeLink();
    }

    public void getAndClaimCurrentCase() {
        getCurrentCase();
        if (unallocatedCaseView.allocateToMeLink.isVisible()) {
            claimCurrentCase();
        }
    }
}
