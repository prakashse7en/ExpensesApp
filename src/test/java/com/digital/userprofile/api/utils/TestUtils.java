package com.digital.userprofile.api.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestUtils {

    public static Response getPostResponse(String token) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(
                        """
                                {
                                    "userName":"seconduser",
                                    "userPhoneNumber":"+85265783645",
                                    "userEmail":"sample@add.com"
                                }
                                """
                )
                .when()
                .post("/api/user");
        return response;
    }
}
