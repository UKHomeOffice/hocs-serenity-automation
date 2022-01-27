package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class Campaign extends BasePage {

    public void selectTheAction(String action) {
        selectSpecificRadioButtonFromGroupWithHeading(action, "Actions");
    }

    public void selectACampaign() {
        waitForPageWithTitle("Specify campaign name");
        String selectedCampaign = selectRandomOptionFromTypeaheadWithHeading("Campaign");
        setSessionVariable("campaign").to(selectedCampaign);
    }
}
