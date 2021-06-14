package com.gb.fineos.script.integration.esb;

import com.gb.fineos.integration.services.ProvideAP;
import com.gb.fineos.integration.rest.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ProvideAPIntegrationTest {

    @Test(groups = {"integration.esb.au", "integration.esb.icare", "integration.esb.nz", "integration.esb.uk"}, description = "ProvideAP ESB batch job scenario for Success status")
    public void callESBProvideAPEndpointSuccessStatusTest() {
        ProvideAP provideAP = new ProvideAP();
        Response response = provideAP.callService();
        ResponseValidator.assertStatus(response, ResponseValidator.SUCCESS_STATUS_MSG);
    }
}
