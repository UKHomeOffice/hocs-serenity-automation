package com.hocs.test.pages.data_input;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInputQADecision extends Page {

    @FindBy(css = "label[for='DataInputQADecision-ACCEPT']")
    private WebElementFacade acceptDataInputQaDecision;

    @FindBy(css = "label[for='DataInputQADecision-REJECT']")
    private WebElementFacade rejectDataInputQaDecision;

    public void acceptDataInputQa() {
        getCaseId();
        acceptDataInputQaDecision.click();
    }

    public void rejectDataInputQa() {
        getCaseId();
        rejectDataInputQaDecision.click();
    }

}
