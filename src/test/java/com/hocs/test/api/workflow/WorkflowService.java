package com.hocs.test.api.workflow;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;

public class WorkflowService extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    public void assertResponseBody() {
        given().when().get().then().
                statusCode(200).
                body("app.name", is("hocs-workflow"),
                        "app.fullname", is("Hocs Workflow Service"));
    }

}
