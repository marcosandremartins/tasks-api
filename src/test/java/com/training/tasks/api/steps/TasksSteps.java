package com.training.tasks.api.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Value;

public class TasksSteps {

    private Response response;
    private String payload;

     @Value("${service.url}")
    private String apiUrl;
    
    @When("the client calls GET Tasks")
    public void the_client_calls_GET_Tasks() {
        response = when().get("/tasks");
    }

    @Then("the api returns with status code {int}")
    public void the_api_returns_status_code_of(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Given("I have a valid payload to create an appointment")
    public void validAppointmentPayload(String body) {
        payload = body;
    }

    @When("I send a POST request to {string}")
    public void iSendAPostRequestTo(String endpoint) {
        RestAssured.baseURI = apiUrl; 
        response = 
            given()
                .header("Content-Type", "application/json")
                .body(payload)
            .when()
                .post(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

     @Then("the response should contain the appointment details")
    public void theResponseShouldContainTheAppointmentDetails() {
        response.then()
            .body("title", equalTo("appointment"))
            .body("description", equalTo("just a new appointment"))
            .body("completionDate", equalTo("2025-01-18T14:00:00"))
            .body("completed", equalTo(false));
    }
}
