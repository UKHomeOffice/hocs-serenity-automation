package com.hocs.test.api;

import static config.Environment.CS_DEV;
import static config.Environment.LOCAL;
import static config.Service.CASE;
import static config.Service.INFO;
import static config.Service.WORKFLOW;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.response.Response;
import net.serenitybdd.core.pages.PageObject;

public class ApiHelper extends PageObject {

    private Response response;

    public void setupApiHelper(String api) {
        String environment = System.getProperty("environment");
        if (environment == null)
            environment = "LOCAL";

        switch (environment.toUpperCase()) {
            case "DEV":
                baseURI = CS_DEV.getEnvironmentURL();
                switch (api.toUpperCase()) {
                    case "CASE":
                        port = CASE.getPort();
                        break;
                    case "INFO":
                        port = INFO.getPort();
                        break;
                    case "WORKFLOW":
                        port = WORKFLOW.getPort();
                        break;
                    default:
                        fail("Service: " + api
                                + " is not defined within ApiHelper.setupApiHelper()");
                        break;
                }
                break;
            case "LOCAL":
                baseURI = LOCAL.getEnvironmentURL();
                basePath = "/actuator/info";
                switch (api.toUpperCase()) {
                    case "CASE":
                        port = CASE.getPort();
                        break;
                    case "WORKFLOW":
                        port = WORKFLOW.getPort();
                        break;
                    default:
                        fail("Service: " + api
                                + " is not defined within ApiHelper.setupApiHelper()");
                        break;
                }
                break;
            default:
                fail("Environment: " + environment +
                        " is not defined within ApiHelper.setupApiHelper()");
        }

    }

    public void assertResponse(int statusCode) {
        given().when().get().then().statusCode(statusCode);
    }

    public void assertResponseBodyContains(String content) {
        given().when().get().then().body(containsString(content));
    }

    public void assertResponseBodyJsonObject(String object, String value) {
        given().when().get().then().body(object, equalTo(value));
    }

    public Response getResponse() {
        return response;
    }

}

