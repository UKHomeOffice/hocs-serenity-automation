package com.hocs.test.api.workflowService;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import net.serenitybdd.core.pages.PageObject;

public class WorkflowService extends PageObject {

    public void assertResponseBody() {
        given().when().get().then().
                statusCode(200).
                body("app.name", is("hocs-workflow"),
                        "app.fullname", is("Hocs Workflow Service"));
    }

}
