package com.hocs.test.glue.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.comp.AccordionCOMP;
import io.cucumber.java.en.And;
import net.serenitybdd.core.pages.WebElementFacade;

public class COMPCaseDetailsAccordionStepDefs extends BasePage {

    AccordionCOMP accordionCOMP;

    @And("the {string} COMP accordion in case details should display the correct information for {string}")
    public void theCOMPAccordionInCaseDetailsShouldDisplayTheCorrectInformation(String accordion, String info) {
        accordionCOMP.openAccordion(accordion);
        accordionCOMP.getQuestionResponse(info);
        accordionCOMP.assertInputMatchesCaseDetailsResponse(info);
    }

    @And("the read-only Case Details accordion should contain all case information entered during the {string} stage")
    public void theReadOnlyCaseDetailsAccordionShouldContainAllCaseInformationEnteredDuringTheStage(String stageName) {
        openAccordionSection(stageName);
        assertAllRecordedCaseDataIsDisplayedInTheReadOnlyAccordionSection();
    }
}
