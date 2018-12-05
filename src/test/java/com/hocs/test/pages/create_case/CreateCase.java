package com.hocs.test.pages.create_case;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateCase extends Page {

    Page page;

    AddDocuments addDocuments;

    SuccessfulCaseCreation successfulCaseCreation;

    // Elements

    @FindBy(className = "govuk-radios")
    private WebElementFacade allRadioButtons;

    @FindBy(id = "")
    private WebElementFacade caseDetailsFreeTextField;

    @FindBy(id = "")
    private WebElementFacade createBulkCaseRadioButton;

    @FindBy(linkText = "Create single case")
    private WebElementFacade createSingleCaseLink;

    @FindBy(id = "")
    private WebElementFacade createSingleCaseRadioButton;

    @FindBy(css = "label[for='case-type-MIN']")
    private WebElementFacade dcuMinRadioButton;

    @FindBy(css = "label[for='case-type-TRO']")
    private WebElementFacade dcuTroRadioButton;

    @FindBy(css = "label[for='case-type-DTEN']")
    private WebElementFacade dcuDtenRadioButton;

    @FindBy(id = "")
    private WebElementFacade addDocumentsNoRadioButton;

    @FindBy(id = "")
    private WebElementFacade addDocumentsYesRadioButton;


    // Basic Methods

    public void assertNoOptionsAvailable() {
        assertThat(allRadioButtons.getText(),is("No options available"));
    }

    public void clickCreateSingleCaseLink() { createSingleCaseLink.click(); }

    public void clickAddDocumentsRadioButton() {
        addDocumentsYesRadioButton.click();
    }

    public void clickCreateBulkCaseRadioButton() {
        createBulkCaseRadioButton.click();
    }

    public void clickCreateSingleCaseRadioButton() {
        createSingleCaseRadioButton.click();
    }

    public void clickDcuMinRadioButton() {
        dcuMinRadioButton.click();
    }

    public void clickDcuTroRadioButton() { dcuTroRadioButton.click(); }

    public void clickDcuDtenRadioButton() {
        dcuDtenRadioButton.click();
    }

    public void clickNoDocumentsToAddButton() {
        addDocumentsNoRadioButton.click();
    }

    public void enterCaseDetailsFreeText() { caseDetailsFreeTextField.sendKeys(generateRandomString()); }


    // Multi Step Methods

    public void createDCUMinSingleCase() {
        clickCreateSingleCaseLink();
        clickDcuMinRadioButton();
        clickNextButton();
        addDocuments.uploadDocument();
        page.clickSubmitButton();
        successfulCaseCreation.clickSuccessfulCaseBackButton();
    }

    public void createDC10SingleCase() {
        clickCreateSingleCaseLink();
        clickDcuDtenRadioButton();
        clickNextButton();
        addDocuments.uploadDocument();
        page.clickSubmitButton();
        successfulCaseCreation.clickSuccessfulCaseBackButton();
    }

    public void createDCTROSingleCase() {
        clickCreateSingleCaseLink();
        clickDcuTroRadioButton();
        clickNextButton();
        addDocuments.uploadDocument();
        page.clickSubmitButton();
        successfulCaseCreation.clickSuccessfulCaseBackButton();
    }


    //Assertions

    public void assertPageTitle() {
        assertTitle("Create case");
    }

    public void radioButtonsAreDisplayed() {
        assertThat(isElementDisplayed(allRadioButtons), is(true));
    }

    public void radioButtonsNotDisplayed() {
        assertThat(isElementDisplayed(allRadioButtons), is(false));
    }

    public void someDCUMINCreateCaseMethod() {
    }

    public void someDCU10CreateCaseMethod() {
    }

    public void someDCUTROCreateCaseMethod() {
    }
}
