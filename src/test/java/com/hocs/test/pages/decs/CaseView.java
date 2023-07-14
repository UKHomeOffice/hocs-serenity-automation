package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class CaseView extends BasePage {

    List visibleAccordionStageHeader;

    List expectedAccordionStageHeader;

    @FindBy(linkText = "Allocate to me")
    public WebElementFacade allocateToMeLink;

    @FindBy(css = "[value = 'Allocate']")
    public WebElementFacade allocateButton;

    @FindBy(id = "user-id")
    public WebElementFacade allocateDropdown;

    @FindBy(xpath = "//h2[contains(@class,'govuk-heading')][text()='Case Details']")
    public WebElementFacade csCaseDetailsAccordion;

    @FindBy(xpath = "//h2[contains(@class,'section-heading')]/button[text()='Case Details']")
    public WebElementFacade wcsCaseDetailsAccordion;

    @FindBy(xpath = "//div[@class='govuk-tabs']")
    public WebElementFacade tabs;

    @FindBy(xpath = "//button[@class='govuk-accordion__show-all']")
    public WebElementFacade showAllAccordionSectionsButton;

    @FindBy(xpath = "//th[text()='Primary topic']/following-sibling::td")
    public WebElementFacade accordion;

    // Basic methods

    public void clickAllocateToMeLink() {
        waitABit(1000);
        safeClickOn(allocateToMeLink);
        allocateToMeLink.waitUntilNotVisible();
        tabs.waitUntilVisible();
    }

    public boolean caseCanBeAllocated() {
        return allocateToMeLink.isCurrentlyVisible();
    }

    public List<String> getValuesFromOpenCaseDetailsAccordionSectionForGivenKey(String heading) {
        List<WebElementFacade> valuesForMatchingHeadings = findAll("//Strong[contains(text(),'" + heading + "')]/parent::div");
        List<String> valuesText = new ArrayList<>();
        for (WebElementFacade value : valuesForMatchingHeadings) {
            if (value.isCurrentlyVisible()) {
                String text = value.getText();
                text = text.split(":")[1];
                text = text.trim();
                valuesText.add(text);
            }
        }
        return valuesText;
    }

    //assertions

    public void assertCaseCannotBeAllocated() {
        assertThat(caseCanBeAllocated(), is(false));
    }

    public void allocateToUserByVisibleText(String allocationUser) {
        selectSpecificOptionFromDropdownWithHeading(allocationUser, "Allocate to a team member");
        safeClickOn(allocateButton);
    }

    public void waitForCaseToLoad() {
        tabs.withTimeoutOf(Duration.ofSeconds(120)).waitUntilVisible();
    }

    public boolean currentCaseIsLoaded() {
        return specificCaseIsLoaded(getCurrentCaseReference());
    }

    public boolean specificCaseIsLoaded(String caseRef) {
        if (!tabs.isCurrentlyVisible()) {
            return false;
        }
        if (header1.isCurrentlyVisible()) {
            try {
                if (header1.getText().equals(caseRef)) {
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
                return false;
            }
        }
        if (headerCaption1.isCurrentlyVisible()) {
            try {
                return headerCaption1.getText().equals(caseRef);
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
                return false;
            }
        }
        return false;
    }

    public void assertExpectedValueIsVisibleInOpenCaseDetailsAccordionForGivenKey(String expectedAccordionValue, String accordionKey) {
        List<String> visibleDisplayValues = getValuesFromOpenCaseDetailsAccordionSectionForGivenKey(accordionKey);
        boolean expectedValueIsDisplayed = false;
        for (String visibleDisplayValue : visibleDisplayValues) {
            if (visibleDisplayValue.contains(expectedAccordionValue)) {
                expectedValueIsDisplayed = true;
                break;
            }
        }
        if (!expectedValueIsDisplayed) {
            Assert.fail("'" + accordionKey + ": " + expectedAccordionValue + "' is not visible in accordion");
        }
    }

    public void expandAllCaseDetailsAccordionSections() {
        if (showAllAccordionSectionsButton.isVisible()) {
            safeClickOn(showAllAccordionSectionsButton);
        } else {
            int n = 0;
            List<WebElementFacade> listOfShowButtons = findAll("//span[@class='govuk-accordion__section-toggle-text']");
            while (n < listOfShowButtons.size()) {
                safeClickOn(listOfShowButtons.get(n));
                n++;
            }
        }
    }

    public void assertExpectedValueIsVisibleInSummaryForGivenKey(String expectedAccordionValue, String accordionKey) {
        List<String> visibleDisplayValues = getValuesFromSummarySectionForGivenKey(accordionKey);
        boolean expectedValueIsDisplayed = false;
        for (String visibleDisplayValue : visibleDisplayValues) {
            if (visibleDisplayValue.contains(expectedAccordionValue)) {
                expectedValueIsDisplayed = true;
                break;
            }
        }
        if (!expectedValueIsDisplayed) {
            Assert.fail("'" + accordionKey + ": " + expectedAccordionValue + "' is not visible in summary");
        }
    }

    public List<String> getValuesFromSummarySectionForGivenKey(String heading) {
        List<WebElementFacade> valuesForMatchingHeadings;
        if(heading.equalsIgnoreCase("Complainant's nationality")){
            valuesForMatchingHeadings = findAll("//th[contains(text(), \"Complainant's nationality\")]/following-sibling::td");
        }else{
            valuesForMatchingHeadings = findAll("//th[contains(text(),'" + heading + "')]/following-sibling::td");
        }
        List<String> valuesText = new ArrayList<>();
        for (WebElementFacade value : valuesForMatchingHeadings) {
            if (value.isCurrentlyVisible()) {
                String text = value.getText();
            //    text = text.split(":")[1];
            //    text = text.trim();
                valuesText.add(text);
            }
        }
        return valuesText;
    }

    public void assertAccordionStageHeader() {
        String caseType = String.valueOf(getCurrentCaseType());
        if(caseType.equalsIgnoreCase("COMP") || caseType.equalsIgnoreCase("COMP2") || caseType.equalsIgnoreCase("COMP2DIRECT")){
            caseType = getTheSpecificCaseTypeForCOMP(caseType);

        }
        visibleAccordionStageHeader = getAccordionStageHeadersContent();
        expectedAccordionStageHeader = getExpectedStageHeaderContent(caseType);

        checkAccordionStageHeaderArePresent(expectedAccordionStageHeader);

    }

    private String getTheSpecificCaseTypeForCOMP(String caseType) {
        if(caseType.equalsIgnoreCase("COMP")){

            if(sessionVariableCalled("complaintType").toString().equalsIgnoreCase("Service")){
                caseType = "COMP Service";
            } else if (sessionVariableCalled("complaintType").toString().equalsIgnoreCase("Ex-Gratia")) {
                caseType = "COMP Ex-Gratia";
            }else if (sessionVariableCalled("complaintType").toString().equalsIgnoreCase("Minor misconduct")) {
                caseType = "COMP Minor misconduct";
            }
        }
        if(caseType.equalsIgnoreCase("COMP2") || caseType.equalsIgnoreCase("COMP2DIRECT")){
            if(sessionVariableCalled("complaintType").toString().equalsIgnoreCase("Service")){
                caseType = "COMP2 Service";
            } else if (sessionVariableCalled("complaintType").toString().equalsIgnoreCase("Ex-Gratia")) {
                caseType = "COMP2 Ex-Gratia";
            }else if (sessionVariableCalled("complaintType").toString().equalsIgnoreCase("Minor misconduct")) {
                caseType = "COMP2 Minor misconduct";
            }
        }
        return caseType;
    }

    private List<String> getExpectedStageHeaderContent(String caseType) {
        List<String> accordionStageHeader = new ArrayList<>();
        switch (caseType.toUpperCase()) {
            case "POGR":
            case "POGR2":
                accordionStageHeader.addAll(Arrays.asList("Data Input", "Investigation", "Draft", "QA", "Dispatch"));
                break;
            case "TO":
                accordionStageHeader.addAll(Arrays.asList("Data Input", "Triage", "Draft", "QA", "Dispatch"));
                break;
            case "IEDET":
                accordionStageHeader.addAll(Arrays.asList("IE Detention Registration", "IE Detention Triage", "IE Detention Outcome"));
                break;
            case "IEDETPSU":
                accordionStageHeader.addAll(Arrays.asList("IE Detention Registration", "IE Detention Triage", "PSU Registration", "PSU Triage", "PSU Outcome"));
                break;
            case "MIN":
                accordionStageHeader.addAll(Arrays.asList("Data Input", "Markup", "Initial Draft", "QA Response", "Private Office Approval",
                        "Ministerial Sign off", "Dispatch"));
                break;
            case "DTEN":
                accordionStageHeader.addAll(Arrays.asList("Data Input", "Markup", "Initial Draft", "QA Response", "Private Office Approval",
                        "Dispatch"));
                break;
            case "TRO":
                accordionStageHeader.addAll(Arrays.asList("Data Input", "Markup", "Initial Draft", "QA Response", "Dispatch"));
                break;
            case "MPAM":
                accordionStageHeader.addAll(Arrays.asList("Creation", "Triage", "Draft", "QA", "Private Office"));
                break;
            case "BF":
                accordionStageHeader.addAll(Arrays.asList("Border Force Registration", "Border Force QA", "Border Force Triage", "Border Force "
                        + "Draft", "Border Force Send"));
                break;
            case "BF2":
                accordionStageHeader.addAll(Arrays.asList("Border Force Registration (Stage 2)",  "Border Force Triage (Stage 2)", "Border "
                        + "Force Draft (Stage 2)", "Border Force QA (Stage 2)", "Border Force Send (Stage 2)"));
                break;
            case "BFPSU":
                accordionStageHeader.addAll(Arrays.asList("Border Force Registration", "PSU Registration", "PSU Triage", "PSU Outcome"));
                break;
            case "COMP SERVICE":
                accordionStageHeader.addAll(Arrays.asList("UKVI Registration",  "UKVI Service Triage", "UKVI Service Draft", "UKVI Service QA",
                        "UKVI Service Send"));
                break;
            case "COMP EX-GRATIA":
                accordionStageHeader.addAll(Arrays.asList("UKVI Registration",  "UKVI Ex-Gratia Triage", "UKVI Ex-Gratia Response Draft", "UKVI "
                                + "Ex-Gratia QA", "UKVI Ex-Gratia Send"));
                break;
            case "COMP MINOR MISCONDUCT":
                accordionStageHeader.addAll(Arrays.asList("UKVI Registration",  "UKVI Minor Misconduct Triage", "UKVI Minor Misconduct Response "
                        + "Draft", "UKVI Minor Misconduct QA", "UKVI Minor Misconduct Send"));
                break;
            case "COMPPSU":
                accordionStageHeader.addAll(Arrays.asList("UKVI Registration", "PSU Registration", "PSU Triage", "PSU Outcome"));
                break;
            case "COMP2 SERVICE":
                accordionStageHeader.addAll(Arrays.asList("UKVI Stage 2 Registration",  "UKVI Stage 2 Service Triage", "UKVI Stage 2 Service "
                        + "Draft", "UKVI Stage 2 Service QA", "UKVI Stage 2 Service Send"));
                break;
            case "COMP2 EX-GRATIA":
                accordionStageHeader.addAll(Arrays.asList("UKVI Stage 2 Registration",  "UKVI Stage 2 Ex-Gratia Triage", "UKVI Stage 2 "
                        + "Ex-Gratia Response Draft", "UKVI Stage 2 Ex-Gratia QA", "UKVI Stage 2 Ex-Gratia Send"));
                break;
            case "COMP2 MINOR MISCONDUCT":
                accordionStageHeader.addAll(Arrays.asList("UKVI Stage 2 Registration",  "UKVI Stage 2 Minor Misconduct Triage", "UKVI Stage 2 "
                        + "Minor Misconduct Response Draft", "UKVI Stage 2 Minor Misconduct QA", "UKVI Stage 2 Minor Misconduct Send"));
                break;
            case "COMP2PSU":
                accordionStageHeader.addAll(Arrays.asList("UKVI Stage 2 Registration", "PSU Registration", "PSU Triage", "PSU Outcome"));
                break;
            default:
                pendingStep(" Accordion stage is not defined within " + caseType);
        }
        return accordionStageHeader;
    }

    private void checkAccordionStageHeaderArePresent(List<String> columns) {
        if((columns.size() == visibleAccordionStageHeader.size()) && ((columns.equals(visibleAccordionStageHeader)))){
            System.out.println("Displayed accordion stage header match the expected accordion stage.\nExpected accordion header was: " + columns +
                    "\nDisplayed accordion header: " + visibleAccordionStageHeader);

            }else {
               Assert.fail(columns + " stage header is not visible in accordion");
            }
    }

    private List<String> getAccordionStageHeadersContent() {

        List<WebElement> tableHeaders = getDriver().findElements(By.xpath("//span[@class='govuk-accordion__section-heading-text-focus']"));
        List<String> accordionHeadersContent = new ArrayList<>();
        for (WebElement tableHeader : tableHeaders) {
            accordionHeadersContent.add(tableHeader.getText());
        }
        return accordionHeadersContent;
    }


}