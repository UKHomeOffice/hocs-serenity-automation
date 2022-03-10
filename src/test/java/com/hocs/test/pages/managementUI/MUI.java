package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.MuiLoginPage;
import com.hocs.test.pages.decs.BasePage;

public class MUI extends BasePage {

    MuiLoginPage muiLoginPage;

    MUIDashboard muiDashboard;

    WithdrawACase withdrawACase;

    public void withdrawACaseInMUI(String caseReference) {
        muiLoginPage.open();
        muiDashboard.selectDashboardLinkWithText("Withdraw a case");
        withdrawACase.withdrawACase(caseReference, getTodaysDate(), "Withdrawn for stage 2 case creation");
    }
}
