package com.ilhamyp.api.steps;

import com.ilhamyp.api.config.ConfigManager;
import com.ilhamyp.api.context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class LoginSteps {

    @When("I send a login request with email {string} and password {string}")
    public void loginUser(String email, String password) {
        RestAssured.baseURI = ConfigManager.get("BASE_URL");
        String body = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        TestContext.lastResponse = given()
                .header("x-api-key", ConfigManager.get("REQRES_API_KEY"))
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/login");

        TestContext.authToken = TestContext.lastResponse.jsonPath().getString("token");
    }

    @Then("I should receive an authentication token")
    public void checkToken() {
        assertNotNull(TestContext.authToken);
    }
}
