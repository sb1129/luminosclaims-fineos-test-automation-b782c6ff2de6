package com.gb.fineos.script.integration.esb;

import com.gb.fineos.integration.services.CashReceipts;
import com.gb.fineos.integration.rest.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CashReceiptsIntegrationTest {

    @Test(groups = {"integration.esb.au", "integration.esb.icare", "integration.esb.nz", "integration.esb.uk"}, description = "Cash Receipts ESB batch job scenario for Success status")
    public void callESBCashReceiptsEndpointSuccessStatusTest() {
        CashReceipts cashReceipts = new CashReceipts();
        Response response = cashReceipts.callService();
        ResponseValidator.assertStatus(response, ResponseValidator.SUCCESS_STATUS_MSG);
    }
}

