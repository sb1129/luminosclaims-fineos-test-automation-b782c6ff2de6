package com.gb.fineos.script.integration.esb;

import com.gb.fineos.integration.services.InsufficientFunds;
import com.gb.fineos.integration.rest.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class InsufficientFundsIntegrationTest {

    @Test(groups = {"integration.esb.au", "integration.esb.icare", "integration.esb.nz", "integration.esb.uk"}, description = "Insufficient Funds / Threshold Breach ESB batch job scenario for Success status")
    public void callESBInsufficientFundsEndpointSuccessStatusTest() {
        InsufficientFunds insufficientFunds = new InsufficientFunds();
        Response response = insufficientFunds.callService();
        ResponseValidator.assertStatus(response, ResponseValidator.SUCCESS_STATUS_MSG);
    }
}

