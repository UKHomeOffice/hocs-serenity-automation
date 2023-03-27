package com.hocs.test.pages.decs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class CaseView extends BasePage {

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

    // Basic methods

    public void clickAllocateToMeLink() {
        safeClickOn(allocateToMeLink);
        allocateToMeLink.waitUntilNotVisible();
        tabs.waitUntilVisible();
    }

    public boolean caseCanBeAllocated() {
        return allocateToMeLink.isCurrentlyVisible();
    }

    public List<String> getValuesFromOpenCaseDetailsAccordionSectionForGivenKey(String heading) {
        List<WebElementFacade> valuesForMatchingHeadings = findAll("//Strong[contains(text(),'" + heading + "')]/parent::span");
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
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
                return false;
            }
        }
        if (headerCaption1.isCurrentlyVisible()) {
            try {
                return headerCaption1.getText().equals(caseRef);
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
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
}
