package com.gb.fineos.integration.services;

import com.gb.fineos.integration.rest.RestApiCall;
import io.restassured.response.Response;

public class ProvidePRC extends RestApiCall {

    private final String providePRCUrl = "/process/payments/batch/provideprc" + "/" + getProperty("dynamics.sourcesystem") + "/" + getProperty("dynamics.company");

    public Response callService() {
        String endpoint = getProperty(ESB_URL_PROPERTY) + providePRCUrl;
        return getRestApiWithAuthorization(endpoint, getProperty("esb.provideap.authtoken"));
    }
}
