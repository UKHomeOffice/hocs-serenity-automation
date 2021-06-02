package com.hocs.test.pages.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.Workstacks;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.NoSuchElementException;

public class fetchExistingDCUCases extends BasePage {

    CreateCase createCase;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qa;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff minister;

    Dashboard dashboard;

    Workstacks workstacks;

    UnallocatedCaseView unallocatedCaseView;

    private void getFirstUnallocatedMINCaseDataInputStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Data "
                        + "Input')]][following-sibling::td[2]"
                        + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        safeClickOn(firstUnallocatedMINCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedTROCaseDataInputStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedTROCase = findAll("//td[following-sibling::td[1][contains(text(), 'Data Input')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'TRO')]]").get(0);
        safeClickOn(firstUnallocatedTROCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedDTENCaseDataInputStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'Data Input')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'DTEN')]]")
                .get(0);
        safeClickOn(firstUnallocatedDTENCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedMINCaseMarkupStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Markup')]][following-sibling::td[2]"
                + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        String caseRef = firstUnallocatedMINCase.getText().split("\\r?\\n")[1];
        goToDECSDashboard();
        dashboard.caseReferenceSearchBar.sendKeys(caseRef);
        dashboard.hitEnterCaseReferenceSearchBar();
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedTROCaseMarkupStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedTROCase = findAll("//td[following-sibling::td[1][contains(text(), 'Markup')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'TRO')]]").get(0);
        safeClickOn(firstUnallocatedTROCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedDTENCaseMarkupStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'Markup')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'DTEN')]]")
                .get(0);
        safeClickOn(firstUnallocatedDTENCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedMINCaseInitialDraftStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Initial Draft')]][following-sibling::td[2]"
                + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        safeClickOn(firstUnallocatedMINCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedTROCaseInitialDraftStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedTROCase = findAll("//td[following-sibling::td[1][contains(text(), 'Initial "
                + "Draft')]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'TRO')"
                + "]]").get(0);
        safeClickOn(firstUnallocatedTROCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedDTENCaseInitialDraftStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'Initial Draft')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'DTEN')]]")
                .get(0);
        safeClickOn(firstUnallocatedDTENCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedMINCaseQAResponseStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'QA Response')]][following-sibling::td[2]"
                + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        safeClickOn(firstUnallocatedMINCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedTROCaseQAResponse() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedTROCase = findAll("//td[following-sibling::td[1][contains(text(), 'QA Response')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'TRO')]]").get(0);
        safeClickOn(firstUnallocatedTROCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedDTENCaseQAResponseStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'QA Response')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'DTEN')]]")
                .get(0);
        safeClickOn(firstUnallocatedDTENCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedMINCasePrivateOfficeStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Private Office Approval')]][following-sibling::td[2]"
                + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        safeClickOn(firstUnallocatedMINCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedDTENCasePrivateOfficeStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Private Office')]][following-sibling::td[2]"
                + "[not(text())]][descendant::a[contains(text(), 'DTEN')]]").get(0);
        safeClickOn(firstUnallocatedDTENCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedMINCaseMinisterialSignOffStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Ministerial Sign off')]][following-sibling::td[2]"
                + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        safeClickOn(firstUnallocatedMINCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedMINCaseDispatchStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedMINCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Dispatch')]][following-sibling::td[2]"
                        + "[not(text())]][descendant::a[contains(text(), 'MIN')]]").get(0);
        safeClickOn(firstUnallocatedMINCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedTROCaseDispatchStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedTROCase = findAll("//td[following-sibling::td[1][contains(text(), 'Dispatch')"
                + "]][following-sibling::td[2][not(text())]][descendant::a[contains(text(), 'TRO')]]").get(0);
        safeClickOn(firstUnallocatedTROCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    private void getFirstUnallocatedDTENCaseDispatchStage() {
        workstacks.waitForWorkstackToLoad();
        WebElementFacade firstUnallocatedDTENCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Dispatch')]][following-sibling::td[2]"
                        + "[not(text())]][descendant::a[contains(text(), 'DTEN')]]").get(0);
        safeClickOn(firstUnallocatedDTENCase);
        safeClickOn(unallocatedCaseView.allocateToMeLink);
    }

    public void giveMeACase(String caseType, String stage) {
        setSessionVariable("caseType").to(caseType);
        setSessionVariable("stage").to(stage);
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                getFirstUnallocatedDataInputCase(caseType);
                break;
            case "MARKUP":
                getFirstUnallocatedMarkupCase(caseType);
                break;
            case "INITIAL DRAFT":
                getFirstUnallocatedInitialDraftCase(caseType);
                break;
            case "QA RESPONSE":
                getFirstUnallocatedQaResponseCase(caseType);
                break;
            case "PRIVATE OFFICE APPROVAL":
                getFirstUnallocatedPrivateOfficeCase(caseType);
                break;
            case "MINISTERIAL SIGN OFF":
                getFirstUnallocatedMinisterialSignOffCase(caseType);
                break;
            case "DISPATCH":
                getFirstUnallocatedDispatchCase(caseType);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    private void getFirstUnallocatedDataInputCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance and Process Team not found - Creating a new case");
                    safeClickOn(dashboard.dashboardLink);
                    createCase.createCaseOfType(caseType);
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    safeClickOn(dashboard.dashboardLink);
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseDataInputStage();
                    try {
                        dataInput.dateCorrespondenceSentDayField.clear();
                    } catch (ElementShouldBeEnabledException | org.openqa.selenium.NoSuchElementException e) {
                        System.out.println("Element not found - Searching for fresh Data Input case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDataInputCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Data Input case not found - Creating a new case");
                    safeClickOn(dashboard.dashboardLink);
                    createCase.createCaseOfType(caseType);
                    safeClickOn(dashboard.dashboardLink);
                    safeClickOn(dashboard.performanceProcessTeam);
                    getFirstUnallocatedMINCaseDataInputStage();
                }
                break;
            case "TRO":
                try {
                    safeClickOn(dashboard.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance and Process Team not found - Creating a new case");
                    safeClickOn(dashboard.dashboardLink);
                    createCase.createCaseOfType(caseType);
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    safeClickOn(dashboard.dashboardLink);
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedTROCaseDataInputStage();
                    try {
                        dataInput.dateCorrespondenceSentDayField.clear();
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Element not found - Searching for fresh Data Input case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDataInputCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Data Input case not found - Creating a new case");
                    safeClickOn(dashboard.dashboardLink);
                    createCase.createCaseOfType(caseType);
                    safeClickOn(dashboard.dashboardLink);
                    safeClickOn(dashboard.performanceProcessTeam);
                    getFirstUnallocatedTROCaseDataInputStage();
                }
                break;
            case "DTEN":
                try {
                    safeClickOn(dashboard.transferN10Team);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Transfers & N10 Team not found - Creating a new case");
                    safeClickOn(dashboard.dashboardLink);
                    createCase.createCaseOfType(caseType);
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    safeClickOn(dashboard.dashboardLink);
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedDTENCaseDataInputStage();
                    try {
                        dataInput.dtenDraftingDeadlineDayField.clear();
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Element not found - Searching for fresh Data Input case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDataInputCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Data Input case not found - Creating a new case");
                    safeClickOn(dashboard.dashboardLink);
                    createCase.createCaseOfType(caseType);
                    safeClickOn(dashboard.dashboardLink);
                    safeClickOn(dashboard.transferN10Team);
                    getFirstUnallocatedDTENCaseDataInputStage();
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    private void getFirstUnallocatedMarkupCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.centralDraftingTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Central Drafting Team not available -  Searching for a Data Input case");
                    goToDECSDashboard();
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseMarkupStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Markup']").shouldContainText("Markup");
                        $("//span[text()='What sort of response is required?']").shouldContainText("What sort of "
                                + "response is required?");
                        System.out.println("Markup is active stage - Continuing test");
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        System.out.println("Markup 'What sort of response is required?' element not found - Searching for a"
                                + " fresh Markup case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedMarkupCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Markup case not found - Searching for a Data Input case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    getFirstUnallocatedMarkupCase(caseType);
                }
                break;
            case "TRO":
                try {
                    safeClickOn(dashboard.centralDraftingTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Transfers & N10 Team not available - Searching for a Data Input case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedTROCaseMarkupStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Markup']").shouldContainText("Markup");
                        $("//span[text()='What sort of response is required?']").shouldContainText("What sort of "
                                + "response is required?");
                        System.out.println("Markup is active stage - Continuing test");
                    } catch (NoSuchElementException e) {
                        System.out.println("Markup case not found - Searching for a Data Input case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedMarkupCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Markup case not found - Searching for a Data Input case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    getFirstUnallocatedMarkupCase(caseType);
                }
                break;
            case "DTEN":
                try {
                    safeClickOn(dashboard.transferN10Team);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Central Drafting Team not available - Searching for a Data Input case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedDTENCaseMarkupStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Markup']").shouldContainText("Markup");
                        $("//span[text()='What sort of response is required?']").shouldContainText("What sort of "
                                + "response is required?");
                        System.out.println("Markup is active stage - Continuing test");
                    } catch (NoSuchElementException e) {
                        System.out.println("Markup 'What sort of response is required? element not found - Searching for a"
                                + " fresh Markup case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedMarkupCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Markup case not found - Searching for a Data Input case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    getFirstUnallocatedMarkupCase(caseType);
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    private void getFirstUnallocatedInitialDraftCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for a Markup case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMarkupCase(caseType);
                    markup.moveCaseFromMarkupToInitialDraft();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseInitialDraftStage();
                    try {
                        safeClickOn(workstacks.caseTimelineTab);
                        if (initialDraft.isElementDisplayed($("//h2[contains(text(), 'Rejection Note')]"))) {
                            safeClickOn(dashboard.dashboardLink);
                            getFirstUnallocatedInitialDraftCase(caseType);
                        } else {
                            safeClickOn(workstacks.caseSummaryTab);
                            $("//caption[text()='Initial Draft']").shouldContainText("Initial Draft");
                            $("//span[text()='Can this correspondence be answered by your team?']").shouldContainText("Can this "
                                            + "correspondence be answered by your team?");
                            System.out.println("Initial Draft is active stage - Continuing Test");
                        }
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Initial Draft 'Can this correspondence be answered by your team?' element not "
                                + "found - Searching for a fresh Initial Draft case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedInitialDraftCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Initial Draft case not found - Searching for a Markup case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMarkupCase(caseType);
                    markup.moveCaseFromMarkupToInitialDraft();
                    getFirstUnallocatedInitialDraftCase(caseType);
                }
                break;
            case "TRO":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for a Markup case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMarkupCase(caseType);
                    markup.moveCaseFromMarkupToInitialDraft();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedTROCaseInitialDraftStage();
                    try {
                        safeClickOn(workstacks.caseTimelineTab);
                        if (initialDraft.isElementDisplayed($("//h2[contains(text(), 'Rejection Note')]"))) {
                            safeClickOn(dashboard.dashboardLink);
                            getFirstUnallocatedInitialDraftCase(caseType);
                        } else {
                            safeClickOn(workstacks.caseSummaryTab);
                            $("//caption[text()='Initial Draft']").shouldContainText("Initial Draft");
                            $("//span[text()='Can this correspondence be answered by your team?']").shouldContainText("Can "
                                            + "this correspondence be answered by your team?");
                            System.out.println("Initial Draft is active stage - Continuing Test");
                        }
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Initial Draft 'Can this correspondence be answered by your team?' element not "
                                + "found - Searching for a fresh Initial Draft case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedInitialDraftCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Initial Draft case not found - Searching for a Markup case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMarkupCase(caseType);
                    markup.moveCaseFromMarkupToInitialDraft();
                    getFirstUnallocatedInitialDraftCase(caseType);
                }
                break;
            case "DTEN":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Central Drafting Team not available - Searching for a Markup case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMarkupCase(caseType);
                    markup.moveCaseFromMarkupToInitialDraft();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedDTENCaseInitialDraftStage();
                    try {
                        safeClickOn(workstacks.caseTimelineTab);
                        if (initialDraft.isElementDisplayed($("//h2[contains(text(), 'Rejection Note')]"))) {
                            safeClickOn(dashboard.dashboardLink);
                            getFirstUnallocatedInitialDraftCase(caseType);
                        } else {
                            safeClickOn(workstacks.caseSummaryTab);
                            $("//caption[text()='Initial Draft']").shouldContainText("Initial Draft");
                            $("//span[text()='Can this correspondence be answered by your team?']").shouldContainText("Can this "
                                            + "correspondence be answered by your team?");
                            System.out.println("Initial Draft is active stage - Continuing Test");
                        }
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Initial Draft 'Can this correspondence be answered by your team?' element not"
                                + " found - Searching for a fresh Initial Draft case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedInitialDraftCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Initial Draft case not found - Searching for a Markup case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMarkupCase(caseType);
                    markup.moveCaseFromMarkupToInitialDraft();
                    getFirstUnallocatedInitialDraftCase(caseType);
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    private void getFirstUnallocatedQaResponseCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for an Initial Draft "
                            + "case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromInitialDraftToQaResponse();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseQAResponseStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='QA Response']").shouldContainText("QA Response");
                        $("//span[text()='Do you approve the response?']").shouldContainText("Do you approve the "
                                + "response?");
                        System.out.println("QA Response is active stage - Continuing Test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("QA Response 'Do you approve the response?' element not found - Searching for a"
                                + " fresh QA Response case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedQaResponseCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("QA Response case not found - Searching for an Initial Draft case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromInitialDraftToQaResponse();
                    getFirstUnallocatedQaResponseCase(caseType);
                }
                break;
            case "TRO":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for an Initial Draft "
                            + "case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromInitialDraftToQaResponse();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedTROCaseQAResponse();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='QA Response']").shouldContainText("QA Response");
                        $("//span[text()='Do you approve the response?']").shouldContainText("Do you approve the "
                                + "response?");
                        System.out.println("QA Response is active stage - Continuing Test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("QA Response 'Do you approve the response?' element not found - Searching for a"
                                + " fresh QA Response case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedQaResponseCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Animals in Science Regulation Unit - Searching for an Initial Draft case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromInitialDraftToQaResponse();
                    getFirstUnallocatedQaResponseCase(caseType);
                }
                break;
            case "DTEN":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for an Initial Draft "
                            + "case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromInitialDraftToQaResponse();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedDTENCaseQAResponseStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='QA Response']").shouldContainText("QA Response");
                        $("//span[text()='Do you approve the response?']").shouldContainText("Do you approve the "
                                + "response?");
                        System.out.println("QA Response is active stage - Continuing Test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("QA Response 'Do you approve the response?' element not found = Searching for a"
                                + " fresh QA Response case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedQaResponseCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("QA Response case not found - Searching for an Initial Draft case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromInitialDraftToQaResponse();
                    getFirstUnallocatedQaResponseCase(caseType);
                }
                break;
            default:
                pendingStep(caseType + " is not defined withing " + getMethodName());
        }
    }

    private void getFirstUnallocatedPrivateOfficeCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available - Searching for a QA Response case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveCaseFromQaResponseToPrivateOfficeApproval();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCasePrivateOfficeStage();
                    try {
                        safeClickOn(workstacks.caseTimelineTab);
                        if (initialDraft.isElementDisplayed($("//h2[contains(text(), 'Rejection Note')]"))) {
                            safeClickOn(dashboard.dashboardLink);
                            getFirstUnallocatedPrivateOfficeCase(caseType);
                        } else {
                            safeClickOn(workstacks.caseSummaryTab);
                            $("//caption[text()='Private Office Approval']").shouldContainText("Private Office Approval");
                            $("//span[text()='Do you approve the response?']").shouldContainText("Do you approve the response?");
                            System.out.println("Private Office Approval is active stage - Continuing Test");
                        }
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Private Office Approval 'Do you approve the response?' element not found - "
                                + "Searching for a fresh Private Office Approval case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedPrivateOfficeCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    safeClickOn(dashboard.dashboardLink);
                    System.out.println("Private Office Approval case not found - Searching for a QA Response case");
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveCaseFromQaResponseToPrivateOfficeApproval();
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                }
                break;
            case "TRO":
                break;
            case "DTEN":
                try {
                    safeClickOn(dashboard.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available - Searching for an Initial Draft case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    initialDraft.moveCaseFromDraftToPrivateOffice();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedDTENCasePrivateOfficeStage();
                    try {
                        safeClickOn(workstacks.caseTimelineTab);
                        if (initialDraft.isElementDisplayed($("//h2[contains(text(), 'Rejection Note')]"))) {
                            safeClickOn(dashboard.dashboardLink);
                            getFirstUnallocatedPrivateOfficeCase(caseType);
                        } else {
                            safeClickOn(workstacks.caseSummaryTab);
                            $("//caption[text()='Private Office Approval']").shouldContainText("Private Office Approval");
                            $("//span[text()='Do you approve the response?']").shouldContainText("Do you approve the response?");
                            System.out.println("Private Office is active stage - Continuing Test");
                        }
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Private Office 'Do you approve the response?' element not found - Searching "
                                + "for a fresh Private Office case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDTENCasePrivateOfficeStage();
                    }
                } catch (IndexOutOfBoundsException e) {
                    safeClickOn(dashboard.dashboardLink);
                    System.out.println("Private Office case not found - Searching for an Initial Draft case");
                    getFirstUnallocatedInitialDraftCase(caseType);
                    initialDraft.moveCaseFromDraftToPrivateOffice();
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    private void getFirstUnallocatedMinisterialSignOffCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available - Searching for a Private Office Approval "
                            + "case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOfficeApproval.moveCaseFromPrivateOfficeToMinisterSignOff();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseMinisterialSignOffStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Ministerial Sign off']").shouldContainText("Ministerial Sign off");
                        $("//span[text()='Do you approve the response?']").shouldContainText("Do you approve the response?");
                        System.out.println("Ministerial Sign Off is active stage - Continuing Test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Ministerial Sign Off case 'Do you approve the response?' element not found - "
                                + "Searching for a fresh Ministerial Sign Off case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedMinisterialSignOffCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    safeClickOn(dashboard.dashboardLink);
                    System.out.println("Ministerial Sign Off case not found - Searching for a Private Office case");
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOfficeApproval.moveCaseFromPrivateOfficeToMinisterSignOff();
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                }
                break;
            case "TRO":
            case "DTEN":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    private void getFirstUnallocatedDispatchCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                try {
                    safeClickOn(dashboard.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance and Process Team not available - Searching for a Ministerial Sign Off "
                            + "case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                    minister.moveCaseFromMinisterToDispatch();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseDispatchStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Dispatch']").shouldContainText("Dispatch");
                        $("//label[text()='How do you intend to respond?']").shouldContainText("How do you intend to respond?");
                        System.out.println("Dispatch is active stage - Continuing Test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Dispatch 'How do you intend to respond?' element not found - Searching for a "
                                + "fresh Dispatch case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDispatchCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    safeClickOn(dashboard.dashboardLink);
                    System.out.println("Dispatch case not found - Searching for a MINISTERIAL SIGN OFF case");
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                    minister.moveCaseFromMinisterToDispatch();
                    getFirstUnallocatedDispatchCase(caseType);
                }
                break;
            case "TRO":
                try {
                    safeClickOn(dashboard.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance and Process Team not available - Searching for QA Response case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveTROCaseFromQAResponseToDispatch();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedTROCaseDispatchStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Dispatch']").shouldContainText("Dispatch");
                        $("//label[text()='How do you intend to respond?']").shouldContainText("How do you intend "
                                + "to respond?");
                        System.out.println("Dispatch is active stage - Continuing Test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Dispatch 'How do you intend to respond?' element not found - Searching for a "
                                + "fresh Dispatch case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDispatchCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    safeClickOn(dashboard.dashboardLink);
                    System.out.println("Dispatch case not found - Searching for QA Response case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveTROCaseFromQAResponseToDispatch();
                    getFirstUnallocatedDispatchCase(caseType);
                }
                break;
            case "DTEN":
                try {
                    safeClickOn(dashboard.transferN10Team);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Transfers & N10 Team not available - Searching for a Private Office case");
                    safeClickOn(dashboard.dashboardLink);
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOfficeApproval.moveDTENCaseFromPrivateOfficeToDispatch();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedDTENCaseDispatchStage();
                    try {
                        safeClickOn(workstacks.caseSummaryTab);
                        $("//caption[text()='Dispatch']").shouldContainText("Dispatch");
                        System.out.println("Dispatch is active stage - Continuing Test");
                        $("//span[text()='Are you able to dispatch this?']").shouldContainText("Are you able to dispatch this?");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Dispatch 'Are you able to dispatch this?' element not found - Searching for a "
                                + "fresh Dispatch case");
                        safeClickOn(dashboard.dashboardLink);
                        getFirstUnallocatedDispatchCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    safeClickOn(dashboard.dashboardLink);
                    System.out.println("Dispatch case not found - Searching for a Private Office case");
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOfficeApproval.moveDTENCaseFromPrivateOfficeToDispatch();
                    getFirstUnallocatedDispatchCase(caseType);
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }
}
