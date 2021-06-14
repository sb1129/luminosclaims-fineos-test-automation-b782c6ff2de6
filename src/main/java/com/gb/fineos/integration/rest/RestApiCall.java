package com.gb.fineos.integration.rest;

import com.gb.fineos.factory.PropertiesFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestApiCall {

    protected static final String ESB_URL_PROPERTY = "ESB_URL";

    public Response getRestApiWithAuthorization(final String url, final String authorization) {
        return given()
            .header("Authorization", authorization)
            .relaxedHTTPSValidation()
            .log().all()
            .when()
            .get(url)
            .then()
            .log().all()
            .extract().response();
    }

    public Response postApi(final String url, final String body, final ContentType contentType, final Map<String, String> headers) {
        RequestSpecification request = given().relaxedHTTPSValidation().contentType(contentType).contentType(ContentType.TEXT);

        headers.forEach((String key, String value) -> request.header(key, value));

        return request.log().all()
            .body(body)
            .when()
            .post(url)
            .then()
            .log().all()
            .extract().response();
    }

    public void checkStatusCode(final Response response, final int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    public String getBatchStatus(final Response response) {
        return response.jsonPath().get("batchOutput.batchStatus").toString();
    }

    public String getProperty(final String propertyKey) {
        return PropertiesFactory.getInstance().getProperties().getProperty(propertyKey);
    }
}
