package com.hocs.test.pages.give_me_a_case;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import net.serenitybdd.core.pages.WebElementFacade;

import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.markup.MarkupFull;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.CreateCase;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class fetch extends Page {

    CreateCase createCase;

    DataInput dataInput;

    MarkupFull markupFullFlow;

    DraftingTeamDecision draftingTeamDecision;

    QAResponse qa;

    PrivateOffice privateOffice;

    MinisterSignOff minister;

    Dispatch dispatch;

    Homepage homepage;

    Workstacks workstacks;

    MarkUpDecision markUpDecision;

    WebDriver driver;

    private void getFirstUnallocatedMINCaseDataInputStage() {
        WebElementFacade firstUnallocatedMINCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Data "
                        + "Input')]][following-sibling::td[2]"
                        + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedDTENCaseDataInputStage() {
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'Data Input')"
                + "]][following-sibling::td[2][not(contains(text(), '@'))]][descendant::a[contains(text(), 'DTEN')]]").get(0);
        firstUnallocatedDTENCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedMINCaseMarkupStage() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Markup')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedDTENCaseMarkupStage() {
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'Markup')"
                + "]][following-sibling::td[2][not(contains(text(), '@'))]][descendant::a[contains(text(), 'DTEN')]]").get(0);
        firstUnallocatedDTENCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedMINInitialDraftStage() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Initial Draft')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedDTENCaseInitialDraftStage() {
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'Initial Draft')"
                + "]][following-sibling::td[2][not(contains(text(), '@'))]][descendant::a[contains(text(), 'DTEN')]]").get(0);
        firstUnallocatedDTENCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedMINQAResponseStage() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'QA Response')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedDTENCaseQAResponseStage() {
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), 'QA Response')"
                + "]][following-sibling::td[2][not(contains(text(), '@'))]][descendant::a[contains(text(), 'DTEN')]]").get(0);
        firstUnallocatedDTENCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedMINPrivateOfficeStage() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Private Office Approval')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedDTENCasePrivateOfficeStage() {
        WebElementFacade firstUnallocatedDTENCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Private Office')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedDTENCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedMINMinisterialSignOffStage() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Ministerial Sign off')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
    }

    private void getFirstUnallocatedMINDispatchStage() {
        WebElementFacade firstUnallocatedMINCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Dispatch')]][following-sibling::td[2]"
                        + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        clickOn(workstacks.allocateToMeButton);
        getCaseId();
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
                // Only Cardiff University Kittens ATM, add random topics later
                getFirstUnallocatedInitialDraftCase(caseType);
                break;
            case "QA RESPONSE":
                getFirstUnallocatedQaResponseCase(caseType);
                break;
            case "PRIVATE OFFICE APPROVAL":
                // As in draft stage, only cardiff uni kittens as Current topic so
                // always come to this team for now, add random topics later
                // Might not need this as we are just interested in 'a case at a stage'
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

    public void getFirstUnallocatedDataInputCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance Process Team not found - Creating a new case");
                    clickOn(homepage.home);
                    createCase.createDCUMinSingleCase();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    clickOn(homepage.home);
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseDataInputStage();
                    try {
                        dataInput.dateCorrespondenceSentDayField.clear();
                    } catch (ElementShouldBeEnabledException | org.openqa.selenium.NoSuchElementException e) {
                        System.out.println("Element not found - Searching for fresh Data Input case");
                        clickOn(homepage.home);
                        getFirstUnallocatedDataInputCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Data Input case not found - Creating a new case");
                    clickOn(homepage.home);
                    createCase.createDCUMinSingleCase();
                    clickOn(homepage.home);
                    clickOn(homepage.performanceProcessTeam);
                    getFirstUnallocatedMINCaseDataInputStage();
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedMarkupCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.centralDraftingTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Central Drafting Team not available -  Searching for a Data Input Case");
                    clickOn(homepage.home);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseMarkupStage();
                    try {
                        clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Markup']").getText(),
                                is("Markup"));
                        System.out.println("Markup is active stage - Continuing test");
                        assertThat($("//span[text()='What sort of response is required?']").getText(), is("What sort of "
                                + "response is required?"));
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        System.out.println("Markup 'What sort of response is required? element not found - Searching for a"
                                + " fresh Markup case");
                        clickOn(homepage.home);
                        getFirstUnallocatedMarkupCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Markup case not found - Searching for a Data Input case");
                    clickOn(homepage.home);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    getFirstUnallocatedMarkupCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }


    public void getFirstUnallocatedInitialDraftCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for a Markup case");
                    clickOn(homepage.home);
                    getFirstUnallocatedMarkupCase(caseType);
                    markupFullFlow.moveCaseFromMarkupToInitialDraft();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINInitialDraftStage();
                    try {
                        clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Initial Draft']").getText(),
                                is("Initial Draft"));
                        System.out.println("Initial Draft is active stage - Continuing Test");
                        assertThat($("//span[text()='Can this correspondence be answered by your team?']").getText(),
                                is("Can this "
                                        + "correspondence be answered by your team?"));
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Initial Draft 'Can this correspondence be answered by your team?' element not "
                                + "found - Searching for a fresh Initial Draft case");
                        clickOn(homepage.home);
                        getFirstUnallocatedInitialDraftCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Initial Draft case not found - Searching for a Markup case");
                    clickOn(homepage.home);
                    getFirstUnallocatedMarkupCase(caseType);
                    markupFullFlow.moveCaseFromMarkupToInitialDraft();
                    getFirstUnallocatedInitialDraftCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedQaResponseCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation Unit not available - Searching for an Initial Draft "
                            + "case");
                    clickOn(homepage.home);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    draftingTeamDecision.moveCaseFromInitialDraftToQaResponse();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINQAResponseStage();
                    try {
                        clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='QA Response']").getText(),
                                is("QA Response"));
                        System.out.println("QA Response is active stage - Continuing Test");
                        assertThat($("//span[text()='Do you approve the response?']").getText(), is("Do you approve the "
                                + "response?"));
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("QA Response 'Do you approve the response?' element not found - Searching for a"
                                + " fresh QA Response case");
                        clickOn(homepage.home);
                        getFirstUnallocatedQaResponseCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("QA Response case not found - Searching for an Initial Draft case");
                    clickOn(homepage.home);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    draftingTeamDecision.moveCaseFromInitialDraftToQaResponse();
                    getFirstUnallocatedQaResponseCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined withing " + getMethodName());
        }
    }

    public void getFirstUnallocatedPrivateOfficeCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available - Searching for a QA Response case");
                    clickOn(homepage.home);
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveCaseFromQaResponseToPrivateOfficeApproval();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINPrivateOfficeStage();
                    try {
                        clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Private Office Approval']").getText(),
                                is("Private Office Approval"));
                        System.out.println("Private Office Approval is active stage - Continuing Test");
                        assertThat($("//span[text()='Do you approve the response?']").getText(),
                                is("Do you approve the response?"));
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Private Office Approval 'Do you approve the response?' element not found - "
                                + "Searching for a fresh Private Office Approval case");
                        clickOn(homepage.home);
                        getFirstUnallocatedPrivateOfficeCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    clickOn(homepage.home);
                    System.out.println("I couldn't find a Private Office Approval case so I am searching for a QA "
                            + "Response case");
                    System.out.println("Private Office Approval case not found - Searching for a QA Response case");
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveCaseFromQaResponseToPrivateOfficeApproval();
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedMinisterialSignOffCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available - Searching for a Private Office Approval "
                            + "case");
                    clickOn(homepage.home);
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOffice.moveCaseFromPrivateOfficeToMinisterSignOff();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINMinisterialSignOffStage();
                    try {
                        clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Ministerial Sign off']").getText(), is("Ministerial Sign off"));
                        System.out.println("Ministerial Sign Off is active stage - Continuing Test");
                        assertThat($("//span[text()='Do you approve the response?']").getText(),
                                is("Do you approve the response?"));
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Ministerial Sign Off case 'Do you approve the response?' element not found - "
                                + "Searching for a fresh Ministerial Sign Off case");
                        clickOn(homepage.home);
                        getFirstUnallocatedMinisterialSignOffCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    clickOn(homepage.home);
                    System.out.println("Ministerial Sign Off case not found - Searching for a Private Office case");
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOffice.moveCaseFromPrivateOfficeToMinisterSignOff();
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedDispatchCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    clickOn(homepage.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance Process Team not available - Searching for a Ministerial Sign Off case");
                    clickOn(homepage.home);
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                    minister.moveCaseFromMinisterToDispatch();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINDispatchStage();
                    try {
                        clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Dispatch']").getText(), is("Dispatch"));
                        System.out.println("Dispatch is active stage - Continuing Test");
                        assertThat($("//label[text()='How do you intend to respond?']").getText(),
                                is("How do you intend to respond?"));
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Dispatch 'How do you intend to respond?' element not found - Searching for a "
                                + "fresh Dispatch case");
                        clickOn(homepage.home);
                        getFirstUnallocatedDispatchCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    clickOn(homepage.home);
                    System.out.println("Dispatch case not found - Searching for a Minister Sign Off case");
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                    minister.moveCaseFromMinisterToDispatch();
                    getFirstUnallocatedDispatchCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }


}
