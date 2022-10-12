package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;

public class StopList extends BasePage {

    public void selectAStopList() {
        waitForDECSPageWithTitle("Specify Stop List name");
        String selectedStopList = selectRandomOptionFromDropdownWithHeading("Stop List");
        setSessionVariable("stopList").to(selectedStopList);
    }

    public void selectASpecificStopList(String stopList) {
        waitForDECSPageWithTitle("Specify Stop List name");
        selectSpecificOptionFromDropdownWithHeading(stopList, "Stop List");
        setSessionVariable("stopList").to(stopList);
    }
}
