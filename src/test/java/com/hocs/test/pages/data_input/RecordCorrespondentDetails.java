package com.hocs.test.pages.data_input;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class RecordCorrespondentDetails extends Page {

    @FindBy(id = "Member")
    private WebElementFacade memberDropdown;

    @FindBy(id = "CorrespondenceReference")
    private WebElementFacade correspondenceCaseReference;

    @FindBy(id = "Correspondent Type")
    private WebElementFacade correspondentTypeDropdown;

    @FindBy(id = "CTitle")
    private WebElementFacade correspondentTitleField;

    @FindBy(id = "CFirstName")
    private WebElementFacade correspondentFirstNameField;

    @FindBy(id = "CLastName")
    private WebElementFacade correspondentLastNameField;

    @FindBy(id = "CBuilding")
    private WebElementFacade correspondentBuildingField;

    @FindBy(id = "CStreet")
    private WebElementFacade correspondentStreetField;

    @FindBy(id = "CTownOrCity")
    private WebElementFacade correspondentTownOrCityField;

    @FindBy(id = "CPostcode")
    private WebElementFacade correspondentPostcodeField;

    @FindBy(id = "Country")
    private WebElementFacade correspondentCountryDropdown;

    @FindBy(id = "CPhone")
    private WebElementFacade correspondentTelephoneField;

    @FindBy(id = "CEmail")
    private WebElementFacade correspondentEmailField;

    @FindBy(css = "input[name='Correspondents']")
    private WebElementFacade selectPrimaryCorrespondentRadioButton;

    public void selectMemberFromDropdown(String member) {
        memberDropdown.selectByVisibleText(member);
    }

    public void selectFirstMemberFromDropdown() {
        memberDropdown.selectByIndex(1);
    }

}
