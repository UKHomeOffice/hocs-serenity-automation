package com.hocs.test.pages.markup;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Deadline extends Page {

    @FindBy(id = "DraftingDeadline-day")
    private WebElementFacade draftingDeadlineDay;

    @FindBy(id = "DraftingDeadline-month")
    private WebElementFacade draftingDeadlineMonth;

    @FindBy(id = "DraftingDeadline-year")
    private WebElementFacade draftingDeadlineYear;

    @FindBy(id = "FinalDeadline-day")
    private WebElementFacade finalDeadlineDay;

    @FindBy(id = "FinalDeadline-month")
    private WebElementFacade finalDeadlineMonth;

    @FindBy(id = "FinalDeadline-year")
    private WebElementFacade finalDeadlineYear;

}
