package com.gb.fineos.script.integration.esb;

import com.gb.fineos.integration.services.ProvidePRC;
import com.gb.fineos.integration.rest.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ProvidePRCIntegrationTest {

    @Test(groups = {"integration.esb.au", "integration.esb.icare", "integration.esb.nz", "integration.esb.uk"}, description = "ProvidePRC ESB batch job scenario for Success status")
    public void callESBProvidePRCEndpointSuccessStatusTest() {
        ProvidePRC providePRC = new ProvidePRC();
        Response response = providePRC.callService();
        ResponseValidator.assertStatus(response, ResponseValidator.SUCCESS_STATUS_MSG);

    }
}
