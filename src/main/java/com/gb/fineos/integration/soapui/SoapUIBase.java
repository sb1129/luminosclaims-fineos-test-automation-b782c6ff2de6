package com.gb.fineos.integration.soapui;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import com.gb.fineos.factory.PropertiesFactory;

import java.util.Objects;

public class SoapUIBase {

    private static final String PATH_TO_PROJECTS = "integration/projects/";

    public void runTestCases(final String projectFileName) throws Exception {
        System.setProperty("soapui.log4j.config", "target/test-classes/soapui-log4j.xml");
        System.setProperty("soapui.logroot", "target/logs/");

        SoapUITestCaseRunner testCaseRunner = new SoapUITestCaseRunner();
        String projectPath = Objects.requireNonNull(getClass().getClassLoader().getResource(PATH_TO_PROJECTS + projectFileName), "Project file not found: " + projectFileName).getPath();
        testCaseRunner.setProjectFile(projectPath);
        testCaseRunner.setPrintReport(true);
        testCaseRunner.setOutputFolder("target/");
        testCaseRunner.setJUnitReport(true);

        testCaseRunner.run();
        testCaseRunner.getAssertionResults().forEach((testAssertion, wsdlTestStepResult) -> System.out.println(wsdlTestStepResult.getStatus().toString()));
    }

    protected String getProperty(final String key) {
        return PropertiesFactory.getInstance().getProperties().getProperty(key);
    }
}
