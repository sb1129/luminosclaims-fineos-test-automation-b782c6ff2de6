package com.gb.fineos.integration.services;

import com.gb.fineos.integration.rest.RestApiCall;
import io.restassured.response.Response;


public class CashReceipts extends RestApiCall {

    private final String cashReceiptsUrl = "/process/payments/batch/cashreceipts" + "/" + getProperty("dynamics.sourcesystem") + "/" + getProperty("dynamics.company");

    public Response callService() {
        String endpoint = getProperty(ESB_URL_PROPERTY) + cashReceiptsUrl;
        return getRestApiWithAuthorization(endpoint, getProperty("esb.cashreceipts.authtoken"));
    }

}

