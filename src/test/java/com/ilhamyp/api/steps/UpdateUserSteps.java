package com.ilhamyp.api.steps;

import com.ilhamyp.api.config.ConfigManager;
import com.ilhamyp.api.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class UpdateUserSteps {

    @Given("I am authenticated")
    public void ensureAuthenticated() {
        assertTrue("No auth token found — run login first", TestContext.authToken != null);
    }

    @When("I update user with id {string} setting name {string} and job {string}")
    public void updateUser(String userId, String name, String job) {
        RestAssured.baseURI = ConfigManager.get("BASE_URL");
        String body = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);

        TestContext.lastResponse = given()
                .header("x-api-key", ConfigManager.get("REQRES_API_KEY"))
                .header("Authorization", "Bearer " + TestContext.authToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/api/users/" + userId);
    }

    @Then("the response should contain the updated name and job")
    public void verifyUpdatedFields() {
        assertEquals(200, TestContext.lastResponse.statusCode());
        assertEquals("Ilham Yulianto", TestContext.lastResponse.jsonPath().getString("name"));
        assertEquals("QA Engineer", TestContext.lastResponse.jsonPath().getString("job"));
    }

    @Then("the response should contain an {string} timestamp")
    public void verifyTimestamp(String field) {
        assertNotNull(TestContext.lastResponse.jsonPath().getString(field));
    }
}
