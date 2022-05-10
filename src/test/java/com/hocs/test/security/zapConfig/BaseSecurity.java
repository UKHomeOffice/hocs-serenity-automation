package com.hocs.test.security.zapConfig;

import static java.lang.String.format;

import com.google.common.base.Preconditions;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.zaproxy.clientapi.core.Alert;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ApiResponseList;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

@Slf4j
public class BaseSecurity extends BaseClassOnDemandDriverSetupWithProxy {

    public static ClientApi clientApi = new ClientApi(zapProxyHost, Integer.parseInt(zapProxyPort), null);
    public static String securityTestReportPath = getProperty("zap.report.path");

    public static void spiderTarget(String targetURL) throws InterruptedException, ClientApiException {
        String application_base_url = "https:<application_url>/";  //Example code to start and synchronize the spider scan.private static void
        // startSpiderScan()
        log.info("Spider : {}", application_base_url);

        // Start spider scan
        ApiResponse apiResponse = clientApi.spider.scan(targetURL, null, null, null, null);
        int progress;

        // Scan response returns scan id to support concurrent scanning.
        String scanId = ((ApiResponseElement) apiResponse).getValue();
        // Poll the status till over.
        do {
            Thread.sleep(5000);
            progress = Integer.parseInt(((ApiResponseElement) clientApi.spider.status(scanId)).getValue());
            log.info("Scan progress: {}{}", progress, "%");
        } while (progress < 100);
        log.info("scan complete");
        // Process the spider results if needed
        List<ApiResponse> spiderResults = ((ApiResponseList) clientApi.spider.results(scanId)).getItems();
        log.info("spider results {}", spiderResults);
    }


    public static void waitForPassiveScanToComplete() throws ClientApiException {
        log.info("--- Waiting for passive scan to finish --- ");
        try {
            // Passive scanner are run by default: https://stackoverflow.com/a/35944273/270835
            clientApi.pscan.enableAllScanners();//  enables all passive scanner.

            ApiResponse response = clientApi.pscan.recordsToScan();

            // iterating till pending scan count is 0
            while (!response.toString().equals("0")) {
                response = clientApi.pscan.recordsToScan();
            }
        } catch (ClientApiException e) {
            System.out.println(e);
            throw new ClientApiException("Was zap proxy started?", e);
        }
        log.info("--- Passive scan finished! ---");
    }

    public static void activeScan(String targetURL) throws InterruptedException, ClientApiException {
        log.info("Active scan: {}", targetURL);

        // Start active scan
        ApiResponse resp = clientApi.ascan.scan(targetURL, "True", "False", null, null, null);
        int progress;

        // Scan response returns scan id to support concurrent scanning.
        String scanId = ((ApiResponseElement) resp).getValue();

        // Poll the status till over.
        do {
            Thread.sleep(5000);
            progress = Integer.parseInt(((ApiResponseElement) clientApi.ascan.status(scanId)).getValue());
            log.info("Scan progress: {}{}", progress, "%");
        } while (progress < 100);
        System.out.println("Active scan complete");
    }

    public static void checkRiskCount(String filterURL) throws ClientApiException {
        log.info("Target URL {}", filterURL);

        int riskCountHigh = 0;
        int riskCountMedium = 0;
        int riskCountLow = 0;
        int riskCountInformational = 0;
        int totalRiskCount;

        List<Alert> alertList = clientApi.getAlerts(filterURL, 0, 9999999);
        for (Alert alert : alertList) {
            String riskName = alert.getRisk().name();
            Alert.Risk risk = alert.getRisk();
            switch (risk) {
                case High:
                    riskCountHigh = riskCountHigh + 1;
                    break;
                case Medium:
                    riskCountMedium = riskCountMedium + 1;
                    break;
                case Low:
                    riskCountLow = riskCountLow + 1;
                    break;
                case Informational:
                    riskCountInformational = riskCountInformational + 1;
                    break;
                default:
                    throw new IllegalStateException(format("Unknown risk level %s", riskName));
            }
        }
        totalRiskCount = riskCountHigh + riskCountMedium + riskCountLow + riskCountInformational;
        log.info("Total risk count: {}", totalRiskCount);
        Preconditions.checkState(totalRiskCount != 0,
                format("Page %s" +
                                "\nHigh Risk count: %s" +
                                "\nMedium Risk count: %s" +
                                "\nLow Risk count: %s" +
                                "\nInformational Risk count: %s" +
                                "\nplease check: %s",
                        filterURL, riskCountHigh, riskCountMedium, riskCountLow, riskCountInformational, securityTestReportPath));
    }

}