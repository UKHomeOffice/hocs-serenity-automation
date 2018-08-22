package com.hocs.test.pages.data_entry;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInputQADecision {

    @FindBy(css = "[for='DataInputQADecision-ACCEPT']")
    private WebElementFacade acceptDataInputQaDecision;

    @FindBy(css = "[for='DataInputQADecision-REJECT']")
    private WebElementFacade rejectDataInputQaDecision;

    public void acceptDataInputQa() {
        acceptDataInputQaDecision.click();
    }

    public void rejectDataInputQa() {
        rejectDataInputQaDecision.click();
    }

}
