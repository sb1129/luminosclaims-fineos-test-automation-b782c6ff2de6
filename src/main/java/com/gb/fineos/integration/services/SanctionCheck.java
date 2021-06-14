package com.gb.fineos.integration.services;

import com.gb.fineos.integration.rest.RestApiCall;
import io.restassured.response.Response;

public class SanctionCheck extends RestApiCall {
    private final String sanctionCheckUrl = "/process/payments/batch/sanctioncheck" + "/" + getProperty("dynamics.sourcesystem") + "/" + getProperty("dynamics.company");

    public Response callService() {
        String endpoint = getProperty(ESB_URL_PROPERTY) + sanctionCheckUrl;
        return getRestApiWithAuthorization(endpoint, getProperty("esb.sanctioncheck.authtoken"));
    }
}
