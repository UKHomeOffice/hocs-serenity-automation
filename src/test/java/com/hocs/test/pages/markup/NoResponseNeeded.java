package com.hocs.test.pages.markup;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NoResponseNeeded extends Page {

    @FindBy(id = "CaseNote_NRN")
    public WebElementFacade noResponseNeededTextField;

    public void assertNRNTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(noResponseNeededTextField), is(true));
    }
}
