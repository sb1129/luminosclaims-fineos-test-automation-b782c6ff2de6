package com.gb.fineos.integration.services;

import com.gb.fineos.integration.rest.RestApiCall;
import io.restassured.response.Response;


public class InsufficientFunds extends RestApiCall {

    private final String insufficientFundsUrl = "/process/payments/batch/insufficientfunds" + "/" + getProperty("dynamics.sourcesystem") + "/" + getProperty("dynamics.company");

    public Response callService() {
        String endpoint = getProperty(ESB_URL_PROPERTY) + insufficientFundsUrl;
        return getRestApiWithAuthorization(endpoint, getProperty("esb.insufficientfunds.authtoken"));
    }

}
