package com.hocs.test.pages.platform;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import config.User;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Dashboard extends BasePage {

    UnallocatedCaseView unallocatedCaseView;

    @FindBy(xpath = "//a[text()='Create Single Case']")
    public WebElementFacade createSingleCaseLink;

    @FindBy(xpath = "//a[text()='Create Bulk Cases']")
    public WebElementFacade createBulkCasesLink;

    @FindBy(xpath = "//a[text()='View Standard Lines']")
    public WebElementFacade viewStandardLinesLink;

    @FindBy(xpath = "//a[text()='Search']")
    public WebElementFacade searchLink;

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

    // COMP Teams

    @FindBy(xpath = "//span[contains(text(),'CCH Closed Cases')]")
    public WebElementFacade cchClosedCasesWorkstack;

    // WCS Teams

    @FindBy(xpath = "//span[text()='Initial Consideration Casework']")
    public WebElementFacade initialConsiderationCaseworkTeam;

    @FindBy(xpath = "//span[text()='WCS Casework Team 1']")
    public WebElementFacade wcsCaseworkTeam1;

    @FindBy(xpath = "//span[text()='WCS Casework Team 2']")
    public WebElementFacade wcsCaseworkTeam2;

    @FindBy(xpath = "//span[text()='WCS Casework Team 3']")
    public WebElementFacade wcsCaseworkTeam3;

    @FindBy(xpath = "//span[text()='WCS Casework Team 4']")
    public WebElementFacade wcsCaseworkTeam4;

    @FindBy(xpath = "//span[text()='WCS Eligibility Team']")
    public WebElementFacade wcsEligibilityTeam;

    @FindBy(xpath = "//span[text()='WCS Payment Team']")
    public WebElementFacade wcsPaymentTeam;

    @FindBy(xpath = "//span[text()='WCS QA Team']")
    public WebElementFacade wcsQATeam;

    @FindBy(xpath = "//span[text()='WCS Registration Team']")
    public WebElementFacade wcsRegistrationTeam;

    @FindBy(xpath = "//span[text()='WCS Tier 1 Team']")
    public WebElementFacade wcsTier1Team;

    @FindBy(xpath = "//span[text()='WCS Triage Team']")
    public WebElementFacade wcsTriageTeam;

    @FindBy(xpath = "//span[text()='WCS Closure Review Team']")
    public WebElementFacade wcsClosureReviewTeam;

    // Basic Methods

    public void enterCaseReferenceIntoSearchBar(String caseReference) {
        caseReferenceSearchBar.sendKeys(caseReference);
    }

    public void hitEnterCaseReferenceSearchBar() {
        caseReferenceSearchBar.sendKeys(Keys.ENTER);
    }

    public void selectSearchLinkFromMenuBar() {
        safeClickOn(searchLink);
    }

    public void selectCreateSingleCaseLinkFromMenuBar() {
        safeClickOn(createSingleCaseLink);
    }

    public void selectCreateBulkCasesLinkFromMenuBar() {
        safeClickOn(createBulkCasesLink);
    }

    public void selectViewStandardLinesLinkFromMenuBar() {
        safeClickOn(viewStandardLinesLink);
    }

    // Select Workstack Methods

    public void selectMyCases() {
        safeClickOn(myCases);
    }

    public void selectPerformanceProcessTeam() {
        safeClickOn(performanceProcessTeam);
    }

    public void selectTransferN10Team() {
        safeClickOn(transferN10Team);
    }

    public void selectCorrectMPAMTeamByStage(String stage) {
        WebElementFacade requiredTeam;
        if (stage.equalsIgnoreCase("CREATION")) {
            requiredTeam = MPAMCreationTeam;
        } else {
            if (stage.equalsIgnoreCase("PRIVATE OFFICE")) {
                stage = "PO";
            }
            requiredTeam =
                    find(By.xpath("//span[contains(text(), '" + stage + "') and contains(text(), '" + sessionVariableCalled(
                            "businessArea") + "') and contains(text(), '" + sessionVariableCalled("refType") + "')]"));
        }
        safeClickOn(requiredTeam);
    }

    public void selectInitialConsiderationCaseworkTeam() {
        waitForDashboard();
        clickOn(initialConsiderationCaseworkTeam);
    }

    public void selectWCSCaseworkTeam1() {
        waitForDashboard();
        clickOn(wcsCaseworkTeam1);
    }

    public void selectWCSCaseworkTeam2() {
        waitForDashboard();
        clickOn(wcsCaseworkTeam2);
    }

    public void selectWCSCaseworkTeam3() {
        waitForDashboard();
        clickOn(wcsCaseworkTeam3);
    }

    public void selectWCSCaseworkTeam4() {
        waitForDashboard();
        clickOn(wcsCaseworkTeam4);
    }

    public void selectWCSCaseworkTeam() {
        String caseWorkTeam = sessionVariableCalled("selectedCaseworkTeam");
        switch (caseWorkTeam) {
            case "Initial Consideration Casework":
                selectInitialConsiderationCaseworkTeam();
                break;
            case "WCS Casework Team 1":
                selectWCSCaseworkTeam1();
                break;
            case "WCS Casework Team 2":
                selectWCSCaseworkTeam2();
                break;
            case "WCS Casework Team 3":
                selectWCSCaseworkTeam3();
                break;
            case "WCS Casework Team 4":
                selectWCSCaseworkTeam4();
                break;
            default:
                pendingStep(caseWorkTeam + " is not defined within " + getMethodName());
        }
    }

    public void selectWCSEligibilityTeam() {
        waitForDashboard();
        clickOn(wcsEligibilityTeam);
    }

    public void selectWCSPaymentTeam() {
        waitForDashboard();
        clickOn(wcsPaymentTeam);
    }

    public void selectWCSQATeam() {
        waitForDashboard();
        clickOn(wcsQATeam);
    }

    public void selectWCSRegistrationTeam() {
        waitForDashboard();
        clickOn(wcsRegistrationTeam);
    }

    public void selectWCSTier1Team() {
        waitForDashboard();
        clickOn(wcsTier1Team);
    }

    public void selectWCSTriageTeam() {
        waitForDashboard();
        clickOn(wcsTriageTeam);
    }

    public void selectWCSClosureReviewTeam() {
        waitForDashboard();
        clickOn(wcsClosureReviewTeam);
    }

    public void selectMTSTeam() {
        safeClickOn(mtsTeamWorkstack);
    }

    public void selectWorkstackByTeamName(String teamName) {
        WebElementFacade workstack = findBy("//span[text()='" + teamName + "']");
        safeClickOn(workstack);
    }

    // Multi Step Methods

    public void getCurrentCase() {
        try {
            caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        } catch (NoSuchElementException e) {
            goToDashboard();
            caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        }
        String currentCase = sessionVariableCalled("caseReference").toString();
        try {
            caseReferenceSearchBar.sendKeys(currentCase);
            assertThat(caseReferenceSearchBar.getValue().equals(currentCase), is(true));
            caseReferenceSearchBar.sendKeys(Keys.RETURN);
        } catch (AssertionError a) {
            caseReferenceSearchBar.clear();
            caseReferenceSearchBar.sendKeys(currentCase);
            caseReferenceSearchBar.sendKeys(Keys.RETURN);
        }
        documentsTab.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
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

    public boolean checkTargetUserIsLoggedInUsingVisibleTeams(User targetUser) {
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
            case "COMP_USER":
                if (cchClosedCasesWorkstack.isVisible()) {
                    correctUser = true;
                }
                break;
            case "WCS_USER":
                if (wcsRegistrationTeam.isVisible()) {
                    correctUser = true;
                }
                break;
            default:
                pendingStep(targetUser + " is not defined within " + getMethodName());
        }
        return correctUser;
    }

    // Assertions

    public void assertAtDashboard() {
        waitFor(myCases);
        assertThat(myCases.isVisible(), is(true));
    }

    public void assertCaseReferenceIsRequiredErrorMessage() {
        caseReferenceIsRequiredErrorMessage.shouldContainText("Case reference is required");
    }

    public void assertCaseReferenceIsInvalidFormatErrorMessage() {
        assertThat(caseReferenceIsInvalidFormatErrorMessage.getText(), Is.is("Case reference is invalid format"));
    }

    public void assertNoActiveWorkflowsForCaseErrorMessage() {
        noActiveWorkflowsForCaseErrorMessage.shouldContainText("No active workflows for case");
    }
}
