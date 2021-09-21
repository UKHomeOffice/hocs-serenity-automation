package com.hocs.test.pages.decs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UnallocatedCaseView extends BasePage {

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

    // Basic methods

    public void clickAllocateToMeLink() {
        safeClickOn(allocateToMeLink);
    }

    public boolean caseCanBeAllocated() {
        return allocateToMeLink.isVisible();
    }

    public List<String> getValuesFromOpenCaseDetailsAccordionSectionForGivenHeading(String heading) {
        List<WebElementFacade> valuesForMatchingHeadings = findAll("//Strong[contains(text(),'" + heading + "')]/parent::span");
        List<String> valuesText = new ArrayList<>();
        for (WebElementFacade value : valuesForMatchingHeadings) {
            if (value.isCurrentlyVisible()) {
                valuesText.add(value.getText());
            }
        }
        return valuesText;
    }

    //assertions

    public void assertCaseCannotBeAssigned() {
        assertThat(caseCanBeAllocated(), is(false));
    }

    public void allocateToUserByVisibleText(String allocationUser) {
        safeClickOn(allocateDropdown);
        allocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(allocateButton);
    }

    public boolean caseDetailsAccordionIsVisible() {
        if (sessionVariableCalled("caseType").equals("WCS")) {
            return wcsCaseDetailsAccordion.isCurrentlyVisible();
        } else {
            return csCaseDetailsAccordion.isCurrentlyVisible();
        }
    }
}
