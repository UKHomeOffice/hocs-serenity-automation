package com.hocs.test.security.zapConfig;

import com.hocs.test.pages.decs.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;


@Slf4j
public class BaseClassOnDemandDriverSetupWithProxy extends BasePage {

    private WebDriver driver;

    private static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String BASE_URL = getEnvSpecificProperty("login.page.url");

    public static String zapProxyHost = getProperty("zap.proxy.host");

    public static String zapProxyPort = getProperty("zap.proxy.port");

    public WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();

            //set the proxy to use ZAP host and port
            String proxyAddress = zapProxyHost + ":" + zapProxyPort;
            Proxy zap_proxy = new Proxy();
            zap_proxy.setHttpProxy(proxyAddress).setSslProxy(proxyAddress);
            log.info("Set proxy to host:{} and port:{}", zapProxyHost, zapProxyPort);

            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.setCapability(CapabilityType.PROXY, zap_proxy);
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    private static String getEnvSpecificProperty(String property) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(property);
    }

    public static String getProperty(String property) {
        return environmentVariables.getProperty(property);
    }
}
