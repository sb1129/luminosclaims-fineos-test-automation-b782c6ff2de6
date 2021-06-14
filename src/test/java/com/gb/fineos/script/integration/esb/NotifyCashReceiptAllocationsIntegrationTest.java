package com.gb.fineos.script.integration.esb;

import com.gb.fineos.integration.services.NotifyCashReceiptAllocations;
import com.gb.fineos.integration.rest.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class NotifyCashReceiptAllocationsIntegrationTest {

    @Test(groups = {"integration.esb.au", "integration.esb.icare", "integration.esb.nz", "integration.esb.uk"}, description = "Notify Cash Allocations ESB batch job scenario for Success status")
    public void callESBNotifyCashReceiptAllocationsEndpointSuccessStatusTest() {
        NotifyCashReceiptAllocations notifyCashReceiptAllocations = new NotifyCashReceiptAllocations();
        Response response = notifyCashReceiptAllocations.callService();
        ResponseValidator.assertStatus(response, ResponseValidator.SUCCESS_STATUS_MSG);
    }
}

