package com.airalo.tests;

import com.airalo.config.ApiConfig;
import com.airalo.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiAutomationTest {

    // This test creates order for 6 esims using order Submit Order API
    // It also verifies the esims data using Get eSIM (/esim/:sim_iccid) api
    @Test
    public void postOrderForESIMs() {
        String accessToken = TokenManager.getAuthToken();

        Response response = given()
                .baseUri(ApiConfig.BASE_URL1)
                .auth().oauth2(accessToken)
                .contentType("application/json")
                .body("{\"package_id\": \"merhaba-7days-1gb\", \"quantity\": 6}")
                .post(ApiConfig.ORDER_ENDPOINT);

        System.out.println("Order response: " + response.asPrettyString());

        //verify http status code
        response.then().statusCode(200)
                .body("data.id", notNullValue());

        // Extract the JSON string from the response body
        String jsonString = response.body().asString();

        // Parse the JSON string into a JSONObject
        JSONObject jsonResponse = new JSONObject(jsonString);

        // Navigate to the "sims" array
        JSONArray simsArray = jsonResponse.getJSONObject("data").getJSONArray("sims");

        System.out.println("Sim Node " + simsArray);

        // Iterate through the sims array to verify esim data
        for (int i = 0; i < simsArray.length(); i++) {
            JSONObject sim = simsArray.getJSONObject(i);
            String iccid = sim.getString("iccid");
            getESIMWithIccid(iccid);
        }
    }

    // This function fetches the eSIMs data for provided ICCID
    public void getESIMWithIccid(String iccid){
        String accessToken = TokenManager.getAuthToken();
        Response response = given()
                .baseUri(ApiConfig.BASE_URL1)
                .auth().oauth2(accessToken)
                .accept("application/json")
                .get(ApiConfig.ESIMS_ENDPOINT+"/"+iccid);

        System.out.println("getESIMWithIccid ==> \n" + response.asPrettyString());

        //verify http status code
        response.then().statusCode(200)
                .body("data.iccid", equalTo(iccid));

    }


    @Disabled("Ignoring this test as simable node is not provided in response")
    @Test
    public void getESIMList() {
        String accessToken = TokenManager.getAuthToken();
        System.out.println("accessToken ==== \n" + accessToken);
        System.out.println(ApiConfig.BASE_URL1 + ApiConfig.ESIMS_ENDPOINT);

        Response response = given()
                .baseUri(ApiConfig.BASE_URL1)
                .auth().oauth2(accessToken)
                .accept("application/json")
                //.queryParam("include", "order,order.status,order.user,share")
                .queryParam("limit", "199")
                .queryParam("page", "1")
                .get(ApiConfig.ESIMS_ENDPOINT);

        System.out.println("Actual Data : ===> \n" + response.asPrettyString());

        //verify http status code
        response.then().statusCode(200)
                .body("data.size()", equalTo(6))
                .body("data.simable.package_id", equalTo("merhaba-7days-1gb"));
    }
}
