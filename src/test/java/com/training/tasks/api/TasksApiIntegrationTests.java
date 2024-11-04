package com.training.tasks.api;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TasksApiApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TasksApiIntegrationTests {

    @Value("${service.url}")
    private String apiUrl;

    @Before
    public void setup() {
        RestAssured.baseURI = apiUrl;
    }
}
