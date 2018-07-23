package com.hocs.test.api.workflow;

import static config.Environments.LOCAL;
import static config.Services.WORKFLOW;
import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;

public class WorkflowService extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    public void getInfo() {
        response = RestAssured.given()
                .get(LOCAL.getEnvironmentURL() + WORKFLOW.getPort() + "/actuator/info");
    }

    public void assertResponse(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void assertResponseBody() {
        response.then().
                statusCode(200).
                body("app.name", is("hocs-workflow"),
                        "app.fullname", is("Hocs Workflow Service"));
    }

}
