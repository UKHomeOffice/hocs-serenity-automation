package com.hocs.test.api;

import static config.Environments.DEV;
import static config.Environments.LOCAL;
import static config.Services.CASE;
import static config.Services.WORKFLOW;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;

public class ApiHelper extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    private Headers headers;

    public void setupApiHelper(String api) {
        String environment = System.getProperty("environment");
        if (environment == null) {
            environment = "DEV";
        }

        switch (environment.toUpperCase()) {
            case "DEV":
                RestAssured.baseURI = DEV.getEnvironmentURL();
                switch (api.toUpperCase()) {
                    case "CASE":
                        RestAssured.port = Integer.parseInt(DEV.getApiPort());
                        break;
                    case "WORKFLOW":

                        break;
                    default:
                        fail("Service: " + api
                                + " is not defined within ApiHelper.setupApiHelper()");
                        break;
                }
                break;
            case "LOCAL":
                RestAssured.baseURI = LOCAL.getEnvironmentURL();
                switch (api.toUpperCase()) {
                    case "CASE":
                        RestAssured.port = CASE.getPort();
                        break;
                    case "WORKFLOW":
                        RestAssured.port = WORKFLOW.getPort();
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

