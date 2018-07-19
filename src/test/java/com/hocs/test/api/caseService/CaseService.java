package com.hocs.test.api.caseService;

import static config.Environments.LOCAL;
import static config.Services.CASE;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class CaseService extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    public void getInfo() {
        Serenity.setSessionVariable("getInfo")
                .to(LOCAL.getEnvironmentURL() + CASE.getPort() + "/actuator/info");
    }

    public void assertResponse(int statusCode) {
        response.then().statusCode(statusCode);
    }

}
