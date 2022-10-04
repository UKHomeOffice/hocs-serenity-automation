package com.hocs.test.pages.mpam;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class Campaign extends BasePage {

    @FindBy(xpath = "//label[text()='Put case into a Campaign']")
    public WebElementFacade putCaseIntoCampaignRadioButton;

    @FindBy(xpath = "//div[@id='CampaignType']//input")
    public WebElementFacade campaignSelectionTypeahead;

    @FindBy(xpath = "//label[text()='Take out of Campaign, move to triage']")
    public WebElementFacade takeOutOfCampaignMoveToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Take out of Campaign, move to draft']")
    public WebElementFacade takeOutOfCampaignMoveToDraftRadioButton;

    @FindBy(id = "CampaignType")
    public WebElementFacade campaignTypeField;

    public void moveCaseFromAStageToCampaign(String campaign) {
        safeClickOn(putCaseIntoCampaignRadioButton);
        clickConfirmButton();
        String selectedCampaign = selectSpecificOptionFromTypeaheadWithHeading(campaign, "Campaign");
        setSessionVariable("campaign").to(selectedCampaign);
        clickConfirmButton();
    }

    public void moveCaseFromCampaignToStage(String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                safeClickOn(takeOutOfCampaignMoveToTriageRadioButton);
                break;
            case "DRAFT":
                safeClickOn(takeOutOfCampaignMoveToDraftRadioButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        clickConfirmButton();
    }

    public void assertCaseIsMovedToCorrectCampaign() {
        String displayedCampaign = campaignTypeField.getText();
        String inputCampaign = sessionVariableCalled("campaign");
        assertThat(displayedCampaign.equals(inputCampaign), is(true));
    }
}
