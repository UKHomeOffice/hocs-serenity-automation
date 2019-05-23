package com.hocs.test.pages.markup;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ReferToOGD extends Page {

    @FindBy(id = "OGDDept")
    public WebElementFacade OGDTitleTextBox;

    public void assertOGDTitleTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(OGDTitleTextBox), is(true));
    }

}
