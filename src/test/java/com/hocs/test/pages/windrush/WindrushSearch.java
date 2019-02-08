package com.hocs.test.pages.windrush;

import com.hocs.test.pages.Page;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

public class WindrushSearch extends Page {

    @FindBy(css = "[title='Go to the Home Office Correspondence Service homepage']")
    public WebElementFacade windrushHome;

    @FindBy(id = "case-ref")
    public WebElementFacade windrushCaserefSearch;

    @FindBy(id = "first-name")
    public WebElementFacade windrushFirstNameSearch;

    @FindBy(id = "last-name")
    public WebElementFacade windrushLastNameSearch;

    @FindBy(id = "date-of-birth-day")
    public WebElementFacade windrushDoBDay;

    @FindBy(id = "date-of-birth-month")
    public WebElementFacade windrushDoBMonth;

    @FindBy(id = "date-of-birth-year")
    public WebElementFacade windrushDoBYear;

    @FindBy(css = "[value='Search']")
    public WebElementFacade windrushSearchButton;

    @FindBy(linkText = "Search")
    public WebElementFacade windrushSearchMenuBar;

    @FindBy(linkText = "New case")
    public WebElementFacade windrushNewCaseMenuBar;

    @FindBy(css = "[name='prefill']")
    public WebElementFacade windrushOpenNewCase;





}
