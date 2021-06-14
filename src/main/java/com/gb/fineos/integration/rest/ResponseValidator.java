package com.gb.fineos.integration.rest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;

public final class ResponseValidator {

    public static final String SUCCESS_STATUS_MSG = "Success";

    private ResponseValidator() {
        // Do nothing.
    }

    public static void assertSuccess(final Response response) {
        Assert.assertTrue(response.jsonPath().get("batchOutput.batchStatus").toString().equalsIgnoreCase(SUCCESS_STATUS_MSG));
    }

    public static void assertStatus(final Response response, final String expectedStatus) {
        Assert.assertTrue(response.jsonPath().get("batchOutput.batchStatus").toString().equalsIgnoreCase(expectedStatus));
    }

    public static void assertHTTPStatus200(final Response response) {
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }
}
