package com.hocs.test.api.caseService;

import static io.restassured.RestAssured.given;

import config.Environment;
import config.Service;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;

class CaseService extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    public void getInfo() {
        RestAssured.baseURI = Environment.LOCAL.getEnvironmentURL();
        RestAssured.port = Service.CASE.getPort();
    }

    public void assertResponse(int statusCode) {
        given().when().get("/actuator/info").then().statusCode(statusCode);
    }

}
