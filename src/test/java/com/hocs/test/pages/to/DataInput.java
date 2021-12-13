package com.hocs.test.pages.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class DataInput extends BasePage {

    RecordCaseData recordCaseData;

    public void selectABusinessArea() {
        String selectedBusinessArea = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
        setSessionVariable("businessArea").to(selectedBusinessArea);
    }

    public void selectAChannelRecieved() {
        String selectedChannelRecieved = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel Received");
        setSessionVariable("channelReceived").to(selectedChannelRecieved);
    }

    public void selectAnAdressee() {
        String selectedAddressee = recordCaseData.selectRandomOptionFromDropdownWithHeading("Addressee");
        setSessionVariable("addressee").to(selectedAddressee);
    }
}
