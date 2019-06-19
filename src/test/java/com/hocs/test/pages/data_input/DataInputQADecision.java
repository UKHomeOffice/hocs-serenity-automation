package com.hocs.test.pages.data_input;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInputQADecision extends Page {

    @FindBy(css = "label[for='DataInputQADecision-ACCEPT']")
    private WebElementFacade acceptDataInputQaDecision;

    @FindBy(css = "label[for='DataInputQADecision-REJECT']")
    private WebElementFacade rejectDataInputQaDecision;

    public void acceptDataInputQa() {
        getCaseId();
        clickOn(acceptDataInputQaDecision);
    }

    public void rejectDataInputQa() {
        getCaseId();
        clickOn(rejectDataInputQaDecision);
    }
}
