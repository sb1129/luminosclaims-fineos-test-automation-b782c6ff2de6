package com.gb.fineos.integration.services;

import com.gb.fineos.integration.rest.RestApiCall;
import io.restassured.response.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class NotifyCashReceiptAllocations extends RestApiCall {

    private final String notifyAllocationsUri = "/process/payments/batch/notifycashallocation" + "/" + getProperty("dynamics.sourcesystem") + "/" + getProperty("dynamics.company");

    public Response callService() {

        // get the current and previous day dates
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        String previousDayDate = dateFormat.format(cal.getTime());

        String notifyAllocationsUriWithParam = notifyAllocationsUri + "?dateFrom=" + previousDayDate + "&dateTo=" + currentDate;

        String endpoint = getProperty(ESB_URL_PROPERTY) + notifyAllocationsUriWithParam;
        return getRestApiWithAuthorization(endpoint, getProperty("esb.notifycashreceiptsalloc.authtoken"));
    }
}
