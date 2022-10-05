package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;

public class Campaign extends BasePage {

    public void selectACampaign() {
        waitForDECSPageWithTitle("Specify campaign name");
        String selectedCampaign = selectRandomOptionFromTypeaheadWithHeading("Campaign");
        setSessionVariable("campaign").to(selectedCampaign);
    }

    public void selectASpecificCampaign(String campaign) {
        waitForDECSPageWithTitle("Specify campaign name");
        String selectedCampaign = selectSpecificOptionFromTypeaheadWithHeading(campaign, "Campaign");
        setSessionVariable("campaign").to(selectedCampaign);
    }
}
