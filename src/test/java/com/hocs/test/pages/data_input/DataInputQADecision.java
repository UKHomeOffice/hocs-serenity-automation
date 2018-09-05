package com.hocs.test.pages.data_input;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

class DataInputQADecision {

    @FindBy(css = "label[for='DataInputQADecision-ACCEPT']")
    private WebElementFacade acceptDataInputQaDecision;

    @FindBy(css = "label[for='DataInputQADecision-REJECT']")
    private WebElementFacade rejectDataInputQaDecision;

    public void acceptDataInputQa() {
        acceptDataInputQaDecision.click();
    }

    public void rejectDataInputQa() {
        rejectDataInputQaDecision.click();
    }

}
