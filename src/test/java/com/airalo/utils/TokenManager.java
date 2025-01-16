package com.airalo.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    private static String authToken;

    public static String getAuthToken() {
        if (authToken == null) {
            authToken = fetchNewToken();
        }
        return authToken;
    }

    private static String fetchNewToken() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", com.airalo.config.ApiConfig.CLIENT_ID);
        formParams.put("client_secret", com.airalo.config.ApiConfig.CLIENT_SECRET);

        Response response = RestAssured.given()
                .baseUri(com.airalo.config.ApiConfig.BASE_URL2)
                .accept("application/json")
                .formParams(formParams)
                .post();

        System.out.println("###### \n"+ response.asString());
        return response.jsonPath().getString("data.access_token");
    }
}
