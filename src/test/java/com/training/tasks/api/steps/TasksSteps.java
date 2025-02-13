package com.training.tasks.api.steps;
import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

public class TasksSteps {

    private Response response;
    private String createdTaskId;

    @Given("the client makes a POST request with title {string} and description {string}")
    public void the_client_makes_a_POST_request(String title, String description) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("description", description);
        payload.put("completionDate", "2025-01-18T14:00:00");
        payload.put("completed", false);

        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/tasks")
                .then()
                .extract()
                .response();

        createdTaskId = response.jsonPath().getString("id");
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("title")).isEqualTo(payload.get("title"));
        assertThat(response.jsonPath().getString("description")).isEqualTo(payload.get("description"));
        assertThat(response.jsonPath().getString("completionDate")).startsWith(payload.get("completionDate").toString());
        assertThat(response.jsonPath().getBoolean("completed")).isEqualTo(payload.get("completed"));
    }

    @When("the client makes a GET request")
    public void the_client_makes_a_GET_request() {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/tasks/" + createdTaskId)
                .then()
                .extract()
                .response();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("id")).isEqualTo(createdTaskId);
    }

    @When("the client calls GET Tasks")
    public void the_client_calls_GET_Tasks() {
        response = when().get("/tasks");
    }

    @Then("the api returns with status code {int}")
    public void the_api_returns_status_code_of(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }
}
