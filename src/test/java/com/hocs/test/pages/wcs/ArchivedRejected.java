package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ArchivedRejected extends BasePage {

    @FindBy(css = "label[for*='Outcome-SendToRegistration']")
    public WebElementFacade restoreToRegistrationRadioButton;

    @FindBy(css = "label[for*='Outcome-SendToEligibility']")
    public WebElementFacade restoreToEligibilityRadioButton;

    @FindBy(css = "label[for*='Outcome-CloseClaim']")
    public WebElementFacade closeClaimRadioButton;

    @FindBy(xpath = "//a[@href='#ArchIROutcome-error']")
    public WebElementFacade archiveIdentityRejectedIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ArchEROutcome-error']")
    public WebElementFacade archiveEligibilityRejectedIsRequiredErrorMessage;

    public void selectToRestoreClaim() {
        selectSpecificRadioButton("Restore claim");
        clickConfirmButton();
    }

    public void selectToPermanentlyCloseClaim() {
        selectSpecificRadioButton("Close claim permanently");
        clickConfirmButton();
    }

    public void assertArchiveIdentityRejectedIsRequiredErrorMessage() {
        archiveIdentityRejectedIsRequiredErrorMessage
                .shouldContainText("Archive: identity rejected is required");
    }

    public void assertArchiveEligibilityRejectedIsRequiredErrorMessage() {
        archiveEligibilityRejectedIsRequiredErrorMessage
                .shouldContainText("Archive: eligibility rejected is required");
    }

}
