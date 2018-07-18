package com.ndelius.test.api.workflow;

import static config.Environments.LOCAL;
import static config.Services.WORKFLOW;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;

public class WorkflowService extends PageObject {

    private Response response;
    private RequestSpecification request;
    private Response post;

    public void getInfo() {
        response = RestAssured.given().
                get(LOCAL.getEnvironmentURL() + WORKFLOW.getService() + "/actuator/info");
    }

}
