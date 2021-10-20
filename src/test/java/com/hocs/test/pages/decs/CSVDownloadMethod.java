package com.hocs.test.pages.decs;

import config.User;
import java.io.IOException;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CSVDownloadMethod extends BasePage {

    LoginPage loginPage;

    public void qaMINCaseDataExtractDownload() {
        String sourceLocation = "\"https://qa.internal.cs-notprod.homeoffice.gov.uk/export/MIN?fromDate=2020-01-01^&toDate=2022-01-01^&exportType=CASE_DATA^&convert=true']\"";
        getDriver().get("https://qa.internal.cs-notprod.homeoffice.gov.uk/export/MIN?fromDate=2020-01-01&toDate=2022-01-01&exportType=CASE_DATA&convert=true']");
        String wget_command =
                "cmd /c C:\\Users\\Cameron.Page\\Wget\\wget.exe -P C:\\Users\\Cameron"
                        + ".Page\\IdeaProjects\\hocs-serenity-automation\\src\\test\\resources\\documents --no-check-certificate \"https://qa"
                        + ".internal"
            + ".cs-notprod.homeoffice.gov.uk/export/MIN?fromDate=2020-01-01^&toDate=2022-01-01^&exportType=CASE_DATA^&convert=true']\"";
        try {
            Process exec = Runtime.getRuntime().exec(wget_command);
            int exitVal = exec.waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println(e.toString());
        }
    }
}
