package com.ilhamyp.api.context;

import io.restassured.response.Response;

public class TestContext {
    public static Response lastResponse;
    public static String authToken;
    public static String userId;
}