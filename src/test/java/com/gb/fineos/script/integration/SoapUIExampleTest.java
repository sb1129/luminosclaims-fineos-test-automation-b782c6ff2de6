package com.gb.fineos.script.integration;

import com.gb.fineos.integration.soapui.SoapUIBase;
import org.testng.annotations.Test;

public class SoapUIExampleTest extends SoapUIBase {

    @Test(groups = "integration.common")
    public void testSoapUIEndpointsCallsNexus() throws Exception {
        runTestCases("NexusTest.xml");
    }
}
