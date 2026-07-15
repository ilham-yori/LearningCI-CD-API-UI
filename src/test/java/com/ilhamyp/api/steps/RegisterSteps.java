package com.ilhamyp.api.steps;

import com.ilhamyp.api.config.ConfigManager;
import com.ilhamyp.api.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegisterSteps {

    @Given("the API base url is set")
    public void setBaseUrl() {
        RestAssured.baseURI = ConfigManager.get("BASE_URL");
    }

    @When("I send a registration request with email {string} and password {string}")
    public void registerUser(String email, String password) {
        String body = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        TestContext.lastResponse = given()
                .header("x-api-key", ConfigManager.get("REQRES_API_KEY"))
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/register");
    }

    @Then("the response status code should be {int}")
    public void checkStatusCode(int statusCode) {
        assertEquals(statusCode, TestContext.lastResponse.statusCode());
    }

    @Then("the response should contain an {string} and {string}")
    public void checkResponseFields(String field1, String field2) {
        assertNotNull(TestContext.lastResponse.jsonPath().get(field1));
        assertNotNull(TestContext.lastResponse.jsonPath().get(field2));
    }
}
