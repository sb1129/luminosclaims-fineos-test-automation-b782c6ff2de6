package com.gb.fineos.integration.services;

import com.gb.fineos.integration.rest.RestApiCall;
import io.restassured.response.Response;

public class ProvideAP extends RestApiCall {

    private final String provideAPUrl = "/process/payments/batch/provideap";

    public Response callService() {
        String endpoint = getProperty(ESB_URL_PROPERTY) + provideAPUrl;
        return getRestApiWithAuthorization(endpoint, getProperty("esb.provideap.authtoken"));
    }
}
