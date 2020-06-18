package com.hocs.test.glue.mpam;

import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.mpam.AccordionMPAM;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.Draft;
import com.hocs.test.pages.mpam.PrivateOffice;
import com.hocs.test.pages.mpam.Triage;
import com.hocs.test.pages.mpam.QA;
import com.hocs.test.pages.mpam.AwaitingDispatch;
import io.cucumber.java.en.And;


public class MPAMCaseDetailsAccordionStepDefs {

    Homepage homepage;

    AccordionMPAM accordionMPAM;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    AwaitingDispatch awaitingDispatch;

    PrivateOffice privateOffice;

    @And("the {string} accordion in case details should display the correct information for {string}")
    public void accordionInCaseDetailsDisplaysCorrectInformation(String accordion, String responseType) {
        switch (accordion.toUpperCase()) {
            case "CREATION":
                accordionMPAM.openCreationAccordion();
                accordionMPAM.getQuestionResponse(responseType);
                accordionMPAM.assertInputMatchesCaseDetailsResponse(responseType);
                break;
            case "TRIAGE":
                accordionMPAM.openTriageAccordion();
                accordionMPAM.getQuestionResponse(responseType);
                accordionMPAM.assertInputMatchesCaseDetailsResponse(responseType);
                break;
            case "DRAFT":
                accordionMPAM.openDraftAccordion();
                accordionMPAM.getQuestionResponse(responseType);
                accordionMPAM.assertInputMatchesCaseDetailsResponse(responseType);
        }
    }

}
