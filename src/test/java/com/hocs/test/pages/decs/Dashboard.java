package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import config.User;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Dashboard extends BasePage {

    CaseView caseView;

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

    //MPAM Teams

    @FindBy(xpath = "//span[text()='MPAM Creation']")
    public WebElementFacade MPAMCreationTeam;

    @FindBy(xpath = "//span[text()='MTS Team']")
    public WebElementFacade mtsTeamWorkstack;

    // Complaints Teams

    @FindBy(xpath = "//span[contains(text(),'CCHReturns Closed Cases')]")
    public WebElementFacade cchClosedCasesWorkstack;

    @FindBy(xpath = "//span[contains(text(),'IE Detention')]")
    public WebElementFacade ieDetentionWorkstack;

    @FindBy(xpath = "//span[contains(text(),'Serious Misconduct')]")
    public WebElementFacade seriousMisconductWorkstack;

    // FOI Teams

    @FindBy(xpath = "//span[contains(text(),'FOI Creation')]")
    public WebElementFacade foiCreationWorkstack;

    // WCS Teams

    @FindBy(xpath = "//span[text()='WCS Registration Team']")
    public WebElementFacade wcsRegistrationTeam;

    // BF Teams

    @FindBy(xpath = "//span[text()='Border Force']")
    public WebElementFacade borderForceWorkstack;

    @FindBy(xpath = "//span[text()='Border Force (Stage 2)']")
    public WebElementFacade borderForceStage2Workstack;

    //TO Teams

    @FindBy(xpath = "//span[text()='Treat Official Creation']")
    public WebElementFacade treatOfficialCreationWorkstack;

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

    private void goToCSDashboard() {
        safeClickOn(csDashboardLink);
        waitForDashboard();
    }

    private void goToWCSDashboard() {
        safeClickOn(wcsDashboardLink);
        waitForDashboard();
    }

    public void goToMUIDashboard() {
        safeClickOn(muiDashboardLink);
    }

    public void goToDashboard() {
        switch (currentPlatform.toUpperCase()) {
            case "CS":
                goToCSDashboard();
                break;
            case "WCS":
                goToWCSDashboard();
                break;
            case "CS MANAGEMENT UI":
            case "WCS MANAGEMENT UI":
                goToMUIDashboard();
                break;
            default:
                pendingStep(currentPlatform + " is not defined within " + getMethodName());
        }
        waitForDashboard();
    }

    public boolean onDashboard() {
        return caseReferenceSearchBar.isCurrentlyVisible();
    }

    public void waitForDashboard() {
        caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
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

    public void selectMTSTeam() {
        safeClickOn(mtsTeamWorkstack);
    }

    public void selectIEDETTeam() {
        safeClickOn(ieDetentionWorkstack);
    }

    public void selectSMCTeam() {
        safeClickOn(seriousMisconductWorkstack);
    }

    public void selectBFTeam() {
        safeClickOn(borderForceWorkstack);
    }

    public void selectBF2Team() {
        safeClickOn(borderForceStage2Workstack);
    }

    public void selectFOICreationTeam() {
        safeClickOn(foiCreationWorkstack);
    }

    public void selectWorkstackByTeamName(String teamName) {
        WebElementFacade workstack = findBy("//span[text()='" + teamName + "']");
        safeClickOn(workstack);
    }

    public void selectARandomWorkstack() {
        List<WebElementFacade> visibleWorkstackCards = findAll("//h2[text()='Team Cases']/following-sibling::ul[contains(@class,'dashboard__teams')"
                + "]/li[contains(@class,'card')]/a");
        Random rand = new Random();
        WebElementFacade randomWorkstack = visibleWorkstackCards.get(rand.nextInt(visibleWorkstackCards.size()));
        safeClickOn(randomWorkstack);
    }

    // Multi Step Methods

    public void loadCase(String caseReference) {
        caseReferenceSearchBar.sendKeys(caseReference);
        try {
            assertThat(caseReferenceSearchBar.getValue().equals(caseReference), is(true));
        } catch (AssertionError a) {
            caseReferenceSearchBar.clear();
            caseReferenceSearchBar.sendKeys(caseReference);
        }
        hitEnterCaseReferenceSearchBar();
        caseView.waitForCaseToLoad();
    }

    public void getCurrentCase() {
        try {
            waitForDashboard();
        } catch (NoSuchElementException e) {
            goToDashboard();
        }
        loadCase(getCurrentCaseReference());
    }

    public void claimCurrentCase() {
        assertThat(caseView.currentCaseIsLoaded(), is(true));
        int attempts = 0;
        while (attempts < 12 && !caseView.caseCanBeAllocated()) {
            waitABit(5000);
            goToDashboard();
            getCurrentCase();
            assertThat(caseView.currentCaseIsLoaded(), is(true));
            attempts++;
        }
        assertThat(caseView.caseCanBeAllocated(), is(true));
        caseView.clickAllocateToMeLink();
    }

    public void getAndClaimCurrentCase() {
        getCurrentCase();
        claimCurrentCase();
    }

    public void ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser() {
        if (!caseView.currentCaseIsLoaded()) {
                goToDashboard();
                waitForDashboard();
                getCurrentCase();
            }
        if (caseView.caseCanBeAllocated()) {
            claimCurrentCase();
            caseView.waitForCaseToLoad();
            caseView.assertCaseCannotBeAllocated();
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

    public int getNumberOfUnallocatedCasesInWorkstackFromDashboardCard(String workstackName) {
        WebElementFacade caseCount = findBy("//span[text()=\"" + workstackName + "\"]/ancestor::li/div/span");
        String totalOfUnallocatedCases = caseCount.getText().split(" ")[0];
        return Integer.parseInt(totalOfUnallocatedCases);
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
            case "MPAM_USER":
                if (mtsTeamWorkstack.isVisible() && !performanceProcessTeam.isVisible()) {
                    correctUser = true;
                }
                break;
            case "COMP_USER":
                if (cchClosedCasesWorkstack.isVisible()) {
                    correctUser = true;
                }
                break;
            case "IEDET_USER":
                if (ieDetentionWorkstack.isVisible()) {
                    correctUser = true;
                }
                break;
            case "SMC_USER":
                if (seriousMisconductWorkstack.isVisible()) {
                    correctUser = true;
                }
                break;
            case "FOI_USER":
                if (foiCreationWorkstack.isVisible()) {
                    correctUser = true;
                }
                break;
            case "BF_USER":
                if (borderForceWorkstack.isVisible()) {
                    correctUser = true;
                }
                break;
            case "TO_USER":
                if (treatOfficialCreationWorkstack.isVisible()) {
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
