package com.hocs.test.pages.mark_up;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Topics extends Page {

    @FindBy(id = "Topics")
    private WebElementFacade topicsTextField;

}
