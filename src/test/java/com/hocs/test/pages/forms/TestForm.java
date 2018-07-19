package com.hocs.test.pages.forms;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webelements.RadioButtonGroup;
import org.openqa.selenium.WebDriver;

public class TestForm extends Page {

    @Managed
    WebDriver driver;

    @FindBy(id = "date-day")
    private WebElementFacade dateDayField;

    @FindBy(id = "date-month")
    private WebElementFacade dateMonthField;

    @FindBy(id = "date-year")
    private WebElementFacade dateYearField;

    @FindBy(id = "checkboxContext-Has1")
    private WebElementFacade checkboxOne;

    @FindBy(id = "checkboxContext-Has2")
    private WebElementFacade checkboxTwo;

    @FindBy(id = "checkboxContext-Has3")
    private WebElementFacade checkboxThree;

    @FindBy(id = "text-area")
    private WebElementFacade textField;

    @FindBy(id = "case-type-isA")
    private RadioButtonGroup radioButtonA;

    @FindBy(id = "case-type-isB")
    private WebElementFacade radioButtonB;

    @FindBy(id = "case-type-isC")
    private WebElementFacade radioButtonC;

    @FindBy(id = "dropdown-test")
    private WebElementFacade testDropdown;

    public void enterDate(String day, String month, String year) {
        dateDayField.clear();
        dateDayField.sendKeys(day);
        dateMonthField.clear();
        dateMonthField.sendKeys(month);
        dateYearField.clear();
        dateYearField.sendKeys(year);
    }

    public void enterName(String name) {
        nameBox.clear();
        nameBox.sendKeys(name);
    }

    public void enterSurname(String surname) {
        surnameBox.clear();
        surnameBox.sendKeys(surname);
    }

    public void enterText() {
        textField.clear();
        textField.sendKeys(generateRandomString());
    }

    public void selectCheckbox() {
        checkboxOne.click();
        checkboxThree.click();
    }

    public void selectRadioButton() {
        radioButtonC.click();
    }

    public void selectTestDropdown(int index) {
        testDropdown.selectByIndex(index);
    }

}
