package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import config.User;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Dashboard extends BasePage {

    UnallocatedCaseView unallocatedCaseView;

    @FindBy(xpath = "//a[text()='Create Single Case']")
    public WebElementFacade createSingleCase;

    @FindBy(xpath = "//a[text()='Create Bulk Cases']")
    public WebElementFacade createBulkCases;

    @FindBy(xpath = "//a[text()='Search']")
    public WebElementFacade searchPage;

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

    @FindBy(xpath = "//a[text()='Documents']")
    public WebElementFacade documentsTab;

    @FindBy(xpath = "//span[text()='MTS Team']")
    public WebElementFacade mtsTeamWorkstack;

    //MPAM Teams

    @FindBy(xpath = "//span[text()='MPAM Creation']")
    public WebElementFacade MPAMCreationTeam;

    @FindBy(xpath = "//span[text()='Awaiting Transfer']")
    public WebElementFacade awaitingTransferTeamWorkstack;

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

    public void selectTransferN10Team() {
        safeClickOn(transferN10Team);
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

    public void selectMTSTeam() {
        safeClickOn(mtsTeamWorkstack);
    }

    // Assertions

    public void assertAtDashboard() {
        waitFor(myCases);
        assertThat(myCases.isVisible(), is(true));
    }

    public void assertCaseIsClosedViaLoadCase() {
        caseReferenceSearchBar.waitUntilVisible().clear();
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
            goToDashboard();
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
        waitFor(documentsTab);
    }

    public void claimCurrentCase() {
        int attempts = 0;
        while (attempts < 3 && !unallocatedCaseView.checkAllocateToMeLinkVisible()) {
            waitABit(2000);
            setCaseReferenceFromUnassignedCase();
            goToDashboard();
            getCurrentCase();
            attempts++;
        }
        unallocatedCaseView.clickAllocateToMeLink();
    }

    public void getAndClaimCurrentCase() {
        if (!documentsTab.isCurrentlyVisible()) {
            getCurrentCase();
        } else if (!currentCaseIsLoaded()) {
            getCurrentCase();
        }
        if (unallocatedCaseView.allocateToMeLink.isCurrentlyVisible()) {
            claimCurrentCase();
        }
    }

    public int getNumberOfCasesInWorkstackFromDashboardCard(String workstackName) {
        WebElementFacade caseCount;
        if (workstackName.equals("My Cases")) {
            caseCount = findBy("//h2[text()=\"" + workstackName + "\"]/following-sibling::ul[1]/li/a/span[1]");
        } else {
            caseCount = findBy("//span[text()=\"" + workstackName + "\"]/preceding-sibling::span");
        }
        return Integer.parseInt(caseCount.getText());
    }

    public boolean checkLoggedInAsCorrectUser(User targetUser) {
        boolean correctUser = false;
        caseReferenceSearchBar.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10));
        switch (targetUser.toString()) {
            case "DECS_USER":
                if (mtsTeamWorkstack.isVisible() && performanceProcessTeam.isVisible()) {
                    correctUser = true;
                }
                break;
            case "DCU_USER":
                if (!mtsTeamWorkstack.isVisible() && performanceProcessTeam.isVisible()) {
                    correctUser = true;
                }
                break;
            case "UKVI_USER":
                if (mtsTeamWorkstack.isVisible() && !performanceProcessTeam.isVisible()) {
                    correctUser = true;
                }
                break;
            default:
                pendingStep(targetUser + " is not defined within " + getMethodName());
        }
        return correctUser;
    }
}
