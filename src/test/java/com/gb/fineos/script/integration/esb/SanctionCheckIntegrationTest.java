package com.gb.fineos.script.integration.esb;

import com.gb.fineos.integration.rest.ResponseValidator;
import com.gb.fineos.integration.services.SanctionCheck;
import org.testng.annotations.Test;

public class SanctionCheckIntegrationTest {
    @Test(groups = {"integration.esb.uk"}, description = "SanctionCheck ESB batch job integration test for 200 response status")
    public void performSanctionCheckForPaymentsSuccessStatusTest() {
        ResponseValidator.assertHTTPStatus200(new SanctionCheck().callService());
    }
}
